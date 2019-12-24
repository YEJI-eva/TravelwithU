<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<h1>Notice Content</h1>
<table id="notice">
	<tr>
  		<th class="twrite">글번호</th>
    	<td class="left" width="200">${board.num}</td> 
    	<th class="twrite">조회수</th>
    	<td class="left" width="200">${board.readcount}</td>
	</tr> 
	<tr>
  		<th class="twrite">작성자명</th>
    	<td class="left">${board.username}</td>
    	<th class="twrite">작성일자</th>
    	<td class="left"><fmt:formatDate value="${board.regDate}" pattern="yyyy년 MM월 dd일 hh시 mm분 ss초" /></td>
	</tr> 
	<tr>
  		<th class="twrite">글제목</th>
    	<td class="left" colspan="3">${board.subject}</td>
	</tr> 
	<tr>
  		<th class="twrite">파일</th>
    	<td class="left" colspan="3">
    	
    		<c:forEach var="attach" items="${attachList}">
    			<c:choose>
    				<c:when test="${attach.filetype eq 'I'}"><%-- 이미지 타입 파일 --%>
    					<a href="upload/${attach.filename}" download>
    					<img src="upload/${attach.filename}" width="50" height="50">
    					</a><br>
    				</c:when>
    				<c:otherwise>
    					<a href="upload/${attach.filename}" download>
    					${attach.filename}
    					</a><br>
    				</c:otherwise>
    			</c:choose>
    		</c:forEach>
	    		
    	</td>
	</tr>   
	<tr>
  		<th class="twrite">글내용</th>
    	<td class="left" colspan="3"><pre>${board.content}</pre></td>
	</tr> 
</table>
<div id="table_search">
	<c:if test="${not empty id and id eq board.username}">
		<input type="button" value="글수정" class="btn" onclick="location.href='fupdateForm?num=${board.num}&pageNum=${pageNum}';"/>
		<input type="button" value="글삭제" class="btn" onclick="checkDelete();"/>
	</c:if>
	<input type="button" value="답글쓰기" class="btn" onclick="location.href='reWrite?reRef=${board.reRef}&reLev=${board.reLev}&reSeq=${board.reSeq}';"/>
	<input type="button" value="목록보기" class="btn" onclick="location.href='fnotice?pageNum=${pageNum}';"/>
</div>
</article>  
     <div class="clear"></div>
   <jsp:include page="../include/footer.jsp" />
</div>
<script>
	function checkDelete() {
		var result = confirm('${board.num}번 글을 정말로 삭제할까요?');
		if (result == true) {
		location.href = 'fdelete?num=${board.num}&pageNum=${pageNum}';			
		}
	}
</script>
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
