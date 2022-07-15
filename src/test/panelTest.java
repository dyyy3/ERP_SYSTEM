// 한 개의 프레임에 두 개 이상 panel을 원하는 위치에 넣는건 불가능

package test;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import javax.swing.*;

public class panelTest {

	public static void main(String[] args) {
		Frame f1 = new Frame();
		f1.setSize(1200, 800);
		f1.setLayout(null);
		
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		
		p1.setSize(600, 800);
		p1.setLocation(0, 0);
		p1.setLayout(null);
		p1.setBackground(Color.pink);
		
		p2.setSize(600, 800);
		p2.setLocation(300, 0);
		p2.setLayout(null);
		p2.setBackground(Color.GREEN);
		
		f1.add(p1);
		f1.add(p2);
		
		f1.setVisible(true);
		
		f1.getSize();
		p1.getSize();
		p2.getSize();
		
		
	}
}
