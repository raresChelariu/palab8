package proj.models;

public class Album {
    int id, artist_id, release_year;
    String name;

    public Album(int id, int artist_id, int release_year, String name) {
        this.id = id;
        this.artist_id = artist_id;
        this.release_year = release_year;
        this.name = name;
    }

    public Album(int artist_id, int release_year, String name) {
        this.artist_id = artist_id;
        this.release_year = release_year;
        this.name = name;
    }

    //<editor-fold desc="GETTERS">
    public int getId() {
        return id;
    }

    public int getArtist_id() {
        return artist_id;
    }

    public int getRelease_year() {
        return release_year;
    }

    public String getName() {
        return name;
    }
    //</editor-fold>

    //<editor-fold desc="SETTERS">
    public void setId(int id) {
        this.id = id;
    }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }

    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }

    public void setName(String name) {
        this.name = name;
    }
    //</editor-fold>
}
