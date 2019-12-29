<%@page import="com.exam.vo.BoardVO"%>
<%@page import="com.exam.dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<%
// 파라미터값 가져오기 num, pageNum
int num = Integer.parseInt(request.getParameter("num"));
String pageNum = request.getParameter("pageNum");
// DAO 객체 준비
BoardDao boardDao = BoardDao.getInstance();
// 수정할 글 가져오기
BoardVO boardVO = boardDao.getBoard(num);
%>
<%-- 세션값 가져오기 (로그인 여부 확인 위해) --%>
<% String id = (String) session.getAttribute("id"); %>
<%!
	boolean hasNotAuth(String id, BoardVO boardVO) {
		boolean result =
			   id == null && boardVO.getPasswd() == null
			|| id != null && boardVO.getPasswd() != null
			|| id != null && !id.equals(boardVO.getUsername());
		return result;
	}
%>

<%
// *로그인 안한 사용자가 로그인한 사용자 글을 삭제하는 경우
// *로그인한 사용자가 로그인 안한 사용자 글을 삭제하는 경우
// *로그인한 사용자의 경우, 세션값 id가 게시글 작성자명과 다른 경우
// "삭제권한없음" 알림 후 글목록 페이지로 강제이동
if (hasNotAuth(id, boardVO)) {
	//response.sendRedirect("notice.jsp?pageNum=" + pageNum);
	%>
	<script>
		alert('삭제 권한이 없습니다.');
		//location.href = 'content.jsp?num=<%=num%>&pageNum=<%=pageNum %>';
		history.back();
	</script>
	<%
	return;
}
%>
<head>
<meta charset="utf-8">
<title>Welcome to Fun Web</title>
<link href="../css/default.css" rel="stylesheet" type="text/css" media="all">
<link href="../css/subpage.css" rel="stylesheet" type="text/css"  media="all">
<link href="../css/print.css" rel="stylesheet" type="text/css"  media="print">
<link href="../css/iphone.css" rel="stylesheet" type="text/css" media="screen">
<script>
function check() {
	var objPasswd = document.frm.passwd;
	if (objPasswd != null) {
		if (objPasswd.value.length == 0) {
			alert('게시글 패스워드는 필수 입력사항입니다.');
			objPasswd.focus(); // 커서가 깜빡이게함
			return false;
		} 
	}
}
</script>
</head>
<body>
<div id="wrap">
  <!--  헤더 영역 -->
	<jsp:include page="../include/header.jsp" />	
  	<div class="clear"></div>
	<div id="sub_img_center"></div>
	<div class="clear"></div>
   <!--  nav 영역 -->
	<jsp:include page="../include/nav_center.jsp" />	
<article>
    
<h1>Notice Delete</h1>
<%
if (id == null) { // 로그인 안한 사용자
	%>
	<form action="fdeleteProcess.jsp" method="post" name="frm" onsubmit="return check();">
	<%-- 수정할 글번호는 눈에 안보이는 hidden 타입 입력요소 사용 --%>
	<input type="hidden" name="pageNum" value="<%=pageNum %>">
	<input type="hidden" name="num" value="<%=num %>">
	<table id="notice">
		<tr>
			<th class="twrite">글 패스워드</th>
			<td><input type="password" name="passwd" /></td>
		</tr>
	</table>
	<div id="table_search">
	<input type="submit" value="글삭제" class="btn" />
	<input type="reset" value="다시작성" class="btn" />
	<input type="button" value="목록보기" class="btn" onclick="location.href='notice.jsp?pageNum=<%=pageNum %>';"/>
	</div>
</form>
	<%
} else { // id != null 로그인한 사용자
		response.sendRedirect("fdeleteProcess.jsp?num="+num+"&pageNum="+pageNum);		
}
%>
</article>  
     <div class="clear"></div>
   <jsp:include page="../include/footer.jsp" />
</div>
</body>
</html> 