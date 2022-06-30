package test;

import java.awt.Choice;
import java.awt.Frame;

public class ChoiceTest {

	public static void main(String[] args) {
		Frame f = new Frame();
		f.setSize(300, 300);
		f.setLayout(null);
		
		Choice c = new Choice();
		c.setSize(100, 50);
		c.setLocation(0, 0);
		c.add("a");
		c.add("b");
		c.add("c");
		f.add(c);
		
		f.setVisible(true);
	}

}
