package erp;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
//import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

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
	DecimalFormat df = new DecimalFormat("###,###,###");
	
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

		// ���̺� �� �ʺ� �ڵ� ���� false
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		// �� �ʺ� ����
		TableColumn t0 = table.getColumnModel().getColumn(0);
		TableColumn t1 = table.getColumnModel().getColumn(1);
		TableColumn t2 = table.getColumnModel().getColumn(2);
		TableColumn t3 = table.getColumnModel().getColumn(3);
		TableColumn t4 = table.getColumnModel().getColumn(4);
		TableColumn t5 = table.getColumnModel().getColumn(5);
		TableColumn t6 = table.getColumnModel().getColumn(6);
		TableColumn t7 = table.getColumnModel().getColumn(7);
		TableColumn t8 = table.getColumnModel().getColumn(8);
		TableColumn t9 = table.getColumnModel().getColumn(9);
		TableColumn t10 = table.getColumnModel().getColumn(9);
		TableColumn t11 = table.getColumnModel().getColumn(9);
		TableColumn t12 = table.getColumnModel().getColumn(9);
		TableColumn t13 = table.getColumnModel().getColumn(9);
		TableColumn t14 = table.getColumnModel().getColumn(9);
		TableColumn t15 = table.getColumnModel().getColumn(9);

		t0.setPreferredWidth(50);
		t1.setPreferredWidth(150);
		t2.setPreferredWidth(300);
		t3.setPreferredWidth(50);
		t4.setPreferredWidth(80);
		t5.setPreferredWidth(80);
		t6.setPreferredWidth(100);
		t7.setPreferredWidth(80);
		t8.setPreferredWidth(80);
		t9.setPreferredWidth(100);
		t10.setPreferredWidth(80);
		t11.setPreferredWidth(80);
		t12.setPreferredWidth(100);
		t13.setPreferredWidth(80);
		t14.setPreferredWidth(80);
		t15.setPreferredWidth(100);

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
		String[][] result = getStoringAndUnStoringQuantity();
		String selectedDate = chYear.getSelectedItem() + "-" + chMonth.getSelectedItem(); // 2022-7
		
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
			String s = getProductLIst();
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
			String year_month = ""; // ���س��
			String product_code = ""; // ǰ���ڵ�
			String product_name = ""; // ǰ���
			String unit = ""; // ����
			int beginning_unit_price = 0; // ���ʴܰ�
			int beginning_quantity = 0; // ���ʼ���
			int beginning_amount = 0; // ���ʱݾ�
			int storing_unit_price = 0;
			int storing_quantity = 0;
			int storing_amount = 0;
			int unstoring_unit_price = 0; // ���ܰ�
			int unstoring_quantity = 0; // ������
			int unstoring_amount = 0; // ���ݾ�
			int inventory_unit_price;
			int inventory_quantity = 0;
			int inventory_amount = 0;
			
			String dfs = df.format(0);
			
			// PRODUCT_CODE_COST : year_month, product_code, product_name, unit
