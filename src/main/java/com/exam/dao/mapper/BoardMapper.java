package com.exam.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.exam.vo.BoardVO;

public interface BoardMapper {
	public int nextBoardNum();
	public int insertBoard(BoardVO boardVO);
	public List<BoardVO> getBoards(@Param("startRow") int startRow, @Param("pageSize") int pageSize, @Param("search") String search);
	public int getBoardCount(String search);
	public void updateReadcount(int num);
	public BoardVO getBoard(int num);
	public int countByNumPasswd(@Param("num") int num, @Param("passwd") String passwd);
	public void updateBoard(BoardVO boardVO);
	public void deleteBoard(int num);
	public int updateReplyGroupSequence(@Param("reRef") int reRef, @Param("reSeq") int reSeq);
	public List<BoardVO> getBoardList(BoardVO boardVO);
}

