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
	private Helpers helpers = new Helpers();

	public boolean createConnection(String folderPath) {
		// boolean readOnly = true;
		File envHome = new File(folderPath);
		EnvironmentConfig myEnvConfig = new EnvironmentConfig();
		DatabaseConfig myDbConfig = new DatabaseConfig();

		// myEnvConfig.setReadOnly(!readOnly);
		// myDbConfig.setReadOnly(!readOnly);
		// myEnvConfig.setAllowCreate(!readOnly);
		// myDbConfig.setAllowCreate(!readOnly);
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

		DatabaseEntry entryKey = new DatabaseEntry(helpers.getBytes(key));
		DatabaseEntry returnKey = new DatabaseEntry();

		try {

			getVendorDB().get(null, entryKey, returnKey, null);

		} catch (DatabaseException e) {

			e.printStackTrace();
		}

		String stringValue = new String(returnKey.getData());
		return stringValue;
	}

	public void put(String key, String value) {
		DatabaseEntry entryKey = new DatabaseEntry(helpers.getBytes(key));
		DatabaseEntry entryValue = new DatabaseEntry(helpers.getBytes(value));

		try {
			getVendorDB().put(null, entryKey, entryValue);
		} catch (DatabaseException e) {

		}
	}

	public void delete(String key) {
		DatabaseEntry entryKey = new DatabaseEntry(helpers.getBytes(key));

		try {
			OperationStatus status = getVendorDB().delete(null, entryKey);
			if (status == OperationStatus.SUCCESS) {
			} else {

			}
		} catch (DatabaseException e) {

		}
	}

	public boolean contains(String key) {
		DatabaseEntry entryKey = new DatabaseEntry(helpers.getBytes(key));

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
