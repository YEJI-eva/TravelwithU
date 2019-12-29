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
		// 파라미터값 가져오기 "id" "passwd" "rememberMe"
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		// checkbox or radio 타입은 선택안하면 null을 리턴
		String rememberMe = request.getParameter("rememberMe");
		// DAO 객체 준비
		MemberDao memberDao = MemberDao.getInstance();
		// 사용자 확인 메소드 호출
		int check = memberDao.userCheck(id, passwd);
		// check == 1  로그인 인증(세션값생성 "id"). index.jsp로 이동
		// check == 0  "패스워드틀림" 뒤로이동
		// check == -1 "아이디없음" 뒤로이동
		if (check == 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('패스워드가 다릅니다.');");
			out.println("history.back();");
			out.println("</script>");
			//out.flush();
			out.close(); // 내부적으로 flush()를 호출함.
			return null;
		} else if (check == -1) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('존재하지 않는 아이디 입니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close(); // 내부적으로 flush()를 호출함.
			return null;
		}
		// 로그인 성공일때
		// 로그인 인증
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
				
		// 로그인 상태유지 여부확인 후
		// 쿠키객체 생성해서 응답시 보내기
		if (rememberMe != null && rememberMe.equals("true")) {
			Cookie cookie = new Cookie("id", id);
			cookie.setMaxAge(60*10); // 초단위. 10분 = 60초 * 10 = 600초
			cookie.setPath("/"); // 쿠키경로 설정
			response.addCookie(cookie); // 응답객체에 추가
		}
		// main로 이동
		ActionForward forward = new ActionForward();
		forward.setPath("main");
		forward.setRedirect(true);
		return forward;
	}
}
