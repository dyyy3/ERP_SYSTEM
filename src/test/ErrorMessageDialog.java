package test;

import java.awt.*;
import java.awt.event.*;

public class ErrorMessageDialog implements ActionListener {
	private String message;
	private String title;
	private Dialog result;
	
	public ErrorMessageDialog(String massage, String title) {
		this.message = massage;
		this.title = title;
		
		result = new Dialog(result, title, true);
		result.setBackground(Color.WHITE);
		result.setSize(300, 200);
		result.setResizable(false); // frame 크기 변경 불가능
		result.setLayout(null);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		result.setLocation(screenSize.width/2-150, screenSize.height/2-100);
		
		Label loginRsMsg = new Label(this.message);
		loginRsMsg.setAlignment(Label.CENTER);
		loginRsMsg.setBackground(Color.WHITE);
		loginRsMsg.setBounds(25, 50, 250, 50);
		
		Button ok = new Button("Ok");
		ok.setBounds(115, 140, 70, 30);
		ok.addActionListener(this);
		
		result.add(loginRsMsg);
		result.add(ok);
		
		result.setVisible(true);
		
	}

	public void actionPerformed(ActionEvent e) {
		result.dispose();
	}
}
