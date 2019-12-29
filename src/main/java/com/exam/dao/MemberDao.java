package com.exam.dao;

<<<<<<< HEAD
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.exam.dao.mapper.MemberMapper;
import com.exam.vo.MemberVO;

public class MemberDao {
	private static final MemberDao instance = new MemberDao();
	public static MemberDao getInstance() {
		return instance;
	}
	private MemberDao() {
	} // ������
	
	// ���̵� �ߺ����� Ȯ��
	public boolean isIdDuplicated(String id) {
		boolean isIdDuplicated = false;
		// Connection ��������
		SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession();
		// �������̽��� ������ Mapper ���Ͻ� ��ü�� ���� ������
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		// ȸ�� ����(�߰�)
		int count = mapper.countMemberById(id);
		if (count > 0) {
			isIdDuplicated = true;
		}
		// JDBC �ڿ� �ݱ�
		sqlSession.close();
		return isIdDuplicated;
	} // isIdDuplicated method
	

	public void insertMember(MemberVO vo) {
		// Connection ��������
		SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession();
		// �������̽��� ������Mapper���Ͻ� ��ü�� ���� ������
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		// ȸ�� ����(�߰�)
		int count = mapper.insertMember(vo);
		// Ŀ��
        if (count > 0) {
		    sqlSession.commit();
        } else {
            sqlSession.rollback();
        }
		// JDBC �ڿ� �ݱ�
		sqlSession.close();
	} // insertMember method

	
	public MemberVO getMember(String id) {
		// Connection ��������
		SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession();
		// �������̽��� ������ Mapper ���Ͻ� ��ü�� ���� ������
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		// ȸ�� ����(�߰�)
		MemberVO memberVO = mapper.getMember(id);
		// JDBC �ڿ� �ݱ�
		sqlSession.close();
		return memberVO;
	} // getMember method
	
	
	public int userCheck(String id, String passwd) {
		int check = -1; // -1 ���̵� ����ġ. 0 �н����� ����ġ. 1 ��ġ
		// Connection ��������
		SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession();
		// �������̽��� ������ Mapper ���Ͻ� ��ü�� ���� ������
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		// ȸ�� ���� ��������
		MemberVO memberVO = mapper.getMemberById(id);
		if (memberVO != null) {
			if (passwd.contentEquals(memberVO.getPasswd())) {
				check = 1; // id,password ��ġ
			}else {
				check = 0; //password ����ġ
			}
		} else { // memberVO == null
			check = -1; // id ����
		}
		// JDBC �ڿ� �ݱ�
		sqlSession.close();
		return check;
	} // userCheck method
	
	
	// ��ü ȸ������ ��������
	public List<MemberVO> getMembers() {  
		// Connection ��������
		SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession();
		// �������̽��� ������ Mapper ���Ͻ� ��ü�� ���� ������
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		// ȸ�� ���� ����Ʈ ��������
		List<MemberVO> list = mapper.getMembers();
		// JDBC �ڿ� �ݱ�
		sqlSession.close();
		return list;
	} // getMembers method

	
	// ȸ�� ������Ʈ
	public void updateMember(MemberVO memberVO) {
		// Connection ��������
		SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession();
		// �������̽��� ������ Mapper ���Ͻ� ��ü�� ���� ������
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		// ȸ������ ����
		mapper.updateMember(memberVO);
		// JDBC �ڿ��ݱ�
		sqlSession.close();
	} // updateMember method
	
//	public List<MemberVO> deleteMember(String[] id){
//	// Connection ��������
//	SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession();
//	// �������̽��� ������ Mapper ���Ͻ� ��ü�� ���� ������
//	MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
//	// ȸ�� ���� ����Ʈ ��������
//	mapper.deleteMember(id);
//	// JDBC �ڿ��ݱ�
//	sqlSession.close();
//
//} // delete Member method
// ȸ�� ����
	public List<MemberVO> deleteMember(String[] id){
	// Connection ��������
	SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession();
	// �������̽��� ������ Mapper ���Ͻ� ��ü�� ���� ������
	MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
	// ȸ�� ���� ����Ʈ ��������
	List<MemberVO> list = mapper.deleteMember(id);
	// JDBC �ڿ��ݱ�
	sqlSession.close();
	return list;
	
} // delete Member method
} // class
=======
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.exam.vo.MemberVO;

public class MemberDao {
	private static final MemberDao instance = new MemberDao();

	public static MemberDao getInstance() {
		return instance;
	}

	private MemberDao() {

	}

