package erp;

import java.awt.*;
import java.awt.event.*;

class LoginDialog implements ActionListener {
	private Frame parentFrame;
	private String message;
	private Dialog loginResult;
	
	public LoginDialog(Frame parentFrame, String massage) {
		this.parentFrame = parentFrame;
		this.message = massage;
		
		loginResult = new Dialog(this.parentFrame, "�α��� �޽���", true);
		loginResult.setBackground(Color.WHITE);
		loginResult.setSize(300, 200);
		loginResult.setResizable(false); // frame ũ�� ���� �Ұ���
		loginResult.setLayout(null);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		loginResult.setLocation(screenSize.width/2-150, screenSize.height/2-100);
		
		Label loginRsMsg = new Label(this.message);
		loginRsMsg.setAlignment(Label.CENTER);
		loginRsMsg.setBackground(Color.WHITE);
		loginRsMsg.setBounds(25, 50, 250, 50);
		
		Button ok = new Button("OK");
		ok.setBounds(115, 140, 70, 30);
		ok.addActionListener(this);
		
		loginResult.add(loginRsMsg);
		loginResult.add(ok);
		
		loginResult.setVisible(true);
		
	}
		public void actionPerformed(ActionEvent e) {
			loginResult.dispose();
		}
}
