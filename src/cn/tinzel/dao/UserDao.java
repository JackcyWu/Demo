package cn.tinzel.dao;

import java.util.List;

import cn.tinzel.domain.User;


public interface UserDao {
	
	/**
	 * �����û����������ѯһ���û�
	 * @author Jack_Wu
	 *
	 */
	User findUserByLogonNameAndLogonPwd(String logonName, String logonPwd);

	
	/**
	 * �����û�
	 * @param user
	 */
	void save(User user);

	
	
    /**
     * ���������ѯ
     * @param user
     */
	List<User> findUsersByMultiSelect(User user);

	
	/**
	 * ����userID��ѯָ������
	 * @param userID
	 * @return
	 */
	User findUserByUserID(int userID);

	
	
	/**
	 * ����userIDɾ��ָ������
	 * @param userID
	 * @return
	 */
	void deleteUserByUserID(int userID);


	
	
	/**
	 * �����û���Ϣ
	 * @param user
	 * @param b
	 */
	void upadte(User user, boolean b);


	

}
