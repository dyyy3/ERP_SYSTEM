package test;

import java.awt.*;
import java.awt.event.*;

public class Test implements ActionListener {
	TextField tf1;
	TextField tf2;
	Button b;
	
	public static void main(String[] args) {
		new Test();
	}
	
	public Test() {
		Frame f = new Frame();
		f.setSize(500, 500);
		
		Panel p = new Panel();
		p.setLayout(null);
		
		tf1 = new TextField();
		tf1.setBounds(10, 10, 200, 30);
		tf1.addActionListener(this);
		p.add(tf1);
		
		tf2 = new TextField();
		tf2.setBounds(10, 50, 200, 30);
		tf2.addActionListener(this);
		p.add(tf2);
		
		b = new Button("���");
		b.setBounds(220, 10, 50, 30);
		b.addActionListener(this);
		p.add(b);
		
		f.add(p);
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int currency_exchange; // �۱�ȯ��
		int remittance_charge; // �۱ݼ�����
		
		switch(e.getActionCommand()) {
		case "���" :
			String s = "0";
			try {
				s = tf1.getText();
			}catch(NullPointerException e1) {
				s = "0";
			}

			try {
				currency_exchange = Integer.parseInt(s); // �۱�ȯ��
			}catch(NumberFormatException e1) {
				s = "0";
				currency_exchange = Integer.parseInt(s); // �۱�ȯ��
			}
			
			try {
				s = tf2.getText();
			}catch(NullPointerException e1) {
				s = "0";
				remittance_charge = Integer.parseInt(s); // �۱ݼ�����
			}
			
//		int currency_exchange = Integer.parseInt(tf[0].getText()); // �۱�ȯ��
//		int remittance_charge = Integer.parseInt(tf[3].getText()); // �۱ݼ�����
		}
		
		
	}
}
