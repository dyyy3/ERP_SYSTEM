package test;

import java.awt.*;
import java.awt.event.*;

public class Login extends WindowAdapter implements ActionListener {
	TextField tfid;
	TextField tfpw;
	
	public static void main(String[] args) throws ClassNotFoundException {
		Login login = new Login();
	}
	
	public Login() {
		
		Frame loginFrame = new Frame("Login");
		loginFrame.setSize(500, 300);
		loginFrame.setBackground(Color.DARK_GRAY);
		loginFrame.addWindowListener(new Login());
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
		loginButton.addActionListener(new Login());
		
		p.add(lid);
		p.add(tfid);
		p.add(lpw);
		p.add(tfpw);
		p.add(loginButton);
		
		loginFrame.add(p);
		
		// Frame을 보이게 함
		loginFrame.setVisible(true);
		
	}
	
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(tfid.getText().equals("")) { // ID 미입력
			// 다이얼로그 생성해서 "ID를 입력하세요." 출력
		} else if(tfpw.getText().equals("")) { // Password 미입력
			// 다이얼로그 생성해서 "Password를 입력하세요." 출력
		} else { // 모두 입력되었을때
			Vo_Login vo = new Vo_Login("user", tfid.getText(), tfpw.getText());
			Dao dao = new Dao();
			boolean b = dao.loginTest(vo); // 로그인 결과 true/false를 받는다
			// 다이얼로그 생성해서 로그인 성공 안내
		}
	}
	
	public void loginMessage() {
		
	}
	
	
//	public void actionPerformed(ActionEvent e) {
//		if (id.getText().equals("")) {
//			tfMsg.setText("ID를 입력하세요.");
//		} else if (pwd.getText().equals("")) {
//			tfMsg.setText("Password를 입력하세요.");
//		} else {
//			System.out.println(id.getText());
//			System.out.println(pwd.getText( ));
//			
//			MemberVo vo = new MemberVo(id.getText(), pwd.getText());
//			boolean b = dao.list(vo);
//			tfMsg.setText(String.valueOf(b));
//		}
}


