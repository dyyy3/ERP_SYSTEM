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
			rs.last(); // Ŀ���� ��ġ�� ��ȸ ������� ���������� �̵�
			if(rs.getRow() == 0) {
				// ���̾�α� �����ؼ� "ID �Ǵ� Password�� ��ġ�����ʽ��ϴ�." ���
			} else {
				return true; // �α��� ����
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false; // �α��� ����
	}
}