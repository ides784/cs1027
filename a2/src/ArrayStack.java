/**
 * A class that implements and houses rules and methods for creating and modifying an array stack.
 * @author Derek Liu
 */

public class ArrayStack<T> implements ArrayStackADT<T>{
    public static String sequence = "";
    private T[] stack;
    private int top;

    public ArrayStack() {
        top = -1;
        stack = (T[]) new Object[14]; //creates stack of size 14
    }
    public ArrayStack(int initialCapacity) {
        top = -1;
        stack = (T[])new Object[initialCapacity]; //creates stack of size initialcapacity
    }


    /**
     * A method that pushes the next item into the stack
     * @param dataItem the item to be pushed
     */
    public void push(T dataItem) { //pushes item in stack
        if (top == stack.length - 1) {
            int altsize;

            if(stack.length < 50) { //if stack length is less than 50 cap of array increases by 10
                altsize = stack.length + 10;
            }
            else {
                altsize = stack.length * 2; //else it just doubles
            }
            T[] newStack = (T[])new Object[altsize];

            for(int i=0;i<size();i++) { //copies
                newStack[i] = stack[i];
            }
            stack = (T[])new Object[altsize];

            for(int i=0;i<size();i++) {
                stack[i] = newStack[i];
            }
            stack[top+1] = dataItem;
            top++;
        }
        else {
            stack[top+1] = dataItem;
            top++;
        }

        if (dataItem instanceof MapCell) { //provided code
            sequence += "push" + ((MapCell)dataItem).getIdentifier();
        }
        else {
            sequence += "push" + dataItem.toString();
        }

    }


    /**
     * A method that takes top item out of stack and returns it
     * @return top item to be popped
     */
    public T pop() throws EmptyStackException {
        if(isEmpty()) {throw new EmptyStackException("Stack is empty!");}
        T result = stack[top];
        top--;

        int newsize = 0;
        if(size() < (stack.length)/4) {

            if ((stack.length/2) <= 14){ //if half of stack length less than 14, then the new size is now 14
                newsize = 14;
            }
            else{
                newsize = stack.length/2;
            }
            T[] newStack = (T[])new Object[newsize]; //create stack of newsize size
            for(int i=0;i<size();i++) {
                newStack[i] = stack[i];
            }

            stack = (T[])new Object[newsize];

            for(int i=0;i<size();i++) { //copies
                stack[i] = newStack[i];
            }
        }
        if (result instanceof MapCell) { //provided code
            sequence += "pop" + ((MapCell)result).getIdentifier();
        }
        else {
            sequence += "pop" + result.toString();
        }

        return result;
    }

    /**
     * A method that looks at top item without returning
     * @return top stack item
     */
    public T peek() throws EmptyStackException{ //looks at top item without returning
        if (isEmpty()) {throw new EmptyStackException("Stack is empty!");}
        return stack[top];
    }

    public boolean isEmpty() { //check if stack is empty
        return top == -1;
    }

    public int size() {//check stack size
        return top + 1;
    }

    public int length() {//check stack length
        return stack.length;
    }

    public String toString() { //to string method to give strign representation
        String str = "Stack: ";
        for(int i=0;i<size()-1;i++) {
            str+= (stack[i]+", ");
        }
        str+=stack[top];
        return str;
    }
}