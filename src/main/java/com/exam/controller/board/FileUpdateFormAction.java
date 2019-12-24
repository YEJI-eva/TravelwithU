package com.exam.controller.board;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.exam.controller.Action;
import com.exam.controller.ActionForward;
import com.exam.dao.AttachDao;
import com.exam.dao.BoardDao;
import com.exam.vo.AttachVO;
import com.exam.vo.BoardVO;

public class FileUpdateFormAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("FileUpdateFormAction");
		ActionForward forward = new ActionForward();
		// 로그인 안한 사용자면 글목록(fnotice.do)으로 이동시키기
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		if (id == null) {
			forward.setPath("fnotice");
			forward.setRedirect(true);
			return forward;
		}
		// 파라미터값 가져오기 num, pageNum
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		// DAO 객체 준비
		BoardDao boardDao = BoardDao.getInstance();
		// 수정할 글 가져오기
		BoardVO boardVO = boardDao.getBoard(num);
		// AttachDao 객체준비
		AttachDao attachDao = AttachDao.getInstance();
		// 글번호에 해당하는 첨부파일정보 가져오기
		List<AttachVO> attachList = attachDao.getAttaches(num);
		// 뷰에서 사용할 데이터를 request 영역객체에 저장
		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("board", boardVO);
		request.setAttribute("attachList", attachList);
		forward.setPath("center/fupdate");
		forward.setRedirect(false);
		return forward;
	}
}
