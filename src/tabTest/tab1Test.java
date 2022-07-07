package tabTest;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;

public class tab1Test implements ActionListener {
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
