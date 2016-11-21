package com.openthinks.secretkeeper.client.model;

import com.openthinks.secretkeeper.common.domain.Item;

public class ItemData {
	private String name;
	private Item preload;
	private CategoryData parent;

	public ItemData(String name, Item preload, CategoryData parent) {
		super();
		this.name = name;
		this.preload = preload;
		this.parent = parent;
	}

	public ItemData(String name, Item preload) {
		this(name, preload, null);
	}

	public ItemData(Item preload) {
		super();
		this.name = preload.getTitle();
		this.preload = preload;
	}

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

	public Item getPreload() {
		return preload;
	}

	public void setPreload(Item preload) {
		this.preload = preload;
	}

	public CategoryData getParent() {
		return parent;
	}

	public void setParent(CategoryData parent) {
		this.parent = parent;
	}

}
