package ru.job4j.isp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;


/**
 * Created by gavrikov.a on 23/08/2017.
 */
public class SimpleIO {

    /**
     * menu.
     */
    private Menu menu;

    /**
     * Command for exit from program.
     */
    private static final String EXIT_COMMAND = "exit";

    /**
     * inputStream.
     */
    private InputStream in;

    /**
     * Constructor.
     * @param menu menu.
     * @param in inputStream
     */
    public SimpleIO(Menu menu, InputStream in) {
        this.menu = menu;
        this.in = in;
    }

    /**
     * Main method for show menu.
     */
    public void showMenu() {
        showMenuRec(this.menu, "-");
        Map<Integer, Item> map = this.menu.getItemsMap();
        String command;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(this.in))){
            while (!(command = br.readLine()).equals(EXIT_COMMAND)) {
                map.get(Integer.parseInt(command)).execute();
                System.out.println(String.format("%s is executed", command));
                showMenuRec(this.menu, "-");
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    /**
     * method for show tree of items menu.
     * @param menu Menu
     * @param rootStruct struct view.
     */
    protected void showMenuRec(Menu menu, String rootStruct) {
        for (Item item : menu.getMenuItems()) {
            System.out.println(String.format("%s %s %s", item.getId(), rootStruct, item.getName()));
            if (item.isHasSubMenu()) {
                showMenuRec(item.getSubMenu(), String.format("%s-", rootStruct));
            }
        }
    }

    /**
     * Start.
     * @param args params.
     */
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.addItem(new SimpleItem("Test1"));
        Item sub = new SimpleItem("Test2");
        menu.addItem(sub);
        sub.addItem(new SimpleItem("Test5"));
        sub.addItem(new SimpleItem("Test6"));
        Item sub2 = new SimpleItem("Test7");
        sub.addItem(sub2);
        sub2.addItem(new SimpleItem("Test8"));
        sub2.addItem(new SimpleItem("Test9"));
        menu.addItem(new SimpleItem("Test3"));
        menu.addItem(new SimpleItem("Test4"));
        SimpleIO simpleIO = new SimpleIO(menu, System.in);
        simpleIO.showMenu();
    }

}
