package tabTest;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MainScreenTapTest extends WindowAdapter implements ActionListener {
	JPanel mainP; // tab�� ���� panel
	int count = 0; // tabbedpane�� panel�� ���� �ѹ��� �����ϱ� ���� ����

	static JFrame main;
	static JMenuBar mb;
	static JMenu[] menu = {
			new JMenu("ǰ��"),
			new JMenu("����"),
			new JMenu("����"),
			new JMenu("�������"), 
			new JMenu("�λ�")
	};
	
	static JTabbedPane tp = new JTabbedPane();
//	static JTabbedPane tp = new JTabbedPane();
	
	public static void main(String[] args) {
		
		main = new JFrame();
		main.setBackground(new Color(214, 221, 228));
		main.setSize(1200, 800);
		main.addWindowListener(new MainScreenTapTest());
		
		// ������ â ���� ������ �ٲٱ�
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("images/folder-icon_34416.png");
		main.setIconImage(img);
		
		mb = new JMenuBar();
		mb.setBackground(new Color(214, 221, 228));
		
		main.setJMenuBar(mb);
		// �޴� : ǰ��,����,����,���,�λ�
		for(int i=0; i<menu.length; i++) {
			menu[i].setBackground(new Color(214, 221, 228));
			mb.add(menu[i]);
		}

		JMenuItem[] item = {
				new JMenuItem("ǰ���ڵ� ���")
		};
		for(int i=0; i<item.length; i++) {
			menu[0].add(item[i]);
			item[i].addActionListener(new MainScreenTapTest());
		}
		
		JMenuItem[] trade = {
				new JMenuItem("����offer ���"),
				new JMenuItem("���Կ��� ���")
		};
		for(int i=0; i<trade.length; i++) {
			menu[1].add(trade[i]);
			trade[i].addActionListener(new MainScreenTapTest());
		}
		JMenuItem[] material = {
				new JMenuItem("�԰� ���"),
				new JMenuItem("��� ���"),
				new JMenuItem("��� ��Ȳ")
		};
		for(int i=0; i<material.length; i++) {
			menu[2].add(material[i]);
			material[i].addActionListener(new MainScreenTapTest());
		}
		JMenuItem[] costAccounting = {
				new JMenuItem("���� ���"),
				new JMenuItem("ǰ����Һ�")
		};
		for(int i=0; i<costAccounting.length; i++) {
			menu[3].add(costAccounting[i]);
			costAccounting[i].addActionListener(new MainScreenTapTest());
		}
		JMenuItem[] personnelManagement = {
				new JMenuItem("���� �� ���� ����")
		};
		for(int i=0; i<personnelManagement.length; i++) {
			menu[4].add(personnelManagement[i]);
			personnelManagement[i].addActionListener(new MainScreenTapTest());
		}
		
		main.setVisible(true);
		
//		// changeListener �� �������̽��� ������ �ν��Ͻ� ���� �Ұ�
//		ChangeListener cl = new ChangeListener() {
//			public void stateChanged(ChangeEvent e) {
//				System.out.println("���� tap ��ġ : " + tp.getSelectedIndex()); // ���콺�� Ŭ���� tab�� index�� ��ȯ
//				
//				System.out.println("tap ���� : " + tp.getComponentCount()); // ������Ʈ ���� ��ȯ
//				
//				int i = tp.indexOfTab("tab1"); // "tab1" �̸��� ���� tab�� index ��ȯ
//				System.out.println("tab1�� tab�� index : " + i);
//			}
//		};
//		
//		tp.addChangeListener(cl); // JTabbedPane�� ChangeListener �߰�
	}
	
	
	
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
	
	public int checkAddTab (String menuItem) {
		int i = tp.indexOfTab(menuItem);
		return i;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String menuItem = e.getActionCommand(); // Ŭ���� menu item
		
		switch(menuItem) {
		case "ǰ���ڵ� ���" :
			int i = checkAddTab(menuItem);
			if(i == -1) {
				Tab_1101 tab1101 = new Tab_1101();
				tp.addTab(menuItem, tab1101.p);
			}else {
				tp.setSelectedIndex(i);
			}
			break;
		case "����offer ���" : 
			Tab_1201 tab1201 = new Tab_1201();
			tp.addTab(menuItem, tab1201.p);
			break;
		case "���Կ��� ���" : 
			Tab_1202 tab1202 = new Tab_1202();
			tp.addTab(menuItem, tab1202.p);
			break;
		case "�԰� ���" : 
			Tab_1301 tab1301 = new Tab_1301();
			tp.addTab(menuItem, tab1301.p);
			break;
		case "��� ���" : 
			Tab_1302 tab1302 = new Tab_1302();
			tp.addTab(menuItem, tab1302.p);
			break;
		case "��� ��Ȳ" : 
			Tab_1303 tab1303 = new Tab_1303();
			tp.addTab(menuItem, tab1303.p);
			break;
		case "���� ���" : 
			Tab_1401 tab1401 = new Tab_1401();
			tp.addTab(menuItem, tab1401.p);
			break;
		case "ǰ����Һ�" : 
			Tab_1402 tab1402 = new Tab_1402();
			tp.addTab(menuItem, tab1402.p);
			break;
		case "���� �� ���� ����" : 
			Tab_1501 tab1501 = new Tab_1501();
			tp.addTab(menuItem, tab1501.p);
			break;
		}
		
		main.add(tp);
		main.setVisible(true);
	}
}