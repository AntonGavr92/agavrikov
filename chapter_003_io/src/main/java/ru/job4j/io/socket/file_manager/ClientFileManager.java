package ru.job4j.io.socket.file_manager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.File;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Properties;


/**
 * Класс, реализующий клиент с помощью которого можно взаимодействовать с серверным приложением файлового менеджера.
 * @author agavrikov
 * @since 18.08.2017
 * @version 1
 */
public class ClientFileManager {

    /**
     * Поле для хранения сокета.
     */
    private final Socket socket;

    /**
     * Поток для приема данных от пользователя.
     */
    private final InputStreamReader userIn;

    /**
     * Конструктор для инициализации сокета.
     * @param socket сокет
     * @param userIn поток ввода
     */
    public ClientFileManager(Socket socket, InputStreamReader userIn) {
        this.socket = socket;
        this.userIn = userIn;
    }

    /**
     * Метод для запуска логики клиента.
     */
    public void start() {
        boolean exit = false;
        try (BufferedReader inUser = new BufferedReader(this.userIn);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             InputStream inFile = socket.getInputStream();
             PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true)) {
            do {
                System.out.println("Write command");
                String str = inUser.readLine();
                String[] command = str.split(" ");
                if (command.length > 1 && command[0].equals("download")) {
                    out.println(str);
                    String[] path = command[1].split("/");
                    File file = new File(String.format("C:/test/%s", path[path.length - 1]));
                    FileOutputStream fos = new FileOutputStream(file);
                    int b;
                    while ((b = inFile.read()) != -1) {
                        fos.write((char) b);
                    }
                    byte[] buffer = new byte[inFile.available()];
                    inFile.read(buffer);
                    fos.write(buffer);
                    fos.close();
                    inFile.close();
                } else if (command.length > 1 && command[0].equals("upload")){
                    out.println(str);
                    in.readLine();
                    FileInputStream fis = new FileInputStream(command[1]);
                    int b;
                    while ((b = fis.read()) != -1) {
                        out.write((char) b);
                    }
                    out.println();
                } else {
                    out.println(str);
                    str = in.readLine();
                    if ((str) != null) {
                        while (!str.isEmpty()) {
                            System.out.println(str);
                            str = in.readLine();
                        }
                    } else {
                        exit = true;
                    }
                }
            } while (!exit);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Точка входа в программу.
     * @param args параметры подключения
     * @throws IOException исключение
     */
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(ServerFileManager.class.getClassLoader().getResourceAsStream("app.properties"));
        String host =  properties.getProperty("host");
        int port = Integer.parseInt(properties.getProperty("port"));
        ClientFileManager client = new ClientFileManager(new Socket(InetAddress.getByName(host), port), new InputStreamReader(System.in));
        client.start();
    }

}