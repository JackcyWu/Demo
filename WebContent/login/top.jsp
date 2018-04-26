<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<html>
	<head>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<style type="text/css">
BODY {
	MARGIN: 0px;
	BACKGROUND-COLOR: #ffffff
}

BODY {
	FONT-SIZE: 12px;
	COLOR: #000000
}

TD {
	FONT-SIZE: 12px;
	COLOR: #000000
}

TH {
	FONT-SIZE: 12px;
	COLOR: #000000
}
</style>
		<link href="${pageContext.request.contextPath}/css/Style.css" rel="stylesheet" type="text/css">
	</HEAD>
	<body onload="startTime()">
		<table width="100%" height="70%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<img width="100%" src="${pageContext.request.contextPath}/images/top_01.jpg">
				</td>

				<td width="100%" background="${pageContext.request.contextPath}/images/top_100.jpg">
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="30" valign="bottom" background="${pageContext.request.contextPath}/images/mis_01.jpg">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="85%" align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							
			<%-- 当前时间为:<%=new Date().toLocaleString() %> --%>		

<!-- 网页中动态的显示日期时间 -->
<font color="#000000"> <span id="nowDateTimeSpan"></span> 
<script language="JavaScript">
function startTime()     
{     
    var today=new Date();//定义日期对象     
    var yyyy = today.getFullYear();//通过日期对象的getFullYear()方法返回年      
    var MM = today.getMonth()+1;//通过日期对象的getMonth()方法返回年      
    var dd = today.getDate();//通过日期对象的getDate()方法返回年       
    var hh=today.getHours();//通过日期对象的getHours方法返回小时     
    var mm=today.getMinutes();//通过日期对象的getMinutes方法返回分钟     
    var ss=today.getSeconds();//通过日期对象的getSeconds方法返回秒     
    // 如果分钟或小时的值小于10，则在其值前加0，比如如果时间是下午3点20分9秒的话，则显示15：20：09     
    MM=checkTime(MM);  
    dd=checkTime(dd);  
    mm=checkTime(mm);     
    ss=checkTime(ss);      
    var day; //用于保存星期（getDay()方法得到星期编号）  
    if(today.getDay()==0)   day   =   "星期日 "   
    if(today.getDay()==1)   day   =   "星期一 "   
    if(today.getDay()==2)   day   =   "星期二 "   
    if(today.getDay()==3)   day   =   "星期三 "   
    if(today.getDay()==4)   day   =   "星期四 "   
    if(today.getDay()==5)   day   =   "星期五 "   
    if(today.getDay()==6)   day   =   "星期六 "   
    document.getElementById('nowDateTimeSpan').innerHTML=yyyy+"年"+MM +"月"+ dd +" " + hh+":"+mm+":"+ss+"   " + day;     
    setTimeout('startTime()',1000);//每一秒中重新加载startTime()方法   
}     
  
function checkTime(i)     
{     
    if (i<10){  
        i="0" + i;  
    }     
      return i;  
} 
</script> </font>


					
							</td>
							<td width="15%">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="16"
											background="${pageContext.request.contextPath}/images/mis_05b.jpg">
											<img
												src="${pageContext.request.contextPath}/images/mis_05a.jpg"
												width="6" height="18">
										</td>
										<td width="155" valign="bottom"
											background="${pageContext.request.contextPath}/images/mis_05b.jpg">
											用户名：
											<font color="blue">${sessionScope.user.logonName }</font>
										</td>
										<td width="10" align="right"
											background="${pageContext.request.contextPath}/images/mis_05b.jpg">
											<img src="${pageContext.request.contextPath}/images/mis_05c.jpg" width="6" height="18">
										</td>
									</tr>
								</table>
							</td>
							<td align="right" width="5%">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</HTML>
