package com.openthinks.secretkeeper.client.model;

public class CategoryData {
	private String name;
	private int level = 1;//first level

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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getChildLevel() {
		return level + 1;
	}

	@Override
	public String toString() {
		return name;
	}

}
