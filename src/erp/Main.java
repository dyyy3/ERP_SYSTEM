package erp;

public class Main {
	public static void main(String[] args) {
		Login login = new Login();
	}
	
	public void createMainScreen(String id) { // 로그인에 성공한 ID를 매개변수로 받는다
		// 우선 현재 등록된 ID와 일치하는 경우를 if-else문으로 만들어서
		// MainScreen의 Menu가 원하는대로 생성되는지 확인
		// 그 후 매개변수로 받은 ID값을 user table에서 읽어서 dept_id에 따라 생성되는것으로 변경
		
		if(id.equals("202201000")) {
			MainScreen ms = new MainScreen("0");
		}else if(id.equals("201804003")) {
			MainScreen ms = new MainScreen("10");
		}else if(id.equals("200607001")) {
			MainScreen ms = new MainScreen("20");
		}else if(id.equals("201506005")) {
			MainScreen ms = new MainScreen("30");
		}else if(id.equals("200908002")) {
			MainScreen ms = new MainScreen("40");
		}else if(id.equals("202011004")) {
			MainScreen ms = new MainScreen("50");
		}
	}
}
