package proj.models;

import proj.controllers.AlbumController;

import java.util.Date;

public class ChartDate {
    private int id, name, date;
    public ChartDate(int id, int name, int date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }
    public ChartDate(int name, int date) {
        this.name = name;
        this.date = date;
    }

    //<editor-fold desc="GETTERS">
    public int getId() {
        return id;
    }

    public int getName() {
        return name;
    }

    public int getDate() {
        return date;
    }
    //</editor-fold>
    //<editor-fold desc="SETTERS">
    public void setId(int id) {
        this.id = id;
    }

    public void setName(int name) {
        this.name = name;
    }

    public void setDate(int date) {
        this.date = date;
    }
    //</editor-fold>
}
