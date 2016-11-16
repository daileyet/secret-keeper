package com.openthinks.secretkeeper.client.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.openthinks.secretkeeper.common.StaticDict;
import com.openthinks.secretkeeper.common.domain.Category;

public class CategoryData {
	private String name;
	private Category preload;
	private Set<CategoryData> children;
	private CategoryData parent;

	public CategoryData() {
		this.children = Collections.synchronizedSet(new HashSet<>());
	}

	public CategoryData(Category preload) {
		this(preload, null);
	}

	public CategoryData(Category preload, CategoryData parent) {
		this(preload == null ? null : preload.getName(), preload, parent);
	}

	public CategoryData(String name, Category preload, CategoryData parent) {
		super();
		this.name = name;
		this.preload = preload;
		this.children = Collections.synchronizedSet(new HashSet<>());
		this.parent = parent;
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

	public void setPreload(Category preload) {
		this.preload = preload;
	}

	public Category getPreload() {
		return preload;
	}

	public Set<CategoryData> getChildren() {
		return children;
	}

	public void setChildren(Set<CategoryData> children) {
		this.children = children;
	}

	public void addChild(CategoryData categoryData) {
		if (children != null) {
			this.children.add(categoryData);
			categoryData.setParent(this);
		}
	}

	public int childrenSize() {
		return children == null ? 0 : children.size();
	}

	public CategoryData getParent() {
		return parent;
	}

	public void setParent(CategoryData parent) {
		this.parent = parent;
	}

	public int getLevel() {
		return preload == null
				? (this.parent == null ? StaticDict.CATEGORY_TOP_LEVEL : StaticDict.CATEGORY_CHILD_LEVEL_1)
				: preload.getLevel();
	}

	@Override
	public String toString() {
		return name;
	}

}
