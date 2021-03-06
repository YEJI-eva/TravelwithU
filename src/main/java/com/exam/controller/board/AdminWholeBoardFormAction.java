package com.exam.controller.board;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.controller.Action;
import com.exam.controller.ActionForward;
import com.exam.dao.BoardDao;
import com.exam.vo.BoardVO;

public class AdminWholeBoardFormAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminWholeBoardFormAction");
		
		// DAO 객체�?�?
		BoardDao boardDao = BoardDao.getInstance();

		// �?목록 �??��?���? 메소?�� ?���?
		List<BoardVO> boardList = boardDao.getBoardList();
		
		request.setAttribute("boardList", boardList);
		
		ActionForward forward = new ActionForward();
		
		forward.setPath("admin/wholeBoard");
		forward.setRedirect(false);
		return forward;
	}

}
