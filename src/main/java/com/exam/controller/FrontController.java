package com.exam.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class FrontController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet");
		// *����Ʈ ��Ʈ�ѷ� ���� �� ����ܰ�
		// 1) ��û����(��ɾ�) ����
		// http://localhost:80/webmvc/MemberJoin.do
		String requestURI = request.getRequestURI();
		System.out.println("��ûURI�ּ�: " + requestURI);
		// ��ûURI�ּ�: /webmvc/MemberJoin.do
		String contextPath = request.getContextPath();
		System.out.println("contextPath: " + contextPath);
		// contextPath: /webmvc
		String command = requestURI.substring(contextPath.length());
		System.out.println("command: " + command);
		// command: /memberJoinForm.do
		// 2) ��û����(��ɾ�)�� �����ϴ� ���� ����
		Action action = null;
		ActionForward forward = null;
		ActionFactory factory = ActionFactory.getInstance();
		action = factory.getAction(command);
		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 3) ȭ�������� ���� ��(JSP)�� �����ؼ� ����
		// URL �ּ�(sendRedirect ��� �̵�. "*.do" ��ɾ�� ���û�Ҷ�)
		// �Ǵ� jsp���ϸ�(dispatch ��� �̵�)
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else { // dispatch��� �̵�
				String path = "WEB-INF/views/" + forward.getPath() + ".jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(path);
				dispatcher.forward(request, response);
			}
		}
	} // doGet method

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost");
		// post ��û���� �ѱ�ó��
		request.setCharacterEncoding("utf-8");
		// doGet() �޼ҵ� ȣ��
		doGet(request, response);
	} // doPost method
} // FrontController method
