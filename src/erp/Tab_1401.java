package erp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;

public class Tab_1401 implements ActionListener {
//	���� �׸�:40,40
//	Label ������:150,30
//	TextField ������:200,30
//	Button ������:50,30
//	���� ���� 30, ���� ���� 10
	JPanel p;
	
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
		
		// Label, Choice, Button �߰�
		Label label = new Label("���س��");
		label.setBounds(10, 50, 150, 30);
		p.add(label);
		
		Choice ch = new Choice();
		ch.setBounds(170, 50, 200, 30);
		p.add(ch);
		
		Button b1 = new Button("��ȸ");
		b1.setBounds(380, 50, 50, 30);
		p.add(b1);

		Button b2 = new Button("���� ���");
		b2.setBounds(10, 90, 80, 30);
		p.add(b2);
		
		// Table �߰�
		String[] header = {
				"����", "ǰ���ڵ�", "ǰ���", "����", "���� ����",
				"���� �ܰ�", "���� �ݾ�", "�԰� �ܰ�", "�԰� ����", "�԰� �ݾ�",
				"��� ����", "��� �ܰ�", "��� �ݾ�", "��� ����", "��� �ܰ�",
				"��� �ݾ�"
		};
		String[][] contents = {
				{"", "", "", "", "",
				"", "", "", "", "",
				"", "", "", "", "",
				""}
		};
		JTable table = new JTable(contents, header);
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(10, 130, 1000, 500);
		p.add(sp);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
