package tabTest;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;

public class Tab_1202 implements ActionListener {
//	���� �׸� : 40, 40
//	Label ������ : 150, 30
//	TextField ������ : 200, 30
//	Button ������ : 50, 30
//	���� ���� 30, ���� ���� 10
	JPanel p;
	
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
		
		TextField ontf = new TextField();
		ontf.setBounds(170, 50, 200, 30);
		p.add(ontf);
		
		// Label �߰�
		Label[] label = new Label[6];
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
		TextField[] tf = new TextField[6];
		
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
		b1.setBounds(380, 50, 50, 30);
		p.add(b1);

		Button b2 = new Button("���");
		b2.setBounds(750, 110, 50, 30);
		p.add(b2);
		
		// Table �߰�
		String[] header = {
				"����", "�ŷ�ó", "ǰ���ڵ�", "ǰ���", "����",
				"����", "�ܰ�", "�ݾ�", "�ܰ�(KRW)", "�ݾ�(KRW)"
		};
		String[][] contents = {
				{"", "", "", "", "", "", "", "", "", ""}
		};
		/*
		'+'��ư�� Ŭ���ɋ����� table�� ���� �߰��Ǿ���ϹǷ�, String 2���� �迭 ��� ��ü �迭�� ������ �ִ��� �׽�Ʈ�غ���
		*/
		JTable table = new JTable(contents, header);
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(10, 230, 1000, 50);
		p.add(sp);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
