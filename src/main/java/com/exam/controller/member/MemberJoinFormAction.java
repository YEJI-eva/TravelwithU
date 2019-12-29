package com.exam.controller.member;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.exam.controller.Action;
import com.exam.controller.ActionForward;

public class MemberJoinFormAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberJoinFormAction");
		// 3�ܰ迡�� ����� �̵����� ��ü �غ��ϰ� ����
		ActionForward forward = new ActionForward();
		forward.setPath("member/join");
		forward.setRedirect(false); // dispatch��� �̵�: �ش��� jsp�� �ٷ� �����ϱ�
		return forward;
	}
}