package niuzhixiang.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import niuzhixiang.bean.HtmlDispatcherBean;

@Path("/htmldispatcher")
public class HtmlDispatcherService {
	
	@GET
	@Path("/")
	@Produces({"application/json", "application/xml", "text/html"})
	public Response foo() {
		return Response.ok(new HtmlDispatcherBean()).build();
	}

}
