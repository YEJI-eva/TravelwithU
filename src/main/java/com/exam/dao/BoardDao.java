package com.exam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.exam.vo.BoardVO;

import sun.util.resources.cldr.bo.CalendarData_bo_CN;

public class BoardDao {
	private static BoardDao instance = new BoardDao();
	
	public static BoardDao getInstance() {
		return instance;
	}
	private BoardDao() {		
	}
	
	// insert할 레코드의 번호 생성 메소드
	public int nextBoardNum() {
		int num = 0;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = DBManager.getConnection();
			// num 컬럼중에 최대값 구하기. 레코드 없으면 null
			String sql = "SELECT MAX(num) FROM board";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			// rs 레코드값 있으면 num= 최대값 + 1
			//			 없으면 num = 1
			if (rs.next()) {
				num = rs.getInt(1)+1;
			} else {
				num = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, stmt, rs);
		}
		
		return num;
	}
	
	// 게시글 한개 등록하는 메소드
	public void insertBoard(BoardVO boardVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		StringBuilder sb = new StringBuilder();
		try {
			con = DBManager.getConnection();
			sb.append("INSERT INTO board (num, username, passwd, subject, content, readcount, ip, reg_date, re_ref, re_lev, re_seq) ");
			sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, boardVO.getNum());
			pstmt.setString(2, boardVO.getUsername());
			pstmt.setString(3, boardVO.getPasswd());
			pstmt.setString(4, boardVO.getSubject());
			pstmt.setString(5, boardVO.getContent());
			pstmt.setInt(6, boardVO.getReadcount());
			pstmt.setString(7, boardVO.getIp());
			pstmt.setTimestamp(8, boardVO.getRegDate());
			pstmt.setInt(9, boardVO.getReRef());
			pstmt.setInt(10, boardVO.getReLev());
			pstmt.setInt(11, boardVO.getReSeq());
			// 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt);
		}
		
	} // insertBoard method
	
	public List<BoardVO> getBoards(int startRow, int pageSize) {
		List<BoardVO> list = new ArrayList<BoardVO>();
		int endRow = startRow + pageSize - 1; // 오라클전용 끝행번호
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT aa.* ");
		sb.append("FROM ");
		sb.append("   (SELECT ROWNUM AS rnum, a.* ");
		sb.append("    FROM ");
		sb.append("        (SELECT * ");
		sb.append("        FROM yeji.board ");
		sb.append("        ORDER BY num DESC) a ");
		sb.append("    WHERE ROWNUM <= ?) aa ");
		sb.append("WHERE rnum >= ? ");
		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, endRow);
			pstmt.setInt(2, startRow);
			// 실행
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVO boardVO = new BoardVO();
				boardVO.setNum(rs.getInt("num"));
				boardVO.setUsername(rs.getString("username"));
				boardVO.setPasswd(rs.getString("passwd"));
				boardVO.setSubject(rs.getString("subject"));
				boardVO.setContent(rs.getString("content"));
				boardVO.setReadcount(rs.getInt("readcount"));
				boardVO.setRegDate(rs.getTimestamp("reg_date"));
				boardVO.setReRef(rs.getInt("re_ref"));
				boardVO.setReLev(rs.getInt("re_lev"));
				boardVO.setReSeq(rs.getInt("re_seq"));
				// 리스트에 vo객체 한개 추가
				list.add(boardVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		return list;		
	} //getBoards method
	
	public List<BoardVO> getBoardList() {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM yeji.board ";
			sql += "ORDER BY num ASC ";
			
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BoardVO boardVO = new BoardVO();
				boardVO.setNum(rs.getInt("num"));
				boardVO.setUsername(rs.getString("username"));
				boardVO.setPasswd(rs.getString("passwd"));
				boardVO.setSubject(rs.getString("subject"));
				boardVO.setContent(rs.getString("content"));
				boardVO.setReadcount(rs.getInt("readcount"));
				boardVO.setIp(rs.getString("ip"));
				boardVO.setRegDate(rs.getTimestamp("reg_date"));
				boardVO.setReRef(rs.getInt("re_ref"));
				boardVO.setReLev(rs.getInt("re_lev"));
				boardVO.setReSeq(rs.getInt("re_seq"));
				
				// 由ъ뒪�듃�뿉 vo媛앹껜 �븳媛� 異붽�
				boardList.add(boardVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		return boardList;
	} // getBoards
	
	
	
	// 게시판(board) 테이블 레코드 개수 가져오기 메소드
	public int getBoardCount() {
		int count = 0;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = DBManager.getConnection();
			String sql = "SELECT COUNT(*) FROM yeji.board";
			stmt = con.createStatement();
			// 실행
			rs = stmt.executeQuery(sql);
			rs.next(); // 커서 옮겨서 행 존재유무 true/false 리턴
			count = rs.getInt(1);	 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, stmt, rs);
		}
		return count;
	}
	
	// 특정 레코드의 조회수를 1 증가시키는 메소드
	public void updateReadcount(int num) {
			Connection con = null;
			PreparedStatement pstmt = null;
			StringBuilder sb = new StringBuilder();
			try {
				con = DBManager.getConnection();
				sb.append("UPDATE yeji.board ");
				sb.append("SET readcount = readcount+1 ");
				sb.append("WHERE num = ? "); // 세미클론 제외
				pstmt =con.prepareStatement(sb.toString());
				pstmt.setInt(1, num);
				// 실행
				pstmt.executeUpdate();	
			} catch (Exception e) {		
				e.printStackTrace();
			} finally {
				DBManager.close(con, pstmt);
			}
	} // updateReadcount method
	
	// 글 한개를 가져오는 메소드
	public BoardVO getBoard(int num) {
		BoardVO boardVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con =DBManager.getConnection();
			String sql="SELECT * FROM board WHERE num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			// 실행
			rs = pstmt.executeQuery();
			if (rs.next()) {
				boardVO = new BoardVO();
				boardVO.setNum(rs.getInt("num"));
				boardVO.setUsername(rs.getString("username"));
				boardVO.setPasswd(rs.getString("passwd"));
				boardVO.setSubject(rs.getString("subject"));
				boardVO.setContent(rs.getString("content"));
				boardVO.setReadcount(rs.getInt("readcount"));
				boardVO.setRegDate(rs.getTimestamp("reg_date"));
				boardVO.setReRef(rs.getInt("re_ref"));
				boardVO.setReLev(rs.getInt("re_lev"));
				boardVO.setReSeq(rs.getInt("re_seq"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		return boardVO;
	}// getBoard method
	
	// 게시글 패스워드비교(로그인 안한 사용자가 수행함)
	public boolean isPasswdEqual(int num, String passwd) {
		boolean result = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT COUNT(*) ");
		sb.append("FROM yeji.board ");
		sb.append("WHERE num = ? ");
		sb.append("AND passwd = ? ");
		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, num);
			pstmt.setString(2, passwd);
			// 실행
			rs = pstmt.executeQuery();
			rs.next(); // 커서 내리기
			int count = rs.getInt(1); // 카운트값 가져옥
			if (count == 1) {
				result = true; // 게시글 패스워드 같음
			} else { // count == 0
				result = false; // 게시글 패스워드 다름
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		return result;
	} // isPasswdEqual method
	
	public void updateBoard(BoardVO boardVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		sql = "UPDATE yeji.board ";
		sql += "SET subject=?, content=? ";
		sql += "WHERE num = ? ";	
		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardVO.getSubject());
			pstmt.setString(2, boardVO.getContent());
			pstmt.setInt(3, boardVO.getNum());
			// 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt);
		}
	} // updateBoard method
	
	public void deleteBoard(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBManager.getConnection();
			String sql = "DELETE FROM yeji.board WHERE num = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			// 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt);
		}
	} // deleteBoard method

	public void deleteBoard(String[] num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBManager.getConnection();
			String sql = "DELETE FROM yeji.board WHERE num = ? ";
			
			pstmt = con.prepareStatement(sql);
			for (int i=0; i<num.length; i++) {
				pstmt.setString(1, num[i]);
				pstmt.executeUpdate();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt);
		}
	} // deleteBoard
	
	// 검색어로 검색된 행의 시작행번호부터 갯수만큼 가져오기(페이징)

    public List<BoardVO> getBoards(int startRow, int pageSize, String search) {

        List<BoardVO> list = new ArrayList<BoardVO>();

        int endRow = startRow + pageSize - 1; // 오라클전용 끝행번호

        Connection con = null;

        PreparedStatement pstmt = null;

        ResultSet rs = null;

        StringBuilder sb = new StringBuilder();

        /*sb.append("SELECT aa.* ");

        sb.append("FROM ");

        sb.append("   (SELECT ROWNUM AS rnum, a.* ");

        sb.append("    FROM ");

        sb.append("        (SELECT * ");

        sb.append("        FROM jspdb.board ");

        if (!(search == null || search.equals(""))) {

            sb.append("        WHERE subject LIKE ? ");            

        }

        sb.append("        ORDER BY re_ref DESC, re_seq ASC) a ");

        sb.append("    WHERE ROWNUM <= ?) aa ");

        sb.append("WHERE rnum >= ? ");

        // 검색어 search가 있을때는 검색조건절 where를 추가함

        */

        // MySQL용 글목록 가져오기 SQL문. 시작행번호 0부터 시작

        sb.append("SELECT * ");

        sb.append("FROM yeji.board ");

        // 검색어 search가 있을때는 검색조건절 where를 추가함

        if (!(search == null || search.equals(""))) {

            sb.append("WHERE subject LIKE ? ");

        }

        sb.append("ORDER BY re_ref DESC, re_seq ASC ");

        sb.append("LIMIT ? OFFSET ? ");

        try {

            con = DBManager.getConnection();

            pstmt = con.prepareStatement(sb.toString());

            if (!(search == null || search.equals(""))) {

                // 검색어가 있을때

            pstmt.setString(1, "%" + search + "%");

            //pstmt.setInt(2, endRow);   // 오라클 기준

            //pstmt.setInt(3, startRow); // 오라클 기준

            pstmt.setInt(2, pageSize);    // MySQL 기준

            pstmt.setInt(3, startRow-1); // MySQL 기준

            } else {

                // 검색어가 없을때

            //pstmt.setInt(1, endRow);    // 오라클 기준

            //pstmt.setInt(2, startRow);    // 오라클 기준

            pstmt.setInt(1, pageSize);    // MySQL 기준

            pstmt.setInt(2, startRow-1);  // MySQL 기준

            }

            // 실행

            rs = pstmt.executeQuery();

            while (rs.next()) {

                BoardVO boardVO = new BoardVO();

                boardVO.setNum(rs.getInt("num"));

                boardVO.setUsername(rs.getString("username"));

                boardVO.setPasswd(rs.getString("passwd"));

                boardVO.setSubject(rs.getString("subject"));

                boardVO.setContent(rs.getString("content"));

                boardVO.setReadcount(rs.getInt("readcount"));

                boardVO.setRegDate(rs.getTimestamp("reg_date"));

                boardVO.setReRef(rs.getInt("re_ref"));

                boardVO.setReLev(rs.getInt("re_lev"));

                boardVO.setReSeq(rs.getInt("re_seq"));

                // 리스트에 vo객체 한개 추가

                list.add(boardVO);

            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            DBManager.close(con, pstmt, rs);

        }

        return list;        

    } //getBoards method

	public int getBoardCount(String search) {
		int count=0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con =DBManager.getConnection();
			String sql="SELECT COUNT(*) FROM yeji.board ";
			// 동적(Dynamic) SQL
			if (!(search == null || search.equals(""))) {
				sql += "WHERE subject LIKE ?";
			}
				pstmt = con.prepareStatement(sql);
			if (!(search == null || search.equals(""))) {
				pstmt.setString(1, "%"+search+"%");
			}
			// 실행
			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		return count;
	}// getBoard method
	
	
	
	
/*
num     subject                reRef      reLev       [reSeq]
================================================================
 6        주글3                   6          0           0
 4        주글2                   4          0           0
 5         ㄴ답글2                 4          1           1
 1               주글1	                 1          0           0
[7]         ㄴ답글2                1          1           1 
 2         ㄴ답글1                 1          1          1+1=2     
 3          ㄴ 답글1_1              1          2          2+1=3
*/
	// 답글쓰기 메소드 (update 이후 insert)
	// 트랜잭션 처리가 요구됨(안전하게 처리하려는 목적)
	public void reInsertBoard(BoardVO boardVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		try {
			con = DBManager.getConnection();
			con.setAutoCommit(false); // 기본값 true
		// 같은 글그룹에서의 답글순서(re_seq) 재배치
		//  조건 re_ref 같은그룹 re_seq 큰값은 re_seq+1
		sql = "UPDATE yeji.board ";
		sql += "SET re_seq = re_seq + 1 "; 
		sql += "WHERE re_ref = ? "; 
		sql += "AND re_seq > ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardVO.getReRef());
			pstmt.setInt(2, boardVO.getReSeq());
			// 실행
			pstmt.executeUpdate();
			// 기존 update문 가진 pstmt 닫기
			pstmt.close();
			// 답글 insert
			sql = "INSERT INTO yeji.board (num, username, passwd, subject, content, readcount, ip, reg_date, re_ref, re_lev, re_seq)";
			sql += "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardVO.getNum());
			pstmt.setString(2, boardVO.getUsername());
			pstmt.setString(3, boardVO.getPasswd());
			pstmt.setString(4, boardVO.getSubject());
			pstmt.setString(5, boardVO.getContent());
			pstmt.setInt(6, boardVO.getReadcount()); // 조회수
			pstmt.setString(7, boardVO.getIp());
			pstmt.setTimestamp(8, boardVO.getRegDate());
			pstmt.setInt(9, boardVO.getReRef()); // 그대로
			pstmt.setInt(10, boardVO.getReLev() +1); // re_lev+1
			pstmt.setInt(11, boardVO.getReSeq() +1); // re_seq+1
			// 실행
			pstmt.executeUpdate(); // insert문 실행
			// commit 실행
			con.commit();
			// 기본설정인 auto commit으로 설정 되돌리기
			con.setAutoCommit(true);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback(); // 실행중 예외발생 시 롤백처리
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			DBManager.close(con, pstmt);
		}
	} // reInsertBoard method
	
	
} 
