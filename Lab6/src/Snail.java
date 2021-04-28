import java.util.Queue;

public class Snail {

    public static char icon = '@';
    private int position;
    private QueueADT<Integer> movePattern;
    private int raceLength = 50;

    public Snail (int[] pattern) {
        position = 0;
        movePattern = new LinkedQueue<>();
        for (int j: pattern)
            movePattern.enqueue(j);
    }

    public void move () {
        int initial = movePattern.dequeue();
        movePattern.enqueue(initial);
        position = position + initial;
        if (position > raceLength){
            position=raceLength;
        }
    }

    public int getPosition () {
        return position;
    }

    public void display () {
        int dashesBefore = position;
        int dashesAfter = raceLength - position;
        for(int i = 0 ; i < dashesBefore ; i++)
            System.out.print("-");
        System.out.print(icon);
        for(int i = 0 ; i < dashesAfter ; i++)
            System.out.print("-");
        System.out.println();
    }

}