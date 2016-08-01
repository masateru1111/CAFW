<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登録確認画面</title>
</head>
<body>
	<table>
		<tr>
			<td>名前</td>
			<td>${user.userName }</td>
		</tr>
		<tr>
			<td>フリガナ</td>
			<td>${user.userKana }</td>
		</tr>
		<tr>
			<td>パスワード</td>
			<td>${user.password }</td>
		</tr>
		<tr>
			<td>メールアドレス</td>
			<td>${user.email }</td>
		</tr>
		<tr>
			<td>電話番号</td>
			<td>${user.cellphone }</td>
		</tr>
		<tr>
			<td>郵便番号</td>
			<td>${user.postalCode }</td>
		</tr>
		<tr>
			<td>住所</td>
			<td>${user.address }</td>
		</tr>
		<tr>
			<td><a href="/CAFE/CreateUserCompletionServlet">登録</a></td>
			<td><a href="/CAFE/CreateUserServlet">戻る</a></td>
		</tr>

	</table>

</body>
</html>