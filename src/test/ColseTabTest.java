package test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ColseTabTest implements ActionListener {
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
		b1.addActionListener(new ColseTabTest());
		p1.add(b1);

		Panel p2 = new Panel();
		Button b2 = new Button("b2");
		b2.addActionListener(new ColseTabTest());
		p2.add(b2);
		
		Panel p3 = new Panel();
		Button b3 = new Button("b3");
		p3.add(b3);
		
		tp.addTab("tab1", p1);
		tp.addTab("tab2", p2);
		tp.addTab("tab3", p3);
		
		f.add(tp);

		// changeListener 는 인터페이스기 때문에 인스턴스 생성 불가
		ChangeListener cl = new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				System.out.println("현재 tap 위치 : " + tp.getSelectedIndex()); // 마우스로 클릭된 tab의 index를 반환
				f.setTitle(null);

				System.out.println("tap 갯수 : " + tp.getComponentCount()); // 컴포넌트 갯수 반환

				int i = tp.indexOfTab("tab1");
				System.out.println("tab1인 tab의 index : " + i);
			}
		};
		
		tp.addChangeListener(cl); // JTabbedPane에 ChangeListener 추가
		
		
		f.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		if(s.equals("b1")) {
			Panel p4 = new Panel();
			tp.addTab("tab1", p4);
		}else if(s.equals("b2")) {
			Panel p5 = new Panel();
			tp.addTab("tab5", p5);
		}
	}
	
	
}
