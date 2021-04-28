/**
 * A class that represents a tree that contains the Shape objects in the nodes.
 * @author Derek Liu
 */

public class ShapeTree {
    private LinkedNaryTree<Shape> tree;

    public LinkedNaryTree<Shape> getTree(){
        return tree;
    }

    public NaryTreeNode<Shape> getRoot(){
        return tree.getRoot();
    }

    /**
     * A method that adds a shape to the tree.
     * @param shape the parameter to be added
     */

    public void addShapeNode(Shape shape){
        NaryTreeNode parent = new NaryTreeNode(shape);
        if (tree == null){//if the tree is empty
            tree = new LinkedNaryTree(parent);//create new tree
        }
        NaryTreeNode traversal = addShapeNodeHelper(shape);
        if (traversal != null){//see where to add shape
            traversal.addChild(parent);
        }
    }


    /**
     * A helper method for addshapenode that performs a stack based traversal of the tree.
     * @param shape the parameter to be added to the tree.
     */

    public NaryTreeNode<Shape> addShapeNodeHelper(Shape shape){
        if(getRoot() == null){
            return null;
        }
        ArrayStack<NaryTreeNode> stackofnodes = new ArrayStack<>();
        stackofnodes.push(getRoot());
        while (!stackofnodes.isEmpty()){
            NaryTreeNode<Shape> v = stackofnodes.pop();
            if (checkNode(v, shape)){
                return v;
            }
            for (int i = v.getNumChildren()-1; i>=0; i--){//iterates over child nodes
                if (v.getChild(i)!=null){
                    stackofnodes.push(v.getChild(i));
                }
            }
        }
        return null;
    }

    /**
     * A method that checks the rules for adding a new node
     * @param node the parent node
     * @param shape the child node
     */

    public boolean checkNode(NaryTreeNode<Shape> node, Shape shape){
        int parentshape = node.getData().getNumSides();
        String parentcolor = node.getData().getColour();

        if (node.getNumChildren() == parentshape) {//rule 1
            return false;
        }
        else if (shape.getColour().equals(parentcolor)){//rule 2
            return false;
        }
        else {
            for (int i =node.getNumChildren()-1; i>=0; i--){//rule 3
                if (node.getChild(i) == null){
                    return false;
                }
                if (node.getChild(i).getData().getColour().equals(shape.getColour())){
                    return false;
                }
            }
        }
        return true;
    }

    public String toString(){//outputs string format
        String output = "Node containing "+ this.tree;
        return output;
    }
}
