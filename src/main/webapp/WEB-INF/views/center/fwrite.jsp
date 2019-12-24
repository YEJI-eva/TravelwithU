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
<body>
<div id="wrap">
 
			<section id="post" class="wrapper bg-img" data-bg="banner5.jpg">
				<div class="inner">
					<article class="box">

<h1>Notice Write</h1>
<form action="fwrite" method="post" name="frm" enctype="multipart/form-data">
<table id="notice">
	<tr>
		<th class="twrite">NAME</th>
	<td class="left" width="500">
	<input type="text" name="username">
	</td>
	</tr>
	<tr>
		<th class="twrite">ID</th>
	<td class="left" width="500">
	<input type="text" name="username" value="${id}" readonly>
	</td>
	</tr>
	<tr>
  		<th class="twrite">TITLE</th>
    	<td class="left">
    	<input type="text" name="subject">
    	</td>
	</tr> 
	<tr>
  		<th class="twrite">FILE</th>
    	<td class="left">
    		<div id="file_container">
   			 	<input type="file" name="filename">
    		</div>
    		<button type="button" onclick="addFileElement(); ">파일 추가</button>
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
	<input type="submit" value="글쓰기" class="btn"  />
	<input type="reset" value="다시작성" class="btn" />
	<input type="button" value="목록보기" class="btn" onclick="location.href='fnotice';"/>
</div>
</form> 
</article>  
     <div class="clear"></div>
</div>
<script>
var num = 2; // 초기값 2
function addFileElement() {
	if (num > 5) { // 파일업로드 최대 5개까지만 허용할 때
		alert('최대 5개까지만 업로드 가능합니다.');
		return;
	}
	// div요소에 file타입 input요소를 추가하기
	var input = '<br><input type="file" name="filename'+num+'">';
	num++; // 다음번 추가를 위해 값을 1 증가
	// id속성값이 file_container인 div요소의 참조 구하기
	var fileContainer = document.getElementById('file_container');
	fileContainer.innerHTML += input;
}
</script>
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
