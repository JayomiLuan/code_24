package cn.day24.filter;

import cn.day24.wrapper.MyRequest;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class EncodingFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//设置字符串，解决POST方式的乱码问题
		request.setCharacterEncoding("UTF-8");
		
		//解决GET方式的中文乱码问题
		//装饰者模式，就是在过滤器中偷梁换柱，实现一个Request的包装类，把增强过的包装类发给目标
		HttpServletRequest req = new MyRequest( (HttpServletRequest)request );

		//放行、(此处的request和response和Servlet/JSP中的是相同的对象)
		chain.doFilter(req, response);
		
	}

	@Override
	public void destroy() {
	}

}
