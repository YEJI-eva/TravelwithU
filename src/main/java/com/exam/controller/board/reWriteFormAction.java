package com.exam.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.controller.Action;
import com.exam.controller.ActionForward;

public class reWriteFormAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
				System.out.println("reWriteFormAction");
//				// 파라미터 가져오기
//				String reRef = request.getParameter("reRef");
//				String reLev = request.getParameter("reLev");
//				String reSeq = request.getParameter("reSeq");
//				// 뷰에서 필요한 데이터를 request 영역객체에 저장
//				request.setAttribute("reRef", reRef);
//				request.setAttribute("reLev", reLev);
//				request.setAttribute("reSeq", reSeq);		
				ActionForward forward = new ActionForward();
				forward.setPath("center/reWrite");
				forward.setRedirect(false);
				return forward;
			}
		}


