package com.openthinks.secretkeeper.client.model;

public class ItemData {
	private String name;

	public ItemData() {
	}

	public ItemData(String name) {
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
