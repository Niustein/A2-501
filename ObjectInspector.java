package A2;
/*==========================================================================
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


========================================================================*/

import java.util.*;
import java.lang.reflect.*;


public class ObjectInspector
{
    public ObjectInspector() { }

    //-----------------------------------------------------------
    public void inspect(Object obj, boolean recursive)
    {
	Vector objectsToInspect = new Vector();
	Class ObjClass = obj.getClass();

	System.out.println("inside inspector: " + obj + " (recursive = "+recursive+")");

	
	if(ObjClass.getSuperclass() != null) {
		System.out.println("Superclass = " + ObjClass.getSuperclass());
	} else {
		System.out.println("No superclass");
	}
	
	
	//inspect the current class
	inspectFields(obj, ObjClass,objectsToInspect);
	inspectConstructor(obj, ObjClass, objectsToInspect);
	inspectMethods(obj, ObjClass, objectsToInspect);
	inspectInterfaces(obj, ObjClass, objectsToInspect);
	
	if(recursive)
	    inspectFieldClasses( obj, ObjClass, objectsToInspect, recursive);
    }
    
    //-----------------------------------------------------------

    public void inspectConstructor (Object obj, Class ObjClass, Vector objectsToInspect) {
    	System.out.println("Start constructor");
    	
    	Constructor[] cons = ObjClass.getDeclaredConstructors();
    	
    	if(ObjClass.getConstructors().length != 0) {
    		for (int i=0; i < cons.length; i++) {
    			Constructor info = cons[i];
    			Class[] paramType = info.getParameterTypes();
    			String mod = Modifier.toString(info.getModifiers());
    			System.out.println("Constructor modifiers are: " + mod);
    			System.out.println("Constructor parameters are: ");
    			
    			for (Class parameter:paramType) {
    				System.out.println(parameter.getName() + " ");
    			}
    			
    		}
    	} else {
    		System.out.println("No constructor");
    	}
    	
    	System.out.println("End Constructor");
    }
    
    //-----------------------------------------------------------
    
    public void inspectInterfaces (Object obj, Class ObjClass, Vector objectsToInspect) {
    	System.out.println("Start Interface");
    	
    	Class[] interfaces = ObjClass.getInterfaces();
    	
    	if(ObjClass.getInterfaces() != null) {
    		for (int i=0; i < interfaces.length; i++) {
    			Class interfaceProp = interfaces[i];
    			System.out.println("Interfaces are: " + interfaceProp.getName());
    		}
    	} else {
    		System.out.println("No Interface");
    	}
    	
    	System.out.println("End Interface");
    }
    
    //-----------------------------------------------------------
    
    public void inspectMethods(Object obj, Class ObjClass, Vector objectsToInspect) {
    	System.out.println("Start method");
    	Method[] method = ObjClass.getDeclaredMethods();
    	
    	if (ObjClass.getDeclaredMethods().length != 0) {
    		for(int i = 0; i < method.length; i++) {
    			Method methodProp = method[i];
    			Class returnType = methodProp.getReturnType();
    			Class[] excep = methodProp.getExceptionTypes();
    			Class[] paramType = methodProp.getParameterTypes();
    			String mod = Modifier.toString(methodProp.getModifiers());
    			
    			System.out.println("Method modifiers are: " + mod);
    			System.out.println("Method parameters are: ");
    			
    			for (Class parameter:paramType) {
    				System.out.println(parameter.getName() + " ");
    			}
    			
    			System.out.println("Method exceptions are: ");
    			
    			for (Class exception:excep) {
    				System.out.println(exception.getName() + " ");
    			}
    			
    			System.out.println("Method return type is: " + returnType);
    			
    		}
    	} else {
    		System.out.println("No methods");
    	}
    	
    	System.out.println("End method");
    }
    
    
    //-----------------------------------------------------------

    private void inspectFieldClasses(Object obj,Class ObjClass,
				     Vector objectsToInspect,boolean recursive)
    {
	
	if(objectsToInspect.size() > 0 )
	    System.out.println("---- Inspecting Field Classes ----");
	
	Enumeration e = objectsToInspect.elements();
	while(e.hasMoreElements())
	    {
		Field f = (Field) e.nextElement();
		System.out.println("Inspecting Field: " + f.getName() );
		
		try
		    {
			System.out.println("******************");
			inspect( f.get(obj) , recursive);
			System.out.println("******************");
		    }
		catch(Exception exp) { exp.printStackTrace(); }
	    }
    }
    //-----------------------------------------------------------
    private void inspectFields(Object obj,Class ObjClass,Vector objectsToInspect)
  
    {
	
	if(ObjClass.getDeclaredFields().length >= 1)
	    {
		Field f = ObjClass.getDeclaredFields()[0];
		
		f.setAccessible(true);
		
		if(! f.getType().isPrimitive() ) 
		    objectsToInspect.addElement( f );
		
		try
		    {
			
			System.out.println("Field: " + f.getName() + " = " + f.get(obj));
		    }
		catch(Exception e) {}    
	    }

	if(ObjClass.getSuperclass() != null)
	    inspectFields(obj, ObjClass.getSuperclass() , objectsToInspect);
    }
}
