package com.org.assignment.Exception;

/**
 * Exception class for RBAC System
 * @author rakesh
 *
 */
public class RBACException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RBACException(String message, Throwable err) {
		super(message, err);
	}
	
	public RBACException(String message) {
		super(message);
	}

}
