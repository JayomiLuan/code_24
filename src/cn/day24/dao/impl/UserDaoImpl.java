package cn.day24.dao.impl;

import cn.day24.dao.UserDao;
import cn.day24.domain.User;
import cn.day24.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;

public class UserDaoImpl implements UserDao {
	
	private QueryRunner queryRunner = new QueryRunner();
	
	//按ID（主键）取得用户信息
	@Override
	public User get(String id) throws Exception {
		Connection conn = JdbcUtils.getConnection();
		String sql = "select id,name,password from tb_user where id=?";
		User user = queryRunner.query(conn, sql, new BeanHandler<>( User.class ) , id);
		
		return user;
	}

}
