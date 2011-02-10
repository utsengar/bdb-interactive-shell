package bdb.eventing;

import bdb.connection.DatabaseMethods;
import javaEventing.EventManager;

public class ShellEvent extends EventManager.EventObject {
	
	public boolean CommandDispatcher(String command)
	{
		DatabaseMethods dbm = new DatabaseMethods();
		String key = null;
		String value = null;
		
		if(command.toLowerCase().startsWith("put"))
		{
			key = command.split(" ")[1];
			value = command.split(" ")[2];
			dbm.put(key, value);
			return false;
		}
		if(command.toLowerCase().startsWith("get"))
		{
			key = command.split(" ")[1];
			System.out.println(dbm.get(key));
			return false;
		}
		if(command.toLowerCase().startsWith("delete"))
		{
			key = command.split(" ")[1];
			dbm.delete(key);
			return false;
		}
		
		if(command.toLowerCase().startsWith("exit"))
		{
			return true;
		}
		if(command.toLowerCase().startsWith("help"))
		{
            System.out.println("Commands:");
            System.out.println("put key value -- Store key and its value");
            System.out.println("get key -- Get a value on the basis of its key");
            System.out.println("delete key -- Remove remove value and the key");
            System.out.println("help -- Print this message.");
            System.out.println("exit -- Exit from this shell.");
			return true;
		}
		else
		{
			//wrong command passed
			return false;
		}
	}
}
