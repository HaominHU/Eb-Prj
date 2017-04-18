<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.util.*" %>
<%@ page import="com.Cproduct.*" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<Map<String,Object>> list =(List<Map<String,Object>>) request.getAttribute("bookCart");
String username = (String)session.getAttribute("username");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>Shopping Cart</title>
    
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
                                <td>
                                    Order Information
  				</td>
  			</tr>
                        
                         <tr>
                                <td>
                                    <HR width="200" color=#987cb9 SIZE="5" /><!-- color颜色，size大小 -->
                                                
                                </td>
                        </tr>
  			<tr>
  				<td >
                                    <form name="form1" action="" method="post">
<!--                                    <table border=1 width=100%>-->
                                        <tr align="center">
                                            <td width=10%><input type="checkbox" name="checkall" onclick="javascript:selectAll(this.checked);" /></td>
                                            <td width=30%>bookname</td>
                                            <td width=30%>price</td>
                                            <td>author</td>
                                            <td>kind</td>
                                            <td>Amount</td>
                                            
                                            
                                        </tr>
                                       
   			<%
                            int sum=0;
   			if(list!=null && !list.isEmpty()){
                                
   				for(Map<String,Object> map :list){%>
   			
                                    <tr align="center">
                                        <td width=10%><input type="checkbox" name="ids" value="<%=map.get("bookid") %>"/></td>
                                        <td width=30%><%=map.get("bookname") %></td>
                                        <td width=30%><%=map.get("bookprice") %></td>
                                        <td><%=map.get("bookauthor") %></td>
                                        <td><%=map.get("bookkind") %></td>
                                        <td><%=map.get("number") %></td>
                                        <td><input type = "hidden" name = "proname" value = "<%=map.get("bookname")%>"readonly/></td>
                                        <td><input type = "hidden" name = "proquantity" value = "<%=map.get("number")%>"readonly/></td>
                                        <td><input type = "hidden" name = "proinv" value = "<%=map.get("bookauthor")%>" readonly/></td>
                                        
                                

   				<%}
   			
   			
   			}else{%>
   			
                                <tr align="center">
                                    <td width=10%><input type="checkbox" name="" /></td>
                                    <td width=30%></td>
                                    <td width=30%></td>
                                    <td></td>
   			
                                </tr><%
   			
   			}   			
   			 %>
                        
                
                                
                                <tr>
                                    <td><input width = 10% type = "hidden" name = "username" value = "<%=username%>"readonly/></td>
                                </tr>
                               
                               
                                
                                <tr>
                                    <td>
                                         <tr>
                                            <td align="center">
                                            <button type="button" onclick="checkOut();">pay</button>
                                            <button type="button" onclick="javascript:history.go(-1)">back</button>
                                            </td>
                                        </tr>
                             
                                    </td>
                                </tr>
                            </form>
                        </td>
                   </tr>

  		
  		</table>
  		
  	
  </div>
  </body>
</html>

<script type="text/javascript">
    function checkOut(){
	
		if(getSelectedCount()<1){
			
			alert("please choose one item at least");
			return;
		
		}
		
		var th = document.form1;
		th.action="<%=path%>/servlet/CProductAction?action_flag=checkout&bookid="+getSelectedValue();
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
</script>