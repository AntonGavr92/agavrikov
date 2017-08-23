package ru.job4j.io.socket;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Класс, реализующий клиент с помощью которого можно взаимодействовать с серверным приложением оракула через сокет.
 * @author agavrikov
 * @since 17.08.2017
 * @version 1
 */
public class ClientOracle {

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
    public ClientOracle(Socket socket, InputStreamReader userIn) {
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
             PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true)) {
            do {
                System.out.println("Write you question to Oracle!");
                String str = inUser.readLine();
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
        String host = "127.0.0.1";
        int port = 5000;
        try (Socket socket = new Socket(InetAddress.getByName(host), port);
            InputStreamReader in = new InputStreamReader(System.in)) {
            ClientOracle client = new ClientOracle(socket, in);
            client.start();
        } catch (Exception e){
            e.getStackTrace();
        }
    }
}
