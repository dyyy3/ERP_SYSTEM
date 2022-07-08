package erp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;

public class Tab_1101 implements ActionListener {
//	���� �׸� : 40, 40
//	Label ������ : 150, 30
//	TextField ������ : 200, 30
//	���� ���� 30, ���� ���� 10
	
	JPanel p;
	
	public Tab_1101() {
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
		
		Label l1 = new Label("ǰ������");
		l1.setBounds(50, 10, 150, 30);
		p.add(l1);

		// label �߰�
		Label lpc = new Label("ǰ���ڵ�");
		Label lpn = new Label("ǰ���");
		
		lpc.setBounds(10, 50, 150, 30);
		lpn.setBounds(10, 90, 150, 30);
		
		p.add(lpc);
		p.add(lpn);
		
		Label[] label = new Label[7];
		String[] labelName = {
				"1.��з�(�ڻ걸��)", "2.�ߺз�(����1)", "3.�Һз�(����2)", "4.���з�1",
				"5.���걹��(������)", "6.���з�2(��������)", "7.���з�3(�β�)"};

		for(int i=0; i<label.length; i++) {
			label[i] = new Label(labelName[i]);
			p.add(label[i]);
		}
		
		label[0].setBounds(10, 130, 150, 30);
		label[1].setBounds(10, 170, 150, 30);
		label[2].setBounds(10, 210, 150, 30);
		label[3].setBounds(10, 250, 150, 30);
		label[4].setBounds(380, 130, 150, 30);
		label[5].setBounds(380, 170, 150, 30);
		label[6].setBounds(380, 210, 150, 30);
		
		
		// textfield �߰�
		TextField tfpc = new TextField(); // ǰ���ڵ�
		TextField tfpn = new TextField(); // ǰ���
		
		tfpc.setEditable(false); // ���� �Ұ�
		tfpc.setFocusable(false); // focusing �Ұ�
		
		tfpn.setEditable(false); // ���� �Ұ�

		p.add(tfpc);
		p.add(tfpn);
		
		tfpc.setBounds(170, 50, 200, 30);
		tfpn.setBounds(170, 90, 560, 30);
		
		// choice �߰�
		
		Choice[] ch = new Choice[7];
		
		for(int i=0; i<ch.length; i++) {
			ch[i] = new Choice();
			p.add(ch[i]);
		}
		
		/*
		�� choice���� ���� �ҷ����� �ڵ� �ۼ� �ʿ�
		*/
		
		for(int i=0; i<ch.length; i++) {
			ch[0].add(Integer.toString(i));
		}
		
		ch[0].setBounds(170, 130, 200, 30);
		ch[1].setBounds(170, 170, 200, 30);
		ch[2].setBounds(170, 210, 200, 30);
		ch[3].setBounds(170, 250, 200, 30);
		ch[4].setBounds(540, 130, 200, 30);
		ch[5].setBounds(540, 170, 200, 30);
		ch[6].setBounds(540, 210, 200, 30);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
