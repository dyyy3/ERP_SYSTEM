package mainScreen;

import java.awt.*;
import java.awt.event.*;

public class MainScreenMenuTest extends WindowAdapter implements ActionListener {
	
	public static void main(String[] args) {
		Frame main = new Frame();
		main.setSize(1200, 800);
		main.addWindowListener(new MainScreenMenuTest());
		main.setVisible(true);
		
		MenuBar mb = new MenuBar();
		main.setMenuBar(mb);
		
		// 메뉴 : 품목,무역,자재,원가계산,인사
		Menu[] menu = {
				new Menu("품목"),
				new Menu("무역"),
				new Menu("자재"),
				new Menu("원가계산"), 
				new Menu("인사")
		};
		for(int i=0; i<menu.length; i++) {
			mb.add(menu[i]);
			menu[i].addActionListener(new MainScreenMenuTest());
		}
		
		// 하위 메뉴
		MenuItem[] item = {
				new MenuItem("품목코드 등록")
		};
		for(int i=0; i<item.length; i++) {
			menu[0].add(item[i]);
			item[i].addActionListener(new MainScreenMenuTest());
		}

		MenuItem[] trade = {
				new MenuItem("수입원가 등록")
		};
		for(int i=0; i<trade.length; i++) {
			menu[1].add(trade[i]);
			trade[i].addActionListener(new MainScreenMenuTest());
		}
		
		MenuItem[] material = {
				new MenuItem("입고 등록"),
				new MenuItem("출고 등록"),
				new MenuItem("재고 현황")
		};
		for(int i=0; i<material.length; i++) {
			menu[2].add(material[i]);
			material[i].addActionListener(new MainScreenMenuTest());
		}
		
		MenuItem[] costAccounting = {
				new MenuItem("원가 계산"),
				new MenuItem("품목수불부")
		};
		for(int i=0; i<costAccounting.length; i++) {
			menu[3].add(costAccounting[i]);
			costAccounting[i].addActionListener(new MainScreenMenuTest());
		}
		MenuItem[] personnelManagement = {
				new MenuItem("인사관리")
		};
		for(int i=0; i<personnelManagement.length; i++) {
			menu[4].add(personnelManagement[i]);
			personnelManagement[i].addActionListener(new MainScreenMenuTest());
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
