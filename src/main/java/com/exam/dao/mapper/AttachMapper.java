package com.exam.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import com.exam.vo.AttachVO;

public interface AttachMapper {
	// 첨부파일정보 입력하기 메소드
	public void insertAttach(AttachVO attachVO);
	
	// 글번호에 해당하는 첨부파일정보 가져오기
	@Select("SELECT * FROM attach WHERE bno = #{bno}")
	public List<AttachVO> getAttaches(int bno);
	@Delete("DELETE FROM attach WHERE bno = #{bno}")
	public void deleteAttachByBno(int bno);
	@Delete("DELETE FROM attach WHERE uuid = #{uuid}")
	public void deleteAttachByUuid(String uuid);
}
