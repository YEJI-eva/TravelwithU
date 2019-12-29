package com.exam.controller.board;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.exam.controller.Action;
import com.exam.controller.ActionForward;
import com.exam.dao.BoardDao;
import com.exam.vo.BoardVO;

public class WriteAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("WriteAction");
		// �ڹٺ� ��ü ����
		BoardVO boardVO = new BoardVO();
		boardVO.setUsername(request.getParameter("username"));
		boardVO.setPasswd(request.getParameter("passwd"));
		boardVO.setSubject(request.getParameter("subject"));
		boardVO.setContent(request.getParameter("content"));
		// �� ��ϳ�¥, IP�ּ� �� ����
		boardVO.setRegDate(new Timestamp(System.currentTimeMillis()));
		boardVO.setIp(request.getRemoteAddr());
		// BoardDao ��ü �غ�
		BoardDao boardDao = BoardDao.getInstance();
		// �Խñ� ��ȣ �����ϴ� �޼ҵ� ȣ��
		int num = boardDao.nextBoardNum();
		// ������ ��ȣ�� �ڹٺ� �� ��ȣ �ʵ忡 ����
		boardVO.setNum(num); 
		boardVO.setReadcount(0); // ��ȸ�� 0
		// �ֱ��� ���
		boardVO.setReRef(num); // [�۱׷��ȣ]�� �۹�ȣ�� ������
		boardVO.setReLev(0); // [�鿩���� ����] 0
		boardVO.setReSeq(0); // [�۱׷� �ȿ����� ����] 0
		// �Խñ� �Ѱ� ����ϴ� �޼ҵ� ȣ�� insertBoard(boardVO)
		boardDao.insertBoard(boardVO);
		// �̵� notice.do�� �����̷�Ʈ
		ActionForward forward = new ActionForward();
		forward.setPath("board");
		forward.setRedirect(true);
		return forward;
	}
}
