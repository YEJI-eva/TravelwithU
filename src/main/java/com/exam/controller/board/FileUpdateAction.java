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
		// �α��� ���� ����� ����
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		if (id == null) {
			forward.setPath("fnotice");
			forward.setRedirect(true);
			return forward;
		}
		// ���Ͼ��ε� ����. MultipartRequest ��ü �غ�
		
		//COS ���̺귯���� �̿��� ���Ͼ��ε�
		//MultipartRequest ������ ȣ��ÿ� ���Ͼ��ε尡 �Ϸ��

		//�ʿ��� �Ű��� 5��
		//1 request

		//2 saveDirectory (���ε��Ұ��)
		ServletContext application = request.getServletContext();
		String realPath = application.getRealPath("/upload");
		System.out.println("realPath : " + realPath);

		//3 �ִ� ���ε� ����ũ��
		int maxSize = 1024 * 1024 * 10; // 10MB
		//4 �����̸� �ѱ�ó��  "utf-8"

		//5 �����̸��ߺ� ó��

		//���Ͼ��ε� ���� �Ϸ�!
		MultipartRequest multi = new MultipartRequest(
				request, realPath, maxSize, "utf-8", 
				new DefaultFileRenamePolicy());
		//============= �Խ��� �� ���� ó�� ���� =================		
		// �ڹٺ� BoardVO ��ü ����
		BoardVO boardVO = new BoardVO();
		// �Ķ���� ã�Ƽ� �ڹٺ� ����
		String pageNum = multi.getParameter("pageNum");
<<<<<<< HEAD
		boardVO.setNum(Integer.parseInt(multi.getParameter("num"))); //������ �۹�ȣ ����
		boardVO.setUsername(multi.getParameter("username"));
		boardVO.setSubject(multi.getParameter("subject"));
		boardVO.setContent(multi.getParameter("content"));
		// �Խñ� �����ϴ� �޼ҵ� ȣ��
		BoardDao boardDao = BoardDao.getInstance();
		boardDao.updateBoard(boardVO);
		//============= �Խ��� �� ���� ó�� ���� =================
		//============= ÷������ DB��� ó�� ���� =================
		// AttachDao �غ�
		AttachDao attachDao = AttachDao.getInstance();
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
				attachVO.setBno(boardVO.getNum()); // �Խñ� ��ȣ
				// �̹��� ���Ͽ��� Ȯ��
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
		// �Խñ� �����ϴ� �޼ҵ� ȣ��
		BoardDao boardDao = BoardDao.getInstance();
		boardDao.updateBoard(boardVO);
		//============= �Խ��� �� ���� ó�� ���� =================
		//============= ÷������ DB��� ó�� ���� =================
		// AttachDao �غ�
		AttachDao attachDao = AttachDao.getInstance();
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
				attachVO.setBno(boardVO.getNum()); // �Խñ� ��ȣ
				// �̹��� ���Ͽ��� Ȯ��
				File file = new File(realPath, realFileName);
				String contentType = Files.probeContentType(file.toPath());
				boolean isImage = contentType.startsWith("image");
				if (isImage) {
					attachVO.setFiletype("I"); // Image File
				} else {
					attachVO.setFiletype("O"); // Other
				}
>>>>>>> branch 'master' of https://github.com/YEJI-eva/TravelwithU.git
				// ÷���������� �Ѱ� ����ϴ� �޼ҵ� ȣ��
				attachDao.insertAttach(attachVO);
			} // if ���Ͼ��ε� ����Ȯ��
		} // while

		//============= ÷������ DB��� ó�� ���� =================
		//============= ������ ���� �����۾� ���� =================

		// ������ �������� �Ķ���� ��������
		String[] delFiles = multi.getParameterValues("delFiles");
		// 17a9f4eb-a9fd-4623-9a52-4233b70fb08f_img21.jpg
		if (delFiles != null) {
			for (String str : delFiles) {
				String[] strArr = str.split("_");
				String uuid = strArr[0];
				String delFilename = strArr[1];
				System.out.println("uuid : " + uuid); 
				System.out.println("delFilename : " + delFilename);
				// ���� �����ϱ�
				File delFile = new File(realPath, delFilename);
				if (delFile.exists()) { // �ش��ο� ������ �����ϸ�
					delFile.delete(); // ���� ����
					System.out.println(delFilename + " ���� ������.");
				}
				
				// attach ���̺� �ش� uuid ���ڵ� �Ѱ� ����
				attachDao.deleteAttach(uuid);
			} // for
		} // if
		//============= ������ ���� �����۾� ���� =================
		// �̵�
		forward.setPath("fnotice?pageNum="+pageNum);
		forward.setRedirect(true);
		return forward;
	}
}
