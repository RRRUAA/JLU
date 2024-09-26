package edu.travel.dao.impl;

import edu.travel.dao.UserDao;
import edu.travel.entity.*;
import edu.travel.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {

	private QueryRunner runner = new QueryRunner(C3p0Utils.getDs());
	private String adminColumn = "id,username,nickname,pwd", userColumn = "id,realname,pwd,sex,photo,nickname,phone";

	private static String bytesToHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		//MD5加密后bytes长度16转换成32位16进制字符串
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(0xFF & bytes[i]);
			if (hex.length() == 1) {
				sb.append('0');
			}
			sb.append(hex);
		}
		return sb.toString();
	}

	@Override
	public Admin selectAdmin(String username, String password) {
		try {// 返回查询的信息
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(password.getBytes());
			String pwd = bytesToHexString(messageDigest.digest());
			return runner.query("select "+adminColumn+" from admin where username=? and pwd=? ", new BeanHandler<Admin>(Admin.class),username, password);

		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public User selectUser(String username, String password) {
		try {// 返回查询的信息
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(password.getBytes());
			String pwd = bytesToHexString(messageDigest.digest());
			return runner.query("select "+userColumn+" from user where phone=? and pwd=? ", new BeanHandler<User>(User.class),
					username, pwd);
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public User findUserById(String id) {
		try {// 返回查询的信息User
			return runner.query("select "+userColumn+" from user where id=? ", new BeanHandler<User>(User.class),
					Integer.parseInt(id));
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public void deleteUser(String id) {
		// TODO Auto-generated method stub
		Integer ids = Integer.parseInt(id);
		try {// 执行更改
			runner.update("update user set existStatus=0 where id=?", ids);
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public User selectUserByPhone(String phone) {
		try {// 返回查询的信息User
			return runner.query("select "+userColumn+" from user where phone=? ", new BeanHandler<User>(User.class), phone);
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		try {
			// 执行插入sql
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(user.getPwd().getBytes());
			String pwd = bytesToHexString(messageDigest.digest());
			runner.update("insert into user(realname,pwd,sex,photo,nickname,phone) values (?,?,?,?,?,?)",
					user.getRealname(), pwd, user.getSex(), user.getPhoto(), user.getNickname(),
					user.getPhone());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public List<User> getUserPage(int pageNum, int pageSize, Map map) {
		String sql = "select "+userColumn+" from user where 1 = 1 ";
		if (map.get("key") != null && !map.get("key").toString().equals("")) {
			sql += " and realname like  '%" + map.get("key").toString() + "%'";
		}
		sql += " limit ?,? ";
		int startNo = (pageNum - 1) * pageSize;
		List<User> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<User>(User.class), new Object[]{startNo, pageSize});// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public int queryUserCount(Map map) {
		String sql = "select count(*) from user where 1 = 1 ";
		if (map.get("key") != null && !map.get("key").toString().equals("")) {
			sql += " and realname like  '%" + map.get("key").toString() + "%'";
		}
		try {
			Long count = (Long) runner.query(sql, new ScalarHandler());
			// 将long的类型转成int类型
			return count.intValue();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		try {// 执行更改
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(user.getPwd().getBytes());
			String pwd = bytesToHexString(messageDigest.digest());
			runner.update("update user set realname=?,pwd=?,sex=?,nickname=?,phone=? where id=?", user.getRealname(),
					pwd, user.getSex(), user.getNickname(), user.getPhone(), user.getId());
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateAdminPassword(String newpass, Integer id) {
		// TODO Auto-generated method stub
		try {// 执行更改
			runner.update("update admin set pwd=? where id=?", newpass, id);
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public void addMoney(User user) {
		// TODO Auto-generated method stub
		try {// 执行更改
			runner.update("update user set money=? where id=?", user.getMoney(), user.getId());
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public List<User> selectUserList() {
		String sql = "select "+userColumn+" from user  ";
		List<User> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<User>(User.class));// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public User selectUserByPhoneAndName(String phone, String realname) {
		try {// 返回查询的信息User
			return runner.query("select "+userColumn+" from user where phone=? and realname like ? ", new BeanHandler<User>(User.class), phone, realname);
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public void trySafeDel(int id) {
		String sql = "select "+userColumn+" from category where id = ? and existStatus = 0";
		List<User> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<User>(User.class), id);
			if(list == null || list.size() == 0){
				return;
			}
			sql = "select id,ordernum,quantity,total_price,pay_way,create_time,tid,uid,uname,status from orders where uid = ?";
			List<Orders> ordersList = runner.query(sql, new BeanListHandler<Orders>(Orders.class), id);
			if(ordersList != null && !ordersList.isEmpty()){
				return;
			}
			sql = "select id,ordernum,quantity,total_price,pay_way,create_time,tid,uid,uname,status from orders where uid = ?";
			List<Orders> scenicList = runner.query(sql, new BeanListHandler<Orders>(Orders.class), id);
			if(scenicList != null && !scenicList.isEmpty()){
				return;
			}
			sql = "delete from user where id = ?";
			runner.update(sql, id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
