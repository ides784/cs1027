/**
 * LinkedQueue represents a linked implementation of a queue.
 *
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 08/12/08
 */

public class LinkedQueue<T> implements QueueADT<T>
{
    private int tracker;
    private LinearNode<T> front, rear;

    /**
     * Creates an empty queue.
     */
    public LinkedQueue() {
        tracker = 0;
        front = rear = null;
    }

    /**
     * Adds the specified element to the rear of this queue.
     *
     * @param input  the element to be added to the rear of this queue
     */
    public void enqueue (T input) {
        LinearNode<T> index = new LinearNode<T>(input);

        if (isEmpty() ==true)
            front = index;
        else
            rear.setNext(index);

        rear = index;
        tracker++;
        
    }

    /**
     * Removes the element at the front of this queue and returns a
     * reference to it. Throws an EmptyCollectionException if the
     * queue is empty.
     *
     * @return                           the element at the front of this queue
     * @throws EmptyCollectionException  if an empty collection exception occurs
     */
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty() == true)
            throw new EmptyCollectionException ("Empty queue");

        T x = front.getElement();
        front = front.getNext();
        tracker--;

        if (isEmpty())
            rear = null;

        return x;

    }


    public T first() throws EmptyCollectionException {
        if (isEmpty() == true)
            throw new EmptyCollectionException ("Empty queue");

        return front.getElement();

    }

    /**
     * Returns true if this queue is empty and false otherwise.
     *
     * @return  true if this queue is empty and false if otherwise
     */
    public boolean isEmpty() {
        return (tracker == 0);
    }

    /**
     * Returns the number of elements currently in this queue.
     *
     * @return  the integer representation of the size of this queue
     */
    public int size() {
        return tracker;
    }

    /**
     * Returns a string representation of this queue.
     *
     * @return  the string representation of this queue
     */
    public String toString() {
        if (isEmpty() == true){
            return ("The queue is empty.");
        }


        String y = "Queue: ";
        LinearNode<T> x = front;
        while (x != null)
        {
            y = y + (x.getElement()).toString() + ", ";
            x = x.getNext();
            if (x.getNext()==null){
                return (y + x.getElement().toString() + ".");
            }
        }
        return y;
    }
}