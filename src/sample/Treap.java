package sample;

import java.util.StringTokenizer;

public class Treap {
    Treap parent;
    Treap left;
    Treap right;
    int priority;
    String value;


    public Treap() {

        priority = -1;

    }

    public Treap(int priority, String value) {
        this.priority = priority;
        this.value = value;
    }

    public Treap(Treap parent, Treap left, Treap right, int priority, String value) {
        this.parent = parent;
        this.left = left;
        this.right = right;
        this.priority = priority;
        this.value = value;
    }

    public void add(Treap treap){

        getRoot().add_to_tree(treap);
    }

    private void add_to_tree(Treap subTreap) {

        // -----------------------------when tree is Empty--------------------------
        if (this.value == null || this.priority == -1) {

            this.value = subTreap.value;
            this.priority = subTreap.priority;

            return;
        }

        // --------------------------compare two values----------------------------------
        int compare = value.compareTo(subTreap.value);


        // ---------------------------set new node parent to root-------------------------
        subTreap.parent = this;


        if (compare > 0) {
            if (left != null)
                left.add_to_tree(subTreap);
            else
                left = subTreap;

            if (priority < left.priority)
                toRight();
        }
        else if (compare < 0) {
            if (right != null)
                right.add_to_tree(subTreap);
            else
                right = subTreap;

            if (priority < right.priority)
                toLeft();
        }
    }


    private void toLeft() {

        System.out.println(value);

        if (parent != null) {
            if (parent.right != null && parent.right.value.equals(value))
                parent.right = right;

            else if (parent.left != null && parent.left.value.equals(value))
                parent.left = right;
        }
        Treap tmp = right.left;

        if (tmp != null) {
            tmp.parent = this;
            System.out.println("not null");
        }

        right.left = this;
        right.parent = parent;
        parent = right;
        right = tmp;

    }


    private void toRight() {

        // ---------------------if node wasn't root------------------------

//        System.out.println(value);

        if (parent != null) {
            if (parent.right != null && parent.right.value.equals(value))
                parent.right = left;

            else if (parent.left != null && parent.left.value.equals(value))
                parent.left = left;
        }
        Treap tmp = left.right;
        if (tmp != null) {
            tmp.parent = this;

            System.out.println("not null");
        }

        left.right = this;
        left.parent = parent;
        parent = left;
        left = tmp;

    }


    public String toString() {

        StringBuilder builder = new StringBuilder();

        treeTraversal(getRoot(), builder);
        builder.append("");

        return builder.toString();
    }


    private void treeTraversal(Treap node, StringBuilder str) {
        if (node == null)
            return;

        treeTraversal(node.left, str.append("("));
        str.append(node.value).append("/").append(node.priority);

        treeTraversal(node.right, str);
        str.append(")");
    }


    public Treap getRoot() {
        if (parent == null)
            return this;

        return parent.getRoot();
    }


    protected String node_toString(){

        return value + "/" + priority;
    }

    public int findHeight(Treap aNode) {
        if (aNode == null) {
            return -1;
        }

        int lefth = findHeight(aNode.left);
        int righth = findHeight(aNode.right);

        if (lefth > righth) {
            return lefth + 1;
        } else {
            return righth + 1;
        }
    }

}


