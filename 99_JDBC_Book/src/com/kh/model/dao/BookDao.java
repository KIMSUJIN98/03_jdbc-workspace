package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.model.vo.Book;

public class BookDao {
	
	public int insertRent(Book b) {
		
		int result = 0;
		
		Connection conn = null;
		Statement stmt = null;
		
		String sql = "INSERT INTO BOOK VALUES(SEQ_MNO.NEXTVAL, "
										+ "'" + b.getmId() 		+ "', "
										+ "'" + b.getmPwd() 	+ "', "
										+ "'" + b.getmName()	+ "', "
										+ "'" + b.getmPhone()	+ "', "
										+ "'" + b.getbNo()		+ "', "
										+ "'" + b.getbName()	+ "', "
										+ "'" + b.getwName() 	+ "', "
										+ "'" + b.getpName()	+ "', DEFAULT, DEFAULT)";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			
			if(result > 0) { // 성공시 커밋
				conn.commit();
			}else { // 실패시 롤백
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
										
	}
	
	public ArrayList<Book> selectList() {
		
		ArrayList<Book> list = new ArrayList<Book>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM BOOK";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				
				Book b = new Book();
				
				b.setmNo(rset.getInt("mno"));
				b.setmId(rset.getString("mId"));
				b.setmPwd(rset.getString("mPwd"));
				b.setmName(rset.getString("mName"));
				b.setmPhone(rset.getString("mPhone"));
				b.setbNo(rset.getInt("bNo"));
				b.setbName(rset.getString("bName"));
				b.setwName(rset.getString("wName"));
				b.setpName(rset.getString("pName"));
				b.setRentDate(rset.getDate("RentDate"));
				b.setEndDate(rset.getDate("EndDate"));
				
				list.add(b);
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
		
	}
	
	public Book selectByUserId(String mId) {
		Book b = null;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM BOOK WHERE MID = '" + mId + "'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(sql);
			
			if(rset.next()) {
				
				b = new Book(rset.getInt("mno"),
							 rset.getString("mid"),
							 rset.getString("mpwd"),
							 rset.getString("mname"),
							 rset.getString("mphone"),
							 rset.getInt("bno"),
							 rset.getString("bname"),
							 rset.getString("wname"),
							 rset.getString("pname"),
							 rset.getDate("rentdate"),
							 rset.getDate("enddate")
							);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return b;
	}
	
	public ArrayList<Book> selectByKeyword(String keyword) {
		ArrayList<Book> list = new ArrayList<>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM BOOK WHERE BNAME LIKE '%" + keyword + "%'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				
				Book b = new Book();
				
				b.setmNo(rset.getInt("mno"));
				b.setmId(rset.getString("mid"));
				b.setmPwd(rset.getString("mpwd"));
				b.setmName(rset.getString("mname"));
				b.setmPhone(rset.getString("mphone"));
				b.setbNo(rset.getInt("bno"));
				b.setbName(rset.getString("bname"));
				b.setwName(rset.getString("wname"));
				b.setpName(rset.getString("pname"));
				b.setRentDate(rset.getDate("rentdate"));
				b.setEndDate(rset.getDate("enddate"));
				
				list.add(b);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public int updateEndDate(String mId) {
		int result = 0;
		
		Connection conn = null;
		Statement stmt = null;
		
		String sql = "UPDATE BOOK SET ENDDATE = ENDDATE+7 WHERE MID = '" + mId + "'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public int deleteBook(String mId) {
		int result = 0;
		
		Connection conn = null;
		Statement stmt = null;
		
		String sql = "DELETE FROM BOOK WHERE MID = '" + mId + "'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

}
