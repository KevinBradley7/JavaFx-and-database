package HeapDump;

import java.util.ArrayList;
import java.util.List;

import Model.Person;

public class HeapCode {
	
	public HeapCode() {}
	
	/**
	 *  Creates person objects 
	 */
	public void OutofMemoryPerson() {
		List<Person> persons = new ArrayList<Person>();
		while(1 < 2) {
			Person person = new Person();
			persons.add(person);
		}
	}
}
