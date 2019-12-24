package com.exam.controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.controller.Action;
import com.exam.controller.ActionForward;
import com.exam.dao.AttachDao;
import com.exam.dao.BoardDao;
import com.exam.vo.AttachVO;
import com.exam.vo.BoardVO;

public class FileContentAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("FileContentAction");
		// 페이지번호 pageNum 파라미터값 가져오기
		String pageNum = request.getParameter("pageNum");
		// 글번호 num 파라미터값 가져오기
		int num = Integer.parseInt(request.getParameter("num"));
		// BoardDAO 객체준비
		BoardDao boardDao = BoardDao.getInstance();
		// 조회수 1 증가시키는 메소드 호출
		boardDao.updateReadcount(num);
		// 글번호에 해당하는 레코드 한개 가져오기
		BoardVO boardVO = boardDao.getBoard(num);
		// AttachDao 객체준비
		AttachDao attachDao = AttachDao.getInstance();
		// 글번호에 해당하는 첨부파일정보 가져오기
		List<AttachVO> attachList = attachDao.getAttaches(num);
		// request 영역객체에 저장
		request.setAttribute("board", boardVO);
		request.setAttribute("attachList", attachList);
		request.setAttribute("pageNum", pageNum);
		ActionForward forward = new ActionForward();
		forward.setPath("center/fcontent");
		forward.setRedirect(false);
		return forward;

	}

}
