package tabTest;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;

public class Tab_1202 implements ActionListener {
//	���� �׸� : 40, 40
//	Label ������ : 150, 30
//	TextField ������ : 200, 30
//	Button ������ : 50, 30
//	���� ���� 30, ���� ���� 10
	JPanel p;
	TextField ontf;
	Label[] label;
	Button b1, b2;
	TextField[] tf;
	DefaultTableModel dtm;
	Dao dao = new Dao();
	Vo vo;
	
	public Tab_1202() {
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

		Label l1 = new Label("���Կ��� ����");
		l1.setBounds(50, 10, 150, 30);
		p.add(l1);
		
		// Label �� TextField - offer��ȣ
		Label onl = new Label("Offer��ȣ");
		onl.setBounds(10, 50, 150, 30);
		p.add(onl);
		
		ontf = new TextField();
		ontf.setBounds(170, 50, 200, 30);
		p.add(ontf);
		
		// Label �߰�
		label = new Label[6];
		String[] labelName = {
				"�۱�ȯ��", "�����(KRW)", "��Ÿ���(KRW)",
				"�۱ݼ�����(KRW)", "��ݺ�(KRW)", "�� �ݾ�(KRW)"
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
		
		// TextField �߰�
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
		
		// Button �߰�
		b1 = new Button("��ȸ");
		b1.addActionListener(this);
		b1.setBounds(380, 50, 50, 30);
		p.add(b1);

		b2 = new Button("���");
		b2.addActionListener(this);
		b2.setBounds(750, 110, 50, 30);
		p.add(b2);
		
		// Table �߰�
		String[] header = {
				"����", "�ŷ�ó", "ǰ���ڵ�", "ǰ���", "����",
				"����", "�ܰ�", "�ݾ�", "�ܰ�(KRW)", "�ݾ�(KRW)"
		};
		dtm = new DefaultTableModel(header, 0);		
		JTable table = new JTable(dtm);
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(10, 230, 1000, 450);
		p.add(sp);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int rowCount = dtm.getRowCount(); // ���� ���̺��� �� ����
		String tfText = ontf.getText();
		switch(e.getActionCommand()) {
		case "��ȸ" :
			if(tfText == null) {
				new ErrorMessageDialog("offer��ȣ�� �Է��ϼ���.", "���Կ��� ���");
			}else {
				// ���̺� ��� : 0 ����, 1 �ŷ�ó, 2 ǰ���ڵ�, 3 ǰ���, 4 ����, 5 ����, 6 �ܰ�, 7 �ݾ�
				vo = new Vo("offer", "offer_num", tfText);
				String[] result = dao.selectAllOfferWhere(vo);
				if(result.length == 0) {
					new ErrorMessageDialog("��ϵ������� offer��ȣ�Դϴ�.", "���Կ��� ���");
				}else {
					// 0 ���� : offer_list���̺��� �ι�° �ʵ�(num)
					vo = new Vo("offer_list", "num", "offer_num", tfText, "num");
					String[] selectResult1 = dao.selectOneFieldWhereOrderBy(vo);
					
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
					
					// 1 �ŷ�ó : offer���̺��� �ι�° �ʵ�(client_name)
					// SELECT client_name FROM OFFER WHERE OFFER_NUM = '22-7-2'
					vo = new Vo("offer", "client_name", "offer_num", tfText);
					String selectResult2 = dao.selectOneFieldWhere(vo);
					
					for(int i=0; i<selectResult1.length; i++) {
						dtm.setValueAt(selectResult2, i, 1);
					}
					
					// 2 ǰ���ڵ� : offer_list���̺��� ����° �ʵ�(product_code)
					// 3 ǰ��� : offer_list���̺��� �׹�° �ʵ�(product_name)
					// 4 ���� : offer_list���̺��� �ټ���° �ʵ�(unit)
					// 5 ���� : offer_list���̺��� ������° �ʵ�(quantity)
					// 6 �ܰ� : offer_list���̺��� �ϰ���° �ʵ�(unit_price)
					// 7 �ݾ� : offer_list���̺��� ������° �ʵ�(amount)
					
					vo = new Vo("offer_list", "offer_num", tfText, "num");
					String[][] selectResult3 = dao.selectAllOfferListWhere(vo);
					
					for(int i=0; i<selectResult3.length; i++) {
						String[] addRow = new String[6];
						for(int j=0; j<selectResult3[i].length - 3; j++) {
							addRow[j] = selectResult3[i][j+2];
							dtm.setValueAt(addRow[j], i, j+2);
						}
					}
				}
			} // else���� ��
			
			// �̹� ������ ��ϵǾ��ִٸ� ��ϵ� ������ ���̺� ���
			vo = new Vo("offer_cost", "offer_num", "offer_num", tfText);
			String checkCost = dao.selectOneFieldWhere(vo);
			//SELECT OFFER_NUM FROM offer_cost WHERE OFFER_NUM = '22-7-3'
			if(checkCost.equals("")) {
				
			}else {
				// tf[0] ~ tf[5]�� offer_cost ���̺� ����� ������ set
				vo = new Vo("offer_cost", "offer_num", tfText);
				String[] offerCost = dao.selectAllOfferCostWhere(vo); // 0 : offer_num, 1 : �۱�ȯ��, 2 : �۱ݼ�����, 3 : �����, 4 : ��ݺ�, 5 : ��Ÿ���, 6 : �� �ݾ�

				tf[0].setText(offerCost[1]); // �۱�ȯ��
				tf[1].setText(offerCost[3]); // �����
				tf[2].setText(offerCost[5]); // ��Ÿ���
				tf[3].setText(offerCost[2]); // �۱ݼ�����
				tf[4].setText(offerCost[4]); // ��ݺ�
				tf[5].setText(offerCost[6]); // �� �ݾ�
				
				// ���̺� (n, 8)�� offer_list ���̺� ����� ������ set
				vo = new Vo("offer_list", "unit_price_krw", "offer_num", tfText, "num");
				String[] unitPriceKrw = dao.selectOneFieldWhereOrderBy(vo);
				
				for(int i=0; i<unitPriceKrw.length; i++) {
					dtm.setValueAt(unitPriceKrw[i], i, 8);
				}
				
				// (n, 9)�� �ܰ� * ���� ��
				vo = new Vo("offer_list", "quantity", "offer_num", tfText, "num");
				String[] quantities = dao.selectOneFieldWhereOrderBy(vo); // ����
				
				for(int i=0; i<quantities.length; i++) {
					dtm.setValueAt(Integer.parseInt(unitPriceKrw[i]) * Integer.parseInt(quantities[i]), i, 9);
				}
				
			}
			break;
			
		case "���" :
			if(tf[0].getText().equals("")) {
				new ErrorMessageDialog("�۱�ȯ���� �ʼ� �Է°��Դϴ�.", "���Կ��� ���");
			}else {
				// �ѱݾ��� ������ ��� TextField�� �Է°��� �� ������ ����
				String s = "0"; // NullPointerException�� ó���ϱ� ���� String s�� ���� ����
				int currency_exchange = 0; // �۱�ȯ��
				int remittance_charge = 0; // �۱ݼ�����
				int custom_clearance_fee = 0; // �����
				int freight_charge = 0; // ��ݺ�
				int other_cost = 0; // ��Ÿ���
				
				currency_exchange = Integer.parseInt(tf[0].getText()); // �۱�ȯ��
				
				try {
					s = tf[3].getText();
				}catch(NullPointerException e1) {
					s = "0";
				}finally {
					remittance_charge = Integer.parseInt(s); // �۱ݼ�����
				}
				
				try {
					s = tf[1].getText();
				}catch(NullPointerException e1) {
					s = "0";
				}finally {
					custom_clearance_fee = Integer.parseInt(s); // �����
				}
				
				try {
					s = tf[4].getText();
				}catch(NullPointerException e1) {
					s = "0";
				}finally {
					freight_charge = Integer.parseInt(s); // ��ݺ�
				}
				
				try {
					s = tf[2].getText();
				}catch(NullPointerException e1) {
					s = "0";
				}finally {
					other_cost = Integer.parseInt(s); // ��Ÿ���
				}
				
				// 1. �ݾ�(USD) ���ϱ�
				// : SELECT SUM(amount) FROM offer_list WHERE offer_num = 'tfTest'
				vo = new Vo("offer_list", "amount", "offer_num", tfText);
				int amount = dao.sumWhere(vo);

				// 2. �ѱݾ�(KRW)�� ������ TextField�� ���
				// : �ѱݾ�(KRW) = �ݾ�(USD) * �۱�ȯ�� + �۱ݼ����� + ����� + ��ݺ� + ��Ÿ���
				int amount_krw = amount * currency_exchange + remittance_charge + custom_clearance_fee + freight_charge + other_cost;
				tf[5].setText(String.valueOf(amount_krw));

				// 3. offer_cost ���̺� �۱�ȯ�� ~ �ѱݾױ��� 6���� �� ����
				vo = new Vo("offer_cost", "offer_num", "offer_num", tfText);
				String checkOfferCost = dao.selectOneFieldWhere(vo); // offer_cost�� ������ offeer_num���� �̹� ����� ���� �ִ��� Ȯ��

				if(checkOfferCost.equals("")) { // ������ insert
					vo = new Vo(tfText, currency_exchange, remittance_charge, custom_clearance_fee, freight_charge, other_cost, amount_krw);
					dao.insertOfferCost(vo);
				}else { // ������ update
					vo = new Vo("offer_cost",
							"offer_num", tfText, "currency_exchange", currency_exchange,
							"remittance_charge",  remittance_charge, "custom_clearance_fee", custom_clearance_fee,
							"freight_charge", freight_charge, "other_cost", other_cost,
							"amount_krw", amount_krw);
					dao.updateSevenIntFieldWhere(vo);
				}
				
				// 4. �׸� ����, �ܰ� ���ؼ� �� �迭�� ������ �� ������ ���Ѵ�
				// : offer_list ���̺��� offer_num�� ontf.getText()�� ��ġ�ϴ� ������ quantity�� ���� �� ����
				vo = new Vo("offer_list", "quantity", "offer_num", tfText, "num");
				String[] quantities = dao.selectOneFieldWhereOrderBy(vo); // ����
				
				vo = new Vo("offer_list", "unit_price", "offer_num", tfText, "num");
				String[] unit_price_arr = dao.selectOneFieldWhereOrderBy(vo); // �ܰ�
				
				int quantity_sum = 0; // �Ѽ���
				for(int i=0; i<quantities.length; i++) {
					quantity_sum += Integer.parseInt(quantities[i]);
				}

				// 5. �ܰ�(KRW)�� ���Ͽ� ���̺�(n, 8)�� ���, offer_list�� 9��(unit_price_krw)�� ����, �ܰ�(KRW) * ������ ���̺�(n, 9)�� ���
				// : �ܰ�(KRW) = �ܰ� * �۱�ȯ�� + (�۱ݼ����� + ����� + ��ݺ� + ��Ÿ���) / �� ����
				int unit_price_krw;
				boolean tryUpdateUnitPriceKrw = false;
				for(int i=0; i<rowCount; i++) {		
					// �ܰ�(KRW) ���
					unit_price_krw = Integer.parseInt(unit_price_arr[i]) * currency_exchange
							+ (remittance_charge + custom_clearance_fee + freight_charge + other_cost) / quantity_sum;
					// ���̺� �ܰ�(KRW) ���
					dtm.setValueAt(unit_price_krw, i, 8);
					// offer_list�� �ܰ�(KRW) ����
					vo = new Vo("offer_list", "unit_price_krw", unit_price_krw, "num", String.valueOf(i+1));
					tryUpdateUnitPriceKrw = dao.updateOneIntFieldWhere(vo);
					// ���̺� �ܰ�(KRW) * ���� ���
					dtm.setValueAt(unit_price_krw * Integer.parseInt(quantities[i]), i, 9);
				}
				
				// ��� ���
				if(tryUpdateUnitPriceKrw == true) {
					new ErrorMessageDialog("���� ������ ��ϵǾ����ϴ�.", "���Կ��� ���");
				}else {
					new ErrorMessageDialog("���� ���� ��Ͽ� �����Ͽ����ϴ�.", "���Կ��� ���");
				}
				
			}
			break;
		}
	}
}
