package Controller;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import View.ExitWindow;
import View.RemoveWindow;
import Model.Student;
import Model.Person;
import Model.Module;

import java.time.LocalDate;

/**
 * 
 * @author KevinBradley
 * @version 1.0
 */
public class StudentController {
	
	private static final String URL = "jdbc:derby:D:\\College\\Year 2\\Semester 2\\Object Oriented Programming\\StudentRecords\\src\\Database\\studentDB;create=True";
	private static final String USERNAME = "";
	private static final String PASSWORD = "";

	private Connection connection = null; // manages connection

	private PreparedStatement addStudentStatement = null; 
	private PreparedStatement removeStudentStatement = null;
	private PreparedStatement selectAllStudents = null;
	private PreparedStatement selectkeyIDStudentStatement = null;
	private PreparedStatement updateStudentStatement = null;
	private PreparedStatement addModuleStatement = null;
	private PreparedStatement selectAllModulesStatement = null;
	private PreparedStatement selectModulesGreater70Statement = null;
	private Statement statement = null;
	private ResultSet resultSet= null;
	
	public StudentController() {
		// constructor
		try 
	      {
	         connection = 
	            DriverManager.getConnection( URL, USERNAME, PASSWORD );
	         
	         // check if the database contains a StudentTable
	         DatabaseMetaData databaseMetadata = connection.getMetaData();

	         resultSet = databaseMetadata.getTables(null, null, "STUDENTTABLE", null);

			 if (!resultSet.next()){ 
					statement = connection.createStatement();
					statement.execute("CREATE TABLE STUDENTTABLE ("
							+ "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,"
							+ "StudentID VARCHAR(20),"
							+ "Name VARCHAR(50),"
							+ "Date date"
							+ ")");
			 }
			 
			 resultSet = databaseMetadata.getTables(null, null, "MODULETABLE", null);
	         if(!resultSet.next()){
	        	 statement = connection.createStatement();
	        	 statement.execute("CREATE TABLE MODULETABLE ("
	        			+ "moduleID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,"
	        	 		+ "ModuleName VARCHAR(30),"
	        	 		+ "Grade INTEGER,"
	        	 		+ "id INTEGER,"
	        	 		+ "CONSTRAINT id_fk FOREIGN KEY (id) "
	        	 		+ "REFERENCES STUDENTTABLE(id) ON DELETE CASCADE ON UPDATE RESTRICT"
	        	 		+ ")");
	         }
			  		
	         /**
	          * Student statements for adding students, getting all students, updating a student and removing a student
	          */
	         
			 addStudentStatement = connection.prepareStatement( 
	            "INSERT INTO StudentTable " + 
	            "(StudentID, Name, Date) " + 
	            "VALUES (?, ?, ?)" );
	         
			 selectAllStudents = 
	                 connection.prepareStatement( "SELECT * FROM StudentTable" );
	         
			 removeStudentStatement = connection.prepareStatement(
	        		 "DELETE FROM StudentTable WHERE StudentID = ?");
			 
			 updateStudentStatement = connection.prepareStatement(
					 "UPDATE StudentTable " +
					 "SET StudentID = ?," +
					 "Name = ?," +
					 "Date = ?" +
					 "Where StudentID = ?");
			 
			 selectkeyIDStudentStatement = connection.prepareStatement(
					 "SELECT id FROM STUDENTTABLE " +
					 "WHERE StudentID = ?"
					 );

			 /**
			  *  Module statements for adding modules, selecting modules and select modules over 70.
			  */
			 addModuleStatement = connection.prepareStatement(
					 	"INSERT INTO ModuleTable" +
					 	"(ModuleName, Grade, id)" +
						"VALUES (?,?,?)"
					 	);
			 
			 selectAllModulesStatement = connection.prepareStatement(
					 "SELECT * FROM MODULETABLE " +
					  "WHERE id = ?"
					 );
			 
			 selectModulesGreater70Statement = connection.prepareStatement(
					 "SELECT * FROM MODULETABLE " +
					 "WHERE Grade>69 AND id = ?"
					 );
	      }
	      catch ( SQLException sqlException )
	      {
	         sqlException.printStackTrace();
	         System.exit( 1 );
	      }
	}
	
	/** This adds a student to the database
	 * 
	 * @param name A person object that contains the student's full name
	 * @param studentID A String containing the student's studentID
	 * @param dob A LocalDate containing the student's dob
	 * @return An int representing if the student was added successfully or not representing the number of rows updated
	 */
	public int addStudentToList(Person name, String studentID, LocalDate dob) {
	int result = 0;
    
	    // set parameters, then execute insertNewPerson
	    try 
	    {
	    	addStudentStatement.setString(1, studentID);
	    	addStudentStatement.setString(2,  name.toString()); 
	    	addStudentStatement.setDate(3, java.sql.Date.valueOf(dob));
	    	
	       // insert the new entry; returns # of rows updated
	       result = addStudentStatement.executeUpdate(); 
	       System.out.println("The data was added correctly: " + result);
	    } // end try
	    catch ( SQLException sqlException )
	    {
	       sqlException.printStackTrace();
	       close();
	    } // end catch
	    
	    return result;
	}
	
