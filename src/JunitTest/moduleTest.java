package JunitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Model.Module;

public class moduleTest {
	
	@Test
	public void testgetModuleGrade() {
		// Given
		Module module1 = new Module("Maths", 80);
		Module module2 = new Module("Java", 78);	
		
		// Then
		assertEquals(80, module1.getGrade());
		assertEquals(78, module2.getGrade());
	}
	
	@Test
	public void testgetModuleName() {
		// Given
		Module module1 = new Module("Maths", 80);
		Module module2 = new Module("Java", 78);	
		
		// Then
		assertEquals("Maths", module1.getModule());
		assertEquals("Java", module2.getModule());
	}
	
	@Test
	public void testtoString() {
		// Given
		Module module1 = new Module("Maths", 80);
		Module module2 = new Module("Java", 78);	
		
		// Then
		assertEquals("Module: Maths Grade: 80\n", module1.toString());
		assertEquals("Module: Java Grade: 78\n", module2.toString());
	}
	
	@Test
	public void testsetModuleGrade() {
		// Given
		Module module1 = new Module();
		int moduleResult = 90;
		module1.setGrade(moduleResult);
		
		Module module2 = new Module();
		int moduleResult2 = 78;
		module2.setGrade(moduleResult2);
		
		// Then
		assertEquals(module1.getGrade(), moduleResult);
		assertEquals(module2.getGrade(), moduleResult2);
	}
	
	@Test
	public void testsetModuleName() {
		// Given
		Module module1 = new Module();
		String moduleName = "Maths";
		module1.setModule(moduleName);
		
		Module module2 = new Module();
		String moduleName2 = "Java";
		module2.setModule(moduleName2);	
		
		// Then
		assertEquals(module1.getModule(), moduleName);
		assertEquals(module2.getModule(), moduleName2);
	}

}
