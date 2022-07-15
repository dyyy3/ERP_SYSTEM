package test;

public class Test {
	public static void main(String[] args) {
		String s1 = "20200101";
		String s2 = "20200111";
		String s3 = "20201101";
		String s4 = "20201111";

		int i1 = Integer.valueOf(s1);
		int i2 = Integer.valueOf(s2);
		int i3 = Integer.valueOf(s3);
		int i4 = Integer.valueOf(s4);
		
		if(i2 > i1) {
			System.out.println("i2 > i1");
		}
	}
}
