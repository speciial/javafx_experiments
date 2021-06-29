package io.github.speciial.treeVis;

public class Tree {

    private Node root;

    private int nodeCounter;

    public Tree() {
        root = null;
    }

    public void insert(Node node) {
        Node trailingPointer = null;
        Node currentPointer = root;

        while (currentPointer != null) {
            trailingPointer = currentPointer;
            if (node.getValue() < currentPointer.getValue()) {
                currentPointer = currentPointer.getLeft();
            } else {
                currentPointer = currentPointer.getRight();
            }
        }
        node.setParent(trailingPointer);
        if (trailingPointer == null) {
            root = node;
        } else if (node.getValue() < trailingPointer.getValue()) {
            trailingPointer.setLeft(node);
        } else {
            trailingPointer.setRight(node);
        }

        nodeCounter++;
    }

    public void inorderTreeWalk(Node node) {
        if (node != null) {
            inorderTreeWalk(node.getLeft());
            System.out.println("Value: " + node.getValue());
            inorderTreeWalk(node.getRight());
        }
    }

    public Node getRoot() {
        return root;
    }

    public int getNodeCounter() {
        return nodeCounter;
    }

}
