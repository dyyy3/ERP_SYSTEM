package erp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;

public class Tab_1101 implements ActionListener {
//	폴더 그림 : 40, 40
//	Label 사이즈 : 150, 30
//	TextField 사이즈 : 200, 30
//	가로 여백 30, 세로 여백 10
	
	JPanel p;
	
	public Tab_1101() {
		p = new JPanel();
		p.setLayout(null);
		
		// icon
		JLabel imageLabel = new JLabel(); // ImageIcon 을 담을 Label 생성

		ImageIcon folder = new ImageIcon("src/images/folder-icon_34416.png");
		Image img = folder.getImage(); // image 크기가 512, 512이므로 40, 40으로 바꾸기 위해 ImageIcon -> Image로 변경
		Image changeimg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon changefolder = new ImageIcon(changeimg);

		imageLabel.setIcon(changefolder);
		p.add(imageLabel);
		imageLabel.setBounds(10, 10, 30, 30);
		
		Label l1 = new Label("품목정보");
		l1.setBounds(50, 10, 150, 30);
		p.add(l1);

		// label 추가
		Label lpc = new Label("품목코드");
		Label lpn = new Label("품목명");
		
		lpc.setBounds(10, 50, 150, 30);
		lpn.setBounds(10, 90, 150, 30);
		
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
		
		label[0].setBounds(10, 130, 150, 30);
		label[1].setBounds(10, 170, 150, 30);
		label[2].setBounds(10, 210, 150, 30);
		label[3].setBounds(10, 250, 150, 30);
		label[4].setBounds(380, 130, 150, 30);
		label[5].setBounds(380, 170, 150, 30);
		label[6].setBounds(380, 210, 150, 30);
		
		
		// textfield 추가
		TextField tfpc = new TextField(); // 품목코드
		TextField tfpn = new TextField(); // 품목명
		
		tfpc.setEditable(false); // 변경 불가
		tfpc.setFocusable(false); // focusing 불가
		
		tfpn.setEditable(false); // 변경 불가

		p.add(tfpc);
		p.add(tfpn);
		
		tfpc.setBounds(170, 50, 200, 30);
		tfpn.setBounds(170, 90, 560, 30);
		
		// choice 추가
		
		Choice[] ch = new Choice[7];
		
		for(int i=0; i<ch.length; i++) {
			ch[i] = new Choice();
			p.add(ch[i]);
		}
		
		/*
		각 choice마다 값을 불러오는 코드 작성 필요
		*/
		
		for(int i=0; i<ch.length; i++) {
			ch[0].add(Integer.toString(i));
		}
		
		ch[0].setBounds(170, 130, 200, 30);
		ch[1].setBounds(170, 170, 200, 30);
		ch[2].setBounds(170, 210, 200, 30);
		ch[3].setBounds(170, 250, 200, 30);
		ch[4].setBounds(540, 130, 200, 30);
		ch[5].setBounds(540, 170, 200, 30);
		ch[6].setBounds(540, 210, 200, 30);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
