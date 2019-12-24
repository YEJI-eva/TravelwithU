package com.exam.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.controller.Action;
import com.exam.controller.ActionForward;

public class MainAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();
		forward.setPath("main/main"); // jsp 경로
		forward.setRedirect(false); // 직접 실행

		return forward;
	}

}
