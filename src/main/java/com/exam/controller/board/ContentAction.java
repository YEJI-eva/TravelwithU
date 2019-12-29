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
		// ��������ȣ pageNum �Ķ���Ͱ� ��������
		String pageNum = request.getParameter("pageNum");
		// �۹�ȣ num �Ķ���Ͱ� ��������
		int num = Integer.parseInt(request.getParameter("num"));
		// DAO ��ü�غ�
		BoardDao boardDao = BoardDao.getInstance();
		// ��ȸ�� 1������Ű�� �޼ҵ� ȣ��
		boardDao.updateReadcount(num); 
		// �۹�ȣ�� �ش��ϴ� ���ڵ� �Ѱ� ��������
		BoardVO boardVO = boardDao.getBoard(num); 
		// request ������ü�� ����
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


