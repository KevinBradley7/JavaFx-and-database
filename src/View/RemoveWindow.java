package View;

import javafx.stage.Stage;
import javafx.scene.control.ListView;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.stage.Modality;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import Controller.StudentController;

public class RemoveWindow {
	static String selected;
	static ListView<String> listView;
	static ObservableList<String> listSelected;
	
	private static StudentController studentControl = StudentTab.getControl();
	
	public static String display() {
		
		listView = new ListView<>();
		Stage window = new Stage();
		Button remove = new Button("Remove");
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Removing students");
		window.setMinWidth(350);
		window.setMinHeight(300);
		
		for(int i = 0; i < studentControl.getAllStudents().size(); i ++) {
			listView.getItems().add(studentControl.getStudent(i).getStudentID());
		}
		listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		remove.setOnAction(e ->{
			listSelected = listView.getSelectionModel().getSelectedItems();
			if(listSelected.isEmpty()) {
				System.out.println("No student to remove or no student selected");
			}else {
				selected = listSelected.get(0).toString();
			}
			window.close();
		});
		
		VBox v1 = new VBox(5);
		v1.getChildren().addAll(listView, remove);
		v1.setAlignment(Pos.CENTER);
		v1.setPadding(new Insets(5, 5, 5, 5));
		
		Scene scene = new Scene(v1);
		window.setScene(scene);
		window.showAndWait();
		
		return selected;
	}
	
}
