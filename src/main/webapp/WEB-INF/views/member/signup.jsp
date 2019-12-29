<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html> 
	<head>
		<title>YEJI's Portfolio</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<!--[if lte IE 8]><script src="js/html5shiv.js"></script><![endif]-->
		<script src="js/jquery.min.js"></script>
		<script src="js/skel.min.js"></script>
		<script src="js/skel-layers.min.js"></script>
		<script src="js/init.js"></script>
		
			<link rel="stylesheet" href="../css/skel.css" />
			<link rel="stylesheet" href="../css/style.css" />
			<link rel="stylesheet" href="../css/style-xlarge.css" />
		
<script>
// 새로운 브라우저를 띄우고 아이디 중복확인해주는 기능
	function winOpen() {
	//var inputId = document.getElementById('id').value
	var inputId = document.frm.id.value;
	// id입력값이 공백이면 '아이디입력하세요' 포커스주기
	if (inputId == '') { // inputId.length == 0
		alert('아이디를 입력하세요.');
		document.frm.id.focus();
		return;
	}
	// 새로운 자식창(브라우저) 열기
	// open()호출한쪽은 부모창
	// open()에 의해 새로열린 창은 자식창
	// 부모-자식 관계가 있음.
	// 자식창의 데이터를 부모창으로 가져올수 있음.
	var childWindow = window.open('joinIdDupCheck.jsp?userid=' + inputId, '', 'width=400,height=300');
	//childWindow.document.write('입력한 아이디: ' + inputId + '<br>');
}

</script>
</head>
		
	</head>
	
	<body id="signup">

			<!-- Header -->
		<jsp:include page="../include/header.jsp" />
		
		<section id="banner">
				<div class="inner">
					<h2>WELCOME!</br></h2>
					<p>Join us</p>
					<ul class="actions">
						
					</ul>
				</div>
			</section>
<div id ="signpage"  style="margin-right:50em; margin-left=:50em; padding: 0.5em;">			
<article>
    
<form name="frm" id="signup" action="signupProcess.jsp" method="post" onsubmit="return check();">    

<fieldset>
	<legend></br>Basic Info</legend>
	<label>User ID</label> <input name="id" type="text" class="id" id="id" > <br>
	<input type="button" value="ID 중복확인" class="dup" onclick="winOpen();"><br></br>
	<label>Password</label> <input name="passwd" type="password" class="pass"><br>
	<label>Retype Password</label> <input name="passwd2" type="password" class="pass"><br>
	<label>Name</label> <input name="name" type="text" class="nick"><br>
	<label>E-Mail</label> <input name="email" type="email" class="email" ><br>
	<label>Retype E-mail</label> <input name="email2" type="email" class="email"><br>
</fieldset>

<fieldset>
	<legend>Optional</legend>
	<label>Address</label> <input name="address" type="text" class="address"><br>
	<label>Phone Number</label> <input name="tel" type="tel" class="phone"><br>
	<label>Mobile Phone Number</label> <input name="mtel" type="tel" class="mobile"><br>
</fieldset>

<div class="clear"></div><br>
<div id="buttons"><br>
<input type="submit" value="Sign up" class="submit"> <input name="" type="button" value="Cancel" class="cancel"> <br>
</div>

</form> 
    
</article>   
</div> 
    <div class="clear"></div>
    
</body>
</html>