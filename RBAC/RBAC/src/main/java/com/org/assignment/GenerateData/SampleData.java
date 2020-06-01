package com.org.assignment.GenerateData;

import java.util.ArrayList;
import java.util.List;

import com.org.assignment.Entities.ActionType;
import com.org.assignment.Entities.Assignment;
import com.org.assignment.Entities.Permissions;
import com.org.assignment.Entities.Resource;
import com.org.assignment.Entities.Role;
import com.org.assignment.Entities.User;

/**
 * Sample Data for Entities.
 * @author rakesh
 *
 */
public class SampleData {
	
	List<User> users;
	List<Role> roles;
	List<Resource> resources;
	List<Assignment> assignments;
	List<Permissions> permissions;
	List<ActionType> actions;
	
	public SampleData() {
		users = new ArrayList<User>();
		roles = new ArrayList<Role>();
		resources = new ArrayList<Resource>();
		assignments = new ArrayList<Assignment>();
		permissions = new ArrayList<Permissions>();
		actions = new ArrayList<ActionType>();
	}
	
	public List<User> getAllUsers() {
		return users;
	}
	
	public List<Role> getAllRoles() {
		return roles;
	}
	
	public List<Resource> getAllResource() {
		return resources;
	}
	
	public List<Assignment> getAllAssignments() {
		return assignments;
	}
	
	public List<Permissions> getAllPermissions() {
		return permissions;
	}
	
	public List<ActionType> getAllActions() {
		return actions;
	}
	
	public void addUser(User u) {
		users.add(u);
	}
	
	public void addRole(Role r) {
		roles.add(r);
	}
	
	public void addAction(ActionType action) {
		actions.add(action);
	}
	
	public void addAssignment(Assignment assignment) {
		assignments.add(assignment);
	}
	
	public void addPermission(Permissions p) {
		permissions.add(p);
	}
	
	public void loadData() {
		addUserData();
		addRoles();
		addResources();
		addActionTypes();
		addAssignments();
		addPermissions();
	}
	
	public void addUserData() {
		User user = new User();
		user.setEmpID("1");
		user.setUserName("Rakesh");
		user.setPhoneNumber("9949231286");
		user.setAddress("Hyderabad");
		user.setEmailID("iiit.rakesh6288@gmail.com");
		users.add(user);
		
		
		user = new User();
		user.setEmpID("2");
		user.setUserName("Anish");
		user.setPhoneNumber("9949231456");
		user.setAddress("Bangalore");
		user.setEmailID("anish.g@gmail.com");
		users.add(user);
		
		user = new User();
		user.setEmpID("3");
		user.setUserName("Aman");
		user.setPhoneNumber("9945631456");
		user.setAddress("Pune");
		user.setEmailID("aman.x@gmail.com");
		users.add(user);
	}
	
	public void addRoles() {
		Role role = new Role();
		role.setID("1");
		role.setName("Admin");
		role.setDescription("Admin has access to all the resources");
		roles.add(role);
		
		role = new Role();
		role.setID("2");
		role.setName("Software Developer Team 1");
		role.setDescription("Developer of Team 1 needs access team specific resources");
		roles.add(role);
		
		role = new Role();
		role.setID("3");
		role.setName("Software Developer Team 2");
		role.setDescription("Developer of Team 2 needs access team specific resources");
		roles.add(role);
	}
	
	public void addResources() {
		Resource resource = new Resource();
		resource.setResourceId("1");
		resource.setResourceType("InMemoryResource");
		resource.setResourceName("Redis");
		resources.add(resource);
		
		resource = new Resource();
		resource.setResourceId("2");
		resource.setResourceType("Server");
		resource.setResourceName("SQL Server 2008");
		resources.add(resource);
		
		resource = new Resource();
		resource.setResourceId("3");
		resource.setResourceType("InMemoryResource");
		resource.setResourceName("MemCache");
		resources.add(resource);
		
		resource = new Resource();
		resource.setResourceId("4");
		resource.setResourceType("BitBucket");
		resource.setResourceName("Repository Team1");
		resources.add(resource);
		
		resource = new Resource();
		resource.setResourceId("5");
		resource.setResourceType("BitBucket");
		resource.setResourceName("Repository Team2");
		resources.add(resource);
		
	}
	
