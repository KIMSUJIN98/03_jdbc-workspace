package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.model.vo.Book;

public class BookDao {
	
	public Book adminOk(String adminId) {
		
		Book b = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM BOOK WHERE MID = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, adminId);
			
			rset = pstmt.executeQuery();
			
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
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return b;
		
	}
	
	public int insertRent(Book b) {
		
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO BOOK VALUES(SEQ_MNO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, DEFAULT, DEFAULT)";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, b.getmId());
			pstmt.setString(2, b.getmPwd());
			pstmt.setString(3, b.getmName());
			pstmt.setString(4, b.getmPhone());
			pstmt.setInt(5, b.getbNo());
			pstmt.setString(6, b.getbName());
			pstmt.setString(7, b.getwName());
			pstmt.setString(8, b.getpName());
			
			result = pstmt.executeUpdate();
			
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
				pstmt.close();
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
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM BOOK";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
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
				pstmt.close();
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
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM BOOK WHERE MID = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mId);
			
			rset = pstmt.executeQuery();
			
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
				pstmt.close();
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
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM BOOK WHERE BNAME LIKE '%' || ? || '%'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, keyword);
			
			rset = pstmt.executeQuery();
			
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
				pstmt.close();
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
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE BOOK SET ENDDATE = ENDDATE+7 WHERE MID = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mId);
			
			result = pstmt.executeUpdate();
			
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
				pstmt.close();
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
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM BOOK WHERE MID = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mId);
			
			result = pstmt.executeUpdate();
			
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
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

}
