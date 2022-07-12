package erp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;
import net.sourceforge.jdatepicker.impl.*;
import java.text.*;
import java.util.*;

public class Tab_1201 implements ActionListener, ItemListener {
//	폴더 그림 : 40, 40
//	Label 사이즈 : 150, 30
//	TextField 사이즈 : 200, 30
//	가로 여백 30, 세로 여백 10
	
	JPanel p;
	TextField tf;
	UtilDateModel model;
	Choice[] ch;
	Button b;
	
	public Tab_1201() {
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

		Label l1 = new Label("수입offer 정보");
		l1.setBounds(50, 10, 150, 30);
		p.add(l1);
		
		// Label 추가
		Label[] label = new Label[5];
		String[] labelName = {"offer번호", "일자", "통화", "거래처", "가격조건"};
		
		for(int i=0; i<label.length; i++) {
			label[i] = new Label(labelName[i]);
			p.add(label[i]);
		}
		
		label[0].setBounds(10, 50, 150, 30);
		label[1].setBounds(10, 90, 150, 30);
		label[2].setBounds(10, 130, 150, 30);
		label[3].setBounds(380, 50, 150, 30);
		label[4].setBounds(380, 90, 150, 30);
		
		// TextField 추가
		tf = new TextField();
		tf.setBounds(170, 50, 200, 30);
		p.add(tf);
		
		// JDatePicker
		model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		datePicker.setBounds(170, 90, 200, 30);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		Date todayDate = new Date();
		String today = sdf.format(todayDate);
		
		String[] date = today.split("-");
		int dateY = Integer.parseInt(date[0]);
		int dateM = Integer.parseInt(date[1]) - 2;
		int dateD = Integer.parseInt(date[2]);
		model.setDate(dateY, dateM, dateD);
		model.setSelected(true);
		
		p.add(datePicker);
		
		// Choice 추가
		ch = new Choice[3];
		
		for(int i=0; i<ch.length; i++) {
			ch[i] = new Choice();
			p.add(ch[i]);
			ch[i].addItemListener((ItemListener)this);
		}
		
		// ch[0] : 거래처
		Dao dao = new Dao();
		
		Vo clientVo = new Vo("client", "client_name");
		String[] clientList = dao.selectOneField(clientVo);
		for(int i=0; i<clientList.length; i++ ) {
			ch[0].add(clientList[i]);
		}
		
		// ch[1] : 가격조건
		String[] incoterms = {"CFR", "CIF", "CIP", "CPT", "DAP", "DPU", "DDP", "EXW", "FAS", "FCA", "FOB"};
		
		for(int i=0; i<11; i++) {
			ch[1].add(incoterms[i]);
		}
		
		// ch[2] : 통화
		Vo currencyVo = new Vo("country", "ISO_4217");
		String[] currencyList = dao.selectOneFieldDistinct(currencyVo);
		for(int i=0; i<currencyList.length; i++) {
			ch[2].add(currencyList[i]);
		}
		
		ch[0].setBounds(540, 50, 200, 30);
		ch[1].setBounds(540, 90, 200, 30);
		ch[2].setBounds(170, 130, 200, 30);
		
		// Button
		b = new Button("저장");
		b.setBounds(750, 50, 50, 30);
		b.addActionListener(this);
		p.add(b);

		// Table 추가
		String[] header = {"", "순번", "품목코드", "품목명", "단위", "수량", "단가", "금액"};
		String[][] contents = {
				{"", "", "", "", "", "", "", ""}
		};
		/*
		'+'버튼이 클릭될떄마다 table의 행이 추가되어야하므로, String 2차원 배열 대신 객체 배열로 넣을수 있는지 테스트해보기
		*/
		JTable table = new JTable(contents, header);
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(10, 170, 1000, 50);
		p.add(sp);
		
		
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		for(int i=0; i<ch.length; i++) {
			System.out.println(ch[i].getSelectedItem());
		}
		System.out.println();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String offerNum = tf.getText();
		if(offerNum.equals("")) {
			System.out.println("offer번호를 입력하세요.");
			new ErrorMessageDialog("offer번호를 입력하세요.", "수입offer 등록");
		}else {
			System.out.println(offerNum);
		}
		
		String selectedDate = model.getYear() + "-" + (model.getMonth() + 1) + "-" + model.getDay();
//		System.out.println(selectedDate);
		
	}

}
