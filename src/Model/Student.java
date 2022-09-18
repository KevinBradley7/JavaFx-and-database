package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * @author KevinBradley
 * @version 1.0
 */

public class Student{
	
	private String name;
	private String studentID;
	private LocalDate dob;
	private ArrayList<Module> modules;
	
	private Person person;
	
	/**
	 *  Creates a student with an unspecified person object, studentID or dob
	 */
	public Student(){}
	
	/** Creates a student with the specified person object, studentID and a date of birth. An arraylist of Modules is also created
	 * 
	 * @param person The student's full name which is inside the person object
	 * @param studentID The student's studentID
	 * @param dob The students's date of birth
	 */
	public Student(Person person, String studentID, LocalDate dob){
		modules = new ArrayList<Module>();
		this.studentID = studentID;
		this.dob = dob;
		
		this.person = person;
	}
	
	public String getName() {
		return name;
	}

	/** Gets the students modules that they are taking
	 * 
	 * @return An arraylist of modules representing all the modules the student is taking
	 */
	public ArrayList<Module> getModules() {
		return modules;
	}
	
	/** Sets the modules that the student is taking
	 * 
	 * @param modules An arraylist containing the student's modules
	 */
	public void setModules(ArrayList<Module> modules) {
		this.modules = modules;
	}
	
	/** Adding a module to the array list of modules that the student is taking
	 * 
	 * @param module A String that contains the name of the module
	 * @param grade An int that contains the grade of the module
	 */
	public void addModule(String module, int grade) {
		Module studentModule = new Module(module, grade);
		modules.add(studentModule);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	/** Gets the student's studentID
	 * 
	 * @return A String representing the student's studentID
	 */
	public String getStudentID() {
		return studentID;
	}
	
	/** Sets the student's studentID
	 * 
	 * @param studentID A String containing the student's studentID
	 */
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	
	/** Gets the students dob
	 * 
	 * @return A LocalDate representing the student's dob
	 */
	public LocalDate getDob() {
		return dob;
	}
	
	/** Sets the student's dob
	 * 
	 * @param dob A LocalDate containing the student's dob
	 */
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	
	/** Gets the student's full name
	 * 
	 * @return A Person object representing the student's full name
	 */
	public Person getPerson() {
		return person;
	}
	
	/** Sets the student's full name
	 * 
	 * @param person A Person object containing the student's full name
	 */
	public void setPeron(Person person) {
		this.person = person;
	}
	
	
	/**
	 *  Overridden toString method prints in the format of "Name: person.toString StudentID: studentID Date of birth: dob"
	 *  where person.toString is the students full name 
	 */
	public String toString(){
		String f = "Name: " + person.toString() + " StudentID: " + studentID + " Date of birth: " + dob + "\n";
		return f;
	}
	
	/** Making a string accumulator containing all the students's details and their respective modules and results
	 * 
	 * @return A string representing the student details followed by the modules name and respective grade on each line
	 */
	public String studentModuleAccumulator() {
		String f = "Name: " + person.toString() + " StudentID: " + studentID + " Date of birth: " + dob + "\n";
		for(int i = 0; i < modules.size(); i++) {
			f += modules.get(i).toString();
		}
		return f;
	}
	
	/**
	 *  This sets the modules so when they are added to the studentModuleAccumulator they will print based upon their name
	 *  alphabetically 
	 */
	public void sortByModuleName(){
		Collections.sort(modules, new ModuleComparator());
	}
	
	/**
	 *  This sets the modules so when they are added to the studentModuleAccumulator they will print based upon their grade
	 *  sorted lowest to highest 
	 */
	public void sortByGradeModule() {
		Collections.sort(modules, new GradeModuleComparator());
	}
}
