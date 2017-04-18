<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.util.*" %>
<%@ page import="com.Cproduct.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String username = (String)session.getAttribute("username");

List<Map<String,Object>> list =(List<Map<String,Object>>) request.getAttribute("listBook");


String bookName = (String) request.getAttribute("bookName");
if(list==null){
	
	CProductService service = new CProductDao();
	
	list = service.listProduct("");
}
	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Used Book Store</title>
    
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
   <div>
   <table width=60% align="center">
   <tr>
   		<td align="left"><font size=2>welcome，<%=username%><br><a href="javascript:logout();">logout</a></font></td>
                <button type="button" name="" value="" onclick="vieworder()">view order</button>
                <button type="button" name="" value="" onclick="mytrade()">my trade</button>
                <button type="button" name="" value="" onclick="shoppingCart()">shopping cart</button>
                   
   </tr>
   	<tr>
   		<td align="center">
   		<form name = "form2" action="" method="post">
                     
   		<table>
   			<tr>
   				<td colspan="2">search product</td>
   				
   			</tr>
   			<tr>
   				<td >product name</td>
   				<td ><input type="text" name="bookname" value="<%= bookName!=null?bookName:"" %>"/></td>
   				
   			</tr>
   			
   			<tr>
   				<td colspan="2" align="center">
   					<button type="button" onclick="searchProduct()" >search</button>
                                        <button type="button" onclick="rank()" >rank by price</button>	
                                        
   				</td>   				
   			</tr>
                        <tr>
                            <td colspan = "2" align = "center">
                                <button type = "button" onclick = "novel()">novel</button>
                                <button type = "button" onclick = "education()">education</button>
                                <button type = "button" onclick = "literature()">literature</button>
                            </td>
                        </tr>
   		</table> 
                                <td><input type = "hidden" name = "username" value = "<%=username%>" readonly/></td>  
   		</form>	
   			
   		</td>
   	</tr>
   	
   	<tr>
   		<td height=50> </td>
   	</tr>
   	<tr>
   		<td> book</td>
   	</tr>
   	
   	<tr>
   	 	<td >
   	 	<form name="form1" action="" method="post">
   		<table border=1 width=100%>
   			<tr align="center">
   				<td width=10%><input type="checkbox" name="checkall" onclick="javascript:selectAll(this.checked);" /></td>
   				<td width=30%>bookname</td>
   				<td width=30%>price</td>
   				<td>author</td>
                                <td>kind</td>
                                <td>seller</td>
   			
   			</tr>
                        
   			<%
   			if(list!=null && !list.isEmpty()){
   			
   				for(Map<String,Object> map :list){%>
   			
   				<tr align="center">
   				<td width=10%><input type="checkbox" name="ids" value="<%=map.get("bookid") %>"/></td>
   				<td width=30%><%=map.get("bookname") %></td>
   				<td width=30%><%=map.get("bookprice") %></td>
   				<td><%=map.get("bookauthor") %></td>
                                <td><%=map.get("bookkind") %></td>
                                <td><%=map.get("bookseller") %></td>
   				<%}
   			
   			
   			}else{%>
   			
   			<tr align="center">
   				<td width=10%><input type="checkbox" name="" /></td>
   				<td width=30%></td>
   				<td width=30%></td>
                                <td width=30%></td>
   				<td width=30%></td>
   				<td></td>
   			
   			</tr><%
   			
   			}   			
   			 %>
   			
   	
   			
   		
   		</table>
                            
                <td><input type = "hidden" name = "username" value = "<%=username%>" readonly/></td>  
   		</form>
   		</td>
   	
   	</tr>
   	
   	<tr>
   		<td>
   			
   			<button type="button" onclick="javascript:view();" >view</button>
                        <button type="button" onclick="javascript:addToCart();" >addtocart</button>
                        
   		
   		</td>
   	</tr>
   	

   			
   
   
   
   
   </table>
   
   
   
   </div>
   
   
  </body>
</html>

<script type="text/javascript">
        function searchProduct(){
		var th = document.form2;
		th.action="<%=path%>/servlet/CProductAction?action_flag=search";
		th.submit();
	}
        function novel(){
		var th = document.form2;
		th.action="<%=path%>/servlet/CProductAction?action_flag=nosearch";
		th.submit();
	}
	
        function education(){
		var th = document.form2;
		th.action="<%=path%>/servlet/CProductAction?action_flag=edsearch";
		th.submit();
	}
        function literature(){
		var th = document.form2;
		th.action="<%=path%>/servlet/CProductAction?action_flag=lisearch";
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
			
			alert("please choose one item at least");
			return;
		
		}else if(getSelectedCount()>1){
			alert("please choose only one item");
			return;
		}
		
		var th = document.form1;
		th.action="<%=path%>/servlet/CProductAction?action_flag=view&bookid="+getSelectedValue();
		th.submit();		
	
	}
        
        function addToCart(){
	
		if(getSelectedCount()<1){
			
			alert("please choose one item at least");
			return;
		
		}
		
		var th = document.form1;
		th.action="<%=path%>/servlet/CProductAction?action_flag=addtocart&bookid="+getSelectedValue();
		th.submit();		
	
	}
        
        
        
        function rank(){
            
            var th = document.form2;
		th.action="<%=path%>/servlet/CProductAction?action_flag=rank";
		th.submit();
	}
        
        function mytrade(){
            
            var th=document.form2;
            
            th.action="<%=path%>/servlet/CProductAction?action_flag=mytrade";
            th.submit();
        }
        
        function vieworder(){
            var th=document.form2;
            
            th.action="<%=path%>/servlet/CProductAction?action_flag=vieworder";
            th.submit();
        }
	
	function shoppingCart(){
            var th=document.form2;
            
            th.action="<%=path%>/servlet/CProductAction?action_flag=viewShoppingCart";
            th.submit();
        }
        
        function logout(){
	
	window.location.href="<%=path %>/servlet/LogoutAction?action_flag=logout";
		
	}
</script>