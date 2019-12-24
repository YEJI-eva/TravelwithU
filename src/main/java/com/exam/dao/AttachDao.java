package com.exam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.exam.vo.AttachVO;

public class AttachDao {
	private static AttachDao instance = new AttachDao();
	
	public static AttachDao getInstance() {
		return instance;
	}
	private AttachDao() {}
	
	
	public List<AttachVO> getAttach(int bno) {
		List<AttachVO> list = new ArrayList<AttachVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBManager.getConnection();
			
			String sql = "SELECT * FROM yeji.attach WHERE bno = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				AttachVO attachVO = new AttachVO();
				attachVO.setBno(rs.getInt("bno"));
				attachVO.setUuid(rs.getString("uuid"));
				attachVO.setFilename(rs.getString("filename"));
				attachVO.setFiletype(rs.getString("filetype"));
				
				list.add(attachVO);
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
		return list;
	} // getAttach
	
	public List<AttachVO> getAttach(String[] num) {
		List<AttachVO> list = new ArrayList<AttachVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBManager.getConnection();
			
			String sql = "SELECT * FROM yeji.attach WHERE bno = ?";
			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < num.length; i++) {
				pstmt.setString(1, num[i]);
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					AttachVO attachVO = new AttachVO();
					attachVO.setBno(rs.getInt("bno"));
					attachVO.setUuid(rs.getString("uuid"));
					attachVO.setFilename(rs.getString("filename"));
					attachVO.setFiletype(rs.getString("filetype"));
					
					list.add(attachVO);
				}
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
		return list;
	} // getAttach
	
	// 첨부파일정보 입력하기 메소드
	public void insertAttach(AttachVO attachVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBManager.getConnection();
			String sql = "INSERT INTO yeji.attach (uuid, filename, filetype, bno) ";
			sql += "VALUES (?, ?, ?, ?) ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, attachVO.getUuid());
			pstmt.setString(2, attachVO.getFilename());
			pstmt.setString(3, attachVO.getFiletype());
			pstmt.setInt(4, attachVO.getBno());
			// 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt);
		}
	} // insertAttch method
	
	// 글번호에 해당하는 첨부파일정보 가져오기
	public List<AttachVO> getAttaches(int bno) {
		List<AttachVO> list = new ArrayList<AttachVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBManager.getConnection();
			String sql = "SELECT * FROM yeji.attach WHERE bno = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			// 실행
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AttachVO attachVO = new AttachVO();
				attachVO.setBno(rs.getInt("bno"));
				attachVO.setUuid(rs.getString("uuid"));
				attachVO.setFilename(rs.getString("filename"));
				attachVO.setFiletype(rs.getString("filetype"));
				list.add(attachVO);
			} // while
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		return list;
	} // getAttaches method
	
	// 게시판 글번호에 해당하는 첨부파일정보 삭제하는 메소드\
	public void deleteAttach(int bno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBManager.getConnection();
			String sql = "DELETE FROM yeji.attach WHERE bno = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
		
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt);
		}
	} // deleteAttach(bno)

	public void deleteAttach(String[] num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBManager.getConnection();
			String sql = "DELETE FROM yeji.attach WHERE bno = ?";
			
			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < num.length; i++) {
				pstmt.setString(1, num[i]);
				pstmt.executeUpdate();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt);
		}
	} // deleteAttach(bno)
	
	public void deleteAttach(String uuid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBManager.getConnection();
			String sql = "DELETE FROM yeji.attach WHERE uuid = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, uuid);
			// 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt);
		}
	} // deleteAttach method
	
	
	
	
	
	
	
	
	
}
