<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<Map<String,Object>> list =(List<Map<String,Object>>) request.getAttribute("listOrder");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>view Order</title>
    
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
   		<td height=50> </td>
   	</tr>
   	<tr>
   		<td> RESULT</td>
   	</tr>
   	
   	<tr>
   	 	<td >
   	 	<form name="form1" action="" method="post">
   		<table border=1 width=100%>
   			<tr align="center">
   				
   				<td width=30%>name</td>
   				<td width=30%>quantity</td>
                                
   			
   			</tr>
   			<%
   			if(list!=null && !list.isEmpty()){
   			
   				for(Map<String,Object> map :list){%>
   			
   				<tr align="center">
   				<td width=30%><%=map.get("bookname") %></td>
   				<td width=30%><%=map.get("bookquantity") %></td>
                              
   				<%}
   			
   			
   			}else{%>
   			
   			<tr align="center">
   				<td width=10%><input type="checkbox" name="" /></td>
   				<td width=30%></td>
   				
   				<td></td>
   			
   			</tr><%
   			
   			}   			
   			 %>
   			</table>   		
   		</form>
   		</td>
   	
   	</tr>
                        
                        
  			<tr>
  				<td align="center">
  					
  					<button type="button" onclick="javascript:history.go(-1)">Back</button>
  				</td>
  			</tr>
  		
  		</table>
  		
  	
  </div>
  </body>
</html>
