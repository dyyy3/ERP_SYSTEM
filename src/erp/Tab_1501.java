package erp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;

public class Tab_1501 implements ActionListener {
//	폴더 그림 : 40, 40
//	Label 사이즈 : 150, 30
//	TextField 사이즈 : 200, 30
//	Button 사이즈 : 50, 30
//	가로 여백 30, 세로 여백 10
	
	JPanel p;
	
	public Tab_1501() {
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
		
		Label l1 = new Label("사원 등록");
		l1.setBounds(50, 10, 150, 30);
		p.add(l1);
		
		// Label
		Label[] label = new Label[4];
		String[] labelName = {
				"사원코드(ID)", "이름",
				"password", "부서"};

		for(int i=0; i<label.length; i++) {
			label[i] = new Label(labelName[i]);
			p.add(label[i]);
		}
		
		label[0].setBounds(10, 50, 150, 30);
		label[1].setBounds(10, 90, 150, 30);
		label[2].setBounds(380, 50, 150, 30);
		label[3].setBounds(380, 90, 150, 30);
		
		// TextField
		TextField[] tf = new TextField[4];
		
		for(int i=0; i<tf.length; i++) {
			tf[i] = new TextField();
			p.add(tf[i]);
		}
		
		tf[0].setBounds(170, 50, 200, 30);
		tf[1].setBounds(170, 90, 200, 30);
		tf[2].setBounds(540, 50, 200, 30);
		tf[3].setBounds(540, 90, 200, 30);
		
		// icon
		JLabel imageLabel2 = new JLabel(); // ImageIcon 을 담을 Label 생성

		ImageIcon folder2 = new ImageIcon("src/images/folder-icon_34416.png");
		Image img2 = folder2.getImage(); // image 크기가 512, 512이므로 40, 40으로 바꾸기 위해 ImageIcon -> Image로 변경
		Image changeimg2 = img2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon changefolder2 = new ImageIcon(changeimg2);

		imageLabel2.setIcon(changefolder2);
		p.add(imageLabel2);
		imageLabel2.setBounds(10, 130, 30, 30);
		
		Label l2 = new Label("권한 설정");
		l2.setBounds(50, 130, 150, 30);
		p.add(l2);
		
		// Button
		Button[] button = new Button[3];
		String[] buttonName = {
				"등록", "조회", "수정"
		};

		for(int i=0; i<button.length; i++) {
			button[i] = new Button(buttonName[i]);
			p.add(button[i]);
		}
		
		button[0].setBounds(750, 50, 50, 30);
		button[1].setBounds(10, 170, 50, 30);
		button[2].setBounds(70, 170, 50, 30);
		
		// 조회 table
		String[] header1 = {"사원코드(ID)", "부서코드", "부서명", "이름", "password"};
		String[][] contents1 = {
				{"","","","",""}
		};
		JTable table1 = new JTable(contents1, header1);
		
		JScrollPane sp1 = new JScrollPane(table1);
		sp1.setBounds(10, 210, 1000, 50);
		p.add(sp1);

		//권한 table
		
		// 대메뉴 열 : 200, 30
		// 중메뉴 열 : 400, 30
		// 읽기, 쓰기, 수정, 삭제 열 : 100, 30
		Label[] label2 = new Label[20];
		String[] labelName2 = {
			"대메뉴", "중메뉴", // 0~1
			"읽기", "쓰기", "수정", "삭제", // 2~5
			"품목", "무역", "자재", "결산", "인사", // 6~10
			"품목코드 등록", "수입offer 등록", "수입원가 등록", // 11~13
			"입고 등록", "출고 등록", "재고 현황", // 14~16
			"원가 계산", "품목수불부", "계정 및 권한 관리" // 17~19
		};
		
		for(int i=0; i<label2.length; i++){
			label2[i] = new Label(labelName2[i]);
			p.add(label2[i]);
		}
		
		label2[0].setBounds(10, 300, 200, 30);
		label2[1].setBounds(210, 300, 400, 30);
		
		label2[2].setBounds(610, 300, 100, 30);
		label2[3].setBounds(710, 300, 100, 30);
		label2[4].setBounds(810, 300, 100, 30);
		label2[5].setBounds(910, 300, 100, 30);
		
		label2[6].setBounds(10, 330, 200, 30);
		label2[7].setBounds(10, 360, 200, 60);
		label2[8].setBounds(10, 420, 200, 90);
		label2[9].setBounds(10, 510, 200, 30);
		label2[10].setBounds(10, 570, 200, 30);

		label2[11].setBounds(210, 330, 400, 30);
		label2[12].setBounds(210, 360, 400, 30);
		label2[13].setBounds(210, 390, 400, 30);
		
		label2[14].setBounds(210, 420, 400, 30);
		label2[15].setBounds(210, 450, 400, 30);
		label2[16].setBounds(210, 480, 400, 30);
		
		label2[17].setBounds(210, 510, 400, 30);
		label2[18].setBounds(210, 540, 400, 30);
		label2[19].setBounds(210, 570, 400, 30);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
