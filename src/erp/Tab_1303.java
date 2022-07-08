package erp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;

public class Tab_1303 implements ActionListener {
//	폴더 그림:40,40
//	Label 사이즈:150,30
//	TextField 사이즈:200,30
//	Button 사이즈:50,30
//	가로 여백 30, 세로 여백 10
	JPanel p;
	
	public Tab_1303() {
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

		Label l1 = new Label("재고 현황");
		l1.setBounds(50, 10, 150, 30);
		p.add(l1);
		
		// Label 추가
		Label[] label = new Label[3];
		String[] labelName = {"기간", "품목코드", "품목명"};

		for (int i = 0; i < label.length; i++) {
			label[i] = new Label(labelName[i]);
			p.add(label[i]);
		}
		
		label[0].setBounds(10, 50, 150, 30);
		label[1].setBounds(380, 50, 150, 30);
		label[2].setBounds(380, 90, 150, 30);
		
		// Choice 추가
		Choice[] ch = new Choice[2];
		
		for(int i=0; i<ch.length; i++) {
			ch[i] = new Choice();
			p.add(ch[i]);
		}
		ch[0].setBounds(170, 50, 200, 30); // 기간
		ch[1].setBounds(170, 90, 200, 30); // 기간
		
		// TextField 추가
		TextField pctf = new TextField();
		pctf.setBounds(540, 50, 200, 30);
		p.add(pctf);

		TextField pntf = new TextField();
		pntf.setBounds(540, 90, 200, 30);
		p.add(pntf);
		
		// Button 추가
		Button pcb = new Button("…");
		pcb.setBounds(750, 50, 30, 30);
		p.add(pcb);
		/*
		버튼을 TextField 안으로 넣을수 있는지 체크
		*/
		
		Button b1 = new Button("조회");
		b1.setBounds(790, 50, 50, 30);
		p.add(b1);

		// Table 추가
		String[] header = {
				"순번", "offer번호", "거래처", "품목코드", "품목명",
				"단위", "기초 수량", "입고 수량", "출고 수량", "재고 수량"
		};
		String[][] contents = {
				{"", "", "", "", "", "", "", "", "", ""}
		};
		/*
		checkbox 추가되어야함
		*/
		JTable table = new JTable(contents, header);
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(10, 130, 1000, 500);
		p.add(sp);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
