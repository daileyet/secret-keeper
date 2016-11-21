package com.openthinks.secretkeeper.common.dao.impl;

import java.io.File;
import java.util.Set;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.openthinks.secretkeeper.common.dao.ItemDao;
import com.openthinks.secretkeeper.common.domain.Item;
import com.openthinks.secretkeeper.common.utils.BeanLoader;

public class ItemDaoImplTest {
	static ItemDao itemDao;
	static String Category10_ID = "55caab8c-e66c-4be7-a218-5c2d30119e32";

	@BeforeClass
	public static void setUp() {
		MapDBHelper.setUp(new File("R:/MyGit/secret-keeper"), "secret.odb");
		itemDao = BeanLoader.loadBean(ItemDaoImpl.class);
	}

	public int getSize() {
		return itemDao.queryDomain((domain) -> {
			return true;
		}).size();
	}

	@Test
	public void testAdd() {
		int begin = getSize();
		System.out.println(begin);
		for (int i = 1; i < 100; i++) {
			Item item = new Item("test" + i, Category10_ID, "This is a test note.");
			itemDao.saveDomain(item);
		}
		itemDao.doCommit();
		int end = getSize();
		System.out.println(end);
		Assert.assertEquals(end - begin, 99);
	}

	//@AfterClass
	public static void tearDown() {
		Set<Item> items = itemDao.queryDomain((domain) -> {
			return Category10_ID.equals(domain.getCateID());
		});
		items.forEach((item) -> {
			itemDao.deleteDomain(item);
		});
		itemDao.doCommit();

	}
}
