package niuzhixiang.test.dao;

import javax.annotation.Resource;

import niuzhixiang.bean.mongo.LogonSession;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:ApplicationContext.xml"})
@TransactionConfiguration(defaultRollback = false)
public class MongoDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Resource(name = "MongoTemplate")
	private MongoTemplate mongoTemplate;
	
	private LogonSession logonSession;
	
	//@Before
	public void setUp() {
		logonSession = new LogonSession();
		logonSession.setSessionid("aaa");
		mongoTemplate.save(logonSession, "logonsession");
	}
	
	@Test
	@Ignore
	public void testUpdate() {
		LogonSession result = mongoTemplate.findOne(new Query(Criteria.where("sessionid").is("aaa")), LogonSession.class, "logonsession");
		result.setUserid(6);
		mongoTemplate.save(result, "logonsession");
	}
}
