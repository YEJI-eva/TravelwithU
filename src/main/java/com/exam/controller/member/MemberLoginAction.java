package com.exam.controller.member;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.controller.Action;
import com.exam.controller.ActionForward;
import com.exam.dao.MemberDao;


public class MemberLoginAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberLoginAction");
		// �Ķ���Ͱ� �������� "id" "passwd" "rememberMe"
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		// checkbox or radio Ÿ���� ���þ��ϸ� null�� ����
		String rememberMe = request.getParameter("rememberMe");
		// DAO ��ü �غ�
		MemberDao memberDao = MemberDao.getInstance();
		// ����� Ȯ�� �޼ҵ� ȣ��
		int check = memberDao.userCheck(id, passwd);
		// check == 1  �α��� ����(���ǰ����� "id"). index.jsp�� �̵�
		// check == 0  "�н�����Ʋ��" �ڷ��̵�
		// check == -1 "���̵����" �ڷ��̵�
		if (check == 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('�н����尡 �ٸ��ϴ�.');");
			out.println("history.back();");
			out.println("</script>");
			//out.flush();
			out.close(); // ���������� flush()�� ȣ����.
			return null;
		} else if (check == -1) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('�������� �ʴ� ���̵� �Դϴ�.');");
			out.println("history.back();");
			out.println("</script>");
			out.close(); // ���������� flush()�� ȣ����.
			return null;
		}
		// �α��� �����϶�
		// �α��� ����
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
				
		// �α��� �������� ����Ȯ�� ��
		// ��Ű��ü �����ؼ� ����� ������
		if (rememberMe != null && rememberMe.equals("true")) {
			Cookie cookie = new Cookie("id", id);
			cookie.setMaxAge(60*10); // �ʴ���. 10�� = 60�� * 10 = 600��
			cookie.setPath("/"); // ��Ű��� ����
			response.addCookie(cookie); // ���䰴ü�� �߰�
		}
		// main�� �̵�
		ActionForward forward = new ActionForward();
		forward.setPath("main");
		forward.setRedirect(true);
		return forward;
	}
}
