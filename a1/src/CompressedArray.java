/**
 * A class that represents the array that has been compressed from a matrix (2D array) into a linear
 * array that excludes elements from the diagonal and any elements above or to the right of the
 * diagonal.
 * @author Derek Liu
 */


public class CompressedArray {
    private int origArraySize;
    private double[] array;

    public CompressedArray(double[][] originalArray) {
        int length = originalArray.length;
        int n = 0;
        //determined capacity for array
        int totalelements = ((length * length) - length) / 2;
        origArraySize = totalelements;
        array = new double[totalelements];
        for (int x = 0; x < length; x++) {
            //loop for every x y value of city given
            for (int y = 0; y < length; y++) {
                if(x > y) {
                    //adds to the array from the given array
                    array[n++] = originalArray[x][y];
                }
            }
        }
    }

    public int getLength() {
        return array.length;
    }

    public double getElement(int x){
        return array[x];
    }

    public boolean equals(CompressedArray obj) {
        if (origArraySize != obj.origArraySize) {
            //check for array length
            return false;
        }
        for (int i=0;i<origArraySize;i++)
        {
            if (array[i]!=obj.array[i])
            {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        int column = 0;
        int row = 0;
        //starts with a new line according to the tests
        String s = "\n";
        for (double i : array) {
            s += String.format("%8.2f", i);
            if (row == column) {
                s += "\n";
                //returns back to the left
                row = 0;
                //adds 1 to the "row"
                column++;
            } else {
                //adds 1 to the "column"
                row++;
            }
        }
        return s;
    }
}





