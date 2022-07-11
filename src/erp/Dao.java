package erp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dao {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "c##green";
	String password = "green1234";
	
	private Connection con;
	private Statement stmt;
	private ResultSet rs;

	public void checkConException() {
		try {
			Class.forName(driver);
		}catch(ClassNotFoundException c) {
			c.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Login
	public boolean loginTest(Vo loginVo) {
		checkConException();
		try {
			String select = "SELECT * FROM " + loginVo.getTableName()
			+ " WHERE user_id = '" + loginVo.getId() + "' AND user_pw = '"
			+ loginVo.getPassword()+ "'";
			// SELECT * FROM user_id WHERE user_id = '' AND user_pw = ''
			rs = stmt.executeQuery(select);
			rs.last(); // 커서의 위치를 조회 결과값의 마지막으로 이동
			if(rs.getRow() == 0) {
				return false; // 로그인 실패
			} else {
				return true; // 로그인 성공
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false; // 로그인 실패
	}

	public String mainFrameMenu(String id) {
		String dept_id = "";
		checkConException();
		try {
			String select = "SELECT dept_id FROM user_info WHERE user_id = '" + id + "'";
			// SELECT dept_id FROM user_info WHERE user_id = ''
			rs = stmt.executeQuery(select);

			while(rs.next()) {
				dept_id = rs.getString("dept_id");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dept_id;
	}
	
	// Tab_1101
	public String[] select(Vo vo) {
		List<String> list = new ArrayList<>(); // 쿼리문으로 얻은 값을 저장. 행의 길이를 모르므로 list로 받는다
		String[] result = null; // list로 받은 값을 String 배열로 바꿔서 return

		checkConException();
		try {
			String select = "SELECT " + vo.getTableName() + " FROM " + vo.getTableName();
			// SELECT asset_code FROM asset
			rs = stmt.executeQuery(select);
			
			while(rs.next()) {
				list.add(rs.getString(1));
			}
			result = list.toArray(new String[list.size()]); // list의 size와 동일한 길이의 String 배열을 만든다
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// Tab_1101
	public String getCode(String table, String field, String record) {
		String code ="";
		checkConException();
		try {
			String select = "SELECT " + field + " FROM " + table + " WHERE " + table + " = '" + record + "'";
			// SELECT STONE_NAME_CODE FROM STONE_NAME WHERE STONE_NAME = ''
			rs = stmt.executeQuery(select);
			
			while(rs.next()){
				code = rs.getString(field);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return code;
	}
	
	public boolean insertProductList(Vo vo) {
		boolean b2 = true;
		checkConException();
		try {
			String insert = "INSERT INTO PRODUCT_LIST (PRODUCT_CODE, PRODUCT_NAME, ASSET_CODE,"
					+ "STONE_TYPE_CODE, STONE_NAME_CODE, SLAB_TYPE_CODE,"
					+ "COUNTRY_CODE, SURFACE_CODE, THICKNESS_CODE)"
					+ " VALUES('" + vo.getField_1() + "', '" + vo.getField_2() + "', '" + vo.getField_3() + "',"
					+ " '" + vo.getField_4() + "', '" + vo.getField_5() + "', '" + vo.getField_6() + "',"
					+ " '" + vo.getField_7() + "', '" + vo.getField_8() + "', '" + vo.getField_9() + "')";
			stmt.executeQuery(insert);
//			rs = stmt.executeQuery(insert);
		}catch(Exception e) {
			e.printStackTrace();
			b2 = false;
		}
		return b2;
	}
}