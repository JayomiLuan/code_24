package cn.day24.filter;

import cn.day24.domain.User;
import cn.day24.service.UserService;
import cn.day24.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutoLoginFilter implements Filter {

	private UserService userService = new UserServiceImpl();
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//强转request和response的类型
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response; 
		
		//一、判断是否已登录，如果是，则放行并返回。(查看Session中否存在currentUser，存在表示已经登录过了)
		User user = (User)req.getSession().getAttribute("currentUser");
		if( user != null ){
			//放行并结束方法
			chain.doFilter(request, response);
			return;
		}
		
//		二、判断Cookie中是否有登录信息：
		Cookie[] cookies = req.getCookies();
		Cookie userCookie = null;
		if( cookies != null ){
			for( Cookie c : cookies ){
				if( "autoLoginCookie".equals( c.getName() ) ){
					userCookie = c;
					break;//如果找到，则循环结束
				}
			}
		}
//		1、没有，则放行并返回(相当于未登录状态，显示index.jsp页面时显示的是“未登录”)
		//判断userCookie是否为空，代表Cookie中是否存在自动登录信息
		if( userCookie == null ){
			//放行并结束方法
			chain.doFilter(request, response);
			return;
		}
		
//		2、有则进行自动登录动作（调用业务逻辑进行登录验证）
		//从Cookie中取出保存的用户名和密码( username@password )
		//对Cookie中保存的字符串用@进行拆分，第一个数组元素是用户名，第二个是密码
		String[] temp = userCookie.getValue().split("@");
		//创建User对象并封装 用户名和密码
		user = new User();
		user.setId( temp[0] );
		user.setPassword( temp[1] );
		User u = null;
		try {
			//调用业务逻辑，进行登录验证
			u = userService.login(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if( u == null ){
//			（1）用户名密码无效，放行并返回
			//放行并结束方法
			chain.doFilter(request, response);
			return;
		}else{
//		（2）用户名密码正确，把业务逻辑返回的用户对象保存到Session，名为“currentUser”，放行并返回
			req.getSession().setAttribute("currentUser", u);
			//放行
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
