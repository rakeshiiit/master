package com.org.assignment.Database;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.org.assignment.Entities.Assignment;
import com.org.assignment.Entities.Permissions;
import com.org.assignment.Exception.RBACException;
import com.org.assignment.GenerateData.SampleData;
import com.org.assignment.Utils.Constants;

/**
 * InMemory Database
 * @author rakesh
 *
 */
class InMemoryDB implements IDatabaseHelper {

	private static InMemoryDB conn;
	private SampleData SD;
	
	private InMemoryDB() {
		SD = new SampleData();
		SD.loadData();
	}
	
	public static synchronized IDatabaseHelper getDBInstance() {
		if(conn == null) {
			conn = new InMemoryDB();
		}
		
		return conn;
	}
	
	/**
	 * Executes Select query for the entities
	 * Simulated it for In memory data.
	 * @throws RBACException 
	 */
	public Object executeSelect(String query) throws RBACException {
		try {
			if(query.contentEquals(Constants.USER)) {
				return SD.getAllUsers();
			} else if(query.contentEquals(Constants.ROLE)) {
				return SD.getAllRoles();
			} else if(query.contentEquals(Constants.ACTIONTYPE)) {
				return SD.getAllActions();
			} else if(query.contentEquals(Constants.RESOURCE)) {
				return SD.getAllResource();
			} else if(query.contentEquals(Constants.ASSIGNMENT)) {
				return SD.getAllAssignments();
			}
		} catch(Exception e) {
			throw new RBACException(e.getLocalizedMessage());
		}
		
		return null;
	}

	/**
	 * Executes Insert for the entities
	 * Simulated it for In memory data.
	 * @throws RBACException 
	 */
	public void executeInsert(String query, Map<String, Object> options) throws RBACException {
		try {
			if(query.contentEquals(Constants.ASSIGNMENT)) {
				Assignment assignment = new Assignment();
				assignment.setRoleID((String) options.get(Constants.ROLE_ID));
				assignment.setUserEmail((String) options.get(Constants.USER_EMAIL));
				SD.addAssignment(assignment);
			}
		} catch(Exception e) {
			throw new RBACException(e.getLocalizedMessage());
		}
	}

	/**
	 * Executes Select with options having where clause for the entities
	 * Simulated it for In memory data.
	 * @throws RBACException 
	 */
	public Object executeSelectWithOptions(String query, Map<String, Object> options) throws RBACException {
		try {
			if(query.equals(Constants.ASSIGNMENT)) {
				
				List<Assignment> out = new ArrayList<Assignment>();
				String userMail = options.get(Constants.USER_EMAIL).toString();
				String roleID = options.get(Constants.ROLE_ID).toString();
				for(Assignment assignment : SD.getAllAssignments()) {
					if(userMail.equals(assignment.getUserEmail()) && roleID.equals(assignment.getRoleID())) {
						out.add(assignment);
					}
				}
				
				return out;
			} else if(query.equals(Constants.PERMISSIONS)) {
				
				List<Permissions> out = new ArrayList<Permissions>();
				String roleID = options.get(Constants.ROLE_ID).toString();
				for(Permissions permission : SD.getAllPermissions()) {
					if(roleID.equals(permission.getRoleID())) {
						out.add(permission);
					}
				}
				
				return out;
			}
		} catch(Exception e) {
			throw new RBACException(e.getLocalizedMessage());
		}
		
		return null;
	}

	/**
	 * Executes Delete for the entities
	 * Simulated it for In memory data.
	 * @throws RBACException 
	 */
	public boolean executeDelete(String query, Map<String, Object> options) throws RBACException {
		try {
			if(query.equals(Constants.ASSIGNMENT)) {
				String userMail = options.get(Constants.USER_EMAIL).toString();
				String roleID = options.get(Constants.ROLE_ID).toString();
				
				Iterator<Assignment> iterator = SD.getAllAssignments().iterator();
				while(iterator.hasNext()) {
					Assignment a = iterator.next();
					if(userMail.equals(a.getUserEmail()) && roleID.equals(a.getRoleID())) {
						iterator.remove();
						return true;
					}
				}
			}
		} catch(Exception e) {
			throw new RBACException(e.getLocalizedMessage());
		}
		
		return false;
	}

}
