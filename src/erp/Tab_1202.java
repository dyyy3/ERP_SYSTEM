package erp;

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
				Vo searchOffer = new Vo("offer", "offer_num", tfText);
				String[] result = dao.selectAllOfferWhere(searchOffer);
				if(result.length == 0) {
					new ErrorMessageDialog("등록되지않은 offer번호입니다.", "수입원가 등록");
				}else {
					// 0 순번 : offer_list테이블의 두번째 필드(num)
					Vo selectIndex0 = new Vo("offer_list", "num", "offer_num", tfText, "num");
					String[] selectResult1 = dao.selectOneFieldWhereOrderBy(selectIndex0);
					
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
					Vo selectIndex1 = new Vo("offer", "client_name", "offer_num", tfText);
					String selectResult2 = dao.selectOneFieldWhere(selectIndex1);
					
					for(int i=0; i<selectResult1.length; i++) {
						dtm.setValueAt(selectResult2, i, 1);
					}
					
					// 2 품목코드 : offer_list테이블의 세번째 필드(product_code)
					// 3 품목명 : offer_list테이블의 네번째 필드(product_name)
					// 4 단위 : offer_list테이블의 다섯번째 필드(unit)
					// 5 수량 : offer_list테이블의 여섯번째 필드(quantity)
					// 6 단가 : offer_list테이블의 일곱번째 필드(unit_price)
					// 7 금액 : offer_list테이블의 여덟번째 필드(amount)
					
					Vo selectIndex2 = new Vo("offer_list", "offer_num", tfText, "num");
					String[][] selectResult3 = dao.selectAllOfferListWhere(selectIndex2);
					
					for(int i=0; i<selectResult3.length; i++) {
						String[] addRow = new String[6];
						for(int j=0; j<selectResult3[i].length - 3; j++) {
							addRow[j] = selectResult3[i][j+2];
							dtm.setValueAt(addRow[j], i, j+2);
						}
					}
					// 수량을 list에 저장
//					for(int i=0; i<selectResult3.length; i++) {
//						quantities.add(selectResult3[i][5]);
//					}
				}
			}
			
			break;
			
		case "등록" :
			if(tf[0].getText().equals("")) {
				new ErrorMessageDialog("송금환율은 필수 입력값입니다.", "수입원가 등록");
			}else {
				// 총금액을 제외한 모든 TextField의 입력값을 읽어와서 offer_cost에 저장
				int currency_exchange = Integer.parseInt(tf[0].getText()); // 송금환율
				int remittance_charge = Integer.parseInt(tf[3].getText()); // 송금수수료
				int custom_clearance_fee = Integer.parseInt(tf[1].getText()); // 통관비
				int freight_charge = Integer.parseInt(tf[4].getText()); // 운반비
				int other_cost = Integer.parseInt(tf[2].getText()); // 기타비용
				
				// 총 금액(KRW)을 구해서 TextField와 table의 (n,9)에 출력
				Vo vo = new Vo("offer_list", "amount");
				int amount = dao.sum(vo);
				int amount_krw = amount * currency_exchange + remittance_charge + custom_clearance_fee + freight_charge + other_cost; // 총 금액 = 금액 * 송금환율 + 송금수수료 + 통관비 + 운반비 + 기타비용
				tf[5].setText(String.valueOf(amount_krw));
				
				for(int i=0; i<rowCount; i++) {
					dtm.setValueAt(amount_krw, i, 8);
				}
				
				// 단가(KRW)을 구해서 table의 (n,10)에 출력하고, offer_list의 9열(unit_price_krw)에 저장
				Vo vo2 = new Vo("offer_list", "quantity", "offer_num", tfText, "num");
				String[] quantities = dao.selectOneFieldWhereOrderBy(vo2);
				
				// UPDATE OFFER_LIST SET UNIT_PRICE_KRW = '20000' WHERE num = '1'
				int unit_price_krw;
				Vo vo3;
				boolean tryUpdateUnitPriceKrw = false;
				for(int i=0; i<rowCount; i++) {
					unit_price_krw = amount_krw / Integer.parseInt(quantities[i]);
					dtm.setValueAt(unit_price_krw, i, 9);
					vo3 = new Vo("offer_list", "unit_price_krw", unit_price_krw, "num", String.valueOf(i+1));
					tryUpdateUnitPriceKrw = dao.updateOneIntFieldWhere(vo3);
				}
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
