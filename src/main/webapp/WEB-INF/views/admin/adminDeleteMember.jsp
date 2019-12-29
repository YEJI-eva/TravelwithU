<%@page import="com.exam.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String adminId = (String) session.getAttribute("id");

if (adminId.equals("admin")) {
	
	String[] id = request.getParameterValues("delId");
	
	for (int i=0; i<id.length; i++) {
		System.out.println(id[i]);
	}
	
	MemberDao memberDao = MemberDao.getInstance();
	
	memberDao.deleteMember(id);
	 
	response.sendRedirect("wholeMember.jsp");
} else {
	%>
	<script>
		alert('권한이 없습니다.');
		location.href='../main/main.jsp';
	</script>
	<%
}
%>