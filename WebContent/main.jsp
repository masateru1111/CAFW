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
<title>メイン画面</title>
</head>
<body>
	<h1>メイン</h1>
	<p>
		<a href="/CAFE/UpdateUserServlet">情報変更</a>
	</p>
	<p>
		<a href="/CAFE/UpdateCardServlet">クレジットカード情報変更</a>
	</p>
	<p>
		<a href="/CAFE/LogoutServlet">ログアウト</a>
	</p>
	<br>
	<p><a href = "/CAFE/CartCheckServlet">カート内：${cart.sumOfNumberOfItems }</a></p>
	<br>
	<br>

	<!-- メイン -->
	<h3>メニュー</h3>
	<%
	    while (it.hasNext()) {
	%>
	<%
	    Item item = it.next();
	%>
	<form action="/CAFE/CartAddServlet" method="post">
		<table border="1" id="itemList">
			<tr>
				<td colspan="2"><img src="<%=item.getImgPath()%>" width="400px"
					height="300px"></td>
				<!-- colspanで2つの枠を1つにまとめる  -->
			</tr>
			<tr>
				<td style="text-align: center"><%=item.getItemName()%></td>
				<td style="text-align: center">&yen; <%=item.getPrice()%></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center"><%=item.getExplanation()%></td>
			</tr>
			<tr>
				<td style="text-align: center"><input type="hidden"
					name="itemId" value=<%=item.getItemId() %>><select name="numberOfItems">
						<option value="">選択してください</option>
						<%
						    for (int i = 1; i <= 10; i++) {
						%>
						<option value=<%=i%>><%=i%></option>
						<%
						    }
						%>
				</select></td>
				<td style="text-align: center"><input type="submit"
					value="カートに追加"></td>
			</tr>
		</table>
		<br><br>
		</form>
		<%
		    }
		%>
	
</body>
</html>