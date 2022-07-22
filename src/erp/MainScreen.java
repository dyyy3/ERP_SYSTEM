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
				new JMenuItem("���� �� ���� ����")
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
	public int checkAddTab (String menuItem) {
		int i = tp.indexOfTab(menuItem);
		return i;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String menuItem = e.getActionCommand(); // Ŭ���� menu item
		int i = checkAddTab(menuItem);
		
		switch(menuItem) {
		case "ǰ���ڵ� ���" :
			if(i == -1) {
				Tab_1101 tab1101 = new Tab_1101();
				tp.addTab(menuItem, tab1101.p);
				tp.setSelectedIndex(checkAddTab(menuItem));
			}else {
				tp.setSelectedIndex(i);
			}
			break;
		case "����offer ���" : 
			if(i == -1) {
				Tab_1201 tab1201 = new Tab_1201();
				tp.addTab(menuItem, tab1201.p);
				tp.setSelectedIndex(checkAddTab(menuItem));
			}else {
				tp.setSelectedIndex(i);
			}
			break;
		case "���Կ��� ���" : 
			if(i == -1) {
				Tab_1202 tab1202 = new Tab_1202();
				tp.addTab(menuItem, tab1202.p);
				tp.setSelectedIndex(checkAddTab(menuItem));
			}else {
				tp.setSelectedIndex(i);
			}
			break;
		case "�԰� ���" : 
			if(i == -1) {
				Tab_1301 tab1301 = new Tab_1301();
				tp.addTab(menuItem, tab1301.p);
				tp.setSelectedIndex(checkAddTab(menuItem));
			}else {
				tp.setSelectedIndex(i);
			}
			break;
		case "��� ���" : 
			if(i == -1) {
				Tab_1302 tab1302 = new Tab_1302();
				tp.addTab(menuItem, tab1302.p);
				tp.setSelectedIndex(checkAddTab(menuItem));
			}else {
				tp.setSelectedIndex(i);
			}
			break;
		case "��� ��Ȳ" : 
			if(i == -1) {
				Tab_1303 tab1303 = new Tab_1303();
				tp.addTab(menuItem, tab1303.p);
				tp.setSelectedIndex(checkAddTab(menuItem));
			}else {
				tp.setSelectedIndex(i);
			}
			break;
		case "���� ���" : 
			if(i == -1) {
				Tab_1401 tab1401 = new Tab_1401();
				tp.addTab(menuItem, tab1401.p);
				tp.setSelectedIndex(checkAddTab(menuItem));
			}else {
				tp.setSelectedIndex(i);
			}
			break;
		case "ǰ����Һ�" : 
			if(i == -1) {
				Tab_1402 tab1402 = new Tab_1402();
				tp.addTab(menuItem, tab1402.p);
				tp.setSelectedIndex(checkAddTab(menuItem));
			}else {
				tp.setSelectedIndex(i);
			}
			break;
		case "���� �� ���� ����" : 
			if(i == -1) {
				Tab_1501 tab1501 = new Tab_1501();
				tp.addTab(menuItem, tab1501.p);
				tp.setSelectedIndex(checkAddTab(menuItem));
			}else {
				tp.setSelectedIndex(i);
			}
			break;
		}
		
		main.add(tp);
		main.setVisible(true);
	}
}




