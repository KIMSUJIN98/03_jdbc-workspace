package test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TestRun {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			// 실행 메뉴 구현하기
			System.out.println("============================");
			System.out.println("PRODUCT 테이블에서 작업하기");
			System.out.println("1. SELECT");
			System.out.println("2. INSERT");
			System.out.println("3. DELETE");
			System.out.println("4. 종료");
			System.out.println("============================");
			System.out.print("수행할 번호 입력: ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			Connection conn = null;		// DB의 연결정보를 보관할 객체
			Statement stmt = null;		// sql문 전달해서 실행 후 결과 받는 객체
			String sql = null;			// 앞으로 실행할 SQL문을 담을 변수
			int result = 0;				// 결과(처리된 행수)를 받아줄 변수
			
			switch(menu) {
			case 1:	// 1. SELECT
				ResultSet rset = null; 		// select문 실행하여 조회된 결과값들이 처음에 실질적으로 담길 객체
				
				sql = "SELECT * FROM PRODUCT";
				
				try {
					// 1) jdbc driver 등록
					Class.forName("oracle.jdbc.driver.OracleDriver");
				
					// 2) Connection 객체 생성
					conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
				
					// 3) Statement 객체 생성
					stmt = conn.createStatement();
					
					// 4,5) sql문 전달해서 실행 후 결과 받기 (ResultSet 객체)
					rset = stmt.executeQuery(sql);
					
					// 6)
					while(rset.next()) {
						int pno = rset.getInt("PNO");
						String pname = rset.getString("PNAME");
						int price = rset.getInt("PRICE");
						Date reg_date = rset.getDate("REG_DATE");
						
						System.out.println(pno + ", " + pname + ", " + price + ", " + reg_date);
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
				
				break;
				
			case 2:	// 2. INSERT
				System.out.print("제품이름 : ");
				String name = sc.nextLine();
				
				System.out.print("제품가격 : ");
				int price = sc.nextInt();
				sc.nextLine();
				
				sql = "INSERT INTO PRODUCT VALUES(SEQ_PNO.NEXTVAL, '" + name + "', " + price + ", SYSDATE)";
				
				try {
					// 1) jdbc driver 등록
					Class.forName("oracle.jdbc.driver.OracleDriver");
					
					// 2) Connection 객체 생성 : DB에 연결(url, 계정명, 비번)
					conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
					
					// 3) Statement 객체 생성
					stmt = conn.createStatement();
					
					// 4,5) sql문 전달하면서 실행 후 결과받기 (처리된 행수)
					result = stmt.executeUpdate(sql);
					
					// 6) 트랜젝션 처리
					if(result > 0) { 		// 성공했을 경우 commit
						conn.commit();
					}else { 				// 실패했을 경우 rollback
						conn.rollback();
					}
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						// 7) 다 쓴 JDBC 객체 자원 반납 (생성된 역순으로)
						stmt.close();
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				if(result > 0) {
					System.out.println("PRODUCT 테이블에 정상적으로 INSERT 되었습니다.");
				}else {
					System.out.println("PRODUCT 테이블에 INSERT 하지 못했습니다.");
				}
				
				break;
				
			case 3:	// 3. DELETE
				System.out.print("삭제할 제품번호 : ");
				int num = sc.nextInt();
				sc.nextLine();
				
				sql = "DELETE FROM PRODUCT WHERE PNO =" + num;
				
				try {
					// 1) jdbc driver 등록
					Class.forName("oracle.jdbc.driver.OracleDriver");
					
					// 2) Connection 객체 생성 : DB에 연결(url, 계정명, 비번)
					conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
					
					// 3) Statement 객체 생성
					stmt = conn.createStatement();
					
					// 4,5) sql문 전달하면서 실행 후 결과받기 (처리된 행수)
					result = stmt.executeUpdate(sql);
					
					// 6) 트랜젝션 처리
					if(result > 0) { 		// 성공했을 경우 commit
						conn.commit();
					}else { 				// 실패했을 경우 rollback
						conn.rollback();
					}
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						// 7) 다 쓴 JDBC 객체 자원 반납 (생성된 역순으로)
						stmt.close();
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				if(result > 0) {
					System.out.println("PRODUCT 테이블에서 정상적으로 DELETE 되었습니다.");
				}else {
					System.out.println("PRODUCT 테이블에서 DELETE 하지 못했습니다.");
				}
				
				break;
				
			case 4:	// 4. 종료
				System.out.println("프로그램을 종료합니다.");
				return;
			}
		}
		
		
	}

}
