package niuzhixiang.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import niuzhixiang.bean.mongo.LogonSession;
import niuzhixiang.util.Utils;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.sun.istack.logging.Logger;

public class AuthorityFilter implements Filter {

	private Logger logger = Logger.getLogger(AuthorityFilter.class);
	
	@Resource(name = "MongoTemplate")
	private MongoTemplate mongoTemplate;
	
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		String uri = req.getRequestURI();
		
		//如果是注册和登录页面，不验证是否登录
		if (uri.equals("")||uri.equals("/MyREST_Maven")||uri.equals("/MyREST_Maven/")
				||uri.equals("/MyREST_Maven/register")||uri.equals("/MyREST_Maven/register/")
				||uri.equals("/MyREST_Maven/logon")||uri.equals("/MyREST_Maven/logon/")
				//测试用
				||uri.equals("/MyREST_Maven/test")||uri.equals("/MyREST_Maven/test/")) {
			logger.info("登录注册页面，无需过滤");
			chain.doFilter(request, response);
		} 
		//否则要验证是否登录
		else {	
			//将所需的cookie转变成Map形式，便于后续处理
			
			//指某些网站为了辨别用户身份、进行session跟踪而储存在用户本地终端上的数据（通常经过加密）。
			//用来判断HTTP传输的状态
			Map<String, String> cookiesMap = Utils.convertCookies(req.getCookies(), "sessionid", "userid");
			logger.info("cookies : " + cookiesMap.toString());		
			//如果有包括sessionid和userid的cookie，则尝试去mongodb中读取LogonSession		
			if (cookiesMap!=null && cookiesMap.containsKey("sessionid") && cookiesMap.containsKey("userid")) {
				LogonSession logonSession = getMongoTemplate().findOne(new Query(Criteria.where("sessionid").is(cookiesMap.get("sessionid"))).addCriteria(Criteria.where("userid").is(Integer.parseInt(cookiesMap.get("userid")))), LogonSession.class, "logonsession");
				//LogonSession完全匹配，说明已经登录
				if (logonSession!=null) {
					logger.info("已经登录");
					chain.doFilter(request, response);
				}
				else {
					logger.info("没找到logonsession");
					resp.sendRedirect("http://localhost/MyREST_Maven");
				}
			} else {
				logger.info("cookie中不包含sessionid或userid");
				resp.sendRedirect("http://localhost/MyREST_Maven");
			}
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
