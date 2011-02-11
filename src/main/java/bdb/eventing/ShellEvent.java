package bdb.eventing;

import bdb.connection.DatabaseMethods;
//import javaEventing.EventManager;

//public class ShellEvent extends EventManager.EventObject {
public class ShellEvent {

	public boolean CommandDispatcher(String command) {
		DatabaseMethods dbm = new DatabaseMethods();
		String key = null;
		String value = null;

		if (command.toLowerCase().startsWith("put")) {
			if(command.split(" ").length == 3)
			{
				key = command.split(" ")[1].trim();
				value = command.split(" ")[2].trim();
				dbm.put(key, value);
			}
			return false;
		}
		if (command.toLowerCase().startsWith("get")) {
			if(command.split(" ").length == 2)
			{
				key = command.split(" ")[1].trim();
				System.out.println(dbm.get(key));
			}

			return false;
		}
		if (command.toLowerCase().startsWith("delete")) {
			if(command.split(" ").length == 2)
			{
				key = command.split(" ")[1].trim();
				dbm.delete(key);
			}

			return false;
		}

		if (command.toLowerCase().startsWith("exit")) {
			return true;
		}
		if (command.toLowerCase().startsWith("help")) {
			System.out.println("Commands:");
			System.out.println("put key value -- Store key and its value");
			System.out.println("get key -- Get a value on the basis of its key");
			System.out.println("delete key -- Remove remove value and the key");
			System.out.println("help -- Print this message.");
			System.out.println("exit -- Exit from this shell.");
			return false;
		} else {
			System.out.println("Please type help to see the list of command available.");
			return false;
		}
	}
}
