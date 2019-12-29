package com.exam.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.controller.Action;
import com.exam.controller.ActionForward;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.exam.controller.Action;
import com.exam.controller.ActionForward;
import com.exam.dao.BoardDao;
import com.exam.vo.BoardVO;


public class ContentAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ContentAction");
		// 페이지번호 pageNum 파라미터값 가져오기
		String pageNum = request.getParameter("pageNum");
		// 글번호 num 파라미터값 가져오기
		int num = Integer.parseInt(request.getParameter("num"));
		// DAO 객체준비
		BoardDao boardDao = BoardDao.getInstance();
		// 조회수 1증가시키는 메소드 호출
		boardDao.updateReadcount(num); 
		// 글번호에 해당하는 레코드 한개 가져오기
		BoardVO boardVO = boardDao.getBoard(num); 
		// request 영역객체에 저장
<<<<<<< HEAD
		request.setAttribute("board", boardVO);
		request.setAttribute("pageNum", pageNum);
		
		ActionForward forward = new ActionForward();
		forward.setPath("center/content");
=======
		request.setAttribute("boardVO", boardVO);
		request.setAttribute("pageNum", pageNum);
		ActionForward forward = new ActionForward();
		forward.setPath("company/content");
>>>>>>> branch 'master' of https://github.com/YEJI-eva/TravelwithU.git
		forward.setRedirect(false);
		return forward;
	}
}


