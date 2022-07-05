package loginTest;

public class Vo {
	private String tableName;
	private String id;
	private String password;
	private String tx1;
	private String tx2;
	private String tx3;

	public Vo() {

	}

	public Vo(String tableName, String id, String password) {
		this.tableName = tableName;
		this.id = id;
		this.password = password;
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
}
