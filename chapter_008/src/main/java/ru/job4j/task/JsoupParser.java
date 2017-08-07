package ru.job4j.task;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ru.job4j.jdbc.DataConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;


/**
 * Класс реализующий парсинг сайта sql.ru для подбора вакансий java и записывающий найденные варианты в бд.
 * @author agavrikov
 * @since 02.08.2017
 * @version 1
 */
public class JsoupParser {

    /**
     * Поле для хранения данных о соединении с БД.
     */
    private DataConnection dataConnection = new DataConnection("localhost", "5432", "postgres", "12345678", "task", "postgresql");

    /**
     * Поле для хранения слов, по которым осуществляется поиск.
     */
    private LinkedList<String> findWords = new LinkedList<String>();

    /**
     * Поле для хранения слов, по которым отбрасываются предложения о работе.
     */
    private LinkedList<String> excludedWords = new LinkedList<String>();

    /**
     * Поле для хранения предложений о работе, которые необходимо поместить в бд.
     */
    private Queue<Element> offers = new LinkedList<Element>();

    /**
     * Поле для хранения ссылок на записи в бд, для исключения дублирования.
     */
    private HashSet<String> doubleControl = new HashSet<String>();

    /**
     * Поле для хранения алиасов месяцев.
     */
    private HashSet<Alias> mounthAliases = new HashSet<Alias>();

    /**
     * Поле для хранения данных о последнем запуске программы.
     */
    private long lastStart = 0;

    /**
     * Поле для хранения временного интервала(в мс), через который необходимо повторно запустить программу.
     */
    private long timeInterval = 0;

    /**
     * Конструктор для инициализации.
     * @param findWords список слов по которым осуществляем поиск
     * @param excludedWords список слов, по которым отбрасываем предложения.
     * @param timeInterval временной интервал, через который необходимо повторно запускать программу(мс)
     */
    public JsoupParser(LinkedList<String> findWords, LinkedList<String> excludedWords, long timeInterval) {
        this.findWords = findWords;
        this.excludedWords = excludedWords;
        this.timeInterval = timeInterval;

        //Заполним алиасы месяцев
        mounthAliases.add(new Alias("янв", "1"));
        mounthAliases.add(new Alias("фев", "2"));
        mounthAliases.add(new Alias("мар", "3"));
        mounthAliases.add(new Alias("апр", "4"));
        mounthAliases.add(new Alias("май", "5"));
        mounthAliases.add(new Alias("июн", "6"));
        mounthAliases.add(new Alias("июл", "7"));
        mounthAliases.add(new Alias("авг", "8"));
        mounthAliases.add(new Alias("сен", "9"));
        mounthAliases.add(new Alias("окт", "10"));
        mounthAliases.add(new Alias("ноя", "11"));
        mounthAliases.add(new Alias("дек", "12"));
    }

