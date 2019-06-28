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
	private BinaryTree left;
	private int frequency;
	private char character;
	private BinaryTree right;
	
	public BinaryTree(BinaryTree nodeLeft, int nodefrequency, char nodeCharacter, BinaryTree nodeRight) {
		left = nodeLeft;
		frequency = nodefrequency;
		character = nodeCharacter;
		right = nodeRight;
	}
	
	public int getFrequency() {
		return frequency;
	}
	
	public char getCharacter() {
		return character;
	}
	
	public BinaryTree getRightTree() {
		return right;
	}
	
	public BinaryTree getLeftTree() {
		return left;
	}
	
	
	// exibe a arvore dada no terminal
	public void showTreeOnConsole(BinaryTree tree, int space) {
		if(tree == null) return;
		showTreeOnConsole(tree.getRightTree(), space + 7);
		for(int i = 0; i <= space; i++)
			System.out.print(" ");
		System.out.println("(" + tree.getFrequency() + " | " + tree.getCharacter() + ")");
		showTreeOnConsole(tree.getLeftTree(), space + 7);
	}
	
	// monta a estrutura da arvore com os nos e com as linhas e os exibe na interface grafica
	public void showGraphicTree(BinaryTree tree, Pane pane, int posX, int posY) {
		showGraphicLines(tree, pane, posX + 35, posY + 35);
		showGraphicNode(tree, pane, posX, posY);
	}
	
	// monta e exibe um no na interface grafica
	public void showGraphicNode(BinaryTree tree, Pane pane, int posX, int posY) {
		if(tree == null) return;
		StackPane graphicNode = tree.getGraphicNode(posX, posY);
		pane.getChildren().add(graphicNode);
		showGraphicNode(tree.getLeftTree(), pane, posX - 200, posY + 200);
		showGraphicNode(tree.getRightTree(), pane, posX + 200, posY + 200);	
	}
	
	// monta e exibe uma linha entre dois nos na interface grafica
	// linha azul representa 1
	// linha vermelha representa 0
	private void showGraphicLines(BinaryTree tree, Pane pane, int posX, int posY) {
		if (tree.getRightTree() == null && tree.getLeftTree() == null) return;
		int[] posNode = {posX, posY};
		int[] posLeftNode = {posX - 200, posY + 200};
		int[] posRightNode = {posX + 200, posY + 200};
		Line rightLine = new Line(posNode[0], posNode[1], posRightNode[0], posRightNode[1]);
		Line leftLine = new Line(posNode[0], posNode[1], posLeftNode[0], posLeftNode[1]);
		rightLine.setStroke(Color.RED);
		leftLine.setStroke(Color.BLUE);
		pane.getChildren().add(rightLine);
		pane.getChildren().add(leftLine);
		showGraphicLines(tree.getLeftTree(), pane, posX - 200, posY + 200);
		showGraphicLines(tree.getRightTree(), pane, posX + 200, posY + 200);	
	}
	
	// monta um no de arvore visualmente e retorna o no
	 private StackPane getGraphicNode(int posX, int posY) {
		Circle nodeCircle = new Circle(posX, posY, 35);
        nodeCircle.setStroke(Color.BLACK);
        //nodeCircle.setFill(Paint.valueOf("#15ff00"));
        nodeCircle.setFill(Color.GREENYELLOW);
        nodeCircle.setStrokeWidth(1);
        Text nodeText = new Text (Integer.toString(frequency)+" " +  Character.toString(character));
        StackPane graphicNode = new StackPane();
        graphicNode.getChildren().addAll(nodeCircle, nodeText);
    	graphicNode.setLayoutX(posX);
    	graphicNode.setLayoutY(posY);
        return graphicNode;
	}

}
