package erp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;

public class MainScreen extends WindowAdapter implements ActionListener {
	JFrame main;
	JMenuBar mb;
	JMenu[] menu = {
			new JMenu("ǰ��"),
			new JMenu("����"),
			new JMenu("����"),
			new JMenu("�������"), 
			new JMenu("�λ�")
	};
	
	JPanel mainP; // tab�� ���� panel
	int count = 0; // tabbedpane�� panel�� ���� �ѹ��� �����ϱ� ���� ����
	
	String dept_id;
	
	public void setMenu(int startIndex, int endIndex) {
		// �޴� : ǰ��,����,����,���,�λ�
		for(int i=startIndex; i<=endIndex; i++) {
			menu[i].setBackground(new Color(214, 221, 228));
			mb.add(menu[i]);
//			menu[i].addActionListener(this); // �޴����� ���� �ʿ������ʴ�
		}
	}
	
	public void setMenuItemItem(int startIndex, int endIndex) {
		JMenuItem[] item = {
				new JMenuItem("ǰ���ڵ� ���")
		};
		for(int i=startIndex; i<=endIndex; i++) {
			menu[0].add(item[i]);
			item[i].addActionListener(this);
		}
	}
	
	public void setMenuItemTrade(int startIndex, int endIndex) {
		JMenuItem[] trade = {
				new JMenuItem("����offer ���"),
				new JMenuItem("���Կ��� ���")
		};
		for(int i=startIndex; i<=endIndex; i++) {
			menu[1].add(trade[i]);
			trade[i].addActionListener(this);
		}
	}
	
	public void setMenuItemMaterial(int startIndex, int endIndex) {
		JMenuItem[] material = {
				new JMenuItem("�԰� ���"),
				new JMenuItem("��� ���"),
				new JMenuItem("��� ��Ȳ")
		};
		for(int i=startIndex; i<=endIndex; i++) {
			menu[2].add(material[i]);
			material[i].addActionListener(this);
		}
	}
	
	public void setMenuItemCostAccounting(int startIndex, int endIndex) {
		JMenuItem[] costAccounting = {
				new JMenuItem("���� ���"),
				new JMenuItem("ǰ����Һ�")
		};
		for(int i=startIndex; i<=endIndex; i++) {
			menu[3].add(costAccounting[i]);
			costAccounting[i].addActionListener(this);
		}
	}
	
	public void setMenuItemPersonnelManagement(int startIndex, int endIndex) {
		JMenuItem[] personnelManagement = {
				new JMenuItem("�λ����")
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
		
		main = new JFrame();
		main.setBackground(new Color(214, 221, 228));
		main.setSize(1200, 800);
		main.addWindowListener(this);
		
		mb = new JMenuBar();
		mb.setBackground(new Color(214, 221, 228));
		
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
		
		main.setJMenuBar(mb);
		main.setVisible(true);
	}
	
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String menuItem = e.getActionCommand(); // Ŭ���� menu item
		JTabbedPane t = null;
		
		if(count++ == 0) {
			mainP = new JPanel(); // tabbedpane�� ���� panel ����
			t = new JTabbedPane(); // MenuBar�� ���� ���
			
			mainP.add(t); // main �����ӿ� tabbedpane�� ���δ�
			main.add(mainP); // frame�� panel �߰�
//			main.setVisible(true);
		}
		
		CreateTab tab = new CreateTab(menuItem);
		t.addTab(menuItem, tab);
	}
}




