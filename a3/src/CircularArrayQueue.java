/**
 * A class that implements and houses rules and methods for creating and modifying a circular array queue.
 * @author Derek Liu
 */

public class CircularArrayQueue<T> implements QueueADT<T> {
    private int front, rear, count;
    private T[] queue;
    private final int DEFAULT_CAPACITY = 20;

    public CircularArrayQueue() {
        front = 1;
        rear = DEFAULT_CAPACITY;
        count = 0;
        queue = (T[]) (new Object[DEFAULT_CAPACITY]); //creates stack of size 20
    }
    public CircularArrayQueue(int initialcapacity) {
        front = 1;
        rear = initialcapacity;
        count = 0;
        queue = (T[]) new Object[initialcapacity];
    }
    /**
     * A method that pushes the next item into the rear of queue
     * @param newelement the item to be pushed
     */
    @Override
    public void enqueue(T newelement) {
        boolean alreadyenqueued = false;
        if (count == queue.length) {//if queue is full, expand the capacity of the queue.
            expandCapacity();
        }
        int x =queue.length + front;
        for (int i=front;i<x; i++) { //check through queue to find empty node
            if(alreadyenqueued==false) {
                int y=(i-1)%queue.length;
                if (queue[y]== null) {
                    count++;
                    alreadyenqueued = true;
                    queue[y]=newelement;
                    rear = (count- 1+front)%queue.length;
                }
            }
        }
    }
    /**
     * A method that removes and returns the front item in queue
     */
    @Override
    public T dequeue() throws EmptyCollectionException {
        if(isEmpty()) {
            throw new EmptyCollectionException("queue is empty");
        }
        T temp = first(); //store data at front index in order to later return it
        queue[front-1] = null;
        count--;
        int mod=(front%queue.length);
        front=1+mod;
        int newrear = count+front-1;
        rear=newrear% queue.length;
        return temp;

    }
    /**
     * A method that peeks the front item of queue
     */
    public T first() throws EmptyCollectionException {
        if(isEmpty()) {
            throw new EmptyCollectionException("queue is empty");
        }
        return queue[front-1]; //returning the element
    }

    public boolean isEmpty() {
        return count == 0;
    }
    public int size() {
        return count;
    }
    public int getFront() {
        return front;
    }
    public int getRear() {
        return rear;
    }
    public int getLength() {
        return queue.length;
    }
    /**
     * A method that returns all items in queue in string form
     */
    public String toString() {
        if(isEmpty()) {
            return "The queue is empty";
        }
        String returnoutput = "QUEUE: ";
        int temp = front-1;
        for (int i = 0; i<count; i++) { //iterates for each item in queue
            if(i + 1 == count) {
                returnoutput = returnoutput+queue[temp] + "."; //if item is the last one, add a period
                break;
            }
            returnoutput=returnoutput+queue[temp] + ", ";
            temp = (temp + 1)%queue.length;
        }
        return returnoutput;
    }

    /**
     * A method that expands the queue capacity by 20
     */
    private void expandCapacity() {
        T[] biggerarray = (T[]) (new Object[queue.length+20]); //creates new queue that is 20 bigger
        int frontindex = front - 1;
        for (int i = 0; i < count; i++) { //copies previous info into new one
            biggerarray[frontindex] = queue[frontindex];
            frontindex = (frontindex + 1) % queue.length;
        }
        rear = queue.length;
        queue = biggerarray; //queue is now the new array

    }
}