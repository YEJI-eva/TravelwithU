package com.exam.controller.board;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.exam.controller.Action;
import com.exam.controller.ActionForward;
import com.exam.dao.AttachDao;
import com.exam.dao.BoardDao;
import com.exam.vo.AttachVO;
import com.exam.vo.BoardVO;

public class FileDeleteAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("FileDeleteAction");
		ActionForward forward = new ActionForward();
		// 로그인 안한 사용자면 글목록(fnotice.do)으로 이동시키기
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		if (id == null) {
			forward.setPath("fnotice");
			forward.setRedirect(true);
			return forward;
		}
		// 파라미터값 가져오기
		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt(request.getParameter("num"));
		// 게시판 DAO 객체준비
		BoardDao boardDao = BoardDao.getInstance();
		BoardVO boardVO = boardDao.getBoard(num);
		// 로그인 아이디와 게시판 글삭정자 아이ㅏ디가 다를 때
		if (!id.equals(boardVO.getUsername())) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 권한이 없습니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		boardDao.deleteBoard(num); // 글 삭제처리
		// 첨부파일 테이블 AttachDao 객체 준비
		AttachDao attachDao = AttachDao.getInstance();
		// 게시판 글번호에 해당하는 첨부파일정보 가져오기
		List<AttachVO> attachList = attachDao.getAttaches(num);
		// application 객체 참조 구하기
		ServletContext application = request.getServletContext();
		// 첨부파일정보가 있으면 해당 실제파일 삭제하기
		for (AttachVO attachVO : attachList) {
			// 삭제할 첨부경로 가져오기
			String realPath = application.getRealPath("/upload");
			// 파일 삭제를 위한 File 객체 준비
			File file = new File(realPath, attachVO.getFilename());
			if (file.exists()) { // 해당경로에 파일이 있는지 확인하기
				file.delete(); // 해당경로에 있는 파일 삭제 수행
			}
		} // for
		// attach 테이블 레코드 삭제
		attachDao.deleteAttach(num);
		// 삭제처리 후 글목록 fnotice로 이동
		forward.setPath("fnotice?pageNum="+pageNum);
		forward.setRedirect(true);
		return forward;
	}
}

