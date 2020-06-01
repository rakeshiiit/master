package com.org.assignment.RBAC;

import java.util.Map;

import com.org.assignment.Entities.ActionType;
import com.org.assignment.Entities.Resource;
import com.org.assignment.Entities.Role;
import com.org.assignment.Entities.User;
import com.org.assignment.Logger.Logger;
import com.org.assignment.Utils.Constants;
import com.org.assignment.Utils.ValidationUtils;

public class RBACSystem implements IRBACSystem{
	
	/**
	 * Adds role to the user.
	 */
	public boolean addRoleToUser(Map<String, Object> options) {
		
		try {
			ValidationUtils.validateOptions(options, Constants.USER_EMAIL);
			ValidationUtils.validateOptions(options, Constants.ROLE_ID);
			
			User user = RBACDatabaseHelper.getInstance().findUser(options.get(Constants.USER_EMAIL).toString());
			
			if(user == null) {
				Logger.logMessage("User doesn't exists. Please register...");
				return false;
			}
			
			Role role = RBACDatabaseHelper.getInstance().findRole(options.get(Constants.ROLE_ID).toString());
			
			if(role == null) {
				Logger.logMessage("Role doesn't exists..Please create role before using it.");
				return false;
			}
			
			boolean exists = RBACDatabaseHelper.getInstance().findUserWithRole(options);
			
			if(exists) {
				Logger.logMessage("User already has role " + options.get(Constants.ROLE_ID).toString());
				return false;
			}
			
			return RBACDatabaseHelper.getInstance().addAssignment(options);
			
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}
	
	/**
	 * Removes role from the user.
	 */
	public boolean removeRoleFromUser(Map<String, Object> options) {
		
		try {
			ValidationUtils.validateOptions(options, Constants.USER_EMAIL);
			ValidationUtils.validateOptions(options, Constants.ROLE_ID);
			
			User user = RBACDatabaseHelper.getInstance().findUser(options.get(Constants.USER_EMAIL).toString());
			
			if(user == null) {
				Logger.logMessage("User doesn't exists. Please register...");
				return false;
			}
			
			Role role = RBACDatabaseHelper.getInstance().findRole(options.get(Constants.ROLE_ID).toString());
			
			if(role == null) {
				Logger.logMessage("Role doesn't exists..Please create role before using it.");
				return false;
			}
			
			boolean exists = RBACDatabaseHelper.getInstance().findUserWithRole(options);
			if(!exists) {
				Logger.logMessage("User doesn't has role " + options.get(Constants.ROLE_ID).toString());
				return false;
			}
			
			return RBACDatabaseHelper.getInstance().removeAssignment(options);
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}
	
	/**
	 * Checks whether user has permission for Action on the resource.
	 */
	public boolean hasPermission(Map<String, Object> options) {
		try {
			ValidationUtils.validateOptions(options, Constants.USER_EMAIL);
			ValidationUtils.validateOptions(options, Constants.ACTION_TYPE);
			ValidationUtils.validateOptions(options, Constants.RESOURCE_ID);
			User user = RBACDatabaseHelper.getInstance().findUser(options.get(Constants.USER_EMAIL).toString());
			
			if(user == null) {
				Logger.logMessage("User doesn't exists. Please register...");
				return false;
			}
			
			ActionType action = RBACDatabaseHelper.getInstance().findAction(options.get(Constants.ACTION_TYPE).toString());
			
			if(action == null) {
				Logger.logMessage("Action not supported.");
				return false;
			}
			
			Resource resource = RBACDatabaseHelper.getInstance().findResource(options.get(Constants.RESOURCE_ID).toString());
			
			if(resource == null) {
				Logger.logMessage("User asking permission for non-existent resource.");
				return false;
			}
			
			return RBACDatabaseHelper.getInstance().findPermission(options);
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}

}
