package test;

import java.awt.*;
import java.awt.event.*;

import erp.Login;

public class MainScreen extends WindowAdapter implements ActionListener {
	
	public static void main(String[] args) {
		Login login = new Login();
		
		
		Frame main = new Frame();
		main.setSize(1200, 800);
		main.addWindowListener(new MainScreen());
		main.setVisible(true);
		
		MenuBar mb = new MenuBar();
		main.setMenuBar(mb);
		
		// �޴� : ǰ��,����,����,�������,�λ�
		Menu[] menu = {
				new Menu("ǰ��"),
				new Menu("����"),
				new Menu("����"),
				new Menu("�������"), 
				new Menu("�λ�")
		};
		for(int i=0; i<menu.length; i++) {
			mb.add(menu[i]);
			menu[i].addActionListener(new MainScreen());
		}
		
		// ���� �޴�
		MenuItem[] item = {
				new MenuItem("ǰ���ڵ� ���")
		};
		for(int i=0; i<item.length; i++) {
			menu[0].add(item[i]);
			item[i].addActionListener(new MainScreen());
		}

		MenuItem[] trade = {
				new MenuItem("����offer ���"),
				new MenuItem("���Կ��� ���")
		};
		for(int i=0; i<trade.length; i++) {
			menu[1].add(trade[i]);
			trade[i].addActionListener(new MainScreen());
		}
		
		MenuItem[] material = {
				new MenuItem("�԰� ���"),
				new MenuItem("��� ���"),
				new MenuItem("��� ��Ȳ")
		};
		for(int i=0; i<material.length; i++) {
			menu[2].add(material[i]);
			material[i].addActionListener(new MainScreen());
		}
		
		MenuItem[] costAccounting = {
				new MenuItem("���� ���"),
				new MenuItem("ǰ����Һ�")
		};
		for(int i=0; i<costAccounting.length; i++) {
			menu[3].add(costAccounting[i]);
			costAccounting[i].addActionListener(new MainScreen());
		}
		MenuItem[] personnelManagement = {
				new MenuItem("�λ����")
		};
		for(int i=0; i<personnelManagement.length; i++) {
			menu[4].add(personnelManagement[i]);
			personnelManagement[i].addActionListener(new MainScreen());
		}
	}
	
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
