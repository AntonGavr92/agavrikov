package ru.job4j.io.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Класс, реализующий сервер приложения оракул через сокет.
 * @author agavrikov
 * @since 17.08.2017
 * @version 1
 */
public class ServerOracle {

    /**
     * Поле для хранения сокета.
     */
    private final Socket socket;

    /**
     * Конструктор для инициализации сокета.
     * @param socket сокет
     */
    public ServerOracle(Socket socket) {
        this.socket = socket;
    }

    /**
     * Метод для запуска логики сервера.
     */
    public void start() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            String ask = "";
            do {
                System.out.println("wait command ...");
                ask = in.readLine();
                System.out.println(ask);
                if ("hello".equals(ask)) {
                    out.println("Hello, dear friend, I'm a oracle.");
                    out.println();
                } else if (!"exit".equals(ask)) {
                    out.println("I dont understand, what you want?");
                    out.println();
                }
            } while (!"exit".equals(ask));

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
        int port = 5000;
        ServerOracle server = new ServerOracle(new ServerSocket(port).accept());
        server.start();
    }
}
