package test;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Insets;

import javax.swing.JMenuBar;

public class MenuBarDesignTest {
	private static JMenuBar menu;
	public static void main(String[] args) {
		Frame f = new Frame();
		f.setSize(300, 300);
		menu = new JMenuBar();
		menu.setMargin(new Insets(10, 10, 10, 10));
		menu.setBackground(new Color(0, 0, 255));
		f.add(menu);
		
		f.setVisible(true);
	}

}
