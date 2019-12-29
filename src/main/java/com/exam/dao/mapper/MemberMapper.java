package com.exam.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.exam.vo.MemberVO;

public interface MemberMapper {	
	@Select("SELECT COUNT(*) AS cnt FROM yeji.member WHERE id = #{id}")
	public int countMemberById(String id);
	public int insertMember (MemberVO vo);
	@Select("SELECT * FROM yeji.member WHERE id = #{id}")
	public MemberVO getMember(String id);
	@Select("SELECT * FROM yeji.member WHERE id = #{id}")
	public MemberVO getMemberById(String id);
	// 전체회원정보 가져오기
	@Select("SELECT * FROM yeji.member ORDER BY id ASC")
	public List<MemberVO> getMembers();
	@Update("UPDATE yeji.member SET name=#{name}, age=#{age}, gender=#{gender}, email=#{email} WHERE id=#{id}")
	public void updateMember(MemberVO memberVO);
	@Delete("DELETE FROM yeji.member WHERE id = #{id}")
	public List<MemberVO> deleteMember(String[] id); 
	

}
