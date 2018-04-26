<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript">
			function addUser(){
				window.location.href = "${pageContext.request.contextPath}/user/add.jsp";
			}
			
			//删除提示对话框
 		    function del(e) {
				var isConfirm = confirm("您确定是否删除？");
				if(!isConfirm){
					//选择取消     组织事件继续传播
					if(e && e.preventDefault){
						//非IE
						e.preventDefault();
					}else{
						//IE
						window.event.returnValue = false; 
					}
					
				}
			}  
			
		</script>
	</HEAD>
	<body>
		<br>
	<s:form id="Form1" name="Form1" action="userAction_find" theme="simple" method="post">	
		<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
			<TBODY>
		<tr>
			<td class="ta_01" align="center" bgColor="#afd1f3">
				<strong>查 询 条 件</strong>
			</td>
		</tr>
		<tr>
			<td>
				<table cellpadding="0" cellspacing="0" border="0" width="100%">
					<tr>
						<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
							用户姓名
						</td>
						<td class="ta_01" bgColor="#ffffff">
						    <s:textfield name="userName" size="15" id="Form1_userName" cssClass="bg"/>
						</td>
						<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
							性别：
						</td>
						<td class="ta_01" bgColor="#ffffff">
							<s:select list="{'男','女'}" name="sex" id="sex" headerKey="" headerValue="--选择性别--"></s:select>
				
						</td>
			</tr>
				<tr>
					<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
						学历：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<!--headerKey代表下拉列表的提示的取值，headerValue代表下拉列表提示的文本  -->
			    <s:select list="{'博士','硕士','研究生','本科','专科','高中'}" headerKey="" headerValue="--选择学历--" name="education" id="education"></s:select>
						
					</td>
					<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
						是否上传简历
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<s:select name="isUpload" id="isUpload" list="#{'1':'有','2':'无'}" headerKey="" headerValue="--请选择--"></s:select>

					</td>
				</tr>
			<tr>
				<td width="100" height="22" align="center" bgColor="#f5fafe"
					class="ta_01">
				</td>
				<td class="ta_01" bgColor="#ffffff">
					<font face="宋体" color="red"> &nbsp;</font>
				</td>
				<td align="right" bgColor="#ffffff" class="ta_01"><br><br></td>
				<td align="right" bgColor="#ffffff" class="ta_01">
					<button type="submit" id="search" name="search" value="&#26597;&#35810;" class="button_view">查询</button>
						<input type="reset" name="reset" value="&#37325;&#32622;" class="button_view"/>

					</td>
				</tr>
			</table>
		</td>

	</tr>
	<tr>
		<td class="ta_01" align="center" bgColor="#afd1f3">
			<strong>用 户 列 表</strong>
		</TD>
	</tr>
					<tr>
						<td class="ta_01" align="right">
							<button type="button" id="add" name="add" value="&#28155;&#21152;" class="button_add" onclick="addUser()">添加用户
</button>

						</td>
					</tr>
	<tr>
		<td class="ta_01" align="center" bgColor="#f5fafe">
			<table cellspacing="0" cellpadding="1" rules="all"
				bordercolor="gray" border="1" id="DataGrid1"
				style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
				<tr
					style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

					<td align="center" width="18%">
						登录名
					</td>
					<td align="center" width="17%">
						用户姓名
					</td>
					<td align="center" width="8%">
						性别
					</td>
					<td align="center" width="23%">
						联系电话
					</td>
					<td width="11%" align="center">
						学历
					</td>
					<td width="7%" align="center">
						编辑
					</td>
					<td width="7%" align="center">
						查看
					</td>
					<td width="7%" align="center">
						删除
					</td>
				</tr>
			<!--用于遍历集合  value放的是集合名  var变量会在root的栈顶中放一份,context放一份-->		
			<s:iterator value="list" var="user" >
			
				<tr onmouseover="this.style.backgroundColor = 'white'"
					onmouseout="this.style.backgroundColor = '#F5FAFE';">
					<td style="CURSOR: hand; HEIGHT: 22px" align="center"
						width="18%">
					    <s:property value="logonName" /> <!-- 代表从root中取   -->
					   <%--  <s:property value="#user.logonName" /> --%>
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center"
						width="17%">
                        <s:property value="userName" />
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center"
						width="8%">
						<s:property value="sex" />
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center"
						width="23%">
						<s:property value="telephone" />
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center">
						<s:property value="education" />
					</td>
					
					<!-- 编辑 -->
					<td align="center" style="HEIGHT: 22px">		
						<s:a action="userAction_toUpdate" namespace="/">
						    <s:param name="userID" value="%{userID}"></s:param>
 	                        <img src="${pageContext.request.contextPath}/images/i_edit.gif" border="0" style="CURSOR: hand">					
						</s:a>
					</td>
					
					<!-- 查看 -->
					<td align="center" style="HEIGHT: 22px">	
						<s:a action="userAction_view" namespace="/">
						    <s:param name="userID" value="%{userID}"></s:param>
						    <img src="${pageContext.request.contextPath}/images/button_view.gif" border="0" style="CURSOR: hand">
						
						</s:a>
					</td>
					
					<!-- 删除 -->
					<td align="center" style="HEIGHT: 22px">	
						<!-- namespace代表名称空间 -->
						<s:a action="userAction_delete" namespace="/" onclick="del(event)">
						    <s:param name="userID" value="%{userID}"></s:param>
						    <img src="${pageContext.request.contextPath}/images/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand">
						</s:a>
					</td>
				</tr>
				</s:iterator>						
			</table>
				</td>
			</tr>
		</TBODY>
	</table>
   </s:form>
	</body>
</HTML>

