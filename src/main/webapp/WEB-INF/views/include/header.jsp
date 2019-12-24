<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String id = null;
// 쿠키찾기
Cookie[] cookies = request.getCookies();
if (cookies != null) {
	for (Cookie cookie : cookies) {
		if (cookie.getName().equals("id")) {
			id = cookie.getValue();
			session.setAttribute("id", id);
		}
	}
}




// 세션값 가져오기 "id" 
id = (String) session.getAttribute("id");
%>
  <header>
       <div id="login"> 
       <%
       		if(id == null) { // 세션값 없음
       			%><a href="memberLoginForm">login</a><%
       		} else { // id != null 세션값 있음
       			%>
       			<%=id%>님
       			<a href="memberLogout">logout</a><%
       		}
       %>
       |		 <a href="memberJoinForm">Join</a>
       </div>
       
        <div class="clear"></div>
        <div id="logo"><a href="../"><img src="images/logo.gif" width="265" height="62" alt="Fun Web"></a></div>
        <nav id="top_menu">
            <ul>
                <li><a href="../">HOME</a></li>
                <li><a href="../company/welcome">COMPANY</a></li>
                <li><a href="#">SOLUTIONS</a></li>
                <li><a href="../center/notice">CUSTOMER CENTER</a></li>
                <li><a href="#">CONTACT US</a></li>
            </ul>
        </nav> 
  </header>
