<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.exam.vo.BoardVO"%>
<%@page import="com.exam.dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>

<html>
	<head>
		<title>Welcome-Road Trip with YEJI</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="css/main.css" />
	</head>
	<body class="subpage">
	<!-- Header -->
			<jsp:include page="../include/header.jsp" />

		<!-- Nav -->
		
			<jsp:include page="../include/nav_center.jsp" />	
<section id="post" class="wrapper bg-img" data-bg="banner2.jpg">
				<div class="inner">
					<article class="box">
<table id="notice">
	<tr>
  		<th class="tno">no.</th>
    	<td class="left" width="200">${board.num}</td>
    	<th class="tread">read</th>
    	<td class="left" width="200">${board.readcount}</td>
	</tr> 
	<tr>
  		<th class="twriter">writer</th>
    	<td class="left">${board.username}</td>
    	<th class="tdate">date</th>
    	<td class="left"><fmt:formatDate value="${board.regDate}" pattern="yyyy년 MM월 dd일" /></td>
	</tr> 
	<tr>
  		<th class="ttitle">글제목</th>
    	<td class="left" colspan="3">${board.subject}</td>
	</tr>  
	<tr>
  		<th class="twrite">글내용</th>
    	<td class="left" colspan="3"><pre>${board.content}</pre></td>
	</tr> 
</table>
<div id="table_search">
	<input type="button" value="글수정" class="btn" onclick="location.href='updateForm?num=${board.num}&pageNum=${pageNum}';"/> 
	<input type="button" value="글삭제" class="btn" onclick="location.href='deleteForm?num=${board.num}&pageNum=${pageNum}';"/>
	<input type="button" value="답글쓰기" class="btn" onclick="location.href='reWriteForm?reRef=${board.reRef}&reLev=${board.reLev}&reSeq=${board.reSeq}';"/>
	<input type="button" value="목록보기" class="btn" onclick="location.href='board?pageNum=${pageNum}';"/>
</div> 
</article>  
     <div class="clear"></div>

</div>
</body>
</html> 
