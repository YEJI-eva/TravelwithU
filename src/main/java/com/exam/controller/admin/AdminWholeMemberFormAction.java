package com.exam.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.controller.Action;
import com.exam.controller.ActionForward;
import com.exam.dao.MemberDao;
import com.exam.vo.MemberVO;

public class AdminWholeMemberFormAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {		System.out.println("AdminWholeMemberFormAction");
		// MemberDAO 
		MemberDao memberDao = MemberDao.getInstance();

		List<MemberVO> memberList = memberDao.getMembers();

		request.setAttribute("memberList", memberList);

		ActionForward forward = new ActionForward();
		forward.setPath("admin/wholeMember");
		forward.setRedirect(false);
		return forward;
	}

}
