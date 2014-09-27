package niuzhixiang.dao;

import java.util.List;

import javax.annotation.Resource;

import niuzhixiang.bean.ItemBean;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

//使用注解来驱动事务
@Transactional(value = "TransactionManager")
public class ItemDao {

	@Resource(name = "HibernateSessionFactory")
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	public List<ItemBean> readAll() {
		Query query = sessionFactory.getCurrentSession().createQuery("from ItemBean as itemBean");
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ItemBean> readSeveral(int start, int size) {
		Query query = sessionFactory.getCurrentSession().createQuery("from ItemBean as itemBean");
		query.setFirstResult(start);
		query.setMaxResults(size);
		return query.list();
	}
	
}
