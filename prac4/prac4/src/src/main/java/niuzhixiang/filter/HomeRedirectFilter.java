package niuzhixiang.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import niuzhixiang.util.Utils;

public class HomeRedirectFilter implements Filter {


	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		String uri = req.getRequestURI();	
		
		//将所需的cookie转变成Map形式，便于后续处理
		Map<String, String> cookiesMap = Utils.convertCookies(req.getCookies(), "userid");
		/*
		 * 举例：若当前用户主页为/MyREST/user/5，此时当他请求形如/MyREST/user/7这样的uri时，说明
		 * 该用户正试图进入别人的主页，此时过滤器要将该请求重定向到他自己的主页。注意正则表达式的写法。
		 */
		if (cookiesMap!=null && Pattern.matches("/MyREST_Maven/user/\\w*", uri) && !uri.equals("/MyREST_Maven/user/"+cookiesMap.get("userid"))) {
			System.out.println("home redirect!");
			resp.sendRedirect("http://localhost/MyREST_Maven/user/"+cookiesMap.get("userid"));
		} else {
			chain.doFilter(request, response);
		}
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
