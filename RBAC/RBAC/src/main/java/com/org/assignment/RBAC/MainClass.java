package com.org.assignment.RBAC;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import com.org.assignment.Utils.Constants;

public class MainClass {

	public MainClass() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean whetherUserHasPermission() {
		System.out.println("Please enter User Email address");
		Scanner sc = new Scanner(System.in);
		String userMail = sc.nextLine();
		System.out.println("Please enter Resource id");
		String resourceID = sc.nextLine();
		System.out.println("Please enter action id");
		String actionID = sc.nextLine();
		RBACSystem obj = new RBACSystem();
		Map<String, Object> options = new HashMap<String, Object>();
		options.put(Constants.USER_EMAIL, userMail);
		options.put(Constants.RESOURCE_ID, resourceID);
		options.put(Constants.ACTION_TYPE, actionID);
		boolean status = obj.hasPermission(options);
		return status;
	}
	
	public boolean AddRole() {
		System.out.println("Please enter User Email address");
		Scanner sc = new Scanner(System.in);
		String userMail = sc.nextLine();
		System.out.println("Please enter Role id to assign");
		String roleID = sc.nextLine();
		RBACSystem obj = new RBACSystem();
		Map<String, Object> options = new HashMap<String, Object>();
		options.put(Constants.USER_EMAIL, userMail);
		options.put(Constants.ROLE_ID, roleID);
		boolean status = obj.addRoleToUser(options);
		return status;
	}
	
	public boolean RemoveRole() {
		System.out.println("Please enter User Email address");
		Scanner sc = new Scanner(System.in);
		String userMail = sc.nextLine();
		System.out.println("Please enter Role id to assign");
		String roleID = sc.nextLine();
		RBACSystem obj = new RBACSystem();
		Map<String, Object> options = new HashMap<String, Object>();
		options.put(Constants.USER_EMAIL, userMail);
		options.put(Constants.ROLE_ID, roleID);
		boolean status = obj.removeRoleFromUser(options);
		return status;
	}

	public static void main(String[] args) {
		MainClass m = new MainClass();
		//System.out.println(m.whetherUserHasPermission());
		System.out.println(m.AddRole());

	}

}
