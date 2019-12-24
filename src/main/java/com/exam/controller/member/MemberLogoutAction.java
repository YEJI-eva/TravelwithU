package com.exam.controller.member;

import java.io.PrintWriter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.exam.controller.Action;
import com.exam.controller.ActionForward;

public class MemberLogoutAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberLogoutAction");
		// ���ǰ� �ʱ�ȭ
		HttpSession session = request.getSession();
		session.invalidate();

		// �α��� ���������� ��Ű �����ϱ�
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("id")) {
					cookie.setMaxAge(0); // ��Ű ��ȿ�Ⱓ 0�ʷ� ���� -> �������� �ش���Ű�� ����ó����.
					cookie.setPath("/"); // ������ ��Ű ��ε� �����ؾ���
					response.addCookie(cookie);
				}
			}
		}
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('�α׾ƿ� �Ǿ����ϴ�.');");
		out.println("location.href = 'main';");
		out.println("</script>");
		out.close();
		return null;
	}
}
