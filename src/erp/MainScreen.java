package erp;

import java.awt.*;
import java.awt.event.*;

public class MainScreen extends WindowAdapter implements ActionListener {
	Frame main;
	MenuBar mb;
	Menu[] menu = {
			new Menu("ǰ��"),
			new Menu("����"),
			new Menu("����"),
			new Menu("�������"), 
			new Menu("�λ�")
	};
	String dept_id;
	
	public void setMenu(int startIndex, int endIndex) {
		// �޴� : ǰ��,����,����,���,�λ�
		for(int i=startIndex; i<=endIndex; i++) {
			mb.add(menu[i]);
			menu[i].addActionListener(this);
		}
	}
	
	public void setMenuItemItem(int startIndex, int endIndex) {
		MenuItem[] item = {
				new MenuItem("ǰ���ڵ� ���")
		};
		for(int i=startIndex; i<=endIndex; i++) {
			menu[0].add(item[i]);
			item[i].addActionListener(this);
		}
	}
	
	public void setMenuItemTrade(int startIndex, int endIndex) {
		MenuItem[] trade = {
				new MenuItem("����offer ���"),
				new MenuItem("���Կ��� ���")
		};
		for(int i=startIndex; i<=endIndex; i++) {
			menu[1].add(trade[i]);
			trade[i].addActionListener(this);
		}
	}
	
	public void setMenuItemMaterial(int startIndex, int endIndex) {
		MenuItem[] material = {
				new MenuItem("�԰� ���"),
				new MenuItem("��� ���"),
				new MenuItem("��� ��Ȳ")
		};
		for(int i=startIndex; i<=endIndex; i++) {
			menu[2].add(material[i]);
			material[i].addActionListener(this);
		}
	}
	
	public void setMenuItemCostAccounting(int startIndex, int endIndex) {
		MenuItem[] costAccounting = {
				new MenuItem("���� ���"),
				new MenuItem("ǰ����Һ�")
		};
		for(int i=startIndex; i<=endIndex; i++) {
			menu[3].add(costAccounting[i]);
			costAccounting[i].addActionListener(this);
		}
	}
	
	public void setMenuItemPersonnelManagement(int startIndex, int endIndex) {
		MenuItem[] personnelManagement = {
				new MenuItem("�λ����")
		};
		for(int i=0; i<personnelManagement.length; i++) {
			menu[4].add(personnelManagement[i]);
			personnelManagement[i].addActionListener(this);
		}
		
	}
	
	public void deptId_0_Menu() { // ������ : 0~4
		setMenu(0, 4);
		setMenuItemItem(0, 0);
		setMenuItemTrade(0, 1);
		setMenuItemMaterial(0, 2);
		setMenuItemCostAccounting(0, 1);
		setMenuItemPersonnelManagement(0, 0);
	}
	public void deptId_10_Menu() { // ���� : 0~3
		setMenu(0, 3);
		
		setMenuItemItem(0, 0);
		setMenuItemTrade(0, 1);
		setMenuItemMaterial(0, 2);
		setMenuItemCostAccounting(0, 1);
//		setMenuItemPersonnelManagement(); // ���� ����
	}
	public void deptId_20_Menu() { // ���� : 2
		setMenu(2, 2);
		
//		setMenuItemItem(); // ���� ����
//		setMenuItemTrade(); // ���� ����
		setMenuItemMaterial(0, 2);
//		setMenuItemCostAccounting(); // ���� ����
//		setMenuItemPersonnelManagement(); // ���� ����
	}
	public void deptId_30_Menu() { // ��� : 2, 3
		setMenu(2, 3);
		
//		setMenuItemItem(); // ���� ����
//		setMenuItemTrade(); // ���� ����
		setMenuItemMaterial(0, 2);
		setMenuItemCostAccounting(0, 1);
//		setMenuItemPersonnelManagement(); // ���� ����
	}
	public void deptId_40_Menu() { // �λ� : 2, 4
		// menu[3] �������̹Ƿ� �޼��� �� �� ȣ��
		setMenu(2, 2);
		setMenu(4, 4);
		
//		setMenuItemItem(); // ���� ����
//		setMenuItemTrade(); // ���� ����
		setMenuItemMaterial(2, 2); // �Ϻ� ����
//		setMenuItemCostAccounting(); // ���� ����
		setMenuItemPersonnelManagement(0, 0);
	}
	public void deptId_50_Menu() { // ���� : 2
		setMenu(2, 2);
		
//		setMenuItemItem(); // ���� ����
//		setMenuItemTrade(); // ���� ����
		setMenuItemMaterial(2, 2); // �Ϻ� ����
//		setMenuItemCostAccounting(); // ���� ����
//		setMenuItemPersonnelManagement(); // ���� ����
	}
	
	
	public MainScreen(String dept_id) {
		this.dept_id = dept_id;
		
		main = new Frame();
		main.setSize(1200, 800);
		main.addWindowListener(this);
		
		mb = new MenuBar();
		
		// dept_id�� ���� menu �� menuitem ����
		switch(dept_id) {
		case "0" :
			deptId_0_Menu();
			break;
		case "10" : 
			deptId_10_Menu();
			break;
		case "20" :
			deptId_20_Menu();
			break;
		case "30" :
			deptId_30_Menu();
			break;
		case "40" :
			deptId_40_Menu();
			break;
		case "50" :
			deptId_50_Menu();
			break;
		}

		main.setMenuBar(mb);
		main.setVisible(true);
	}
	
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
