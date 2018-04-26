package cn.tinzel.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.tinzel.dao.UserDao;
import cn.tinzel.domain.User;
import cn.tinzel.util.C3P0Util;

public class UserDaoImpl implements UserDao {

	
	//登录
	private QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
	public User findUserByLogonNameAndLogonPwd(String logonName, String logonPwd) {

		try {
			return qr.query("select * from s_user where logonName=? and logonPwd=?", new BeanHandler<User>(User.class),logonName,logonPwd);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//添加用户信息
	public void save(User user) {
		try {
			qr.update("insert into s_user values(null,?,?,?,?,?,?,?,?,?,?,?)",user.getUserName(),user.getLogonName(),user.getLogonPwd(),
					 user.getSex(),user.getBirthday(),user.getEducation(),user.getTelephone(),user.getInterest(),user.getPath(),
					 user.getFilename(),user.getRemark());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	public List<User> findUsersByMultiSelect(User user) {
		try {
			String sql = "select * from s_user where 1=1 ";
			List<String> argList = new ArrayList<String>(); // 参数列表
			if (user.getUserName() != null
					&& user.getUserName().trim().length() > 0) {
				sql += " and userName like ? ";
				argList.add("%" + user.getUserName() + "%");
			}
			if (user.getSex() != null && user.getSex().trim().length() > 0) {
				sql += " and sex = ? ";
				argList.add(user.getSex());
			}
			if (user.getEducation() != null
					&& user.getEducation().trim().length() > 0) {
				sql += "and education = ? ";
				argList.add(user.getEducation());
			}
			if (user.getIsUpload() != null
					&& user.getIsUpload().trim().length() > 0) {
				if (user.getIsUpload().equals("1")) {
					// 上传简历
					sql += "and filename is not null";
				} else if (user.getIsUpload().equals("2")) {
					// 没有上传简历
					sql += "and filename is null";
				}
			}

			return qr.query(sql, new BeanListHandler<User>(User.class),argList.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
			throw  new RuntimeException(e);
		}
	}

	


	//根据userID删除指定对象
	public User findUserByUserID(int userID) {
		try {
			return qr.query("select * from s_user where userID=?", new BeanHandler<User>(User.class),userID);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
}
	
	
	//根据userID查询指定对象
	public void deleteUserByUserID(int userID) {
		try {
			qr.update("delete from s_user where userID=?",userID);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
     }

	//更新用户信息
	public void upadte(User user, boolean b) {
		String sql="";
		Object[] args =null;
		if(b){
			//说明要更新简历
		     sql = "update s_user set userName= ? ,logonName=? , logonPwd =? ,sex=? ,birthday=?, education=? ,telephone=?, interest=?, path=? ,filename=?, remark=? where userID = ?";
			 args = new Object[] { user.getUserName(), user.getLogonName(),
					user.getLogonPwd(), user.getSex(), user.getBirthday(),
					user.getEducation(), user.getTelephone(),
					user.getInterest(), user.getPath(), user.getFilename(),
					user.getRemark(), user.getUserID() };
		}else{
			 sql = "update s_user set userName= ? ,logonName=? , logonPwd =? ,sex=? ,birthday=?, education=? ,telephone=?, interest=?, remark=? where userID = ?";
			 args = new Object[] { user.getUserName(), user.getLogonName(),
					user.getLogonPwd(), user.getSex(), user.getBirthday(),
					user.getEducation(), user.getTelephone(),
					user.getInterest(), 
					user.getRemark(), user.getUserID() };
		}
		
		
		try {
			qr.update(sql,args);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
		
	}
