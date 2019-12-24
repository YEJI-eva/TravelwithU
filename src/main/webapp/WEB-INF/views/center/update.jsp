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
<%-- 세션값 가져오기 --%>
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
// *로그인 안한 사용자가 로그인 한 사용자 글을 수정하는 경우
// *로그인한 사용자가 로그인 안한 사용자 글을 수정하는 경우
// *로그인한 사용자의 경우, 세션값 id가 게시글 작성자명과 다른 경우
// "수정권한없음" 알림 후 글목록 페이지로 강제이동
if (hasNotAuth(id, boardVO)) {
	//response.sendRedirect("notice.jsp?pageNum=" + pageNum); 안알려주고 넘어갈 때
	%>
	<script>
		alert('수정 권한이 없습니다.');
		location.href = 'content.jsp?num=<%=num%>&pageNum=<%=pageNum %>';
		//history.back();
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
    
<h1>Notice Write</h1>
<form action="updateProcess.jsp" method="post" name="frm" onsubmit="return check();">
<%-- 수정할 글번호는 눈에 안보이는 hidden 타입 입력요소 사용 --%>
<input type="hidden" name="pageNum" value="<%=pageNum %>">
<input type="hidden" name="num" value="<%=num %>">
<table id="notice">
<%
if (id == null) { // 로그인 안했을 때
	%>
	<tr>
		<th class="twrite">NAME</th>
	<td class="left" width="300">
	<input type="text" name="username" value="<%=boardVO.getUsername() %>" readonly="readonly">
	</td>
	</tr>
	<tr>
  		<th class="twrite">PASSWORD</th>
    	<td class="left">
    	<input type="password" name="passwd" placeholder="작성자 확인을 위해 패스워드를 입력하세요.">
    	</td>
	</tr> 
	<%
} else { // id != null 로그인 했을 때는 행 하나만 나오게
	%>
	<tr>
		<th class="twrite">ID</th>
	<td class="left" width="300">
	<input type="text" name="username" value="<%=id %>" readonly>
	</td>
	</tr>
	<%
}
%>  
	<tr>
  		<th class="twrite">TITLE</th>
    	<td class="left">
    	<input type="text" name="subject" value="<%=boardVO.getSubject() %>">
    	</td>
	</tr> 
	<tr>
  		<th class="twrite">TEXT</th>
    	<td class="left">
    		<textarea name="content" rows="13" cols="40"><%=boardVO.getContent() %></textarea>
    	</td>
	</tr>  
</table>
<div id="table_search">
	<input type="submit" value="글수정" class="btn" />
	<input type="reset" value="다시작성" class="btn" />
	<input type="button" value="목록보기" class="btn" onclick="location.href='notice.jsp?pageNum=<%=pageNum %>';"/>
</div>
</form> 
</article>  
     <div class="clear"></div>
   <jsp:include page="../include/footer.jsp" />
</div>
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
	// 수정 의도 확인
	var result = confirm('<%=num %>번 글을 정말로 수정할까요?');
	if (result == false) {
		return false;
	}
}
</script>
</body>
</html> 