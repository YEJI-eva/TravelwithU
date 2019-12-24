package com.exam.controller.board;

import java.io.File;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.controller.Action;
import com.exam.controller.ActionForward;
import com.exam.dao.AttachDao;
import com.exam.dao.BoardDao;
import com.exam.vo.AttachVO;
import com.exam.vo.BoardVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FileWriteAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("FileWriteAction");
		// COS ���̺귯���� �̿��� ���Ͼ��ε�
		// MultipartRequest ������ ȣ��ÿ� ���Ͼ��ε尡 �Ϸ��
		// �ʿ��� �Ű��� 5��
		// 1 request
		// 2 saveDirectory (���ε��Ұ��)
		ServletContext application = request.getServletContext();
		String realPath = application.getRealPath("/upload");
		System.out.println("realPath : " + realPath);
		// 3 �ִ� ���ε� ����ũ��
		int maxSize = 1024 * 1024 * 10; // 10MB
		// 4 �����̸� �ѱ�ó��  "utf-8"
		// 5 �����̸��ߺ� ó��
		// ���Ͼ��ε� ���� �Ϸ�!
		MultipartRequest multi = new MultipartRequest(
				request, realPath, maxSize, "utf-8", 
				new DefaultFileRenamePolicy());
		//============= �Խ��� �� ��� ó�� ���� =================
		// �ڹٺ� BoardVO ��ü ����
		BoardVO boardVO = new BoardVO();
		// �Ķ���� ã�Ƽ� �ڹٺ� ����
		boardVO.setUsername(multi.getParameter("username"));
		boardVO.setPasswd(multi.getParameter("passwd"));
		boardVO.setSubject(multi.getParameter("subject"));
		boardVO.setContent(multi.getParameter("content"));
		// �۵�ϳ�¥, IP�ּ� �� ����
		boardVO.setRegDate(new Timestamp(System.currentTimeMillis()));
		boardVO.setIp(request.getRemoteAddr());
		//DAO ��ü �غ�
		BoardDao boardDao = BoardDao.getInstance();
		//�Խñ� ��ȣ �����ϴ� �޼ҵ� ȣ��
		int num = boardDao.nextBoardNum(); 
		//������ ��ȣ�� �ڹٺ� �۹�ȣ �ʵ忡 ����
		boardVO.setNum(num);
		boardVO.setReadcount(0); // ��ȸ�� 0
		//�ֱ��� ��� 
		boardVO.setReRef(num); // [�۱׷��ȣ]�� �۹�ȣ�� ������
		boardVO.setReLev(0); // [�鿩���� ����] 0
		boardVO.setReSeq(0); // [�۱׷� �ȿ����� ����] 0
		// �Խñ� �Ѱ� ����ϴ� �޼ҵ� ȣ�� insertBoard(boardVO)
		boardDao.insertBoard(boardVO);
		//============= �Խ��� �� ��� ó�� ���� =================
		//============= ÷������ ��� ó�� ���� =================
		// ���ε��� ���� �����̸�  a.ppt
		// String originalFileName = multi.getOriginalFileName("filename");
		// System.out.println("originalFileName : " + originalFileName);
		// ������ ���ε�� �����̸�  a1.ppt
		// String realFileName = multi.getFilesystemName("filename");
		// System.out.println("realFileName : " + realFileName);
		// Enumeration ������. file�� �Ķ���� �̸����� ����
		// �ڹ��� Iterator�� ������� ������
		Enumeration<String> enu = multi.getFileNames();
		while (enu.hasMoreElements()) { // ������Ұ� ������
			String str = enu.nextElement();
			System.out.println(str);
			// �Ķ���� �̸����� ������ ���ε�� �����̸� ���ϱ�
			// �ش� �Ķ���� �̸��� ���ε忡 ��� �������� null�� ���ϵ� 
			String realFileName = multi.getFilesystemName(str);
			// ���Ͼ��ε� ����Ȯ��. ���ε� ������
			if (realFileName != null) {
				// �ڹٺ� AttachVO ��ü ����
				AttachVO attachVO = new AttachVO();
				UUID uuid = UUID.randomUUID();
				attachVO.setUuid(uuid.toString());
				attachVO.setFilename(realFileName); // ���� ������ �����̸�
				attachVO.setBno(num); // �Խñ� ��ȣ
				// �̹��� ���Ͽ��� Ȯ��
				File file = new File(realPath, realFileName);
				String contentType = Files.probeContentType(file.toPath()); 
				boolean isImage = contentType.startsWith("image");
				if (isImage) {
					attachVO.setFiletype("I"); // Image File 
				} else { 
					attachVO.setFiletype("O"); // Other
				}
				// AttachDao �غ�
				AttachDao attachDao = AttachDao.getInstance();
				// ÷���������� �Ѱ� ����ϴ� �޼ҵ� ȣ��
				attachDao.insertAttach(attachVO);
			} // if ���Ͼ��ε� ����Ȯ��
		} // while
		//============= ÷������ ��� ó�� ���� =================
		// �̵� fnotice.jsp
		ActionForward forward = new ActionForward();
		forward.setPath("fnotice");
		forward.setRedirect(true);
		return forward;
	}
}