	public int updateStudentInList(String studentIDUpdating, Person name, String studentID, LocalDate dob) {
		int result = 0;
		
		try {
			updateStudentStatement.setString(1, studentID);
			updateStudentStatement.setString(2, name.toString());
			updateStudentStatement.setDate(3, java.sql.Date.valueOf(dob));
			updateStudentStatement.setString(4, studentIDUpdating);
			
			result = updateStudentStatement.executeUpdate();
			System.out.println("The data was updated correctly: " + result);
		}
		catch(SQLException sqlExpection){
			sqlExpection.printStackTrace();
			close();
		}
		return result;
	}
	
	/** This removes a student from the database based upon the student's studentID 
	 * 
	 * @param studentID A string containing the student's studentID
	 * @return An int representing if the student was removed. It returns a 1 or a 0. 
	 * It returns 1 if it was removed and a 0 if it wasn't
	 */
	public int removeStudent(String studentID) {
		int result = 0;
		try {
			removeStudentStatement.setString(1, studentID);
			result = removeStudentStatement.executeUpdate(); 
			System.out.println("The student with ID " + studentID + " was removed");
		}catch(SQLException sqlException)
	    {
		       sqlException.printStackTrace();
		       close();
		} // end catch
		return result;
	}
	
	/** This gets all the students in the database and makes their data back into students again
	 * 
	 * @return An arraylist of student objects 
	 */
	public ArrayList<Student> getAllStudents() 
	   {
	      ArrayList<Student> results = null; 
	      ResultSet resultSet = null;
	      
	      try 
	      {
	         // executeQuery returns ResultSet containing matching entries
	         resultSet = selectAllStudents.executeQuery(); 
	         results = new ArrayList<Student>();
	         String personDetails;
	         String[] split;
	         String fName;
	         String middleInitial;
	         String lName;
	         while(resultSet.next() )
	         {
	        	personDetails = resultSet.getString("Name");
	        	split = personDetails.split("\\.");
	        	fName = split[0];
	        	middleInitial = split[1];
	        	lName = split[2];
	        	Person person = new Person(fName, middleInitial, lName);
	            results.add( new Student(
	               person,
	               resultSet.getString("StudentID"),
	               resultSet.getDate("Date").toLocalDate())
	            		);
	            
	         } // end while
	         try {
		         if(results.isEmpty());
	         } catch(StringIndexOutOfBoundsException e) {
	             System.out.println("String index out of bounds. ");
	         }
	      } // end try
	      catch ( SQLException sqlException )
	      {
	         sqlException.printStackTrace();         
	      } // end catch
	      finally
	      {
	         try 
	         {
	            resultSet.close();
	         } // end try
	         catch ( SQLException sqlException )
	         {
	            sqlException.printStackTrace();         
	            close();
	         } // end catch
	      } // end finally
	      
	      return results;
	   }
	
	/** Gets all the student objects from getAllStudents and adds them to a string accumulator
	 * 
	 * @return A string of students and their data formatted
	 */
	public String getStudents() {
		List<Student> student = getAllStudents();
		String allStudents = "";
		for(int i = 0; i < student.size(); i++) {
			allStudents += student.get(i);
		}
		return allStudents;
	}
	
	/** Gets the student at index i
	 * 
	 * @param i This represents the index of the student wanted
	 * @return A student at that specific index
	 */
	public Student getStudent(int i) {
		ArrayList<Student> students = new ArrayList<>();
		students = getAllStudents();
		if(i >= 0 && i < students.size()) {
			return students.get(i);
		}
		return null;
	}
	
	/**
	 * Close the database connection
	 */
	public void close()
	{
		try 
	    {
			connection.close();
	    } // end try
		catch ( SQLException sqlException )
	    {
	         sqlException.printStackTrace();
	    } // end catch
	 } // end method close
		
	/** Adds a module for a particular student to the module DB
	 * 
	 * @param name A string representing the name of the module
	 * @param grade A string representing the grade of the module
	 * @param studentID A string representing the studentID of a student
	 * @return An int result if the add of the module was successful or unsuccessful
	 */
	public int addModule(String name, String grade, String studentID) {
		int result = 0;
		ResultSet resultSet = null;
	    try 
	    {
	    	selectkeyIDStudentStatement.setString(1, studentID);
	    	resultSet = selectkeyIDStudentStatement.executeQuery();
	    	resultSet.next();
	    	
	    	addModuleStatement.setString(1, name);
	    	addModuleStatement.setInt(2,  Integer.parseInt(grade));
	    	addModuleStatement.setInt(3, resultSet.getInt(1));
	    	
	       // insert the new entry; returns # of rows updated
	       result = addModuleStatement.executeUpdate(); 
	       System.out.println("The data was added correctly: " + result);
	    } // end try
	    catch ( SQLException sqlException )
	    {
	       sqlException.printStackTrace();
	       close();
	    } // end catch
	    
	    return result;
	}
	
