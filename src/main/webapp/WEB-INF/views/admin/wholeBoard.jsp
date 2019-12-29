<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.exam.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@page import="com.exam.dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

    
<!DOCTYPE html>

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
<section id="post" class="wrapper bg-img" data-bg="banner2.jpg">
				<div class="inner">
					<article class="box">
	<div class="row">
		<h2 class="community_board">전체 게시글조회</h2>
		<div class="col-md-12">
			<div class="table-responsive">
			<form action="adminContentDelete" method="post" >
				<table class="board_table" style="width: 100%;">
					<tr id="boarder_head">
						<th scope="col" class="tno">글번호</th>
						<th scope="col" class="tcate">카테고리</th>
						<th scope="col" class="ttitle">제목</th>
						<th scope="col" class="twrite">글쓴이</th>
						<th scope="col" class="tdate">작성일</th>
						<th scope="col" class="tread">조회수</th>
						<th scope="col" class="tread">선택</th>
					</tr>
					<c:choose>
						<c:when test="${fn:length(boardList) gt 0}">
							<c:forEach var="boardVO" items="${boardList}">
								<tr class="tr1">
									<td onclick="location.href='adminContentForm?num=${boardVO.num}';">${boardVO.num}</td>
									<td onclick="location.href='adminContentForm?num=${boardVO.num}';">${boardVO.category}</td>
									<td class="td1" onclick="location.href='adminContentForm?num=${boardVO.num}';">
										<div>${boardVO.subject}</div>
										<div class="td2" style="display: none; float: left; border: solid 1px;">내용 : ${boardVO.content}</div>
									</td>
									<td onclick="location.href='adminContentForm?num=${boardVO.num}';">${boardVO.username}</td>
									<td onclick="location.href='adminContentForm?num=${boardVO.num}';"><fmt:formatDate value="${boardVO.regDate}" pattern="yyyy.MM.dd"/></td>
									<td onclick="location.href='adminContentForm?num=${boardVO.num}';">${boardVO.readcount }</td>
									<td><input type="checkbox" name="delBoard" value="${boardVO.num}" /></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="6">게시글이 없습니다.</td>
							</tr>	
						</c:otherwise>
					</c:choose>
				</table>
				<input id="wholeMemDel" type="submit" value="삭제"/>
				</form>
			</div>
		</div>
	</div>
	<%-- footer area --%>
	<jsp:include page="../include/footer.jsp"/>
</body>
<script src="scripts/jquery-3.4.1.min.js"></script>
<script>
	$(document).ready(function () {
	    $('.board_table').hover(function () {
	        $(this).find("div.td2").show();
	    }, function () {
	    	$(this).find("div.td2").hide();
	    });
	});
</script>
