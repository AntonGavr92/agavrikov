package ru.job4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class отвечающий за начало работы модуля.
 * @author agavrikov
 * @since 09.07.2017
 * @version 1
 */
public class StartUI {

    /**
     * Поле для хранения объекта, отвечающего за ввод/вывод.
     */
    private Input input;

    /**
     * Поле для хранения объекта трекера.
     */
    private Tracker tracker;

    /**
     * Конструктор.
     * @param input - объект ввода/вывода
     * @param tracker - объект трекер
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Метод, инициализирующий работу программы.
     */
    public void init() {
        boolean exit = false;

        checkDb();

        MenuTracker menu = new MenuTracker();

        while (!exit) {
            ArrayList<Integer> range = new ArrayList<Integer>();

            for (UserAction menuItem : menu.getActions()) {
                input.print(menuItem.info());
                range.add(menuItem.key());
            }
            int indexAction = input.ask("Select: ", range);
            exit = menu.select(indexAction).execute(tracker, input);

        }
    }

    /**
     * Точка входа в программу.
     * @param args - аргументы задаваемые при запуске программы
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(), new Tracker()).init();
    }


    /**
     * Мето для проверки наличия таблиц в базе данных, и их создания, в случае отсутствия.
     */
    public void checkDb(){
        if (tableIsNotExist("items")) {
            try (Connection conn = DriverManager.getConnection(tracker.getDataConnection().urlConnection(), tracker.getDataConnection().getUser(), tracker.getDataConnection().getPassword())) {
                PreparedStatement ps = conn.prepareStatement(getSqlScript("chapter_002\\src\\tmp\\createTableItems.sql"));
                ps.execute();
                ps.close();
            } catch (SQLException e) {
                System.out.println(e.getStackTrace());
            }
        }
        if (tableIsNotExist("comments")) {
            try (Connection conn = DriverManager.getConnection(tracker.getDataConnection().urlConnection(), tracker.getDataConnection().getUser(), tracker.getDataConnection().getPassword())) {
                PreparedStatement ps = conn.prepareStatement(getSqlScript("chapter_002\\src\\tmp\\createTableComments.sql"));
                ps.execute();
                ps.close();
            } catch (SQLException e) {
                System.out.println(e.getStackTrace());
            }
        }
    }

    /**
     * Метод для получения скрипта Sql из файла
     * @param path
     * @return
     */
    private String getSqlScript(String path) {
        File file = new File(path);
        StringBuffer str = new StringBuffer();
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (scanner != null) {
            try {
                while (scanner.hasNextLine()) {
                    str.append(scanner.nextLine());
                }

            } finally {
                scanner.close();
            }
        }
        return str.toString();
    }

    /**
     * Метод для проверки наличия таблицы в базе данных.
     * @param tableName наименование таблицы
     * @return true - если таблица существует, иначе false
     */
    public boolean tableIsNotExist(String tableName) {
        boolean result = true;
        try {
            Connection conn = DriverManager.getConnection(tracker.getDataConnection().urlConnection(), tracker.getDataConnection().getUser(), tracker.getDataConnection().getPassword());
            PreparedStatement ps = conn.prepareStatement("select table_name from information_schema.tables where table_name = ?;");
            ps.setString(1, tableName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = false;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getStackTrace());
        }
        return result;
    }
}
