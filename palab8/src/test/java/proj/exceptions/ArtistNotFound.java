package proj.exceptions;

public class ArtistNotFound extends RuntimeException {
    public ArtistNotFound(String message) {
        super(String.format("Album %s not found!", message));
    }
}
