package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;
import com.kh.model.vo.Book;

public class BookDao {
	
	public Book adminOk(Connection conn, String adminId) {
		
		Book b = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM BOOK WHERE MID = ?";
		
			try {
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
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
		return b;
	}
	
	public int insertRent(Connection conn, Book b) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO BOOK VALUES(SEQ_MNO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, DEFAULT, DEFAULT)";
		
			try {
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
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
		return result;
	}
	
	public ArrayList<Book> selectList(Connection conn) {
		
		ArrayList<Book> list = new ArrayList<Book>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM BOOK";
		
			try {
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
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
		return list;
	}
	
	public Book selectByUserId(Connection conn, String mId) {
		Book b = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM BOOK WHERE MID = ?";
		
			try {
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
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
		return b;
	}
	
	public ArrayList<Book> selectByKeyword(Connection conn, String keyword) {
		ArrayList<Book> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM BOOK WHERE BNAME LIKE '%' || ? || '%'";
		
			try {
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
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
		return list;
	}
	
	public int updateEndDate(Connection conn, String mId) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE BOOK SET ENDDATE = ENDDATE+7 WHERE MID = ?";
		
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mId);
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
		return result;
	}
	
	public int deleteBook(Connection conn, String mId) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM BOOK WHERE MID = ?";
		
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mId);
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
		return result;
	}

}
