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
		// �ڹٺ� BoardVO ��ü ����
		BoardVO boardVO = new BoardVO();
		boardVO.setUsername(request.getParameter("username"));
		boardVO.setPasswd(request.getParameter("passwd"));
		boardVO.setSubject(request.getParameter("subject"));
		boardVO.setContent(request.getParameter("content"));
		boardVO.setReRef(Integer.parseInt(request.getParameter("reRef")));
		boardVO.setReLev(Integer.parseInt(request.getParameter("reLev")));
		boardVO.setReSeq(Integer.parseInt(request.getParameter("reSeq")));
		// �� ��ϳ�¥, IP�ּ� �� ����
		boardVO.setRegDate(new Timestamp(System.currentTimeMillis()));

		boardVO.setIp(request.getRemoteAddr());
		// BoardDao ��ü �غ�
		BoardDao boardDao = BoardDao.getInstance();
		// �Խñ� ��ȣ �����ϴ� �޼ҵ� ȣ��
		int num = boardDao.nextBoardNum();
		// ������ ��ȣ�� �ڹٺ� �� ��ȣ �ʵ忡 ����
		boardVO.setNum(num); // [�۱׷��ȣ]�� �۹�ȣ�� ������
		boardVO.setReadcount(0); // ��ȸ�� 0
		// ��۾��� �޼ҵ� ȣ��
		boardDao.reInsertBoard(boardVO);
		// �۸���̷� �̵�
		ActionForward forward = new ActionForward();
		forward.setPath("notice");
		forward.setRedirect(true);
		return forward;


		
		
		
	}
}
