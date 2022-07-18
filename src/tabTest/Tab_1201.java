package tabTest;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;
import javax.swing.table.*;
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
	DefaultTableModel dtm;
	Choice[] ch;
	Button searchButton, saveButton, addButton, deleteButton;
	Dao dao;
	
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
		
		// JDatePicker 추가
		model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		datePicker.setBounds(170, 90, 200, 30);
		
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
		
		// Choice 추가
		ch = new Choice[3];
		
		for(int i=0; i<ch.length; i++) {
			ch[i] = new Choice();
			p.add(ch[i]);
			ch[i].addItemListener((ItemListener)this);
		}

		ch[0].setBounds(540, 50, 200, 30);
		ch[1].setBounds(540, 90, 200, 30);
		ch[2].setBounds(170, 130, 200, 30);
		
		// ch[0] : 거래처
		dao = new Dao();
		
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
		Vo currencyVo = new Vo("country", "currency");
		String[] currencyList = dao.selectOneFieldDistinct(currencyVo);
		for(int i=0; i<currencyList.length; i++) {
			ch[2].add(currencyList[i]);
		}
		
		// Button
		searchButton = new Button("조회");
		searchButton.setBounds(750, 50, 50, 30);
		searchButton.addActionListener(this);
		p.add(searchButton);

		saveButton = new Button("저장");
		saveButton.setBounds(750, 90, 50, 30);
		saveButton.addActionListener(this);
		p.add(saveButton);

		addButton = new Button("+");
		addButton.setBounds(1000, 130, 50, 30);
		addButton.addActionListener(this);
		p.add(addButton);

		addButton = new Button("-");
		addButton.setBounds(1060, 130, 50, 30);
		addButton.addActionListener(this);
		p.add(addButton);
		
		
		// Table 추가
		String[] header = {"순번", "품목코드", "품목명", "단위", "수량", "단가", "금액"};
		dtm = new DefaultTableModel(header, 0);		
		JTable table = new JTable(dtm);
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(10, 170, 1000, 500);
		p.add(sp);
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int rowCount = dtm.getRowCount(); // 행 갯수를 구하는 메서드
		String tfText = tf.getText();
		
		switch(e.getActionCommand()) {
		
		case "조회" :
			if(tfText == null) {
				new ErrorMessageDialog("offer번호를 입력하세요.", "수입offer 등록");
			}else {
				Vo searchOffer = new Vo("offer", "offer_num", tfText);
				String[] result = dao.selectAllOfferWhere(searchOffer);
				if(result.length == 0) {
					new ErrorMessageDialog("등록되지않은 offer번호입니다.", "수입offer 등록");
					int deleteRow = rowCount; // 이전에 조회되어있던 offer의 상세정보를 테이블에서 보이지않게함
					if(deleteRow != 0) {
						while(deleteRow != 0) {
							dtm.removeRow(deleteRow - 1);
							deleteRow--;
						}
					}
				}else {
					// offer테이블 데이터 출력
					tf.setText(result[0]);
					ch[0].select(result[1]);
					
					String[] date = result[2].split("-");
					int dateY = Integer.parseInt(date[0]);
					int dateM = Integer.parseInt(date[1]) - 1;
					int dateD = Integer.parseInt(date[2]);
					model.setDate(dateY, dateM, dateD);
					
					ch[1].select(result[3]);
					ch[2].select(result[4]);
					
					// offer_list테이블 데이터 출력
					Vo searchOfferList = new Vo("offer_list", "offer_num", tfText, "num");
					String[][] result2 = dao.selectAllOfferListWhere(searchOfferList);
					
					int deleteRowCount = rowCount; // 데이터 출력전에 현재 추가되어있는 모든 행을 삭제한다
					if(deleteRowCount == 0) {
						
					}else {
						while(deleteRowCount != 0){
							dtm.removeRow(deleteRowCount - 1);
							deleteRowCount--;
						}
					}
					
					for(int i=0; i<result2.length; i++) {
						String[] addRow = new String[7];
						for(int j=0; j<result2[i].length - 2; j++) { 
							addRow[j] = result2[i][j+1]; // 0번에는 offer_num 값이 있으므로 넣지않음 
						}
						dtm.addRow(addRow);
					}
					
				}
				// offer, offer_list 테이블 select
			}
			break;
		
		case "저장" :
			String offer_num = tfText;
			String client_name = ch[0].getSelectedItem(); // 거래처
			String offer_date = model.getYear() + "-" + (model.getMonth() + 1) + "-" + model.getDay();
			String incoterms = ch[1].getSelectedItem(); // 가격조건
			String currency = ch[2].getSelectedItem(); // 통화
			
			// offer 번호, 거래처, 일자, 가격조건, 통화 빈칸 유무 체크
			boolean checkNull = false;

			if(offer_num.equals("")) { // 일자는 오늘 날짜로 default값이 정해져 있어 빈칸인 경우가 없으므로 조건에 미포함
				new ErrorMessageDialog("offer번호를 입력하세요.", "수입 offer 등록");
				checkNull = true;
			}else if(client_name.equals("")) {
				new ErrorMessageDialog("거래처를 입력하세요.", "수입 offer 등록");
				checkNull = true;
			}else if(incoterms.equals("")) {
				new ErrorMessageDialog("가격조건을 입력하세요.", "수입 offer 등록");
				checkNull = true;
			}else if(currency.equals("")) {
				new ErrorMessageDialog("통화를 입력하세요.", "수입 offer 등록");
				checkNull = true;
			}
			
			// 빈칸 없을때 -> 미등록이면 insert, 기등록이면 update
			boolean tryInsertOffer = false; // offer번호 미등록
			boolean tryUpdateOffer = false; // offer번호 기등록
			
			// offer 테이블
			if(checkNull == false) {
				Vo checkOfferNum = new Vo("offer", "offer_num", "offer_num", tfText);
				String selectedOfferNum = dao.selectOneFieldWhere(checkOfferNum);
				if(selectedOfferNum.equals("")) {
					// 미등록 -> insert
					Vo vo = new Vo(offer_num, client_name, offer_date, incoterms, currency);
					tryInsertOffer = dao.insertOffer(vo);
					if(tryInsertOffer == true) {
						new ErrorMessageDialog("offer정보가 등록되었습니다.", "수입offer 등록");
					}else{
						new ErrorMessageDialog("이미 등록된 offer번호입니다.", "수입offer 등록");
					}
				}else {
					// 기등록 -> update
					Vo vo = new Vo("offer", "client_name", client_name, "offer_date", offer_date,
							"incoterms", incoterms, "currency", currency, "offer_num", offer_num);
					tryUpdateOffer = dao.updateFourFieldsWhere(vo);
					if(tryUpdateOffer == true) {
						new ErrorMessageDialog("offer정보가 수정되었습니다.", "수입offer 등록");
					}else{
						new ErrorMessageDialog("offer정보 수정 실패하였습니다.\n관리자에게 문의하세요.", "수입offer 등록");
					}
				}
			}
			
			// offer_list 테이블
			if(tryInsertOffer == true || tryUpdateOffer == true) { // offer번호 미등록, 기등록 상태 중 하나로 insert 또는 update가 성공하였을때
				if(rowCount == 0) {
					new ErrorMessageDialog("저장할 데이터가 없습니다.","수입offer 등록");
				}
				else {
					Vo vo = new Vo("offer_list", "offer_num", tfText);
					int count = dao.countAllWhere(vo);
					int tableFirstNum = Integer.parseInt(dtm.getValueAt(0, 0).toString());
					
					if(tryInsertOffer == true && tryUpdateOffer == false) {
						if(count + 1 == tableFirstNum) { // true : DB에 저장된 순번에 이어서 현재 테이블의 순번을 붙일 수 있음
							if(count == 0) { // insert
								boolean tryInsetOfferList = false;
								String[] grv = new String[7];
								for(int i=0; i<rowCount; i++) { // 행 index : 0 ~ rowCount - 1까지
									for(int j=0; j<grv.length; j++) {
										grv[j] = String.valueOf(dtm.getValueAt(i, j));
									}
									Vo offerListVo = new Vo(tfText, grv[0], grv[1], grv[2], grv[3], grv[4], grv[5], grv[6]); // 열 길이 8. 첫번째는 OFFER_NUM, 나머지는 TABLE 값 읽어오기
									tryInsetOfferList = dao.insertOfferList(offerListVo);
									if(tryInsetOfferList == false) {
										String message = "" + i + "행의 번호 또는 입력 형식이 잘못되었습니다.";
										new ErrorMessageDialog(message, "수입offer 등록");
										break;
									}
								}
								if(tryInsetOfferList == true) {
									new ErrorMessageDialog("저장되었습니다.", "수입offer 등록");
								}
							} // if(count == 0)문의 끝
							else { // 1부터 offer_list 마지막 레코드 갯수까지는 update, dtm.getValueAt(0, 0)부터 마지막까지는 insert
								// 1부터 offer_list 마지막 레코드 갯수까지 update
								boolean tryInsertOfferList = false;
								boolean tryUpdateOfferList = false;
								
								String[] grv = new String[7];
								
								for(int i=0; i<rowCount; i++) { // 행 index : 0 ~ rowCount - 1까지
									for(int j=0; j<grv.length; j++) {
										grv[j] = String.valueOf(dtm.getValueAt(i, j));
									}
									if(i < count) { // update : 0 ~ count-1까지
										Vo offerListVo1 = new Vo("offer_list", "product_code", grv[1], "product_name", grv[2],
												"unit", grv[3], "quantity", grv[4], "unit_price", grv[5],
												"amount", grv[6], "offer_num", tfText, "num", grv[0]);
										tryUpdateOfferList = dao.updateSixFieldsWhereTwoField(offerListVo1);
									}else { // insert : count ~ rowCount-1까지
										Vo offerListVo1 = new Vo(tfText, grv[0], grv[1], grv[2], grv[3], grv[4], grv[5], grv[6]); // 열 길이 8. 첫번째는 OFFER_NUM, 나머지는 TABLE 값 읽어오기
										tryInsertOfferList = dao.insertOfferList(offerListVo1);
									}
									
									System.out.println(tryInsertOfferList);
									System.out.println(tryUpdateOfferList);
									
									if(tryInsertOfferList == false || tryUpdateOfferList == false) {
										String message = "" + i + "행의 번호 또는 입력 형식이 잘못되었습니다.";
										new ErrorMessageDialog(message, "수입offer 등록");
										break;
									}
								}
							} // else문의 끝
						} // if(count + 1 == tableFirstNum)문의 끝
						else {
							new ErrorMessageDialog("순번을 확인해주세요.", "수입offer 등록");
						}
					} // if(tryInsertOffer == true && tryUpdateOffer == false)문의 끝
					
					else if(tryInsertOffer == false && tryUpdateOffer == true) {
						boolean checkFirstNum = false;
						if(Integer.valueOf(dtm.getValueAt(0, 0).toString()) == 1) {
							checkFirstNum = true;
						}else {
							new ErrorMessageDialog("순번을 1부터 순서대로 입력해주세요.", "수입offer 등록");
						}
						
						if(checkFirstNum == true && count == 0) { // insert
							boolean tryInsetOfferList = false;
							String[] grv = new String[7];
							for(int i=0; i<rowCount; i++) { // 행 index : 0 ~ rowCount - 1까지
								for(int j=0; j<grv.length; j++) {
									grv[j] = String.valueOf(dtm.getValueAt(i, j));
								}
								Vo offerListVo = new Vo(tfText, grv[0], grv[1], grv[2], grv[3], grv[4], grv[5], grv[6]); // 열 길이 8. 첫번째는 OFFER_NUM, 나머지는 TABLE 값 읽어오기
								tryInsetOfferList = dao.insertOfferList(offerListVo);
								if(tryInsetOfferList == false) {
									String message = "" + i + "행의 번호 또는 입력 형식이 잘못되었습니다.";
									new ErrorMessageDialog(message, "수입offer 등록");
									break;
								}
							}
							if(tryInsetOfferList == true) {
								new ErrorMessageDialog("저장되었습니다.", "수입offer 등록");
							}
						}else if(checkFirstNum == true && count != 0) { // 전체 delete후 insert
							DeleteConfirmDialog d = new DeleteConfirmDialog("이미 등록된 내용이있습니다. 수정하시겠습니까?", "수입offer 등록");
							boolean b = d.response();
							if(b == true) {
								// delete
								int deleteRow = count; // 테이블의 현재 레코드 갯수를 대입
								boolean deleteResult = false; 
										
								while(true) {
									if(deleteRow == 0) {
										break;
									}
									Vo deleteVo = new Vo("offer_list", "offer_num", tfText, "num", String.valueOf(deleteRow));
									deleteResult = dao.deleteWhereTwoFiled(deleteVo);
									deleteRow--;
								}
								// insert
								boolean tryInsetOfferList = false;
								String[] grv = new String[7];
								for(int i=0; i<rowCount; i++) { // 행 index : 0 ~ rowCount - 1까지
									for(int j=0; j<grv.length; j++) {
										grv[j] = String.valueOf(dtm.getValueAt(i, j));
									}
									Vo offerListVo = new Vo(tfText, grv[0], grv[1], grv[2], grv[3], grv[4], grv[5], grv[6]); // 열 길이 8. 첫번째는 OFFER_NUM, 나머지는 TABLE 값 읽어오기
									tryInsetOfferList = dao.insertOfferList(offerListVo);
									if(tryInsetOfferList == false) {
										String message = "" + i + "행의 번호 또는 입력 형식이 잘못되었습니다.";
										new ErrorMessageDialog(message, "수입offer 등록");
										break;
									}
								}
								if(tryInsetOfferList == true) {
									new ErrorMessageDialog("저장되었습니다.", "수입offer 등록");
								}
							} //if(b == true)문의 끝
						} // else if(checkFirstNum == true && count != 0)
					} // else if(tryInsertOffer == false && tryUpdateOffer == true)문의 끝
				} // else문의 끝
			} // if(tryInsertOffer == true || tryUpdateOffer == true)문의 끝
			break;

		case "+" :
			String[] addRow = {String.valueOf(rowCount + 1), "", "", "", "", "", ""};
			dtm.addRow(addRow);
			break;
			
		case "-" :
			if(rowCount == 0) {
				new ErrorMessageDialog("삭제할 행이 없습니다.", "수입offer 등록");
			}else {
				DeleteConfirmDialog d = new DeleteConfirmDialog("삭제하시겠습니까?", "수입offer 등록");
				boolean b = d.response();
				if(b == true) {
					String deleteRow = String.valueOf(dtm.getValueAt(rowCount - 1, 0)); // 삭제할 행의 순번(num)을 읽어온다
					dtm.removeRow(dtm.getRowCount() - 1);
					boolean b3 = true;
					Vo deleteVo = new Vo("offer_list", "num", deleteRow);
					b3 = dao.delete(deleteVo);
					if(b3 == true) {
						new ErrorMessageDialog("삭제되었습니다.", "수입offer 등록");
					}
				}
			}
			break;
			
		case "…" :
			break;
			
		}
	}
}
