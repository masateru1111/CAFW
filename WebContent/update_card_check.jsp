<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>クレジットカード情報確認</title>
</head>
<body>
	<h1>クレジットカード情報確認</h1>


		<table>
			<tr>
				<td><font color="red">${errorMsg}</font></td>
			</tr>
			<tr>
				<td>種類</td>
				<td>${user.cardVariety }</td>
			</tr>
			<tr>
				<td>クレジットカード番号</td>
				<td>${user.cardNumber }</td>
			</tr>
			<tr>
				<td>期限(月)/(年)</td>
				<td>${user.expirationDate }</td>
			</tr>

			<tr>
				<td>クレジットカード名義</td>
				<td>${user.cardHolder }</td>
			</tr>
			<tr>
				<td>セキュリティコード</td>
				<td>${user.cardSecurity }</td>
			</tr>

			<tr>
				<td> <a href="/CAFE/UpdateCardCompletionServlet">登録</a></td>
				<td><a href="/CAFE/UpdateCardServlet">戻る</a></td>
			</tr>
		</table>

	
</body>
</html>