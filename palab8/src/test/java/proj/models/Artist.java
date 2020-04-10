package proj.models;

public class Artist {
    private int id;
    private String name, country;

    public Artist(int id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public Artist(String name, String country) {
        this.id = -1;
        this.name = name;
        this.country = country;
    }

    @Override
    public String toString() {
        return String.format("Artist with id %d, name %s, country %s", id, name, country);
    }

    //<editor-fold desc="GETTERS">
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }
    //</editor-fold>

    //<editor-fold desc="SETTERS">
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    //</editor-fold>
}
