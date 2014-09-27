package niuzhixiang.dao;

import javax.annotation.Resource;

import niuzhixiang.bean.DataBean;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional(value = "TransactionManager")
public class DataDao {

	@Resource(name = "HibernateSessionFactory")
	private SessionFactory sessionFactory;
	
	public void save(DataBean data) {
		sessionFactory.getCurrentSession().save(data);
	}
}
