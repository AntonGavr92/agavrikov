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
        try (BufferedReader inUser = new BufferedReader(this.userIn)) {
            do {
                System.out.println("Write command");
                String str = inUser.readLine();
                String[] command = str.split(" ");
                if (command.length > 1 && command[0].equals("download")) {
                    String[] path = command[1].split("/");
                    downloadFile(str, path[path.length - 1]);
                } else if (command.length > 1 && command[0].equals("upload")){
                    uploadFile(str, command[1]);
                } else {
                    exit = simpleCommand(str);
                }
            } while (!exit);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Функция для загрузки файла с сервера.
     * @param userCommand пользовательская команда
     * @param fileName имя файла, который нужно скачать с сервера
     */
    private void downloadFile(String userCommand, String fileName) {
        try (InputStream inFile = socket.getInputStream();
             PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true)) {
            out.println(userCommand);
            File file = new File(String.format("C:/test/%s", fileName));
            FileOutputStream fos = new FileOutputStream(file);
            int b;
            while ((b = inFile.read()) != -1) {
                fos.write((char) b);
            }
            byte[] buffer = new byte[inFile.available()];
            inFile.read(buffer);
            fos.write(buffer);
            fos.close();
        } catch (IOException e) {

        }
    }

    /**
     * Функция для загрузки файла на сервер.
     * @param userCommand пользовательская команда
     * @param path путь к файлу, который нужно передать на сервер.
     */
    private void uploadFile (String userCommand, String path) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true)) {
            out.println(userCommand);
            in.readLine();
            FileInputStream fis = new FileInputStream(path);
            int b;
            while ((b = fis.read()) != -1) {
                out.write((char) b);
            }
            out.println();

        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    /**
     * Метод, для отправки запроса и получения ответа от сервера, простых комманд, которые не связаны с обменом файлов.
     * @param userCommand пользовательская команда
     * @return в случае если необходимо выйти из программы - true, иначе false.
     */
    private boolean simpleCommand(String userCommand) {
        boolean exit = false;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true)) {
            out.println(userCommand);
            String str = in.readLine();
            if ((str) != null) {
                while (!str.isEmpty()) {
                    System.out.println(str);
                    str = in.readLine();
                }
            } else {
                exit = true;
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        return exit;
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