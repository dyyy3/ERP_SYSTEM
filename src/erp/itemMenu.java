package erp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;

public class itemMenu implements ActionListener {
	static Choice[] ch = null;
	
//	public itemMenu() {
	public static void main(String[] args) {
		//
		Frame test = new Frame();
		test.setSize(500,500);
		//
		
		
		JPanel p = new JPanel();
		
		// label �߰�
		Label lpc = new Label("ǰ���ڵ�");
		Label lpn = new Label("ǰ���");
		
		p.add(lpc);
		p.add(lpn);
		
		Label[] label = new Label[7];
		String[] labelName = {
				"1.��з�(�ڻ걸��)", "2.�ߺз�(����1)", "3.�Һз�(����2)", "4.���з�1",
				"5.���걹��(������)", "6.���з�2(��������)", "7.���з�3(�β�)"};

		for(int i=0; i<label.length; i++) {
			label[i] = new Label(labelName[i]);
			p.add(label[i]);
		}
		
		// textfield �߰�
		TextField tfpc = new TextField(); // ǰ���ڵ�
		TextField tfpn = new TextField(); // ǰ���
		
		tfpc.setEditable(false); // ���� �Ұ�
		tfpc.setFocusable(false); // focusing �Ұ�
		
		tfpn.setEditable(false); // ���� �Ұ�

		p.add(tfpc);
		p.add(tfpn);
		
		// choice �߰�
		
		ch = new Choice[7];
		
		for(int i=0; i<ch.length; i++) {
			ch[i] = new Choice();
			p.add(ch[i]);
		}
		
		for(int i=0; i<ch.length; i++) {
			ch[0].add(Integer.toString(i));
		}

		
		//
		test.add(p);
		test.setVisible(true);
		//
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
