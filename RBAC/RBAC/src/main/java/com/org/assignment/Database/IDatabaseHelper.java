package com.org.assignment.Database;

import java.util.Map;
import com.org.assignment.Exception.RBACException;

/**
 * Interface for different Database.
 * @author rakesh
 *
 */
public interface IDatabaseHelper {

	public Object executeSelect(String query) throws RBACException;
	public Object executeSelectWithOptions(String query, Map<String, Object> options) throws RBACException;
	public void executeInsert(String query, Map<String, Object> options) throws RBACException;
	public boolean executeDelete(String query, Map<String, Object> options) throws RBACException;
}
