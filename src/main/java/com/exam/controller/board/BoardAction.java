package com.exam.controller.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.controller.Action;
import com.exam.controller.ActionForward;
import com.exam.dao.BoardDao;
import com.exam.vo.BoardVO;

public class BoardAction implements Action {

   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
<<<<<<< HEAD
      System.out.println("NoticeAction");
      
      // �Ķ���Ͱ� search  pageNum ��������
      String search = request.getParameter("search"); // �˻���
      if (search == null) {
         search = "";
      }

      String strPageNum = request.getParameter("pageNum");
      if (strPageNum == null) {
         strPageNum = "1";
      }
      // ������ ��ȣ
      int pageNum = Integer.parseInt(strPageNum);
      
      
      // ==========================================
      // �� �������� �ش��ϴ� �۸�� ���ϱ� �۾�
      // ==========================================
      // DAO ��ü �غ�
      BoardDao boardDao = BoardDao.getInstance();

      // ��������(ȭ��)�� ������ �� ����
      int pageSize = 10;

      // �������ȣ ���ϱ�
//      int startRow = (pageNum - 1) * pageSize + 1; // Oracle ����
      int startRow = (pageNum - 1) * pageSize;     // MySQL ����
      

      // �۸�� �������� �޼ҵ� ȣ��
      List<BoardVO> boardList = boardDao.getBoards(startRow, pageSize, search);
      
      
      
      // ==========================================
      // ������ ��� �������� ���ϱ� �۾�
      // ==========================================
      
      // board���̺� ��ü�۰��� �������� �޼ҵ� ȣ��
      int count = boardDao.getBoardCount(search);
      
      // �� ������ ���� ���ϱ�
      //  ��ü �۰��� / ���������� �۰��� (+ 1 : ������ ������)
      int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
      
      // ��������� �� ����
      int pageBlock = 5;
      
      // ������������ȣ startPage ���ϱ�
      // pageNum���� 1~5 ���̸� -> ������������ �׻� 1�� ���;� ��
      
      // ((1 - 1) / 5) * 5 + 1 -> 1
      // ((2 - 1) / 5) * 5 + 1 -> 1
      // ((3 - 1) / 5) * 5 + 1 -> 1
      // ((4 - 1) / 5) * 5 + 1 -> 1
      // ((5 - 1) / 5) * 5 + 1 -> 1
      
      // ((6 - 1) / 5) * 5 + 1 -> 6
      // ((7 - 1) / 5) * 5 + 1 -> 6
      // ((8 - 1) / 5) * 5 + 1 -> 6
      // ((9 - 1) / 5) * 5 + 1 -> 6
      // ((10- 1) / 5) * 5 + 1 -> 6
      int startPage = ((pageNum - 1) / pageBlock) * pageBlock + 1;
      
      // ����������ȣ endPage ���ϱ�
      int endPage = startPage + pageBlock - 1;
      if (endPage > pageCount) {
         endPage = pageCount;
      }
      
      // ��������� ���������� Map �Ǵ� VO ��ü�� �غ�
      Map<String, Integer> pageInfoMap = new HashMap<String, Integer>();
      pageInfoMap.put("count", count);
      pageInfoMap.put("pageCount", pageCount);
      pageInfoMap.put("pageBlock", pageBlock);
      pageInfoMap.put("startPage", startPage);
      pageInfoMap.put("endPage", endPage);
      
      
      // ��(jsp)�� ����� �����͸� request ������ü�� ����
      request.setAttribute("boardList", boardList);
      request.setAttribute("pageInfoMap", pageInfoMap);
      request.setAttribute("pageNum", pageNum);
      request.setAttribute("search", search);
      
      
      ActionForward forward = new ActionForward();
      forward.setPath("center/board");
      forward.setRedirect(false);
      return forward;
   }

}
=======
		System.out.println("BoardAction");
		// �Ķ���Ͱ� search pageNum ��������
		String search = request.getParameter("search");
		if (search == null) {
			search = "";
		}
		String strPageNum = request.getParameter("pageNum");
		if (strPageNum == null) {
			strPageNum = "1";
		}
		// ������ ��ȣ
		int pageNum = Integer.parseInt(strPageNum);
		// ==========================================
		// �� �������� �ش��ϴ� �۸�� ���ϱ� �۾�
		// ==========================================
		// DAO ��ü �غ�
		BoardDao boardDao = BoardDao.getInstance();
		// ��������(ȭ��)�� ������ �� ����
		int pageSize = 10;
		// �������ȣ ���ϱ�
		int startRow = (pageNum - 1) * pageSize + 1;
		// �۸�� �������� �޼ҵ� ȣ��
		List<BoardVO> boardList = boardDao.getBoards(startRow, pageSize, search);
		// ==========================================
		// ������ ��� �������� ���ϱ� �۾�
		// ==========================================
		// board���̺� ��ü�۰��� �������� �޼ҵ� ȣ��
		int count = boardDao.getBoardCount(search);
		// �� ������ ���� ���ϱ�
		//  ��ü �۰��� / ���������� �۰��� (+ 1 : ������ ������)
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		// ��������� �� ����
		int pageBlock = 5;
		// ������������ȣ startPage ���ϱ�
		// pageNum���� 1~5 ���̸� -> ������������ �׻� 1�� ���;� ��
		// ((1 - 1) / 5) * 5 + 1 -> 1
		// ((2 - 1) / 5) * 5 + 1 -> 1
		// ((3 - 1) / 5) * 5 + 1 -> 1
		// ((4 - 1) / 5) * 5 + 1 -> 1
		// ((5 - 1) / 5) * 5 + 1 -> 1
		// ((6 - 1) / 5) * 5 + 1 -> 6
		// ((7 - 1) / 5) * 5 + 1 -> 6
		// ((8 - 1) / 5) * 5 + 1 -> 6
		// ((9 - 1) / 5) * 5 + 1 -> 6
		// ((10- 1) / 5) * 5 + 1 -> 6
		int startPage = ((pageNum - 1) / pageBlock) * pageBlock + 1;
				
		// ����������ȣ endPage ���ϱ�
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		// ��������� ���������� Map �Ǵ� VO ��ü�� �غ�
		Map<String, Integer> pageInfoMap = new HashMap<String, Integer>();
		pageInfoMap.put("count", count);
		pageInfoMap.put("pageCount", pageCount);
		pageInfoMap.put("pageBlock", pageBlock);
		pageInfoMap.put("startPage", startPage);
		pageInfoMap.put("endPage", endPage);
		// ��(jsp)�� ����� �����͸� request ������ü�� ����
		request.setAttribute("boardList", boardList);
		request.setAttribute("pageInfoMap", pageInfoMap);
		request.setAttribute("pageNum", pageNum);
		ActionForward forward = new ActionForward();
		forward.setPath("center/board");
		forward.setRedirect(false);
		return forward;
	}
}

>>>>>>> branch 'master' of https://github.com/YEJI-eva/TravelwithU.git
