package JunitTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Model.ModuleComparator;
import Model.Module;

public class moduleComparatorTest {

	@Test
	public void test() {
		// Given
		Module module1 = new Module("Java", 80);
		Module module2 = new Module("Maths", 90);
		ModuleComparator comparison = new ModuleComparator();
		int compareModule1ToModule1 = comparison.compare(module1, module1);
		
		int compareModule2toModule1 = comparison.compare(module2, module1);
		
		int compareModule1toModule2 = comparison.compare(module1, module2);
		
		// Then
		assertEquals(0, compareModule1ToModule1);
		
		// Testing that Java does come before Maths where it returns a negative so java is compared to Maths
		assertTrue(compareModule1toModule2 < 0, "Java should come before Maths so the number should be smaller than 0.");
		
		// Testing that Java does come before Maths where it returns a positive so Maths is compared to java
		assertTrue(compareModule2toModule1 > 0, "Java should come before Maths so the number should be greater than 0.");
	}

}
