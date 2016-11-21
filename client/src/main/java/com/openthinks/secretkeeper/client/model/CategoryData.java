package com.openthinks.secretkeeper.client.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.openthinks.secretkeeper.common.StaticDict;
import com.openthinks.secretkeeper.common.domain.Category;

public class CategoryData {
	private String name;
	private Category preload;
	private Set<CategoryData> childrenCategory;
	private CategoryData parent;
	private Set<ItemData> childrenItem;

	public CategoryData() {
		this.childrenCategory = Collections.synchronizedSet(new HashSet<>());
		this.childrenItem = Collections.synchronizedSet(new HashSet<>());
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
		this.childrenCategory = Collections.synchronizedSet(new HashSet<>());
		this.childrenItem = Collections.synchronizedSet(new HashSet<>());
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

	public Set<CategoryData> getChildrenCategory() {
		return childrenCategory;
	}

	public void setChildrenCategory(Set<CategoryData> children) {
		this.childrenCategory = children;
		if (children != null) {
			children.parallelStream().forEach((cateData) -> {
				cateData.setParent(this);
			});
		}
	}

	public void addChildCategory(CategoryData categoryData) {
		if (childrenCategory != null) {
			this.childrenCategory.add(categoryData);
			categoryData.setParent(this);
		}
	}

	public int childrenCategorySize() {
		return childrenCategory == null ? 0 : childrenCategory.size();
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

	public Set<ItemData> getChildrenItem() {
		return childrenItem;
	}

	public void setChildrenItem(Set<ItemData> childrenItem) {
		this.childrenItem = childrenItem;
		if (childrenItem != null) {
			childrenItem.parallelStream().forEach((itemData) -> {
				itemData.setParent(this);
			});
		}
	}

	public int childrenItemSize() {
		return childrenItem == null ? 0 : childrenItem.size();
	}

	public void addChildItem(ItemData itemData) {
		if (childrenItem != null) {
			this.childrenItem.add(itemData);
			itemData.setParent(this);
		}
	}

	public boolean isLeafLevel() {
		return childrenItemSize() != 0;
	}

	public boolean isBranchLevel() {
		return childrenCategorySize() != 0;
	}

}
