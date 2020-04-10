package proj;

import com.github.javafaker.Faker;
import proj.controllers.AlbumController;
import proj.controllers.ArtistController;
import proj.controllers.ChartController;
import proj.models.Album;
import proj.models.Artist;
import proj.models.ChartDate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    static ArrayList<Artist> artists;
    static ArrayList<Album> albums;
    static Faker faker;
    public static void main(String[] args) {
        try {
            generateFakeDataSet();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Integer> makeRandomSet(int low, int high) {
        Random random = new Random();
        ArrayList<Integer> allNo = new ArrayList<Integer>();
        ArrayList<Integer> randomSet = new ArrayList<Integer>();
        for (int i = low; i <= high; i++)
            allNo.add(i);
        while (!allNo.isEmpty()) {
            int randomIndex = Math.abs(random.nextInt()) % allNo.size();
            randomSet.add(allNo.get(randomIndex));
            allNo.remove(randomIndex);
        }
        return randomSet;
    }

    private static void generateFakeDataSet() throws SQLException {
        final int ARTIST_NO = 3;
        final int MAX_RELEASE_YEAR = 2010;
        artists = new ArrayList<Artist>();
        albums = new ArrayList<Album>();

        ArtistController artistDAO = new ArtistController();
        AlbumController albumDAO = new AlbumController();

        faker = new Faker();
        Random random = new Random();
        for (int i = 0; i < ARTIST_NO; i++) {
            //<editor-fold desc="FINDING UNIQUE ARTIST NAME">
            String singerName = getRandomSinger();
            System.out.println("Artist name : " + singerName);
            boolean uniqueSinger;
            do {
                uniqueSinger = true;
                for (int j = 0; j < i; j++)
                    if (artists.get(j).getName().equals(singerName)) {
                        uniqueSinger = false;
                        break;
                    }
            } while (!uniqueSinger);
            //</editor-fold>

            //<editor-fold desc="ADDING ARTIST">
            Artist currentArtist = new Artist(singerName, "Romania");
            artists.add(currentArtist);
            artistDAO.create(currentArtist.getName(), currentArtist.getCountry());
            //</editor-fold>

            //<editor-fold desc="ADDING ALBUMS FOR CURRENT ARTIST">
            int artistID = artistDAO.findByName(currentArtist.getName()).getId();
            int albumNoForCurrentArtist = 1 + Math.abs(random.nextInt()) % 4;
            for (int j = 0; j < albumNoForCurrentArtist; j++) {
                String currentAlbumName = String.format("%s + %s", faker.animal().name(), faker.beer().name());
                int releaseYear = Math.abs(random.nextInt()) % MAX_RELEASE_YEAR;
                Album currentAlbum = new Album(artistID, releaseYear, currentAlbumName);
                albums.add(currentAlbum);
                albumDAO.create(currentAlbumName,
                        artistID,
                        releaseYear);
            }
            //</editor-fold>
        }
        //<editor-fold desc="CREATE 2 CHARTS">
        ChartController chartDAO = new ChartController();
        String[] chartNames = {"US TOP 10", "MTV Romania Top 5"};
        String[] dates = {"2003 November", "2000"};
        int[] chartID = new int[2];
        for (int i = 0; i < 2; i++)
            chartDAO.createChart(chartNames[i], dates[i]);
        //</editor-fold>
        ArrayList<Integer> randomAlbumIDs, randomRankings;
        //<editor-fold desc="FIRST RANKING">
        chartID[0] = chartDAO.getChartID("US TOP 10", "2003 November");
        randomAlbumIDs = makeRandomSet(1, 10);
        randomRankings = makeRandomSet(1, 10);
        for (int i = 0; i < 10; i++)
            chartDAO.insertRank(chartID[0], randomAlbumIDs.get(i), randomRankings.get(i));
        //</editor-fold>
        //<editor-fold desc="SECOND RANKING">
        chartID[1] = chartDAO.getChartID("MTV Romania Top 5", "2000");
        randomAlbumIDs = makeRandomSet(1, 5);
        randomRankings = makeRandomSet(1, 5);
        for (int i = 0; i < 5; i++)
            chartDAO.insertRank(chartID[1], randomAlbumIDs.get(i), randomRankings.get(i));
        //</editor-fold>
    }

    private static String getRandomSinger() {
        String output = faker.rockBand().name();
        output = output.replace("'", "");
        return output;
    }
}
