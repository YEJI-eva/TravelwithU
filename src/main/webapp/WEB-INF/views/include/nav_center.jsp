<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    
    <%-- 쿠키 찾기 --%>
<%
String id = null;

Cookie[] cookies = request.getCookies();
if (cookies != null) {
	for (Cookie cookie : cookies){
		if (cookie.getName().equals("id")) {
			id = cookie.getValue();
			// 세션에 쿠키값을 저장
			session.setAttribute("id", id);
		}
	}
}

%>

<%
// 세션값 가져오기 "id"
id = (String) session.getAttribute("id");

%>


				<nav id="menu">
				<ul class="links">				
							<%
				if (id == null) { // 세션값 없음
					%>
					<li><a href="main">Home</a></li>
					<li class="login"><a href="memberLoginForm">Login</a></li>
					<li><a href="memberJoinForm">SignUp</a></li>
					<li><a href="welcome">Welcome</a></li>
					<li><a href="board">Board</a></li>
					<li><a href="fnotice">File Board</a></li>
						<%
		} else { // id != null 세션값 있음
			if (id.equals("admin")) {
				%>	
					<li><a href="memberLogout">로그아웃</a></li>
					<li><a href="adminWholeMemberForm">회원관리</a></li>
					<li><a href="adminWholeBoardForm">게시글관리</a></li>

				<%	
			} else {
				%>	
				<li><a href="main">Home</a></li>
				<li><a href="memberLogout">로그아웃</a></li>
				<li><a href="welcome">Welcome</a></li>
				<li><a href="board">Board</a></li>
				<li><a href="fnotice">File Board</a></li>
				<%
			}
		}
		%>	
		
				</ul>
			</nav>
