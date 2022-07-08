package tabTest;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;

public class Tab_1202 implements ActionListener {
//	폴더 그림 : 40, 40
//	Label 사이즈 : 150, 30
//	TextField 사이즈 : 200, 30
//	Button 사이즈 : 50, 30
//	가로 여백 30, 세로 여백 10
	JPanel p;
	
	public Tab_1202() {
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

		Label l1 = new Label("수입원가 정보");
		l1.setBounds(50, 10, 150, 30);
		p.add(l1);
		
		// Label 및 TextField - offer번호
		Label onl = new Label("Offer번호");
		onl.setBounds(10, 50, 150, 30);
		p.add(onl);
		
		TextField ontf = new TextField();
		ontf.setBounds(170, 50, 200, 30);
		p.add(ontf);
		
		// Label 추가
		Label[] label = new Label[6];
		String[] labelName = {
				"송금환율", "통관비(KRW)", "기타비용(KRW)",
				"송금수수료(KRW)", "운반비(KRW)", "총 금액(KRW)"
		};
		
		for(int i=0; i<label.length; i++) {
			label[i] = new Label(labelName[i]);
			p.add(label[i]);
		}
		
		label[0].setBounds(10, 110, 150, 30);
		label[1].setBounds(10, 150, 150, 30);
		label[2].setBounds(10, 190, 150, 30);
		label[3].setBounds(380, 110, 150, 30);
		label[4].setBounds(380, 150, 150, 30);
		label[5].setBounds(380, 190, 150, 30);
		
		// TextField 추가
		TextField[] tf = new TextField[6];
		
		for(int i=0; i<tf.length; i++) {
			tf[i] = new TextField();
			p.add(tf[i]);
		}
		
		tf[0].setBounds(170, 110, 200, 30);
		tf[1].setBounds(170, 150, 200, 30);
		tf[2].setBounds(170, 190, 200, 30);
		tf[3].setBounds(540, 110, 200, 30);
		tf[4].setBounds(540, 150, 200, 30);
		tf[5].setBounds(540, 190, 200, 30);
		
		// Button 추가
		Button b1 = new Button("조회");
		b1.setBounds(380, 50, 50, 30);
		p.add(b1);

		Button b2 = new Button("등록");
		b2.setBounds(750, 110, 50, 30);
		p.add(b2);
		
		// Table 추가
		String[] header = {
				"순번", "거래처", "품목코드", "품목명", "단위",
				"수량", "단가", "금액", "단가(KRW)", "금액(KRW)"
		};
		String[][] contents = {
				{"", "", "", "", "", "", "", "", "", ""}
		};
		/*
		'+'버튼이 클릭될떄마다 table의 행이 추가되어야하므로, String 2차원 배열 대신 객체 배열로 넣을수 있는지 테스트해보기
		*/
		JTable table = new JTable(contents, header);
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(10, 230, 1000, 50);
		p.add(sp);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
