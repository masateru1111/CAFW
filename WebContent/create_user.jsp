
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規登録</title>
</head>
<body>
	<h1>新規登録</h1>

	<form action="/CAFE/CreateUserServlet" method="post">
		<table>
			<tr>
				<td><font color="red">${vacantMsg}</font></td>
			</tr>
			<tr>
				<td><font color="red">${errorMsg}</font></td>
			</tr>
			<tr>
				<td>名前</td>
				<td><input type="text" name="userName" value=${userName }></td>
			</tr>
			<tr>
				<td><font color="red">${nameError}</font></td>
			</tr>
			<tr>
				<td>フリガナ</td>
				<td><input type="text" name="userKana"value=${userKana }></td>
			</tr>
			<tr>
				<td><font color="red">${kanaError}</font></td>
			</tr>
			<tr>
				<td>パスワード</td>
				<td><input type="password" id="password" name="password"></td>
			</tr>
			<tr>
				<td><font color="red">${passError}</font></td>
			</tr>
			<tr>
				<td>パスワード(確認)</td>
				<td><input type="password" id="confirmPass" name="confirmPass"></td>
			</tr>
			<tr>
				<td><font color="red">${confirmPassError}</font></td>
			</tr>
			<tr>
				<td>パスワード表示</td>
				<td><input type="checkbox" onclick="checkPassword(this)"></td>
			</tr>

			<tr>
				<td>メールアドレス</td>
				<td><input type="text" name="email"value=${email }></td>
			</tr>
			<tr>
				<td><font color="red">${emailError}</font></td>
			</tr>
			<tr>
				<td>メールアドレス(確認)</td>
				<td><input type="text" name="confirmEmail" value=${confirmEmail }></td>
			</tr>
			<tr>
				<td><font color="red">${confirmEmailError}</font></td>
			</tr>
			<tr>
				<td>電話番号(ハイフンなし)</td>
				<td><input type="text" name="cellphone" onKeyup="this.value=this.value.replace(/[^0-9]+/,'')" maxlength="11" value=${cellphone }></td>
			</tr>
			<tr>
				<td><font color="red">${cellphoneError}</font></td>
			</tr>
			<tr>
				<td>郵便番号(ハイフンなし)</td>
				<td><input type="text" name="postalCode" onKeyup="this.value=this.value.replace(/[^0-9]+/,'')" maxlength="7" value=${postalCode }></td>
			</tr>
			<tr>
				<td><font color="red">${postalCodeError}</font></td>
			</tr>
			<tr>
				<td>住所</td>
				<td><input type="text" name="address"value=${address }></td>
			</tr>
			<tr>
				<td><input type="submit" value="確認"></td>
			</tr>
		</table>
	</form>
	<script type="text/javascript"src="./js/script.js"></script>
</body>
</html>