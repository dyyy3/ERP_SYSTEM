package erp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;

public class Tab_1501 implements ActionListener {
//	���� �׸� : 40, 40
//	Label ������ : 150, 30
//	TextField ������ : 200, 30
//	Button ������ : 50, 30
//	���� ���� 30, ���� ���� 10
	
	JPanel p;
	
	public Tab_1501() {
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
		
		Label l1 = new Label("��� ���");
		l1.setBounds(50, 10, 150, 30);
		p.add(l1);
		
		// Label
		Label[] label = new Label[4];
		String[] labelName = {
				"����ڵ�(ID)", "�̸�",
				"password", "�μ�"};

		for(int i=0; i<label.length; i++) {
			label[i] = new Label(labelName[i]);
			p.add(label[i]);
		}
		
		label[0].setBounds(10, 50, 150, 30);
		label[1].setBounds(10, 90, 150, 30);
		label[2].setBounds(380, 50, 150, 30);
		label[3].setBounds(380, 90, 150, 30);
		
		// TextField
		TextField[] tf = new TextField[4];
		
		for(int i=0; i<tf.length; i++) {
			tf[i] = new TextField();
			p.add(tf[i]);
		}
		
		tf[0].setBounds(170, 50, 200, 30);
		tf[1].setBounds(170, 90, 200, 30);
		tf[2].setBounds(540, 50, 200, 30);
		tf[3].setBounds(540, 90, 200, 30);
		
		// icon
		JLabel imageLabel2 = new JLabel(); // ImageIcon �� ���� Label ����

		ImageIcon folder2 = new ImageIcon("src/images/folder-icon_34416.png");
		Image img2 = folder2.getImage(); // image ũ�Ⱑ 512, 512�̹Ƿ� 40, 40���� �ٲٱ� ���� ImageIcon -> Image�� ����
		Image changeimg2 = img2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon changefolder2 = new ImageIcon(changeimg2);

		imageLabel2.setIcon(changefolder2);
		p.add(imageLabel2);
		imageLabel2.setBounds(10, 130, 30, 30);
		
		Label l2 = new Label("���� ����");
		l2.setBounds(50, 130, 150, 30);
		p.add(l2);
		
		// Button
		Button[] button = new Button[3];
		String[] buttonName = {
				"���", "��ȸ", "����"
		};

		for(int i=0; i<button.length; i++) {
			button[i] = new Button(buttonName[i]);
			p.add(button[i]);
		}
		
		button[0].setBounds(750, 50, 50, 30);
		button[1].setBounds(10, 170, 50, 30);
		button[2].setBounds(70, 170, 50, 30);
		
		// ��ȸ table
		String[] header1 = {"����ڵ�(ID)", "�μ��ڵ�", "�μ���", "�̸�", "password"};
		String[][] contents1 = {
				{"","","","",""}
		};
		JTable table1 = new JTable(contents1, header1);
		
		JScrollPane sp1 = new JScrollPane(table1);
		sp1.setBounds(10, 210, 1000, 50);
		p.add(sp1);

		//���� table
		
		// ��޴� �� : 200, 30
		// �߸޴� �� : 400, 30
		// �б�, ����, ����, ���� �� : 100, 30
		Label[] label2 = new Label[20];
		String[] labelName2 = {
			"��޴�", "�߸޴�", // 0~1
			"�б�", "����", "����", "����", // 2~5
			"ǰ��", "����", "����", "���", "�λ�", // 6~10
			"ǰ���ڵ� ���", "����offer ���", "���Կ��� ���", // 11~13
			"�԰� ���", "��� ���", "��� ��Ȳ", // 14~16
			"���� ���", "ǰ����Һ�", "���� �� ���� ����" // 17~19
		};
		
		for(int i=0; i<label2.length; i++){
			label2[i] = new Label(labelName2[i]);
			p.add(label2[i]);
		}
		
		label2[0].setBounds(10, 300, 200, 30);
		label2[1].setBounds(210, 300, 400, 30);
		
		label2[2].setBounds(610, 300, 100, 30);
		label2[3].setBounds(710, 300, 100, 30);
		label2[4].setBounds(810, 300, 100, 30);
		label2[5].setBounds(910, 300, 100, 30);
		
		label2[6].setBounds(10, 330, 200, 30);
		label2[7].setBounds(10, 360, 200, 60);
		label2[8].setBounds(10, 420, 200, 90);
		label2[9].setBounds(10, 510, 200, 30);
		label2[10].setBounds(10, 570, 200, 30);

		label2[11].setBounds(210, 330, 400, 30);
		label2[12].setBounds(210, 360, 400, 30);
		label2[13].setBounds(210, 390, 400, 30);
		
		label2[14].setBounds(210, 420, 400, 30);
		label2[15].setBounds(210, 450, 400, 30);
		label2[16].setBounds(210, 480, 400, 30);
		
		label2[17].setBounds(210, 510, 400, 30);
		label2[18].setBounds(210, 540, 400, 30);
		label2[19].setBounds(210, 570, 400, 30);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
