package ru.job4j.todolist.servlets;


import ru.job4j.todolist.models.Item;
import ru.job4j.todolist.repository.ItemsRep;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;


/**
 * Class-Servlet for working with items.
 * @author agavrikov
 * @since 29.08.2017
 * @version 1
 */
public class ItemsListServlet extends HttpServlet {

    /**
     * Instance of repository.
     */
    private final ItemsRep repository = ItemsRep.instanceOf();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("filterDone") != null) {
            resp.setContentType("application/json");
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (Item item : getItemsByFilter(req)) {
                sb.append(String.format("{\"descr\": \"%s\", \"created\": %s, \"done\": %s},",
                        item.getDesc(), item.getCreated(), item.isDone()));
            }
            sb.delete(sb.length() - 1, sb.length());
            sb.append("]");
            sendAnswerJson(sb.toString(), resp.getOutputStream());
        } else {
            req.setAttribute("itemsList", repository.getAllItems());
            req.getRequestDispatcher("/WEB-INF/views/items.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        if (req.getParameter("descr") != null) {
            sendAnswerJson(createItem(req), resp.getOutputStream());
        }
    }

    /**
     * Method for getting items by filter.
     * @param req http request
     * @return list of items.
     */
    private List<Item> getItemsByFilter(HttpServletRequest req) {
        List<Item> result = new LinkedList<>();
        if (req.getParameter("filterDone").equals("true")) {
            result = repository.getItemsWithFilter("done", "true", "is");
        } else if (req.getParameter("filterDone").equals("false")) {
            result = repository.getItemsWithFilter("done", "false", "is");
        } else {
            result = repository.getAllItems();
        }
        return result;
    }

    /**
     * Method for send message to client.
     * @param answerJson json string.
     * @param out output stream
     */
    private void sendAnswerJson(String answerJson, OutputStream out) {
        PrintWriter pw = new PrintWriter(out);
        pw.println(answerJson);
        pw.flush();
    }

    /**
     * Method for create new item.
     * @param req http request
     * @return string of answer to client.
     */
    private String createItem(HttpServletRequest req) {
        boolean done = req.getParameter("done").equals("on");
        String result = "";
        Item item = new Item();
        item.setDesc(req.getParameter("descr"));
        item.setCreated(System.currentTimeMillis());
        item.setDone(done);
        try {
            repository.addItem(item);
            result = String.format("{\"descr\": \"%s\", \"created\": %s, \"done\": %s}", item.getDesc(), item.getCreated(), item.isDone());
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }
}
