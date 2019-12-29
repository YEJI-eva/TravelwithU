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
	} // 생성자
	
	// 아이디 중복여부 확인
	public boolean isIdDuplicated(String id) {
		boolean isIdDuplicated = false;
		// Connection 가져오기
		SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession();
		// 인터페이스를 구현한 Mapper 프록시 객체를 만들어서 리턴함
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		// 회원 가입(추가)
		int count = mapper.countMemberById(id);
		if (count > 0) {
			isIdDuplicated = true;
		}
		// JDBC 자원 닫기
		sqlSession.close();
		return isIdDuplicated;
	} // isIdDuplicated method
	

	public void insertMember(MemberVO vo) {
		// Connection 가져오기
		SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession();
		// 인터페이스를 구현한Mapper프록시 객체를 만들어서 리턴함
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		// 회원 가입(추가)
		int count = mapper.insertMember(vo);
		// 커밋
        if (count > 0) {
		    sqlSession.commit();
        } else {
            sqlSession.rollback();
        }
		// JDBC 자원 닫기
		sqlSession.close();
	} // insertMember method

	
	public MemberVO getMember(String id) {
		// Connection 가져오기
		SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession();
		// 인터페이스를 구현한 Mapper 프록시 객체를 만들어서 리턴함
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		// 회원 가입(추가)
		MemberVO memberVO = mapper.getMember(id);
		// JDBC 자원 닫기
		sqlSession.close();
		return memberVO;
	} // getMember method
	
	
	public int userCheck(String id, String passwd) {
		int check = -1; // -1 아이디 불일치. 0 패스워드 불일치. 1 일치
		// Connection 가져오기
		SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession();
		// 인터페이스를 구현한 Mapper 프록시 객체를 만들어서 리턴함
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		// 회원 정보 가져오기
		MemberVO memberVO = mapper.getMemberById(id);
		if (memberVO != null) {
			if (passwd.contentEquals(memberVO.getPasswd())) {
				check = 1; // id,password 일치
			}else {
				check = 0; //password 불일치
			}
		} else { // memberVO == null
			check = -1; // id 없음
		}
		// JDBC 자원 닫기
		sqlSession.close();
		return check;
	} // userCheck method
	
	
	// 전체 회원정보 가져오기
	public List<MemberVO> getMembers() {  
		// Connection 가져오기
		SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession();
		// 인터페이스를 구현한 Mapper 프록시 객체를 만들어서 리턴함
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		// 회원 정보 리스트 가져오기
		List<MemberVO> list = mapper.getMembers();
		// JDBC 자원 닫기
		sqlSession.close();
		return list;
	} // getMembers method

	
	// 회원 업데이트
	public void updateMember(MemberVO memberVO) {
		// Connection 가져오기
		SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession();
		// 인터페이스를 구현한 Mapper 프록시 객체를 만들어서 리턴함
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		// 회원정보 수정
		mapper.updateMember(memberVO);
		// JDBC 자원닫기
		sqlSession.close();
	} // updateMember method
	
//	public List<MemberVO> deleteMember(String[] id){
//	// Connection 가져오기
//	SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession();
//	// 인터페이스를 구현한 Mapper 프록시 객체를 만들어서 리턴함
//	MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
//	// 회원 정보 리스트 가져오기
//	mapper.deleteMember(id);
//	// JDBC 자원닫기
//	sqlSession.close();
//
//} // delete Member method
// 회원 삭제
	public List<MemberVO> deleteMember(String[] id){
	// Connection 가져오기
	SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession();
	// 인터페이스를 구현한 Mapper 프록시 객체를 만들어서 리턴함
	MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
	// 회원 정보 리스트 가져오기
	List<MemberVO> list = mapper.deleteMember(id);
	// JDBC 자원닫기
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
		// 중복이면 true , 중복 아니면 false

		boolean isIdDuplicated = false;
		int count = 0; // id 값이 일치하는 행의 개수
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

			rs.next(); // 커서 옮기기
			count = rs.getInt(1);
			if (count == 1) {
				isIdDuplicated = true; // 중복이다
			} else { // count ==0
				isIdDuplicated = false; // 중복아니다
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		return isIdDuplicated;
	} // 중복확인 끝

	// 멤버 집어 넣기
	public int insertMember(MemberVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;

		try {
			con = DBManager.getConnection();
			// 3단계 sql뮨 준비

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
			// 4단계: sql문 실행
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
			// 4단계: sql문 실행
			rs = pstmt.executeQuery();
			// 5단계: rs 데이터 사용

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
		int check = -1; // -1 아이디 불일치. 0 패스워드 불일치. 1 일치함
		//
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DBManager.getConnection();
//		         
			// 3단계: sql문 준비
			String sql = "SELECT passwd FROM yeji.member WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			// 4단계: sql문 실행
			rs = pstmt.executeQuery();
			// 5단계: rs 데이터 사용
			//
			if (rs.next()) {
//		            // 아이디 있음
				if (passwd.equals(rs.getString("passwd"))) {
					check = 1; // 아이디,패스워드 일치
				} else {
					check = 0; // 패스워드 불일치
				}
			} else { // 아이디 없음
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

	// // 전체회원정보 가져오기
	public List<MemberVO> getMembers() {
		List<MemberVO> list = new ArrayList<MemberVO>();
//		      
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			// 1단계: DB 드라이버 로딩
			// 2단계: DB연결
			con = DBManager.getConnection();
			// 3단계: sql문 준비
			sql = "SELECT * FROM yeji.member ORDER BY id ASC";
			stmt = con.createStatement();
			// 4단계: sql문 실행 -> rs 생성
			rs = stmt.executeQuery(sql);
			// 5단계: while문 rs.next() 다음행이 있으면
			// 자바빈객체 MemberVO 생성 <- rs 행1개 저장
			// 자바빈 한개 -> 배열리스트에 추가
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
	// 회원정보 수정하기 메소드
	// 매개변수 memberVO에 passwd필드는 수정의 대상이 아니라
	// 본인 확인 용도로 사용
	public int updateMember(MemberVO memberVO) {
		int rowCount = 0;

		Connection con = null;
		PreparedStatement pstmt = null;
//		      
		String sql = "";

		try {
			// 1단계: DB 드라이버 로딩
			// 2단계: DB연결
			con = DBManager.getConnection();
			// 3단계: sql문 준비
			sql = "UPDATE yeji.member SET name=?, age=?, gender=?, email=? WHERE id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberVO.getName());

			pstmt.setString(4, memberVO.getEmail());
			pstmt.setString(5, memberVO.getId());
			// 실행
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
