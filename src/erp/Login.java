package erp;

import java.awt.*;
import java.awt.event.*;

public class Login extends WindowAdapter implements ActionListener {
	Frame loginFrame;
	TextField tfid;
	TextField tfpw;
	
	public Login() {
		loginFrame = new Frame("Login");
		loginFrame.setSize(500, 300);
		loginFrame.setBackground(Color.DARK_GRAY);
		loginFrame.addWindowListener(this);
		loginFrame.setResizable(false); // frame ũ�� ���� �Ұ���
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		loginFrame.setLocation(screenSize.width/2-250, screenSize.height/2-150);

		// Label, TestField, Button ����
		
		Label loginLabel = new Label("Log in");
		Label lid = new Label("ID :", Label.RIGHT);
		Label lpw = new Label("Password : ", Label.RIGHT);
		
		tfid = new TextField(10);
		tfpw = new TextField(10);
		tfpw.setEchoChar('*');
		
		Button loginButton = new Button("Log in");
		
		// Label, TestField, Button�� Frame�� �߰�
		
		loginLabel.setSize(30, 50);
		loginLabel.setForeground(Color.WHITE); // ���� ���� ����
		Font setBOLD30 = new Font("LoginLabelFont",Font.BOLD, 30);
		loginLabel.setFont(setBOLD30);
		loginFrame.add(loginLabel, "North");
		
		Panel p = new Panel();
		p.setLayout(null);
		
		lid.setSize(70, 30);
		lid.setLocation(50, 60);
		lid.setForeground(Color.WHITE); // ���� ���� ����
		lid.setFont(setBOLD30);
		lid.setAlignment(Label.LEFT);
		
		tfid.setSize(180, 30);
		tfid.setLocation(130, 60);
		
		lpw.setSize(70, 30);
		lpw.setLocation(50, 100);
		lpw.setForeground(Color.WHITE); // ���� ���� ����
		lpw.setFont(setBOLD30);
		lpw.setAlignment(Label.LEFT);
		
		tfpw.setSize(180, 30);
		tfpw.setLocation(130, 100);
		
		loginButton.setSize(100, 70);
		loginButton.setLocation(320, 60);
		loginButton.addActionListener(this);
		
		p.add(lid);
		p.add(tfid);
		p.add(lpw);
		p.add(tfpw);
		p.add(loginButton);
		
		loginFrame.add(p, "Center");
		
		// Frame�� ���̰� ��
		loginFrame.setVisible(true);
	}
	
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(tfid.getText().equals("")) { // ID ���Է�
			new LoginDialog(loginFrame, "ID�� �Է��ϼ���."); // ���̾�α� �����ؼ� "ID�� �Է��ϼ���." ���
		} else if(tfpw.getText().equals("")) { // Password ���Է�
			new LoginDialog(loginFrame, "Password�� �Է��ϼ���."); // ���̾�α� �����ؼ� "Password�� �Է��ϼ���." ���
		} else { // ��� �ԷµǾ�����
			Vo loginVo = new Vo("user_info", tfid.getText(), tfpw.getText());
			Dao dao = new Dao();
			boolean b = dao.loginTest(loginVo); // �α��� ��� true/false�� �޴´�
			if(String.valueOf(b).equals("true")) {
				new LoginDialog(loginFrame, "�α��� ����");
				loginFrame.dispose(); // �α��� ���� �� �α��� �������� �ݴ´�
				Main main = new Main();
				main.createMainScreen(loginVo.getField_1()); // �α��ο� ������ ID�� �Ѱ��ش�
				// �α��� ������ ���� ������ setvisible true
			} else if(String.valueOf(b).equals("false")) {
				new LoginDialog(loginFrame, "���̵� �Ǵ� ��й�ȣ�� ��ġ�����ʽ��ϴ�.");
			}
		}
	}
}