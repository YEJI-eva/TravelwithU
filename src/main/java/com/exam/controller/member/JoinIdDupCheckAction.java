package com.exam.controller.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.controller.Action;
import com.exam.controller.ActionForward;
import com.exam.dao.MemberDao;

public class JoinIdDupCheckAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("JoinIdDupCheck");
		
		String id = request.getParameter("id");

		MemberDao memberDao = MemberDao.getInstance();

		boolean isIdDup = memberDao.isIdDuplicated(id);

		System.out.print(isIdDup);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(isIdDup);
		out.close();
		
		return null;
	}

}
