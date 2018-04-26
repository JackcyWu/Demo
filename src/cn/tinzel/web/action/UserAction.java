package cn.tinzel.web.action;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

import cn.tinzel.domain.User;
import cn.tinzel.service.UserService;
import cn.tinzel.service.impl.UserServiceImpl;

public class UserAction extends ActionSupport implements ModelDriven<User> {

	//ģ������
	private User user = new User();	
	public User getModel() {
		return user;
	}
	
	
	//����ҵ���߼�
		private UserService userService = new UserServiceImpl();
	

		
	//---------------��¼
		
	@InputConfig(resultName="loginINPUT")	
    public String login() throws Exception {
    	//1.����ҵ�񷽷����ж��û������������Ч��
    	User loginedUser = userService.login(user.getLogonName(),user.getLogonPwd());
    	if(loginedUser!=null){
			//˵����¼ �ɹ�
    		ServletActionContext.getRequest().getSession().setAttribute("user", loginedUser);
			return "loginSUCCESS";
    	}else {
    		this.addActionError(this.getText("loginfail"));//���ʻ�
			return LOGIN;
    	}
    }
	
	/**
	 * =========================
	 * ����û�����ʵ�ּ����ϴ�
	 * 
	 * 1.���ղ���,������ModelDriven������,��add.jspҳ�����ı����ȡֵ���浽��ջ��(User����)
	 * 2.�ļ��ϴ�(�����ϴ�),fileUpload�������Ὣ <s:file name="upload" size="30" id="userAction_save_do_upload"/>
	 *   ���е�ֵ�������һ���淶��������()
	 *   private File upload;
	 *   private String uploadContextType;
	 *   private String uploadFileName;
	 *   ����set();
	 */
    
    
    @InputConfig(resultName="saveINPUT")
    public String save() throws Exception {
    	//1.�ж��Ƿ��ϴ��˼�����Ϣ
		if(upload!=null){
			//˵���ϴ����� �ˣ���Ҫ����   /WEB-INF/upload/
			String absPath = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload/");//����·��
			File file = new File(absPath);
			if(!file.exists()){
				file.mkdirs();
			}
			
			//�����ļ�
			String uuid = UUID.randomUUID().toString();//�ļ���
			File destFile = new File(absPath+File.separator+uuid);//��ʱ�ļ�������uuid,���Է��ļ����������⣬�Լ�����ȫ
			FileUtils.copyFile(upload, destFile);
			
			//����filename,path��ȡֵ
			user.setFilename(uploadFileName);//������ʵ�ļ���
			user.setPath("/WEB-INF/upload/"+uuid);//��Ϊ������Tomcat��ַ���ܻᷢ���ı䣬�����ݿ��д���Ե�ַ��������ʱ��ȡ����·��
		}
		
	    //2.������û���ϴ����� ����Ҫ�����û��Ļ�����Ϣ�������ݿ���
		//����ҵ�񷽷��������û���Ϣ
		userService.save(user);
    	
    	return "saveSUCCESS";
    }
    
    private File upload;
    private String uploadContextType;     //�ļ���ʽ
    private String uploadFileName;        //�ļ���
	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
    

	
	/**
	 * 
	 * ���������ѯ,��ѯ������ֱ�ӷ���ջ����Model������
	 */
	
	public String find() throws Exception {
		//����ҵ�񷽷�,��ѯһ��List����
        list = userService.findUsersByMultiSelect(user);
		return "findSUCCESS";
	}
	//��jspҳ���õ�getList()�������ؽ��ʱ,find()�����Ѿ�ִ�й���
	private List<User> list;   //action�������������get������JSPҳ��Ϳ���ֱ��ʹ��OGNL���ʽȡֵ
	public List<User> getList() {
		return list;
	}
	
	
	
	/**
	 * ɾ��ָ���û�  ?  UserID=1    ------>ֵջ��ջ����model����
	 * 
	 */
	
	public String delete() throws Exception {
        //1.����userID����ָ����user����
		User u = userService.findUserByUserID(user.getUserID());
		if(u!=null && u.getFilename()!=null && u.getFilename().trim().length()>0){
			//˵���û��ϴ��˼���
			//��Ҫɾ������
			String relativePasth = u.getPath(); //�õ��û������·��
			String absPath = ServletActionContext.getServletContext().getRealPath(relativePasth);  //����·��
		    //�����ļ�����,׼��ɾ������ļ�
			File file = new File(absPath);
			file.delete();
		}
		
		userService.deleteUserByUserID(user.getUserID());
		
		return "deleteSUCCESS";
	}
	
	
	
	
	/**
	 * �鿴����---------��userID=xxx--------��ջ��(Model����(user�о���һ��userID))-------��ʱ�����ӵĲ����ͷ�����ջ������
	 * 
	 */
	
	public String view() throws Exception {
        //����ҵ�񷽷�,���Ҿ������
	    user = userService.findUserByUserID(user.getUserID());
		
		return "viewSUCCESS";
	}
	
	
	

	
	/**
	 * �޸���Ϣ����
	 * @return
	 * 
	 */
	
	public String toUpdate() throws Exception {
		//1.�����û�userID,�õ��û���Ϣ
		user = userService.findUserByUserID(user.getUserID());
		return "toUpdateSUCCESS";
	}
	
	
	
	/**
	 * 
	 * ִ�и��²���
	 * ����û�ѡ���˼������ڷ��������滻ԭ�м��� 
	 * ����û�ûѡ����������ԶԼ������޸� 
	 * ������ͨ���ֶε����ݶ������� ջ��(user����)
	 * �����ϴ����ֵ����ݱ�����upload��
	 */

	
	public String doUpdate() throws Exception {

		//1.�ж��Ƿ�����˼��� 
		if(upload==null){
			//˵��û�и��¼���,ֱ�ӽ������ֶθ��º�Ľ�����浽���ݿ�
			userService.update(user,false);//false��������û���Ϣʱ��������path,filename�ֶ�   update s_user set ...... (����path,filename)
		}else{
			//˵�������˼���
			//1.�����ݿ��ѯ�����û��ļ�����Ϣ
			User u  = userService.findUserByUserID(user.getUserID());
			//2.�õ������ľ���·��,��ɾ��ԭ�м���
			String absPath = ServletActionContext.getServletContext().getRealPath(u.getPath());
			File file = new File(absPath);
			file.delete();
			
			//3.�ϴ�һ���µļ��� 
			String diskFileName = UUID.randomUUID().toString();
			String path = "/WEB-INF/upload/"+diskFileName;//���������ݿ��е�·��
			absPath = ServletActionContext.getServletContext().getRealPath(path);
			File newFile = new File(absPath);
			FileUtils.copyFile(upload, newFile);
			
			//4. user�����filename,path�����µ�ȡֵ
			user.setPath(path);
			user.setFilename(uploadFileName);//��ʵ�ϴ�ʱ���û�ѡ����ļ���
			
			//5.�����û���Ϣ
			userService.update(user,true);//���¼�������ֶ�
		}
		return "doUdateSUCCESS";
		}
		      
}