//			String[][] pccString = new String[result.length][4]; -> result �迭�� ���� ��Ȱ��
			
			int[][] pccInt = new int[result.length][12]; // PRODUCT_CODE_COST�� insert�Ǵ� 12�� row
			
			if(rowCount == 0) {
				new ErrorMessageDialog("���س�� �Է� �� ������ ��ȸ���ּ���.", "���� ���");
			}else {
				// addRow[4] = getBeginningQuantity(lastMonth, addRow[1].toString()); // ���ʼ���
				// addRow[7] = result[i][3]; // �԰����
				// addRow[10] = result[i][6]; // ������
				// addRow[13] = (Integer.parseInt(addRow[4].toString()) + Integer.parseInt(addRow[7].toString()) - Integer.parseInt(addRow[10].toString())); // ������
				
				String s2 = getProductLIst();
				
				for(int i=0; i<result.length; i++) {
					// ���ʱݾ� = ������ ���ݾ�
					String ba;
					String lastMonth = getLastMonth();
					vo = new Vo("product_code_cost", "inventory_amount", "year_month", lastMonth, "product_code", result[i][0]);
					ba = dao.selectOneFieldWhereTwoFields(vo);
					
					// ���ʼ���, �ܰ�, �ݾ�
					if(ba == null || ba.equals("")) { // ���� ���ݾ��� ������
						beginning_unit_price = 0; // ���ʴܰ�
						beginning_quantity = 0; // ���ʼ���
						beginning_amount = 0; // ���ʱݾ�
					}else { // ���� ���ݾ��� ������
						beginning_amount = Integer.parseInt(ba);
						beginning_quantity = getBeginningQuantity(lastMonth, result[i][0]);
						beginning_unit_price = beginning_amount / beginning_quantity; // ���ʴܰ� = ���� ���ݾ� / ���� ������
					}
					
					pccInt[i][0] = beginning_quantity;
					pccInt[i][1] = beginning_unit_price;
					pccInt[i][2] = beginning_amount;					
					
					dfs = df.format(beginning_quantity);
					dtm.setValueAt(dfs, i, 4);
					
					dfs = df.format(beginning_unit_price);
					dtm.setValueAt(dfs, i, 5);

					dfs = df.format(beginning_amount);
					dtm.setValueAt(dfs, i, 6);
					
					
					// �԰����, �ܰ�, �ݾ�
					char[] chArr = selectedDate.toCharArray(); // 2,0,2,2,-,0,7
					String s3 = String.valueOf(chArr[2]) + String.valueOf(chArr[3]) + "-" + chMonth.getSelectedItem(); // 22-7
					
					vo = new Vo("offer_list", "quantity", "offer_num", s3, "product_code", result[i][0]);
					String sq = dao.sumWhereTwoFieldsLike(vo);
					
					if(sq == null || sq.equals("")) { // �԰���� 0�ϋ�
						storing_quantity = 0;
						storing_unit_price = 0;
						storing_amount = 0;
					}else { // �԰���� 0�� �ƴҶ�
						storing_quantity = Integer.parseInt(sq);
						vo = new Vo("offer_list", "quantity", "unit_price_krw", "offer_num", s3, "product_code", result[i][0]);
						String[][] result2 = dao.selectTwoFieldsWhereTwoFieldsLike(vo);
						
						for(int j=0; j<result2.length; j++) {
							int m = Integer.parseInt(result2[j][0]) * Integer.parseInt(result2[j][1]);
							storing_amount += m;
						}
						storing_unit_price = storing_amount / storing_quantity;
					}
					pccInt[i][3] = storing_quantity;
					pccInt[i][4] = storing_unit_price;
					pccInt[i][5] = storing_amount;

					dfs = df.format(storing_quantity);
					dtm.setValueAt(dfs, i, 7);

					dfs = df.format(storing_unit_price);
					dtm.setValueAt(dfs, i, 8);

					dfs = df.format(storing_amount);
					dtm.setValueAt(dfs, i, 9);
					
					
					// ������, �ܰ�, �ݾ�
					vo = new Vo("unstoring", "quantity", "unstoring_date", selectedDate, "product_code", result[i][0]);
					String usq = dao.sumWhereTwoFieldsLike(vo);
					
					if(usq == null || usq.equals("")) { // ������ 0�ϋ�
						unstoring_quantity = 0;
						unstoring_unit_price = 0;
						unstoring_amount = 0;
					}else { // ������ 0�� �ƴҶ�
						unstoring_quantity = Integer.parseInt(usq);
						unstoring_unit_price = (beginning_amount + storing_amount) / (beginning_quantity + storing_quantity);
						unstoring_amount = unstoring_quantity * unstoring_unit_price;
					}
					pccInt[i][6] = unstoring_quantity;
					pccInt[i][7] = unstoring_unit_price;
					pccInt[i][8] = unstoring_amount;

					dfs = df.format(unstoring_quantity);
					dtm.setValueAt(dfs, i, 10);

					dfs = df.format(unstoring_unit_price);
					dtm.setValueAt(dfs, i, 11);
					
					dfs = df.format(unstoring_amount);
					dtm.setValueAt(dfs, i, 12);
					
					// ������, �ܰ�, �ݾ�
					inventory_quantity = beginning_quantity + storing_quantity - unstoring_quantity;
					if(inventory_quantity == 0) {
						inventory_unit_price = 0;
						inventory_amount = 0;
					}else {
						inventory_amount = beginning_amount + storing_amount - unstoring_amount;
						inventory_unit_price = inventory_amount / inventory_quantity;
					}
					pccInt[i][9] = inventory_quantity;
					pccInt[i][10] = inventory_unit_price;
					pccInt[i][11] = inventory_amount;

					dfs = df.format(inventory_quantity);
					dtm.setValueAt(dfs, i, 13);

					dfs = df.format(inventory_unit_price);
					dtm.setValueAt(dfs, i, 14);

					dfs = df.format(inventory_amount);
					dtm.setValueAt(dfs, i, 15);
					
					storing_amount=0; // �� ������ ���� ���ؼ� �հ踦 ���ϹǷ�, ���� ǰ���ڵ��� �հ踦 ���� 0���� �ʱ�ȭ
					
				} // for���� ��

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
