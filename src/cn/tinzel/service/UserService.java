package cn.tinzel.service;

import java.util.List;

import cn.tinzel.domain.User;

public interface UserService {

	
	/**
	 * 登录
	 * @param logonName
	 * @param logonPwd
	 * @author Jack_Wu
	 *
	 */
	User login(String logonName, String logonPwd);

	
	/**
	 * 添加用户
	 * @param user
	 *
	 */
	void save(User user);


	/**
	 * 组合条件查询
	 * @return
	 */
	List<User> findUsersByMultiSelect(User user);


	
	
	/**
	 * 根据userID查询指定对象
	 * @param userID
	 * @return
	 */
	User findUserByUserID(int userID);


	
	/**
	 * 根据userID删除指定对象
	 * @param userID
	 * @return
	 */
	void deleteUserByUserID(int userID);


	
	
	/**
	 * 更新用户信息
	 * @param user
	 * @param b   false代表不更新简历       true代表更新简历
	 */
	void update(User user, boolean b);


	


	
	/**
	 * 根据userID删除指定对象
	 * @param userID
	 */
	
	

}
