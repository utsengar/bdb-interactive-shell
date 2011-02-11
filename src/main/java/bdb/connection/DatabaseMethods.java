package bdb.connection;

import java.io.File;
import com.sleepycat.je.Cursor;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.je.LockMode;
import com.sleepycat.je.OperationStatus;

public class DatabaseMethods {
	private Environment myEnv;
	private static Database vendorDb;

	public boolean createConnection(String folderPath) {
		File envHome = new File(folderPath);
		EnvironmentConfig myEnvConfig = new EnvironmentConfig();
		DatabaseConfig myDbConfig = new DatabaseConfig();
		
		try {
			Environment myEnv = new Environment(envHome, myEnvConfig);
			vendorDb = myEnv.openDatabase(null, "VendorDB", myDbConfig);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public void closeConnection() {
		if (myEnv != null) {
			try {
				vendorDb.close();
				myEnv.close();
			} catch (DatabaseException dbe) {
				System.exit(-1);
			}
		}
	}

	public String get(String key) {

		DatabaseEntry entryKey = new DatabaseEntry(key.getBytes());
		DatabaseEntry returnKey = new DatabaseEntry();
		String stringValue = null;

		try {
			
			getVendorDB().get(null, entryKey, returnKey, null);
			
			stringValue = new String(returnKey.getData());

		} catch (Exception e) {
			stringValue = "key: " + key + " is not present.";
		}
		
		return stringValue;
	}

	public void put(String key, String value) {
		DatabaseEntry entryKey = new DatabaseEntry(key.getBytes());
		DatabaseEntry entryValue = new DatabaseEntry(value.getBytes());

		try {
			getVendorDB().put(null, entryKey, entryValue);
		} catch (DatabaseException e) {
			
		}
	}

	public void delete(String key) {
		DatabaseEntry entryKey = new DatabaseEntry(key.getBytes());

		try {
			OperationStatus status = getVendorDB().delete(null, entryKey);
			if (status == OperationStatus.SUCCESS) {
			} else {
				System.out.println("Key deleted successfully!");
			}
		} catch (DatabaseException e) {
			
		}
	}

	public boolean contains(String key) {
		DatabaseEntry entryKey = new DatabaseEntry(key.getBytes());

		boolean keyFound = false;
		Cursor cursor = null;
		DatabaseEntry entryValue = new DatabaseEntry();

		try {
			cursor = getVendorDB().openCursor(null, null);
			OperationStatus status = cursor.getSearchKey(entryKey, entryValue,
					LockMode.DEFAULT);
			if (status == OperationStatus.SUCCESS) {
				keyFound = true;
			}
			cursor.close();
		} catch (DatabaseException e) {

		}
		return keyFound;
	}

	public static Database getVendorDB() {
		return vendorDb;
	}
}
