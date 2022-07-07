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
	
	JTabbedPane tp = new JTabbedPane();
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
	
	// tab�� �ߺ� �߰��� �����ϱ� ���� �ߺ� üũ�ϴ� �޼���
	public boolean checkAddTab (String menuItem) {
		int i = tp.indexOfTab(menuItem);
		if(i == -1) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String menuItem = e.getActionCommand(); // Ŭ���� menu item
		boolean b = checkAddTab(menuItem);
		
		switch(menuItem) {
		case "ǰ���ڵ� ���" :
			if(String.valueOf(b).equals("true")) {
				Tab_1101 tab1101 = new Tab_1101();
				tp.addTab(menuItem, tab1101.p);
			}
			break;
		case "����offer ���" : 
			if(String.valueOf(b).equals("true")) {
				Tab_1201 tab1201 = new Tab_1201();
				tp.addTab(menuItem, tab1201.p);
			}
			break;
		case "���Կ��� ���" : 
			if(String.valueOf(b).equals("true")) {
				Tab_1202 tab1202 = new Tab_1202();
				tp.addTab(menuItem, tab1202.p);
			}
			break;
		case "�԰� ���" : 
			if(String.valueOf(b).equals("true")) {
				Tab_1301 tab1301 = new Tab_1301();
				tp.addTab(menuItem, tab1301.p);
			}
			break;
		case "��� ���" : 
			if(String.valueOf(b).equals("true")) {
				Tab_1302 tab1302 = new Tab_1302();
				tp.addTab(menuItem, tab1302.p);
			}
			break;
		case "��� ��Ȳ" : 
			if(String.valueOf(b).equals("true")) {
				Tab_1303 tab1303 = new Tab_1303();
				tp.addTab(menuItem, tab1303.p);
			}
			break;
		case "���� ���" : 
			if(String.valueOf(b).equals("true")) {
				Tab_1401 tab1401 = new Tab_1401();
				tp.addTab(menuItem, tab1401.p);
			}
			break;
		case "ǰ����Һ�" : 
			if(String.valueOf(b).equals("true")) {
				Tab_1402 tab1402 = new Tab_1402();
				tp.addTab(menuItem, tab1402.p);
			}
			break;
		case "�λ����" : 
			if(String.valueOf(b).equals("true")) {
				Tab_1501 tab1501 = new Tab_1501();
				tp.addTab(menuItem, tab1501.p);
			}
			break;
		}
		
//		switch(menuItem) {
//		case "ǰ���ڵ� ���" :
//			Panel p1 = new Panel();
//			tp.addTab("p1", p1);
//			break;
//		case "����offer ���" : 
//			Panel p2 = new Panel();
//			tp.addTab("p2", p2);
//			break;
//		case "���Կ��� ���" : 
//			Panel p3 = new Panel();
//			tp.addTab("p3", p3);
//			break;
//		case "�԰� ���" : 
//			Panel p4 = new Panel();
//			tp.addTab("p4", p4);
//			break;
//		case "��� ���" : 
//			Panel p5 = new Panel();
//			tp.addTab("p5", p5);
//			break;
//		case "��� ��Ȳ" : 
//			Panel p6 = new Panel();
//			tp.addTab("p6", p6);
//			break;
//		case "���� ���" : 
//			Panel p7 = new Panel();
//			tp.addTab("p7", p7);
//			break;
//		case "ǰ����Һ�" : 
//			Panel p8 = new Panel();
//			tp.addTab("p8", p8);
//			break;
//		case "�λ����" : 
//			Panel p9 = new Panel();
//			tp.addTab("p9", p9);
//			break;
//		}
		
		main.add(tp);
		main.setVisible(true);
	}
}




