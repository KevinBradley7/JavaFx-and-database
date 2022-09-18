package View;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.stage.Modality;

public class ExitWindow {
	
	public static void display() {
		
		Label text = new Label("Are you sure you want to quit?");
		
		Button continueUse = new Button("Continue");
		Button exit = new Button("Exit");
		
		HBox h1 = new HBox(6);
		h1.getChildren().addAll(continueUse, exit);
		h1.setAlignment(Pos.CENTER);
		
		VBox v1 = new VBox();
		v1.getChildren().addAll(text, h1);
		v1.setAlignment(Pos.CENTER);
		v1.setPadding(new Insets(10, 20, 20, 20));
		
		Stage window = new Stage();
		
		continueUse.setOnAction(e -> {
		    window.close();
		});
		
		exit.setOnAction(e->{
			System.exit(0);
		});
		
		window.setTitle("Exit");
		window.setMinWidth(350);
		window.setMinHeight(120);
		window.initModality(Modality.APPLICATION_MODAL);
		
		Scene scene = new Scene(v1);
		window.setScene(scene);
		window.showAndWait();
		
	}
}
