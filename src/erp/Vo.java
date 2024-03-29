package erp;

public class Vo {
	private String tableName;
	private String field_1;
	private String field_2;
	private String field_3;
	private String field_4;
	private String field_5;
	private String field_6;
	private String field_7;
	private String field_8;
	private String field_9;
	private String field_10;
	private String field_11;
	private String field_12;
	private String field_13;
	private String field_14;
	private String field_15;
	private String field_16;
	private int value_1;
	private int value_2;
	private int value_3;
	private int value_4;
	private int value_5;
	private int value_6;
	private int value_7;
	private int value_8;
	private int value_9;
	private int value_10;
	private int value_11;
	private int value_12;

	public Vo() {

	}
	
	// Tab_1101, Tab_1401
	public Vo(String tableName) {
		this.tableName = tableName;
	}

	// Tab_1101, Tab_1201, Tab_1501
	public Vo(String tableName, String field_1) {
		this.tableName = tableName;
		this.field_1 = field_1;
	}
	
	// login, Tab_1501
	public Vo(String tableName, String field_1, String field_2) {
		this.tableName = tableName;
		this.field_1 = field_1;
		this.field_2 = field_2;
	}
	
	// Tab_1301
	public Vo(String field_1, String field_2, int value_1) {
		this.field_1 = field_1;
		this.field_2 = field_2;
		this.value_1 = value_1;
	}
	
	// Tab_1101, Tab_1201, Tab_1202, Tab_1401
	public Vo(String tableName, String field_1, String field_2, String field_3) {
		this.tableName = tableName;
		this.field_1 = field_1;
		this.field_2 = field_2;
		this.field_3 = field_3;
	}
	
	// Tab_1301
	public Vo(String tableName, String field_1, String field_2, int value_1) {
		this.tableName = tableName;
		this.field_1 = field_1;
		this.field_2 = field_2;
		this.value_1 = value_1;
	}
	
	// Tab_1501
	public Vo(String field_1, String field_2, int value_1, String field_3) {
		this.field_1 = field_1;
		this.field_2 = field_2;
		this.value_1 = value_1;
		this.field_3 = field_3;
	}
	
	// Tab_1202, Tab_1301, Tab_1302
	public Vo(String tableName, String field_1, int value_1,
			String field_2, String field_3)
	{
		this.tableName = tableName;
		this.field_1 = field_1;
		this.value_1 = value_1;
		this.field_2 = field_2;
		this.field_3 = field_3;
	}

	// Tab_1201, Tab_1202, Tab_1301, Tab_1303
	public Vo(String field_1, String field_2, String field_3,
			String field_4, String field_5)
	{
		this.field_1 = field_1;
		this.field_2 = field_2;
		this.field_3 = field_3;
		this.field_4 = field_4;
		this.field_5 = field_5;
	}
	
	// Tab_1301
	public Vo(String field_1, String field_2, String field_3,
			int value_1, String field_4) 
	{
		this.field_1 = field_1;
		this.field_2 = field_2;
		this.field_3 = field_3;
		this.value_1 = value_1;
		this.field_4 = field_4;
	}

	// Tab_1301, Tabl_1401
	public Vo(String tableName, String field_1, String field_2,
			String field_3, String field_4, String field_5)
	{
		this.tableName = tableName;
		this.field_1 = field_1;
		this.field_2 = field_2;
		this.field_3 = field_3;
		this.field_4 = field_4;
		this.field_5 = field_5;
	}
	
	// Tab_1301, Tab_1302
	public Vo(int value_1, String field_1, String field_2,
			String field_3, String field_4, int value_2) 
	{
		this.value_1 = value_1;
		this.field_1 = field_1;
		this.field_2 = field_2;
		this.field_3 = field_3;
		this.field_4 = field_4;
		this.value_2 = value_2;
	}
	
	// Tab_1501
	public Vo(String field_1, String field_2,
			int value_1, int value_2,
			int value_3, int value_4)
	{
		this.field_1 = field_1;
		this.field_2 = field_2;
		this.value_1 = value_1;
		this.value_2 = value_2;
		this.value_3 = value_3;
		this.value_4 = value_4;
	}

	// Tab_1202
	public Vo(String field_1, int value_1,
			int value_2, int value_3, int value_4,
			int value_5, int value_6)
	{
		this.field_1 = field_1;
		this.value_1 = value_1;
		this.value_2 = value_2;
		this.value_3 = value_3;
		this.value_4 = value_4;
		this.value_5 = value_5;
		this.value_6 = value_6;
	}
	
	// Tab_1301, Tab_1303, Tab_1401
	public Vo(String field_1, String field_2, String field_3,
			String field_4, String field_5, String field_6,
			String field_7)
	{
		this.field_1 = field_1;
		this.field_2 = field_2;
		this.field_3 = field_3;
		this.field_4 = field_4;
		this.field_5 = field_5;
		this.field_6 = field_6;
		this.field_7 = field_7;
	}
	
	// Tab_1202
	public Vo(String tableName, String field_1, int value_1,
			String field_2, String field_3,
			String field_4, String field_5)
	{
		this.tableName = tableName;
		this.field_1 = field_1;
		this.value_1 = value_1;
		this.field_2 = field_2;
		this.field_3 = field_3;
		this.field_4 = field_4;
		this.field_5 = field_5;
	}
	
	// Tab_1201
	public Vo(String field_1, String field_2, String field_3,
			String field_4, String field_5, String field_6,
			String field_7, String field_8)
	{
		this.field_1 = field_1;
		this.field_2 = field_2;
		this.field_3 = field_3;
		this.field_4 = field_4;
		this.field_5 = field_5;
		this.field_6 = field_6;
		this.field_7 = field_7;
		this.field_8 = field_8;
	}
	
