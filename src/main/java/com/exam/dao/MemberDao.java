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
