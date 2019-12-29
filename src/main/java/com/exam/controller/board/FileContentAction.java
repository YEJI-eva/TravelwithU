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
		// ��������ȣ pageNum �Ķ���Ͱ� ��������
		String pageNum = request.getParameter("pageNum");
		// �۹�ȣ num �Ķ���Ͱ� ��������
		int num = Integer.parseInt(request.getParameter("num"));
		// BoardDAO ��ü�غ�
		BoardDao boardDao = BoardDao.getInstance();
		// ��ȸ�� 1 ������Ű�� �޼ҵ� ȣ��
		boardDao.updateReadcount(num);
		// �۹�ȣ�� �ش��ϴ� ���ڵ� �Ѱ� ��������
		BoardVO boardVO = boardDao.getBoard(num);
		// AttachDao ��ü�غ�
		AttachDao attachDao = AttachDao.getInstance();
		// �۹�ȣ�� �ش��ϴ� ÷���������� ��������
		List<AttachVO> attachList = attachDao.getAttaches(num);
		// request ������ü�� ����
		request.setAttribute("board", boardVO);
		request.setAttribute("attachList", attachList);
		request.setAttribute("pageNum", pageNum);
		ActionForward forward = new ActionForward();
		forward.setPath("center/fcontent");
		forward.setRedirect(false);
		return forward;

	}

}
