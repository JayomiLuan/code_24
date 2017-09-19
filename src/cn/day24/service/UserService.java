package cn.day24.service;

import cn.day24.domain.User;

public interface UserService {
	//登录成功返回用户对象，失败返回null
	User login(User user ) throws Exception;
}
