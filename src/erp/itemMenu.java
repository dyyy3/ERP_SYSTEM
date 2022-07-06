package erp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;

public class itemMenu implements ActionListener {
	static Choice[] ch = null;
	
//	public itemMenu() {
	public static void main(String[] args) {
		//
		Frame test = new Frame();
		test.setSize(500,500);
		//
		
		
		JPanel p = new JPanel();
		
		// label 추가
		Label lpc = new Label("품목코드");
		Label lpn = new Label("품목명");
		
		p.add(lpc);
		p.add(lpn);
		
		Label[] label = new Label[7];
		String[] labelName = {
				"1.대분류(자산구분)", "2.중분류(석종1)", "3.소분류(석종2)", "4.세분류1",
				"5.생산국가(원산지)", "6.세분류2(가공형태)", "7.세분류3(두께)"};

		for(int i=0; i<label.length; i++) {
			label[i] = new Label(labelName[i]);
			p.add(label[i]);
		}
		
		// textfield 추가
		TextField tfpc = new TextField(); // 품목코드
		TextField tfpn = new TextField(); // 품목명
		
		tfpc.setEditable(false); // 변경 불가
		tfpc.setFocusable(false); // focusing 불가
		
		tfpn.setEditable(false); // 변경 불가

		p.add(tfpc);
		p.add(tfpn);
		
		// choice 추가
		
		ch = new Choice[7];
		
		for(int i=0; i<ch.length; i++) {
			ch[i] = new Choice();
			p.add(ch[i]);
		}
		
		for(int i=0; i<ch.length; i++) {
			ch[0].add(Integer.toString(i));
		}

		
		//
		test.add(p);
		test.setVisible(true);
		//
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
