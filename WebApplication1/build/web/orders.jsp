<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.util.*" %>
<%@ page import="com.Order.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String username = (String)session.getAttribute("username");
List<Map<String,Object>> list =(List<Map<String,Object>>) request.getAttribute("listOrder");


	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Order Preview</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	function searchOrder(){
		var th = document.form2;
		th.action="<%=path%>/servlet/OrderAction?action_flag=search";
		th.submit();
	}
	
	function selectAll(flag){
		
		var ids = document.getElementsByName("ids");
		for(var i = 0 ; i < ids.length ; i++){
			ids[i].checked = flag;
		}
	
	}
	
	function getSelectedCount(){
	
		var ids = document.getElementsByName("ids");
		var count = 0;
		for(var i = 0 ; i < ids.length ; i++)
		{
						
			ids[i].checked==true?count++:0;					
		}
		return count;
	
	}
	
	function del(){
	
		if(getSelectedCount()==0){
			
			alert("Select at least one item!");
			return;
		
		}
		
		var th = document.form1;
		th.action="<%=path%>/servlet/OrderAction?action_flag=del";
		th.submit();		
	
	}
	
	function getSelectedValue(){
		var ids = document.getElementsByName("ids");
		
		for(var i = 0 ; i < ids.length ; i++)
		{
						
			if(ids[i].checked){
				return ids[i].value;
			}				
		}
		
	}
	
	function view(){
	
		if(getSelectedCount()<1){
			
			alert("Select at least one item!");
			return;
		
		}else if(getSelectedCount()>1){
			alert("Select only one item!");
			return;
		}
		
		var th = document.form1;
		th.action="<%=path%>/servlet/OrderAction?action_flag=view&oid="+getSelectedValue();
		th.submit();		
	
	}
	
	
	
	</script>

  </head>
  
  <body>
   <div>

   <table width=60% align="center">
   <tr>
   		<td align="left"><font size=2>welcome!<%=username%><br><a href="javascript:logout();"></a></font></td>
   </tr>
   	
   	
   	<tr>
   		<td height=50> </td>
   	</tr>
   	<tr>
   		<td> RESULT</td>
                <br>
                <br>
   	</tr>
   	
   	<tr>
   	 	<td >
   	 	<form name="form1" action="" method="post">
   		<table border=1 width=100%>
   			<tr align="center">
   				<td width=10%><input type="checkbox" name="checkall" onclick="javascript:selectAll(this.checked);" /></td>
   				<td width=15%>username</td>
   				<td width=15%>total</td>
                <td width=15%>time</td>
   			</tr>
   			<%
   			if(list!=null && !list.isEmpty()){
   			
   				for(Map<String,Object> map :list){%>
   			
   				<tr align="center">
   				<td width=10%><input type="checkbox" name="ids" value="<%=map.get("oid") %>"/></td>
   				<td width=15%><%=map.get("username") %></td>
   				<td width=15%><%=map.get("total") %></td>
                <td width=15%><%=map.get("time") %></td>
                              
   				
   				<%}
   			
   			
   			}else{%>
   			
   			<tr align="center">
   				<td width=10%><input type="checkbox" name="" /></td>
   				<td width=15%></td>
   				<td width=15%></td>
                <td width=15%></td>
                               
   			
   			</tr><%
   			
   			}   			
   			 %>
   			
   	
   			
   		
   		</table>   		
   		</form>
   		</td>
   	
   	</tr>
   	
   	<tr>
   		<td>

   			<button type="button" onclick="javascript:view();" >view</button>
   		
   		</td>
   	</tr>
   	
        
        

        
        <tr>
                <td colspan="2" align="center">
   				
                <br>
                <br>
   			<button type="button" style="width: 65px; height: 25px;"onclick="javascript:location.href='<%=path %>/Cmain.jsp'">finish</button>
               
   		</td>   				
        </tr>    
   </table>

   
   
   </div>
   
   
  </body>
</html>
