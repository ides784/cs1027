/**
 * A class that searches for targets in a map and takes in a map file.
 * @author Derek Liu
 */


import java.io.IOException;
import java.io.FileNotFoundException;


public class StartSearch {
    private Map targetMap;
    private int numArrows;
    private int inertia;
    private int direction;

    /**
     * constructor to initialize the inertia, direction, and open map file, as well as catch provided exceptions
     * @param filename name of file
     */
        public StartSearch(String filename) {
        ArrayStack.sequence="";
        this.inertia = 0;
        this.direction = -1;
        try {
            targetMap = new Map(filename);
        }
        catch (InvalidMapException e){
            System.out.println("Input file is wrong format!");
        }
        catch (FileNotFoundException e){
            System.out.println("File not found!");
        }
        catch (IOException e) {
            System.out.println("Input exception!");
        }

        numArrows = targetMap.quiverSize();
    }

    /**
     * method to look for the next best cell in the map
     * @param cell the current cell
     * @return the next best cell
     */
    private MapCell nextCell (MapCell cell){
        MapCell bestCell;
//follow path if possible
        try{
        if (cell.isVerticalPath()){
            if (cell.getNeighbour(0) != null && !cell.getNeighbour(0).isMarked() && !cell.getNeighbour(0).isBlackHole() && (this.direction==0) && !cell.getNeighbour(0).isHorizontalPath()){
                this.inertia++;
                return cell.getNeighbour(0);
            }
            if (cell.getNeighbour(2) != null && !cell.getNeighbour(2).isMarked() && !cell.getNeighbour(2).isBlackHole() && (this.direction==2) && !cell.getNeighbour(2).isHorizontalPath()){
                this.inertia++;
                return cell.getNeighbour(2);
            }
        }
        if (cell.isHorizontalPath()){
            if (cell.getNeighbour(1) != null && !cell.getNeighbour(1).isMarked() && !cell.getNeighbour(1).isBlackHole() && (this.direction==1) && !cell.getNeighbour(1).isVerticalPath()){
                this.inertia++;
                return cell.getNeighbour(1);
            }
            if (cell.getNeighbour(3) != null && !cell.getNeighbour(3).isMarked() && !cell.getNeighbour(3).isBlackHole() && (this.direction==3) && !cell.getNeighbour(3).isVerticalPath()){
                this.inertia++;
                return cell.getNeighbour(3);
            }
        }
        if(this.direction == 0){
            // if cell exists & is NOT marked
            if (cell.getNeighbour(0) != null && !cell.getNeighbour(0).isMarked() && !cell.getNeighbour(0).isBlackHole() && !cell.getNeighbour(0).isHorizontalPath()) {
                this.inertia++;
                return cell.getNeighbour(0);
            }
        }

        if(this.direction == 1){
            // if cell exists & is NOT marked
            if (cell.getNeighbour(1) != null && !cell.getNeighbour(1).isMarked() && !cell.getNeighbour(1).isBlackHole() && !cell.getNeighbour(1).isVerticalPath()) {
                this.inertia++;
                return cell.getNeighbour(1);
            }
        }

        if(this.direction == 2){
            // if cell exists & is NOT marked
            if (cell.getNeighbour(2) != null && !cell.getNeighbour(2).isMarked() && !cell.getNeighbour(2).isBlackHole() && !cell.getNeighbour(2).isHorizontalPath()) {
                this.inertia++;
                return cell.getNeighbour(2);
            }
        }
        if(this.direction == 3){
            // if cell exists & is NOT marked
            if (cell.getNeighbour(3) != null && !cell.getNeighbour(3).isMarked() && !cell.getNeighbour(3).isBlackHole() && !cell.getNeighbour(3).isVerticalPath()) {
                this.inertia++;
                return cell.getNeighbour(3);
            }
        }

        if(this.inertia >= 3) {return null;}//if inertia reached, can no longer turn


        for (int i = 0; i < 4; i++) { //if path is blocked, turn
            // if cell exists & is NOT marked
            if (cell.getNeighbour(i) != null && !cell.getNeighbour(i).isMarked() && !cell.getNeighbour(i).isBlackHole()) {
                // if neighbor is target
                if (cell.getNeighbour(i).isTarget()) {
                    bestCell = cell.getNeighbour(i);
                    this.direction = i;
                    return bestCell;
                }
            }
        }


        for (int i = 0; i < 4; i++) { //if path is blocked, turn
            // if cell exists & is NOT marked
            if (cell.getNeighbour(i) != null && !cell.getNeighbour(i).isMarked() && !cell.getNeighbour(i).isBlackHole()) {
                // if neighbor is crosspath
                if (cell.getNeighbour(i).isCrossPath()) {
                    bestCell = cell.getNeighbour(i);
                    this.direction = i;
                    return bestCell;
                }
            }
        }

        for (int i = 0; i < 4; i++) { //if path is blocked, turn
            // if cell exists & is NOT marked
            if (cell.getNeighbour(i) != null && !cell.getNeighbour(i).isMarked() && !cell.getNeighbour(i).isBlackHole()) {
                // if cell is free or is destination cell
                if (cell.getNeighbour(i).isTarget()) {
                    bestCell = cell.getNeighbour(i);
                    this.direction = i;
                    return bestCell;
                } else if (cell.getNeighbour(i).isCrossPath()) { //if neighbor is crosspath
                    bestCell = cell.getNeighbour(i);
                    this.direction = i;
                    return bestCell;
                } else if (cell.getNeighbour(i).isVerticalPath() || cell.getNeighbour(i).isHorizontalPath()) { //if neighbor is hor or vert path
                    if (cell.getNeighbour(i).isVerticalPath()) {
                        if (i == 0 || i == 2) {
                            this.direction = i;
                            return cell.getNeighbour(i);
                        }
                    }
                    if (cell.getNeighbour(i).isHorizontalPath()) {
                        if (i == 1 || i == 3) {
                            this.direction = i;
                            return cell.getNeighbour(i);
                        }
                    }
                }
            }
        }
    return null; // if no valid cell then return null
    }
    catch(InvalidNeighbourIndexException e){
        return null;
    }
}

