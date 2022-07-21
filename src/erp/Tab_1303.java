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
//	���� �׸�:40,40
//	Label ������:150,30
//	TextField ������:200,30
//	Button ������:50,30
//	���� ���� 30, ���� ���� 10
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
		Label[] label = new Label[2];
		String[] labelName = {"ǰ���ڵ�", "ǰ���"};

		for (int i = 0; i < label.length; i++) {
			label[i] = new Label(labelName[i]);
			p.add(label[i]);
		}
		
		label[0].setBounds(10, 50, 150, 30);
		label[1].setBounds(10, 90, 150, 30);
		
		// TextField �߰�
		pctf = new TextField();
		pctf.setBounds(170, 50, 200, 30);
		p.add(pctf);

		pntf = new TextField();
		pntf.setBounds(170, 90, 200, 30);
		pntf.setEditable(false); // ���� �Ұ�
		p.add(pntf);
		
		// Button �߰�
		Button pcb = new Button("��");
		pcb.setBounds(380, 50, 30, 30);
		p.add(pcb);
		/*
		��ư�� TextField ������ ������ �ִ��� üũ
		*/
		
		Button b1 = new Button("��ȸ");
		b1.setBounds(440, 50, 50, 30);
		b1.addActionListener(this);
		p.add(b1);

		// Table �߰�
		String[] header = {"����", "ǰ���ڵ�", "ǰ���", "����", "��� ����"};
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
			
			String[][] result;
			
			if(pctf.getText() == null || pctf.getText().equals("")) { // ��� ǰ�� ��ȸ
				vo = new Vo("s.PRODUCT_CODE", "pl.PRODUCT_NAME", "s.UNIT", "s.QUANTITY", "PRODUCT_NAME");
				result = dao.selectFourFieldsProductListAndStockJoinWhere(vo);
				
			}else { // �Էµ� ǰ�� ��ȸ
				String product_code = pctf.getText();
				vo = new Vo("s.PRODUCT_CODE", "pl.PRODUCT_NAME", "s.UNIT", "s.QUANTITY", "s.PRODUCT_CODE", product_code, "PRODUCT_NAME");
				result = dao.selectFourFieldsProductListAndStockJoinWhereTwoFields(vo);
			}
			
			for(int i=0; i<result.length; i++) {
				Object[] addRow = new Object[7];
				addRow[0] = count++; // ����
				addRow[1] = result[i][0]; // ǰ���ڵ�
				addRow[2] = result[i][1]; // ǰ���
				addRow[3] = result[i][2]; // ����
				addRow[4] = result[i][3]; // ������
				dtm.addRow(addRow);
			}
			count = 1;
			break;
		}
	}
}
