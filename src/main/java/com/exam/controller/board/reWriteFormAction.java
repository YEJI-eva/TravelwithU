package com.exam.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.controller.Action;
import com.exam.controller.ActionForward;

public class reWriteFormAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
				System.out.println("reWriteFormAction");
//				// �Ķ���� ��������
//				String reRef = request.getParameter("reRef");
//				String reLev = request.getParameter("reLev");
//				String reSeq = request.getParameter("reSeq");
//				// �信�� �ʿ��� �����͸� request ������ü�� ����
//				request.setAttribute("reRef", reRef);
//				request.setAttribute("reLev", reLev);
//				request.setAttribute("reSeq", reSeq);		
				ActionForward forward = new ActionForward();
				forward.setPath("center/reWrite");
				forward.setRedirect(false);
				return forward;
			}
		}


