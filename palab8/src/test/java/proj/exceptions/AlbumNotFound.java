package proj.exceptions;

public class AlbumNotFound extends RuntimeException {
    public AlbumNotFound(String message) {
        super(String.format("Album %s not found!", message));
    }
}
