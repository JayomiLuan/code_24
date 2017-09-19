package cn.day24.dao;

import cn.day24.domain.User;

public interface UserDao {
	//根据ID取得用户对象
	User get(String id ) throws Exception;
}
