package Model;

import java.util.Comparator;

/**
 * 
 * @author KevinBradley
 * @version 1.0
 */

public class ModuleComparator implements Comparator<Module> {
	
	/**
	 *  This compare Method takes in two modules o1 and o2 and orders them based on the name of the module. 
	 *  @return An int representing if the module names are equal, greater than or less than each other
	 */
	@Override
	public int compare(Module o1, Module o2) {
		String md1 = o1.getModule();
		String md2 = o2.getModule();
		md1.toUpperCase();
		md2.toUpperCase();
		return md1.compareTo(md2);
	}

}
