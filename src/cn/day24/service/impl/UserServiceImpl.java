package cn.day24.service.impl;

import cn.day24.dao.UserDao;
import cn.day24.dao.impl.UserDaoImpl;
import cn.day24.domain.User;
import cn.day24.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserDao userDao = new UserDaoImpl();
	
	//如果用户名和密码正确，则返回用户对象，否则返回null
	@Override
	public User login(User user) throws Exception {
		//从数据库中取得指定用户信息
		User u = userDao.get( user.getId() );
		if( u == null ){
			return null;
		}
		//判断提交的密码和数据库中取出的密码是否相同
		if( u.getPassword().equals( user.getPassword() ) ){
			return u;
		}
		
		return null;
	}

}
