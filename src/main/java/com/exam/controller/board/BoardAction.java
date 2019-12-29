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
      
      // 파라미터값 search  pageNum 가져오기
      String search = request.getParameter("search"); // 검색어
      if (search == null) {
         search = "";
      }

      String strPageNum = request.getParameter("pageNum");
      if (strPageNum == null) {
         strPageNum = "1";
      }
      // 페이지 번호
      int pageNum = Integer.parseInt(strPageNum);
      
      
      // ==========================================
      // 한 페이지에 해당하는 글목록 구하기 작업
      // ==========================================
      // DAO 객체 준비
      BoardDao boardDao = BoardDao.getInstance();

      // 한페이지(화면)에 보여줄 글 개수
      int pageSize = 10;

      // 시작행번호 구하기
//      int startRow = (pageNum - 1) * pageSize + 1; // Oracle 기준
      int startRow = (pageNum - 1) * pageSize;     // MySQL 기준
      

      // 글목록 가져오기 메소드 호출
      List<BoardVO> boardList = boardDao.getBoards(startRow, pageSize, search);
      
      
      
      // ==========================================
      // 페이지 블록 관련정보 구하기 작업
      // ==========================================
      
      // board테이블 전체글개수 가져오기 메소드 호출
      int count = boardDao.getBoardCount(search);
      
      // 총 페이지 개수 구하기
      //  전체 글개수 / 한페이지당 글개수 (+ 1 : 나머지 있을때)
      int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
      
      // 페이지블록 수 설정
      int pageBlock = 5;
      
      // 시작페이지번호 startPage 구하기
      // pageNum값이 1~5 사이면 -> 시작페이지는 항상 1이 나와야 함
      
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
      
      // 끝페이지번호 endPage 구하기
      int endPage = startPage + pageBlock - 1;
      if (endPage > pageCount) {
         endPage = pageCount;
      }
      
      // 페이지블록 관련정보를 Map 또는 VO 객체로 준비
      Map<String, Integer> pageInfoMap = new HashMap<String, Integer>();
      pageInfoMap.put("count", count);
      pageInfoMap.put("pageCount", pageCount);
      pageInfoMap.put("pageBlock", pageBlock);
      pageInfoMap.put("startPage", startPage);
      pageInfoMap.put("endPage", endPage);
      
      
      // 뷰(jsp)에 사용할 데이터를 request 영역객체에 저장
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
		// 파라미터값 search pageNum 가져오기
		String search = request.getParameter("search");
		if (search == null) {
			search = "";
		}
		String strPageNum = request.getParameter("pageNum");
		if (strPageNum == null) {
			strPageNum = "1";
		}
		// 페이지 번호
		int pageNum = Integer.parseInt(strPageNum);
		// ==========================================
		// 한 페이지에 해당하는 글목록 구하기 작업
		// ==========================================
		// DAO 객체 준비
		BoardDao boardDao = BoardDao.getInstance();
		// 한페이지(화면)에 보여줄 글 개수
		int pageSize = 10;
		// 시작행번호 구하기
		int startRow = (pageNum - 1) * pageSize + 1;
		// 글목록 가져오기 메소드 호출
		List<BoardVO> boardList = boardDao.getBoards(startRow, pageSize, search);
		// ==========================================
		// 페이지 블록 관련정보 구하기 작업
		// ==========================================
		// board테이블 전체글개수 가져오기 메소드 호출
		int count = boardDao.getBoardCount(search);
		// 총 페이지 개수 구하기
		//  전체 글개수 / 한페이지당 글개수 (+ 1 : 나머지 있을때)
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		// 페이지블록 수 설정
		int pageBlock = 5;
		// 시작페이지번호 startPage 구하기
		// pageNum값이 1~5 사이면 -> 시작페이지는 항상 1이 나와야 함
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
				
		// 끝페이지번호 endPage 구하기
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		// 페이지블록 관련정보를 Map 또는 VO 객체로 준비
		Map<String, Integer> pageInfoMap = new HashMap<String, Integer>();
		pageInfoMap.put("count", count);
		pageInfoMap.put("pageCount", pageCount);
		pageInfoMap.put("pageBlock", pageBlock);
		pageInfoMap.put("startPage", startPage);
		pageInfoMap.put("endPage", endPage);
		// 뷰(jsp)에 사용할 데이터를 request 영역객체에 저장
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
