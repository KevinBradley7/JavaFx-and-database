package View;

import Controller.StudentController;
import Model.Person;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.control.Tab;
import javafx.scene.control.DatePicker;

public class StudentTab extends Tab{
	
	private Label enterName;
	private Label enterInitial;
	private Label enterLastName;
	
	private Label enterStudentID;
	private Label enterDOB;
	
	private TextField name;
	private TextField initial;
	private TextField lastName;
	
	private TextField studentID;
	private TextArea allStudents;
	
	private DatePicker dob;
	
	private Button addButton, removeButton, listButton, exitButton;
	
	private static StudentController studentControl = new StudentController();
	
	public static StudentController getControl() {
		return studentControl;
	}
	
	public StudentTab() {
		try {
			setText("Add Students");
			
			/**
			 *  Making the Labels and TextFields
			 */
			enterName = new Label("First Name:");
			name= new TextField();
			
			enterInitial = new Label("Initial:");
			initial = new TextField();
			initial.setMaxWidth(50);
			
			enterLastName = new Label("Last Name:");
			lastName = new TextField();
			
			enterStudentID = new Label("Student ID:");
			studentID = new TextField();
			
			enterDOB = new Label("Date of Birth:");
			dob = new DatePicker();
			dob.setEditable(false);
				
			/**
			 *  Buttons 
			 */
			addButton = new Button("Add");
			removeButton = new Button("Remove");
			listButton = new Button("List");
			exitButton = new Button("Exit");
			
			/** 
			 * The text area allStudents is not able to be edited.
			 */
			allStudents = new TextArea("Students in the application");
			allStudents.setEditable(false);
			
			/**
			 *  Adding the button functionality for adding student
			 *  Once the addButton is pressed it creates a new Person using the input the user put into the txt fields 
			 *  It accesses a method in the student Controller and sends the person object , studentID and dob as values
			 *  to it. The text fields are then reset back to empty
			 */
			addButton.setOnAction( e ->{
				Person person = new Person(name.getText(), initial.getText(), lastName.getText());
				studentControl.addStudentToList(person ,studentID.getText(), dob.getValue());
				name.setText("");
				initial.setText("");
				lastName.setText("");
				studentID.setText("");
			});
			
			/**
			 *  Adding the button functionality for removing Student, this opens up a new pop out window that deals with 
			 *  the removal
			 */
			removeButton.setOnAction(e ->
				studentControl.removeStudentPopUp()
			);
			
			/**
			 *  Adding the button functionality listing student, this access the method in the studentController getStudents
			 *  which creates a String of all the students. If this is empty a message appears in the text area otherwise
			 *  the students get added here
			 */
			listButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String allOfStudents = studentControl.getStudents();
				if(allOfStudents == "") {
					allStudents.setText("Waiting for students to be added...");
				}else {
					allStudents.setText(allOfStudents);
				}
			 	}
			});		
			
			/**
			 *  Adding the button functionality for exiting. This accesses a method in the studentController exitProgram
			 *  which opens a smaller pop out window
			 */
			
			exitButton.setOnAction(e ->
				studentControl.exitProgram()
			);
			
			/**
			 *  Adding the TextFields, Labels, Buttons and Text Area to respective HBoxs and VBoxs
			 */
			HBox r1 = new HBox(10);
			r1.getChildren().addAll(enterName, name, enterInitial, initial);
			r1.setPadding(new Insets(10, 20, 0, 20));
			
			HBox r2 = new HBox(10);
			r2.getChildren().addAll(enterLastName, lastName);
			r2.setPadding(new Insets(0, 20, 0, 20));
			
			HBox r3 = new HBox(10);
			r3.getChildren().addAll(enterStudentID, studentID);
			r3.setPadding(new Insets(0, 20, 0, 20));
			
			HBox r4 = new HBox(10);
			r4.getChildren().addAll(enterDOB, dob);
			r4.setPadding(new Insets(0, 20, 0, 20));
			
			HBox r5 = new HBox(10);
			r5.getChildren().addAll(addButton, removeButton, listButton);
			r5.setPadding(new Insets(0, 20, 0, 20));
			
			HBox r6 = new HBox(100);
			r6.getChildren().addAll(allStudents);
			HBox.setHgrow(allStudents, Priority.ALWAYS);
			r6.setPadding(new Insets(0, 20, 0, 20));
			
			HBox r7 = new HBox(10);
			r7.getChildren().addAll(exitButton);
			r7.setAlignment(Pos.BOTTOM_RIGHT);
			r7.setPadding(new Insets(0, 20, 0, 20));
			
			VBox v1 = new VBox(10);
			v1.getChildren().addAll(r1, r2, r3, r4, r5, r6, r7);
			
			setContent(v1);
		
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