    /**
     * main that calls the methods and interates over cells in map
     * @param args command line args
     */
    public static void main(String[] args){
        ArrayStack stack = new ArrayStack(); //creates stack
        if (args.length ==0){
            throw new IllegalArgumentException("no arguments provided!");
        }
        StartSearch search = new StartSearch(args[0]); //takes first commandline arg
        int distance;
        if (args.length!=2){
            distance = 1000;
        }
        else{
            distance = Integer.parseInt(args[1]); //takes second commandline arg
        }
        int origdistance = distance;
        int targetsfound = 0;
        int returns = 0;
        int arrowsshot =0;
        MapCell cell = search.targetMap.getStart(); // gets the startcell

        while (search.numArrows != 0) { //while Cupid still has arrows
            stack.push(cell); // pushes starting cell to stack
            cell.markInStack();
            search.numArrows--;
            arrowsshot++;


            while (!stack.isEmpty() && distance != 0 && returns != 3) {
                MapCell currentCell = (MapCell) stack.peek();    // looks at cell at top of stack
                if(currentCell == null){
                }
                // if top cell is the target cell break
                if (currentCell.isTarget()) {
                    targetsfound++;
                    currentCell.markInStack();
                    while(!stack.isEmpty()) {
                        stack.pop();
                    }
                    returns=0;
                    distance=origdistance;
                    search.direction = -1;
                    search.inertia=0;
                    break;
                }

                MapCell bestcell = search.nextCell(currentCell);

                if (bestcell != null) { // checks to see if neighbor cells are marked
                    distance--;
                    try {
                        stack.push(bestcell);
                        (bestcell).markInStack();
                    } catch (NullPointerException e) {
                        System.out.println("Null pointer!");
                    }
                }

                else { //backtrack
                    returns++;
                    distance++;
                    stack.pop();

                }

            }
            while(!stack.isEmpty()) {
                stack.pop();
            }
            returns=0;
            distance=origdistance;
            search.inertia=0;
            search.direction=-1;

        }
        //final summary
        System.out.println("Max arrow length: " + origdistance);
        System.out.println("Original number of arrows in quiver: " + (arrowsshot+ search.numArrows));
        System.out.println("Targets found: " + targetsfound);

    }

}

