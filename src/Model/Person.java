package Model;

/**
 * 
 * @author KevinBradley
 * @version 1.0
 */

public class Person {
	private String firstName;
	private String middleInitial;
	private String lastName;
	
	/**
	 * Creates a person with an unspecified firstName, middlInitial and lastName
	 */
	public Person(){}
	
	/** Creates a person with the specified name
	 * 
	 * @param firstName The person's first name
	 * @param middleInitial The person's middle name initial
	 * @param lastName The person's last name
	 */
	public Person(String firstName, String middleInitial, String lastName){
		this.firstName = firstName;
		this.middleInitial = middleInitial;
		this.lastName = lastName;
	}
	
	/** Gets the person's first name.
	 * 
	 * @return A string representing the person's first name
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/** Sets the person's first name
	 * 
	 * @param firstName A String containing the person's first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/** Gets the person's middle initial
	 * 
	 * @return A String representing the person's middle initial
	 */
	public String getMiddleInitial() {
		return middleInitial;
	}
	
	/** Sets the person's middle initial
	 * 
	 * @param middleInitial A String containing the person's middle initial
	 */
	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}
	
	/** Gets the person's last name
	 * 
	 * @return A string representing the person's last name
	 */
	public String getLastName() {
		return lastName;
	}
	
	/** Sets the person's last name
	 * 
	 * @param lastName A string containing the person's last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 *  Overridden toString method prints in the format of "firstName.middleInitial.lastName"
	 *  where the strings are split by a "."
	 */
	public String toString() {
		String f= firstName + "." + middleInitial + "." + lastName;
		return f;
	}
}