	/**
	 * Access to the remove window pop up
	 */
	public void removeStudentPopUp() {
		removeStudent(RemoveWindow.display());
	}
	
	/**
	 * Access to the exit window pop up
	 */
	public void exitProgram() {
		ExitWindow.display();
	}
	
	/** Gets all the Modules for a particular student and adds them to the student
	 * 
	 * @param studentID A string representing the studentID of a particular student
	 * @return A student containing the modules being added to that student
	 */
	public Student getAllModules(String studentID)
	   {
	      ArrayList<Module> results = null; 
	      ResultSet resultSet = null;
	      ResultSet resultSet2 = null;
	      Student student = null;
	      
	      try 
	      {
		    selectkeyIDStudentStatement.setString(1, studentID);
		    resultSet2 = selectkeyIDStudentStatement.executeQuery();
		    resultSet2.next();
	        // executeQuery returns ResultSet containing matching entries
	    	selectAllModulesStatement.setInt(1, resultSet2.getInt(1));
	        resultSet = selectAllModulesStatement.executeQuery();  
	         
	        results = new ArrayList<Module>();
	        
	 		int size = getAllStudents().size();
			for(int i = 0; i < size; i ++){
				student = getStudent(i);
				if(studentID.equals(student.getStudentID())) {
					break;
				}
			}
	         
	         while(resultSet.next() )
	         {
	        	 student.addModule(resultSet.getString("ModuleName"), resultSet.getInt("Grade"));
	            
	         } // end while
	         try {
		         if(results.isEmpty());
	         } catch(StringIndexOutOfBoundsException e) {
	             System.out.println("String index out of bounds. ");
	         }
	      } // end try
	      catch ( SQLException sqlException )
	      {
	         sqlException.printStackTrace();         
	      } // end catch
	      finally
	      {
	         try 
	         {
	            resultSet.close();
	         } // end try
	         catch ( SQLException sqlException )
	         {
	            sqlException.printStackTrace();         
	            close();
	         } // end catch
	      } // end finally
	      
	      return student;
	   }
	
	/** Gets the Modules for a particular student where they got more than 70 % in
	 * 
	 * @param studentID A string representing the studentID for a particular student
	 * @return A string containing all of the modules where a student got greater than 70%
	 */
	public String getModulesgreater70(String studentID)
		   {
			      String results = null; 
			      ResultSet resultSet = null;
			      ResultSet resultSet2 = null;
			      Student student = null;
			      
			      try 
			      {
			        // executeQuery returns ResultSet containing matching entries
					selectkeyIDStudentStatement.setString(1, studentID);
					resultSet2 = selectkeyIDStudentStatement.executeQuery();
					resultSet2.next();
					selectModulesGreater70Statement.setInt(1, resultSet2.getInt(1));
			        resultSet = selectModulesGreater70Statement.executeQuery(); 
			       
			         
			 		int size = getAllStudents().size();
					for(int i = 0; i < size; i ++){
						student = getStudent(i);
						if(studentID.equals(student.getStudentID())) {
							break;
						}
					}
			         
			         while(resultSet.next() )
			         {
			        	 student.addModule(resultSet.getString("ModuleName"), resultSet.getInt("Grade"));
			            
			         } // end while
			         results = student.studentModuleAccumulator();
			         try {
				         if(results.isEmpty());
			         } catch(StringIndexOutOfBoundsException e) {
			             System.out.println("String index out of bounds. ");
			         }
			      } // end try
			      catch ( SQLException sqlException )
			      {
			         sqlException.printStackTrace();         
			      } // end catch
			      finally
			      {
			         try 
			         {
			            resultSet.close();
			         } // end try
			         catch ( SQLException sqlException )
			         {
			            sqlException.printStackTrace();         
			            close();
			         } // end catch
			      } // end finally
			      return results; 
	}
		
	/** Gets the modules for a particular student and adds them to a String
	 * 
	 * @param studentID A string containing the studentID for a particular student
	 * @return A string representing the details of a student and all their respective modules and grades
	 */
	public String getModules(String studentID) {
		String allModulesforStudent = "";
		Student student = getAllModules(studentID);
		allModulesforStudent = student.studentModuleAccumulator();
		return allModulesforStudent;
	}
	
	/** Sets the modules for a particular student to be ordered based upon the name of the module
	 * 
	 * @param studentID A string containing the studentID for a particular student
	 */
	public void sortByNameModule(String studentID) {
		Student student = getAllModules(studentID);
		student.sortByModuleName();
	}
	
	/** Sets the modules for a particular student to be ordered based upon the grade of the module
	 * 
	 * @param studentID A string containing the studentID for a particular student
	 */
	public void sortByGradeModule(String studentID) {
		Student student = getAllModules(studentID);
		student.sortByGradeModule();
	}
}
