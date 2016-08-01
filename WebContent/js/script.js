function checkPassword(obj){
	var pass = document.getElementById("password");
	var pass2 = document.getElementById("confirmPass");
	if(obj.checked){
		pass.setAttribute("type","text");
		pass2.setAttribute("type","text");
	}else{
		pass.setAttribute("type","password");
		pass2.setAttribute("type","password");
	}
	
}