public class Player {
    private String name;
    private String position;
    private int jerseyNum;

    public Player(String name, String position, int jerseyNum){
        /*
        This is the constructor so we will be
        initializing the member variables here
        */
        /**
         * Constructor, with parameters name, position, and jersey number
         * @param name name of the player
         * @param position position of the player
         * @param jerseyNum the number of the player
         */

        this.name = name;
        this.position = position;
        this.jerseyNum = jerseyNum;

    }
    public String getName() {
        // Get the player's name.
        return name;
    }
    public String getPosition() {
        /**
         * This is a getter method for the position value.
         */

        return position;
    }
    public int getJerseyNum() {
        /**
         * @return the jersey number of the player
         */
        return jerseyNum;
    }
    public void setName(String newName) {
        /**
         * sets newName as a new value
         */
        name = newName;
    }
    public void setPosition(String newPosition) {
        position = newPosition;
    }
    public void setJerseyNum(int newJerseyNum) {
        jerseyNum = newJerseyNum;
    }
    public String toString() {
        return this.name + ": #" + this.jerseyNum;
    }
    public boolean equals(Player other) {
        if (this.name.equals(other.name) && this.jerseyNum == other.jerseyNum) {
            return true;
        }
        else{
            return false;
        }
    }

}
