package erp;

//import java.awt.event.*;

public class Main {
	public static void main(String[] args) {
		Login login = new Login();
	}
	
	public void createMainScreen(String id) { // 로그인에 성공한 ID를 매개변수로 받는다
		Dao dao = new Dao();
		String dept_id = dao.mainFrameMenu(id);
		MainScreen ms = new MainScreen(dept_id);
	}
}
	
