package tabTest;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;

import java.text.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class Tab_1301 implements ActionListener, ItemListener {
//	폴더 그림:40,40
//	Label 사이즈:150,30
//	TextField 사이즈:200,30
//	Button 사이즈:50,30
//	가로 여백 30, 세로 여백 10
	JPanel p;
	Choice ch;
	TextField[] tf;
	Button plb, b1, b2;
	UtilDateModel model;
	UtilDateModel model2;
	DefaultTableModel dtm;
	Dao dao = new Dao();
	Vo vo;
	
	public Tab_1301() {
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

		Label l1 = new Label("입고 정보");
		l1.setBounds(50, 10, 150, 30);
		p.add(l1);
		
		// Label 추가
		Label[] label = new Label[4];
		String[] labelName = {"기간", "offer번호", "거래처", "품목코드"};

		for (int i = 0; i < label.length; i++) {
			label[i] = new Label(labelName[i]);
			p.add(label[i]);
		}
		
		label[0].setBounds(10, 50, 150, 30);
		label[1].setBounds(380, 50, 150, 30);
		label[2].setBounds(380, 90, 150, 30);
		label[3].setBounds(380, 130, 150, 30);
		
		// JDatePicker 추가
		model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		datePicker.setBounds(170, 50, 200, 30);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date todayDate = new Date();
		String today = sdf.format(todayDate);

		String[] date = today.split("-");
		int dateY = Integer.parseInt(date[0]);
		int dateM = Integer.parseInt(date[1]) - 1;
		int dateD = Integer.parseInt(date[2]);
		model.setDate(dateY, dateM, dateD);
		model.setSelected(true);

		p.add(datePicker);
		
		// JDatePicker 추가(2)
		model2 = new UtilDateModel();
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
		JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2);
		datePicker2.setBounds(170, 90, 200, 30);
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		Date todayDate2 = new Date();
		String today2 = sdf2.format(todayDate2);
		
		String[] date2 = today2.split("-");
		int dateY2 = Integer.parseInt(date2[0]);
		int dateM2 = Integer.parseInt(date2[1]) - 1;
		int dateD2 = Integer.parseInt(date2[2]);
		model2.setDate(dateY2, dateM2, dateD2);
		model2.setSelected(true);
		
		p.add(datePicker2);
		
		// Choice 추가
		ch = new Choice();
		ch.setBounds(540, 90, 200, 30); // 거래처
		ch.addItemListener((ItemListener) this);
		p.add(ch);
		
		vo = new Vo("client", "client_name");
		String[] clientList = dao.selectOneField(vo);
		for(int i=0; i<clientList.length + 1; i++ ) {
			if(i == 0) {
				ch.add("");
			}else {
				ch.add(clientList[i-1]);
			}
		}
		
		// TextField 추가
		tf = new TextField[2];
		
		for(int i=0; i<tf.length; i++) {
			tf[i] = new TextField();
			p.add(tf[i]);
			tf[i].addActionListener(this);
		}
		tf[0].setBounds(540, 50, 200, 30); // offer번호
		tf[1].setBounds(540, 130, 200, 30); // 품목코드
		
		// Button 추가
		plb = new Button("…");
		plb.setBounds(750, 130, 30, 30);
		p.add(plb);
		/*
		버튼을 TextField 안으로 넣을수 있는지 체크
		*/
		
		b1 = new Button("조회");
		b1.setBounds(750, 50, 50, 30);
		b1.addActionListener(this);
		p.add(b1);

		b2 = new Button("입고처리");
		b2.setBounds(10, 130, 50, 30);
		b2.addActionListener(this);
		p.add(b2);
		
		// Table 추가
		String[] header = {
				"", "순번", "offer번호", "거래처", "품목코드",
				"품목명", "단위", "수량"
		};
		
		/*
		checkbox 추가되어야함
		*/
		
		dtm = new DefaultTableModel(header, 0);		
		JTable table = new JTable(dtm);
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(10, 170, 1000, 500);
		p.add(sp);
	}
	
	public void setTable(String s1, String s2) {
		// 테이블에 출력 : 0 체크박스, 1 순번, 2 offer번호, 3 거래처, 4 품목코드, 5 품목명, 6 단위, 7 수량
		String date1 = s1; // 2022-7-1
		String date2 = s2; // 2022-7-31
		String[] date1Arr = s1.split("-"); // 2022, 7, 1
		String[] date2Arr = s2.split("-"); // 2022, 7, 31
		
		// offer_date를 DB에서 가져온 후 저장
		vo = new Vo("offer", "offer_date");
		String[] selectResult1 = dao.selectOneField(vo);
		
		String[][] dbDate = new String[selectResult1.length][3];
		
		for(int i=0; i<selectResult1.length; i++) { // DB의 날짜를 구분자로 나누어서 2차원 배열에 담는다
			String[] splitDate = selectResult1[i].split("-");
			for(int j=0; j<dbDate[i].length; j++) {
				dbDate[i][j] =  splitDate[j];
				System.out.print(dbDate[i][j] + " ");
			}
			System.out.println();
		}
		
		String[] finallyDate;
		// 기간 조건을 충족하는 날짜들을 String 배열에 저장
		for(int i=0; i<dbDate.length; i++) {
			boolean b = true;
			if(Integer.valueOf(dbDate[i][0]) >= Integer.valueOf(date1Arr[0])) { // 시작년도보다 빠를때
				
			}else {
				b = false;
			}
			if(Integer.valueOf(dbDate[i][1]) >= Integer.valueOf(date1Arr[1])) { // 시작월보다 빠를때

			}else {
				b = false;
			}
			if(Integer.valueOf(dbDate[i][2]) >= Integer.valueOf(date1Arr[2])) { // 시작일보다 빠를때

			}else {
				b = false;
			}
			if(Integer.valueOf(dbDate[i][0]) <= Integer.valueOf(date2Arr[0])) { // 종료년도보다 늦을때

			}else {
				b = false;
			}
			if(Integer.valueOf(dbDate[i][1]) <= Integer.valueOf(date2Arr[1])) { // 종료월보다 늦을때

			}else {
				b = false;
			}
			if(Integer.valueOf(dbDate[i][2]) <= Integer.valueOf(date2Arr[2])) { // 종료일보다 늦를때

			}else {
				b = false;
			}
			
			if(b == true) {
				finallyDate[i] = dbDate[i][0];
			}
		}
		
		
		
//		// 날짜 형식을 2022-1-1에서 2022-01-01로 변경
//		char[] c = date1.toCharArray();
//
//		// 1~9월을 01~09로 변경
//		if (c[5] == '1' && c[6] != '-') { // 10~12월
//
//		} else { // 1~9월
//			c[4] = '0';
//		}
//
//		// 1~9일을 01~09일로 변경
//		if (c[c.length - 2] == '-') {
//			c[c.length - 2] = '0';
//		}
//
//		String[] date3 = new String[2];
//
//		for (int i = 0; i < date3.length; i++) { // 빈 문자열로 초기화
//			date3[i] = "";
//		}
//
//		switch (c.length) {
//		case 8:
//			for (int i = 0; i < c.length; i++) {
//				date3[0] += Character.toString(c[i]);
//			}
//			break;
//		case 9:
//			if (c[6] == '-') {
//				for (int i = 0; i < c.length; i++) {
//					if (i == 6) {
//
//					} else {
//						date3[0] += Character.toString(c[i]);
//					}
//				}
//			} else if (c[4] == '-') {
//				for (int i = 0; i < c.length; i++) {
//					if (i == 4) {
//
//					} else {
//						date3[0] += Character.toString(c[i]);
//					}
//				}
//			}
//			break;
//		case 10:
//			for (int i = 0; i < c.length; i++) {
//				if (i == 4 || i == 7) { // 구분자 2개는 넣지않음
//
//				} else {
//					date3[0] += Character.toString(c[i]);
//				}
//			}
//			break;
//		}
//
//		System.out.println(date3[0]);

//		// 1~2 : offer_list 테이블의 1,2열, 4~7 : offer_list 테이블의 3~6열
//		vo = new Vo();
//		String[][] result1 = dao.selectAllOfferListWhere(vo);
//		
//		// 3 : offer 테이블의 2열
//		vo = new Vo();
//		String[] result2 = dao.selectAllOfferWhere(vo);
	}
	
	public void setTable(String s1, String s2, String s3, String s4, String s5) {
		// 테이블에 출력 : 0 체크박스, 1 순번, 2 offer번호, 3 거래처, 4 품목코드, 5 품목명, 6 단위, 7 수량 
		String date1;
		String date2;
		String offer_num;
		String client_name;
		String product_code;
		
		// 1~2 : offer_list 테이블의 1,2열, 4~7 : offer_list 테이블의 3~6열
		vo = new Vo();
		String[][] result1 = dao.selectAllOfferListWhere(vo);

		// 3 : offer 테이블의 2열
		vo = new Vo();
		String[] result2 = dao.selectAllOfferWhere(vo);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "조회" :
			String date1 = model.getYear() + "-" + (model.getMonth() + 1) + "-" + model.getDay();
			String date2 = model2.getYear() + "-" + (model2.getMonth() + 1) + "-" + model2.getDay();
			String offer_num = tf[0].getText();
			String client_name = ch.getSelectedItem();
			String product_code = tf[1].getText();
			
			
			// 조회 조건 경우의 수 : 8가지
			if(offer_num.equals("") && client_name.equals("") && product_code.equals("")) { // 기간으로 조회 (offer번호, 거래처, 품목코드 빈칸인 경우)
				setTable(date1, date2);
				
			}else if(client_name.equals("") && product_code.equals("")) { // 기간 + offer번호로 조회 (거래처, 품목코드 빈칸인 경우)
				
			}else if(offer_num.equals("") && product_code.equals("")) { // 기간 + 거래처로 조회 (offer번호, 품목코드 빈칸인 경우)
				
			}else if(offer_num.equals("") && client_name.equals("")) { // 기간 + 품목코드로 조회 (offer번호, 거래처빈칸인 경우)
				
			}else if(product_code.equals("")) { // 기간 + offer번호 + 거래처로 조회 (품목코드 빈칸인 경우)
				
			}else if(client_name.equals("")) { // 기간 + offer번호 + 품목코드로 조회 (거래처 빈칸인 경우)
				
			}else if(offer_num.equals("")) { // 기간 + 거래처 + 품목코드로 조회 (offer번호 빈칸인 경우)
				
			}else { // 기간 + offer번호 + 거래처 + 품목코드로 조회
				setTable(date1, date2, offer_num, client_name, product_code);
			}
			

			break;
			
		case "입고처리" :
			break;
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
}
