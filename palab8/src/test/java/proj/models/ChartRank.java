package proj.models;

public class ChartRank {
    int id, chartID, albumID, rank;

    public ChartRank(int id, int chartID, int albumID, int rank) {
        this.id = id;
        this.chartID = chartID;
        this.albumID = albumID;
        this.rank = rank;
    }

    public ChartRank(int chartID, int albumID, int rank) {
        this.chartID = chartID;
        this.albumID = albumID;
        this.rank = rank;
    }

    //<editor-fold desc="GETTERS">
    public int getId() {
        return id;
    }

    public int getChartID() {
        return chartID;
    }

    public int getRank() {
        return rank;
    }

    public int getAlbumID() {
        return albumID;
    }
    //</editor-fold>

    //<editor-fold desc="SETTERS">
    public void setId(int id) {
        this.id = id;
    }

    public void setChartID(int chartID) {
        this.chartID = chartID;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }
    //</editor-fold>
}
