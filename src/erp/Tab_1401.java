package erp;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
//import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Tab_1401 implements ActionListener, ItemListener {
//	폴더 그림:40,40
//	Label 사이즈:150,30
//	TextField 사이즈:200,30
//	Button 사이즈:50,30
//	가로 여백 30, 세로 여백 10
	JPanel p;
	Choice chYear, chMonth;
	DefaultTableModel dtm;
	JTable table;
	Button b1, b2;
	Dao dao = new Dao();
	Vo vo;
	DecimalFormat df = new DecimalFormat("###,###,###");
	
	public Tab_1401() {
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
		Label label = new Label("기준년월");
		label.setBounds(10, 50, 150, 30);
		p.add(label);
		
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
		
		if(date[1].charAt(0) == '0') {
			char[] ch = date[1].toCharArray();
			date[1] = String.valueOf(ch[1]);
		}
		
		
		for(int i=2000; i<=Integer.parseInt(date[0]); i++) {
			chYear.add(String.valueOf(i));
		}
		
		for(int i=1; i<=12; i++) {
			chMonth.add(String.valueOf(i));
		}
		
		chYear.select(date[0]);
		chMonth.select(date[1]);
		
		p.add(chYear);
		p.add(chMonth);

		// Button 추가
		b1 = new Button("조회");
		b1.setBounds(380, 50, 50, 30);
		b1.addActionListener(this);
		p.add(b1);

		b2 = new Button("원가 계산");
		b2.setBounds(10, 90, 80, 30);
		b2.addActionListener(this);
		p.add(b2);
		
		// Table 추가
		String[] header = {
				"순번", "품목코드", "품목명", "단위", "기초 수량",
				"기초 단가", "기초 금액", "입고 수량", "입고 단가", "입고 금액",
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
		sp.setBounds(0, 130, 1160, 500);
		p.add(sp);
	}
	
	public String getProductLIst(){
		// storing, unstoring 테이블 중 품목코드 수가 더 많은쪽의 품목코드 리스트를 기준으로 정한다
		String s;
		
		vo = new Vo("storing", "product_code");
		String[] storingProductList = dao.selectOneFieldDistinct(vo);
		
		vo = new Vo("unstoring", "product_code");
		String[] unstoringProductList = dao.selectOneFieldDistinct(vo);
		
		if (storingProductList.length >= unstoringProductList.length) {
			s = "s"; // s.PRODUCT_CODE
		} else {
			s = "u"; // u.PRODUCT_CODE
		}
		return s;
	}
	
	
	public String[][] getStoringAndUnStoringQuantity() {
		// storing, unstoring 각 테이블의 품목코드별 수량 합계를 구한 결과의 full outer join 결과를 2차원 배열에 저장
		String year_month = chYear.getSelectedItem() + "-" + chMonth.getSelectedItem();
		
		vo = new Vo(year_month);
		String[][] result = dao.selectAllStroingAndUnStoringFullOuterJoin(vo);

		return result;
	}
	
	
	public String getLastMonth() {
		String lastMonth;

		// 현재 선택된 기준년월 - 1로 전월을 구한다
		int bqYear = Integer.parseInt(chYear.getSelectedItem());
		int bqMonth = Integer.parseInt(chMonth.getSelectedItem());

		if (bqMonth == 1) { // 현재 1월일때
			bqYear = bqYear - 1;
			bqMonth = 12;
		} else { // 현재 2~12월일때
			bqMonth = bqMonth - 1;
		}
		lastMonth = String.valueOf(bqYear) + "-" + String.valueOf(bqMonth);
		
		return lastMonth;
	}
	
	public int getBeginningQuantity(String lastMonth, String product_code) { // 기초수량 = 전월 재고수량, 만약 전월 재고수량이 없다면 0
		int returnValue = 0; // 반환할 값
					
		// product_code_cost 테이블에서 기초수량(전월 재고수량)을 구한다
		String getBeginningQuantity; // 기초수량
		vo = new Vo("product_code_cost", "inventory_quantity", "year_month", lastMonth, "product_code", product_code);
		getBeginningQuantity = dao.selectOneFieldWhereTwoFields(vo);
					
		if(getBeginningQuantity == null || getBeginningQuantity.equals("")) { // 전월 재고수량이 없을때
			returnValue = 0;
		}else { // 전월 재고수량이 있을때
			returnValue = Integer.parseInt(getBeginningQuantity);
		}
		
		return returnValue;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		int rowCount = dtm.getRowCount();
		String[][] result = getStoringAndUnStoringQuantity();
		String selectedDate = chYear.getSelectedItem() + "-" + chMonth.getSelectedItem(); // 2022-7
		
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
			String s = getProductLIst();
			int count = 1;

			// 테이블에 출력
			for(int i=0; i<result.length; i++) {
				Object[] addRow = new Object[16];
				addRow[0] = count++; // 순번
				addRow[1] = result[i][0]; // 품목코드
				String lastMonth = getLastMonth();
				addRow[4] = getBeginningQuantity(lastMonth, addRow[1].toString()); // 기초수량
				if(result[i][3] == null || result[i][3].equals("")) {
					addRow[7] = "0";
				}else {
					addRow[7] = result[i][3]; // 입고수량
				}
				if(result[i][6] == null || result[i][6].equals("")) {
					addRow[10] = "0";
				}else {
					addRow[10] = result[i][6]; // 출고수량
				}
				addRow[13] = (Integer.parseInt(addRow[4].toString()) + Integer.parseInt(addRow[7].toString()) - Integer.parseInt(addRow[10].toString())); // 재고수량
				if(s.equals("s")) {
					addRow[2] = result[i][1]; // 품목명
					addRow[3] = result[i][2]; // 단위
				}else if(s.equals("o")) {
					addRow[2] = result[i][4]; // 품목명
					addRow[3] = result[i][5]; // 단위
				}
				dtm.addRow(addRow);
			}
			count = 1;
			break;
			
		case "원가 계산" :
			String year_month = ""; // 기준년월
			String product_code = ""; // 품목코드
			String product_name = ""; // 품목명
			String unit = ""; // 단위
			int beginning_unit_price = 0; // 기초단가
			int beginning_quantity = 0; // 기초수량
			int beginning_amount = 0; // 기초금액
			int storing_unit_price = 0;
			int storing_quantity = 0;
			int storing_amount = 0;
			int unstoring_unit_price = 0; // 출고단가
			int unstoring_quantity = 0; // 출고수량
			int unstoring_amount = 0; // 출고금액
			int inventory_unit_price;
			int inventory_quantity = 0;
			int inventory_amount = 0;
			
			String dfs = df.format(0);
			
			// PRODUCT_CODE_COST : year_month, product_code, product_name, unit
//			String[][] pccString = new String[result.length][4]; -> result 배열의 값을 재활용
			
			int[][] pccInt = new int[result.length][12]; // PRODUCT_CODE_COST에 insert되는 12개 row
			
			if(rowCount == 0) {
				new ErrorMessageDialog("기준년월 입력 후 수량을 조회해주세요.", "원가 계산");
			}else {
				// addRow[4] = getBeginningQuantity(lastMonth, addRow[1].toString()); // 기초수량
				// addRow[7] = result[i][3]; // 입고수량
				// addRow[10] = result[i][6]; // 출고수량
				// addRow[13] = (Integer.parseInt(addRow[4].toString()) + Integer.parseInt(addRow[7].toString()) - Integer.parseInt(addRow[10].toString())); // 재고수량
				
				String s2 = getProductLIst();
				
				for(int i=0; i<result.length; i++) {
					// 기초금액 = 전월의 재고금액
					String ba;
					String lastMonth = getLastMonth();
					vo = new Vo("product_code_cost", "inventory_amount", "year_month", lastMonth, "product_code", result[i][0]);
					ba = dao.selectOneFieldWhereTwoFields(vo);
					
					// 기초수량, 단가, 금액
					if(ba == null || ba.equals("")) { // 전월 재고금액이 없을때
						beginning_unit_price = 0; // 기초단가
						beginning_quantity = 0; // 기초수량
						beginning_amount = 0; // 기초금액
					}else { // 전월 재고금액이 있을때
						beginning_amount = Integer.parseInt(ba);
						beginning_quantity = getBeginningQuantity(lastMonth, result[i][0]);
						beginning_unit_price = beginning_amount / beginning_quantity; // 기초단가 = 전월 재고금액 / 전월 재고수량
					}
					
					pccInt[i][0] = beginning_quantity;
					pccInt[i][1] = beginning_unit_price;
					pccInt[i][2] = beginning_amount;					
					
					dfs = df.format(beginning_quantity);
					dtm.setValueAt(dfs, i, 4);
					
					dfs = df.format(beginning_unit_price);
					dtm.setValueAt(dfs, i, 5);

					dfs = df.format(beginning_amount);
					dtm.setValueAt(dfs, i, 6);
					
					
					// 입고수량, 단가, 금액
					char[] chArr = selectedDate.toCharArray(); // 2,0,2,2,-,0,7
					String s3 = String.valueOf(chArr[2]) + String.valueOf(chArr[3]) + "-" + chMonth.getSelectedItem(); // 22-7
					
					vo = new Vo("offer_list", "quantity", "offer_num", s3, "product_code", result[i][0]);
					String sq = dao.sumWhereTwoFieldsLike(vo);
					
					if(sq == null || sq.equals("")) { // 입고수량 0일떄
						storing_quantity = 0;
						storing_unit_price = 0;
						storing_amount = 0;
					}else { // 입고수량 0이 아닐때
						storing_quantity = Integer.parseInt(sq);
						vo = new Vo("offer_list", "quantity", "unit_price_krw", "offer_num", s3, "product_code", result[i][0]);
						String[][] result2 = dao.selectTwoFieldsWhereTwoFieldsLike(vo);
						
						for(int j=0; j<result2.length; j++) {
							int m = Integer.parseInt(result2[j][0]) * Integer.parseInt(result2[j][1]);
							storing_amount += m;
						}
						storing_unit_price = storing_amount / storing_quantity;
					}
					pccInt[i][3] = storing_quantity;
					pccInt[i][4] = storing_unit_price;
					pccInt[i][5] = storing_amount;

					dfs = df.format(storing_quantity);
					dtm.setValueAt(dfs, i, 7);

					dfs = df.format(storing_unit_price);
					dtm.setValueAt(dfs, i, 8);

					dfs = df.format(storing_amount);
					dtm.setValueAt(dfs, i, 9);
					
					
					// 출고수량, 단가, 금액
					vo = new Vo("unstoring", "quantity", "unstoring_date", selectedDate, "product_code", result[i][0]);
					String usq = dao.sumWhereTwoFieldsLike(vo);
					
					if(usq == null || usq.equals("")) { // 출고수량 0일떄
						unstoring_quantity = 0;
						unstoring_unit_price = 0;
						unstoring_amount = 0;
					}else { // 출고수량 0이 아닐때
						unstoring_quantity = Integer.parseInt(usq);
						unstoring_unit_price = (beginning_amount + storing_amount) / (beginning_quantity + storing_quantity);
						unstoring_amount = unstoring_quantity * unstoring_unit_price;
					}
					pccInt[i][6] = unstoring_quantity;
					pccInt[i][7] = unstoring_unit_price;
					pccInt[i][8] = unstoring_amount;

					dfs = df.format(unstoring_quantity);
					dtm.setValueAt(dfs, i, 10);

					dfs = df.format(unstoring_unit_price);
					dtm.setValueAt(dfs, i, 11);
					
					dfs = df.format(unstoring_amount);
					dtm.setValueAt(dfs, i, 12);
					
					// 재고수량, 단가, 금액
					inventory_quantity = beginning_quantity + storing_quantity - unstoring_quantity;
					if(inventory_quantity == 0) {
						inventory_unit_price = 0;
						inventory_amount = 0;
					}else {
						inventory_amount = beginning_amount + storing_amount - unstoring_amount;
						inventory_unit_price = inventory_amount / inventory_quantity;
					}
					pccInt[i][9] = inventory_quantity;
					pccInt[i][10] = inventory_unit_price;
					pccInt[i][11] = inventory_amount;

					dfs = df.format(inventory_quantity);
					dtm.setValueAt(dfs, i, 13);

					dfs = df.format(inventory_unit_price);
					dtm.setValueAt(dfs, i, 14);

					dfs = df.format(inventory_amount);
					dtm.setValueAt(dfs, i, 15);
					
					storing_amount=0; // 이 변수에 값을 더해서 합계를 구하므로, 다음 품목코드의 합계를 위해 0으로 초기화
					
				} // for문의 끝

				// product_code_cost 테이블에 값 저장
				
			}
			break;
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
}
