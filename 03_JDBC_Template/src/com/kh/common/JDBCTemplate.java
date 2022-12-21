package com.kh.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// 공통 템플릿 (매번 반복적으로 작성될 코드를 메소드로 정의해둘꺼임)
public class JDBCTemplate {
	// 모든 메소드를 싹 다 static 메소드로 만들거임
	// 이건 실행되자마자 메모리 영역에 다 올라감
	// 싱글톤 패턴 : 메모리 영역에 단 한번만 올려두고 매번 재사용 하는 개념(Math 클래스)			// new를 사용하지 않아도 됨
	
	/**
	 * 1. Connection 객체 생성 (DB와 접속) 한 후 해당 Connection 객체 반환해주는 메소드
	 * @return
	 */
	public static Connection getConnection() {
		
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
		
	}
	
	/**
	 * 2. Commit을 처리해주는 메소드 (Connection 전달 받아서)
	 * @param conn
	 */
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) { // conn이 null이 아니면서, close상태가 아닐 때만 실행하겠다.
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 3. RollBack을 처리해주는 메소드 (Connection 전달 받아서)
	 * @param conn
	 */
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// JDBC용 객체들 전달 받아서 반납처리 해주는 메소드
	
	/**
	 * 4. Statement 관련 객체 전달받아서 반납시켜주는 메소드
	 * @param stmt
	 */
	public static void close(Statement stmt) { // 얘가 부모라서 PreparedStatement 받을 수 있음 // Statement가 PreparedStatement의 부모이므로 다형성이 적용된다!
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 5. Connection 객체 전달 받아서 반납시켜주는 메소드
	 * @param conn
	 */
	public static void close(Connection conn) { // 오버로딩 : 메소드명이 같아도 매개변수가 다르므로 사용가능
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 6. ResultSet 객체 전달 받아서 반납시켜주는 메소드
	 * @param rset
	 */
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
