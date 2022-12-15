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

		Connection conn = null; // DB의 연결정보를 보관할 객체
		Statement stmt = null; // sql문 전달해서 실행 후 결과 받는 객체
		ResultSet rset = null; // select문 실행하여 조회된 결과값들이 처음에 실질적으로 담길 객체
		int result = 0; // 결과(처리된 행수)를 받아줄 변수
		boolean dml = false;
		String sql = null; // 앞으로 실행할 SQL문을 담을 변수
		String type = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");

			stmt = conn.createStatement();

			while (true) { // -- while 반복문 시작
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

				switch (menu) { // -- switch문 시작
				case 1: // 1. SELECT
					dml = false;

					sql = "SELECT * FROM PRODUCT";

					rset = stmt.executeQuery(sql);

					while (rset.next()) {
						int pno = rset.getInt("PNO");
						String pname = rset.getString("PNAME");
						int price = rset.getInt("PRICE");
						Date reg_date = rset.getDate("REG_DATE");

						System.out.println(pno + ", " + pname + ", " + price + ", " + reg_date);
					}
					break;

				case 2: // 2. INSERT
					dml = true;
					type = "INSERT";

					System.out.print("제품이름 : ");
					String name = sc.nextLine();

					System.out.print("제품가격 : ");
					int price = sc.nextInt();
					sc.nextLine();

					sql = "INSERT INTO PRODUCT VALUES(SEQ_PNO.NEXTVAL, '" + name + "', " + price + ", SYSDATE)";
					break;

				case 3: // 3. DELETE
					dml = true;
					type = "DELETE";

					System.out.print("삭제할 제품번호 : ");
					int num = sc.nextInt();
					sc.nextLine();

					sql = "DELETE FROM PRODUCT WHERE PNO =" + num;
					break;

				case 4: // 4. 종료
					System.out.println("프로그램을 종료합니다.");
					return;
				} // -- switch문 종료

				if (dml) {
					result = stmt.executeUpdate(sql);

					if (result > 0) { // 성공했을 경우
						conn.commit();
						System.out.printf("PRODUCT 테이블에서 정상적으로 %s 되었습니다.\n", type);
					} else { // 실패했을 경우
						conn.rollback();
						System.out.printf("PRODUCT 테이블에서 %s 하지 못했습니다.\n", type);
					}
				}

			} // -- while 반복문 종료

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close(); // rset을 전역으로 선언 => DML만 수행한 경우에도 닫아야함.
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				// SELECT없이 DML만 수행한 경우 예외처리 위함.
			}
		}
	}
}
