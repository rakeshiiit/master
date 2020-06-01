package com.org.assignment.RBAC;

import java.util.Map;

/**
 * Facade Layer for RBAC System
 * @author rakesh
 *
 */
public interface IRBACSystem {

	public boolean addRoleToUser(Map<String, Object> options);
	public boolean removeRoleFromUser(Map<String, Object> options);
	public boolean hasPermission(Map<String, Object> options);
}
