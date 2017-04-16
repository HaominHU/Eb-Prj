<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Map<String,Object> map = (Map<String,Object>)request.getAttribute("bookDetail");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>view product</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <div align="center">
  		
  		<table width=60% style="margin:auto">
  			
  			<tr>
  				<td height=100>
  					
  				</td>
  			</tr>
  			<tr>
  				<td >
  					book detail
  				</td>
  			</tr>
  			<tr>
  				<td>
  					<table width = 99% border =1 >
	  					<tr align="center">
	  						<td width = 20%>book name</td>
	  						<td width = 30%><%=map.get("bookname") %></td>
	  						<td width = 20%>book price</td>
	  						<td><%=map.get("bookprice") %></td>
	  						
	  					
	  					</tr>
	  					<tr align="center">
	  						<td >book introduction</td>
	  						<td colspan=3><%=map.get("bookintro") %></td>					
	  					</tr>
                                                <tr align="center">
	  						<td >kind</td>
	  						<td colspan=3><%=map.get("bookkind") %></td>					
	  					</tr>
  					</table>
  				</td>
  			</tr>
  			<tr>
  				<td align="center">
  					<!--<button type="button" onclick="javascript:location.href='<%=path %>/main.jsp'">Submit</button>-->
  					<button type="button" onclick="javascript:history.go(-1)">Back</button>
  				</td>
  			</tr>
  		
  		</table>
  		
  	
  </div>
  </body>
</html>
