package Model;

/**
 * 
 * @author KevinBradley
 * @version 1.0
 */

public class Module{

	private String module;
	private int grade;
	
	/** 
	 * Creates a module with an unspecified module name and grade
	 */
	public Module() {}
	
	/** Creates a module with the specified module name and grade. 
	 * 
	 * @param module The name of the module
	 * @param grade  The grade for that module
	 */
	public Module(String module, int grade) {
		this.module = module;
		this.grade = grade;
	}
	
	/** Gets the module name
	 * 
	 * @return A String representing the name of the module
	 */
	public String getModule() {
		return module;
	}
	
	/** Sets the name of the module
	 * 
	 * @param module A string containing the name of the module
	 */
	public void setModule(String module) {
		this.module = module;
	}
	
	/** Gets the grade for the module
	 * 
	 * @return An int representing the grade of the module
	 */
	public int getGrade() {
		return grade;
	}
	
	/**
	 * 
	 * @param grade An int containing the grade of the module
	 */
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	/**
	 *  Overridden toString method prints in the format of "Module: module Grade: grade" where module is the name of the Module 
	 */
	public String toString() {
		String f = "Module: " + module + " Grade: " + grade + "\n";
		return f;
	}
	
}
