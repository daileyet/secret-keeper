package com.openthinks.secretkeeper.client.model;

public class CategoryData {
	private String name;

	public CategoryData() {
	}

	public CategoryData(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
