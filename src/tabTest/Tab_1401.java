package tabTest;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
//import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

public class Tab_1401 implements ActionListener, ItemListener {
//	���� �׸�:40,40
//	Label ������:150,30
//	TextField ������:200,30
//	Button ������:50,30
//	���� ���� 30, ���� ���� 10
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
		JLabel imageLabel = new JLabel(); // ImageIcon �� ���� Label ����

		ImageIcon folder = new ImageIcon("src/images/folder-icon_34416.png");
		Image img = folder.getImage(); // image ũ�Ⱑ 512, 512�̹Ƿ� 40, 40���� �ٲٱ� ���� ImageIcon -> Image�� ����
		Image changeimg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon changefolder = new ImageIcon(changeimg);

		imageLabel.setIcon(changefolder);
		p.add(imageLabel);
		imageLabel.setBounds(10, 10, 30, 30);

		Label l1 = new Label("��� ��Ȳ");
		l1.setBounds(50, 10, 150, 30);
		p.add(l1);
		
		// Label �߰�
		Label label = new Label("���س��");
		label.setBounds(10, 50, 150, 30);
		p.add(label);
		
		// Choice �߰�
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

		// Button �߰�
		b1 = new Button("��ȸ");
		b1.setBounds(380, 50, 50, 30);
		b1.addActionListener(this);
		p.add(b1);

		b2 = new Button("���� ���");
		b2.setBounds(10, 90, 80, 30);
		b2.addActionListener(this);
		p.add(b2);
		