    /**
     * Основной метод для начала парсинга сайта.
     */
    public void parseSite() {
        while (true) {
            int lastPage;
            Document doc;
            try {
                URL url = new URL("http://www.sql.ru/forum/job");
                doc = Jsoup.parse(url, 10000);
                Element element = doc.body().getElementsByClass("sort_options").last();
                Element a = element.getElementsByTag("a").last();
                lastPage = Integer.parseInt(a.text());
                createDoubleControl();
                for (int i = 1; i <= lastPage; i++) {
                    url = new URL(String.format("http://www.sql.ru/forum/job/%s", i));
                    if (!findWordInDoc(Jsoup.parse(url, 10000))) {
                        break;
                    }
                }
                createDataInDb();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                lastStart = System.currentTimeMillis();
                Thread.sleep(timeInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод для парсинга документа (например HTML) для установления даты и наличия искомых слов.
     * @param doc Документ, на котором необходимо произвести поиск
     * @return булево true  если мы не достигли предельной даты и времени для поиска. false - если достигли
     */
    private boolean findWordInDoc(Document doc) {
        boolean needNextPage = true;
        for (Element trForum : doc.body().getElementsByClass("forumTable").first().getElementsByTag("tr")) {
            if (trForum.getElementsByClass("postslisttopic").first() != null) {
                Element a = trForum.getElementsByClass("postslisttopic").first().getElementsByTag("a").first();
                if (textContainsWord(a.text())) {
                    if (checkDateOffer(trForum.getElementsByTag("td").last().text())) {
                        System.out.println(a.text());
                        offers.add(a);
                    } else {
                        needNextPage = false;
                        break;
                    }
                }
            }
        }
        return needNextPage;
    }

    /**
     * Вспомогательный метод для поиска необходимых слов и изъятий предложений с не нужными словами.
     * @param text - текст предложения.
     * @return true если не найдено ненужных слов и найдено нужное.
     */
    private boolean textContainsWord(String text) {
        boolean result = true;
        for (String s : excludedWords) {
            if (text.split(s).length > 1) {
                result = false;
                break;
            }
        }
        if (result) {
            result = false;
            for (String s : findWords) {
                if (text.split(s).length > 1) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Вспомогательный метод для проверки даты предложения.
     * @param date дата предложения
     * @return true если необходимо учесть данное предложение, false - если нет.
     */
    private boolean checkDateOffer(String date) {
        boolean result = false;
        SimpleDateFormat formatForDate = new SimpleDateFormat("dd MM yy, hh:mm");
        StringBuffer formatDate = new StringBuffer(date);
        Calendar calendar = Calendar.getInstance();
        if (formatDate.indexOf("сегодня") != -1) {
            formatDate.replace(formatDate.indexOf("сегодня"), formatDate.indexOf(","), String.format("%s %s %s", calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR)));
        } else if (formatDate.indexOf("вчера") != -1) {
            calendar.add(Calendar.DATE, -1);
            formatDate.replace(formatDate.indexOf("вчера"), formatDate.indexOf(","), String.format("%s %s %s", calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR)));
        } else {
            for (Alias alias : mounthAliases) {
                if (formatDate.indexOf(alias.getWord()) != -1) {
                    formatDate.replace(formatDate.indexOf(alias.getWord()), formatDate.indexOf(" ", formatDate.indexOf(alias.getWord())), alias.getAlias());
                    break;
                }
            }
        }
        try {
            long dateOffer = formatForDate.parse(formatDate.toString()).getTime();
            long startYear = formatForDate.parse(String.format("%s %s %s, 00:00", "01", "01", calendar.get(Calendar.YEAR))).getTime();
            if ((lastStart == 0 && startYear < dateOffer) || (lastStart > 0 && dateOffer > lastStart)) {
                result = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод для помещения найденных результатов в бд.
     */
    private void createDataInDb() {
        boolean executeQuery = false;
        StringBuilder query = new StringBuilder("INSERT INTO job_offers (text, href) VALUES ");
        for (Element a : offers) {
            String href = a.attr("href");
            String text = a.text();
            if (!doubleControl.contains(href)) {
                query.append(String.format("('%s', '%s'),", text, href));
                doubleControl.add(href);
                executeQuery = true;
            }
        }
        if (executeQuery) {
            query.delete(query.length() - 1, query.length());
            try (Connection conn = DriverManager.getConnection(dataConnection.urlConnection(), dataConnection.getUser(), dataConnection.getPassword());
                 PreparedStatement ps = conn.prepareStatement(query.toString())) {
                ps.execute();

            } catch (SQLException e) {
                e.getStackTrace();
            }
        }
    }

    /**
     * Метод для создания защиты от дублей, при наличии записей в бд.
     */
    public void createDoubleControl() {
        try (Connection conn = DriverManager.getConnection(dataConnection.urlConnection(), dataConnection.getUser(), dataConnection.getPassword());
             PreparedStatement ps = conn.prepareStatement("SELECT href FROM job_offers")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                doubleControl.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    /**
     * Точка входа в программу.
     * @param args параметры.
     */
    public static void main(String[] args) {
        LinkedList<String> findWords = new LinkedList<String>();
        findWords.add("Java");
        findWords.add("java");

        LinkedList<String> excludedWords = new LinkedList<String>();
        //отбросим js
        excludedWords.add("javascript");
        excludedWords.add("java script");
        excludedWords.add("JavaScript");
        excludedWords.add("Javascript");
        excludedWords.add("Java Script");
        excludedWords.add("Java script");
        excludedWords.add("java Script");
        //Отбросим прикрепленные посты
        excludedWords.add("Сообщения от модераторов");
        excludedWords.add("Правила форума");
        excludedWords.add("Студентам, желающим помощи");


        JsoupParser jsoupParser = new JsoupParser(findWords, excludedWords, 1000 * 60 * 60 * 24);
        jsoupParser.parseSite();

    }
}
