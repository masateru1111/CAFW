<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>情報変更</title>
</head>
<body>
	<h1>情報変更</h1>

	<form action="/CAFE/UpdateUserServlet" method="post">
		<table>
			<tr>
				<td><font color="red">${vacantMsg}</font></td>
			</tr>
			<tr>
				<td><font color="red">${errorMsg}</font></td>
			</tr>
			<tr>
				<td>パスワード</td>
				<td><input type="password" id="password" name="password" value=${user.password }></td>
			</tr>
			<tr>
				<td><font color="red">${passError}</font></td>
			</tr>
			<tr>
				<td>パスワード(確認)</td>
				<td><input type="password" id="confirmPass" name="confirmPass"value=${user.password }></td>
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
				<td><input type="text" name="email"value=${user.email }></td>
			</tr>
			<tr>
				<td><font color="red">${emailError}</font></td>
			</tr>
			<tr>
				<td>メールアドレス(確認)</td>
				<td><input type="text" name="confirmEmail"value=${user.email }></td>
			</tr>
			<tr>
				<td><font color="red">${confirmEmailError}</font></td>
			</tr>
			<tr>
				<td>電話番号(ハイフンなし)</td>
				<td><input type="text" name="cellphone"value=${user.cellphone }></td>
			</tr>
			<tr>
				<td><font color="red">${cellphoneError}</font></td>
			</tr>
			<tr>
				<td>郵便番号(ハイフンなし)</td>
				<td><input type="text" name="postalCode"value=${user.postalCode }></td>
			</tr>
			<tr>
				<td><font color="red">${postalCodeError}</font></td>
			</tr>
			<tr>
				<td>住所</td>
				<td><input type="text" name="address"value=${user.address }></td>
			</tr>
			<tr>
				<td><input type="submit" value="確認"></td>
			</tr>
		</table>
	</form>

	<!-- パスワードを表示させるためのスクリプト文 -->
	<script>
		function checkPassword(e) {

			pass = document.getElementById("password");

			pass1 = document.getElementById("confirmPass");

			if (e.checked) {

				pass.setAttribute("type", "text");

				pass1.setAttribute("type", "text");

			} else {

				pass.setAttribute("type", "password");

				pass1.setAttribute("type", "password")

			}

		}
	</script>

</body>
</html>