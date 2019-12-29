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
		// �α��� ���� ����ڸ� �۸��(fnotice.do)���� �̵���Ű��
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		if (id == null) {
			forward.setPath("fnotice");
			forward.setRedirect(true);
			return forward;
		}
		// �Ķ���Ͱ� �������� num, pageNum
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		// DAO ��ü �غ�
		BoardDao boardDao = BoardDao.getInstance();
		// ������ �� ��������
		BoardVO boardVO = boardDao.getBoard(num);
		// AttachDao ��ü�غ�
		AttachDao attachDao = AttachDao.getInstance();
		// �۹�ȣ�� �ش��ϴ� ÷���������� ��������
		List<AttachVO> attachList = attachDao.getAttaches(num);
		// �信�� ����� �����͸� request ������ü�� ����
		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("board", boardVO);
		request.setAttribute("attachList", attachList);
		forward.setPath("center/fupdate");
		forward.setRedirect(false);
		return forward;
	}
}
