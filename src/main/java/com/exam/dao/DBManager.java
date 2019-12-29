package com.exam.dao;

<<<<<<< HEAD
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
=======
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBManager {

	public static Connection getConnection() throws Exception {
		Connection con = null;
		Context initContext = new InitialContext();
		Context envContext = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/mysqldb");
//		Context context = new InitialContext();
//		DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/mysqldb");

		con = ds.getConnection();
		return con;
	}
	// select ���� ������ �� ���ҽ� ������ ���� �޼ҵ�

	public static void close(Connection con, Statement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}// close method

	// insert, update, delete���� ������ �� ���ҽ� ������ ���� �޼ҵ�

	public static void close(Connection con, Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	} // close method

>>>>>>> branch 'master' of https://github.com/YEJI-eva/TravelwithU.git
}
