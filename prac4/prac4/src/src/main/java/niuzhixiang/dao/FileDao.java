package niuzhixiang.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import niuzhixiang.bean.FileBean;
import niuzhixiang.bean.Files;
import niuzhixiang.util.Multipart;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

//使用注解来驱动事务
@Transactional(value = "TransactionManager")
public class FileDao {

	@Resource(name = "HibernateSessionFactory")
	private SessionFactory sessionFactory;	
	
	@Resource(name = "Multipart")
	private Multipart multipart;
	
	public boolean uploadFile(Attachment attachment, int userid) {
		try {
			String path = multipart.uploadFile(attachment);//multipart是增加和修改链接可用
			FileBean fileBean = new FileBean();
			fileBean.setPath(path);
			fileBean.setUserid(userid);
			sessionFactory.getCurrentSession().save(fileBean);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean uploadFiles(List<Attachment> attachments, int userid) {
		boolean result = true;
		for (Attachment attachment : attachments) {
			if (!uploadFile(attachment, userid)) {
				result = false;
			}		
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public Files getFilesByUser(int userid) {
		Files files = new Files();
		Query query = sessionFactory.getCurrentSession().createQuery("from FileBean as fileBean where fileBean.userid = :userid");
		query.setParameter("userid", userid);
		files.setFiles(query.list());
		return files;
	}
	
	public FileBean getFileById(int fileid) {
		Query query = sessionFactory.getCurrentSession().createQuery("from FileBean as fileBean where fileBean.id = :id");
		query.setParameter("id", fileid);
		List<FileBean> resultList = query.list();
		if (resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
}
