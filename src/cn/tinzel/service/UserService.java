package cn.tinzel.service;

import java.util.List;

import cn.tinzel.domain.User;

public interface UserService {

	
	/**
	 * ��¼
	 * @param logonName
	 * @param logonPwd
	 * @author Jack_Wu
	 *
	 */
	User login(String logonName, String logonPwd);

	
	/**
	 * ����û�
	 * @param user
	 *
	 */
	void save(User user);


	/**
	 * ���������ѯ
	 * @return
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
	 * @param b   false�������¼���       true������¼���
	 */
	void update(User user, boolean b);


	


	
	/**
	 * ����userIDɾ��ָ������
	 * @param userID
	 */
	
	

}
