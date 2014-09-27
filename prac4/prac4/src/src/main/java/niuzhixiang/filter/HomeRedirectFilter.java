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
		
		//�������cookieת���Map��ʽ�����ں�������
		Map<String, String> cookiesMap = Utils.convertCookies(req.getCookies(), "userid");
		/*
		 * ����������ǰ�û���ҳΪ/MyREST/user/5����ʱ������������/MyREST/user/7������uriʱ��˵��
		 * ���û�����ͼ������˵���ҳ����ʱ������Ҫ���������ض������Լ�����ҳ��ע��������ʽ��д����
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
