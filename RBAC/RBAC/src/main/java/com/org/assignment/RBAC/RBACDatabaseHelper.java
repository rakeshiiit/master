package com.org.assignment.RBAC;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.org.assignment.Database.DatabaseFactory;
import com.org.assignment.Database.IDatabaseHelper;
import com.org.assignment.Entities.ActionType;
import com.org.assignment.Entities.Assignment;
import com.org.assignment.Entities.Permissions;
import com.org.assignment.Entities.Resource;
import com.org.assignment.Entities.Role;
import com.org.assignment.Entities.User;
import com.org.assignment.Exception.RBACException;
import com.org.assignment.Logger.Logger;
import com.org.assignment.Utils.Constants;

class RBACDatabaseHelper {

	private static IDatabaseHelper databaseHelper;
	private static RBACDatabaseHelper instance = null;
	
	private RBACDatabaseHelper() {
		initializeDB();
	}
	
	public static synchronized RBACDatabaseHelper getInstance() {
		if(instance == null) {
			instance = new RBACDatabaseHelper();
		}
		
		return instance;
	}
	
	public static void initializeDB() {
		databaseHelper = DatabaseFactory.getInstance("InMemory");
	}
	
	public User findUser(String userEmail) throws RBACException {
		List<User> users = (List<User>) databaseHelper.executeSelect(Constants.USER);
		
		for(User user : users) {
			if(user.getEmailID().equals(userEmail)) 
				return user;
		}
		
		return null;
		
	}
	
	public ActionType findAction(String actionID) throws RBACException {
		List<ActionType> actions = (List<ActionType>) databaseHelper.executeSelect(Constants.ACTIONTYPE);
		
		for(ActionType action : actions) {
			if(action.getID().equals(actionID)) 
				return action;
		}
		
		return null;	
	}
	
	public Resource findResource(String resourceId) throws RBACException {
		List<Resource> resources = (List<Resource>) databaseHelper.executeSelect(Constants.RESOURCE);
		
		for(Resource resource : resources) {
			if(resource.getResourceId().equals(resourceId)) 
				return resource;
		}
		
		return null;
		
	}
	
	public Role findRole(String roleID) throws RBACException {
		List<Role> roles = (List<Role>) databaseHelper.executeSelect(Constants.ROLE);
		
		for(Role role : roles) {
			if(role.getID().equals(roleID)) 
				return role;
		}
		
		return null;
	}
	
	public boolean addAssignment(Map<String, Object> options) {
		try {
			databaseHelper.executeInsert(Constants.ASSIGNMENT, options);
			
		} catch (Exception e) {
			Logger.logMessage("Issue in adding roles");
			return false;
		}
 		
		return true;
		
	}
	
	public boolean findUserWithRole(Map<String, Object> options) throws RBACException {
		List<Assignment> assignments = (List<Assignment>) databaseHelper.executeSelectWithOptions(Constants.ASSIGNMENT, options);
		if(assignments.size() >= 1) {
			return true;
		}
		
		return false;
	}
	
	public boolean removeAssignment(Map<String, Object> options) {
		try {
			return databaseHelper.executeDelete(Constants.ASSIGNMENT, options);
			
		} catch (Exception e) {
			Logger.logMessage("Issue in adding roles");
			return false;
		}
	}
	
	public List<String> findAllRolesForUser(String userMail) {
		List<String> rolesForUser = null;
		try {
			rolesForUser = new ArrayList<String>();
			List<Assignment> assignments = (List<Assignment>) databaseHelper.executeSelect(Constants.ASSIGNMENT);
			
			
			for(Assignment assignment : assignments) {
				if(userMail.equals(assignment.getUserEmail())) {
					rolesForUser.add(assignment.getRoleID());
				}
			}
			
		} catch (Exception e) {
			Logger.logMessage("Issue in adding roles");
		}
 		
		return rolesForUser;
	}
	
	public boolean findPermission(Map<String, Object> options) {
		try {
			
			String userMail = options.get(Constants.USER_EMAIL).toString();
			String actionID = options.get(Constants.ACTION_TYPE).toString();
			String resourceID = options.get(Constants.RESOURCE_ID).toString();
			
			List<String> roleIDs = findAllRolesForUser(userMail);
			
			for(String role : roleIDs) {
				options.put(Constants.ROLE_ID, role);
				List<Permissions> permissions = (List<Permissions>) databaseHelper.executeSelectWithOptions(Constants.PERMISSIONS, options);
				for(Permissions p : permissions) {
					if(actionID.equals(p.getActionType()) && resourceID.equals(p.getResourceID())) {
						return true;
					}
				}
			}
			
		} catch(Exception e) {
			Logger.logMessage("Issue in findPermissions");
		}
		
		return false;
	}

}
