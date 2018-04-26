package cn.tinzel.service.impl;

import java.util.List;

import cn.tinzel.dao.UserDao;
import cn.tinzel.dao.impl.UserDaoImpl;
import cn.tinzel.domain.User;
import cn.tinzel.service.UserService;


/**
 * 实现类
 * @author Jack_Wu
 *
 */
public class UserServiceImpl implements UserService {

    private UserDao dao = new UserDaoImpl(); 
    //登录
	public User login(String logonName, String logonPwd) {

		return dao.findUserByLogonNameAndLogonPwd(logonName,logonPwd);
	}

	//添加用户信息
	public void save(User user) {
		dao.save(user);
	}

	
	
	//组合条件查询
	public List<User> findUsersByMultiSelect(User user) {

		return dao.findUsersByMultiSelect(user);
	}


	
	
	//根据userID查询指定对象
	public User findUserByUserID(int userID) {

		return dao.findUserByUserID(userID);
	}


	
	//根据userID删除指定对象
	public void deleteUserByUserID(int userID) {
		
		dao.deleteUserByUserID(userID);
	}

	//更新用户信息
	public void update(User user, boolean b) {

		dao.upadte(user,b);
	}

	
	
	
	
	
	



}
