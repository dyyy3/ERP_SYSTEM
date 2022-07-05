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
		loginFrame.setResizable(false); // frame 크기 변경 불가능
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		loginFrame.setLocation(screenSize.width/2-250, screenSize.height/2-150);

		// Label, TestField, Button 생성
		
		Label loginLabel = new Label("Log in");
		Label lid = new Label("ID :", Label.RIGHT);
		Label lpw = new Label("Password : ", Label.RIGHT);
		
		tfid = new TextField(10);
		tfpw = new TextField(10);
		tfpw.setEchoChar('*');
		
		Button loginButton = new Button("Log in");
		
		// Label, TestField, Button을 Frame에 추가
		
		loginLabel.setSize(30, 50);
		loginLabel.setForeground(Color.WHITE); // 글자 색상 변경
		Font setBOLD30 = new Font("LoginLabelFont",Font.BOLD, 30);
		loginLabel.setFont(setBOLD30);
		loginFrame.add(loginLabel, "North");
		
		Panel p = new Panel();
		p.setLayout(null);
		
		lid.setSize(70, 30);
		lid.setLocation(50, 60);
		lid.setForeground(Color.WHITE); // 글자 색상 변경
		lid.setFont(setBOLD30);
		lid.setAlignment(Label.LEFT);
		
		tfid.setSize(180, 30);
		tfid.setLocation(130, 60);
		
		lpw.setSize(70, 30);
		lpw.setLocation(50, 100);
		lpw.setForeground(Color.WHITE); // 글자 색상 변경
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
		
		// Frame을 보이게 함
		loginFrame.setVisible(true);
		
	}
	
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(tfid.getText().equals("")) { // ID 미입력
			new LoginDialog(loginFrame, "ID를 입력하세요."); // 다이얼로그 생성해서 "ID를 입력하세요." 출력
		} else if(tfpw.getText().equals("")) { // Password 미입력
			new LoginDialog(loginFrame, "Password를 입력하세요."); // 다이얼로그 생성해서 "Password를 입력하세요." 출력
		} else { // 모두 입력되었을때
			Vo loginVo = new Vo("user_info", tfid.getText(), tfpw.getText());
			Dao dao = new Dao();
			boolean b = dao.loginTest(loginVo); // 로그인 결과 true/false를 받는다
			if(String.valueOf(b).equals("true")) {
				new LoginDialog(loginFrame, "로그인 성공");
				
			} else if(String.valueOf(b).equals("false")) {
				new LoginDialog(loginFrame, "아이디 또는 비밀번호가 일치하지않습니다.");
			}
		}
	}
}