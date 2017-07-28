package ru.job4j.jdbc;

/**
 * Created by gavrikov.a on 28/07/2017.
 */
public class DataConnection {

    private String host;

    private String port;

    private String user;

    private String password;

    private String db;

    private String urlConnection;

    public DataConnection(String host, String port, String user, String password, String db, String typeJdbc) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
        this.db = db;
        this.urlConnection = String.format("jdbc:%s://%s:%s/%s", typeJdbc, host, port, db);
    }

    public String getPort() {
        return this.port;
    }

    public String getHost() {
        return this.host;
    }

    public String getUser() {
        return this.user;
    }

    public String getPassword() {
        return this.password;
    }

    public String getDb() {
        return this.db;
    }

    public String urlConnection() {
        return this.urlConnection;
    }
}
