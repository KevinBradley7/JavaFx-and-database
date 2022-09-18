package View;

import javafx.geometry.HPos;
//import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;

import Controller.StudentController;

public class ModuleTab extends Tab{
	
	private Label enterModule;
	private Label enterGrade;
	
	private TextField module;
	private TextField grade;
	
	private ComboBox<String> studentPicker;
	private Button updatePicker;
	
	private Button addButton;
	
	private static StudentController studentControl = StudentTab.getControl();
	
	public ModuleTab(){
	  
		// Upon load the list of Students to pick from will be put in a ComboBox
		
		studentPicker = new ComboBox<String>();
		for(int i = 0; i < studentControl.getAllStudents().size(); i ++) {
			studentPicker.getItems().add(studentControl.getStudent(i).getStudentID());
		}
		studentPicker.setPromptText("Select a Student");
		
		setText("Record Modules");
		
		// Making Label, TextFields and Buttons
		
		enterModule = new Label("Name of Module: ");
		module = new TextField();
		
		enterGrade = new Label("Grade of Student: ");
		grade = new TextField();
		
		updatePicker = new Button("Update Students");
		
		addButton = new Button("Add Record");
		
		// Updating the list of students to pick from this is incase a student has being removed in the student Tab
		
		updatePicker.setOnAction(e ->{
			studentPicker.getItems().removeAll(studentPicker.getItems());
			studentPicker.setPromptText("Select a Student");
			for(int i = 0; i < studentControl.getAllStudents().size(); i ++) {
				studentPicker.getItems().add(studentControl.getStudent(i).getStudentID());
			}
		});
		
		addButton.setOnAction(e ->{
			String selection = studentPicker.getSelectionModel().getSelectedItem();
			if(studentPicker.getSelectionModel().getSelectedItem() == null) {
				System.out.println("No Student ID was selected");
			}else if(module.getText() == "" || grade.getText() == "") {
				System.out.println("Some of the Fields were left blank");
			}else if(Integer.parseInt(grade.getText()) < 0 || Integer.parseInt(grade.getText()) > 100) {
				System.out.println("Invalid grade has been entered. Try between 0 and 100");
			}else {
				studentControl.addModule(module.getText(), grade.getText(), selection);
			}
			module.setText("");
			grade.setText("");
		});
		
		// Making the gridpane and adding the buttons, labels and textfields into their rows and columns
		GridPane  gp = new GridPane();
		gp.add(studentPicker, 2, 1, 1, 1);
		gp.add(updatePicker, 3, 1, 1, 1 );
		
		// Setting the position of updatePicker to the right of column 3 row 1
		GridPane.setHalignment(updatePicker, HPos.RIGHT);
		
		gp.add(enterModule, 2, 3, 1, 1);
		gp.add(module, 3, 3, 1, 1);
		
		gp.add(enterGrade, 2, 5, 1, 1);
		gp.add(grade, 3, 5, 1, 1);
		
		gp.add(addButton, 3, 7, 1, 1);
		
		// Setting the position of addButton to the right of column 3 row 7
		GridPane.setHalignment(addButton, HPos.RIGHT);
		
		gp.setPadding(new Insets(30, 30, 30, 30));
		
		gp.setVgap(10);
		gp.setHgap(10);

		setContent(gp);
   }
}
