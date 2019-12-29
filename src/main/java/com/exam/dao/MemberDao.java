package com.exam.dao;

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