	public boolean isIdDuplicated(String id) {
		// �ߺ��̸� true , �ߺ� �ƴϸ� false

		boolean isIdDuplicated = false;
		int count = 0; // id ���� ��ġ�ϴ� ���� ����
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		try {
			con = DBManager.getConnection();
			sql = "SELECT COUNT(*) AS cnt FROM yeji.member WHERE id =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			rs.next(); // Ŀ�� �ű��
			count = rs.getInt(1);
			if (count == 1) {
				isIdDuplicated = true; // �ߺ��̴�
			} else { // count ==0
				isIdDuplicated = false; // �ߺ��ƴϴ�
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		return isIdDuplicated;
	} // �ߺ�Ȯ�� ��

	// ��� ���� �ֱ�
	public int insertMember(MemberVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;

		try {
			con = DBManager.getConnection();
			// 3�ܰ� sql�� �غ�

			String sql = "INSERT INTO yeji.member (id,passwd,name,email,address,tel,mtel,reg_date) ";
			sql += " VALUES (?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPasswd());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getAddress());
			pstmt.setString(6, vo.getTel());
			pstmt.setString(7, vo.getMtel());
			pstmt.setTimestamp(8, vo.getRegDate());
			// 4�ܰ�: sql�� ����
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt);
		}
		return rowCount;
	} // insertMember method

	public MemberVO getMember(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		MemberVO memberVO = new MemberVO();

		try {
			con = DBManager.getConnection();
			sql = "SELECT * FROM yeji.member WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			// 4�ܰ�: sql�� ����
			rs = pstmt.executeQuery();
			// 5�ܰ�: rs ������ ���

			if (rs.next()) {

				memberVO.setId(rs.getString("id"));
				memberVO.setPasswd(rs.getString("passwd"));
				memberVO.setName(rs.getString("name"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setRegDate(rs.getTimestamp("reg_date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		return memberVO;
	}
	// getMember method

//		      
	public int userCheck(String id, String passwd) {
		int check = -1; // -1 ���̵� ����ġ. 0 �н����� ����ġ. 1 ��ġ��
		//
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DBManager.getConnection();
//		         
			// 3�ܰ�: sql�� �غ�
			String sql = "SELECT passwd FROM yeji.member WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			// 4�ܰ�: sql�� ����
			rs = pstmt.executeQuery();
			// 5�ܰ�: rs ������ ���
			//
			if (rs.next()) {
//		            // ���̵� ����
				if (passwd.equals(rs.getString("passwd"))) {
					check = 1; // ���̵�,�н����� ��ġ
				} else {
					check = 0; // �н����� ����ġ
				}
			} else { // ���̵� ����
				check = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		return check;
	}
// userCheck method

	// // ��üȸ������ ��������
	public List<MemberVO> getMembers() {
		List<MemberVO> list = new ArrayList<MemberVO>();
//		      
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			// 1�ܰ�: DB ����̹� �ε�
			// 2�ܰ�: DB����
			con = DBManager.getConnection();
			// 3�ܰ�: sql�� �غ�
			sql = "SELECT * FROM yeji.member ORDER BY id ASC";
			stmt = con.createStatement();
			// 4�ܰ�: sql�� ���� -> rs ����
			rs = stmt.executeQuery(sql);
			// 5�ܰ�: while�� rs.next() �������� ������
			// �ڹٺ�ü MemberVO ���� <- rs ��1�� ����
			// �ڹٺ� �Ѱ� -> �迭����Ʈ�� �߰�
			while (rs.next()) {
				MemberVO memberVO = new MemberVO();
				memberVO.setId(rs.getString("id"));
				memberVO.setPasswd(rs.getString("passwd"));
				memberVO.setName(rs.getString("name"));

				memberVO.setEmail(rs.getString("email"));
				memberVO.setRegDate(rs.getTimestamp("reg_date"));

				list.add(memberVO);
			} // while
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, stmt, rs);
		}
		return list;
	} // getMembers method
	 public List<MemberVO> getMemberList() {
	    	
	        List<MemberVO> memberList = new ArrayList<MemberVO>();
	        
	        Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        
	        try {
	            StringBuffer sb = new StringBuffer();
	            sb.append("SELECT * FROM yeji.member ");
	            
	            con = DBManager.getConnection();
	            pstmt = con.prepareStatement(sb.toString());
	            rs = pstmt.executeQuery();
	            
	            while (rs.next()) {
	            	MemberVO memberVO = new MemberVO();
					memberVO.setId(rs.getString("id"));
					memberVO.setName(rs.getString("name"));
					memberVO.setRegDate(rs.getTimestamp("reg_date"));
					memberVO.setEmail(rs.getString("email"));
					memberVO.setTel(rs.getString("tel"));
	                memberList.add(memberVO);
	            }
	            
	        } catch (Exception e) {
	        	e.printStackTrace();
	        } finally {
	            DBManager.close(con, pstmt, rs);
	        }
	        
	        return memberList;
	    } // getMemberList
	// ȸ������ �����ϱ� �޼ҵ�
	// �Ű����� memberVO�� passwd�ʵ�� ������ ����� �ƴ϶�
	// ���� Ȯ�� �뵵�� ���
	public int updateMember(MemberVO memberVO) {
		int rowCount = 0;

		Connection con = null;
		PreparedStatement pstmt = null;
//		      
		String sql = "";

		try {
			// 1�ܰ�: DB ����̹� �ε�
			// 2�ܰ�: DB����
			con = DBManager.getConnection();
			// 3�ܰ�: sql�� �غ�
			sql = "UPDATE yeji.member SET name=?, age=?, gender=?, email=? WHERE id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberVO.getName());

			pstmt.setString(4, memberVO.getEmail());
			pstmt.setString(5, memberVO.getId());
			// ����
			rowCount = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt);
		}
		return rowCount;
	} // updateMember method

	public void deleteMember(String[] id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBManager.getConnection();
			String sql = "DELETE FROM yeji.member WHERE id = ? ";
			
			pstmt = con.prepareStatement(sql);
			
			for (int i=0; i<id.length; i++) {
				pstmt.setString(1, id[i]);
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt);
		}
	} // deleteMember


} // class MemberDao
>>>>>>> branch 'master' of https://github.com/YEJI-eva/TravelwithU.git
