<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.util.*" %>
<%@ page import="com.Cproduct.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String username = (String)session.getAttribute("username");

List<Map<String,Object>> list =(List<Map<String,Object>>) request.getAttribute("listTrade");


if(list==null){
	
	CProductService service = new CProductDao();
	List<Object> params = new ArrayList<Object>();
        params.add(username);
	list = service.viewMytrade(params);
}
	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Product management</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	function searchProduct(){
		var th = document.form2;
		th.action="<%=path%>/servlet/ProductAction?action_flag=search";
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
			
			alert("At least select one to delete!!!");
			return;
		
		}
		
		var th = document.form1;
		th.action="<%=path%>/servlet/CProductAction?action_flag=del";
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
			
			alert("Select only one item！");
			return;
		
		}else if(getSelectedCount()>1){
			alert("Select only one item！");
			return;
		}
		
		var th = document.form1;
		th.action="<%=path%>/servlet/CProductAction?action_flag=view";
		th.submit();		
	
	}
	
	function logout(){
	
	window.location.href="<%=path %>/servlet/LogoutAction?action_flag=logout";
		
	}
	
	
	</script>

  </head>
  
  <body>
   <div>
   <table width=60% align="center">
   <tr>
   		<td align="left"><font size=2>Welcome，<%=username%><br><a href="javascript:logout();">Logout</a></font></td>
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
   				<td width=10%><input type="checkbox" name="checkall" onclick="javascript:selectAll(this.checked);" /></td>
                                <td width=30%>bookname</td>
   				<td width=30%>author</td>
   				<td width = 30%>price</td>
                                <td width = 30%>kind</td>
   			
   			</tr>
   			<%
   			if(list!=null && !list.isEmpty()){
   			
   				for(Map<String,Object> map :list){%>
   			
   				<tr align="center">
   				<td width=10%><input type="checkbox" name="ids" value="<%=map.get("bookid") %>"/></td>
   				<td width=30%><%=map.get("bookname") %></td>
   				<td width=30%><%=map.get("bookauthor") %></td>
   				<td width=30%><%=map.get("bookprice") %></td>
   				<td width=30%><%= map.get("bookkind")%></td>
                                
   				<%}
   			
   			
   			}else{%>
   			
   			<tr align="center">
   				<td width=10%><input type="checkbox" name="" /></td>
   				<td width=30%></td>
   				<td width=30%></td>
                                <td width = 30%></td>
   				<td></td>
   			
   			</tr><%
   			
   			}   			
   			 %>
   			
   	
   			
   		
   		</table>   		
   		</form>
   		</td>
   	
   	</tr>
   	
   	<tr>
   		<td>
   			<button type="button" onclick="javascript:del();">Delete</button>
   			<button type="button" onclick="javascript:view();" >View</button>
                        <button type="button" onclick="javascript:location.href='<%=path %>/addProduct.jsp'">Add</button>
                        
   		
   		</td>
   	</tr>
   </table>
   
   </div>
   
  </body>
</html>