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
//	���� �׸�:40,40
//	Label ������:150,30
//	TextField ������:200,30
//	Button ������:50,30
//	���� ���� 30, ���� ���� 10
	JPanel p;
	TextField pctf, pntf;
	Button pcb, b1, b2;
	UtilDateModel model;
	DefaultTableModel dtm;
	JTable table;
	Dao dao = new Dao();
	Vo vo;
	
	DefaultTableCellRenderer dcr = new DefaultTableCellRenderer() {
		public Component getTableCellRendererComponent // ��������
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
		JLabel imageLabel = new JLabel(); // ImageIcon �� ���� Label ����

		ImageIcon folder = new ImageIcon("src/images/folder-icon_34416.png");
		Image img = folder.getImage(); // image ũ�Ⱑ 512, 512�̹Ƿ� 40, 40���� �ٲٱ� ���� ImageIcon -> Image�� ����
		Image changeimg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon changefolder = new ImageIcon(changeimg);

		imageLabel.setIcon(changefolder);
		p.add(imageLabel);
		imageLabel.setBounds(10, 10, 30, 30);

		Label l1 = new Label("��� ����");
		l1.setBounds(50, 10, 150, 30);
		p.add(l1);
		
		// Label �߰�
		Label[] label = new Label[3];
		String[] labelName = {"ǰ���ڵ�", "ǰ���", "�������"};

		for (int i = 0; i < label.length; i++) {
			label[i] = new Label(labelName[i]);
			p.add(label[i]);
		}
		
		label[0].setBounds(10, 50, 150, 30); // ǰ���ڵ�
		label[1].setBounds(10, 90, 150, 30); // ǰ���
		label[2].setBounds(10, 130, 150, 30); // �������

		// JDatePicker �߰�
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

		// TextField �߰�
		pctf = new TextField();
		pctf.setBounds(170, 50, 200, 30);
		p.add(pctf);

		pntf = new TextField();
		pntf.setBounds(170, 90, 200, 30);
		pntf.setEditable(false); // ���� �Ұ�
		p.add(pntf);

		// Button �߰�
		pcb = new Button("��");
		pcb.setBounds(380, 50, 30, 30);
		p.add(pcb);
		/*
		 * ��ư�� TextField ������ ������ �ִ��� üũ
		 */

		b1 = new Button("��ȸ");
		b1.setBounds(420, 50, 50, 30);
		b1.addActionListener(this);
		p.add(b1);

		b2 = new Button("���ó��");
		b2.setBounds(380, 130, 70, 30);
		b2.addActionListener(this);
		p.add(b2);

		// Table �߰�
		String[] header = { "", "����", "ǰ���ڵ�", "ǰ���", "����", "������", "������" };

		dtm = new DefaultTableModel(header, 0);
		table = new JTable(dtm);

		// �� �ʺ� ����
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

		// �� ���� ����
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
		case "��ȸ" :
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
					addRow[0] = false; // üũ�ڽ�
					addRow[1] = count++; // ����
					addRow[2] = result[i][0]; // ǰ���ڵ�
					addRow[3] = result[i][1]; // ǰ���
					addRow[4] = result[i][10]; // ����
					addRow[5] = result[i][11]; // ������
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
					addRow[0] = false; // üũ�ڽ�
					addRow[1] = count++; // ����
					addRow[2] = result[i][0]; // ǰ���ڵ�
					addRow[3] = result[i][1]; // ǰ���
					addRow[4] = result[i][10]; // ����
					addRow[5] = result[i][11]; // ������
					dtm.addRow(addRow);
				}
				table.getColumn("").setCellRenderer(dcr);
				
				JCheckBox box = new JCheckBox();
				box.setHorizontalAlignment(JLabel.CENTER);
				table.getColumn("").setCellEditor(new DefaultCellEditor(box));
				
				count = 1;
			}
			break;

		case "���ó��":
			int unstoring_num = 0;
			String unstoring_date = model.getYear() + "-" + (model.getMonth() + 1) + "-" + model.getDay();

			// ����ȣ ����
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

			// DB���̺� ����
			boolean b = true;

			// ������ üũ, ������ - ������ >= 0 üũ
			boolean checkUnstoringQuantityNull = true;
			boolean checkStockQuantity = true;

			for (int i = 0; i < dtm.getRowCount(); i++) {
				b = Boolean.valueOf(table.getValueAt(i, 0).toString()); // üũ���� Ȯ��
				if (b == true) {
					if (table.getValueAt(i, 6) == null || table.getValueAt(i, 6).toString().equals("")) {
						new ErrorMessageDialog("�������� �Է����ּ���.", "��� ���");
						checkUnstoringQuantityNull = false; // �������� ��ĭ�� ���� ������ �ٸ� �൵ ���ó�������ʵ��� ��
						break;
					}else {
						String product_code2 = table.getValueAt(i, 2).toString();
						vo = new Vo("stock", "quantity", "product_code", product_code2);
						int stock_quantity = Integer.parseInt(dao.selectOneFieldWhere(vo));
						int unstoring_quantity = Integer.parseInt(table.getValueAt(i, 6).toString());
						int updateStockQuantity = stock_quantity - unstoring_quantity;

						if (updateStockQuantity < 0) {
							new ErrorMessageDialog("(-)��� ��������ʴ� â���Դϴ�.", "��� ���");
							checkStockQuantity = false; // ���� ����� �������� (-)�� �Ǵ� ���� ������ ���ó�������ʵ��� ��
							break;
						}
					}
				}
			}
			
			// DB�� ����
			boolean tryInsertUnstoring = false;
			
			if (checkUnstoringQuantityNull == true && checkStockQuantity == true) { // üũ�� ��� ���� �������� ��ĭ�� ����, ������
				for (int i = 0; i < dtm.getRowCount(); i++) {
					b = Boolean.valueOf(table.getValueAt(i, 0).toString());
					if (b == true) {
						// ������ ����
						int unstoring_quantity = Integer.parseInt(table.getValueAt(i, 6).toString());
						
						// ���ó�� �� �������� ���Ѵ�
						String product_code2 = table.getValueAt(i, 2).toString();
						vo = new Vo("stock", "quantity", "product_code", product_code2);
						int stock_quantity = Integer.parseInt(dao.selectOneFieldWhere(vo));
						int updateStockQuantity = stock_quantity - unstoring_quantity;
						
						// ��� table�� insert
						String product_name = table.getValueAt(i, 3).toString();
						String unit = table.getValueAt(i, 4).toString();
						
						vo = new Vo(unstoring_num, unstoring_date, product_code2, product_name, unit,
								unstoring_quantity);
						tryInsertUnstoring = dao.insertUnstoring(vo);
						
						if (tryInsertUnstoring == true) {
							table.setValueAt(String.valueOf(updateStockQuantity), i, 5); // �������� ����� ������ ����
							table.setValueAt("", i, 6);// ������ ���� ���
						} else {
							String falseMessage = String.valueOf(i) + "���� ��� ó���� �����Ͽ����ϴ�.";
							new ErrorMessageDialog(falseMessage, "��� ���");
							break;
						}
						
						// ��� table�� update
						vo = new Vo("stock", "quantity", updateStockQuantity, "product_code", product_code2);
						dao.updateOneIntFieldWhere(vo);
					}
				} // for���� ��
			}

			if (tryInsertUnstoring == true) {
				new ErrorMessageDialog("��� ó���Ǿ����ϴ�.", "��� ���");
			}

			break;
		}
	}
}
