package test;

import java.sql.*;

public class Dao {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "c##green";
	String password = "green1234";
	
	private Connection con;
	private Statement stmt;
	private ResultSet rs;

	public Dao() {
		try {
			Class.forName(driver);
		}catch(ClassNotFoundException c) {
			c.printStackTrace();
		}
	}
	
	public void checkConException() {
		try {
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean loginTest(Vo_Login vo) {
		checkConException();
		try {
			String select = "SELECT * FROM " + vo.getTableName()
			+ "WHERE user_id = '" + vo.getId() + "' AND user_pw = '"
			+ vo.getPassword()+ "'";
			// SELECT * FROM user WHERE user_id = '' AND user_pw = ''
			rs = stmt.executeQuery(select);
			rs.last(); // 커서의 위치를 조회 결과값의 마지막으로 이동
			if(rs.getRow() == 0) {
				// 다이얼로그 생성해서 "ID 또는 Password가 일치하지않습니다." 출력
			} else {
				return true; // 로그인 성공
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false; // 로그인 실패
	}
}