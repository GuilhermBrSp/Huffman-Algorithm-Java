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
import java.util.Scanner;;


public class Main extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter your string: ");
		String text = keyboard.nextLine();
		TextFile file = new TextFile(text);
		HuffmanTree compressionAlgorithm = new HuffmanTree();
		compressionAlgorithm.mountTree(file.getFrequency());
		BinaryTree huffmanTree = compressionAlgorithm.getTree();
		huffmanTree.showTreeOnConsole(huffmanTree);
		keyboard.close();
		
		
		
		Pane root = new Pane();
        ScrollPane sp = new ScrollPane();
   
        sp.setPannable(true); // it means that the user should be able to pan the viewport by using the mouse.
        Scene scene = new Scene(sp, 100000, 100000);
        huffmanTree.showGraphicTree(huffmanTree, root, 10000, 200);

        sp.setContent(root);
        stage.setScene(scene);
        stage.show();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String... args) {
		launch(args);
	}
}
