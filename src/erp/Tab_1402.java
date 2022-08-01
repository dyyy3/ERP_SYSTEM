package erp;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
//import javax.swing.event.*;
import javax.swing.table.TableColumn;

public class Tab_1402 implements ActionListener, ItemListener {
//	폴더 그림:40,40
//	Label 사이즈:150,30
//	TextField 사이즈:200,30
//	Button 사이즈:50,30
//	가로 여백 30, 세로 여백 10
	JPanel p;
	Choice chYear;
	Choice chMonth;
	DefaultTableModel dtm;
	JTable table;
	
	public Tab_1402() {
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

		Label l1 = new Label("검색");
		l1.setBounds(50, 10, 150, 30);
		p.add(l1);
		
		// Label 추가
		Label[] label = new Label[3];
		String[] labelName = {"기준년월", "품목코드", "품목명"};
		
		for(int i=0; i<label.length; i++) {
			label[i] = new Label(labelName[i]);
			p.add(label[i]);
		}
		
		label[0].setBounds(10, 50, 150, 30);
		label[1].setBounds(380, 50, 150, 30);
		label[2].setBounds(380, 90, 150, 30);
		
		// Choice 추가
		chYear = new Choice();
		chYear.setBounds(170, 50, 130, 30);
		chYear.addItemListener((ItemListener)this);
		
		chMonth = new Choice();
		chMonth.setBounds(310, 50, 60, 30);
		chMonth.addItemListener((ItemListener)this);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date todayDate = new Date();
		String today = sdf.format(todayDate);
		String[] date = today.split("-"); // date[0] : year, date[1] : month, date[2] : day

		if (date[1].charAt(0) == '0') {
			char[] ch = date[1].toCharArray();
			date[1] = String.valueOf(ch[1]);
		}

		for (int i = 2000; i <= Integer.parseInt(date[0]); i++) {
			chYear.add(String.valueOf(i));
		}

		for (int i = 1; i <= 12; i++) {
			chMonth.add(String.valueOf(i));
		}

		chYear.select(date[0]);
		chMonth.select(date[1]);
		
		p.add(chYear);
		p.add(chMonth);

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
		
		Button b1 = new Button("조회");
		b1.setBounds(790, 50, 50, 30);
		p.add(b1);
		
		// icon
		JLabel imageLabel2 = new JLabel(); // ImageIcon 을 담을 Label 생성

		ImageIcon folder2 = new ImageIcon("src/images/folder-icon_34416.png");
		Image img2 = folder2.getImage(); // image 크기가 512, 512이므로 40, 40으로 바꾸기 위해 ImageIcon -> Image로 변경
		Image changeimg2 = img2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon changefolder2 = new ImageIcon(changeimg2);

		imageLabel2.setIcon(changefolder2);
		p.add(imageLabel2);
		imageLabel2.setBounds(10, 130, 30, 30);

		Label l2 = new Label("품목수불부");
		l2.setBounds(50, 130, 150, 30);
		p.add(l2);
		
		// Table 추가
		String[] header = {
				"순번", "품목코드", "품목명", "단위", "기초 수량",
				"기초 단가", "기초 금액", "입고 단가", "입고 수량", "입고 금액",
				"출고 수량", "출고 단가", "출고 금액", "재고 수량", "재고 단가",
				"재고 금액"
		};
		dtm = new DefaultTableModel(header, 0);
		table = new JTable(dtm);
		
		// 테이블 열 너비 자동 조정 false
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// 열 너비 조정
		TableColumn t0 = table.getColumnModel().getColumn(0);
		TableColumn t1 = table.getColumnModel().getColumn(1);
		TableColumn t2 = table.getColumnModel().getColumn(2);
		TableColumn t3 = table.getColumnModel().getColumn(3);
		TableColumn t4 = table.getColumnModel().getColumn(4);
		TableColumn t5 = table.getColumnModel().getColumn(5);
		TableColumn t6 = table.getColumnModel().getColumn(6);
		TableColumn t7 = table.getColumnModel().getColumn(7);
		TableColumn t8 = table.getColumnModel().getColumn(8);
		TableColumn t9 = table.getColumnModel().getColumn(9);
		TableColumn t10 = table.getColumnModel().getColumn(9);
		TableColumn t11 = table.getColumnModel().getColumn(9);
		TableColumn t12 = table.getColumnModel().getColumn(9);
		TableColumn t13 = table.getColumnModel().getColumn(9);
		TableColumn t14 = table.getColumnModel().getColumn(9);
		TableColumn t15 = table.getColumnModel().getColumn(9);

		t0.setPreferredWidth(50);
		t1.setPreferredWidth(150);
		t2.setPreferredWidth(300);
		t3.setPreferredWidth(50);
		t4.setPreferredWidth(80);
		t5.setPreferredWidth(80);
		t6.setPreferredWidth(100);
		t7.setPreferredWidth(80);
		t8.setPreferredWidth(80);
		t9.setPreferredWidth(100);
		t10.setPreferredWidth(80);
		t11.setPreferredWidth(80);
		t12.setPreferredWidth(100);
		t13.setPreferredWidth(80);
		t14.setPreferredWidth(80);
		t15.setPreferredWidth(100);

		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(10, 170, 1160, 500);
		p.add(sp);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "조회" :
			break;
			
		case "…" :
			break;
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
}
