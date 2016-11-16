package com.openthinks.secretkeeper.common.dao.impl;

import java.io.File;
import java.util.Set;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.openthinks.libs.utilities.pools.object.SharedContext;
import com.openthinks.secretkeeper.common.StaticDict;
import com.openthinks.secretkeeper.common.dao.CategoryDao;
import com.openthinks.secretkeeper.common.domain.Category;

public class CategoryDaoImplTest {
	static CategoryDao categoryDao;
	final static Category testDomain = new Category("Notes", StaticDict.CATEGORY_ROOT_LEVEL);

	@BeforeClass
	public static void setUp() {
		MapDBHelper.setUp(new File("R:/MyGit/secret-keeper"), "secret.odb");
		categoryDao = SharedContext.get().lookup(CategoryDaoImpl.class);
	}

	/**
	 * @return
	 */
	protected int getTestDomainSize() {
		return categoryDao.queryDomain((domain) -> {
			return testDomain.getName().equals(domain.getName());
		}).size();
	}

	//@Test
	public void testAdd() {
		int actual_size1 = getTestDomainSize();
		System.out.println("Size1:" + actual_size1);
		Category domain = testDomain;
		boolean actual = categoryDao.saveDomain(domain);
		categoryDao.doCommit();
		Assert.assertEquals(true, actual);
		int actual_size2 = getTestDomainSize();
		System.out.println("Size2:" + actual_size2);
		Assert.assertEquals(1, actual_size2 - actual_size1);
	}

	//@Test
	public void testAddSubs() {
		categoryDao.queryDomain((domain) -> {
			return testDomain.getName().equals(domain.getName());
		}).parallelStream().findFirst().ifPresent((domain) -> {
			for (int i = 1; i < 11; i++) {
				categoryDao
						.saveDomain(new Category("note" + i, StaticDict.CATEGORY_CHILD_LEVEL_1, domain.getUniqueID()));
			}
			categoryDao.doCommit();
		});
	}

	@Test
	public void test() {
		categoryDao.queryDomain((domain) -> {
			return true;
		}).forEach((domain) -> {
			System.out.println(domain);
		});
	}

	//@AfterClass
	public static void tearDown() {
		Set<Category> categorySet = categoryDao.queryDomain((domain) -> {
			return testDomain.getName().equals(domain.getName());
		});
		categorySet.forEach((domain) -> {
			categoryDao.deleteDomain(domain);
		});
		categoryDao.doCommit();
	}
}
