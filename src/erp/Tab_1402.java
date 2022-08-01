package erp;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
//import javax.swing.event.*;
import javax.swing.table.TableColumn;

public class Tab_1402 implements ActionListener, ItemListener {
//	���� �׸�:40,40
//	Label ������:150,30
//	TextField ������:200,30
//	Button ������:50,30
//	���� ���� 30, ���� ���� 10
	JPanel p;
	Choice chYear;
	Choice chMonth;
	DefaultTableModel dtm;
	JTable table;
	
	public Tab_1402() {
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

		Label l1 = new Label("�˻�");
		l1.setBounds(50, 10, 150, 30);
		p.add(l1);
		
		// Label �߰�
		Label[] label = new Label[3];
		String[] labelName = {"���س��", "ǰ���ڵ�", "ǰ���"};
		
		for(int i=0; i<label.length; i++) {
			label[i] = new Label(labelName[i]);
			p.add(label[i]);
		}
		
		label[0].setBounds(10, 50, 150, 30);
		label[1].setBounds(380, 50, 150, 30);
		label[2].setBounds(380, 90, 150, 30);
		
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

		if (date[1].charAt(0) == '0') {
			char[] ch = date[1].toCharArray();
			date[1] = String.valueOf(ch[1]);
		}

		for (int i = 2000; i <= Integer.parseInt(date[0]); i++) {
			chYear.add(String.valueOf(i));
		}

		for (int i = 1; i <= 12; i++) {
			chMonth.add(String.valueOf(i));
		}

		chYear.select(date[0]);
		chMonth.select(date[1]);
		
		p.add(chYear);
		p.add(chMonth);

		// TextField �߰�
		TextField pctf = new TextField();
		pctf.setBounds(540, 50, 200, 30);
		p.add(pctf);

		TextField pntf = new TextField();
		pntf.setBounds(540, 90, 200, 30);
		p.add(pntf);
		
		// Button �߰�
		Button pcb = new Button("��");
		pcb.setBounds(750, 50, 30, 30);
		p.add(pcb);
		
		Button b1 = new Button("��ȸ");
		b1.setBounds(790, 50, 50, 30);
		p.add(b1);
		
		// icon
		JLabel imageLabel2 = new JLabel(); // ImageIcon �� ���� Label ����

		ImageIcon folder2 = new ImageIcon("src/images/folder-icon_34416.png");
		Image img2 = folder2.getImage(); // image ũ�Ⱑ 512, 512�̹Ƿ� 40, 40���� �ٲٱ� ���� ImageIcon -> Image�� ����
		Image changeimg2 = img2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon changefolder2 = new ImageIcon(changeimg2);

		imageLabel2.setIcon(changefolder2);
		p.add(imageLabel2);
		imageLabel2.setBounds(10, 130, 30, 30);

		Label l2 = new Label("ǰ����Һ�");
		l2.setBounds(50, 130, 150, 30);
		p.add(l2);
		
		// Table �߰�
		String[] header = {
				"����", "ǰ���ڵ�", "ǰ���", "����", "���� ����",
				"���� �ܰ�", "���� �ݾ�", "�԰� �ܰ�", "�԰� ����", "�԰� �ݾ�",
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
		sp.setBounds(10, 170, 1160, 500);
		p.add(sp);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "��ȸ" :
			break;
			
		case "��" :
			break;
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
}
