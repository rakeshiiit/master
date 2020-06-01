package com.org.assignment.Database;

import com.org.assignment.Utils.Constants;

/**
 * DataBase Factory to return instance of specific DB.
 * @author rakesh
 *
 */
public class DatabaseFactory {

	public static IDatabaseHelper getInstance(String type) {
		if(type.equals(Constants.INMEMORY)) {
			return InMemoryDB.getDBInstance();
		}
		
		return null;
	}
	

}
