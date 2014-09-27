package niuzhixiang.dao;


import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import niuzhixiang.bean.SchoolBean;
import niuzhixiang.bean.UserBean;
import niuzhixiang.bean.mongo.LogonSession;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.transaction.annotation.Transactional;

//利用注解来驱动事务
@Transactional(value = "TransactionManager")
public class UserDao {
	
	public UserDao() {
		super();
		// TODO Auto-generated constructor stub	
	}
	
	//利用注解来注入
	@Resource(name = "HibernateSessionFactory")
	private SessionFactory sessionFactory;
	
	@Resource(name = "MongoTemplate")
	private MongoTemplate mongoTemplate;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	public String save(String username, String email, String password, int schoolid) {
		UserBean userBean = new UserBean();
		userBean.setUsername(username);
		userBean.setEmail(email);
		userBean.setPassword(password);
		userBean.setSchoolBean((SchoolBean)(sessionFactory.getCurrentSession().load(SchoolBean.class, schoolid)));		
		if (userExists(userBean)) {
			System.out.println("user exists!");
			return "user_exists";
		} else {
			sessionFactory.getCurrentSession().save(userBean);
			System.out.println("user created successfully!");
			return "register_success";
		}
		
	}
	
	private boolean userExists(UserBean userBean){
		Query query = sessionFactory.getCurrentSession().createQuery("from UserBean as userbean where userbean.email = :email or userbean.username = :username");
		query.setParameter("email", userBean.getEmail());
		query.setParameter("username", userBean.getUsername());		
		if (query.list().size() == 0) {			
			return false;
		} else {			
			return true;
		}
	}
	
	@SuppressWarnings("unchecked")
	public UserBean checkUser(String username, String password) {
		Query query = sessionFactory.getCurrentSession().createQuery("from UserBean as userbean where userbean.username = :username and userbean.password = :password");
		query.setParameter("username", username);
		query.setParameter("password", password);
		List<UserBean> list = query.list();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * 创建登录会话
	 * @param userBean
	 * @return 登录会话LogonSession（持久化到mongodb中）
	 */
	public LogonSession createLogonSession(UserBean userBean) {
		String sessionid = UUID.randomUUID().toString();
		getMongoTemplate().insert(new LogonSession(sessionid, userBean.getId()), "logonsession");
		return getMongoTemplate().findOne(new org.springframework.data.mongodb.core.query.Query(Criteria.where("userid").is(userBean.getId())), LogonSession.class, "logonsession");
	}
	
	/**
	 * 注销登录会话
	 * @param userBean
	 */
	public void deleteLogonSession(int userid) {
		getMongoTemplate().remove(new org.springframework.data.mongodb.core.query.Query(Criteria.where("userid").is(userid)), "logonsession");
	}
	
	
	@SuppressWarnings("unchecked")
	public UserBean getUserById(int userid) {
		Query query = sessionFactory.getCurrentSession().createQuery("from UserBean as userbean where userbean.id = :userid");
		query.setParameter("userid", userid);
		List<UserBean> list = query.list();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public UserBean getUserByProperty(String property, Object value) {
		Query query = getSessionFactory().getCurrentSession().createQuery("from UserBean as userBean where userBean." + property + " = :value");
		query.setParameter("value", value);
		List<UserBean> userList = query.list();
		if (userList.size() > 0) {
			return userList.get(0);
		} else {
			return null;
		}
		
	}
}
