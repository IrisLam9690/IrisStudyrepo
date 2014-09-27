package niuzhixiang.test.dao;

import niuzhixiang.bean.UserBean;
import niuzhixiang.dao.UserDao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:ApplicationContext.xml"})
@TransactionConfiguration(defaultRollback = true)

public class UserDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private UserDao userDao;
	
	@Test
	@Transactional

	public void testSave() {
		String result = userDao.save("test_username", "test_email@test.com", "test_password", 1);
		assertEquals("the return value of method save() must be \"register_success\"", "register_success", result);
	}
	
	@Test
	@Transactional
	public void testGetUserByProperty() {
		userDao.save("test_username", "test_email@test.com", "test_password", 1);
		assertEquals("result must be test_username", "test_username", userDao.getUserByProperty("username", "test_username").getUsername());
	}
}
