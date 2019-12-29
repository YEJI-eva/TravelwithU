package com.exam.controller.board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.exam.controller.Action;
import com.exam.controller.ActionForward;
import com.exam.dao.BoardDao;
import com.exam.vo.BoardVO;

public class UpdateFormAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("UpdateFormAction");
		// 파라미터값 가져오기 num, pageNum
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		// DAO 객체 준비
		BoardDao boardDao = BoardDao.getInstance();
		// 수정할 글 가져오기
		BoardVO boardVO = boardDao.getBoard(num);
		// request 영역객체에 저장
		request.setAttribute("board", boardVO);
		request.setAttribute("pageNum", pageNum);
		ActionForward forward = new ActionForward();
		forward.setPath("center/update");
		forward.setRedirect(false);
		return forward;
	}
}
