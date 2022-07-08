package tabTest;

public class Vo {
	private String tableName;
	private String id;
	private String password;
	private String table1;
	private String field1;

	public Vo() {

	}

	public Vo(String tableName, String id, String password) {
		this.tableName = tableName;
		this.id = id;
		this.password = password;
	}

	public Vo(String table1, String field1) {
		this.table1 = table1;
		this.field1 = field1;
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
	
	public String getTable1() {
		return table1;
	}
	
	public String getField1() {
		return field1;
	}
}
