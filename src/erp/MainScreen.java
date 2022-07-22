package erp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;

public class MainScreen extends WindowAdapter implements ActionListener {
	JFrame main;
	JMenuBar mb;
	JMenu[] menu = {
			new JMenu("품목"),
			new JMenu("무역"),
			new JMenu("자재"),
			new JMenu("원가계산"), 
			new JMenu("인사")
	};
	
	JTabbedPane tp = new JTabbedPane();
	String dept_id;
	
	
	
	public void setMenu(int startIndex, int endIndex) {
		// 메뉴 : 품목,무역,자재,결산,인사
		for(int i=startIndex; i<=endIndex; i++) {
			menu[i].setBackground(new Color(214, 221, 228));
			mb.add(menu[i]);
//			menu[i].addActionListener(this); // 메뉴에는 굳이 필요하지않다
		}
		
	}
	
	public void setMenuItemItem(int startIndex, int endIndex) {
		JMenuItem[] item = {
				new JMenuItem("품목코드 등록")
		};
		for(int i=startIndex; i<=endIndex; i++) {
			menu[0].add(item[i]);
			item[i].addActionListener(this);
		}
	}
	
	public void setMenuItemTrade(int startIndex, int endIndex) {
		JMenuItem[] trade = {
				new JMenuItem("수입offer 등록"),
				new JMenuItem("수입원가 등록")
		};
		for(int i=startIndex; i<=endIndex; i++) {
			menu[1].add(trade[i]);
			trade[i].addActionListener(this);
		}
	}
	
	public void setMenuItemMaterial(int startIndex, int endIndex) {
		JMenuItem[] material = {
				new JMenuItem("입고 등록"),
				new JMenuItem("출고 등록"),
				new JMenuItem("재고 현황")
		};
		for(int i=startIndex; i<=endIndex; i++) {
			menu[2].add(material[i]);
			material[i].addActionListener(this);
		}
	}
	
	public void setMenuItemCostAccounting(int startIndex, int endIndex) {
		JMenuItem[] costAccounting = {
				new JMenuItem("원가 계산"),
				new JMenuItem("품목수불부")
		};
		for(int i=startIndex; i<=endIndex; i++) {
			menu[3].add(costAccounting[i]);
			costAccounting[i].addActionListener(this);
		}
	}
	
	public void setMenuItemPersonnelManagement(int startIndex, int endIndex) {
		JMenuItem[] personnelManagement = {
				new JMenuItem("계정 및 권한 관리")
		};
		for(int i=0; i<personnelManagement.length; i++) {
			menu[4].add(personnelManagement[i]);
			personnelManagement[i].addActionListener(this);
		}
		
	}
	
