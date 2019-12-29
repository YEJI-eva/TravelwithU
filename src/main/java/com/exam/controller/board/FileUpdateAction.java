package com.exam.controller.board;

import java.io.File;
import java.nio.file.Files;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.controller.Action;
import com.exam.controller.ActionForward;
import com.exam.dao.AttachDao;
import com.exam.dao.BoardDao;
import com.exam.vo.AttachVO;
import com.exam.vo.BoardVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FileUpdateAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("FileUpdateAction");
		ActionForward forward = new ActionForward();
		// 로그인 안한 사용자 금지
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		if (id == null) {
			forward.setPath("fnotice");
			forward.setRedirect(true);
			return forward;
		}
		// 파일업로드 수행. MultipartRequest 객체 준비
		
		//COS 라이브러리를 이용한 파일업로드
		//MultipartRequest 생성자 호출시에 파일업로드가 완료됨

		//필요한 매개값 5개
		//1 request

		//2 saveDirectory (업로드할경로)
		ServletContext application = request.getServletContext();
		String realPath = application.getRealPath("/upload");
		System.out.println("realPath : " + realPath);

		//3 최대 업로드 파일크기
		int maxSize = 1024 * 1024 * 10; // 10MB
		//4 파일이름 한글처리  "utf-8"

		//5 파일이름중복 처리

		//파일업로드 수행 완료!
		MultipartRequest multi = new MultipartRequest(
				request, realPath, maxSize, "utf-8", 
				new DefaultFileRenamePolicy());
		//============= 게시판 글 수정 처리 시작 =================		
		// 자바빈 BoardVO 객체 생성
		BoardVO boardVO = new BoardVO();
		// 파라미터 찾아서 자바빈에 저장
		String pageNum = multi.getParameter("pageNum");
<<<<<<< HEAD
		boardVO.setNum(Integer.parseInt(multi.getParameter("num"))); //수정할 글번호 저장
		boardVO.setUsername(multi.getParameter("username"));
		boardVO.setSubject(multi.getParameter("subject"));
		boardVO.setContent(multi.getParameter("content"));
		// 게시글 수정하는 메소드 호출
		BoardDao boardDao = BoardDao.getInstance();
		boardDao.updateBoard(boardVO);
		//============= 게시판 글 수정 처리 종료 =================
		//============= 첨부파일 DB등록 처리 시작 =================
		// AttachDao 준비
		AttachDao attachDao = AttachDao.getInstance();
		// Enumeration 열거형. file의 파라미터 이름들을 가짐
		// 자바의 Iterator와 사용방법이 동일함
		Enumeration<String> enu = multi.getFileNames();
		while (enu.hasMoreElements()) { // 다음요소가 있으면
			String str = enu.nextElement();
			System.out.println(str);
			// 파라미터 이름으로 실제로 업로드된 파일이름 구하기
			// 해당 파라미터 이름을 업로드에 사용 안했으면 null이 리턴됨 
			String realFileName = multi.getFilesystemName(str);
			// 파일업로드 여부확인. 업로드 했으면
			if (realFileName != null) {
				// 자바빈 AttachVO 객체 생성
				AttachVO attachVO = new AttachVO();
				UUID uuid = UUID.randomUUID();
				attachVO.setUuid(uuid.toString());
				attachVO.setFilename(realFileName); // 실제 생성된 파일이름
				attachVO.setBno(boardVO.getNum()); // 게시글 번호
				// 이미지 파일여부 확인
				File file = new File(realPath, realFileName);
				String contentType = Files.probeContentType(file.toPath());
				if (contentType != null) {
					boolean isImage = contentType.startsWith("image");
					if (isImage) {
						attachVO.setFiletype("I"); // Image File
					} else {
						attachVO.setFiletype("O"); // Other
					}
				} else {
					attachVO.setFiletype("O"); // Other
				}


=======
		boardVO.setNum(Integer.parseInt(multi.getParameter("num")));
		boardVO.setUsername(multi.getParameter("username"));
		boardVO.setSubject(multi.getParameter("subject"));
		boardVO.setContent(multi.getParameter("content"));
		// 게시글 수정하는 메소드 호출
		BoardDao boardDao = BoardDao.getInstance();
		boardDao.updateBoard(boardVO);
		//============= 게시판 글 수정 처리 종료 =================
		//============= 첨부파일 DB등록 처리 시작 =================
		// AttachDao 준비
		AttachDao attachDao = AttachDao.getInstance();
		// Enumeration 열거형. file의 파라미터 이름들을 가짐
		// 자바의 Iterator와 사용방법이 동일함
		Enumeration<String> enu = multi.getFileNames();
		while (enu.hasMoreElements()) { // 다음요소가 있으면
			String str = enu.nextElement();
			System.out.println(str);
			// 파라미터 이름으로 실제로 업로드된 파일이름 구하기
			// 해당 파라미터 이름을 업로드에 사용 안했으면 null이 리턴됨 
			String realFileName = multi.getFilesystemName(str);
			// 파일업로드 여부확인. 업로드 했으면
			if (realFileName != null) {
				// 자바빈 AttachVO 객체 생성
				AttachVO attachVO = new AttachVO();
				UUID uuid = UUID.randomUUID();
				attachVO.setUuid(uuid.toString());
				attachVO.setFilename(realFileName); // 실제 생성된 파일이름
				attachVO.setBno(boardVO.getNum()); // 게시글 번호
				// 이미지 파일여부 확인
				File file = new File(realPath, realFileName);
				String contentType = Files.probeContentType(file.toPath());
				boolean isImage = contentType.startsWith("image");
				if (isImage) {
					attachVO.setFiletype("I"); // Image File
				} else {
					attachVO.setFiletype("O"); // Other
				}
>>>>>>> branch 'master' of https://github.com/YEJI-eva/TravelwithU.git
				// 첨부파일정보 한개 등록하는 메소드 호출
				attachDao.insertAttach(attachVO);
			} // if 파일업로드 여부확인
		} // while

		//============= 첨부파일 DB등록 처리 종료 =================
		//============= 삭제할 파일 삭제작업 시작 =================

		// 삭제할 파일정보 파라미터 가져오기
		String[] delFiles = multi.getParameterValues("delFiles");
		// 17a9f4eb-a9fd-4623-9a52-4233b70fb08f_img21.jpg
		if (delFiles != null) {
			for (String str : delFiles) {
				String[] strArr = str.split("_");
				String uuid = strArr[0];
				String delFilename = strArr[1];
				System.out.println("uuid : " + uuid); 
				System.out.println("delFilename : " + delFilename);
				// 파일 삭제하기
				File delFile = new File(realPath, delFilename);
				if (delFile.exists()) { // 해당경로에 파일이 존재하면
					delFile.delete(); // 파일 삭제
					System.out.println(delFilename + " 파일 삭제됨.");
				}
				
				// attach 테이블에 해당 uuid 레코드 한개 삭제
				attachDao.deleteAttach(uuid);
			} // for
		} // if
		//============= 삭제할 파일 삭제작업 종료 =================
		// 이동
		forward.setPath("fnotice?pageNum="+pageNum);
		forward.setRedirect(true);
		return forward;
	}
}
