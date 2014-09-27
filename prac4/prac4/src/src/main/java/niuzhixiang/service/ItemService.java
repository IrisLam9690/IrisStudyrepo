package niuzhixiang.service;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import niuzhixiang.bean.ItemBean;
import niuzhixiang.bean.ListContainer;
import niuzhixiang.dao.ItemDao;

@Path("/item")
public class ItemService {
	
	@Resource(name = "ItemDao")
	private ItemDao itemDao;
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllItems() {
		return Response.ok(new ListContainer<ItemBean>(itemDao.readAll())).build();
	}
}
