import java.util.Scanner;  // Import the Scanner class

public class simon {

    public void playsimon(int n){
        LinkedQueue queue = new LinkedQueue();
        queue.enqueue(getNextColor());
        for (i: queue.size()){
            showColor(queue.dequeue());
        }
        checkanswer();
    }

    public static void main(String[] args) {

    }
}