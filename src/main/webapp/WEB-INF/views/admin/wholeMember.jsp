<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.exam.vo.MemberVO"%>
<%@page import="com.exam.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		<h2 class="community_board">전체멤버조회</h2>
		<div class="col-md-12">
			<div class="table-responsive">
			<form action="adminMemberDelete" method="post">
				<table class="board_table" style="width: 100%;">
					<tr id="boarder_head">
						<th scope="col" class="tid">아이디</th>
						<th scope="col" class="tname">이름</th>
						<th scope="col" class="temail">이메일</th>
						<th scope="col" class="ttel">전화번호</th>
						<th scope="col" class="tregdate">가입날짜</th>
						<th scope="col" class="tcheck">선택</th>
					</tr>
					<c:choose>
						<c:when test="${fn:length(memberList) gt 0}">
							<c:forEach var="memberList" items="${memberList}">
								<c:choose>
									<c:when test="${memberList.id ne 'admin' }">
										<tr>
											<td>${memberList.id}</td>
											<td>${memberList.name}</td>
											<td>${memberList.email}</td>
											<td>${memberList.tel}</td>
											<td><fmt:formatDate value="${boardVO.regDate}" pattern="yyyy.MM.dd"/></td>
											<td><input type="checkbox" name="delId" value="${memberList.id}" /></td>
										</tr>				
									</c:when>
									<c:otherwise>
										<tr>
											<td>${memberList.id}</td>
											<td>${memberList.name}</td>
											<td><fmt:formatDate value="${boardVO.regDate}" pattern="yyyy.MM.dd"/></td>
											<td>${memberList.email}</td>
											<td>${memberList.tel}</td>
										</tr>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="6">회원이 없습니다.</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</table>
				<input id="wholeMemDel" type="submit" value="삭제"/>
				</form>
			</div>
		</div>

	</div>
	
</body>
</html>