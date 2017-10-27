/*
 * Assignmment 2 - CPSC 501
 * Samuel Niu
 * 10047006
 * 
 * Unit test to test constructor printing
 */

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

public class unitTestConstructor {

	//Test with recursion true
	@Test
	public void unitTestConstructorTrue() {
		
		
		//Initialize driver and constructorTest
		Asst2TestDriver driver;
		constructorExample cons = new constructorExample(1);
		
		try {
			driver = new Asst2TestDriver("ObjectInspector", true);
			driver.runTest(cons);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Run assertEquals passing in 1 to consturctorExample
		assertEquals(ObjectInspector.constructorGetter(),
				"*****Start constructors, for object: constructorExample ***** \n" + 
				"==Start individual constructor==\n" + 
				"Constructor modifiers are: public\n" + 
				"Constructor parameters are: \n" + 
				"float \n" + 
				"==End individual constructor==\n" + 
				"*****Start constructors, for object: java.lang.Object ***** \n" + 
				"==Start individual constructor==\n" + 
				"Constructor modifiers are: public\n" + 
				"Constructor parameters are: \n" + 
				"==End individual constructor==\n" + 
				"*****End Constructor, for object: java.lang.Object ***** \n" + 
				"\n" + 
				"*****End Constructor, for object: constructorExample ***** \n\n");
	}
	
	//Test with recursion false
	@Test
	public void unitTestConstructorFalse() {
		
		
		//Initialize driver and constructorTest
		Asst2TestDriver driver;
		constructorExample cons = new constructorExample(1);
		
		try {
			driver = new Asst2TestDriver("ObjectInspector", false);
			driver.runTest(cons);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		//Run assertEquals passing in 1 to consturctorExample
		assertEquals(ObjectInspector.constructorGetter(),
				"*****Start constructors, for object: constructorExample ***** \n" + 
				"==Start individual constructor==\n" + 
				"Constructor modifiers are: public\n" + 
				"Constructor parameters are: \n" + 
				"float \n" + 
				"==End individual constructor==\n" + 
				"*****Start constructors, for object: java.lang.Object ***** \n" + 
				"==Start individual constructor==\n" + 
				"Constructor modifiers are: public\n" + 
				"Constructor parameters are: \n" + 
				"==End individual constructor==\n" + 
				"*****End Constructor, for object: java.lang.Object ***** \n" + 
				"\n" + 
				"*****End Constructor, for object: constructorExample ***** \n\n");
	}
}
