package ru.job4j.io.socket;

import org.junit.Test;

import java.io.InputStreamReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test ClientOracle class.
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */
public class ClientOracleTest {

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
        checkCommandsResult(String.format("exit%s", LS, LS), String.format("Write you question to Oracle!%sexit%s", LS, LS));
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
        System.setOut(new PrintStream(out));

        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);

        ClientOracle client = new ClientOracle(socket, new InputStreamReader(in));
        client.start();
        assertThat(out.toString(), is(expectedResult));
    }

}