package niuzhixiang.dao;

import java.util.List;

import javax.annotation.Resource;

import niuzhixiang.bean.SchoolBean;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional(value = "TransactionManager")
public class SchoolDao {
	
	@Resource(name = "HibernateSessionFactory")
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	public List<SchoolBean> readAll() {
		Query query = getSessionFactory().getCurrentSession().createQuery("from SchoolBean as schoolBean");
		return query.list();
	}

}
