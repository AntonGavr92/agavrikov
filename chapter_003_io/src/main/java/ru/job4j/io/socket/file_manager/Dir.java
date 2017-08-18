package ru.job4j.io.socket.file_manager;

import java.io.File;

/**
 * Created by gavrikov.a on 18/08/2017.
 */
public class Dir {

    private String path;

    private String newPath;

    public Dir(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setNewPath(String newPath) {
        File file = new File(newPath);
        if(file.isDirectory()) {
            this.newPath = String.format("%s/",newPath);
        } else {
            this.newPath = newPath;
        }
    }

    public String getNewPath() {
        return this.newPath;
    }

    public void updatePath () {
        this.path = this.newPath;
    }

}
