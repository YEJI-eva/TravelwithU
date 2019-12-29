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
		// 자바7이상부터 try with resuources 문법 제공. try블록 끝나면 자동으로 close() 호출해서 자원 닫아줌
		
		
		try (InputStream is = Resources.getResourceAsStream("mybatis-config.xml")) {
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // static 초기화 블록
	
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
	// select 문을 수행한 후 리소스 해제를 위한 메소드

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

	// insert, update, delete문을 수행한 후 리소스 해제를 위한 메소드

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
