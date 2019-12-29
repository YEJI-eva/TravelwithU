package com.exam.controller.board;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.exam.controller.Action;
import com.exam.controller.ActionForward;
import com.exam.dao.AttachDao;
import com.exam.dao.BoardDao;
import com.exam.vo.AttachVO;
import com.exam.vo.BoardVO;

public class FileDeleteAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("FileDeleteAction");
		ActionForward forward = new ActionForward();
		// �α��� ���� ����ڸ� �۸��(fnotice.do)���� �̵���Ű��
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		if (id == null) {
			forward.setPath("fnotice");
			forward.setRedirect(true);
			return forward;
		}
		// �Ķ���Ͱ� ��������
		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt(request.getParameter("num"));
		// �Խ��� DAO ��ü�غ�
		BoardDao boardDao = BoardDao.getInstance();
		BoardVO boardVO = boardDao.getBoard(num);
		// �α��� ���̵�� �Խ��� �ۻ����� ���̤��� �ٸ� ��
		if (!id.equals(boardVO.getUsername())) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('���� ������ �����ϴ�.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		boardDao.deleteBoard(num); // �� ����ó��
		// ÷������ ���̺� AttachDao ��ü �غ�
		AttachDao attachDao = AttachDao.getInstance();
		// �Խ��� �۹�ȣ�� �ش��ϴ� ÷���������� ��������
		List<AttachVO> attachList = attachDao.getAttaches(num);
		// application ��ü ���� ���ϱ�
		ServletContext application = request.getServletContext();
		// ÷������������ ������ �ش� �������� �����ϱ�
		for (AttachVO attachVO : attachList) {
			// ������ ÷�ΰ�� ��������
			String realPath = application.getRealPath("/upload");
			// ���� ������ ���� File ��ü �غ�
			File file = new File(realPath, attachVO.getFilename());
			if (file.exists()) { // �ش��ο� ������ �ִ��� Ȯ���ϱ�
				file.delete(); // �ش��ο� �ִ� ���� ���� ����
			}
		} // for
		// attach ���̺� ���ڵ� ����
		attachDao.deleteAttach(num);
		// ����ó�� �� �۸�� fnotice�� �̵�
		forward.setPath("fnotice?pageNum="+pageNum);
		forward.setRedirect(true);
		return forward;
	}
}

