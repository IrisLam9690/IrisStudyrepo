package niuzhixiang.service;

import javax.annotation.Resource;
import javax.mail.FetchProfile.Item;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;

import niuzhixiang.bean.DataBean;
import niuzhixiang.bean.HomeBeanMobile;
import niuzhixiang.bean.HomeBeanPC;
import niuzhixiang.bean.ItemBean;
import niuzhixiang.bean.ListContainer;
import niuzhixiang.bean.Steps;
import niuzhixiang.bean.UserBean;
import niuzhixiang.dao.DataDao;
import niuzhixiang.dao.ItemDao;
import niuzhixiang.dao.UserDao;

@Path("/user")
public class HomeService {
	
	Logger logger = Logger.getLogger(HomeService.class);

	@Resource(name = "UserDao")
	private UserDao userDao;
	
	@Resource(name = "ItemDao")
	private ItemDao itemDao;
	
	@Resource(name = "DataDao")
	private DataDao dataDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public ItemDao getItemDao() {
		return itemDao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}
	
	@GET
	@Path("/{userid}")
	@Produces({ "application/json", "application/xml", "text/html" })
	public Response homePC(@PathParam("userid") int userid, @QueryParam("start") String start, @QueryParam("size") String size) {
		try {			
			//如果没有start和size参数，说明这是第一次加载该页面（非AJAX请求），则返回HomeBeanPC（包含UserBean和ListContainer）
			if (start==null || size==null) {
				UserBean userBean = userDao.getUserById(userid);
				ListContainer<ItemBean> items = new ListContainer<ItemBean>(itemDao.readSeveral(0, 5));			
				HomeBeanPC homeBeanPC = new HomeBeanPC(userBean, items);
				return Response.ok(homeBeanPC).status(200).build();
			} 
			//如果有start和size参数，说明是AJAX请求，则只返回ListContainer即可
			else {
				ListContainer<ItemBean> items = new ListContainer<ItemBean>(itemDao.readSeveral(Integer.parseInt(start), Integer.parseInt(size)));
				return Response.ok(items).status(200).build();
			}			
		} catch (Exception e) {
			// TODO: handle exception
			return Response.ok().status(500).build();
		}		
	}
	
	@GET
	@Path("/mobile/{userid}")
	@Produces({ "application/json", "application/xml", "text/html" })
	public Response homeMobile(@PathParam("userid") int userid) {
		try {			
			logger.info("/user/mobile is visited");
			UserBean userBean = userDao.getUserById(userid);		
			HomeBeanMobile homeBeanMobile = new HomeBeanMobile(userBean);
			return Response.ok(homeBeanMobile).status(200).build();			
		} catch (Exception e) {
			// TODO: handle exception
			return Response.ok().status(500).build();
		}		
	}
	
	@GET
	@Path("/{userid}/step1")
	@Produces({ "application/json", "application/xml", "text/html" })
	public Response step1(@PathParam("userid") int userid) {
		return Response.ok(Steps.STEP1).build();
	}
	
	@GET
	@Path("/{userid}/step2")
	@Produces({ "application/json", "application/xml", "text/html" })
	public Response step2(@PathParam("userid") int userid) {
		return Response.ok(Steps.STEP2).build();
	}
	
	@POST
	@Path("/{userid}/stepcomplete")
	@Produces({ "application/json", "application/xml", "text/html" })
	public Response stepComplete(@PathParam("userid") int userid, @CookieParam("data1") String data1, @CookieParam("data2") String data2, @CookieParam("data3") String data3, @CookieParam("data4") String data4) {
		DataBean data = new DataBean();
		data.setData1(data1);
		data.setData2(data2);
		data.setData3(data3);
		data.setData4(data4);
		try {
			dataDao.save(data);
		} catch (Exception e) {
			// TODO: handle exception
			return Response.ok().status(500).build();
		}
		return Response.ok().status(200).build();
	}
}
