package erp;

public class Main {
	public static void main(String[] args) {
		Login login = new Login();
	}
	
	public void createMainScreen(String id) { // �α��ο� ������ ID�� �Ű������� �޴´�
		// �켱 ���� ��ϵ� ID�� ��ġ�ϴ� ��츦 if-else������ ����
		// MainScreen�� Menu�� ���ϴ´�� �����Ǵ��� Ȯ��
		// �� �� �Ű������� ���� ID���� user table���� �о dept_id�� ���� �����Ǵ°����� ����
		
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
