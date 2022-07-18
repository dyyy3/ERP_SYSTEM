package erp;

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
				"", "offer번호", "순번", "거래처", "품목코드",
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
	
	// 기간은 어떤 경우에라도 주어져야하는 조건이므로 중복을 제거하기위해 기간 조건에 맞는 날짜와 offer_num을 구하는 코드는 별도의 메서드로 작성하여 호출
	public String[][] dateAndOfferNum(String date1, String date2) {
		String[] date1Arr = date1.split("-"); // 2022, 7, 1
		String[] date2Arr = date2.split("-"); // 2022, 7, 31
		
		String[][] dateAndOfferNum; // 리턴값
		
		// offer_date와 offer_num을 DB에서 가져온 후 저장
		vo = new Vo("offer", "offer_date", "offer_num");
		String[] selectResult1 = dao.selectOneFieldOrderBy(vo); // offer_date
		vo = new Vo("offer", "offer_num", "offer_num");
		String[] selectResult2 = dao.selectOneFieldOrderBy(vo); // offer_num
		
		
		String[][] dbDate = new String[selectResult1.length][4]; // 0 ~ 2 : 날짜(년,월,일) / 3 : offer번호 
		
		for(int i=0; i<selectResult1.length; i++) { // DB의 날짜를 구분자로 나누어서 2차원 배열에 담는다
			String[] splitDate = selectResult1[i].split("-");
			for(int j=0; j<dbDate[i].length; j++) {
				if(j < 3) { // 날짜 저장
					dbDate[i][j] =  splitDate[j];
				}else { // offer번호 저장
					dbDate[i][j] = selectResult2[i];
				}
//				System.out.print(dbDate[i][j] + " ");
			}
//			System.out.println();
		}
//		System.out.println();
		
		String[][] selectedDateAndOfferNum = new String[selectResult1.length][4]; // 기간 조건을 충족하는 날짜와 offer번호를 String타입 2차원 배열에 저장
		int index = 0; // String[] selectedDate의 index
		
		for(int i=0; i<dbDate.length; i++) {
			boolean checkStartDate = true;
			boolean checkEndDate = true;
			
			// 시작일과 비교
			if(Integer.valueOf(dbDate[i][0]) > Integer.valueOf(date1Arr[0])) { // db년도 > 시작년도
				// checkStartDate = true;
			}else if(Integer.valueOf(dbDate[i][0]).equals(Integer.valueOf(date1Arr[0]))) { // db년도 = 시작년도
				if(Integer.valueOf(dbDate[i][1]) > Integer.valueOf(date1Arr[1])) { // db월 > 시작월
					// checkStartDate = true;
				}else if(Integer.valueOf(dbDate[i][1]).equals(Integer.valueOf(date1Arr[1]))) { // db월 = 시작월
					if(Integer.valueOf(dbDate[i][2]) > Integer.valueOf(date1Arr[2])) { // db일 > 시작일
						// checkStartDate = true;
					}else if(Integer.valueOf(dbDate[i][2]).equals(Integer.valueOf(date1Arr[2]))) { // db일 = 시작일
						// checkStartDate = true;
					}else if(Integer.valueOf(dbDate[i][2]) < Integer.valueOf(date1Arr[2])) { // db일 < 시작일
						checkStartDate = false;
					}
				}else if(Integer.valueOf(dbDate[i][1]) < Integer.valueOf(date1Arr[1])) { // db월 < 시작월
					checkStartDate = false;
				}
			}else if(Integer.valueOf(dbDate[i][0]) < Integer.valueOf(date1Arr[0])) {  // db년도 < 시작년도
				checkStartDate = false;
			}
			
			// 종료일과 비교
			if(Integer.valueOf(dbDate[i][0]) < Integer.valueOf(date2Arr[0])) { // db년도 < 종료년도
				// checkEndDate = true;
			}else if(Integer.valueOf(dbDate[i][0]).equals(Integer.valueOf(date2Arr[0]))) { // db년도 = 종료년도
				if(Integer.valueOf(dbDate[i][1]) < Integer.valueOf(date2Arr[1])) { // db월 < 종료월
					// checkEndDate = true;
				}else if(Integer.valueOf(dbDate[i][1]).equals(Integer.valueOf(date2Arr[1]))) { // db월 = 종료월
					if(Integer.valueOf(dbDate[i][2]) < Integer.valueOf(date2Arr[2])) { // db일 < 종료일
						// checkEndDate = true;
					}else if(Integer.valueOf(dbDate[i][2]).equals(Integer.valueOf(date2Arr[2]))) { // db일 = 종료일
						// checkEndDate = true;
					}else if(Integer.valueOf(dbDate[i][2]) > Integer.valueOf(date2Arr[2])) { // db일 > 종료일
						checkEndDate = false;
					}
				}else if(Integer.valueOf(dbDate[i][1]) > Integer.valueOf(date2Arr[1])) { // db월 > 종료월
					checkEndDate = false;
				}
			}else if(Integer.valueOf(dbDate[i][0]) > Integer.valueOf(date2Arr[0])) {  // db년도 > 종료료년도
				checkEndDate = false;
			}

			// 시작일과 종료일 비교 결과값이 모두 true일때 selectedDate 배열에 저장
			if(checkStartDate == true && checkEndDate == true) {
				selectedDateAndOfferNum[index][0] = dbDate[i][0] + "-" + dbDate[i][1] + "-" + dbDate[i][2];
				selectedDateAndOfferNum[index][1] = selectResult2[i];
				index++;
			}
			
		} // for문의 끝
		
		// selectedDateAndOfferNum 배열에서 null값을 제외한 값을 selectedDateAndOfferNum 배열에 복사
		dateAndOfferNum = new String[index][2];
		
		for(int i=0; i<selectedDateAndOfferNum.length; i++) {
			if(selectedDateAndOfferNum[i][0] == null) {

			}else {
				dateAndOfferNum[i][0] = selectedDateAndOfferNum[i][0]; // 날짜
				dateAndOfferNum[i][1] = selectedDateAndOfferNum[i][1]; // offer번호
			}
		}
		
		return dateAndOfferNum;
	}
	
	public void setTable(String date1, String date2) {
		// 테이블에 출력 : 0 체크박스, 1 offer번호, 1 순번, 3 거래처, 4 품목코드, 5 품목명, 6 단위, 7 수량
		
		String[][] result = dateAndOfferNum(date1, date2); // 0열 : 날짜, 1열 : offer번호
		
		// 1~2 : offer_list 테이블의 1,2열, 3 : offer 테이블의 2열, 4~7 : offer_list 테이블의 3~6열
		for(int i=0; i<result.length; i++) {
			vo = new Vo("offer_list", "offer_num", result[i][1], "offer_num");
			
			String[][] result1 = dao.selectAllOfferListWhere(vo);
			
			vo = new Vo("offer", "client_name", "offer_num");
			String[] result2 = dao.selectOneFieldOrderBy(vo);
			
			for(int j=0; j<result1.length; j++) {
				String[] addRow = new String[9];
				addRow[0] = "";
				addRow[1] = result1[j][0]; // 2 offer번호
				addRow[2] = result1[j][1]; // 1 순번
				addRow[3] = result2[j]; // 3 거래처
				addRow[4] = result1[j][2]; // 4 품목코드
				addRow[5] = result1[j][3]; // 5 품목명
				addRow[6] = result1[j][4]; // 6 단위
				addRow[7] = result1[j][5]; // 7 수량
				dtm.addRow(addRow);
			}
		}
	}
	
	public void setTable(String date1, String date2, String offer_num, String client_name, String product_code) {
		// 테이블에 출력 : 0 체크박스, 1 offer번호, 1 순번, 3 거래처, 4 품목코드, 5 품목명, 6 단위, 7 수량
		
		
		
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
