/**
 * A class that represents a single node in a nary tree.
 * @author Derek Liu
 */

public class NaryTreeNode<T>{
    private T data;
    private int numChildren;
    private NaryTreeNode<T> [] children;

    public NaryTreeNode(T parameter){
        data = parameter;
        numChildren=0;
        children = null;
    }
    /**
     * A method that adds child to parent node
     * @param child the item to be added
     */
    public void addChild(NaryTreeNode<T> child){
        if(children == null){
            children = new NaryTreeNode[3];
        }
        else if(numChildren %3==0){//if children array full
            expandCapacity();
        }
        children[numChildren] = child;//adds child to the next available slot in the children array
        numChildren=numChildren+1;
    }
    /**
     * A method that expands the capacity of the children by 3 spaces
     */
    public void expandCapacity(){
        int expansion = numChildren+3;
        NaryTreeNode<T> [] newc = new NaryTreeNode[expansion];//expands by 3 spaces
        for(int i=0;i < numChildren;i++){
            newc[i] = children[i];
        }

        children = newc;
    }

    public int getNumChildren(){
        return numChildren;
    }
    /**
     * A method that takes an index and returns the child at the index
     * @param index the index to be returned
     */
    public NaryTreeNode<T> getChild(int index){
        if(children == null){//checks if children is null, or invalid index
            return null;
        }
        else if (index < 0){
            return null;
        }
        else if(index >= numChildren){
            return null;
        }
        else{
            return children[index];
        }
    }

    public T getData(){
        return data;
    }

    public String toString(){
        String output = "Node containing "+ this.data;
        return output;
    }
}

