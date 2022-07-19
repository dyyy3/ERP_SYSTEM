package test;

public class Test {
	public static void main(String[] args) {
		String offer_num = "22-7-1";
		String client_name = "Blue Stone";
		String product_code = "1-G031-CCN006-020";
		
		String[] check = product_code.split("-");
		
		System.out.println(check.length);

		// check.length가 3이면 offer_num
		// 1이면 clinet_name
		// 4면 product_code
	}
}
