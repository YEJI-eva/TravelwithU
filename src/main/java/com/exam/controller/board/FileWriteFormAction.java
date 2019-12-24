package com.exam.controller.board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.exam.controller.Action;
import com.exam.controller.ActionForward;

public class FileWriteFormAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("FileWriteFormAction");
		ActionForward forward = new ActionForward();
		// �α��� ���� ����ڸ� �۸��(fnotice.do)�̷� �̵���Ű��
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		if (id == null) {
			forward.setPath("fnotice");
			forward.setRedirect(true);
			return forward;
		}
		forward.setPath("center/fwrite");
		forward.setRedirect(false);
		return forward;
	}
}
