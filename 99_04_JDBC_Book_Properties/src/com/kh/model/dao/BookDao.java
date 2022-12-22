package com.kh.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static com.kh.common.JDBCTemplate.*;
import com.kh.model.vo.Book;

/**
 * BookDao.java
 * @author ksj
 * @since 2022.12.21
 * @version 1.0
 *
 */
public class BookDao {
	
	private Properties prop = new Properties();
	
	public BookDao() {
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * 사용자로부터 관리자 아이디, 비번을 입력받아 일치하는 경우에만 프로그램이 실행되도록 처리하는 메소드
	 * @param conn
	 * @param adminId   : 사용자로부터 입력받은 아이디
	 * @return			: 사용자가 입력한 아이디로 조회된 Book 객체 하나 반환
	 */
	public Book adminOk(Connection conn, String adminId) {
		
		Book b = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("adminOk");
		
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
	
	/**
	 * 사용자로부터 값을 입력받아 새로운 데이터를 추가해주는 메소드
	 * @param conn
	 * @param b			: 사용자로부터 입력받은 Book 객체 하나
	 * @return			: 수행결과 (처리된 행수)
	 */
	public int insertRent(Connection conn, Book b) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertRent");
		
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
	
	/**
	 * 사용자의 전체 리스트 조회 요청을 처리해주는 메소드
	 * @param conn
	 * @return			: 조회된 전체 리스트
	 */
	public ArrayList<Book> selectList(Connection conn) {
		
		ArrayList<Book> list = new ArrayList<Book>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
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
	
	/**
	 * 사용자가 입력한 회원 아이디의 Book 객체 조회 요청을 처리해주는 메소드
	 * @param conn
	 * @param mId		: 사용자가 입력한 회원 아이디
	 * @return			: 사용자가 입력한 회원 아이디를 가진 Book 객체 하나
	 */
	public Book selectByUserId(Connection conn, String mId) {
		Book b = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectByUserId");
		
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
	
	/**
	 * 사용자가 입력한 키워드를 가진 도서명을 모두 조회 처리해주는 메소드
	 * @param conn
	 * @param keyword		: 사용자가 입력한 키워드
	 * @return				: 사용자가 입력한 키워드를 가진 도서명 모두를 담은 리스트
	 */
	public ArrayList<Book> selectByKeyword(Connection conn, String keyword) {
		ArrayList<Book> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectByKeyword");
		
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
	
	/**
	 * 사용자가 입력한 회원 아이디의 반납기한 일주일 연장 요청을 처리해주는 메소드
	 * @param conn
	 * @param mId		: 사용자가 입력한 회원 아이디
	 * @return			: 수행결과 (처리된 행수)
	 */
	public int updateEndDate(Connection conn, String mId) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateEndDate");
		
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
	
	/**
	 * 사용자가 입력한 회원 아이디의 Book 객체 삭제 요청을 처리해주는 메소드
	 * @param conn
	 * @param mId		: 사용자가 입력한 회원 아이디
	 * @return			: 수행결과 (처리된 행수)
	 */
	public int deleteBook(Connection conn, String mId) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteBook");
		
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
