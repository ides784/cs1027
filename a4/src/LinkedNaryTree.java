/**
 * A class that represents the Nary tree.
 * @author Derek Liu
 */

import java.util.Iterator;

public class LinkedNaryTree<T> {
    private NaryTreeNode<T> root;


    public LinkedNaryTree(){
        root = null;
    }


    public LinkedNaryTree(NaryTreeNode<T> parameter){
        root = parameter;
    }

    /**
     * A method that adds child to parent
     * @param parentnode the parent node
     * @param childnode the child node
     */
    public void addNode(NaryTreeNode<T> parentnode, NaryTreeNode<T> childnode){
        parentnode.addChild(childnode);
    }


    public NaryTreeNode<T> getRoot(){
        return root;
    }


    public NaryTreeNode<T> getRootElement(){
        return root.getChild(0);
    }

    /**
     * A method that checks if tree is empty
     */
    public boolean isEmpty(){
        if (root == null){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * A method that checks the size of the tree
     * @param node the parameter in which size is calculated from
     */
    public int size(NaryTreeNode<T> node){
        int x = 1;
        if (node == null) {
            return 0;
        }
        for (int i = 0; i < node.getNumChildren(); i++) {//iterates over children and adds to size for each one
            x += size(node.getChild(i));
        }
        return x;
    }

    /**
     * A method that performs a preorder traversal of the tree
     * @param element the item to be added to list
     * @param list the list of elements
     */
    public void preorder(NaryTreeNode<T> element, ArrayUnorderedList<T> list){
        if(element!=null) {
            list.addToRear(element.getData());
            for(int i = 0; i< element.getNumChildren(); i++){//recursive traversal, calling preorder in preorder
                preorder(element.getChild(i),list);
            }
        }
    }

    /**
     * A method that iterates the preorder traversal
     */
    public Iterator<T> iteratorPreorder(){
        ArrayUnorderedList<T> arraylist=new ArrayUnorderedList<>();
        preorder(root,arraylist);
        return arraylist.iterator();
    }



    public String toString(){
        if (!isEmpty()) {
            String s = "";
            Iterator<T> list;
            list = iteratorPreorder();
            while (list.hasNext()) {
                s += list.next().toString();
            }
            return s;
        }
        return "Tree is empty.";
    }
}
