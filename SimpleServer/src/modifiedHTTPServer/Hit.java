package modifiedHTTPServer;


public class Hit {

    String artist;
    String song;

    @SuppressWarnings("javadoc")
    public Hit(String artist, String song) {
        this.artist = artist;
        this.song = song;
    }

    @SuppressWarnings("javadoc")
    public String getArtist() {
        return artist;
    }

    @SuppressWarnings("javadoc")
    public void setArtist(String artist) {
        this.artist = artist;
    }

    @SuppressWarnings("javadoc")
    public String getSong() {
        return song;
    }

    @SuppressWarnings("javadoc")
    public void setSong(String song) {
        this.song = song;
    }
}
