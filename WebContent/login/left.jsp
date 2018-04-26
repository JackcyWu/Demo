<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>菜单</title>
<link href="${pageContext.request.contextPath}/css/left.css" rel="stylesheet" type="text/css">
</head>
<body>
<table width="100" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="12"></td>
  </tr>
</table>
<table width="100%" border="0">
  <tr>
    <td>
<div class="dtree">

	<a href="javascript: d.openAll();">展开所有</a> | <a href="javascript: d.closeAll();">关闭所有</a>
	<link rel="StyleSheet" href="${pageContext.request.contextPath}/css/dtree.css" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree.js"></script>
	<script type="text/javascript">
		<!--
		d = new dTree('d');
		d.add(0,-1,'系统菜单树');  // 0代表当前节点编号,-1代表根节点的编号,系统菜单为显示的文本,第四个参数代表跳转的地址,第六个参数代表在哪个区域中显示
		d.add(2,0,'员工管理','${pageContext.request.contextPath}/login/welcome.jsp','','mainFrame');
	    d.add(4,0,'薪资管理','${pageContext.request.contextPath}/login/welcome.jsp','','mainFrame');
		
		//子目录添加
		d.add(3,2,'用户管理','${pageContext.request.contextPath}/userAction_find.action','','mainFrame');
		 d.add(5,4,'小时工资','${pageContext.request.contextPath}/user/list.jsp','','mainFrame');
		
		document.write(d);
		//-->
	</script>
</div>	</td>
  </tr>
</table>
</body>
</html>
