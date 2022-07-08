package erp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;

public class Tab_1301 implements ActionListener {
//	���� �׸�:40,40
//	Label ������:150,30
//	TextField ������:200,30
//	Button ������:50,30
//	���� ���� 30, ���� ���� 10
	JPanel p;
	
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
		
		// Choice �߰�
		Choice[] ch = new Choice[4];
		
		for(int i=0; i<ch.length; i++) {
			ch[i] = new Choice();
			p.add(ch[i]);
		}
		ch[0].setBounds(170, 50, 200, 30); // �Ⱓ
		ch[1].setBounds(170, 90, 200, 30); // �Ⱓ
		ch[2].setBounds(540, 50, 200, 30);
		ch[3].setBounds(540, 90, 200, 30);
		
		// TextField �߰�
		TextField pltf = new TextField();
		pltf.setBounds(540, 130, 200, 30);
		p.add(pltf);
		
		// Button �߰�
		Button plb = new Button("��");
		plb.setBounds(750, 130, 30, 30);
		p.add(plb);
		/*
		��ư�� TextField ������ ������ �ִ��� üũ
		*/
		
		Button b1 = new Button("��ȸ");
		b1.setBounds(750, 50, 50, 30);
		p.add(b1);

		Button b2 = new Button("�԰�ó��");
		b2.setBounds(10, 130, 50, 30);
		p.add(b2);
		
		// Table �߰�
		String[] header = {
				"", "����", "offer��ȣ", "�ŷ�ó", "ǰ���ڵ�",
				"ǰ���", "����", "����"
		};
		String[][] contents = {
				{"", "", "", "", "", "", "", ""}
		};
		/*
		checkbox �߰��Ǿ����
		*/
		JTable table = new JTable(contents, header);
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(10, 170, 1000, 500);
		p.add(sp);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
