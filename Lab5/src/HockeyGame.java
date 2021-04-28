public class HockeyGame {

    private HockeyTeam toronto;
    private HockeyTeam montreal;
    private String[] torontoPlayers = new String[] { "Tavares", "Matthews", "Marner", "Rielly", "Ceci" };
    private String[] montrealPlayers = new String[] { "Tatar", "Danault", "Lehkonen", "Chiarot", "Weber" };
    private int goalsToWin = 3;
    private ArrayStack eventLog;


    public HockeyGame () {
        eventLog = new ArrayStack();
    }

    public void emptyEventLog() {
        while (!eventLog.isEmpty()) {
            System.out.println(eventLog.pop().toString());
        }
        System.out.println();
    }

    public void simulate () {

        toronto = new HockeyTeam(torontoPlayers,"Toronto");
        montreal = new HockeyTeam(montrealPlayers,"Montreal");

        boolean torontoPossession = true;
        boolean intercept = false, pass = false;

        //Simulation code goes inside
        while (toronto.getGoals() < this.goalsToWin && montreal.getGoals() < this.goalsToWin) {

            //If we have an intercept, we will want to set possession manually.
            //Otherwise, simulate randomly which team gets the puck.
            if (!intercept & !pass) {
                if(HockeyGame.rollDice() > 50) {
                    torontoPossession = true;
                    eventLog.push(new HockeyEvent(torontoPlayers[HockeyGame.randomPlayerNumber()],"Toronto", "retrieved the puck"));
                }
                else {
                    torontoPossession = false;
                    eventLog.push(new HockeyEvent(montrealPlayers[HockeyGame.randomPlayerNumber()],"Montreal", "retrieved the puck"));
                }
            }
            else if (intercept) {
                intercept = false;
                torontoPossession = !torontoPossession;
            }

            //Any previous pass flags are reset as we move into the simulation behaviour.
            pass = false;

            // This is the code block for the Toronto team's events.
            if(torontoPossession) {

                int randomEvent = HockeyGame.rollDice();
                //System.out.println(randomEvent);
                if (randomEvent <= 10) {
                    eventLog.push(new HockeyEvent(torontoPlayers[HockeyGame.randomPlayerNumber()],"Toronto", "lost the puck! It's loose now!"));
                    this.emptyEventLog();
                }
                else if (randomEvent <= 20){
                    intercept = true;
                    eventLog.push(new HockeyEvent(torontoPlayers[HockeyGame.randomPlayerNumber()], "Toronto", "intercepted by Montreal"));
                    this.emptyEventLog();
                }
                else if (randomEvent <= 80) {
                    toronto.addPass();
                    eventLog.push(new HockeyEvent(torontoPlayers[HockeyGame.randomPlayerNumber()], "Toronto", "pass successful"));
                }
                else if (randomEvent <= 90) {
                    toronto.addShot();
                    eventLog.push(new HockeyEvent(torontoPlayers[HockeyGame.randomPlayerNumber()], "Toronto", "shoot! no score"));
                    this.emptyEventLog();
                }
                else {
                    toronto.addShot();
                    toronto.addGoal();
                    eventLog.push(new HockeyEvent(torontoPlayers[HockeyGame.randomPlayerNumber()], "Toronto", "shoot! aaaand goal!!!"));
                    this.emptyEventLog();
                }
            } // End Toronto Possession.
            //This is the code block for Montreal team events
            else {

                int randomEvent = HockeyGame.rollDice();
                //System.out.println(randomEvent);
                if (randomEvent <= 10) {
                    eventLog.push(new HockeyEvent(montrealPlayers[HockeyGame.randomPlayerNumber()], "Montreal", "lost the puck! It's loose now"));
                    this.emptyEventLog();
                }
                else if (randomEvent <= 20){
                    intercept = true;
                    eventLog.push(new HockeyEvent(montrealPlayers[HockeyGame.randomPlayerNumber()], "Montreal", "intercepted by Toronto"));
                    this.emptyEventLog();
                }
                else if (randomEvent <= 80) {
                    montreal.addPass();
                    eventLog.push(new HockeyEvent(montrealPlayers[HockeyGame.randomPlayerNumber()], "Montreal", "pass successful"));
                }
                else if (randomEvent <= 90) {
                    montreal.addShot();
                    eventLog.push(new HockeyEvent(montrealPlayers[HockeyGame.randomPlayerNumber()], "Montreal", "shoot! no score"));
                    this.emptyEventLog();
                }
                else {
                    montreal.addShot();
                    montreal.addGoal();
                    eventLog.push(new HockeyEvent(montrealPlayers[HockeyGame.randomPlayerNumber()], "Montreal", "shoot! aaaand goal!!!"));
                    this.emptyEventLog();
                }
            } // End Montreal simulation code.
        } // End while loop
        System.out.println("FINAL SCORE");
        int montrealGoals = montreal.getGoals();
        int torontoGoals = toronto.getGoals();
        if (torontoGoals > montrealGoals) {
            System.out.println("TORONTO with " + torontoGoals + " goals and " + toronto.getShots() + " and " + toronto.getPasses() + "passes");
            System.out.println("VS");
            System.out.println("MONTREAL with " + montrealGoals + " goals and " + montreal.getShots() + " and " + montreal.getPasses() + "passes");
        }
        else {
            System.out.println("MONTREAL with " + montrealGoals + " goals and " + montreal.getShots() + " and " + montreal.getPasses() + "passes");
            System.out.println("VS");
            System.out.println("TORONTO with " + torontoGoals + " goals and " + toronto.getShots() + " and " + toronto.getPasses() + "passes");
        }
    }

    public static int rollDice() {
        return ((int)(Math.random()*100)+1);
    }

    public static int randomPlayerNumber() {
        return (int)(Math.random()*5);
    }


    public static void main(String[] args) {
        HockeyGame simulateGame = new HockeyGame();
        simulateGame.simulate();
    }

}