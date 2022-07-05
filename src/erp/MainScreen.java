package erp;

import java.awt.*;
import java.awt.event.*;

public class MainScreen extends WindowAdapter implements ActionListener {
	Frame main;
	MenuBar mb;
	Menu[] menu = {
			new Menu("품목"),
			new Menu("무역"),
			new Menu("자재"),
			new Menu("원가계산"), 
			new Menu("인사")
	};
	String dept_id;
	
	public void setMenu(int startIndex, int endIndex) {
		// 메뉴 : 품목,무역,자재,결산,인사
		for(int i=startIndex; i<=endIndex; i++) {
			mb.add(menu[i]);
			menu[i].addActionListener(this);
		}
	}
	
	public void setMenuItemItem(int startIndex, int endIndex) {
		MenuItem[] item = {
				new MenuItem("품목코드 등록")
		};
		for(int i=startIndex; i<=endIndex; i++) {
			menu[0].add(item[i]);
			item[i].addActionListener(this);
		}
	}
	
	public void setMenuItemTrade(int startIndex, int endIndex) {
		MenuItem[] trade = {
				new MenuItem("수입offer 등록"),
				new MenuItem("수입원가 등록")
		};
		for(int i=startIndex; i<=endIndex; i++) {
			menu[1].add(trade[i]);
			trade[i].addActionListener(this);
		}
	}
	
	public void setMenuItemMaterial(int startIndex, int endIndex) {
		MenuItem[] material = {
				new MenuItem("입고 등록"),
				new MenuItem("출고 등록"),
				new MenuItem("재고 현황")
		};
		for(int i=startIndex; i<=endIndex; i++) {
			menu[2].add(material[i]);
			material[i].addActionListener(this);
		}
	}
	
	public void setMenuItemCostAccounting(int startIndex, int endIndex) {
		MenuItem[] costAccounting = {
				new MenuItem("원가 계산"),
				new MenuItem("품목수불부")
		};
		for(int i=startIndex; i<=endIndex; i++) {
			menu[3].add(costAccounting[i]);
			costAccounting[i].addActionListener(this);
		}
	}
	
	public void setMenuItemPersonnelManagement(int startIndex, int endIndex) {
		MenuItem[] personnelManagement = {
				new MenuItem("인사관리")
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
		
		main = new Frame();
		main.setSize(1200, 800);
		main.addWindowListener(this);
		
		mb = new MenuBar();
		
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
