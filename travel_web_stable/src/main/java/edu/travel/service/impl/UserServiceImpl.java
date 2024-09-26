package edu.travel.service.impl;

import edu.travel.dao.UserDao;
import edu.travel.dao.impl.UserDaoImpl;
import edu.travel.entity.Admin;
import edu.travel.entity.User;
import edu.travel.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

	UserDao dao = new UserDaoImpl();

	@Override
	public Admin selectAdmin(String username, String password) {
		// TODO Auto-generated method stub
		return dao.selectAdmin(username, password);
	}

	@Override
	public User selectUser(String username, String password) {
		// TODO Auto-generated method stub
		return dao.selectUser(username, password);
	}

	@Override
	public User findUserById(String id) {
		// TODO Auto-generated method stub
		return dao.findUserById(id);
	}

	@Override
	public void deleteUser(String id) {
		// TODO Auto-generated method stub
		dao.deleteUser(id);
	}

	@Override
	public User selectUserByPhone(String phone) {
		// TODO Auto-generated method stub
		return dao.selectUserByPhone(phone);
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		dao.addUser(user);
	}

	@Override
	public List<User> getUserPage(int pageNum, int pageSize, Map map) {
		// TODO Auto-generated method stub
		return dao.getUserPage(pageNum, pageSize, map);
	}

	@Override
	public int queryUserCount(Map map) {
		// TODO Auto-generated method stub
		return dao.queryUserCount(map);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		dao.updateUser(user);
	}

	@Override
	public void updateAdminPassword(String newpass, Integer id) {
		// TODO Auto-generated method stub
		dao.updateAdminPassword(newpass, id);
	}

	@Override
	public void addMoney(User user) {
		// TODO Auto-generated method stub
		dao.addMoney(user);
	}

	@Override
	public List<User> selectUserList() {
		// TODO Auto-generated method stub
		return dao.selectUserList();
	}

	@Override
	public User selectUserByPhoneAndName(String phone, String realname) {
		return dao.selectUserByPhoneAndName(phone, realname);
	}

}
