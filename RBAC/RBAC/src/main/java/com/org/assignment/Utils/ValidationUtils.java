package com.org.assignment.Utils;

import java.util.Map;

import com.org.assignment.Exception.RBACException;

public class ValidationUtils {

	public static void validateOptions(Map<String, Object> options, String key) throws RBACException{
		if(options.get(key) == null) {
			throw new RBACException("Key " + key + "not present in options");
		}
	}

}
