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
//		t = new JTabbedPane(); // MenuBar�� ���� ���
		
		// ���޹��� menuItem�� ���� ���� panel�� �޶�����
		switch(menuItem) {
		case "ǰ���ڵ� ���" :
			Item_0 p0 = new Item_0();
			p = p0;
//			t.add(p0.p);
			break;
		case "����offer ���" : 
			break;
		case "���Կ��� ���" : 
			break;
		case "�԰� ���" : 
			break;
		case "��� ���" : 
			break;
		case "��� ��Ȳ" : 
			break;
		case "���� ���" : 
			break;
		case "ǰ����Һ�" : 
			break;
		case "�λ����" : 
			break;
		}
		

		
		
		
		
		// test
//		Frame f = new Frame();
//		f.setSize(500, 500);
//		
//		JTabbedPane t = new JTabbedPane(); // MenuBar�� ���� ���
//		Panel p1 = new Panel();
//		Button b = new Button("test");
//		t.addTab("ǰ���ڵ� ���", p1); // �Ű����� : title, component �Ǵ� panel
//		p1.add(b);
//		
//		f.add(t);
//		f.setVisible(true);
		// test
	}

}
