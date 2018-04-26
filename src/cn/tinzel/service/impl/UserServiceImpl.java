package cn.tinzel.service.impl;

import java.util.List;

import cn.tinzel.dao.UserDao;
import cn.tinzel.dao.impl.UserDaoImpl;
import cn.tinzel.domain.User;
import cn.tinzel.service.UserService;


/**
 * ʵ����
 * @author Jack_Wu
 *
 */
public class UserServiceImpl implements UserService {

    private UserDao dao = new UserDaoImpl(); 
    //��¼
	public User login(String logonName, String logonPwd) {

		return dao.findUserByLogonNameAndLogonPwd(logonName,logonPwd);
	}

	//����û���Ϣ
	public void save(User user) {
		dao.save(user);
	}

	
	
	//���������ѯ
	public List<User> findUsersByMultiSelect(User user) {

		return dao.findUsersByMultiSelect(user);
	}


	
	
	//����userID��ѯָ������
	public User findUserByUserID(int userID) {

		return dao.findUserByUserID(userID);
	}


	
	//����userIDɾ��ָ������
	public void deleteUserByUserID(int userID) {
		
		dao.deleteUserByUserID(userID);
	}

	//�����û���Ϣ
	public void update(User user, boolean b) {

		dao.upadte(user,b);
	}

	
	
	
	
	
	



}
