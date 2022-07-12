package tabTest;

import java.awt.*;
import java.awt.event.*;

public class DeleteConfirmDialog implements ActionListener {
	private String message;
	private String title;
	private Dialog result;
	private boolean b = true;
	
	public DeleteConfirmDialog(String massage, String title) {
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
		ok.setBounds(75, 140, 70, 30);
		ok.addActionListener(this);
		
		Button cancel = new Button("Cancel");
		cancel.setBounds(155, 140, 70, 30);
		cancel.addActionListener(this);
		
		result.add(loginRsMsg);
		result.add(ok);
		result.add(cancel);
		
		result.setVisible(true);
	}
	
	public boolean response() {
		return b;
	}

	public void actionPerformed(ActionEvent e) {
		String r = e.getActionCommand();
		if(r.equals("Ok")) {
			b = true;
		}else if(r.equals("Cancel")) {
			b = false;
		}
		result.dispose();
	}
}
