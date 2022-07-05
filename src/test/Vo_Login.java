package test;

public class Vo_Login {
	private String tableName;
	private String id;
	private String password;

	public Vo_Login() {

	}

	public Vo_Login(String tableName, String id, String password) {
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
