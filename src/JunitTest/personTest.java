package JunitTest;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Model.Person;

public class personTest {

	@Test
	public void testGetFirstName() {
		//Given
		Person person1 = new Person("Kevin", "T", "Bradley");
		Person person2 = new Person("Kev", "D", "Hunt");
		//Then
		assertEquals("Kevin", person1.getFirstName());
		assertEquals("Kev", person2.getFirstName());
	}
	@Test
	public void testgetInitial() {
		// Given
		Person person1 = new Person("Kevin", "T", "Bradley");
		Person person2 = new Person("Kev", "D", "Hunt");
		// Then
		assertEquals("T", person1.getMiddleInitial());
		assertEquals("D", person2.getMiddleInitial());
	}
	
	@Test
	public void testgetLastName() {
		// Given
		Person person1 = new Person("Kevin", "T", "Bradley");
		Person person2 = new Person("Kev", "D", "Hunt");
		// Then
		assertEquals("Bradley", person1.getLastName());
		assertEquals("Hunt", person2.getLastName());
	}
	
	@Test
	public void testtoString() {
		// Given
		Person person1 = new Person("Kevin", "T", "Bradley");
		Person person2 = new Person("Kev", "D", "Hunt");
		// Then
		assertEquals("Kevin.T.Bradley", person1.toString());
		assertEquals("Kev.D.Hunt", person2.toString());
	}
	
	@Test
	public void testsetFirstName() {
		// Given
		Person person1 = new Person();
		String firstName = "Thomas";
		person1.setFirstName(firstName);
		
		Person person2 = new Person();
		String firstName2 = "Michael";
		person2.setFirstName(firstName2);
		
		//Then
		assertEquals(person1.getFirstName(), firstName);
		assertEquals(person2.getFirstName(), firstName2);
	}
	
	@Test
	public void testsetInitial() {
		// Given
		Person person1 = new Person();
		String middleInitial = "K";
		person1.setMiddleInitial(middleInitial);
		
		Person person2 = new Person();
		String middleInitial2 = "B";
		person2.setMiddleInitial(middleInitial2);
		
		// Then
		assertEquals(person1.getMiddleInitial(), middleInitial);
		assertEquals(person2.getMiddleInitial(), middleInitial2);
	}
	
	@Test
	public void testsetLastName() {
		// Given
		Person person1 = new Person();
		String lastName = "Bradley";
		person1.setLastName(lastName);
		
		Person person2 = new Person();
		String lastName2 = "Jordan";
		person2.setLastName(lastName2);
		
		// Then
		assertEquals(person1.getLastName(), lastName);
		assertEquals(person2.getLastName(), lastName2);
	}

}

