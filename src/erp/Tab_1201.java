package erp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;
import javax.swing.table.*;
import net.sourceforge.jdatepicker.impl.*;
import java.text.*;
import java.util.*;

public class Tab_1201 implements ActionListener, ItemListener {
//	���� �׸� : 40, 40
//	Label ������ : 150, 30
//	TextField ������ : 200, 30
//	���� ���� 30, ���� ���� 10
	
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
		JLabel imageLabel = new JLabel(); // ImageIcon �� ���� Label ����

		ImageIcon folder = new ImageIcon("src/images/folder-icon_34416.png");
		Image img = folder.getImage(); // image ũ�Ⱑ 512, 512�̹Ƿ� 40, 40���� �ٲٱ� ���� ImageIcon -> Image�� ����
		Image changeimg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon changefolder = new ImageIcon(changeimg);

		imageLabel.setIcon(changefolder);
		p.add(imageLabel);
		imageLabel.setBounds(10, 10, 30, 30);

		Label l1 = new Label("����offer ����");
		l1.setBounds(50, 10, 150, 30);
		p.add(l1);
		
		// Label �߰�
		Label[] label = new Label[5];
		String[] labelName = {"offer��ȣ", "����", "��ȭ", "�ŷ�ó", "��������"};
		
		for(int i=0; i<label.length; i++) {
			label[i] = new Label(labelName[i]);
			p.add(label[i]);
		}
		
		label[0].setBounds(10, 50, 150, 30);
		label[1].setBounds(10, 90, 150, 30);
		label[2].setBounds(10, 130, 150, 30);
		label[3].setBounds(380, 50, 150, 30);
		label[4].setBounds(380, 90, 150, 30);
		
		// TextField �߰�
		tf = new TextField();
		tf.setBounds(170, 50, 200, 30);
		p.add(tf);
		
		// JDatePicker �߰�
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
		
		// Choice �߰�
		ch = new Choice[3];
		
		for(int i=0; i<ch.length; i++) {
			ch[i] = new Choice();
			p.add(ch[i]);
			ch[i].addItemListener((ItemListener)this);
		}

		ch[0].setBounds(540, 50, 200, 30);
		ch[1].setBounds(540, 90, 200, 30);
		ch[2].setBounds(170, 130, 200, 30);
		
		// ch[0] : �ŷ�ó
		dao = new Dao();
		
		Vo clientVo = new Vo("client", "client_name");
		String[] clientList = dao.selectOneField(clientVo);
		for(int i=0; i<clientList.length; i++ ) {
			ch[0].add(clientList[i]);
		}
		
		// ch[1] : ��������
		String[] incoterms = {"CFR", "CIF", "CIP", "CPT", "DAP", "DPU", "DDP", "EXW", "FAS", "FCA", "FOB"};
		
		for(int i=0; i<11; i++) {
			ch[1].add(incoterms[i]);
		}
		
		// ch[2] : ��ȭ
		Vo currencyVo = new Vo("country", "currency");
		String[] currencyList = dao.selectOneFieldDistinct(currencyVo);
		for(int i=0; i<currencyList.length; i++) {
			ch[2].add(currencyList[i]);
		}
		
		// Button
		searchButton = new Button("��ȸ");
		searchButton.setBounds(750, 50, 50, 30);
		searchButton.addActionListener(this);
		p.add(searchButton);

		saveButton = new Button("����");
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
		
		
		// Table �߰�
		String[] header = {"����", "ǰ���ڵ�", "ǰ���", "����", "����", "�ܰ�", "�ݾ�"};
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
//		for(int i=0; i<ch.length; i++) {
//			System.out.println(ch[i].getSelectedItem());
//		}
//		System.out.println();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int rowCount = dtm.getRowCount(); // �� ������ ���ϴ� �޼���
		String tfText = tf.getText();
		
