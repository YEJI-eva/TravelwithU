<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    
<!DOCTYPE html>

<html>
	<head>
		<title>board - Road Trip by TEMPLATED</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="css/main.css" />
	</head>
	<body class="subpage">
<<<<<<< HEAD
		<!-- Header -->
			<jsp:include page="../include/header.jsp" />

		<!-- Nav -->
		
			<jsp:include page="../include/nav_center.jsp" />	
<section id="post" class="wrapper bg-img" data-bg="banner2.jpg">
				<div class="inner">
					<article class="box">

<h1>Notice [전체글개수 : ${pageInfoMap.count}]</h1> 
<table id="notice">
  <tr>
    <th scope="col" class="tno">no.</th>
    <th scope="col" class="ttitle">title</th>
    <th scope="col" class="twriter">writer</th>
=======

			<!-- Header -->
			<header id="header" class="alt">
				<div class="logo"><a href="main">Road Trip <span> with YEJI<span></a></div>
				<a href="#menu"><span>Menu</span></a>
			</header>


		<!-- Nav -->
		
			<jsp:include page="../include/nav_center.jsp" />	
<section id="post" class="wrapper bg-img" data-bg="banner2.jpg">
				<div class="inner">
					<article class="box">

<h1>Notice [전체글개수 : ${pageInfoMap.count}]</h1> 
<table id="notice">
  <tr>
    <th scope="col" class="tno">no.</th>
    <th scope="col" class="ttitle">title</th>
    <th scope="col" class="twrite">writer</th>
>>>>>>> branch 'master' of https://github.com/YEJI-eva/TravelwithU.git
    <th scope="col" class="tdate">date</th>
    <th scope="col" class="tread">read</th>
  </tr>
 
  <c:choose>
  	<c:when test="${pageInfoMap.count gt 0}">,<%-- ${not empty boardList} --%>
  	<c:forEach var="board" items="${boardList}">
  	  <tr onclick="location.href='content?num=${board.num}&pageNum=${pageNum}';">
  		<td>${board.num}</td>
  		<td class="left">
			<c:if test="${board.reLev gt 0}"><%-- 답글일때 --%>
				<c:set var="level" value="${board.reLev * 10}" />		  	
		        <img src="images/center/level.gif" width="${level}" height="13">
		        <img src="images/center/icon_re.gif">
		  	</c:if>
		  	${board.subject} 
		  </td>	
		  <td>${board.username} </td>
		  <td><fmt:formatDate value="${board.regDate}" pattern="yyyy.MM.dd" /></td>
		  <td>${board.readcount} </td>
		</tr>
  	</c:forEach>
  	</c:when>
  	<c:otherwise>
  		<tr>
		  <td colspan="5">게시판 글이 없습니다.</td>
		</tr>
  	</c:otherwise>
  </c:choose> 
                 
</table>

<form action="notice" method="get">
<div id="table_search">

	<input type="text" name="search" value="${search}" class="input_box">  
	<input type="submit" value="제목검색" class="btn">
	<input type="button" value="글쓰기" class="btn" onclick="location.href='writeForm';" />
</div>
</form>
 <div class="clear"></div>
<div id="page_control">
<c:if test="${pageInfoMap.count gt 0}">
	<%-- [이전] 출력 --%>
	<c:if test="${pageInfoMap.startPage gt pageInfoMap.pageBlock}">
		<a href="board?pageNum=${pageInfoMap.startPage - pageInfoMap.pageBlock}&search=${search}">[이전]</a> 
	</c:if>
	<%-- 페이지블록 페이지 5개 출력 --%>
	<c:forEach var="i" begin="${pageInfoMap.startPage}" end="${pageInfoMap.endPage}" step="1">
		<a href="board?pageNum=${i}&search=${search}">
		<c:choose>
			<c:when test="${i eq pageNum}">
				<span style="font-weight: bold;">[${i}]</span>
			</c:when>
			<c:otherwise>
				${i}
			</c:otherwise>
		</c:choose>
		</a>
	</c:forEach>
	<%-- [다음] 출력 --%>
	<c:if test="${pageInfoMap.endPage lt pageInfoMap.pageCount}">
		<a href="board?pageNum=${pageInfoMap.startPage + pageInfoMap.pageBlock}&search=${search}">[다음]</a> 
	</c:if>
</c:if>
</div>
</article>  
		<!-- Footer -->

		<!-- Scripts -->
			<script src="js/jquery.min.js"></script>
			<script src="js/jquery.scrolly.min.js"></script>
			<script src="js/jquery.scrollex.min.js"></script>
			<script src="js/skel.min.js"></script>
			<script src="js/util.js"></script>
			<script src="js/main.js"></script>

	</body>
</html>
