package cn.day24.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HelloFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("===init hello ...");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("===do filter hello 预处理...");
		//调用放行的方法，请求才能到达指定的位置 （访问到指定的资源）
		chain.doFilter(request, response);
		
		System.out.println("===do filter hello 后处理...");

	}

	@Override
	public void destroy() {
		System.out.println("===destory hello...");
	}

}
