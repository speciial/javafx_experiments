package io.github.speciial;

import io.github.speciial.treeVis.Node;
import io.github.speciial.treeVis.Tree;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.Queue;

public class TreeVisApplication extends Application {


    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();

        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Tree tree = new Tree();

        tree.insert(new Node(4));
        tree.insert(new Node(7));
        tree.insert(new Node(2));
        tree.insert(new Node(9));
        tree.insert(new Node(1));
        tree.insert(new Node(6));
        tree.insert(new Node(3));

        tree.inorderTreeWalk(tree.getRoot());

        Queue<Node> nodes = new LinkedList<>();
        nodes.add(tree.getRoot());
        tree.getRoot().setX(800.0 / 2);
        tree.getRoot().setY(50);
        tree.getRoot().setLevel(1);
        gc.fillText("" + tree.getRoot().getValue(), tree.getRoot().getX(), tree.getRoot().getY());
        gc.strokeOval(tree.getRoot().getX() - 10, tree.getRoot().getY() - 10, 20, 20);

        while (!nodes.isEmpty()) {
            Node temp = nodes.poll();

            if (temp.getLeft() != null) {
                Node leftNode = temp.getLeft();
                leftNode.setLevel(temp.getLevel() + 1);
                leftNode.setX(temp.getX() - (80 - (temp.getLevel() + 1) * 20));
                leftNode.setY((temp.getLevel() + 1) * 50);

                gc.strokeOval(leftNode.getX() - 10, leftNode.getY() - 10, 20, 20);
                gc.fillText("" + leftNode.getValue(), leftNode.getX(), leftNode.getY());
                gc.strokeLine(leftNode.getX(), leftNode.getY(), temp.getX(), temp.getY());

                nodes.add(leftNode);
            }
            if (temp.getRight() != null) {
                Node rightNode = temp.getRight();
                rightNode.setLevel(temp.getLevel() + 1);
                rightNode.setX(temp.getX() + (80 - (temp.getLevel() + 1) * 20));
                rightNode.setY((temp.getLevel() + 1) * 50);

                gc.strokeOval(rightNode.getX() - 10, rightNode.getY() - 10, 20, 20);
                gc.fillText("" + rightNode.getValue(), rightNode.getX(), rightNode.getY());
                gc.strokeLine(rightNode.getX(), rightNode.getY(), temp.getX(), temp.getY());

                nodes.add(rightNode);
            }
        }

        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        stage.setTitle("Tree Visualisation");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
