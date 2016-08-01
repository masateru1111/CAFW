<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,model.Item,java.util.Iterator"%>
<%
    ArrayList<Item> itemList = (ArrayList<Item>) session.getAttribute("itemList");
    Iterator<Item> it = itemList.iterator();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>カート内</title>
</head>
<body>
	<h1>カート内</h1>
	<table>
		<%
		    while (it.hasNext()) {
		%>
		<%
		    Item item = it.next();
		%>
		<tr>
			<td><img src="<%=item.getImgPath() %>" width="100px" height="100px"></td>
			<td><%= item.getItemName() %></td>
			<td>数量：<input type="text" name="numberOfItems" value=<%= item.getNumberOfItems() %>></td>
			<td></td>
		</tr>
		<%
		    }
		%>
	</table>

	<br>
	<br>
	<h3>合計金額：</h3>
	¥ ${sumOfPrice }
</body>
</html>