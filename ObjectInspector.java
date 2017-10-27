/*
 * Samuel Niu
 * CPSC 501
 * Assignemtn #2 - Reflection
 * The header below was included in the files given by the professor 
 * Part of inspect, inspectField and inspectFieldClass.
 */


/***********************************************************************====
File: ObjectInspector.java
Purpose:Demo Object inspector for the Asst2TestDriver
Location: University of Calgary, Alberta, Canada
Created By: Jordan Kidney
Created on:  Oct 23, 2005
Last Updated: Oct 23, 2005
***********************************************************************
If you are going to reproduce this code in any way for your asignment 
rember to include my name at the top of the file toindicate where you
got the original code from
***********************************************************************
**********************************************************************==*/

import java.util.*;
import java.lang.reflect.*;


public class ObjectInspector
{
    public ObjectInspector() { }

    //Create hashmap to determine when an object has already been analyzed
    HashMap<Object, Boolean> finInspection = new HashMap<Object, Boolean>();
    
    //Create static string to use for constructor unit test
    static String constructorTester = "";
    
    // Main inspect method that inspects the initial object 
    public void inspect(Object obj, boolean recursive) {
    	
    	//If the object is null, immediately return without doing anything
    	if(obj == null) {
			return;
		}
    	
    	//Initialize the vector containing objects to inspect and the object class
		Vector objectsToInspect = new Vector();
		Class ObjClass = obj.getClass();
	
		finInspection.put(objectsToInspect, true);
		
		System.out.println("inside inspector: " + obj + " (recursive = "+recursive+")");
	
		//Determine if there is a superclass
		if(ObjClass.getSuperclass() != null) {
			System.out.println("Superclass = " + ObjClass.getSuperclass());
		} else {
			System.out.println("No superclass");
		}
		
		
		//inspect the current class by inspecting constructors, interfaces, methods and fields
		inspectConstructor(obj, ObjClass, objectsToInspect);
		inspectInterfaces(obj, ObjClass, objectsToInspect);
		inspectMethods(obj, ObjClass, objectsToInspect);
		inspectFields(obj, ObjClass,objectsToInspect);
	
		
		//Only call inspectFieldClasses if the recursive flag is set to true
		if(recursive) inspectFieldClasses( obj, ObjClass, objectsToInspect, recursive);
		
		
    }
    
    
    // This method inspects the constructor. 3 arguments are passed in, an Object, Class and Vector

    public void inspectConstructor (Object obj, Class ObjClass, Vector objectsToInspect) {
    	System.out.println("*****Start constructors, for object: " + ObjClass.getName() +" ***** ");
    	constructorTester += "*****Start constructors, for object: " + ObjClass.getName() + " ***** \n";
    	
    	//Constructor array is initalized with the constructors of the object
    	Constructor[] cons = ObjClass.getDeclaredConstructors();
    	
    	//Runs this if constructors are present
    	if(ObjClass.getConstructors().length != 0) {
    		for (int i=0; i < cons.length; i++) {
    	    	System.out.println("==Start individual constructor==");
    	    	constructorTester += "==Start individual constructor==\n";
    	    	
    	    	//Gets the constructor information and stores them in variables
    			Constructor info = cons[i];
    			Class[] paramType = info.getParameterTypes();
    			String mod = Modifier.toString(info.getModifiers());

    			//Prints the consturctor modifiers and parameters
    			System.out.println("Constructor modifiers are: " + mod);
    	    	constructorTester += "Constructor modifiers are: " + mod + "\n";

    			System.out.println("Constructor parameters are: ");
    	    	constructorTester += "Constructor parameters are: \n";


    			for (Class parameter:paramType) {
    				System.out.println(parameter.getName() + " ");
    				constructorTester += parameter.getName() + " \n";
    			}
    			
    	    	System.out.println("==End individual constructor==");
    	    	constructorTester += "==End individual constructor==\n";
    		}
    	} else {
    		System.out.println("No constructor");
	    	constructorTester += "No constructor\n";

    	}
    	
		if(ObjClass.getSuperclass() != null) {
		    inspectConstructor(obj, ObjClass.getSuperclass() , objectsToInspect);
		}
    	
    	System.out.println("*****End Constructor, for object: "+ObjClass.getName() + " ***** \n");
    	constructorTester += "*****End Constructor, for object: " + ObjClass.getName() + " ***** \n\n";

    }
    
    // This method inspects the interfaces. 3 arguments are passed in, an Object, Class and Vector
    
    public void inspectInterfaces (Object obj, Class ObjClass, Vector objectsToInspect) {
    	System.out.println("*****Start Interfaces, for object: " +ObjClass.getName() + " ***** ");
    	
    	Class[] interfaces = ObjClass.getInterfaces();
    	
    	if(ObjClass.getInterfaces() != null) {
    		for (int i=0; i < interfaces.length; i++) {
    			// Prints the interface properties
    			Class interfaceProp = interfaces[i];
    			System.out.println("Interfaces are: " + interfaceProp.getName());
    		}
    	} else {
    		System.out.println("No Interface");
    	}
    	
		if(ObjClass.getSuperclass() != null) {
		    inspectInterfaces(obj, ObjClass.getSuperclass() , objectsToInspect);
		}
    	
    	System.out.println("*****End Interface, for object: "+ ObjClass.getName() +" ***** \n");
    }
    
