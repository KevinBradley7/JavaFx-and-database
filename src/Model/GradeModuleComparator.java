package Model;

import java.util.Comparator;

/**
 * 
 * @author KevinBradley
 * @version 1.0
 */
public class GradeModuleComparator implements Comparator<Module> {
	
	/**
	 *  This compare Method takes in two modules o1 and o2 and orders them based on the grade in the module. 
	 *  @return An int representing if the module grades are equal, greater than or less than each other
	 */
	 public int compare (Module o1, Module o2) {
		  Integer id1 = o1.getGrade();
		  Integer id2 = o2.getGrade();
		  return id1.compareTo(id2); 
	  }
}
