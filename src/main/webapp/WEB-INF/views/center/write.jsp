<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>board - Road Trip by TEMPLATED</title>
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

		<!-- Content -->
		<!--
			Note: To show a background image, set the "data-bg" attribute below
			to the full filename of your image. This is used in each section to set
			the background image.
		-->
			<section id="post" class="wrapper bg-img" data-bg="banner2.jpg">
				<div class="inner">
					<article class="box">
<div id="wrap">

<% String id = (String) session.getAttribute("id"); %>		
<article>
    
<h1>Notice Write</h1>
<form action="write" method="post" name="frm">
<table id="notice">
<%
if (id == null) { // 로그인 안했을 때
	%>
	<tr>
		<th class="twrite" >NAME</th>
	<td class="left" width="500">
	<input type="text" name="username">
	</td>
	</tr>
	<tr>
  		<th class="twrite">PASSWORD</th>
    	<td class="left">
    	<input type="password" name="passwd">
    	</td>
	</tr> 
	<%
} else { // id != null 로그인 했을 때는 행 하나만 나오게
	%>
	<tr>
		<th class="twrite">ID</th>
	<td class="left" width="500">
	<input type="text" name="username" value="<%=id %>" readonly>
	</td>
	</tr>
	<%
}
%>  
	<tr>
  		<th class="twrite">TITLE</th>
    	<td class="left">
    	<input type="text" name="subject">
    	</td>
	</tr> 
	<tr>
  		<th class="twrite">TEXT</th>
    	<td class="left">
    		<textarea name="content" rows="13" cols="40"></textarea>
    	</td>
	</tr>  
</table>
<div id="table_search">
	<input type="submit" value="글쓰기" class="btn" />
	<input type="reset" value="다시작성" class="btn" />
	<input type="button" value="목록보기" class="btn" onclick="location.href='board'"/>
</div>
</form> 
</article>  
			<footer id="footer">
				<div class="inner">



					<form action="#" method="post">

						
					</form>

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