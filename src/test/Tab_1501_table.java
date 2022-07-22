package test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;
import javax.swing.table.*;


public class Tab_1501_table implements ActionListener {
//	���� �׸� : 40, 40
//	Label ������ : 150, 30
//	TextField ������ : 200, 30
//	Button ������ : 50, 30
//	���� ���� 30, ���� ���� 10
	
	JPanel p;
	
	public Tab_1501_table() {
		p = new JPanel();
		p.setLayout(null);
		
		// icon
		JLabel imageLabel = new JLabel(); // ImageIcon �� ���� Label ����

		ImageIcon folder = new ImageIcon("src/images/folder-icon_34416.png");
		Image img = folder.getImage(); // image ũ�Ⱑ 512, 512�̹Ƿ� 40, 40���� �ٲٱ� ���� ImageIcon -> Image�� ����
		Image changeimg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon changefolder = new ImageIcon(changeimg);
		
		imageLabel.setIcon(changefolder);
		p.add(imageLabel);
		imageLabel.setBounds(10, 10, 30, 30);
		
		Label l1 = new Label("��� ���");
		l1.setBounds(50, 10, 150, 30);
		p.add(l1);
		
		// Label
		Label[] label = new Label[4];
		String[] labelName = {
				"����ڵ�(ID)", "�̸�",
				"password", "�μ�"};

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
		JLabel imageLabel2 = new JLabel(); // ImageIcon �� ���� Label ����

		ImageIcon folder2 = new ImageIcon("src/images/folder-icon_34416.png");
		Image img2 = folder2.getImage(); // image ũ�Ⱑ 512, 512�̹Ƿ� 40, 40���� �ٲٱ� ���� ImageIcon -> Image�� ����
		Image changeimg2 = img2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon changefolder2 = new ImageIcon(changeimg2);

		imageLabel2.setIcon(changefolder2);
		p.add(imageLabel2);
		imageLabel2.setBounds(10, 130, 30, 30);
		
		Label l2 = new Label("���� ����");
		l2.setBounds(50, 130, 150, 30);
		p.add(l2);
		
		// Button
		Button[] button = new Button[3];
		String[] buttonName = {
				"���", "��ȸ", "����"
		};

		for(int i=0; i<button.length; i++) {
			button[i] = new Button(buttonName[i]);
			p.add(button[i]);
		}
		
		button[0].setBounds(750, 50, 50, 30);
		button[1].setBounds(10, 170, 50, 30);
		button[2].setBounds(70, 170, 50, 30);
		
		// ��ȸ table
		String[] header1 = {"����ڵ�(ID)", "�μ��ڵ�", "�μ���", "�̸�", "password"};
		String[][] contents1 = {
				{"","","","",""}
		};
		JTable table1 = new JTable(contents1, header1);
		
		JScrollPane sp1 = new JScrollPane(table1);
		sp1.setBounds(10, 210, 1000, 50);
		p.add(sp1);

		//���� table
		String[] header2 = {"��޴�", "�߸޴�", "�б�", "����", "����", "����"};
		String[][] contents2 = {
				{"ǰ��","ǰ���ڵ� ���","","","",""},
				{"����","����offer ���","","","",""},
				{"","���Կ��� ���","","","",""},
				{"","�԰� ���","","","",""},
				{"����","��� ���","","","",""},
				{"","���� ��Ȳ","","","",""},
				{"���","���� ���","","","",""},
				{"","ǰ����Һ�","","","",""},
				{"�λ�","���� �� ���� ����","","","",""}
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
