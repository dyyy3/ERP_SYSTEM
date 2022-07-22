package test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;
import javax.swing.table.*;


public class Tab_1501_table implements ActionListener {
//	폴더 그림 : 40, 40
//	Label 사이즈 : 150, 30
//	TextField 사이즈 : 200, 30
//	Button 사이즈 : 50, 30
//	가로 여백 30, 세로 여백 10
	
	JPanel p;
	
	public Tab_1501_table() {
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
		
		Label l1 = new Label("사원 등록");
		l1.setBounds(50, 10, 150, 30);
		p.add(l1);
		
		// Label
		Label[] label = new Label[4];
		String[] labelName = {
				"사원코드(ID)", "이름",
				"password", "부서"};

		for(int i=0; i<label.length; i++) {
			label[i] = new Label(labelName[i]);
			p.add(label[i]);
		}
		
		label[0].setBounds(10, 50, 150, 30);
		label[1].setBounds(10, 90, 150, 30);
		label[2].setBounds(380, 50, 150, 30);
		label[3].setBounds(380, 90, 150, 30);
		
		// TextField
		TextField[] tf = new TextField[4];
		
		for(int i=0; i<tf.length; i++) {
			tf[i] = new TextField();
			p.add(tf[i]);
		}
		
		tf[0].setBounds(170, 50, 200, 30);
		tf[1].setBounds(170, 90, 200, 30);
		tf[2].setBounds(540, 50, 200, 30);
		tf[3].setBounds(540, 90, 200, 30);
		
		// icon
		JLabel imageLabel2 = new JLabel(); // ImageIcon 을 담을 Label 생성

		ImageIcon folder2 = new ImageIcon("src/images/folder-icon_34416.png");
		Image img2 = folder2.getImage(); // image 크기가 512, 512이므로 40, 40으로 바꾸기 위해 ImageIcon -> Image로 변경
		Image changeimg2 = img2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon changefolder2 = new ImageIcon(changeimg2);

		imageLabel2.setIcon(changefolder2);
		p.add(imageLabel2);
		imageLabel2.setBounds(10, 130, 30, 30);
		
		Label l2 = new Label("권한 설정");
		l2.setBounds(50, 130, 150, 30);
		p.add(l2);
		
		// Button
		Button[] button = new Button[3];
		String[] buttonName = {
				"등록", "조회", "수정"
		};

		for(int i=0; i<button.length; i++) {
			button[i] = new Button(buttonName[i]);
			p.add(button[i]);
		}
		
		button[0].setBounds(750, 50, 50, 30);
		button[1].setBounds(10, 170, 50, 30);
		button[2].setBounds(70, 170, 50, 30);
		
		// 조회 table
		String[] header1 = {"사원코드(ID)", "부서코드", "부서명", "이름", "password"};
		String[][] contents1 = {
				{"","","","",""}
		};
		JTable table1 = new JTable(contents1, header1);
		
		JScrollPane sp1 = new JScrollPane(table1);
		sp1.setBounds(10, 210, 1000, 50);
		p.add(sp1);

		//권한 table
		String[] header2 = {"대메뉴", "중메뉴", "읽기", "쓰기", "수정", "삭제"};
		String[][] contents2 = {
				{"품목","품목코드 등록","","","",""},
				{"무역","수입offer 등록","","","",""},
				{"","수입원가 등록","","","",""},
				{"","입고 등록","","","",""},
				{"자재","출고 등록","","","",""},
				{"","자재 현황","","","",""},
				{"결산","원가 계산","","","",""},
				{"","품목수불부","","","",""},
				{"인사","계정 및 권한 관리","","","",""}
		};
		DefaultTableModel dtm = new DefaultTableModel(contents2, header2);
		JTable table2 = new JTable(dtm);
//		JTable table2 = new JTable(contents2, header2);
		JScrollPane sp2 = new JScrollPane(table2);
		
		table2.getColumnModel().getColumn(2).setCellRenderer(new AddCheckBox());
		table2.getColumnModel().getColumn(2).setCellEditor(new AddCheckBox());

		table2.getColumnModel().getColumn(3).setCellRenderer(new AddCheckBox());
		table2.getColumnModel().getColumn(3).setCellEditor(new AddCheckBox());
		
		table2.getColumnModel().getColumn(4).setCellRenderer(new AddCheckBox());
		table2.getColumnModel().getColumn(4).setCellEditor(new AddCheckBox());

		table2.getColumnModel().getColumn(5).setCellRenderer(new AddCheckBox());
		table2.getColumnModel().getColumn(5).setCellEditor(new AddCheckBox());
		
		sp2.setBounds(10, 270, 1000, 200);
		p.add(sp2);
		
		
	}

	class AddCheckBox extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {

		JCheckBox cb;
		
		public AddCheckBox() {
			cb = new JCheckBox();
		}
		
		@Override
		public Object getCellEditorValue() {
			return null;
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			return cb;
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			return cb;
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