	public void addActionTypes() {
		ActionType action = new ActionType();
		action.setID("1");
		action.setActionName("read");
		actions.add(action);
		
		action = new ActionType();
		action.setID("2");
		action.setActionName("write");
		actions.add(action);
		
		action = new ActionType();
		action.setID("3");
		action.setActionName("delete");
		actions.add(action);
	}
	
	public void addAssignments() {
		Assignment assignment = new Assignment();
		assignment.setUserEmail("iiit.rakesh6288@mgmail.com");
		assignment.setRoleID("1");
		assignments.add(assignment);
		
		assignment = new Assignment();
		assignment.setUserEmail("anish.g@gmail.com");
		assignment.setRoleID("2");
		assignments.add(assignment);
		
		assignment = new Assignment();
		assignment.setUserEmail("aman.g@gmail.com");
		assignment.setRoleID("3");
		assignments.add(assignment);
	}
	
	public void addPermissions() {
		Permissions permission = new Permissions();
		permission.setActionType("1");
		permission.setRoleID("1");
		permission.setResourceID("1");
		permissions.add(permission);
		
		permission = new Permissions();
		permission.setActionType("2");
		permission.setRoleID("1");
		permission.setResourceID("1");
		permissions.add(permission);
		
		permission = new Permissions();
		permission.setActionType("3");
		permission.setRoleID("1");
		permission.setResourceID("1");
		permissions.add(permission);
		
		permission = new Permissions();
		permission.setActionType("1");
		permission.setRoleID("1");
		permission.setResourceID("2");
		permissions.add(permission);
		
		permission = new Permissions();
		permission.setActionType("2");
		permission.setRoleID("1");
		permission.setResourceID("2");
		permissions.add(permission);
		
		permission = new Permissions();
		permission.setActionType("3");
		permission.setRoleID("1");
		permission.setResourceID("2");
		permissions.add(permission);
		
		permission = new Permissions();
		permission.setActionType("1");
		permission.setRoleID("1");
		permission.setResourceID("3");
		permissions.add(permission);
		
		permission = new Permissions();
		permission.setActionType("2");
		permission.setRoleID("1");
		permission.setResourceID("3");
		permissions.add(permission);
		
		permission = new Permissions();
		permission.setActionType("3");
		permission.setRoleID("1");
		permission.setResourceID("3");
		permissions.add(permission);
		
		permission = new Permissions();
		permission.setActionType("1");
		permission.setRoleID("1");
		permission.setResourceID("4");
		permissions.add(permission);
		
		permission = new Permissions();
		permission.setActionType("2");
		permission.setRoleID("1");
		permission.setResourceID("4");
		permissions.add(permission);
		
		permission = new Permissions();
		permission.setActionType("3");
		permission.setRoleID("1");
		permission.setResourceID("4");
		permissions.add(permission);
		
		permission = new Permissions();
		permission.setActionType("1");
		permission.setRoleID("1");
		permission.setResourceID("5");
		permissions.add(permission);
		
		permission = new Permissions();
		permission.setActionType("2");
		permission.setRoleID("1");
		permission.setResourceID("5");
		permissions.add(permission);
		
		permission = new Permissions();
		permission.setActionType("3");
		permission.setRoleID("1");
		permission.setResourceID("5");
		permissions.add(permission);
		
		permission = new Permissions();
		permission.setActionType("1");
		permission.setRoleID("2");
		permission.setResourceID("4");
		permissions.add(permission);
		
		permission = new Permissions();
		permission.setActionType("2");
		permission.setRoleID("2");
		permission.setResourceID("4");
		permissions.add(permission);
		
		permission = new Permissions();
		permission.setActionType("3");
		permission.setRoleID("2");
		permission.setResourceID("4");
		permissions.add(permission);
		
		permission = new Permissions();
		permission.setActionType("1");
		permission.setRoleID("2");
		permission.setResourceID("5");
		permissions.add(permission);
		
		permission = new Permissions();
		permission.setActionType("1");
		permission.setRoleID("3");
		permission.setResourceID("5");
		permissions.add(permission);
		
		permission = new Permissions();
		permission.setActionType("2");
		permission.setRoleID("3");
		permission.setResourceID("5");
		permissions.add(permission);
		
		permission = new Permissions();
		permission.setActionType("3");
		permission.setRoleID("3");
		permission.setResourceID("5");
		permissions.add(permission);
	}
}
