package application;

import View.DisplayingStudents;
import View.EditTab;
import View.HeapSpace;
import View.ModuleTab;
import View.StudentTab;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;

/**
 * 
 * @author KevinBradley
 * @version 1.0
 *
 */
public class Main extends Application {
	
	private TabPane collection;

	/**
	 * Creation of the Tabs
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane mainPane = new BorderPane();  
			Group root = new Group();
			Scene scene = new Scene(root,500,500);
			// Creating the Tab Pane and adding each tab to it and setting it so the tabs can't be closed.
			collection = new TabPane();
			
			collection.getTabs().add(new StudentTab());
			collection.getTabs().add (new ModuleTab());
			collection.getTabs().add(new DisplayingStudents());
			collection.getTabs().add(new EditTab());
			collection.getTabs().add(new HeapSpace());
			collection.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
			
			mainPane.setCenter(collection);
			
		    mainPane.prefHeightProperty().bind(scene.heightProperty());
		    mainPane.prefWidthProperty().bind(scene.widthProperty());
		      
		    root.getChildren().add(mainPane);
			// Labelling the stage and setting and Min Height and Width
			primaryStage.setScene(scene);
		    primaryStage.setMinHeight(450);
		    primaryStage.setMinWidth(450);
			primaryStage.setTitle("MTU Student Record System");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/** Runs the file
	 * 
	 * @param args Representing a string of arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
