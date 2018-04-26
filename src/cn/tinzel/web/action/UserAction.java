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

	//模型驱动
	private User user = new User();	
	public User getModel() {
		return user;
	}
	
	
	//引入业务逻辑
		private UserService userService = new UserServiceImpl();
	

		
	//---------------登录
		
	@InputConfig(resultName="loginINPUT")	
    public String login() throws Exception {
    	//1.调用业务方法，判断用户名，密码的有效性
    	User loginedUser = userService.login(user.getLogonName(),user.getLogonPwd());
    	if(loginedUser!=null){
			//说明登录 成功
    		ServletActionContext.getRequest().getSession().setAttribute("user", loginedUser);
			return "loginSUCCESS";
    	}else {
    		this.addActionError(this.getText("loginfail"));//国际化
			return LOGIN;
    	}
    }
	
	/**
	 * =========================
	 * 添加用户，并实现简历上传
	 * 
	 * 1.接收参数,交给了ModelDriven拦截器,将add.jsp页面中文本框的取值保存到了栈顶(User对象)
	 * 2.文件上传(简历上传),fileUpload拦截器会将 <s:file name="upload" size="30" id="userAction_save_do_upload"/>
	 *   框中的值放入具有一定规范的属性中()
	 *   private File upload;
	 *   private String uploadContextType;
	 *   private String uploadFileName;
	 *   生成set();
	 */
    
    
    @InputConfig(resultName="saveINPUT")
    public String save() throws Exception {
    	//1.判断是否上传了简历信息
		if(upload!=null){
			//说明上传简历 了，就要保存   /WEB-INF/upload/
			String absPath = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload/");//带有路径
			File file = new File(absPath);
			if(!file.exists()){
				file.mkdirs();
			}
			
			//保存文件
			String uuid = UUID.randomUUID().toString();//文件名
			File destFile = new File(absPath+File.separator+uuid);//此时文件名就是uuid,可以防文件名重名问题，以及更安全
			FileUtils.copyFile(upload, destFile);
			
			//设置filename,path的取值
			user.setFilename(uploadFileName);//设置真实文件名
			user.setPath("/WEB-INF/upload/"+uuid);//因为服务器Tomcat地址可能会发生改变，而数据库中存相对地址，可以随时获取绝对路径
		}
		
	    //2.不管有没有上传简历 ，都要保存用户的基本信息，到数据库中
		//调用业务方法，保存用户信息
		userService.save(user);
    	
    	return "saveSUCCESS";
    }
    
    private File upload;
    private String uploadContextType;     //文件格式
    private String uploadFileName;        //文件名
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
	 * 组合条件查询,查询条件都直接放在栈顶的Model对象中
	 */
	
	public String find() throws Exception {
		//调用业务方法,查询一个List集合
        list = userService.findUsersByMultiSelect(user);
		return "findSUCCESS";
	}
	//当jsp页面用到getList()方法返回结果时,find()方法已经执行过了
	private List<User> list;   //action的属性如果具有get方法，JSP页面就可以直接使用OGNL表达式取值
	public List<User> getList() {
		return list;
	}
	
	
	
	/**
	 * 删除指定用户  ?  UserID=1    ------>值栈的栈顶是model对象
	 * 
	 */
	
	public String delete() throws Exception {
        //1.根据userID查找指定的user对象
		User u = userService.findUserByUserID(user.getUserID());
		if(u!=null && u.getFilename()!=null && u.getFilename().trim().length()>0){
			//说明用户上传了简历
			//就要删除简历
			String relativePasth = u.getPath(); //得到用户的相对路径
			String absPath = ServletActionContext.getServletContext().getRealPath(relativePasth);  //绝对路径
		    //构造文件对象,准备删除这个文件
			File file = new File(absPath);
			file.delete();
		}
		
		userService.deleteUserByUserID(user.getUserID());
		
		return "deleteSUCCESS";
	}
	
	
	
	
	/**
	 * 查看详情---------》userID=xxx--------》栈顶(Model对象(user中具有一个userID))-------此时超链接的参数就放在了栈顶对象
	 * 
	 */
	
	public String view() throws Exception {
        //调用业务方法,查找具体对象
	    user = userService.findUserByUserID(user.getUserID());
		
		return "viewSUCCESS";
	}
	
	
	

	
	/**
	 * 修改信息界面
	 * @return
	 * 
	 */
	
	public String toUpdate() throws Exception {
		//1.根据用户userID,得到用户信息
		user = userService.findUserByUserID(user.getUserID());
		return "toUpdateSUCCESS";
	}
	
	
	
	/**
	 * 
	 * 执行更新操作
	 * 如果用户选择了简历，在服务器端替换原有简历 
	 * 如果用户没选择简历，忽略对简历的修改 
	 * 对于普通表单字段的内容都保存在 栈顶(user对象)
	 * 对于上传文字的内容保存在upload中
	 */

	
	public String doUpdate() throws Exception {

		//1.判断是否更新了简历 
		if(upload==null){
			//说明没有更新简历,直接将其它字段更新后的结果保存到数据库
			userService.update(user,false);//false代表更新用户信息时，不更新path,filename字段   update s_user set ...... (除了path,filename)
		}else{
			//说明更新了简历
			//1.从数据库查询最新用户的简历信息
			User u  = userService.findUserByUserID(user.getUserID());
			//2.得到简历的绝对路径,并删除原有简历
			String absPath = ServletActionContext.getServletContext().getRealPath(u.getPath());
			File file = new File(absPath);
			file.delete();
			
			//3.上传一份新的简历 
			String diskFileName = UUID.randomUUID().toString();
			String path = "/WEB-INF/upload/"+diskFileName;//保存在数据库中的路径
			absPath = ServletActionContext.getServletContext().getRealPath(path);
			File newFile = new File(absPath);
			FileUtils.copyFile(upload, newFile);
			
			//4. user对象的filename,path设置新的取值
			user.setPath(path);
			user.setFilename(uploadFileName);//真实上传时，用户选择的文件名
			
			//5.保存用户信息
			userService.update(user,true);//更新简历相关字段
		}
		return "doUdateSUCCESS";
		}
		      
}
