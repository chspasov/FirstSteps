package modifiedHTTPServer;


import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author stoyanstoyanov
 * Creates a compilation with name and year parameters.
 * You can add and remove artists and songs.
 *
 */

public class Compilation {

    private String name;
    private String year;
    private ArrayList<Hit> hits = new ArrayList<Hit>();

    @SuppressWarnings("javadoc")
    public String getName() {
        return name;
    }

    @SuppressWarnings("javadoc")
    public void setName(String name) {
        this.name = name;
    }

    @SuppressWarnings("javadoc")
    public String getYear() {
        return year;
    }

    @SuppressWarnings("javadoc")
    public void setYear(String year) {
        this.year = year;
    }

    @SuppressWarnings("javadoc")
    public Compilation(String name, String year) {
        super();
        this.name = name;
        this.year = year;
    }

    @Override
    public String toString() {
        String start = "{\n\"Compilation\": {\n \"Name\": \"" + name + "\",\n \"Year\": \"" + year
                + "\",\n \"Songs\": [\n";
        Hit hit;
        String songs = "";
        Iterator<Hit> it = hits.iterator();
        while (it.hasNext()) {
            hit = it.next();
            songs = songs.concat("  {\"Artist\": \"" + hit.artist + "\", \"Title\": \"" + hit.song + "\"},\n");            
        }
        String end = "  ]\n }\n}";
        return start.concat(songs).concat(end);
    }
    
    /**
     * @param artist
     * @param song
     * adds a Hit song to the arrayList
     */
    public void addHit(String artist, String song){
        this.hits.add(new Hit(artist,song));
    }

    @SuppressWarnings("javadoc")
    public static void main(String[] args) {
        Compilation hitove = new Compilation("Summer Hits","2012");
        hitove.addHit("Maroon5 Featuring Wiz Khalifa", "Payphone");
        hitove.addHit("Niki Taskov", "Pushi");
        System.out.println(hitove.toString());
    }
}
