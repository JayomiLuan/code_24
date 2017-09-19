package cn.day24.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.day24.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import cn.day24.service.UserService;
import cn.day24.service.impl.UserServiceImpl;

public class LoginServlet extends HttpServlet {

	private UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//解决POST方式的中文乱问题
		req.setCharacterEncoding("UTF-8");

		//接收请求
		User user = new User();
		try {
			BeanUtils.populate( user , req.getParameterMap() );
			//调用业务逻辑
			user = userService.login(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//转跳页面
		if( user == null ){
			//登录失败
			req.setAttribute("errMsg", "用户名密码错误！");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}else{
			//登录成功,把用户对象存入Session，这样在此网站中任何页面都可以得到当前登录用户的信息（显示姓名）
			req.getSession().setAttribute("currentUser", user);
			
			//得到是否自动登录的标记，如果是，则用户名密码保存到Cookie
			String flag = req.getParameter("autoLogin");
			if( "true".equals( flag ) ){
				//保存Cookie的KV对，Key是固定名字autoLoginCookie，值是由@分隔的用户ID和密码
				Cookie cookie = new Cookie( "autoLoginCookie" , user.getId() + "@" +user.getPassword() );
				//设置Cookie的有效期为一天
				cookie.setMaxAge(24*60*60);
				//把Cookie写入客户端
				resp.addCookie(cookie);
			}
			
			//重定向到首页
			resp.sendRedirect( getServletContext().getContextPath() + "/index.jsp");
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
