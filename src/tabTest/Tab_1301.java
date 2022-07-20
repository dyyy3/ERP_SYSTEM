package tabTest;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;

import java.text.*;
import java.util.*;

import javax.swing.table.DefaultTableCellRenderer;
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
	UtilDateModel model3;
	DefaultTableModel dtm;
	JTable table;
	Dao dao = new Dao();
	Vo vo;
	
	DefaultTableCellRenderer dcr = new DefaultTableCellRenderer() {
		public Component getTableCellRendererComponent // 셀렌더러
		(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			JCheckBox box = new JCheckBox();
			box.setSelected(((Boolean) value).booleanValue());
//			box.setSelected(((Boolean) dtm.getValueAt(0, 0)).booleanValue());
			box.setHorizontalAlignment(JLabel.CENTER);
			return box;
		}
	};
	
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
		Label[] label = new Label[5];
		String[] labelName = {"기간", "offer번호", "거래처", "입고일자", "품목코드"};

		for (int i = 0; i < label.length; i++) {
			label[i] = new Label(labelName[i]);
			p.add(label[i]);
		}
		
		label[0].setBounds(10, 50, 150, 30);
		label[1].setBounds(380, 50, 150, 30);
		label[2].setBounds(380, 90, 150, 30);
		label[3].setBounds(10, 130, 150, 30);
		label[4].setBounds(380, 130, 150, 30);
		
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
		
		// JDatePicker 추가(3)
		model3 = new UtilDateModel();
		JDatePanelImpl datePanel3 = new JDatePanelImpl(model3);
		JDatePickerImpl datePicker3 = new JDatePickerImpl(datePanel3);
		datePicker3.setBounds(170, 130, 200, 30);

		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
		Date todayDate3 = new Date();
		String today3 = sdf3.format(todayDate3);

		String[] date3 = today3.split("-");
		int dateY3 = Integer.parseInt(date3[0]);
		int dateM3 = Integer.parseInt(date3[1]) - 1;
		int dateD3 = Integer.parseInt(date3[2]);
		model3.setDate(dateY3, dateM3, dateD3);
		model3.setSelected(true);

		p.add(datePicker3);
		
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
		b2.setBounds(10, 170, 50, 30);
		b2.addActionListener(this);
		p.add(b2);
		
		// Table 추가
		String[] header = {
				"", "offer번호", "순번", "거래처", "품목코드",
				"품목명", "단위", "수량"
		};
		
		dtm = new DefaultTableModel(header, 0);		
		table = new JTable(dtm);
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(10, 210, 1000, 500);
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
			}
		}
		
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
		// 기간으로 조회 (offer번호, 거래처, 품목코드 빈칸인 경우)
		
		// 0 체크박스
		// 1 offer번호 : offer와 offer_list Join 테이블의 1
		// 2 순번 : 7
		// 3 거래처 : 2
		// 4 품목코드 : 8
		// 5 품목명 : 9
		// 6 단위 : 10
		// 7 수량 : 11
		
		String[][] result = dateAndOfferNum(date1, date2); // 0열 : 날짜, 1열 : offer번호
		
		for(int i=0; i<result.length; i++) {
			vo = new Vo("o.OFFER_NUM", result[i][1], "o.offer_num");
			String[][] result1 = dao.selectAllOfferAndOfferListJoinWhereTwoFields(vo);
			
			for(int j=0; j<result1.length; j++) {
				Object[] addRow = new Object[9];
				addRow[0] = false;
				addRow[1] = result1[j][0];
				addRow[2] = result1[j][6];
				addRow[3] = result1[j][1];
				addRow[4] = result1[j][7];
				addRow[5] = result1[j][8];
				addRow[6] = result1[j][9];
				addRow[7] = result1[j][10];
				dtm.addRow(addRow);
			}
		}
		table.getColumn("").setCellRenderer(dcr);
		
		JCheckBox box = new JCheckBox();
		box.setHorizontalAlignment(JLabel.CENTER);
		table.getColumn("").setCellEditor(new DefaultCellEditor(box));
	}
	
	public void setTable(String date1, String date2, String ocp) {
		// 기간 + offer번호로 조회 (거래처, 품목코드 빈칸인 경우)
		// 기간 + 거래처로 조회 (offer번호, 품목코드 빈칸인 경우)
		// 기간 + 품목코드로 조회 (offer번호, 거래처 빈칸인 경우)
		
		// 0 체크박스
		// 1 offer번호 : offer와 offer_list Join 테이블의 1
		// 2 순번 : 7
		// 3 거래처 : 2
		// 4 품목코드 : 8
		// 5 품목명 : 9
		// 6 단위 : 10
		// 7 수량 : 11

		String[][] result = dateAndOfferNum(date1, date2); // 0열 : 날짜, 1열 : offer번호
		
		// 세번째 매개변수가 어떤 값인지 확인
		String offer_num;
		String client_name;
		String product_code;
		
		String[] check = ocp.split("-");
		
		switch(check.length) {
		case 3 : 
			offer_num = ocp;
			
			for(int i=0; i<result.length; i++) {
				vo = new Vo("o.OFFER_NUM", result[i][1], "o.OFFER_NUM", offer_num, "o.offer_num");
				String[][] result1 = dao.selectAllOfferAndOfferListJoinWhereThreeFields(vo);
				
				for(int j=0; j<result1.length; j++) {
					Object[] addRow = new Object[9];
					addRow[0] = false;
					addRow[1] = result1[j][0];
					addRow[2] = result1[j][6];
					addRow[3] = result1[j][1];
					addRow[4] = result1[j][7];
					addRow[5] = result1[j][8];
					addRow[6] = result1[j][9];
					addRow[7] = result1[j][10];
					dtm.addRow(addRow);
				}
			}
			break;
			
		case 1 : 
			client_name = ocp;
			
			for(int i=0; i<result.length; i++) {
				vo = new Vo("o.OFFER_NUM", result[i][1], "o.CLIENT_NAME", client_name, "o.offer_num");
				String[][] result1 = dao.selectAllOfferAndOfferListJoinWhereThreeFields(vo);
				
				for(int j=0; j<result1.length; j++) {
					Object[] addRow = new Object[9];
					addRow[0] = false;
					addRow[1] = result1[j][0];
					addRow[2] = result1[j][6];
					addRow[3] = result1[j][1];
					addRow[4] = result1[j][7];
					addRow[5] = result1[j][8];
					addRow[6] = result1[j][9];
					addRow[7] = result1[j][10];
					dtm.addRow(addRow);
				}
			}
			break;
			
		case 4 : 
			product_code = ocp;

			for(int i=0; i<result.length; i++) {
				vo = new Vo("o.OFFER_NUM", result[i][1], "ol.PRODUCT_CODE", product_code, "o.offer_num");
				String[][] result1 = dao.selectAllOfferAndOfferListJoinWhereThreeFields(vo);
				
				for(int j=0; j<result1.length; j++) {
					Object[] addRow = new Object[9];
					addRow[0] = false;
					addRow[1] = result1[j][0];
					addRow[2] = result1[j][6];
					addRow[3] = result1[j][1];
					addRow[4] = result1[j][7];
					addRow[5] = result1[j][8];
					addRow[6] = result1[j][9];
					addRow[7] = result1[j][10];
					dtm.addRow(addRow);
				}
			}
			break;
		}
		table.getColumn("").setCellRenderer(dcr);
		
		JCheckBox box = new JCheckBox();
		box.setHorizontalAlignment(JLabel.CENTER);
		table.getColumn("").setCellEditor(new DefaultCellEditor(box));
	}
	
	public void setTable(String date1, String date2, String ocp1, String ocp2) {
		// 기간 + offer번호 + 거래처로 조회 (품목코드 빈칸인 경우) // 3, 1
		// 기간 + offer번호 + 품목코드로 조회 (거래처 빈칸인 경우) // 3, 4
		// 기간 + 거래처 + 품목코드로 조회 (offer번호 빈칸인 경우) // 1, 4
		
		// 0 체크박스
		// 1 offer번호 : offer와 offer_list Join 테이블의 1
		// 2 순번 : 7
		// 3 거래처 : 2
		// 4 품목코드 : 8
		// 5 품목명 : 9
		// 6 단위 : 10
		// 7 수량 : 11
		
		String[][] result = dateAndOfferNum(date1, date2); // 0열 : 날짜, 1열 : offer번호
		
		// 세번째, 네번째 매개변수가 어떤 값인지 확인
		String offer_num;
		String client_name;
		String product_code;
		
		String[] check1 = ocp1.split("-");
		String[] check2 = ocp2.split("-");
		
		if(check1.length == 3 && check2.length == 1) {
			offer_num = ocp1;
			client_name = ocp2;
			
			for(int i=0; i<result.length; i++) {
				vo = new Vo("o.OFFER_NUM", result[i][1], "o.OFFER_NUM", offer_num,
						"o.CLIENT_NAME", client_name, "o.OFFER_NUM");
				String[][] result1 = dao.selectAllOfferAndOfferListJoinWhereFourFields(vo);
				
				for(int j=0; j<result1.length; j++) {
					Object[] addRow = new Object[9];
					addRow[0] = false;
					addRow[1] = result1[j][0];
					addRow[2] = result1[j][6];
					addRow[3] = result1[j][1];
					addRow[4] = result1[j][7];
					addRow[5] = result1[j][8];
					addRow[6] = result1[j][9];
					addRow[7] = result1[j][10];
					dtm.addRow(addRow);
				}
			}
		}else if(check1.length == 3 && check2.length == 4) {
			offer_num = ocp1;
			product_code = ocp2;
			
			for(int i=0; i<result.length; i++) {
				vo = new Vo("o.OFFER_NUM", result[i][1], "o.OFFER_NUM", offer_num,
						"ol.PRODUCT_CODE", product_code, "o.OFFER_NUM");
				String[][] result1 = dao.selectAllOfferAndOfferListJoinWhereFourFields(vo);
				
				for(int j=0; j<result1.length; j++) {
					Object[] addRow = new Object[9];
					addRow[0] = false;
					addRow[1] = result1[j][0];
					addRow[2] = result1[j][6];
					addRow[3] = result1[j][1];
					addRow[4] = result1[j][7];
					addRow[5] = result1[j][8];
					addRow[6] = result1[j][9];
					addRow[7] = result1[j][10];
					dtm.addRow(addRow);
				}
			}
		}else if(check1.length == 1 && check2.length == 4) {
			client_name = ocp1;
			product_code = ocp2;
			
			for(int i=0; i<result.length; i++) {
				vo = new Vo("o.OFFER_NUM", result[i][1], "o.CLIENT_NAME", client_name,
						"ol.PRODUCT_CODE", product_code, "o.OFFER_NUM");
				String[][] result1 = dao.selectAllOfferAndOfferListJoinWhereFourFields(vo);
				
				for(int j=0; j<result1.length; j++) {
					Object[] addRow = new Object[9];
					addRow[0] = false;
					addRow[1] = result1[j][0];
					addRow[2] = result1[j][6];
					addRow[3] = result1[j][1];
					addRow[4] = result1[j][7];
					addRow[5] = result1[j][8];
					addRow[6] = result1[j][9];
					addRow[7] = result1[j][10];
					dtm.addRow(addRow);
				}
			}
		}
		table.getColumn("").setCellRenderer(dcr);
		
		JCheckBox box = new JCheckBox();
		box.setHorizontalAlignment(JLabel.CENTER);
		table.getColumn("").setCellEditor(new DefaultCellEditor(box));
	}
	
	public void setTable(String date1, String date2, String offer_num, String client_name, String product_code) {
		// 기간 + offer번호 + 거래처 + 품목코드로 조회

		// 기간으로 조회 (offer번호, 거래처, 품목코드 빈칸인 경우)
		// 0 체크박스
		// 1 offer번호 : offer와 offer_list Join 테이블의 1
		// 2 순번 : 7
		// 3 거래처 : 2
		// 4 품목코드 : 8
		// 5 품목명 : 9
		// 6 단위 : 10
		// 7 수량 : 11

		String[][] result = dateAndOfferNum(date1, date2); // 0열 : 날짜, 1열 : offer번호

		for (int i = 0; i < result.length; i++) {
			vo = new Vo("o.OFFER_NUM", result[i][1], "o.OFFER_NUM", offer_num, "o.CLIENT_NAME", client_name,
					"ol.PRODUCT_CODE", product_code, "o.offer_num");
			String[][] result1 = dao.selectAllOfferAndOfferListJoinWhereFourFields(vo);

			for (int j = 0; j < result1.length; j++) {
				Object[] addRow = new Object[9];
				addRow[0] = false;
				addRow[1] = result1[j][0];
				addRow[2] = result1[j][6];
				addRow[3] = result1[j][1];
				addRow[4] = result1[j][7];
				addRow[5] = result1[j][8];
				addRow[6] = result1[j][9];
				addRow[7] = result1[j][10];
				dtm.addRow(addRow);
			}
		}
		table.getColumn("").setCellRenderer(dcr);
		
		JCheckBox box = new JCheckBox();
		box.setHorizontalAlignment(JLabel.CENTER);
		table.getColumn("").setCellEditor(new DefaultCellEditor(box));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int rowCount = dtm.getRowCount();
		
		switch(e.getActionCommand()) {
		case "조회" :
			if(rowCount != 0) {
				int deleteRowCount = rowCount;
				if(deleteRowCount == 0) {
					
				}else {
					while(deleteRowCount != 0){
						dtm.removeRow(deleteRowCount - 1);
						deleteRowCount--;
					}
				}
			}
			
			String date1 = model.getYear() + "-" + (model.getMonth() + 1) + "-" + model.getDay();
			String date2 = model2.getYear() + "-" + (model2.getMonth() + 1) + "-" + model2.getDay();
			String offer_num = tf[0].getText();
			String client_name = ch.getSelectedItem();
			String product_code = tf[1].getText();
			
			// 조회 조건 경우의 수 : 8가지
			if(offer_num.equals("") && client_name.equals("") && product_code.equals("")) { // 기간으로 조회 (offer번호, 거래처, 품목코드 빈칸인 경우)
				setTable(date1, date2);
				
			}else if(client_name.equals("") && product_code.equals("")) { // 기간 + offer번호로 조회 (거래처, 품목코드 빈칸인 경우)
				setTable(date1, date2, offer_num);
				
			}else if(offer_num.equals("") && product_code.equals("")) { // 기간 + 거래처로 조회 (offer번호, 품목코드 빈칸인 경우)
				setTable(date1, date2, client_name);
				
			}else if(offer_num.equals("") && client_name.equals("")) { // 기간 + 품목코드로 조회 (offer번호, 거래처 빈칸인 경우)
				setTable(date1, date2, product_code);
				
			}else if(product_code.equals("")) { // 기간 + offer번호 + 거래처로 조회 (품목코드 빈칸인 경우)
				setTable(date1, date2, offer_num, client_name);
				
			}else if(client_name.equals("")) { // 기간 + offer번호 + 품목코드로 조회 (거래처 빈칸인 경우)
				setTable(date1, date2, offer_num, product_code);
				
			}else if(offer_num.equals("")) { // 기간 + 거래처 + 품목코드로 조회 (offer번호 빈칸인 경우)
				setTable(date1, date2, client_name, product_code);
				
			}else { // 기간 + offer번호 + 거래처 + 품목코드로 조회
				setTable(date1, date2, offer_num, client_name, product_code);
			}
			break;
			
		case "입고처리" :
			boolean b = true;
			String[] offerNum = new String[dtm.getRowCount()];
			int max = 0;
			int min = 1000000;
			int storing_num = 0;
			
			// 입고번호 결정
			for(int i=0; i<dtm.getRowCount(); i++) {
				b = Boolean.valueOf(table.getValueAt(i, 0).toString());
				if(b == true) {
					String s = table.getValueAt(i, 1).toString();
					String[] toSplit = s.split("-");
					offerNum[i] = toSplit[0] + toSplit[1] + toSplit[2]; 
				}
			}
			
			for(int i=0; i<offerNum.length; i++) {
				if(offerNum[i] != null) {
					if(max < Integer.parseInt(offerNum[i])) {
						max = Integer.parseInt(offerNum[i]);
					}
					if(min > Integer.parseInt(offerNum[i])) {
						min = Integer.parseInt(offerNum[i]);
					}
				}
			}
			
			if(min == max) {
				vo = new Vo("storing", "offer_num", "offer_num", String.valueOf(max));
				String checkOfferNum = dao.selectOneFieldDistinctWhere(vo);
				if(checkOfferNum == null) {
					storing_num = Integer.parseInt(String.valueOf(max) + "0001");
				}else {
					vo = new Vo();
					int i = dao.selectMaxWhere(vo) + 1;
					
					if(i < 10) {
						storing_num = Integer.parseInt(String.valueOf(max) + "000" + String.valueOf(i));
					}else if(i < 100) {
						storing_num = Integer.parseInt(String.valueOf(max) + "00" + String.valueOf(i));
					}else if(i < 1000) {
						storing_num = Integer.parseInt(String.valueOf(max) + "0" + String.valueOf(i));
					}
				}
			}else { // min != max -> 서로 다른 offer번호가 함께 체크된 상태
				new ErrorMessageDialog("offer번호가 다른 품목이 있습니다. 같은 offeer번호로 선택해주세요", "입고 등록");
				break;
			}
			
			// DB테이블에 저장
			int num2;
			
			String storing_date = model3.getYear() + "-" + (model3.getMonth() + 1) + "-" + model3.getDay();
			String offer_num2 = "";
			String client_name2 = "";
			String product_code2 = "";
			String product_name = "";
			String unit = "";
			int quantity = 0;
			
			boolean tryInsertStoring = true;
			
			for(int i=0; i<dtm.getRowCount(); i++) {
				b = Boolean.valueOf(table.getValueAt(i, 0).toString());
				if(b == true) {
					offer_num2 = table.getValueAt(i, 1).toString();
					num2 = Integer.parseInt(table.getValueAt(i, 2).toString());
					
					vo = new Vo("ol.OFFER_NUM", offer_num2, "ol.NUM", num2, "ol.offer_num");
					String[][] result = dao.selectAllOfferAndOfferListJoinWhereTwoStringFieldsAndOneIntField(vo);
					
					for(int j=0; j<result.length; j++) {
						client_name2 = result[j][1];
						product_code2 = result[j][7];
						product_name = result[j][8];
						unit = result[j][9];
						quantity = Integer.parseInt(result[j][10]);
					}
					vo = new Vo(storing_num, storing_date, offer_num2, client_name2, 
							product_code2, product_name, unit, quantity);
					tryInsertStoring = dao.insertStoring(vo);
				}
			}
			
			if(tryInsertStoring == true) {
				new ErrorMessageDialog("입고 처리되었습니다.", "입고 등록");
			}else {
				new ErrorMessageDialog("입고 처리에 실패하였습니다.", "입고 등록");
			}
			
			break;
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		
	}
}
