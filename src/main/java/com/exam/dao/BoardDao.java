package com.exam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.exam.dao.mapper.BoardMapper;
import com.exam.vo.BoardVO;


public class BoardDao {
	private static BoardDao instance = new BoardDao();
	
	public static BoardDao getInstance() {
		return instance;
	}
	private BoardDao() {		
	}
	
	public int nextBoardNum() {
		try (SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession()) {
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			return mapper.nextBoardNum();
		}
	} // nextBoardNum
	
	public void insertBoard(BoardVO boardVO) {
		try (SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession()){
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			mapper.insertBoard(boardVO);
			sqlSession.commit();
		} 
	} // insertBoard method
	
	public List<BoardVO> getBoards(int startRow, int pageSize, String search) {
		try (SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession()){
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class); 
			return mapper.getBoards(startRow, pageSize, search);
		} 
	} //getBoards method
	
	public int getBoardCount(String search) {
		try (SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession()){
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			return mapper.getBoardCount(search);  
		} 
	} // getBoardCount method
	
	
	public List<BoardVO> getBoardList(BoardVO boardVO) {
		try (SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession()){
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class); 
			return mapper.getBoardList(boardVO);
		} 
	} // getBoardList
	
	
	public void updateReadcount(int num) {
		try (SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession()){
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			mapper.updateReadcount(num); 
			sqlSession.commit();       
		} 
	} // updateReadcount method
	
	public BoardVO getBoard(int num) {
		try (SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession()){
			return sqlSession.getMapper(BoardMapper.class).getBoard(num); 
		} 
	}// getBoard method
	
	public boolean isPasswdEqual(int num, String passwd) {
		System.out.println("num : "+num+"passwd : "+passwd);
		boolean result = false;
		try (SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession()){
			int count = sqlSession.getMapper(BoardMapper.class).countByNumPasswd(num, passwd);
			if (count == 1) {
				result = true;
			} else {
				result = false;
			}
			return result;
		} 
	} // isPasswdEqual method
	
	public void updateBoard(BoardVO boardVO) {
		try (SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession()){
			sqlSession.getMapper(BoardMapper.class).updateBoard(boardVO);
			sqlSession.commit();
		}
	} // updateBoard method
	
	public void deleteBoard(int num) {
		try (SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession()){
			sqlSession.getMapper(BoardMapper.class).deleteBoard(num);
			sqlSession.commit();
		}
	} // deleteBoard method
	
	public boolean reInsertBoard(BoardVO boardVO) { 
		boolean inInserted = false;
		try (SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession()){
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			// 조건 re_ref같은그룹 re_seq 큰값은 re_seq+1
			int updatedCount = mapper.updateReplyGroupSequence(boardVO.getReRef(), boardVO.getReSeq());
			// 답글 insert re_ref 그대로 re_lev+1 re_seq+1
			// re_lev는 [답글을 다는 대상글]의 들여쓰기값 +1
			boardVO.setReLev(boardVO.getReLev() +1);  
			// re_seq는 [답글을 다는 대상글]의 글그룹 내 순번값 +1 
			boardVO.setReSeq(boardVO.getReSeq() +1);
			// 답글 insert 수행
			mapper.insertBoard(boardVO);
			// 트랜잭션 작업 모두 성공적으로 수행되면 commit 
			sqlSession.commit();
		}
		// insert, update, delete문 수행후에는
		// sqlSession.commit(); 메소드를 호출해야 DB에 반영됨.
		// sqlSession.commit()이 호출되지 않은 상태에서
		// sqlSession.close() 이 호출되면 rollback 처리되어
		// DB에 적용되지 않음.
		return inInserted;
	} // reInsertBoard method
} 
