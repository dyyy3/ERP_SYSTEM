package tabTest;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;

public class Tab_1202 implements ActionListener {
//	폴더 그림 : 40, 40
//	Label 사이즈 : 150, 30
//	TextField 사이즈 : 200, 30
//	Button 사이즈 : 50, 30
//	가로 여백 30, 세로 여백 10
	JPanel p;
	TextField ontf;
	Label[] label;
	Button b1, b2;
	TextField[] tf;
	DefaultTableModel dtm;
	Dao dao = new Dao();
	Vo vo;
	
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
		
		ontf = new TextField();
		ontf.setBounds(170, 50, 200, 30);
		p.add(ontf);
		
		// Label 추가
		label = new Label[6];
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
		tf = new TextField[6];
		
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
		b1 = new Button("조회");
		b1.addActionListener(this);
		b1.setBounds(380, 50, 50, 30);
		p.add(b1);

		b2 = new Button("등록");
		b2.addActionListener(this);
		b2.setBounds(750, 110, 50, 30);
		p.add(b2);
		
		// Table 추가
		String[] header = {
				"순번", "거래처", "품목코드", "품목명", "단위",
				"수량", "단가", "금액", "단가(KRW)", "금액(KRW)"
		};
		dtm = new DefaultTableModel(header, 0);		
		JTable table = new JTable(dtm);
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(10, 230, 1000, 450);
		p.add(sp);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int rowCount = dtm.getRowCount(); // 현재 테이블의 행 갯수
		String tfText = ontf.getText();
		switch(e.getActionCommand()) {
		case "조회" :
			if(tfText == null) {
				new ErrorMessageDialog("offer번호를 입력하세요.", "수입원가 등록");
			}else {
				// 테이블에 출력 : 0 순번, 1 거래처, 2 품목코드, 3 품목명, 4 단위, 5 수량, 6 단가, 7 금액
				vo = new Vo("offer", "offer_num", tfText);
				String[] result = dao.selectAllOfferWhere(vo);
				if(result.length == 0) {
					new ErrorMessageDialog("등록되지않은 offer번호입니다.", "수입원가 등록");
				}else {
					// 0 순번 : offer_list테이블의 두번째 필드(num)
					vo = new Vo("offer_list", "num", "offer_num", tfText, "num");
					String[] selectResult1 = dao.selectOneFieldWhereOrderBy(vo);
					
					int deleteRowCount = rowCount;
					if(deleteRowCount == 0) {
						
					}else {
						while(deleteRowCount != 0){
							dtm.removeRow(deleteRowCount - 1);
							deleteRowCount--;
						}
					}
					
					for(int i=0; i<selectResult1.length; i++) { // 0~2
						String[] addRow = new String[1];
						for(int j=0; j<addRow.length; j++) {
							addRow[j] = selectResult1[i];
							dtm.addRow(addRow);
						}
					}
					
					// 1 거래처 : offer테이블의 두번째 필드(client_name)
					// SELECT client_name FROM OFFER WHERE OFFER_NUM = '22-7-2'
					vo = new Vo("offer", "client_name", "offer_num", tfText);
					String selectResult2 = dao.selectOneFieldWhere(vo);
					
					for(int i=0; i<selectResult1.length; i++) {
						dtm.setValueAt(selectResult2, i, 1);
					}
					
					// 2 품목코드 : offer_list테이블의 세번째 필드(product_code)
					// 3 품목명 : offer_list테이블의 네번째 필드(product_name)
					// 4 단위 : offer_list테이블의 다섯번째 필드(unit)
					// 5 수량 : offer_list테이블의 여섯번째 필드(quantity)
					// 6 단가 : offer_list테이블의 일곱번째 필드(unit_price)
					// 7 금액 : offer_list테이블의 여덟번째 필드(amount)
					
					vo = new Vo("offer_list", "offer_num", tfText, "num");
					String[][] selectResult3 = dao.selectAllOfferListWhere(vo);
					
					for(int i=0; i<selectResult3.length; i++) {
						String[] addRow = new String[6];
						for(int j=0; j<selectResult3[i].length - 3; j++) {
							addRow[j] = selectResult3[i][j+2];
							dtm.setValueAt(addRow[j], i, j+2);
						}
					}
				}
			} // else문의 끝
			
			// 이미 원가가 등록되어있다면 등록된 원가도 테이블에 출력
			vo = new Vo("offer_cost", "offer_num", "offer_num", tfText);
			String checkCost = dao.selectOneFieldWhere(vo);
			//SELECT OFFER_NUM FROM offer_cost WHERE OFFER_NUM = '22-7-3'
			if(checkCost.equals("")) {
				
			}else {
				// tf[0] ~ tf[5]에 offer_cost 테이블에 저장된 값으로 set
				vo = new Vo("offer_cost", "offer_num", tfText);
				String[] offerCost = dao.selectAllOfferCostWhere(vo); // 0 : offer_num, 1 : 송금환율, 2 : 송금수수료, 3 : 통관비, 4 : 운반비, 5 : 기타비용, 6 : 총 금액

				tf[0].setText(offerCost[1]); // 송금환율
				tf[1].setText(offerCost[3]); // 통관비
				tf[2].setText(offerCost[5]); // 기타비용
				tf[3].setText(offerCost[2]); // 송금수수료
				tf[4].setText(offerCost[4]); // 운반비
				tf[5].setText(offerCost[6]); // 총 금액
				
				// 테이블 (n, 8)에 offer_list 테이블에 저장된 값으로 set
				vo = new Vo("offer_list", "unit_price_krw", "offer_num", tfText, "num");
				String[] unitPriceKrw = dao.selectOneFieldWhereOrderBy(vo);
				
				for(int i=0; i<unitPriceKrw.length; i++) {
					dtm.setValueAt(unitPriceKrw[i], i, 8);
				}
				
				// (n, 9)에 단가 * 수량 값
				vo = new Vo("offer_list", "quantity", "offer_num", tfText, "num");
				String[] quantities = dao.selectOneFieldWhereOrderBy(vo); // 수량
				
				for(int i=0; i<quantities.length; i++) {
					dtm.setValueAt(Integer.parseInt(unitPriceKrw[i]) * Integer.parseInt(quantities[i]), i, 9);
				}
				
			}
			break;
			
		case "등록" :
			if(tf[0].getText().equals("")) {
				new ErrorMessageDialog("송금환율은 필수 입력값입니다.", "수입원가 등록");
			}else {
				// 총금액을 제외한 모든 TextField의 입력값을 각 변수에 저장
				String s = "0"; // NullPointerException을 처리하기 위해 String s에 먼저 대입
				int currency_exchange = 0; // 송금환율
				int remittance_charge = 0; // 송금수수료
				int custom_clearance_fee = 0; // 통관비
				int freight_charge = 0; // 운반비
				int other_cost = 0; // 기타비용
				
				currency_exchange = Integer.parseInt(tf[0].getText()); // 송금환율
				
				try {
					s = tf[3].getText();
				}catch(NullPointerException e1) {
					s = "0";
				}finally {
					remittance_charge = Integer.parseInt(s); // 송금수수료
				}
				
				try {
					s = tf[1].getText();
				}catch(NullPointerException e1) {
					s = "0";
				}finally {
					custom_clearance_fee = Integer.parseInt(s); // 통관비
				}
				
				try {
					s = tf[4].getText();
				}catch(NullPointerException e1) {
					s = "0";
				}finally {
					freight_charge = Integer.parseInt(s); // 운반비
				}
				
				try {
					s = tf[2].getText();
				}catch(NullPointerException e1) {
					s = "0";
				}finally {
					other_cost = Integer.parseInt(s); // 기타비용
				}
				
				// 1. 금액(USD) 구하기
				// : SELECT SUM(amount) FROM offer_list WHERE offer_num = 'tfTest'
				vo = new Vo("offer_list", "amount", "offer_num", tfText);
				int amount = dao.sumWhere(vo);

				// 2. 총금액(KRW)을 구한후 TextField에 출력
				// : 총금액(KRW) = 금액(USD) * 송금환율 + 송금수수료 + 통관비 + 운반비 + 기타비용
				int amount_krw = amount * currency_exchange + remittance_charge + custom_clearance_fee + freight_charge + other_cost;
				tf[5].setText(String.valueOf(amount_krw));

				// 3. offer_cost 테이블에 송금환율 ~ 총금액까지 6가지 값 저장
				vo = new Vo("offer_cost", "offer_num", "offer_num", tfText);
				String checkOfferCost = dao.selectOneFieldWhere(vo); // offer_cost에 동일한 offeer_num으로 이미 저장된 값이 있는지 확인

				if(checkOfferCost.equals("")) { // 없으면 insert
					vo = new Vo(tfText, currency_exchange, remittance_charge, custom_clearance_fee, freight_charge, other_cost, amount_krw);
					dao.insertOfferCost(vo);
				}else { // 있으면 update
					vo = new Vo("offer_cost",
							"offer_num", tfText, "currency_exchange", currency_exchange,
							"remittance_charge",  remittance_charge, "custom_clearance_fee", custom_clearance_fee,
							"freight_charge", freight_charge, "other_cost", other_cost,
							"amount_krw", amount_krw);
					dao.updateSevenIntFieldWhere(vo);
				}
				
				// 4. 항목별 수량, 단가 구해서 각 배열에 저장후 총 수량을 구한다
				// : offer_list 테이블에서 offer_num이 ontf.getText()와 일치하는 조건의 quantity로 구할 수 있음
				vo = new Vo("offer_list", "quantity", "offer_num", tfText, "num");
				String[] quantities = dao.selectOneFieldWhereOrderBy(vo); // 수량
				
				vo = new Vo("offer_list", "unit_price", "offer_num", tfText, "num");
				String[] unit_price_arr = dao.selectOneFieldWhereOrderBy(vo); // 단가
				
				int quantity_sum = 0; // 총수량
				for(int i=0; i<quantities.length; i++) {
					quantity_sum += Integer.parseInt(quantities[i]);
				}

				// 5. 단가(KRW)을 구하여 테이블(n, 8)에 출력, offer_list의 9열(unit_price_krw)에 저장, 단가(KRW) * 수량을 테이블(n, 9)에 출력
				// : 단가(KRW) = 단가 * 송금환율 + (송금수수료 + 통관비 + 운반비 + 기타비용) / 총 수량
				int unit_price_krw;
				boolean tryUpdateUnitPriceKrw = false;
				for(int i=0; i<rowCount; i++) {		
					// 단가(KRW) 계산
					unit_price_krw = Integer.parseInt(unit_price_arr[i]) * currency_exchange
							+ (remittance_charge + custom_clearance_fee + freight_charge + other_cost) / quantity_sum;
					// 테이블에 단가(KRW) 출력
					dtm.setValueAt(unit_price_krw, i, 8);
					// offer_list에 단가(KRW) 저장
					vo = new Vo("offer_list", "unit_price_krw", unit_price_krw, "num", String.valueOf(i+1));
					tryUpdateUnitPriceKrw = dao.updateOneIntFieldWhere(vo);
					// 테이블에 단가(KRW) * 수량 출력
					dtm.setValueAt(unit_price_krw * Integer.parseInt(quantities[i]), i, 9);
				}
				
				// 결과 출력
				if(tryUpdateUnitPriceKrw == true) {
					new ErrorMessageDialog("원가 정보가 등록되었습니다.", "수입원가 등록");
				}else {
					new ErrorMessageDialog("원가 정보 등록에 실패하였습니다.", "수입원가 등록");
				}
				
			}
			break;
		}
	}
}
