package tabTest;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;

public class tab1Test implements ActionListener {
//	폴더 그림부분 Label 및 TextField의 y값 : 40
//	Label 사이즈 : 150, 30
//	TextField 사이즈 : 200, 30
//	가로 여백 30, 세로 여백 10
	static JTabbedPane tp;
	
	public static void main(String[] args) {
		Frame f = new Frame();
		f.setSize(500, 500);
		
		// menu
		MenuBar mb = new MenuBar();
		
		Menu m1 = new Menu("m1");
		Menu m2 = new Menu("m2");
		
		mb.add(m1);
		mb.add(m2);
		
		f.setMenuBar(mb);
		
		// tab
		tp = new JTabbedPane();
		Panel p1 = new Panel();
		Button b1 = new Button("b1");
		b1.addActionListener(new tab1Test());
		p1.add(b1);

		Panel p2 = new Panel();
		Button b2 = new Button("b2");
		b2.addActionListener(new tab1Test());
		p2.add(b2);
		
		Panel p3 = new Panel();
		Button b3 = new Button("b3");
		p3.add(b3);
		
		tp.addTab("tab1", p1);
		tp.addTab("tab2", p2);
		tp.addTab("tab3", p3);
		
		f.add(tp);
		
//		Panel mainP = new Panel();
//		mainP.add(tp);
//		f.add(mainP);
		
		// button 추가는 가능하나 tab 이름이 가려짐
		Button eb1 = new Button("x");
		tp.setTabComponentAt(0, eb1);

		Button eb2 = new Button("x");
		tp.setTabComponentAt(1, eb2);
		
		Button eb3 = new Button("x");
		tp.setTabComponentAt(2, eb3);
		
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		if(s.equals("b1")) {
			Panel p4 = new Panel();
			tp.addTab("tab4", p4);
		}else if(s.equals("b2")) {
			Panel p5 = new Panel();
			tp.addTab("tab5", p5);
		}
	}
}
