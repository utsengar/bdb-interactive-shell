package bdb.eventing;

import java.lang.reflect.*;
import java.io.*;

public class ReflectionAPI {
   public static void main(String s[]) {
	   ReflectionAPI t = new ReflectionAPI();
      try {
         t.doit();
         }
      catch (Exception e) {
         e.printStackTrace();
         }
      }
   public void doit() throws Exception {
      String aClass;
      String aMethod;   
      // we assume that called methods have no argument
      Class params[] = {};
      Object paramsObj[] = {};
      
      while (true) {
         /* examples 
            Class: class1         Method: class1Method2
            Class: java.util.Date Method: toString
            Class: java.util.Date Method: getTime
         */
         aClass  = Input.Line("\nClass : ");
         aMethod = Input.Line("Method: ");
         // get the Class
         Class thisClass = Class.forName(aClass);
         // get an instance 
         Object iClass = thisClass.newInstance();
         // get the method
         Method thisMethod = thisClass.getDeclaredMethod(aMethod, params);
         // call the method
         System.out.println(thisMethod.invoke(iClass, paramsObj).toString());
         }
      }
   
   static class Input {
     public static String Line(String s) throws IOException {
         BufferedReader input = 
             new BufferedReader(new InputStreamReader(System.in));
         System.out.print(s);
         return input.readLine();     
        }
     }
}

class class1 {
   public String class1method1() {
      return "*** Class 1, Method1 ***";
      }
   public String class1method2() {
      return "### Class 1, Method2 ###";
      }
}
