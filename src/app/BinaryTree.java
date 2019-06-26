package app;

import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BinaryTree {
	BinaryTree left;
	int frequency;
	char caracter;
	BinaryTree right;
	
	public BinaryTree(BinaryTree nodeLeft, int nodefrequency, char nodeCaracter, BinaryTree nodeRight) {
		left = nodeLeft;
		frequency = nodefrequency;
		caracter = nodeCaracter;
		right = nodeRight;
	}
	
	public int getFrequency() {
		return frequency;
	}
	
	public void showTreeOnConsole(BinaryTree tree) {
		if(tree == null) return;
		showTreeOnConsole(tree.right);
		System.out.println("(" + tree.frequency + " | " + tree.caracter + ")");
		showTreeOnConsole(tree.left);
	}
	
	public void showGraphicTree(BinaryTree tree, Pane pane, int posX, int posY) {
		showGraphicLines(tree, pane, posX + 35, posY + 35);
		showGraphicNode(tree, pane, posX, posY);
	}
	
	public void showGraphicNode(BinaryTree tree, Pane pane, int posX, int posY) {
		if(tree == null) return;
		StackPane graphicNode = tree.getGraphicNode(posX, posY);
		pane.getChildren().add(graphicNode);
		showGraphicNode(tree.left, pane, posX - 200, posY + 200);
		showGraphicNode(tree.right, pane, posX + 200, posY + 200);	
	}
	
	public void showGraphicLines(BinaryTree tree, Pane pane, int posX, int posY) {
		
		if (tree.right == null && tree.left == null) return;
		int[] posNode = {posX, posY};
		int[] posLeftNode = {posX - 200, posY + 200};
		int[] posRightNode = {posX + 200, posY + 200};
		System.out.println(posX);
		System.out.println(posY);
		Line rightLine = new Line(posNode[0], posNode[1], posRightNode[0], posRightNode[1]);
		Line leftLine = new Line(posNode[0], posNode[1], posLeftNode[0], posLeftNode[1]);
		rightLine.setStroke(Color.RED);
		leftLine.setStroke(Color.BLUE);
		pane.getChildren().add(rightLine);
		pane.getChildren().add(leftLine);
		showGraphicLines(tree.left, pane, posX - 200, posY + 200);
		showGraphicLines(tree.right, pane, posX + 200, posY + 200);	
	}
	
	public StackPane getGraphicNode(int posX, int posY) {
		Circle nodeCircle = new Circle(posX, posY, 35);
        nodeCircle.setStroke(Color.BLACK);
        //nodeCircle.setFill(Paint.valueOf("#15ff00"));
        nodeCircle.setFill(null);
        nodeCircle.setStrokeWidth(1);
        Text nodeText = new Text (Integer.toString(frequency)+ Character.toString(caracter));
        StackPane graphicNode = new StackPane();
        graphicNode.getChildren().addAll(nodeCircle, nodeText);
    	graphicNode.setLayoutX(posX);
    	graphicNode.setLayoutY(posY);
        return graphicNode;
	}

}
