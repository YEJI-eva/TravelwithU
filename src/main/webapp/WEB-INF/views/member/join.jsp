<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>Welcome-Road Trip with YEJI</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="css/main.css" />


<script>
// 사용자 입력값 검증
function check() {
	if (frm.id.value.length < 3) {
		alert('ID는 세글자 이상 사용가능합니다.');
		frm.id.select();
		return false;
	}
	if (frm.passwd.value.length == 0) {
		alert('password는 필수 입력 항목입니다.');
		frm.passwd.focus();
		return false;
	}
	if (frm.name.value.length == 0) {
		alert('name은 필수 입력 항목입니다.');
		frm.name.focus();
		return false;
	}
	if (frm.email.value.length == 0) {
		alert('email은 필수 입력 항목입니다.');
		frm.email.focus();
		return false;
	}
	if (document.frm.passwd.value != document.frm.passwd2.value) {
		alert('password 입력값이 서로 다릅니다.');
		document.frm.passwd.select();
		return false;
	}
	if (frm.email.value != frm.email2.value) {
		alert('email 입력값이 서로 다릅니다.');
		document.frm.email.select();
		return false;
	}
	return true;
}

// 새로운 브라우저를 띄우고 아이디 중복 확인해주는 기능
//function winOpen() {
	// var inputId = document.getElementById('id').value;
//	var inputId = document.frm.id.value;
	// id입력값이 공백이면 '아이디를 입력하세요' focus 주기
//	if (inputId == '') { // inputId.length == 0
	//	alert('ID를 입력하세요.');
		//document.frm.id.focus();
		//return;
	//}
	// 새로운 자식창(브라우저) 열기
	// open() 호출한쪽은 부모창
	// open()에 의해 새로열린 창은 자식창
	// 부모-자식 관계가 있음. 자식창의 데이터를 부모창으로 가져올 수 있음.
	//var childWindow = window.open('joinIdDupCheck?userid='+inputId, '', 'width=400, height=200'); // ? = 파라미터값 찾아옴
	//childWindow.document.write('입력한 아이디: '+inputId+'<br>');
//}
</script>
</head>

<body>

	<body class="subpage">

		<!-- Header -->
			<header id="header" class="alt">
				<div class="logo"><a href="main">Road Trip <span> with YEJI<span></a></div>
				<a href="#menu"><span>Menu</span></a>
			</header>

		<!-- Nav -->
			<jsp:include page="../include/nav_center.jsp" />	
		<!-- Main -->
<h2>Join Us</h2>

<div id ="signpage"  style="text-align: center;margin-left: 30em;margin-right: 30em;" padding: 0.5em;">			

<form name="frm"  action="memberJoin" method="post" id="join">
	<p>회원의 개인 정보는 영리 목적으로 사용되지 않습니다</p>

<fieldset>
	<legend>Basic Info</legend>
	
	<label>User ID</label><br><input type="text" id="id" name="id" autofocus="autofocus"><br><span id="idMessage"></span>
	<!-- <input name="" style="margin-top: 1em;"type="button" value="ID 중복확인" class="dup" onclick="winOpen();"><br> -->
	<label>Password</label> <input type="password" id="passwd1" name="passwd"><br>
	<label>Retype Password</label><br> <input type="password" id="passwd2" name="passwd2"><br><span id="pwMessage"></span>
	<label>Name</label> <input name="name" type="text" class="nick"><br>
	<label>E-Mail</label> <input name="email" type="email" class="email" ><br>
	<label>Retype E-mail</label> <input name="email2" type="email" class="email"><br>
</fieldset>


<fieldset>
	<legend>Optional</legend>
	<label>Address</label> <input name="address" type="text" class="address"><br>
	<label>Phone Number</label> <input name="tel" type="text" class="phone"><br>
	<label>Mobile Phone Number</label> <input name="mtel" type="text" class="mobile"><br>
</fieldset>

<div class="clear"></div>
<div id="buttons"style="margin-top: 2em;">
<input type="submit" value="회원가입" class="submit" > 
<input name="" type="button" value="Cancel" class="cancel">
</div>

</form> 
    

    <div class="clear"></div>
    
</div>
	<script src="scripts/jquery-3.4.1.min.js"></script>
	<script>
	$('#id').keyup(function () {
		var id = $(this).val();

		$.ajax({
			url: 'joinIdDupCheck',
			data: {id: id},
			success: function (data) {
				console.log(typeof data);
				console.log(data);
				
			idDupMessage(data);
			}
		})
	});
	
	function idDupMessage(isIdDup){
		if (isIdDup) {
			$('span#idMessage').html('중복된 아이디').css('color', 'red');
		} else {
			$('span#idMessage').html('사용가능 아이디').css('color', 'green');
		}
		
	}
	</script>

	<script>
	$('#passwd2').keyup(function () {
		passwordCheck();
	});
	function passwordCheck() {
		if (frm.passwd.value == frm.passwd2.value) {
			$('span#pwMessage').html('패스워드 일치').css('color', 'green');		
		} else {
			$('span#pwMessage').html('패스워드 불일치').css('color', 'red');
		}
	}
	</script>

</body>
<!-- Scripts -->
			<script src="js/jquery.min.js"></script>
			<script src="js/jquery.scrolly.min.js"></script>
			<script src="js/jquery.scrollex.min.js"></script>
			<script src="js/skel.min.js"></script>
			<script src="js/util.js"></script>
			<script src="js/main.js"></script>
			


</html>   