package com.exam.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.controller.Action;
import com.exam.controller.ActionForward;

public class ElementAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		forward.setPath("main/elements"); // jsp ���
		forward.setRedirect(false); // ���� ����

		return forward;
	}

}
