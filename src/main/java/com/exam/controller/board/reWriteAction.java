package com.exam.controller.board;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.controller.Action;
import com.exam.controller.ActionForward;
import com.exam.dao.BoardDao;
import com.exam.vo.BoardVO;


public class reWriteAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("reWriteAction");
		// 자바빈 BoardVO 객체 생성
		BoardVO boardVO = new BoardVO();
		boardVO.setUsername(request.getParameter("username"));
		boardVO.setPasswd(request.getParameter("passwd"));
		boardVO.setSubject(request.getParameter("subject"));
		boardVO.setContent(request.getParameter("content"));
		boardVO.setReRef(Integer.parseInt(request.getParameter("reRef")));
		boardVO.setReLev(Integer.parseInt(request.getParameter("reLev")));
		boardVO.setReSeq(Integer.parseInt(request.getParameter("reSeq")));
		// 글 등록날짜, IP주소 값 저장
		boardVO.setRegDate(new Timestamp(System.currentTimeMillis()));

		boardVO.setIp(request.getRemoteAddr());
		// BoardDao 객체 준비
		BoardDao boardDao = BoardDao.getInstance();
		// 게시글 번호 생성하는 메소드 호출
		int num = boardDao.nextBoardNum();
		// 생성된 번호를 자바빈 글 번호 필드에 설정
		boardVO.setNum(num); // [글그룹번호]는 글번호와 동일함
		boardVO.setReadcount(0); // 조회수 0
		// 답글쓰기 메소드 호출
		boardDao.reInsertBoard(boardVO);
		// 글목록이로 이동
		ActionForward forward = new ActionForward();
		forward.setPath("notice");
		forward.setRedirect(true);
		return forward;


		
		
		
	}
}
