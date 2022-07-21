package tabTest;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
//import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

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
			
//			// storing, unstoring 테이블 중 품목코드 수가 더 많은쪽의 품목코드 리스트를 기준으로 정한다
//			String s;
//			
//			vo = new Vo("storing", "product_code");
//			String[] storingProductList = dao.selectOneFieldDistinct(vo);
//			
//			vo = new Vo("unstoring", "product_code");
//			String[] unstoringProductList = dao.selectOneFieldDistinct(vo);
//			
//			if(storingProductList.length >= unstoringProductList.length) {
//				s = "s"; // s.PRODUCT_CODE
//			}else {
//				s = "u"; // u.PRODUCT_CODE
//			}
//			
//			// storing, unstoring 각 테이블의 품목코드별 수량 합계를 구한 결과의 full outer join 결과를 2차원 배열에 저장
//			String year_month = chYear.getSelectedItem() + "-" + chMonth.getSelectedItem();
//			int count = 1;
//			
//			vo = new Vo(year_month);
//			String[][] result = dao.selectAllStroingAndUnStoringFullOuterJoin(vo);
			
			String s = getProductLIst();
			String[][] result = getStoringAndUnStoringQuantity();
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
			if(rowCount == 0) {
				new ErrorMessageDialog("기준년월 입력 후 수량을 조회해주세요.", "원가 계산");
			}else {
				// addRow[4] = getBeginningQuantity(lastMonth, addRow[1].toString()); // 기초수량
				// addRow[7] = result[i][3]; // 입고수량
				// addRow[10] = result[i][6]; // 출고수량
				// addRow[13] = (Integer.parseInt(addRow[4].toString()) + Integer.parseInt(addRow[7].toString()) - Integer.parseInt(addRow[10].toString())); // 재고수량
				
				String s2 = getProductLIst();
				String[][] result2 = getStoringAndUnStoringQuantity();
				
				for(int i=0; i<result2.length; i++) {
					int unitPrice = 0; // 단가
					int amount = 0; // 금액

					// 기초금액 = 전월의 재고금액
					String beginning_amount;
					String lastMonth = getLastMonth();
					vo = new Vo("product_code_cost", "inventory_amount", "year_month", lastMonth, "product_code", result2[i][0]);
					beginning_amount = dao.selectOneFieldWhereTwoFields(vo);
					
					if(beginning_amount == null || beginning_amount.equals("")) { // 전월 재고금액이 없을때
						amount = 0;
					}else { // 전월 재고금액이 있을때
						amount = Integer.parseInt(beginning_amount);
					}
					dtm.setValueAt(amount, i, 6);
					
					// 기초단가
					if(amount == 0) {
						unitPrice = 0; // 기초단가 = 0
					}else {
						unitPrice = amount / getBeginningQuantity(lastMonth, result2[i][0]); // 기초단가 = 전월 재고금액 / 전월 재고수량
					}
					dtm.setValueAt(unitPrice, i, 5);
					
					// 입고금액
					
					
				}
				
//				int returnValue = 0; // 반환할 값
//				
//				// product_code_cost 테이블에서 기초수량(전월 재고수량)을 구한다
//				String getBeginningQuantity; // 기초수량
//				vo = new Vo("product_code_cost", "inventory_quantity", "year_month", lastMonth, "product_code", product_code);
//				getBeginningQuantity = dao.selectOneFieldWhereTwoFields(vo);
//							
//				if(getBeginningQuantity == null || getBeginningQuantity.equals("")) { // 전월 재고수량이 없을때
//					returnValue = 0;
//				}else { // 전월 재고수량이 있을때
//					returnValue = Integer.parseInt(getBeginningQuantity);
//				}
				
				
				// 테이블에 setValueAt
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
