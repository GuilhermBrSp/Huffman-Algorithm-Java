package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;;


public class Main extends Application {
	

	@Override
	public void start(Stage stage) throws Exception {

		Scanner keyboard = new Scanner(System.in);
		System.out.println("Insira o texto do seu arquivo: ");
		String text = keyboard.nextLine();
		keyboard.close();
		
		// criando um objeto textFile para representar o 'arquivo' lido
		TextFile file = new TextFile(text);
		
		HuffmanTree compressionAlgorithm = new HuffmanTree();
		compressionAlgorithm.compressFile(file);
		BinaryTree huffmanTree = compressionAlgorithm.getTree();
		
		// exibindo a arvore montada no console
		System.out.println("Exibindo Arvore de Huffman no console: ");
		huffmanTree.showTreeOnConsole(huffmanTree, 0);
		

		System.out.println("Representacao Binaria do seu arquivo comprimido: \n" + compressionAlgorithm.getCompressedBitString());
		System.out.println("\n Taxa de Compressao (levando em conta que cada caractere em java tem 2 bytes): ");
		System.out.println(compressionAlgorithm.getCompressionRatio()+"%");
		System.out.println("Exibindo Arvore de Huffman no painel grafico...");
		
		
		
		Pane root = new Pane();
        ScrollPane sp = new ScrollPane();
   
        sp.setPannable(true);
        Scene scene = new Scene(sp, 1500, 500);
        huffmanTree.showGraphicTree(huffmanTree, root, 10000, 200);

        sp.setContent(root);
        stage.setScene(scene);
        stage.show();
	}


	public static void main(String... args) {
		// o launch eh utilizado para iniciar um projeto que utiliza javafx
		launch(args);
	}
}