		// Table �߰�
		String[] header = {
				"����", "ǰ���ڵ�", "ǰ���", "����", "���� ����",
				"���� �ܰ�", "���� �ݾ�", "�԰� ����", "�԰� �ܰ�", "�԰� �ݾ�",
				"��� ����", "��� �ܰ�", "��� �ݾ�", "��� ����", "��� �ܰ�",
				"��� �ݾ�"
		};
		dtm = new DefaultTableModel(header, 0);
		table = new JTable(dtm);
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(0, 130, 1160, 500);
		p.add(sp);
	}
	
	public String getProductLIst(){
		// storing, unstoring ���̺� �� ǰ���ڵ� ���� �� �������� ǰ���ڵ� ����Ʈ�� �������� ���Ѵ�
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
		// storing, unstoring �� ���̺��� ǰ���ڵ庰 ���� �հ踦 ���� ����� full outer join ����� 2���� �迭�� ����
		String year_month = chYear.getSelectedItem() + "-" + chMonth.getSelectedItem();
		
		vo = new Vo(year_month);
		String[][] result = dao.selectAllStroingAndUnStoringFullOuterJoin(vo);

		return result;
	}
	
	
	public String getLastMonth() {
		String lastMonth;

		// ���� ���õ� ���س�� - 1�� ������ ���Ѵ�
		int bqYear = Integer.parseInt(chYear.getSelectedItem());
		int bqMonth = Integer.parseInt(chMonth.getSelectedItem());

		if (bqMonth == 1) { // ���� 1���϶�
			bqYear = bqYear - 1;
			bqMonth = 12;
		} else { // ���� 2~12���϶�
			bqMonth = bqMonth - 1;
		}
		lastMonth = String.valueOf(bqYear) + "-" + String.valueOf(bqMonth);
		
		return lastMonth;
	}
	
	public int getBeginningQuantity(String lastMonth, String product_code) { // ���ʼ��� = ���� ������, ���� ���� �������� ���ٸ� 0
		int returnValue = 0; // ��ȯ�� ��
					
		// product_code_cost ���̺��� ���ʼ���(���� ������)�� ���Ѵ�
		String getBeginningQuantity; // ���ʼ���
		vo = new Vo("product_code_cost", "inventory_quantity", "year_month", lastMonth, "product_code", product_code);
		getBeginningQuantity = dao.selectOneFieldWhereTwoFields(vo);
					
		if(getBeginningQuantity == null || getBeginningQuantity.equals("")) { // ���� �������� ������
			returnValue = 0;
		}else { // ���� �������� ������
			returnValue = Integer.parseInt(getBeginningQuantity);
		}
		
		return returnValue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int rowCount = dtm.getRowCount();
		
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
			
//			// storing, unstoring ���̺� �� ǰ���ڵ� ���� �� �������� ǰ���ڵ� ����Ʈ�� �������� ���Ѵ�
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
//			// storing, unstoring �� ���̺��� ǰ���ڵ庰 ���� �հ踦 ���� ����� full outer join ����� 2���� �迭�� ����
//			String year_month = chYear.getSelectedItem() + "-" + chMonth.getSelectedItem();
//			int count = 1;
//			
//			vo = new Vo(year_month);
//			String[][] result = dao.selectAllStroingAndUnStoringFullOuterJoin(vo);
			
			String s = getProductLIst();
			String[][] result = getStoringAndUnStoringQuantity();
			int count = 1;

			// ���̺� ���
			for(int i=0; i<result.length; i++) {
				Object[] addRow = new Object[16];
				addRow[0] = count++; // ����
				addRow[1] = result[i][0]; // ǰ���ڵ�
				String lastMonth = getLastMonth();
				addRow[4] = getBeginningQuantity(lastMonth, addRow[1].toString()); // ���ʼ���
				if(result[i][3] == null || result[i][3].equals("")) {
					addRow[7] = "0";
				}else {
					addRow[7] = result[i][3]; // �԰����
				}
				if(result[i][6] == null || result[i][6].equals("")) {
					addRow[10] = "0";
				}else {
					addRow[10] = result[i][6]; // ������
				}
				addRow[13] = (Integer.parseInt(addRow[4].toString()) + Integer.parseInt(addRow[7].toString()) - Integer.parseInt(addRow[10].toString())); // ������
				if(s.equals("s")) {
					addRow[2] = result[i][1]; // ǰ���
					addRow[3] = result[i][2]; // ����
				}else if(s.equals("o")) {
					addRow[2] = result[i][4]; // ǰ���
					addRow[3] = result[i][5]; // ����
				}
				dtm.addRow(addRow);
			}
			count = 1;
			break;
			
		case "���� ���" :
			if(rowCount == 0) {
				new ErrorMessageDialog("���س�� �Է� �� ������ ��ȸ���ּ���.", "���� ���");
			}else {
				// addRow[4] = getBeginningQuantity(lastMonth, addRow[1].toString()); // ���ʼ���
				// addRow[7] = result[i][3]; // �԰����
				// addRow[10] = result[i][6]; // ������
				// addRow[13] = (Integer.parseInt(addRow[4].toString()) + Integer.parseInt(addRow[7].toString()) - Integer.parseInt(addRow[10].toString())); // ������
				
				String s2 = getProductLIst();
				String[][] result2 = getStoringAndUnStoringQuantity();
				
				for(int i=0; i<result2.length; i++) {
					int unitPrice = 0; // �ܰ�
					int amount = 0; // �ݾ�

					// ���ʱݾ� = ������ ���ݾ�
					String beginning_amount;
					String lastMonth = getLastMonth();
					vo = new Vo("product_code_cost", "inventory_amount", "year_month", lastMonth, "product_code", result2[i][0]);
					beginning_amount = dao.selectOneFieldWhereTwoFields(vo);
					
					if(beginning_amount == null || beginning_amount.equals("")) { // ���� ���ݾ��� ������
						amount = 0;
					}else { // ���� ���ݾ��� ������
						amount = Integer.parseInt(beginning_amount);
					}
					dtm.setValueAt(amount, i, 6);
					
					// ���ʴܰ�
					if(amount == 0) {
						unitPrice = 0; // ���ʴܰ� = 0
					}else {
						unitPrice = amount / getBeginningQuantity(lastMonth, result2[i][0]); // ���ʴܰ� = ���� ���ݾ� / ���� ������
					}
					dtm.setValueAt(unitPrice, i, 5);
					
					// �԰�ݾ�
					
					
				}
				
//				int returnValue = 0; // ��ȯ�� ��
//				
//				// product_code_cost ���̺��� ���ʼ���(���� ������)�� ���Ѵ�
//				String getBeginningQuantity; // ���ʼ���
//				vo = new Vo("product_code_cost", "inventory_quantity", "year_month", lastMonth, "product_code", product_code);
//				getBeginningQuantity = dao.selectOneFieldWhereTwoFields(vo);
//							
//				if(getBeginningQuantity == null || getBeginningQuantity.equals("")) { // ���� �������� ������
//					returnValue = 0;
//				}else { // ���� �������� ������
//					returnValue = Integer.parseInt(getBeginningQuantity);
//				}
				
				
				// ���̺� setValueAt
				// product_code_cost ���̺� �� ����
			}
			break;
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
}
