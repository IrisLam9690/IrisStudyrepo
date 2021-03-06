package niuzhixiang.test.service;

import java.io.IOException;

import javax.servlet.ServletException;

import niuzhixiang.service.AuthorityService;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;

public class AuthorityServiceTest {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private CXFServlet cxfServlet;
	
	@Before
	public void setUp() {
		//request = new MockHttpServletRequest();
		//response = new MockHttpServletResponse();
	}
	
	@Test
	@Ignore
	
	//cxfServlet是apache的一个webservice引擎。
	public void testIndex() {
		request = new MockHttpServletRequest("GET", "http://localhost/MyREST_Maven");
		response = new MockHttpServletResponse();
		cxfServlet = new CXFServlet();
		try {
			cxfServlet.service(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("niuzhixiang.bean.IndexBean", response.getClass().toString());
	}
}
