<%@page import="java.io.File"%>
<%@page import="com.exam.vo.AttachVO"%>
<%@page import="java.util.List"%>
<%@page import="com.exam.dao.AttachDao"%>
<%@page import="com.exam.dao.BoardDao"%>
<%@page import="com.exam.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String adminId = (String) session.getAttribute("id");

if (adminId.equals("admin")) {
	
	// post 파라미터값 한글처리
	request.setCharacterEncoding("utf-8");

	// 파라미터값 가져오기
	String[] num = request.getParameterValues("delBoard");

	// DAO 객체준비
	BoardDao boardDao = BoardDao.getInstance();

	// 첨부파일 테이블 AttachDao객체 준비
	AttachDao attachDao = AttachDao.getInstance();

	// 게시판 글번호에 해당하는 첨부파일 정보 가져오기
	List<AttachVO> attachList = attachDao.getAttach(num);

	// 삭제할 첨부파일경로 가져오기
	String realPath = application.getRealPath("/upload");
	// 첨부파일 정보가 있으면 해당 실제 파일 삭제하기
	
	for (AttachVO attachVO : attachList) { // List 0개이면 실행 안함
		 
		// 파일 삭제를 위한 File 객체 준비
		File file = new File(realPath, attachVO.getFilename()); // 폴더나 파일 작업 가능한 객체
		// parent = 파일이 위치하고 있는 경로, child = 파일명
		
		// 삭제 수행
		if (file.exists()) { // 해당경로에 파일 유무 확인
			file.delete(); // 해당경로에 있는 파일 삭제 수행
		}
	}

	// attach 테이블 레코드 삭제
	attachDao.deleteAttach(num);
	
	boardDao.deleteBoard(num);
	 
	response.sendRedirect("wholeBoard.jsp");
} else {
	%>
	<script>
		alert('권한이 없습니다.');
		location.href='../main/main.jsp';
	</script>
	<%
}
%>