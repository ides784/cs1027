/**
 * A class that takes reads in a file of cities and creates objects for each of them, contains the array of
 * those cities, and creates a CompressedArray containing the distances between each of the cities
 * read in from the file.
 * @author Derek Liu
 */

public class Program {
    private int cityCount;
    private City cityArray[];
    private CompressedArray array;


    public Program(String cityfile, Boolean showMap){
        //initializes cityarray with only 3 cells at first
        cityArray = new City[3];
        MyFileReader reader = new MyFileReader(cityfile);
        cityCount = 0;
        reader.readString();
        //making sure end of file isn't reached
        while(!(reader.endOfFile())) {
            //reads each data entry from the file in order; the city's name, x value, and y value
            String name = reader.readString();
            int xvalue = reader.readInt();
            int yvalue = reader.readInt();
            City entry = new City (name, xvalue, yvalue);

            if (cityArray.length == cityCount) {
                //expanding the array capacity if required
                expandCapacity();
            }
            //adds the city value of a certain line in the file to the array at citycount position
            cityArray[cityCount] = entry;
            //increments citycount by 1 to move onto next
            cityCount = cityCount + 1;
        }
        if (showMap == true) {
            //adds markers if showmap boolean is true, calling map class
            Map m = new Map();
            for (int j = 0; j<cityCount; j++) {
                //adds city for each number of citycount
                m.addCity(cityArray[j]);
            }
        }
    }

    public City[] getCityList() {
        return cityArray;
    }

    private void expandCapacity() {
        //creates temporary variable
        City variable[]=cityArray;
        //adds 3 to variable length
        City additionalcityArray[]=new City[variable.length+3];
        // copies old array data into the new one
        if (cityCount >= 0) System.arraycopy(cityArray, 0, additionalcityArray, 0, cityCount);
        cityArray = additionalcityArray;
    }

    public double distBetweenCities(City citya, City cityb) {
        //calculates absolute value of differences in x and y coordinates of the city
        double a = Math.abs(cityb.getX() - citya.getX());
        double b = Math.abs(cityb.getY() - citya.getY());
        //finds hypotenuse, the euclidian distance
        double distance = Math.sqrt((a*a) + (b*b));
        return distance;
    }

    public void compareDistances() {
        //creates distances for each city pair
        double[][] array2d = new double[cityCount][cityCount];
        //loops though every city, calling distbetweencities
        for (int k = 0; k < cityCount; k++){
            for (int l = 0; l< cityCount; l++){
                array2d[k][l] = distBetweenCities(cityArray[k], cityArray[l]);
            }
        }
        array = new CompressedArray(array2d);
    }

    public  CompressedArray getArray(){
        return array;
    }

}