    // This method inspects the methods. 3 arguments are passed in, an Object, Class and Vector
    
    public void inspectMethods(Object obj, Class ObjClass, Vector objectsToInspect) {
    	System.out.println("*****Start methods, for object: "+ ObjClass.getName() +" ***** ");
    	Method[] method = ObjClass.getDeclaredMethods();
    	
    	if (ObjClass.getDeclaredMethods().length != 0) {
    		for(int i = 0; i < method.length; i++) {
    	    	System.out.println("==Start individual method==");
    	    	// Get the method prorperties and store them in variables
    			Method methodProp = method[i];
    			Class returnType = methodProp.getReturnType();
    			Class[] excep = methodProp.getExceptionTypes();
    			Class[] paramType = methodProp.getParameterTypes();
    			String mod = Modifier.toString(methodProp.getModifiers());
    			
    			// Print out the method modifiers and parameters
    			System.out.println("Method modifiers are: " + mod);
    			System.out.println("Method parameters are: ");
    			
    			for (Class parameter:paramType) {
    				System.out.println(parameter.getName() + " ");
    			}
    			
    			// Print out the method exceptions
    			System.out.println("Method exceptions are: ");
    			
    			for (Class exception:excep) {
    				System.out.println(exception.getName() + " ");
    			}
    			
    			// Print out the method return type
    			System.out.println("Method return type is: " + returnType);
    			
    	    	System.out.println("==End individual method== \n");

    			
    		}
    	} else {
    		System.out.println("No methods");
    	}
    	
		if(ObjClass.getSuperclass() != null) {
		    inspectMethods(obj, ObjClass.getSuperclass() , objectsToInspect);
		}
    	
    	System.out.println("*****End methods, for object: " + ObjClass.getName() + " ***** \n");
    }
    
    
    // This method inspects the Field Classes. 4 arguments are passed in, an Object, Class and Vector and the recursive flag

    private void inspectFieldClasses(Object obj,Class ObjClass,
				     Vector objectsToInspect,boolean recursive){
	
    	if(objectsToInspect.size() > 0 ) {
	    	System.out.println("*****Start Field Classes, for object: " + ObjClass.getName() + " ***** ");
		}
    	Enumeration e = objectsToInspect.elements();
    	
    	while(e.hasMoreElements()) {
			Field f = (Field) e.nextElement();
			System.out.println("Inspecting Field: " + f.getName());
			
			Object fieldObj = null;
			
			try {
				fieldObj = f.get(obj);
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	
			// If fieldObj is null then return as there is nothing to inspect
			if(fieldObj == null) {
				return;
			}
			
			Class<?> Types = fieldObj.getClass().getComponentType();
			
			try {
				if (!finInspection.containsKey(f.get(obj))) {
						
					if(fieldObj.getClass().isArray()) {
							
						if(Types.isPrimitive()) {
							
							for(int i = 0; i < Array.getLength(fieldObj); i++) {
								System.out.println("Array Index: " + i);
								System.out.println("Array Value: " + Array.get(fieldObj,i));
							}
							
						} else {
							// Array is not a primitive type, recurse object
							
							for (int i = 0; i < Array.getLength(fieldObj); i++) {
								System.out.println("Array index: " + i);
								System.out.println("inner contents: ");
								Object innerElement = Array.get(fieldObj,  i);
								inspect(innerElement, true);
							}
							
						}
							
					}
						
					System.out.println("******************");
					inspect(fieldObj, recursive);
					System.out.println("******************");
				}  else {
			    	System.out.println("Already Inspected " + fieldObj);
				}
			} catch(Exception exp) {
				exp.printStackTrace(); 
			}
		}
		if(ObjClass.getSuperclass() != null) {
		    inspectFieldClasses(obj, ObjClass.getSuperclass() , objectsToInspect, recursive);
		}
	}

    // This method inspects the Fields. 3 arguments are passed in, an Object, Class and Vector
    private void inspectFields(Object obj,Class ObjClass,Vector objectsToInspect){
    	System.out.println("*****Start Fields, for object: "+ ObjClass.getName() +" ***** ");
    	Field[] fields = ObjClass.getDeclaredFields();
    	
		if(ObjClass.getDeclaredFields().length >= 1){
			
			for(int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				
				field.setAccessible(true);
				
				if(!field.getType().isPrimitive()) {
				    objectsToInspect.addElement(field);		
				}

    	    	try {
    	    		System.out.println("==Start individual Field==");
					System.out.println("Field: " + field.getName() + " == " + field.get(obj));
					System.out.println("Field Type: " + field.getType());
	    	    	System.out.println("Field Modifiers: " + Modifier.toString(field.getModifiers()));
				
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	    }
							
			if(ObjClass.getSuperclass() != null) {
		    inspectFields(obj, ObjClass.getSuperclass() , objectsToInspect);
			}
			System.out.println("*****End Fields***** \n");
		}
    }
    
    //Getter for the constructor unit test
    public static String constructorGetter() {
		String toSendString = constructorTester;
    	constructorTester = "";
    	
    	return toSendString;
  	
    }
}