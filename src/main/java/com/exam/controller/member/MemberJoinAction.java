package com.exam.controller.member;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.exam.controller.Action;
import com.exam.controller.ActionForward;
import com.exam.dao.MemberDao;
import com.exam.vo.MemberVO;

public class MemberJoinAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberJoinAction");
		// MemberVO �ڹٺ� ��ü ����
		MemberVO memberVO = new MemberVO();
		// �ڹٺ� ��ü�� �Ķ���� �� ã�Ƽ� �����ϱ�
		memberVO.setId(request.getParameter("id"));
		memberVO.setPasswd(request.getParameter("passwd"));
		memberVO.setName(request.getParameter("name"));
		memberVO.setEmail(request.getParameter("email"));
		memberVO.setAddress(request.getParameter("address"));
		memberVO.setTel(request.getParameter("tel"));
		memberVO.setMtel(request.getParameter("mtel"));
		// ���Գ�¥ �����ؼ� �ڹٺ� ����
		memberVO.setRegDate(new Timestamp(System.currentTimeMillis()));
		// DAO ��ü �غ�
		MemberDao memberDao = MemberDao.getInstance();
		// ȸ������(�߰�) �޼ҵ� ȣ��
		memberDao.insertMember(memberVO);
		// �α��� �������� �̵��ϴ� ���� �����ؼ� ����
		ActionForward forward = new ActionForward();
		forward.setPath("memberLoginForm");
		forward.setRedirect(true);
		return forward;
	}
}
