package View;

import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;

import Controller.StudentController;

public class DisplayingStudents extends Tab {
	
	private ComboBox<String> studentPicker;
	private Button updatePicker;
	private Button listStudent;
	private TextArea displayStudent;
	
	private ComboBox<String> sortModule;
	
	private static StudentController studentControl = StudentTab.getControl();
	
	public DisplayingStudents() {
		studentPicker = new ComboBox<String>();
		for(int i = 0; i < studentControl.getAllStudents().size(); i ++) {
			studentPicker.getItems().add(studentControl.getStudent(i).getStudentID());
		}
		studentPicker.setPromptText("Select a Student");
		
		updatePicker = new Button("Update Students");
		
		listStudent = new Button("List Student");
		
		sortModule = new ComboBox<String>();
		sortModule.setPromptText("Ordering Modules");
		sortModule.getItems().add("By Name");
		sortModule.getItems().add("By Grade");
		sortModule.getItems().add("Greater 70");
		
		displayStudent = new TextArea("Select a Student and their Modules and Grade results will appear here...");
		displayStudent.setEditable(false);
		
		setText("View Records");
		
		updatePicker.setOnAction(e ->{
			studentPicker.getItems().removeAll(studentPicker.getItems());
			studentPicker.setPromptText("Select a Student");
			for(int i = 0; i < studentControl.getAllStudents().size(); i ++) {
				studentPicker.getItems().add(studentControl.getStudent(i).getStudentID());
			}
		});	
		
		listStudent.setOnAction(e ->{
			int index = sortModule.getSelectionModel().getSelectedIndex();
			String selection = studentPicker.getSelectionModel().getSelectedItem();
			if(index == 2) {
				String modulesGreater70 = studentControl.getModulesgreater70(selection);
				displayStudent.setText(modulesGreater70);
			}else {
				if(index == 0){
					studentControl.sortByNameModule(selection);
				}else if(index == 1) {
					studentControl.sortByGradeModule(selection);
				}
				String allModulesforStudent = studentControl.getModules(selection);
				displayStudent.setText(allModulesforStudent);
			}
		});
		
		
		GridPane gp = new GridPane();
		gp.add(studentPicker, 2, 1, 1, 1);
		gp.add(updatePicker, 2, 3, 1, 1 );
		
		gp.add(displayStudent, 2, 5, 1, 1);
		gp.add(sortModule, 2, 7, 1, 1);
		gp.add(listStudent, 2, 7, 1, 1);

		gp.setHalignment(listStudent, HPos.RIGHT);
		
		gp.setPadding(new Insets(30, 30, 30, 30));
		
		gp.setVgap(5);
		gp.setHgap(5);
		
		setContent(gp);
		
	}

}
