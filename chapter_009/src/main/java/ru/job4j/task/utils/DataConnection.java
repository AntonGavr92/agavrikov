package ru.job4j.task.utils;

/**
 * Created by gavrikov.a on 10/08/2017.
 */
public class DataConnection {

    private String dbDriver;

    private String dbUrl;

    private String dbUserName;

    private String dbPassword;

    public DataConnection(String dbDriver, String dbUrl, String dbUserName, String dbPassword) {
        this.dbDriver = dbDriver;
        this.dbUrl = dbUrl;
        this.dbUserName = dbUserName;
        this.dbPassword = dbPassword;
    }

    public String getDbDriver() {
        return dbDriver;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public String getDbPassword() {
        return dbPassword;
    }
}
