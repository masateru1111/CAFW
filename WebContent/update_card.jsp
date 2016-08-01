<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>クレジットカード情報登録</title>
</head>
<body>
	<h1>クレジットカード情報登録</h1>
	<p><font color ="red">${errorMsg }</font></p>
	<form action="/CAFE/UpdateCardServlet"method="post">
	<table>
		<tr>
			<td>種類</td>
			<td><select name="cardVariety">
					<option value="">選択してください</option>
					<option value="VISA">VISA</option>
					<option value="MUSTER">MUSTER</option>
					<option value="JCB">JCB</option>
			</select></td>
		</tr>
		<tr>
			<td>クレジットカード番号</td>
			<td><input type="password" name="cardNumber" onKeyup="this.value=this.value.replace(/[^0-9]+/,'')" maxlength="16"></td>
		</tr>
		<tr>
			<td><font color ="red">${cardNumError }</font></td>
		</tr>
		<tr>
			<td>期限(月)/(年)</td>
			<td><select name="cardMonth">
					<option value="">選択してください</option>
					<%for(int i=1;i<=12;i++){ %>
					<option value=<%=i %>><%=i %></option>
					<%} %>
			</select> / <select name="cardYear">
					<option value="">選択してください</option>
					<%for(int i=17;i<=25;i++){ %>
					<option value=<%=i %>><%=i %></option>
					<%} %>
			</select></td>
		</tr>
		<tr>
			<td>クレジットカード名義(半角英字で間にスペースを入れてください)</td>
			<td><input type="text"name="cardHolder"value=${cardHolder }></td>
		</tr>
		<tr>
			<td><font color ="red">${cardHolderError }</font></td>
		</tr>
		<tr>
			<td>クレジットカードセキュリティ</td>
			<td><input type="text"name="cardSecurity" onKeyup="this.value=this.value.replace(/[^0-9]+/,'')" maxlength="4" value=${cardSecurity }></td>
		</tr>
		<tr>
			<td><font color ="red">${cardSecurityError }</font></td>
		</tr>
		<tr>
			<td><input type="submit"value="確認"></td>
		</tr>
	</table>
	</form>
</body>
</html>