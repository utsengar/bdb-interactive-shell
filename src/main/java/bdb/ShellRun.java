package bdb;

import java.util.Scanner;

//import javaEventing.EventManager;
import bdb.connection.DatabaseMethods;
import bdb.eventing.*;

public class ShellRun {
	static boolean exit = false;
	static boolean connected = false;

	public static void main(String[] args) {
		
		DatabaseMethods db = new DatabaseMethods();
		System.out.println("Please provide path to BDB: /Users/utkarsh/Desktop/bdb");
		//Connect to BDB
		while(!connected)
		{
			System.out.print("BDB: ");
			Scanner dbInit = new Scanner(System.in);
			connected = db.createConnection(dbInit.nextLine());
			
			if(!connected)
			{
				System.out.println("Nope, wrong path, try again.");
			}
			else
			{
				System.out.println("We are connected!");
			}
		}
		
		//Start the loop and keep listening
		while(!exit)
		{
			System.out.print("bdb>");
			Scanner command = new Scanner(System.in);
			//exit = EventManager.waitUntilTriggered(new ShellEvent(command.nextLine()), 5000);
			ShellEvent se =  new ShellEvent();
			exit = se.CommandDispatcher(command.nextLine());
		}
		
		db.closeConnection();
		System.out.print("BBye..");
	}
}