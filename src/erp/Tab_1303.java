package erp;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
//import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class Tab_1303 implements ActionListener {
//	폴더 그림:40,40
//	Label 사이즈:150,30
//	TextField 사이즈:200,30
//	Button 사이즈:50,30
//	가로 여백 30, 세로 여백 10
	JPanel p;
	TextField pctf, pntf;
	DefaultTableModel dtm;
	JTable table;
	Dao dao = new Dao();
	Vo vo;
	
	public Tab_1303() {
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
		Label[] label = new Label[2];
		String[] labelName = {"품목코드", "품목명"};

		for (int i = 0; i < label.length; i++) {
			label[i] = new Label(labelName[i]);
			p.add(label[i]);
		}
		
		label[0].setBounds(10, 50, 150, 30);
		label[1].setBounds(10, 90, 150, 30);
		
		// TextField 추가
		pctf = new TextField();
		pctf.setBounds(170, 50, 200, 30);
		p.add(pctf);

		pntf = new TextField();
		pntf.setBounds(170, 90, 200, 30);
		pntf.setEditable(false); // 변경 불가
		p.add(pntf);
		
		// Button 추가
		Button pcb = new Button("…");
		pcb.setBounds(380, 50, 30, 30);
		p.add(pcb);
		/*
		버튼을 TextField 안으로 넣을수 있는지 체크
		*/
		
		Button b1 = new Button("조회");
		b1.setBounds(440, 50, 50, 30);
		b1.addActionListener(this);
		p.add(b1);

		// Table 추가
		String[] header = {"순번", "품목코드", "품목명", "단위", "재고 수량"};
		dtm = new DefaultTableModel(header, 0);
		table = new JTable(dtm);
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(10, 130, 1000, 500);
		p.add(sp);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int rowCount = dtm.getRowCount();
		int count = 1;
		
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
			
			String[][] result;
			
			if(pctf.getText() == null || pctf.getText().equals("")) { // 모든 품목 조회
				vo = new Vo("s.PRODUCT_CODE", "pl.PRODUCT_NAME", "s.UNIT", "s.QUANTITY", "PRODUCT_NAME");
				result = dao.selectFourFieldsProductListAndStockJoinWhere(vo);
				
			}else { // 입력된 품목 조회
				String product_code = pctf.getText();
				vo = new Vo("s.PRODUCT_CODE", "pl.PRODUCT_NAME", "s.UNIT", "s.QUANTITY", "s.PRODUCT_CODE", product_code, "PRODUCT_NAME");
				result = dao.selectFourFieldsProductListAndStockJoinWhereTwoFields(vo);
			}
			
			for(int i=0; i<result.length; i++) {
				Object[] addRow = new Object[7];
				addRow[0] = count++; // 순번
				addRow[1] = result[i][0]; // 품목코드
				addRow[2] = result[i][1]; // 품목명
				addRow[3] = result[i][2]; // 단위
				addRow[4] = result[i][3]; // 재고수량
				dtm.addRow(addRow);
			}
			count = 1;
			break;
		}
	}
}
