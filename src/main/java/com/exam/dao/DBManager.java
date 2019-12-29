package com.exam.dao;

import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBManager {
	private static SqlSessionFactory sqlSessionFactory;
	static {
		// �ڹ�7�̻���� try with resuources ���� ����. try��� ������ �ڵ����� close() ȣ���ؼ� �ڿ� �ݾ���
		
		
		try (InputStream is = Resources.getResourceAsStream("mybatis-config.xml")) {
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // static �ʱ�ȭ ���
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory; 
	}
}
