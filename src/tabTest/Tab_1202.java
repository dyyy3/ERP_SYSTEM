package tabTest;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.*;
//import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

public class Tab_1202 implements ActionListener {
//	���� �׸� : 40, 40
//	Label ������ : 150, 30
//	TextField ������ : 200, 30
//	Button ������ : 50, 30
//	���� ���� 30, ���� ���� 10
	JPanel p;
	TextField ontf;
	Label[] label;
	TextField[] tf;
	DefaultTableModel dtm;
	Dao dao = new Dao();
	
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
		Button b1 = new Button("��ȸ");
		b1.addActionListener(this);
		b1.setBounds(380, 50, 50, 30);
		p.add(b1);

		Button b2 = new Button("���");
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
		
		switch(e.getActionCommand()) {
		case "��ȸ" :
			String tfText = ontf.getText();
			if(tfText == null) {
				new ErrorMessageDialog("offer��ȣ�� �Է��ϼ���.", "���Կ��� ���");
			}else {
				// ���̺� ��� : 0 ����, 1 �ŷ�ó, 2 ǰ���ڵ�, 3 ǰ���, 4 ����, 5 ����, 6 �ܰ�, 7 �ݾ�
				Vo searchOffer = new Vo("offer", "offer_num", tfText);
				String[] result = dao.selectAllOfferWhere(searchOffer);
				if(result.length == 0) {
					new ErrorMessageDialog("��ϵ������� offer��ȣ�Դϴ�.", "���Կ��� ���");
				}else {
					// 0 ���� : offer_list���̺��� �ι�° �ʵ�(num)
					Vo selectIndex0 = new Vo("offer_list", "num", "offer_num", tfText, "num");
					String[] selectResult1 = dao.selectOneFieldWhereOrderBy(selectIndex0);
					
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
					Vo selectIndex1 = new Vo("offer", "client_name", "offer_num", tfText);
					String selectResult2 = dao.selectOneFieldWhere(selectIndex1);
					
					for(int i=0; i<selectResult1.length; i++) {
						dtm.setValueAt(selectResult2, i, 1);
					}
					
					// 2 ǰ���ڵ� : offer_list���̺��� ����° �ʵ�(product_code)
					// 3 ǰ��� : offer_list���̺��� �׹�° �ʵ�(product_name)
					// 4 ���� : offer_list���̺��� �ټ���° �ʵ�(unit)
					// 5 ���� : offer_list���̺��� ������° �ʵ�(quantity)
					// 6 �ܰ� : offer_list���̺��� �ϰ���° �ʵ�(unit_price)
					// 7 �ݾ� : offer_list���̺��� ������° �ʵ�(amount)
					
					Vo selectIndex2 = new Vo("offer_list", "offer_num", tfText, "num");
					String[][] selectResult3 = dao.selectAllOfferListWhere(selectIndex2);
					
					for(int i=0; i<selectResult3.length; i++) {
						String[] addRow = new String[6];
						for(int j=0; j<selectResult3[i].length - 2; j++) {
							addRow[j] = selectResult3[i][j+2];
							dtm.setValueAt(addRow[j], i, j+2);
						}
					}
				}
			}
			
			break;
			
		case "���" :
			if(tf[0].getText().equals("")) {
				new ErrorMessageDialog("�۱�ȯ���� �ʼ� �Է°��Դϴ�.", "���Կ��� ���");
			}else {
				// textField�� ��簪�� �о�´�
				int currency_exchange = Integer.parseInt(tf[0].getText());
				int remittance_charge;
				int custom_clearance_fee;
				int freight_charge;
				int other_cost;
				int amount_krw;

//				tf[0].setBounds(170, 110, 200, 30);
//				tf[1].setBounds(170, 150, 200, 30);
//				tf[2].setBounds(170, 190, 200, 30);
//				tf[3].setBounds(540, 110, 200, 30);
//				tf[4].setBounds(540, 150, 200, 30);
//				tf[5].setBounds(540, 190, 200, 30);
				
			}
			break;
		}
	}
}
