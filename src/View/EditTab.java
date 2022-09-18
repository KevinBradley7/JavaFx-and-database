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
import javafx.scene.control.DatePicker;
import Controller.StudentController;
import Model.Person;

public class EditTab extends Tab{
	
	private Label enterStudentID;
	private Label enterName;
	private Label enterDob;
	private Label enterInitial;
	private Label enterLastName;
	
	private TextField studentID;
	private TextField name;
	private TextField initial;
	private TextField lastName;
	
	private DatePicker dob;
	
	private ComboBox<String> studentPicker;
	private Button updatePicker;
	
	private Button updateButton;
	
	private static StudentController studentControl = StudentTab.getControl();
	
	public EditTab(){
	  
		// Upon load the list of Students to pick from will be put in a ComboBox
		
		studentPicker = new ComboBox<String>();
		for(int i = 0; i < studentControl.getAllStudents().size(); i ++) {
			studentPicker.getItems().add(studentControl.getStudent(i).getStudentID());
		}
		studentPicker.setPromptText("Select a Student");
		
		setText("Edit Students");
		
		// Making Label, TextFields and Buttons
		
		enterStudentID = new Label("StudentID: ");
		studentID = new TextField();
		
		enterName = new Label("First Name: ");
		name = new TextField();
		
		enterInitial = new Label("Initial: ");
		initial = new TextField();
		
		enterLastName = new Label("Last Name: ");
		lastName = new TextField();
		
		enterDob = new Label("Dob: ");
		
		dob = new DatePicker();
		dob.setEditable(false);
		
		updatePicker = new Button("Update List");
		
		updateButton = new Button("Update Student");
		
		// Updating the list of students to pick from this is incase a student has being removed in the student Tab
		
		updatePicker.setOnAction(e ->{
			studentPicker.getItems().removeAll(studentPicker.getItems());
			studentPicker.setPromptText("Select a Student");
			for(int i = 0; i < studentControl.getAllStudents().size(); i ++) {
				studentPicker.getItems().add(studentControl.getStudent(i).getStudentID());
			}
		});
		
		updateButton.setOnAction(e ->{
			String selection = studentPicker.getSelectionModel().getSelectedItem();
			if(name.getText().isEmpty() || studentID.getText().isEmpty() || initial.getText().isEmpty() || lastName.getText().isEmpty()) {
				System.out.println("Data cant be added");
			}else {
				Person person = new Person(name.getText(), initial.getText(), lastName.getText());
				studentControl.updateStudentInList(selection, person, studentID.getText(), dob.getValue());
				name.setText("");
				initial.setText("");
				lastName.setText("");
				studentID.setText("");
			}
		});
		
		// Making the gridpane and adding the buttons, labels and textfields into their rows and columns
		GridPane  gp = new GridPane();
		gp.add(studentPicker, 2, 1, 1, 1);
		gp.add(updatePicker, 3, 1, 1, 1 );
		
		// Setting the position of updatePicker to the right of column 3 row 1
		GridPane.setHalignment(updatePicker, HPos.RIGHT);
		
		gp.add(enterStudentID, 2, 3, 1, 1);
		gp.add(studentID, 3, 3, 1, 1);
		
		gp.add(enterName, 2, 5, 1, 1);
		gp.add(name, 3, 5, 1, 1);
		
		gp.add(enterInitial, 2, 7, 1, 1);
		gp.add(initial, 3, 7, 1, 1);
		
		gp.add(enterLastName, 2, 9, 1, 1);
		gp.add(lastName, 3, 9, 1, 1);
		
		gp.add(enterDob, 2, 11, 1, 1);
		gp.add(dob, 3, 11, 1, 1);
		
		gp.add(updateButton, 3, 13, 1, 1);
		
	
		GridPane.setHalignment(updateButton, HPos.RIGHT);
		
		gp.setPadding(new Insets(30, 30, 30, 30));
		
		gp.setVgap(10);
		gp.setHgap(10);

		setContent(gp);
   }
}
