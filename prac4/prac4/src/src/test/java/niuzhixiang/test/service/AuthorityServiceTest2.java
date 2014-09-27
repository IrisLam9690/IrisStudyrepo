package niuzhixiang.test.service;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.core.Response;

import niuzhixiang.bean.mongo.LogonSession;
import niuzhixiang.service.AuthorityService;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:ApplicationContext.xml"})
@TransactionConfiguration(defaultRollback = true)
public class AuthorityServiceTest2 extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private AuthorityService authorityService;
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Test
	@Transactional
	public void testDoRegister() {
		Response response = authorityService.doRegister("test", "test@test.com", "test", "1");
		Assert.assertEquals(200, response.getStatus());
	}
	
	@Test
	@Transactional
	
	
	//Criteria��һ�ֱ�hql���������Ĳ�ѯ��ʽ��
	//�������� FetchMode( ���ϲ�ѯץȡ��ģʽ ) ����������ʽ��Criteria ���������� FlushModel ����ˢ Session �ķ�ʽ���� LockMode �����ݿ���ģʽ����[1]
	public void testLogonAndLogout() {
		Response response = authorityService.logon("nzx", "nzx", "pc");
		Assert.assertEquals("response code of logon must be 200", 200, response.getStatus());
		Assert.assertTrue(mongoTemplate.find(Query.query(Criteria.where("userid").is(3)), LogonSession.class, "logonsession").size() > 0);
		Assert.assertNotNull("there must be a cookie named \"userid\"", response.getCookies().get("userid"));
		Assert.assertNotNull("there must be a cookie named \"sessionid\"", response.getCookies().get("sessionid"));
		try {
			Assert.assertEquals(new URI("/user/3"), response.getLocation());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Response response2 = authorityService.logout(3);
		Assert.assertEquals("response code of logout must be 302", 302, response2.getStatus());
		Assert.assertTrue(mongoTemplate.find(Query.query(Criteria.where("userid").is(3)), LogonSession.class, "logonsession").size() == 0);	
	}
}
