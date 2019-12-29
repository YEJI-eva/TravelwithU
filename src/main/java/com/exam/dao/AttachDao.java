package com.exam.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.exam.dao.mapper.AttachMapper;
import com.exam.vo.AttachVO;

public class AttachDao {
	private static AttachDao instance = new AttachDao();
	
	public static AttachDao getInstance() {
		return instance;
	}
	private AttachDao() {}
	
	public void insertAttach(AttachVO attachVO) {
		try (SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession()){
			AttachMapper mapper = sqlSession.getMapper(AttachMapper.class);
			mapper.insertAttach(attachVO);
			sqlSession.commit();
		}
	} // insertAttch method
	
	public List<AttachVO> getAttaches(int bno) {
		try (SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession()){
			return sqlSession.getMapper(AttachMapper.class).getAttaches(bno);
		}
	} // getAttaches method
	
	public void deleteAttach(int bno) {
		try (SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession()){
			AttachMapper mapper = sqlSession.getMapper(AttachMapper.class);
			mapper.deleteAttachByBno(bno);
			sqlSession.commit();
		}
	} // deleteAttach method
		
	public void deleteAttach(String uuid) {
		try (SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession()){
			AttachMapper mapper = sqlSession.getMapper(AttachMapper.class);
			mapper.deleteAttachByUuid(uuid);
			sqlSession.commit();
		}	 
	} // deleteAttach method
}