		switch(e.getActionCommand()) {
		
		case "��ȸ" :
			if(tfText == null) {
				new ErrorMessageDialog("offer��ȣ�� �Է��ϼ���.", "����offer ���");
			}else {
				Vo searchOffer = new Vo("offer", "offer_num", tfText);
				String[] result = dao.selectAllOfferWhere(searchOffer);
				if(result.length == 0) {
					new ErrorMessageDialog("��ϵ������� offer��ȣ�Դϴ�.", "����offer ���");
				}else {
					// offer���̺� ������ ���
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
					
					// offer_list���̺� ������ ���
					Vo searchOfferList = new Vo("offer_list", "offer_num", "1", "num");
					String[][] result2 = dao.selectAllOfferListWhere(searchOfferList);
					
					int deleteRowCount = rowCount; // ������ ������� ���� �߰��Ǿ��ִ� ��� ���� �����Ѵ�
					if(deleteRowCount == 0) {
						
					}else {
						while(deleteRowCount != 0){
							dtm.removeRow(deleteRowCount - 1);
							deleteRowCount--;
						}
					}
					
					for(int i=0; i<result2.length; i++) {
						String[] addRow = new String[7];
						for(int j=0; j<result2[i].length - 1; j++) { 
							addRow[j] = result2[i][j+1]; // 0������ offer_num ���� �����Ƿ� �������� 
						}
						dtm.addRow(addRow);
					}
					
				}
				// offer, offer_list ���̺� select
			}
			
			break;
		
		case "����" :
			// 7/13 �ۼ� �ڵ�
			String offer_num = tfText;
			String client_name = ch[0].getSelectedItem(); // �ŷ�ó
			String offer_date = model.getYear() + "-" + (model.getMonth() + 1) + "-" + model.getDay();
			String incoterms = ch[1].getSelectedItem(); // ��������
			String currency = ch[2].getSelectedItem(); // ��ȭ
			
			// offer ��ȣ, �ŷ�ó, ����, ��������, ��ȭ ��ĭ ���� üũ
			boolean checkNull = true;

			if(offer_num.equals("")) { // ���ڴ� ���� ��¥�� default���� ������ �־� ��ĭ�� ��찡 �����Ƿ� ���ǿ� ������
				new ErrorMessageDialog("offer��ȣ�� �Է��ϼ���.", "���� offer ���");
				checkNull = false;
			}else if(client_name.equals("")) {
				new ErrorMessageDialog("�ŷ�ó�� �Է��ϼ���.", "���� offer ���");
				checkNull = false;
			}else if(incoterms.equals("")) {
				new ErrorMessageDialog("���������� �Է��ϼ���.", "���� offer ���");
				checkNull = false;
			}else if(currency.equals("")) {
				new ErrorMessageDialog("��ȭ�� �Է��ϼ���.", "���� offer ���");
				checkNull = false;
			}
			
			// ��ĭ ������ -> �̵���̸� insert, �����̸� alter
			if(checkNull == true) {
				Vo checkOfferNum = new Vo("offer", "offer_num", "offer_num", tfText);
				String selectedOfferNum = dao.selectOneFieldWhere(checkOfferNum);
				if(selectedOfferNum.equals("")) {
					// �̵�� -> insert
					boolean b = true;
					Vo vo = new Vo(offer_num, client_name, offer_date, incoterms, currency);
					b = dao.insertOffer(vo);
					if(b == true) {
						new ErrorMessageDialog("����offer�� ��ϵǾ����ϴ�.", "����offer ���");
					}else{
						new ErrorMessageDialog("�̹� ��ϵ� offer��ȣ�Դϴ�.", "����offer ���");
					}
				}else {
					System.out.println("����");
					// ���� -> alter
				}
			}
			
			
			// 7/12 �ۼ� �ڵ�
//			if(rowCount == 0) { // table�� ���� �ϳ��� ������ -> offer���� ����
//				if(tfText.equals("")) {
//					new ErrorMessageDialog("offer��ȣ�� �Է��ϼ���.", "����offer ���");
//				}else {
////				offer table�� ������ ����
//					String offer_num = tfText;
//					String client_name = ch[0].getSelectedItem();
//					String offer_date = model.getYear() + "-" + (model.getMonth() + 1) + "-" + model.getDay();
//					String incoterms = ch[1].getSelectedItem();
//					String currency = ch[2].getSelectedItem();
//					
//					boolean b = true;
//					Vo vo = new Vo(offer_num, client_name, offer_date, incoterms, currency);
//					b = dao.insertOffer(vo);
//					if(b == true) {
//						new ErrorMessageDialog("����offer�� ��ϵǾ����ϴ�.", "����offer ���");
//					}else{
//						new ErrorMessageDialog("�̹� ��ϵ� offer��ȣ�Դϴ�.", "����offer ���");
//					}
//				}
//			}else { // table�� ���� �ϳ��� ������ -> offer������ ����
//			/*
//			 ù��° ����� �ƴ� �߰� ��� �Ǵ� �����϶� �����Ͱ� �����ǵ��� �ڵ� �ۼ� �ʿ�
//			 -> offer��ȣ ���� �о �̹� ��ϵǾ������� : num ������ ��ȣ�� üũ
//			 -> ��ϵǾ����������� -> ����
//			 
//			 insert ������ ���
//			 1) ǰ���ڵ� �̵��(�θ�Ű�� �����ϴ�)
//			 2) offer_num �̵��(�θ�Ű�� �����ϴ�)
//			 3) int Ÿ�� �����Ϳ� varchar2 Ÿ�� �����͸� �ִ� ���� Ÿ�Ժ���ġ(��ġ�� �������մϴ�)
//			 �� ��쿡 ���� �����ڵ带 ��ȯ�޾Ƽ� ��Ȳ�� ������ �����޽����� ����ϵ��� ���� �ʿ�
//			 */
//				boolean b2 = true;
//				String[] grv = new String[7];
//				for(int i=0; i<dtm.getRowCount(); i++) { // �� index : 0 ~ rowCount - 1����
//					for(int j=0; j<grv.length; j++) {
//						grv[j] = String.valueOf(dtm.getValueAt(i, j));
//					}
//					Vo offerListVo = new Vo(tfText, grv[0], grv[1], grv[2], grv[3], grv[4], grv[5], grv[6]); // �� ���� 8. ù��°�� OFFER_NUM, �������� TABLE �� �о����
//					b2 = dao.insertOfferList(offerListVo);
//					if(b2 == false) {
//						String message = "" + i + "���� ��ȣ �Ǵ� �Է� ������ �߸��Ǿ����ϴ�.";
//						new ErrorMessageDialog(message, "����offer ���");
//						break;
//					}
//				}
//				if(b2 == true) {
//					new ErrorMessageDialog("����Ǿ����ϴ�.", "����offer ���");
//				}
//			}
			break;

		case "+" :
			String[] addRow = {String.valueOf(rowCount + 1), "", "", "", "", "", ""};
			dtm.addRow(addRow);
			break;
			
		case "-" :
			/*
			 ���� Ŭ���� ���� �����͵� �����ǵ��� �ۼ� �ʿ�
			 */
			if(rowCount == 0) {
				new ErrorMessageDialog("������ ���� �����ϴ�.", "����offer ���");
			}else {
				DeleteConfirmDialog d = new DeleteConfirmDialog("�����Ͻðڽ��ϱ�?", "����offer ���");
				boolean b = d.response();
				if(b == true) {
					String deleteRow = String.valueOf(dtm.getValueAt(rowCount - 1, 0)); // ������ ���� ����(num)�� �о�´�
					dtm.removeRow(dtm.getRowCount() - 1);
					boolean b3 = true;
					Vo deleteVo = new Vo("offer_list", "num", deleteRow);
					b3 = dao.delete(deleteVo);
					if(b3 == true) {
						new ErrorMessageDialog("�����Ǿ����ϴ�.", "����offer ���");
					}
				}
			}
			break;
			
		case "��" :
			break;
			
		}
	}
}
