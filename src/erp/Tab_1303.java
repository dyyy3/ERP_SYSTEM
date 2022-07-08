package erp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;

public class Tab_1303 implements ActionListener {
//	���� �׸�:40,40
//	Label ������:150,30
//	TextField ������:200,30
//	Button ������:50,30
//	���� ���� 30, ���� ���� 10
	JPanel p;
	
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
		Label[] label = new Label[3];
		String[] labelName = {"�Ⱓ", "ǰ���ڵ�", "ǰ���"};

		for (int i = 0; i < label.length; i++) {
			label[i] = new Label(labelName[i]);
			p.add(label[i]);
		}
		
		label[0].setBounds(10, 50, 150, 30);
		label[1].setBounds(380, 50, 150, 30);
		label[2].setBounds(380, 90, 150, 30);
		
		// Choice �߰�
		Choice[] ch = new Choice[2];
		
		for(int i=0; i<ch.length; i++) {
			ch[i] = new Choice();
			p.add(ch[i]);
		}
		ch[0].setBounds(170, 50, 200, 30); // �Ⱓ
		ch[1].setBounds(170, 90, 200, 30); // �Ⱓ
		
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
		/*
		��ư�� TextField ������ ������ �ִ��� üũ
		*/
		
		Button b1 = new Button("��ȸ");
		b1.setBounds(790, 50, 50, 30);
		p.add(b1);

		// Table �߰�
		String[] header = {
				"����", "offer��ȣ", "�ŷ�ó", "ǰ���ڵ�", "ǰ���",
				"����", "���� ����", "�԰� ����", "��� ����", "��� ����"
		};
		String[][] contents = {
				{"", "", "", "", "", "", "", "", "", ""}
		};
		/*
		checkbox �߰��Ǿ����
		*/
		JTable table = new JTable(contents, header);
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(10, 130, 1000, 500);
		p.add(sp);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