	// Tab_1301
	public Vo(int value_1, String field_1, String field_2, String field_3,
			String field_4, String field_5, String field_6, int value_2) 
	{
		this.value_1 = value_1;
		this.field_1 = field_1;
		this.field_2 = field_2;
		this.field_3 = field_3;
		this.field_4 = field_4;
		this.field_5 = field_5;
		this.field_6 = field_6;
		this.value_2 = value_2;
	}
	
	// Tab_1201, Tab_1301
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
	
	// Tab_1301
	public Vo(String field_1, int value_1, int value_2, int value_3,
			int value_4, int value_5, int value_6, String field_2)
	{
		this.field_1 = field_1;
		this.value_1 = value_1;
		this.value_2 = value_2;
		this.value_3 = value_3;
		this.value_4 = value_4;
		this.value_5 = value_5;
		this.value_6 = value_6;
		this.field_2 = field_2;
	}
	
	// Tab_1201
	public Vo(String tableName, String field_1, String field_2,
			String field_3, String field_4, String field_5,
			String field_6, String field_7, String field_8,
			String field_9, String field_10)
	{
		this.tableName = tableName;
		this.field_1 = field_1;
		this.field_2 = field_2;
		this.field_3 = field_3;
		this.field_4 = field_4;
		this.field_5 = field_5;
		this.field_6 = field_6;
		this.field_7 = field_7;
		this.field_8 = field_8;
		this.field_9 = field_9;
		this.field_10 = field_10;
	}
	
	// Tab_1501
	public Vo(String tableName,
			String field_1, int value_1,
			String field_2, int value_2,
			String field_3, int value_3,
			String field_4, int value_4,
			String field_5, String field_6, 
			String field_7, String field_8 
			)
	{
		this.tableName = tableName;
		this.field_1 = field_1;
		this.value_1 = value_1;
		this.field_2 = field_2;
		this.value_2 = value_2;
		this.field_3 = field_3;
		this.value_3 = value_3;
		this.field_4 = field_4;
		this.value_4 = value_4;
		this.field_5 = field_5;
		this.field_6 = field_6;
		this.field_7 = field_7;
		this.field_8 = field_8;
	}
	
	// Tab_1401
	public Vo(String field_1, String field_2, String field_3, String field_4,
			int value_1, int value_2, int value_3,
			int value_4, int value_5, int value_6,
			int value_7, int value_8, int value_9,
			int value_10, int value_11, int value_12)
	{
		this.field_1 = field_1;
		this.field_2 = field_2;
		this.field_3 = field_3;
		this.field_4 = field_4;
		this.value_1 = value_1;
		this.value_2 = value_2;
		this.value_3 = value_3;
		this.value_4 = value_4;
		this.value_5 = value_5;
		this.value_6 = value_6;
		this.value_7 = value_7;
		this.value_8 = value_8;
		this.value_9 = value_9;
		this.value_10 = value_10;
		this.value_11 = value_11;
		this.value_12 = value_12;
	}
	
	// Tab_1201
	public Vo(String tableName, String field_1, String field_2,
			String field_3, String field_4, String field_5,
			String field_6, String field_7, String field_8,
			String field_9, String field_10, String field_11,
			String field_12, String field_13, String field_14,
			String field_15, String field_16)
	{
		this.tableName = tableName;
		this.field_1 = field_1;
		this.field_2 = field_2;
		this.field_3 = field_3;
		this.field_4 = field_4;
		this.field_5 = field_5;
		this.field_6 = field_6;
		this.field_7 = field_7;
		this.field_8 = field_8;
		this.field_9 = field_9;
		this.field_10 = field_10;
		this.field_11 = field_11;
		this.field_12 = field_12;
		this.field_13 = field_13;
		this.field_14 = field_14;
		this.field_15 = field_15;
		this.field_16 = field_16;
	}

	// Tab_1202
	public Vo(String tableName,
			String field_7, String field_8, String field_1, int value_1,
			String field_2, int value_2, String field_3, int value_3,
			String field_4, int value_4, String field_5, int value_5,
			String field_6, int value_6)
	{
		this.tableName = tableName;
		this.field_7 = field_7;
		this.field_8 = field_8;
		this.field_1 = field_1;
		this.value_1 = value_1;
		this.field_2 = field_2;
		this.value_2 = value_2;
		this.field_3 = field_3;
		this.value_3 = value_3;
		this.field_4 = field_4;
		this.value_4 = value_4;
		this.field_5 = field_5;
		this.value_5 = value_5;
		this.field_6 = field_6;
		this.value_6 = value_6;
		
	}
	
	
	
	
	public String getTableName() {
		return tableName;
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
	
	public String getField_10() {
		return field_10;
	}
	
	public String getField_11() {
		return field_11;
	}
	
	public String getField_12() {
		return field_12;
	}
	
	public String getField_13() {
		return field_13;
	}
	
	public String getField_14() {
		return field_14;
	}
	
	public String getField_15() {
		return field_15;
	}
	
	public String getField_16() {
		return field_16;
	}
	
	public int getValue_1() {
		return value_1;
	}
	
	public int getValue_2() {
		return value_2;
	}
	
	public int getValue_3() {
		return value_3;
	}
	
	public int getValue_4() {
		return value_4;
	}
	
	public int getValue_5() {
		return value_5;
	}
	
	public int getValue_6() {
		return value_6;
	}
	
	public int getValue_7() {
		return value_7;
	}
	
	public int getValue_8() {
		return value_8;
	}
	
	public int getValue_9() {
		return value_9;
	}
	
	public int getValue_10() {
		return value_10;
	}
	
	public int getValue_11() {
		return value_11;
	}
	
	public int getValue_12() {
		return value_12;
	}
}