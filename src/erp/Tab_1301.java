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

public class Tab_1301 implements ActionListener, ItemListener {
//	���� �׸�:40,40
//	Label ������:150,30
//	TextField ������:200,30
//	Button ������:50,30
//	���� ���� 30, ���� ���� 10
	JPanel p;
	Choice ch;
	TextField[] tf;
	Button plb, b1, b2;
	UtilDateModel model;
	UtilDateModel model2;
	DefaultTableModel dtm;
	Dao dao = new Dao();
	Vo vo;
	
	public Tab_1301() {
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

		Label l1 = new Label("�԰� ����");
		l1.setBounds(50, 10, 150, 30);
		p.add(l1);
		
		// Label �߰�
		Label[] label = new Label[4];
		String[] labelName = {"�Ⱓ", "offer��ȣ", "�ŷ�ó", "ǰ���ڵ�"};

		for (int i = 0; i < label.length; i++) {
			label[i] = new Label(labelName[i]);
			p.add(label[i]);
		}
		
		label[0].setBounds(10, 50, 150, 30);
		label[1].setBounds(380, 50, 150, 30);
		label[2].setBounds(380, 90, 150, 30);
		label[3].setBounds(380, 130, 150, 30);
		
		// JDatePicker �߰�
		model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		datePicker.setBounds(170, 50, 200, 30);

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
		
		// JDatePicker �߰�(2)
		model2 = new UtilDateModel();
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
		JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2);
		datePicker2.setBounds(170, 90, 200, 30);
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		Date todayDate2 = new Date();
		String today2 = sdf2.format(todayDate2);
		
		String[] date2 = today2.split("-");
		int dateY2 = Integer.parseInt(date2[0]);
		int dateM2 = Integer.parseInt(date2[1]) - 1;
		int dateD2 = Integer.parseInt(date2[2]);
		model2.setDate(dateY2, dateM2, dateD2);
		model2.setSelected(true);
		
		p.add(datePicker2);
		
		// Choice �߰�
		ch = new Choice();
		ch.setBounds(540, 90, 200, 30); // �ŷ�ó
		ch.addItemListener((ItemListener) this);
		p.add(ch);
		
		vo = new Vo("client", "client_name");
		String[] clientList = dao.selectOneField(vo);
		for(int i=0; i<clientList.length; i++ ) {
			ch.add(clientList[i]);
		}
		
		// TextField �߰�
		tf = new TextField[2];
		
		for(int i=0; i<tf.length; i++) {
			tf[i] = new TextField();
			p.add(tf[i]);
			tf[i].addActionListener(this);
		}
		tf[0].setBounds(540, 50, 200, 30); // offer��ȣ
		tf[1].setBounds(540, 130, 200, 30); // ǰ���ڵ�
		
		// Button �߰�
		plb = new Button("��");
		plb.setBounds(750, 130, 30, 30);
		p.add(plb);
		/*
		��ư�� TextField ������ ������ �ִ��� üũ
		*/
		
		b1 = new Button("��ȸ");
		b1.setBounds(750, 50, 50, 30);
		b1.addActionListener(this);
		p.add(b1);

		b2 = new Button("�԰�ó��");
		b2.setBounds(10, 130, 50, 30);
		b2.addActionListener(this);
		p.add(b2);
		
		// Table �߰�
		String[] header = {
				"", "����", "offer��ȣ", "�ŷ�ó", "ǰ���ڵ�",
				"ǰ���", "����", "����"
		};
		
		/*
		checkbox �߰��Ǿ����
		*/
		
		dtm = new DefaultTableModel(header, 0);		
		JTable table = new JTable(dtm);
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(10, 170, 1000, 500);
		p.add(sp);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "��ȸ" :
			String date1 = model.getYear() + "-" + (model.getMonth() + 1) + "-" + model.getDay();
			String date2 = model2.getYear() + "-" + (model2.getMonth() + 1) + "-" + model2.getDay();
			String offer_num = tf[0].getText();
			String client_name = ch.getSelectedItem();
			String product_code = tf[1].getText();
			
			// �Ⱓ + offer��ȣ�� ��ȸ
			// �Ⱓ + �ŷ�ó�� ��ȸ
			// �Ⱓ + ǰ���ڵ�� ��ȸ
			break;
		case "�԰�ó��" :
			break;
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
}
