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
	Button searchButton;
	Button saveButton;
	Button addButton;
	Button deleteButton;
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
		
//		String[][] contents = {
//				{"", "", "", "", "", "", ""}
//		};
		
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
				}else {
					// offer테이블 데이터 출력
					System.out.println(Arrays.toString(result));
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
					Vo searchOfferList = new Vo("offer_list", "offer_num", "1");
					String[][] result2 = dao.selectAllOfferListWhere(searchOfferList);
					
					for(int i=0; i<result2.length; i++) {
						String[] addRow = new String[8];
						for(int j=0; j<result2[i].length; j++) {
							addRow[j] = result2[i][j];
						}
						dtm.addRow(addRow);
					}
					
				}
				// offer, offer_list 테이블 select
			}
			
			break;
		
		case "저장" :
			if(rowCount == 0) { // table에 행이 하나도 없을때 -> offer정보 저장
				if(tfText.equals("")) {
					new ErrorMessageDialog("offer번호를 입력하세요.", "수입offer 등록");
				}else {
//				offer table에 데이터 저장
					String offer_num = tfText;
					String client_name = ch[0].getSelectedItem();
					String offer_date = model.getYear() + "-" + (model.getMonth() + 1) + "-" + model.getDay();
					String incoterms = ch[1].getSelectedItem();
					String currency = ch[2].getSelectedItem();
					
					boolean b = true;
					Vo vo = new Vo(offer_num, client_name, offer_date, incoterms, currency);
					b = dao.insertOffer(vo);
					if(b == true) {
						new ErrorMessageDialog("수입offer가 등록되었습니다.", "수입offer 등록");
					}else{
						new ErrorMessageDialog("이미 등록된 offer번호입니다.", "수입offer 등록");
					}
				}
			}else { // table에 행이 하나라도 있을때 -> offer상세정보 저장
			/*
			 첫번째 등록이 아닌 추가 등록 또는 수정일때 데이터가 수정되도록 코드 작성 필요
			 -> offer번호 값을 읽어서 이미 등록되어있을때 : num 마지막 번호를 체크
			 -> 등록되어있지않을때 -> 성공
			 
			 insert 실패할 경우
			 1) 품목코드 미등록(부모키가 없습니다)
			 2) offer_num 미등록(부모키가 없습니다)
			 3) int 타입 데이터에 varchar2 타입 데이터를 넣는 등의 타입불일치(수치가 부적합합니다)
			 위 경우에 대한 에러코드를 반환받아서 상황에 적절한 에러메시지를 출력하도록 수정 필요
			 */
				boolean b2 = true;
				String[] grv = new String[7];
				for(int i=0; i<dtm.getRowCount(); i++) { // 행 index : 0 ~ rowCount - 1까지
					for(int j=0; j<grv.length; j++) {
						grv[j] = String.valueOf(dtm.getValueAt(i, j));
					}
					Vo offerListVo = new Vo(tfText, grv[0], grv[1], grv[2], grv[3], grv[4], grv[5], grv[6]); // 열 길이 8. 첫번째는 OFFER_NUM, 나머지는 TABLE 값 읽어오기
					b2 = dao.insertOfferList(offerListVo);
					if(b2 == false) {
						String message = "" + i + "행의 번호 또는 입력 형식이 잘못되었습니다.";
						new ErrorMessageDialog(message, "수입offer 등록");
						break;
					}
				}
				if(b2 == true) {
					new ErrorMessageDialog("저장되었습니다.", "수입offer 등록");
				}
			}
			break;

		case "+" :
			String[] addRow = {String.valueOf(rowCount + 1), "", "", "", "", "", ""};
			dtm.addRow(addRow);
			break;
			
		case "-" :
			/*
			 삭제 클릭시 실제 데이터도 삭제되도록 작성 필요
			 */
			if(rowCount == 0) {
				new ErrorMessageDialog("삭제할 행이 없습니다.", "수입offer 등록");
			}else {
				DeleteConfirmDialog d = new DeleteConfirmDialog("삭제하시겠습니까?", "수입offer 등록");
				boolean b = d.response();
				if(b == true) {
					dtm.removeRow(dtm.getRowCount() - 1);
				}
			}
			break;
			
		case "…" :
			break;
			
		}
	}
}
