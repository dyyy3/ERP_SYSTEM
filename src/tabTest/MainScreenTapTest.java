package tabTest;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MainScreenTapTest extends WindowAdapter implements ActionListener {
	JPanel mainP; // tab을 붙일 panel
	int count = 0; // tabbedpane과 panel을 최초 한번만 생성하기 위한 변수

	static JFrame main;
	static JMenuBar mb;
	static JMenu[] menu = {
			new JMenu("품목"),
			new JMenu("무역"),
			new JMenu("자재"),
			new JMenu("원가계산"), 
			new JMenu("인사")
	};
	
	static JTabbedPane tp = new JTabbedPane();
//	static JTabbedPane tp = new JTabbedPane();
	
	public static void main(String[] args) {
		
		main = new JFrame();
		main.setBackground(new Color(214, 221, 228));
		main.setSize(1200, 800);
		main.addWindowListener(new MainScreenTapTest());
		
		// 윈도우 창 위쪽 아이콘 바꾸기
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("images/folder-icon_34416.png");
		main.setIconImage(img);
		
		mb = new JMenuBar();
		mb.setBackground(new Color(214, 221, 228));
		
		main.setJMenuBar(mb);
		// 메뉴 : 품목,무역,자재,결산,인사
		for(int i=0; i<menu.length; i++) {
			menu[i].setBackground(new Color(214, 221, 228));
			mb.add(menu[i]);
		}

		JMenuItem[] item = {
				new JMenuItem("품목코드 등록")
		};
		for(int i=0; i<item.length; i++) {
			menu[0].add(item[i]);
			item[i].addActionListener(new MainScreenTapTest());
		}
		
		JMenuItem[] trade = {
				new JMenuItem("수입offer 등록"),
				new JMenuItem("수입원가 등록")
		};
		for(int i=0; i<trade.length; i++) {
			menu[1].add(trade[i]);
			trade[i].addActionListener(new MainScreenTapTest());
		}
		JMenuItem[] material = {
				new JMenuItem("입고 등록"),
				new JMenuItem("출고 등록"),
				new JMenuItem("재고 현황")
		};
		for(int i=0; i<material.length; i++) {
			menu[2].add(material[i]);
			material[i].addActionListener(new MainScreenTapTest());
		}
		JMenuItem[] costAccounting = {
				new JMenuItem("원가 계산"),
				new JMenuItem("품목수불부")
		};
		for(int i=0; i<costAccounting.length; i++) {
			menu[3].add(costAccounting[i]);
			costAccounting[i].addActionListener(new MainScreenTapTest());
		}
		JMenuItem[] personnelManagement = {
				new JMenuItem("계정 및 권한 관리")
		};
		for(int i=0; i<personnelManagement.length; i++) {
			menu[4].add(personnelManagement[i]);
			personnelManagement[i].addActionListener(new MainScreenTapTest());
		}
		
		main.setVisible(true);
		
//		// changeListener 는 인터페이스기 때문에 인스턴스 생성 불가
//		ChangeListener cl = new ChangeListener() {
//			public void stateChanged(ChangeEvent e) {
//				System.out.println("현재 tap 위치 : " + tp.getSelectedIndex()); // 마우스로 클릭된 tab의 index를 반환
//				
//				System.out.println("tap 갯수 : " + tp.getComponentCount()); // 컴포넌트 갯수 반환
//				
//				int i = tp.indexOfTab("tab1"); // "tab1" 이름을 가진 tab의 index 반환
//				System.out.println("tab1인 tab의 index : " + i);
//			}
//		};
//		
//		tp.addChangeListener(cl); // JTabbedPane에 ChangeListener 추가
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
		String menuItem = e.getActionCommand(); // 클릭된 menu item
		
		switch(menuItem) {
		case "품목코드 등록" :
			int i = checkAddTab(menuItem);
			if(i == -1) {
				Tab_1101 tab1101 = new Tab_1101();
				tp.addTab(menuItem, tab1101.p);
			}else {
				tp.setSelectedIndex(i);
			}
			break;
		case "수입offer 등록" : 
			Tab_1201 tab1201 = new Tab_1201();
			tp.addTab(menuItem, tab1201.p);
			break;
		case "수입원가 등록" : 
			Tab_1202 tab1202 = new Tab_1202();
			tp.addTab(menuItem, tab1202.p);
			break;
		case "입고 등록" : 
			Tab_1301 tab1301 = new Tab_1301();
			tp.addTab(menuItem, tab1301.p);
			break;
		case "출고 등록" : 
			Tab_1302 tab1302 = new Tab_1302();
			tp.addTab(menuItem, tab1302.p);
			break;
		case "재고 현황" : 
			Tab_1303 tab1303 = new Tab_1303();
			tp.addTab(menuItem, tab1303.p);
			break;
		case "원가 계산" : 
			Tab_1401 tab1401 = new Tab_1401();
			tp.addTab(menuItem, tab1401.p);
			break;
		case "품목수불부" : 
			Tab_1402 tab1402 = new Tab_1402();
			tp.addTab(menuItem, tab1402.p);
			break;
		case "계정 및 권한 관리" : 
			Tab_1501 tab1501 = new Tab_1501();
			tp.addTab(menuItem, tab1501.p);
			break;
		}
		
		main.add(tp);
		main.setVisible(true);
	}
}