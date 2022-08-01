package erp;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
//import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class Tab_1302 implements ActionListener {
//	폴더 그림:40,40
//	Label 사이즈:150,30
//	TextField 사이즈:200,30
//	Button 사이즈:50,30
//	가로 여백 30, 세로 여백 10
	JPanel p;
	TextField pctf, pntf;
	Button pcb, b1, b2;
	UtilDateModel model;
	DefaultTableModel dtm;
	JTable table;
	Dao dao = new Dao();
	Vo vo;
	
	DefaultTableCellRenderer dcr = new DefaultTableCellRenderer() {
		public Component getTableCellRendererComponent // 셀렌더러
		(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			JCheckBox box = new JCheckBox();
			box.setSelected(((Boolean) value).booleanValue());
			box.setHorizontalAlignment(JLabel.CENTER);
			return box;
		}
	};
	
	public Tab_1302() {
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

		Label l1 = new Label("출고 정보");
		l1.setBounds(50, 10, 150, 30);
		p.add(l1);
		
		// Label 추가
		Label[] label = new Label[3];
		String[] labelName = {"품목코드", "품목명", "출고일자"};

		for (int i = 0; i < label.length; i++) {
			label[i] = new Label(labelName[i]);
			p.add(label[i]);
		}
		
		label[0].setBounds(10, 50, 150, 30); // 품목코드
		label[1].setBounds(10, 90, 150, 30); // 품목명
		label[2].setBounds(10, 130, 150, 30); // 출고일자

		// JDatePicker 추가
		model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		datePicker.setBounds(170, 130, 200, 30);
		
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

		// TextField 추가
		pctf = new TextField();
		pctf.setBounds(170, 50, 200, 30);
		p.add(pctf);

		pntf = new TextField();
		pntf.setBounds(170, 90, 200, 30);
		pntf.setEditable(false); // 변경 불가
		p.add(pntf);

		// Button 추가
		pcb = new Button("…");
		pcb.setBounds(380, 50, 30, 30);
		p.add(pcb);
		/*
		 * 버튼을 TextField 안으로 넣을수 있는지 체크
		 */

		b1 = new Button("조회");
		b1.setBounds(420, 50, 50, 30);
		b1.addActionListener(this);
		p.add(b1);

		b2 = new Button("출고처리");
		b2.setBounds(380, 130, 70, 30);
		b2.addActionListener(this);
		p.add(b2);

		// Table 추가
		String[] header = { "", "순번", "품목코드", "품목명", "단위", "재고수량", "출고수량" };

		dtm = new DefaultTableModel(header, 0);
		table = new JTable(dtm);

		// 열 너비 조정
		TableColumn t0 = table.getColumnModel().getColumn(0);
		TableColumn t1 = table.getColumnModel().getColumn(1);
		TableColumn t2 = table.getColumnModel().getColumn(2);
		TableColumn t3 = table.getColumnModel().getColumn(3);
		TableColumn t4 = table.getColumnModel().getColumn(4);
		TableColumn t5 = table.getColumnModel().getColumn(5);
		TableColumn t6 = table.getColumnModel().getColumn(6);

		t0.setPreferredWidth(75);
		t1.setPreferredWidth(75);
		t2.setPreferredWidth(225);
		t3.setPreferredWidth(400);
		t4.setPreferredWidth(75);
		t5.setPreferredWidth(155);
		t6.setPreferredWidth(155);

		// 행 높이 조정
		table.setRowHeight(0, 30);

		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(10, 170, 1160, 500);
		p.add(sp);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int rowCount = dtm.getRowCount();
		String product_code = pctf.getText();
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
			
			if(product_code == null || product_code.equals("")) {
				vo = new Vo("pl.product_code");
				String[][] result = dao.selectAllProductListAndStockJoinWhere(vo);
				for(int i=0; i<result.length; i++) {
					Object[] addRow = new Object[7];
					addRow[0] = false; // 체크박스
					addRow[1] = count++; // 순번
					addRow[2] = result[i][0]; // 품목코드
					addRow[3] = result[i][1]; // 품목명
					addRow[4] = result[i][10]; // 단위
					addRow[5] = result[i][11]; // 재고수량
					dtm.addRow(addRow);
				}
				table.getColumn("").setCellRenderer(dcr);
				
				JCheckBox box = new JCheckBox();
				box.setHorizontalAlignment(JLabel.CENTER);
				table.getColumn("").setCellEditor(new DefaultCellEditor(box));
				
				count = 1;
			}else {
				vo = new Vo("pl.product_code", product_code, "pl.product_code");
				String[][] result = dao.selectAllProductListAndStockJoinWhereTwoFields(vo);
				for(int i=0; i<result.length; i++) {
					Object[] addRow = new Object[7];
					addRow[0] = false; // 체크박스
					addRow[1] = count++; // 순번
					addRow[2] = result[i][0]; // 품목코드
					addRow[3] = result[i][1]; // 품목명
					addRow[4] = result[i][10]; // 단위
					addRow[5] = result[i][11]; // 재고수량
					dtm.addRow(addRow);
				}
				table.getColumn("").setCellRenderer(dcr);
				
				JCheckBox box = new JCheckBox();
				box.setHorizontalAlignment(JLabel.CENTER);
				table.getColumn("").setCellEditor(new DefaultCellEditor(box));
				
				count = 1;
			}
			break;

		case "출고처리":
			int unstoring_num = 0;
			String unstoring_date = model.getYear() + "-" + (model.getMonth() + 1) + "-" + model.getDay();

			// 출고번호 결정
			String removeYear = unstoring_date.replaceFirst("2", "-"); // 2022-7-21 -> -022-7-21
			removeYear = removeYear.replaceFirst("0", "-"); // -022-7-21 -> --22-7-21
			String[] toSplit = removeYear.split("-"); // --22-7-21 -> "", "", 22, 7, 21
			String date = toSplit[2] + toSplit[3] + toSplit[4]; // 22721

			vo = new Vo("unstoring", "unstoring_num", "unstoring_num", date);
			String checkUnstoringNum = dao.selectOneFieldWhereLike(vo);
			if (checkUnstoringNum == null) {
				unstoring_num = Integer.parseInt(date + "0001"); // 227210001
			} else {
				vo = new Vo("unstoring", "unstoring_num", "unstoring_num", date);
				int i = dao.selectMaxWhereLike(vo) + 1;
				unstoring_num = i;
			}

			// DB테이블에 저장
			boolean b = true;

			// 출고수량 체크, 재고수량 - 출고수량 >= 0 체크
			boolean checkUnstoringQuantityNull = true;
			boolean checkStockQuantity = true;

			for (int i = 0; i < dtm.getRowCount(); i++) {
				b = Boolean.valueOf(table.getValueAt(i, 0).toString()); // 체크여부 확인
				if (b == true) {
					if (table.getValueAt(i, 6) == null || table.getValueAt(i, 6).toString().equals("")) {
						new ErrorMessageDialog("출고수량을 입력해주세요.", "출고 등록");
						checkUnstoringQuantityNull = false; // 출고수량이 빈칸인 행이 있으면 다른 행도 출고처리되지않도록 함
						break;
					}else {
						String product_code2 = table.getValueAt(i, 2).toString();
						vo = new Vo("stock", "quantity", "product_code", product_code2);
						int stock_quantity = Integer.parseInt(dao.selectOneFieldWhere(vo));
						int unstoring_quantity = Integer.parseInt(table.getValueAt(i, 6).toString());
						int updateStockQuantity = stock_quantity - unstoring_quantity;

						if (updateStockQuantity < 0) {
							new ErrorMessageDialog("(-)재고를 허용하지않는 창고입니다.", "출고 등록");
							checkStockQuantity = false; // 수행 결과로 재고수량이 (-)가 되는 행이 있으면 출고처리되지않도록 함
							break;
						}
					}
				}
			}
			
			// DB에 저장
			boolean tryInsertUnstoring = false;
			
			if (checkUnstoringQuantityNull == true && checkStockQuantity == true) { // 체크된 모든 행의 출고수량에 빈칸이 없고, 실행후
				for (int i = 0; i < dtm.getRowCount(); i++) {
					b = Boolean.valueOf(table.getValueAt(i, 0).toString());
					if (b == true) {
						// 출고수량 저장
						int unstoring_quantity = Integer.parseInt(table.getValueAt(i, 6).toString());
						
						// 출고처리 후 재고수량을 구한다
						String product_code2 = table.getValueAt(i, 2).toString();
						vo = new Vo("stock", "quantity", "product_code", product_code2);
						int stock_quantity = Integer.parseInt(dao.selectOneFieldWhere(vo));
						int updateStockQuantity = stock_quantity - unstoring_quantity;
						
						// 출고 table에 insert
						String product_name = table.getValueAt(i, 3).toString();
						String unit = table.getValueAt(i, 4).toString();
						
						vo = new Vo(unstoring_num, unstoring_date, product_code2, product_name, unit,
								unstoring_quantity);
						tryInsertUnstoring = dao.insertUnstoring(vo);
						
						if (tryInsertUnstoring == true) {
							table.setValueAt(String.valueOf(updateStockQuantity), i, 5); // 재고수량을 변경된 값으로 수정
							table.setValueAt("", i, 6);// 출고수량 셀을 비움
						} else {
							String falseMessage = String.valueOf(i) + "행의 출고 처리에 실패하였습니다.";
							new ErrorMessageDialog(falseMessage, "출고 등록");
							break;
						}
						
						// 재고 table에 update
						vo = new Vo("stock", "quantity", updateStockQuantity, "product_code", product_code2);
						dao.updateOneIntFieldWhere(vo);
					}
				} // for문의 끝
			}

			if (tryInsertUnstoring == true) {
				new ErrorMessageDialog("출고 처리되었습니다.", "출고 등록");
			}

			break;
		}
	}
}
