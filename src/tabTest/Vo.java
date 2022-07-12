package tabTest;

public class Vo {
	private String tableName;
	private String id;
	private String password;
	private String field_1;
	private String field_2;
	private String field_3;
	private String field_4;
	private String field_5;
	private String field_6;
	private String field_7;
	private String field_8;
	private String field_9;

	public Vo() {

	}
	
	// login
	public Vo(String tableName, String id, String password) {
		this.tableName = tableName;
		this.id = id;
		this.password = password;
	}
	
	// Tab_1101
	public Vo(String tableName) {
		this.tableName = tableName;
	}
	
	// Tab_1201
	public Vo(String tableName, String field_1) {
		this.tableName = tableName;
		this.field_1 = field_1;
	}
	
	// Tab_1101
	public Vo(String field_1, String field_2, String field_3,
			String field_4, String field_5, String field_6,
			String field_7, String field_8, String field_9)
	{
		this.field_1 = field_1;
		this.field_2 = field_2;
		this.field_3 = field_3;
		this.field_4 = field_4;
		this.field_5 = field_5;
		this.field_6 = field_6;
		this.field_7 = field_7;
		this.field_8 = field_8;
		this.field_9 = field_9;
	}
	
	public String getTableName() {
		return tableName;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}
	
	public String getField_1() {
		return field_1;
	}
	
	public String getField_2() {
		return field_2;
	}
	
	public String getField_3() {
		return field_3;
	}
	
	public String getField_4() {
		return field_4;
	}
	
	public String getField_5() {
		return field_5;
	}
	
	public String getField_6() {
		return field_6;
	}
	
	public String getField_7() {
		return field_7;
	}
	
	public String getField_8() {
		return field_8;
	}
	
	public String getField_9() {
		return field_9;
	}
	
}
