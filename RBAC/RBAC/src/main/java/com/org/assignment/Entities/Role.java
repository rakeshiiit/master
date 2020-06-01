package com.org.assignment.Entities;

import java.util.ArrayList;
import java.util.List;

public class Role {
	
	private String ID;
	private String name;
	private String Description;

	public Role() {
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}
}
