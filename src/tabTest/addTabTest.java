package tabTest;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;

public class addTabTest {
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
		JTabbedPane tp = new JTabbedPane();
		
		Panel p1 = new Panel();
		Button b1 = new Button("b1");
		p1.add(b1);

		Panel p2 = new Panel();
		Button b2 = new Button("b2");
		p2.add(b2);
		
		Panel p3 = new Panel();
		Button b3 = new Button("b3");
		p3.add(b3);
		
		tp.addTab("tab1", p1);
		tp.addTab("tab1", p2);
		tp.addTab("tab1", p3);
		
		f.add(tp);
		
//		Panel mainP = new Panel();
//		mainP.add(tp);
//		f.add(mainP);
		
		f.setVisible(true);
	}
}
