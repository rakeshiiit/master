package com.org.assignment.RBAC;

import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import com.org.assignment.Utils.Constants;
import junit.framework.TestCase;

public class RBACSystemTests extends TestCase {
	
	@Test
	public void testAddAssignmentWithValidUserAndRoleShouldPass() {
		try {
			String userMail = "anish.g@gmail.com";
			String roleID = "1";
			RBACSystem obj = new RBACSystem();
			Map<String, Object> options = new HashMap<String, Object>();
			options.put(Constants.USER_EMAIL, userMail);
			options.put(Constants.ROLE_ID, roleID);
			boolean status = obj.addRoleToUser(options);
			Assert.assertTrue(status);
			status = RBACDatabaseHelper.getInstance().getInstance().findUserWithRole(options);
			Assert.assertTrue(status);
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	@Test
	public void testAddAssignmentWithInvalidUserAndValidRoleShouldFail() {
		try {
			String userMail = "rakesh.g@kony.com";
			String roleID = "1";
			RBACSystem obj = new RBACSystem();
			Map<String, Object> options = new HashMap<String, Object>();
			options.put(Constants.USER_EMAIL, userMail);
			options.put(Constants.ROLE_ID, roleID);
			boolean status = obj.addRoleToUser(options);
			Assert.assertFalse(status);
			status = RBACDatabaseHelper.getInstance().findUserWithRole(options);
			Assert.assertFalse(status);
		} catch (Exception e) {
			Assert.fail();
		}	
	}
	
	@Test
	public void testAddAssignmentValidUserAndInvalidRoleShouldFail() {
		try {
			String userMail = "anish.g@gmail.com";
			String roleID = "6";
			RBACSystem obj = new RBACSystem();
			Map<String, Object> options = new HashMap<String, Object>();
			options.put(Constants.USER_EMAIL, userMail);
			options.put(Constants.ROLE_ID, roleID);
			boolean status = obj.addRoleToUser(options);
			Assert.assertFalse(status);
			status = RBACDatabaseHelper.getInstance().findUserWithRole(options);
			Assert.assertFalse(status);
		} catch (Exception e) {
			Assert.fail();
		}
		
	}
	
	@Test
	public void testAddAssignmentInvalidUserAndInvalidRoleShouldFail() {
		try {
			String userMail = "rakesh.g@kony.com";
			String roleID = "6";
			RBACSystem obj = new RBACSystem();
			Map<String, Object> options = new HashMap<String, Object>();
			options.put(Constants.USER_EMAIL, userMail);
			options.put(Constants.ROLE_ID, roleID);
			boolean status = obj.addRoleToUser(options);
			Assert.assertFalse(status);
			status = RBACDatabaseHelper.getInstance().findUserWithRole(options);
		Assert.assertFalse(status);	
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	@Test
	public void testAddAssignmentForExistingUserAndRoleShouldFail() {
		try {
			String userMail = "anish.g@gmail.com";
			String roleID = "2";
			RBACSystem obj = new RBACSystem();
			Map<String, Object> options = new HashMap<String, Object>();
			options.put(Constants.USER_EMAIL, userMail);
			options.put(Constants.ROLE_ID, roleID);
			boolean status = obj.addRoleToUser(options);
			Assert.assertFalse(status);
			status = RBACDatabaseHelper.getInstance().findUserWithRole(options);
			Assert.assertTrue(status);
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	@Test
	public void testRemoveAssignmentWithValidUserAndRoleShouldPass() {
		try {
			String userMail = "anish.g@gmail.com";
			String roleID = "2";
			RBACSystem obj = new RBACSystem();
			Map<String, Object> options = new HashMap<String, Object>();
			options.put(Constants.USER_EMAIL, userMail);
			options.put(Constants.ROLE_ID, roleID);
			boolean status = obj.removeRoleFromUser(options);
			Assert.assertTrue(status);
			status = RBACDatabaseHelper.getInstance().findUserWithRole(options);
			Assert.assertFalse(status);
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	@Test
	public void testRemoveAssignmentWithInvalidUserAndRoleShouldFail() {
		try {
			String userMail = "anish.g@kony.com";
			String roleID = "2";
			RBACSystem obj = new RBACSystem();
			Map<String, Object> options = new HashMap<String, Object>();
			options.put(Constants.USER_EMAIL, userMail);
			options.put(Constants.ROLE_ID, roleID);
			boolean status = obj.removeRoleFromUser(options);
			Assert.assertFalse(status);
			status = RBACDatabaseHelper.getInstance().findUserWithRole(options);
			Assert.assertFalse(status);
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	@Test
	public void testRemoveAssignmentWithValidUserAndInvalidRoleShouldFail() {
		try {
			String userMail = "anish.g@gmail.com";
			String roleID = "6";
			RBACSystem obj = new RBACSystem();
			Map<String, Object> options = new HashMap<String, Object>();
			options.put(Constants.USER_EMAIL, userMail);
			options.put(Constants.ROLE_ID, roleID);
			boolean status = obj.removeRoleFromUser(options);
			Assert.assertFalse(status);
			status = RBACDatabaseHelper.getInstance().findUserWithRole(options);
			Assert.assertFalse(status);
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	@Test
	public void testRemoveAssignmentWithInvalidUserAndInvalidRoleShouldFail() {
		try {
			String userMail = "anish.g@kony.com";
			String roleID = "6";
			RBACSystem obj = new RBACSystem();
			Map<String, Object> options = new HashMap<String, Object>();
			options.put(Constants.USER_EMAIL, userMail);
			options.put(Constants.ROLE_ID, roleID);
			boolean status = obj.removeRoleFromUser(options);
			Assert.assertFalse(status);
			status = RBACDatabaseHelper.getInstance().findUserWithRole(options);
			Assert.assertFalse(status);
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	@Test
	public void testHasPermissionWithValidArgumentShouldPass() {
		String userMail = "anish.g@gmail.com";
		String resourceID = "4";
		String actionID = "2";
		RBACSystem obj = new RBACSystem();
		Map<String, Object> options = new HashMap<String, Object>();
		options.put(Constants.USER_EMAIL, userMail);
		options.put(Constants.RESOURCE_ID, resourceID);
		options.put(Constants.ACTION_TYPE, actionID);
		boolean status = obj.hasPermission(options);
		Assert.assertTrue(status);
	}
	
	@Test
	public void testHasPermissionWithInvalidUserShouldFail() {
		String userMail = "anish.g@kony.com";
		String resourceID = "4";
		String actionID = "2";
		RBACSystem obj = new RBACSystem();
		Map<String, Object> options = new HashMap<String, Object>();
		options.put(Constants.USER_EMAIL, userMail);
		options.put(Constants.RESOURCE_ID, resourceID);
		options.put(Constants.ACTION_TYPE, actionID);
		boolean status = obj.hasPermission(options);
		Assert.assertFalse(status);
	}
	
	@Test
	public void testHasPermissionWithInvalidActionTypeShouldFail() {
		String userMail = "anish.g@gmail.com";
		String resourceID = "4";
		String actionID = "7";
		RBACSystem obj = new RBACSystem();
		Map<String, Object> options = new HashMap<String, Object>();
		options.put(Constants.USER_EMAIL, userMail);
		options.put(Constants.RESOURCE_ID, resourceID);
		options.put(Constants.ACTION_TYPE, actionID);
		boolean status = obj.hasPermission(options);
		Assert.assertFalse(status);
	}
	
	
	@Test
	public void testHasPermissionWithInvalidResourceShouldFail() {
		String userMail = "anish.g@gmail.com";
		String resourceID = "8";
		String actionID = "2";
		RBACSystem obj = new RBACSystem();
		Map<String, Object> options = new HashMap<String, Object>();
		options.put(Constants.USER_EMAIL, userMail);
		options.put(Constants.RESOURCE_ID, resourceID);
		options.put(Constants.ACTION_TYPE, actionID);
		boolean status = obj.hasPermission(options);
		Assert.assertFalse(status);
	}
	
	@Test
	public void testHasPermissionWithValidArgumentButNoPermissionShouldPass() {
		String userMail = "anish.g@gmail.com";
		String resourceID = "5";
		String actionID = "2";
		RBACSystem obj = new RBACSystem();
		Map<String, Object> options = new HashMap<String, Object>();
		options.put(Constants.USER_EMAIL, userMail);
		options.put(Constants.RESOURCE_ID, resourceID);
		options.put(Constants.ACTION_TYPE, actionID);
		boolean status = obj.hasPermission(options);
		Assert.assertFalse(status);
	}
}
