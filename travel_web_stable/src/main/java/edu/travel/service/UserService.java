package edu.travel.service;

import edu.travel.entity.Admin;
import edu.travel.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

	Admin selectAdmin(String username, String password);

	User selectUser(String username, String password);

	User findUserById(String id);

	void deleteUser(String id);

	User selectUserByPhone(String phone);

	void addUser(User user);

	List<User> getUserPage(int pageNum, int pageSize, Map map);

	int queryUserCount(Map map);

	void updateUser(User user);

	void updateAdminPassword(String newpass, Integer id);

	void addMoney(User user);

	List<User> selectUserList();

	User selectUserByPhoneAndName(String phone, String realname);
}
