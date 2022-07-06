package erp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class CreateTab {
	Item_0 p;
	
//	JTabbedPane t;
	
//	public static void main(String[] args) {
	public CreateTab(String menuItem) { 
//		t = new JTabbedPane(); // MenuBar와 같은 기능
		
		// 전달받은 menuItem에 따라 붙일 panel이 달라진다
		switch(menuItem) {
		case "품목코드 등록" :
			Item_0 p0 = new Item_0();
			p = p0;
//			t.add(p0.p);
			break;
		case "수입offer 등록" : 
			break;
		case "수입원가 등록" : 
			break;
		case "입고 등록" : 
			break;
		case "출고 등록" : 
			break;
		case "재고 현황" : 
			break;
		case "원가 계산" : 
			break;
		case "품목수불부" : 
			break;
		case "인사관리" : 
			break;
		}
		

		
		
		
		
		// test
//		Frame f = new Frame();
//		f.setSize(500, 500);
//		
//		JTabbedPane t = new JTabbedPane(); // MenuBar와 같은 기능
//		Panel p1 = new Panel();
//		Button b = new Button("test");
//		t.addTab("품목코드 등록", p1); // 매개변수 : title, component 또는 panel
//		p1.add(b);
//		
//		f.add(t);
//		f.setVisible(true);
		// test
	}

}
