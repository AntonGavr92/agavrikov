package ru.job4j.io.socket;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test ServerOracle class.
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */
public class ServerOracleTest {

    /**
     * константа для хранения разделителя строк.
     */
    private static final String LS = System.lineSeparator();

    /**
     * Метод для тестирования команды exit.
     * @throws IOException исключение
     */
    @Test
    public void whenOracleGetCommandExitThanExit() throws IOException {
        checkCommandsResult(String.format("exit%s", LS), "");
    }

    /**
     * Метод для тестирования неизвестной команды.
     * @throws IOException исключение
     */
    @Test
    public void whenOracleGetUnknownCommandThanReturnTextForUnknownCommand() throws IOException {
        checkCommandsResult(String.format("dsfs%sexit%s", LS, LS), String.format("I dont understand, what you want?%s%s", LS, LS));
    }

    /**
     * Метод для тестирования команды hello.
     * @throws IOException исключение
     */
    @Test
    public void whenOracleGetCommandHelloThanReturnHello() throws IOException {
        checkCommandsResult(String.format("hello%sexit%s", LS, LS), String.format("Hello, dear friend, I'm a oracle.%s%s", LS, LS));
    }

    /**
     * Вспомогательный метод для тестирования.
     * @param command команда оракулу.
     * @param expectedResult ожидаемый ответ
     * @throws IOException исключение
     */
    private void checkCommandsResult(String command, String expectedResult) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(command.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        ServerOracle server = new ServerOracle(socket);
        server.start();
        assertThat(out.toString(), is(expectedResult));
    }
}