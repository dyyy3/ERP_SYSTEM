package erp;

//import java.awt.event.*;

public class Main {
	public static void main(String[] args) {
		Login login = new Login();
	}
	
	public void createMainScreen(String id) { // �α��ο� ������ ID�� �Ű������� �޴´�
		Dao dao = new Dao();
		String dept_id = dao.mainFrameMenu(id);
		MainScreen ms = new MainScreen(dept_id);
	}
}
	
