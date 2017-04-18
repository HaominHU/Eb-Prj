<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String username = (String)session.getAttribute("username");
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<base href="<%=basePath%>">
	
	<title>Add product</title>
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">	
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
function dosubmit(){
	var th = document.form1;
        if(th.bookprice.value<0){
            
            alert("wrong price");  
            return ; 
         }
        
	th.action="<%= path%>/servlet/CProductAction?action_flag=add";
	th.submit();

}

</script>
  </head>
  
  <body>
	<div align="center">
	
		<table width=70% style="margin:auto;">
			<tr><td align="center" height=150 valign="bottom">New book info</td></tr>
			<tr>
				<td>
                                    <form id="form1" name="form1" action="" method="post" >        
                                        
					<table border=1 style="margin:auto">
						<tr >
							<td>Book name</td>
							<td><input type="text" name="bookname" id="bookname"/></td>
                                                        <td>Book author</td>
							<td><input type="text" name="bookauthor" id="bookauthor"/></td>
							<td>Book price</td>
							<td><input type="text" name="bookprice" id="bookprice"/></td>
						</tr>
						<tr>
							<td>book introduction</td>
							<td colspan="3"><input type="text" name="bookintro" id="bookintro"/></td>
                                                        
						</tr>
                                                <tr>
							<td>Type</td>
                                                        <td>
                                                            <select name="bookkind">
                                                                <option value="novel">Novel</option>
                                                                <option value="education">Education</option>
                                                                <option value="literature">Literature</option>
                                                                
                                                            </select>

                                                        </td>
							
						</tr>
					</table> 
                                            <td><input type = "hidden" name = "bookseller" value = "<%=username%>" readonly/></td>
					</form>   				
				
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<button type="button" onclick="javascript:dosubmit();">Submit</button>
					<button type="button" onclick="javascript:location.href='main.jsp'">Back</button>
				
				</td>
			</tr>
			
		
		</table>
		
	</div>
  </body>
</html>
