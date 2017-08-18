package ru.job4j.io.socket.file_manager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * Created by gavrikov.a on 18/08/2017.
 */
public class ServerFileManager {

    private final Socket socket;

    private Dir serverPath;

    private final List<FileManagerAction> listCommand = new LinkedList<FileManagerAction>();

    public ServerFileManager(Socket socket, String serverPath) {
        this.socket = socket;
        this.serverPath = new Dir(serverPath);

        this.listCommand.add(new GetListFiles());
        this.listCommand.add(new ChangeDir());
        this.listCommand.add(new UpDir());
        this.listCommand.add(new DownloadFile());
        this.listCommand.add(new UploadFile());
        this.listCommand.add(new Help(listCommand));
    }

    public void start() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))) {
            String command = "";
            do{
                boolean commandNotFound = true;
                command = in.readLine();
                String[] com = command.split(" ");
                for (FileManagerAction action : this.listCommand) {
                     if (action.getCommand().equals(com[0])) {
                        if (com.length > 1){
                            this.serverPath.setNewPath(String.format("%s%s", this.serverPath.getPath(), com[1]));
                        }
                        action.doAction(this.socket.getOutputStream(), this.socket.getInputStream(), this.serverPath);
                        commandNotFound = false;
                        break;
                    }
                }
                if (commandNotFound) {
                    PrintWriter pw = new PrintWriter(this.socket.getOutputStream(), true);
                    pw.write(String.format("Command %s not found. For get all commands use command help.%s", com[0], System.lineSeparator()));
                    pw.println();
                }
            } while (!command.equals("exit"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Properties properties = new Properties();
        String path = "C:/projects/test/";
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-d")) {
                path = args[i + 1];
            }
        }
        try {
            properties.load(ServerFileManager.class.getClassLoader().getResourceAsStream("app.properties"));
            int port = Integer.parseInt(properties.getProperty("port"));
            ServerFileManager sfm = new ServerFileManager(new ServerSocket(port).accept(),path);
            sfm.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
