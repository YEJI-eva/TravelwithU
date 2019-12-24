package com.exam.controller.member;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.exam.controller.Action;
import com.exam.controller.ActionForward;

public class MemberJoinFormAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberJoinFormAction");
		// 3단계에서 사용할 이동정보 객체 준비하고 리턴
		ActionForward forward = new ActionForward();
		forward.setPath("member/join");
		forward.setRedirect(false); // dispatch방식 이동: 해당경로 jsp를 바로 실행하기
		return forward;
	}
}