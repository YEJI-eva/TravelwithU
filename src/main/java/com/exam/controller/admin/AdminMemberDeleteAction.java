package com.exam.controller.admin;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.controller.Action;
import com.exam.controller.ActionForward;
import com.exam.dao.MemberDao;

public class AdminMemberDeleteAction implements Action {


	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminMemberDeleteAction");
		
		HttpSession session = request.getSession();
		String adminId = (String) session.getAttribute("id");

		if (adminId.equals("admin")) {
			
			String[] id = request.getParameterValues("delId");
			
			for (int i=0; i<id.length; i++) {
				System.out.println(id[i]);
			}
			
			MemberDao memberDao = MemberDao.getInstance();
			
			memberDao.deleteMember(id);
			 
			response.sendRedirect("adminWholeMemberForm");
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('식제 완료');");
			out.println("location.href='main';");
			out.println("</script>");
		}
		return null;
	}

}
