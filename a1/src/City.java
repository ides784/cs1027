/**
 * A class that represents each city that is loaded in from a text file, containing
 * the city's name, x and y coordinates, and a CityMarker icon.
 * @author Derek Liu
 */

public class City {
    private String name;
    private int x;
    private int y;
    private CityMarker marker;

    public City(String name, int x, int y) {
        //sets variables as the given name, x and y values
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public CityMarker getMarker() {
        return marker;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setMarker(CityMarker marker) {
        this.marker = marker;
    }

    public String toString() {
        return name;
    }
}
