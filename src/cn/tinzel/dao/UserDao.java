package cn.tinzel.dao;

import java.util.List;

import cn.tinzel.domain.User;


public interface UserDao {
	
	/**
	 * 根据用户名，密码查询一个用户
	 * @author Jack_Wu
	 *
	 */
	User findUserByLogonNameAndLogonPwd(String logonName, String logonPwd);

	
	/**
	 * 保存用户
	 * @param user
	 */
	void save(User user);

	
	
    /**
     * 组合条件查询
     * @param user
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
	 * @param b
	 */
	void upadte(User user, boolean b);


	

}
