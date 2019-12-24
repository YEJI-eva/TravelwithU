<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Welcome-Road Trip with YEJI</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="css/main.css" />
	</head>
	<body class="subpage">

		<!-- Header -->
			<header id="header" class="alt">
				<div class="logo"><a href="main">Road Trip <span> with YEJI<span></a></div>
				<a href="#menu"><span>Menu</span></a>
			</header>

		<!-- Nav -->
			<jsp:include page="../include/nav_center.jsp" />	
		<!-- Main -->
			<div id="main" class="container">

				<!-- Content -->
					<h2>LOGIN</h2>
				<form action="memberLogin" method="post" id="join">
 				<div class ="inner">
				<div class="6u 12u$(small)">
				<form action="#" method="post">

				<div class="field half first">
				<fieldset>
				
				<label for="name">ID</label>
				<input type="text" name="id" placeholder="아이디" autofocus="autofocus">
				</div>
				<div class="field half">
				<label for="passwd">PASSWORD</label>
				<input type="password" name="passwd" placeholder="비밀번호">
				</div>
				<label>Remember Me</label>
				<input type="checkbox" name="rememberMe" ><br>
				<ul class="actions">
				<li><input value="로그인" class="button alt" type="submit">
				<input type="reset" value="초기화" class="cancel"></li>
				
				</ul>
				</fieldset>
				</form>
						
							<h3>Sem turpis amet semper</h3>
							<p>Nunc lacinia ante nunc ac lobortis. Interdum adipiscing gravida odio porttitor sem non mi integer non faucibus ornare mi ut ante amet placerat aliquet. Volutpat commodo eu sed ante lacinia. Sapien a lorem in integer ornare praesent commodo adipiscing arcu in massa commodo lorem accumsan at odio massa ac ac. Semper adipiscing varius montes viverra nibh in adipiscing blandit tempus accumsan.</p>
						</div>
					</div>

				<hr class="major" />

				<!-- Elements -->
					<h2 id="elements">Elements</h2>
					<div class="row 200%">
						
					</div>

			</div>

		<!-- Footer -->
			<footer id="footer">
				<div class="inner">
					
					<ul class="icons">
						<li><a href="#" class="icon round fa-twitter"><span class="label">Twitter</span></a></li>
						<li><a href="#" class="icon round fa-facebook"><span class="label">Facebook</span></a></li>
						<li><a href="#" class="icon round fa-instagram"><span class="label">Instagram</span></a></li>
					</ul>

					<div class="copyright">
						&copy; Untitled. Design: <a href="https://templated.co">TEMPLATED</a>. Images: <a href="https://unsplash.com">Unsplash</a>.
					</div>

				</div>
			</footer>

		<!-- Scripts -->
			<script src="js/jquery.min.js"></script>
			<script src="js/jquery.scrolly.min.js"></script>
			<script src="js/jquery.scrollex.min.js"></script>
			<script src="js/skel.min.js"></script>
			<script src="js/util.js"></script>
			<script src="js/main.js"></script>

	</body>
</html>