	public void deptId_0_Menu() { // 마스터 : 0~4
		setMenu(0, 4);
		setMenuItemItem(0, 0);
		setMenuItemTrade(0, 1);
		setMenuItemMaterial(0, 2);
		setMenuItemCostAccounting(0, 1);
		setMenuItemPersonnelManagement(0, 0);
	}
	public void deptId_10_Menu() { // 구매 : 0~3
		setMenu(0, 3);
		
		setMenuItemItem(0, 0);
		setMenuItemTrade(0, 1);
		setMenuItemMaterial(0, 2);
		setMenuItemCostAccounting(0, 1);
//		setMenuItemPersonnelManagement(); // 권한 없음
	}
	public void deptId_20_Menu() { // 물류 : 2
		setMenu(2, 2);
		
//		setMenuItemItem(); // 권한 없음
//		setMenuItemTrade(); // 권한 없음
		setMenuItemMaterial(0, 2);
//		setMenuItemCostAccounting(); // 권한 없음
//		setMenuItemPersonnelManagement(); // 권한 없음
	}
	public void deptId_30_Menu() { // 결산 : 2, 3
		setMenu(2, 3);
		
//		setMenuItemItem(); // 권한 없음
//		setMenuItemTrade(); // 권한 없음
		setMenuItemMaterial(0, 2);
		setMenuItemCostAccounting(0, 1);
//		setMenuItemPersonnelManagement(); // 권한 없음
	}
	public void deptId_40_Menu() { // 인사 : 2, 4
		// menu[3] 미포함이므로 메서드 두 번 호출
		setMenu(2, 2);
		setMenu(4, 4);
		
//		setMenuItemItem(); // 권한 없음
//		setMenuItemTrade(); // 권한 없음
		setMenuItemMaterial(2, 2); // 일부 권한
//		setMenuItemCostAccounting(); // 권한 없음
		setMenuItemPersonnelManagement(0, 0);
	}
	public void deptId_50_Menu() { // 영업 : 2
		setMenu(2, 2);
		
//		setMenuItemItem(); // 권한 없음
//		setMenuItemTrade(); // 권한 없음
		setMenuItemMaterial(2, 2); // 일부 권한
//		setMenuItemCostAccounting(); // 권한 없음
//		setMenuItemPersonnelManagement(); // 권한 없음
	}
	
	
	public MainScreen(String dept_id) {
		this.dept_id = dept_id;
		
		main = new JFrame();
		main.setBackground(new Color(214, 221, 228));
		main.setSize(1200, 800);
		main.addWindowListener(this);
		
		mb = new JMenuBar();
		mb.setBackground(new Color(214, 221, 228));
		
		// dept_id에 따라 menu 및 menuitem 구성
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
	
	// tab의 중복 추가를 방지하기 위해 중복 체크하는 메서드
	public int checkAddTab (String menuItem) {
		int i = tp.indexOfTab(menuItem);
		return i;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String menuItem = e.getActionCommand(); // 클릭된 menu item
		int i = checkAddTab(menuItem);
		
		switch(menuItem) {
		case "품목코드 등록" :
			if(i == -1) {
				Tab_1101 tab1101 = new Tab_1101();
				tp.addTab(menuItem, tab1101.p);
				tp.setSelectedIndex(checkAddTab(menuItem));
			}else {
				tp.setSelectedIndex(i);
			}
			break;
		case "수입offer 등록" : 
			if(i == -1) {
				Tab_1201 tab1201 = new Tab_1201();
				tp.addTab(menuItem, tab1201.p);
				tp.setSelectedIndex(checkAddTab(menuItem));
			}else {
				tp.setSelectedIndex(i);
			}
			break;
		case "수입원가 등록" : 
			if(i == -1) {
				Tab_1202 tab1202 = new Tab_1202();
				tp.addTab(menuItem, tab1202.p);
				tp.setSelectedIndex(checkAddTab(menuItem));
			}else {
				tp.setSelectedIndex(i);
			}
			break;
		case "입고 등록" : 
			if(i == -1) {
				Tab_1301 tab1301 = new Tab_1301();
				tp.addTab(menuItem, tab1301.p);
				tp.setSelectedIndex(checkAddTab(menuItem));
			}else {
				tp.setSelectedIndex(i);
			}
			break;
		case "출고 등록" : 
			if(i == -1) {
				Tab_1302 tab1302 = new Tab_1302();
				tp.addTab(menuItem, tab1302.p);
				tp.setSelectedIndex(checkAddTab(menuItem));
			}else {
				tp.setSelectedIndex(i);
			}
			break;
		case "재고 현황" : 
			if(i == -1) {
				Tab_1303 tab1303 = new Tab_1303();
				tp.addTab(menuItem, tab1303.p);
				tp.setSelectedIndex(checkAddTab(menuItem));
			}else {
				tp.setSelectedIndex(i);
			}
			break;
		case "원가 계산" : 
			if(i == -1) {
				Tab_1401 tab1401 = new Tab_1401();
				tp.addTab(menuItem, tab1401.p);
				tp.setSelectedIndex(checkAddTab(menuItem));
			}else {
				tp.setSelectedIndex(i);
			}
			break;
		case "품목수불부" : 
			if(i == -1) {
				Tab_1402 tab1402 = new Tab_1402();
				tp.addTab(menuItem, tab1402.p);
				tp.setSelectedIndex(checkAddTab(menuItem));
			}else {
				tp.setSelectedIndex(i);
			}
			break;
		case "계정 및 권한 관리" : 
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




