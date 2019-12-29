package com.exam.controller.board;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.exam.controller.Action;
import com.exam.controller.ActionForward;
import com.exam.dao.BoardDao;
import com.exam.vo.BoardVO;

public class UpdateAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("UpdateActoin");
		String pageNum = request.getParameter("pageNum");
		// �ڹٺ� ��ü ����
		BoardVO boardVO = new BoardVO();
		// �Ķ���� ���� �ڹٺ� ����
		boardVO.setNum(Integer.parseInt(request.getParameter("num")));
		boardVO.setUsername(request.getParameter("username"));
		boardVO.setPasswd(request.getParameter("passwd"));
		boardVO.setSubject(request.getParameter("subject"));
		boardVO.setContent(request.getParameter("content"));
		// BoardDao ��ü �غ�
		BoardDao boardDao = BoardDao.getInstance();
		boolean isPasswdEqual = boardDao.isPasswdEqual(boardVO.getNum(), boardVO.getPasswd());
		if (!isPasswdEqual) { // !boardVO.getPasswd().equals(dbBoardVO.getPasswd())
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('�� �н����尡 �ٸ��ϴ�.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		// �Խñ� �����ϱ� �޼ҵ� ȣ��
		boardDao.updateBoard(boardVO);
		// �۸�� notice.do�� �����̷�Ʈ�� �̵�
//		ActionForward forward = new ActionForward();
//		forward.setPath("content.do?num=" + boardVO.getNum() + "&pageNum=" + pageNum + "';");
//		forward.setRedirect(true);
//		return forward;
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('�� ���� ����!');");
		out.println("location.href = 'content?num=" + boardVO.getNum() + "&pageNum=" + pageNum + "';");
		out.println("</script>");
		out.close();
		return null;
	}
}

