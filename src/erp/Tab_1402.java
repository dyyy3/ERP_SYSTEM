package erp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;

public class Tab_1402 implements ActionListener {
//	���� �׸�:40,40
//	Label ������:150,30
//	TextField ������:200,30
//	Button ������:50,30
//	���� ���� 30, ���� ���� 10
	JPanel p;
	
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
		Choice ch = new Choice();
		ch.setBounds(170, 50, 200, 30);
		p.add(ch);
		
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
		String[][] contents = {
				{"", "", "", "", "",
				"", "", "", "", "",
				"", "", "", "", "",
				""}
		};
		JTable table = new JTable(contents, header);
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(10, 170, 1000, 500);
		p.add(sp);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
