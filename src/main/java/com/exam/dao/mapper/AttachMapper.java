package com.exam.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import com.exam.vo.AttachVO;

public interface AttachMapper {
	// ÷���������� �Է��ϱ� �޼ҵ�
	public void insertAttach(AttachVO attachVO);
	
	// �۹�ȣ�� �ش��ϴ� ÷���������� ��������
	@Select("SELECT * FROM attach WHERE bno = #{bno}")
	public List<AttachVO> getAttaches(int bno);
	@Delete("DELETE FROM attach WHERE bno = #{bno}")
	public void deleteAttachByBno(int bno);
	@Delete("DELETE FROM attach WHERE uuid = #{uuid}")
	public void deleteAttachByUuid(String uuid);
}
