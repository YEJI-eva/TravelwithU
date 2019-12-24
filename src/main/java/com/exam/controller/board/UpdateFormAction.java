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
		// �Ķ���Ͱ� �������� num, pageNum
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		// DAO ��ü �غ�
		BoardDao boardDao = BoardDao.getInstance();
		// ������ �� ��������
		BoardVO boardVO = boardDao.getBoard(num);
		// request ������ü�� ����
		request.setAttribute("board", boardVO);
		request.setAttribute("pageNum", pageNum);
		ActionForward forward = new ActionForward();
		forward.setPath("center/update");
		forward.setRedirect(false);
		return forward;
	}
}
