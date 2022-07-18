package erp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;

public class Tab_1101 implements ActionListener, ItemListener {
//	���� �׸� : 40, 40
//	Label ������ : 150, 30
//	TextField ������ : 200, 30
//	Button ������ : 50,30
//	���� ���� 30, ���� ���� 10
	
	JPanel p;
	Choice[] ch;
	TextField tfpc; // ǰ���ڵ�
	TextField tfpn; // ǰ���
	String[] tableName = {"asset", "stone_type", "stone_name", "slab_type", "country", "surface", "thickness"};
	String[] fieldName = new String[7];
	StringBuilder productCode = new StringBuilder(" -    -      -   "); // ǰ���ڵ� TextField�� ����� ����. ���� 17
	//1-G038-DCN003-020
	StringBuilder productName = new StringBuilder(); // ǰ��� TestField�� ����� ����
	// ��ǰ-��Ÿ���Ǹ���-�ٿ�-�߱�-����-20T
	String[] pn = new String[6];
	// 0 : asset, 1 : stone_name, 2 : slab_type, 3 : country, 4 : surface, 5: thickness
	String[] choiceXY;
	String eventSource = "";
	String eventChoiceXY;
	
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
		tfpc = new TextField(); // ǰ���ڵ�
		tfpn = new TextField(); // ǰ���
		
		tfpc.setEditable(false); // ���� �Ұ�
		tfpn.setEditable(false); // ���� �Ұ�

		p.add(tfpc);
		p.add(tfpn);
		
		tfpc.setBounds(170, 50, 200, 30);
		tfpn.setBounds(170, 90, 560, 30);
		
		// choice �߰�
		
		ch = new Choice[7];
		
		for(int i=0; i<ch.length; i++) {
			ch[i] = new Choice();
			p.add(ch[i]);
			ch[i].addItemListener((ItemListener)this);
		}

		Dao dao = new Dao();
		
		for(int i=0; i<tableName.length; i++) {
			Vo vo = new Vo(tableName[i], tableName[i]);
			String[] result = dao.selectOneField(vo);
//			Vo vo = new Vo(tableName[i]);
//			String[] result = dao.selectAll(vo);
			for(int j=0; j<result.length; j++) {
				ch[i].add(result[j]);
			}
		}
		
		ch[0].setBounds(170, 130, 200, 30);
		ch[1].setBounds(170, 170, 200, 30);
		ch[2].setBounds(170, 210, 200, 30);
		ch[3].setBounds(170, 250, 200, 30);
		ch[4].setBounds(540, 130, 200, 30);
		ch[5].setBounds(540, 170, 200, 30);
		ch[6].setBounds(540, 210, 200, 30);
		
		// ch�� x,y ��ǥ���� �迭�� ����
		choiceXY = new String[ch.length];
		
		for(int i=0; i<choiceXY.length; i++) {
			choiceXY[i] = ch[i].getX()+ "," + ch[i].getY();
		}
		// 170,130   170,170   170,210   170,250   540,130   540,170   540,210
		
		// button �߰�
		Button b = new Button("���");
		b.setBounds(740, 90, 50, 30);
		b.addActionListener(this);
		p.add(b);
	}
	
	
	public int getSelectedChoiceIndex(String eventChoiceXY) {
		int index = choiceXY.length + 1;
		for(int i=0; i<choiceXY.length; i++) {
			if(eventChoiceXY.equals(choiceXY[i])) {
				index = i;
			}
		}
		return index;
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
//		tfpn.setText("");
		
		eventSource = String.valueOf(e.getSource()); // java.awt.Choice[choice0,170,210,200x24,current=��Ʈ����]
		eventChoiceXY = eventSource.substring(24, 31); // 170,210
		int i = getSelectedChoiceIndex(eventChoiceXY); // i=2, �� ch[2]
		
		for(int j=0; j<fieldName.length; j++) {
			fieldName[j] = tableName[j] + "_code";
		}
		
		String record = String.valueOf(e.getItem());
		String code;
		Vo vo = new Vo(tableName[i], fieldName[i], tableName[i], record);
		Dao dao = new Dao();
		code = dao.selectOneFieldWhere(vo);
		
		switch(i) {
		case 0 : 
			productCode.replace(0, 1, code);
			pn[i] = record;
			break;
		case 1 : 
			productCode.replace(2, 3, code);
//			productName[i] = record; // stone_type ������ stone_name table�� �����Ƿ� ǰ��� ��������
			break;
		case 2 : 
			productCode.replace(3, 6, code);
			pn[i-1] = record;
			break;
		case 3 : 
			productCode.replace(7, 8, code);
			pn[i-1] = record;
			break;
		case 4 : 
			productCode.replace(8, 10, code);
			pn[i-1] = record;
			break;
		case 5 : 
			productCode.replace(10, 13, code);
			pn[i-1] = record;
			break;
		case 6 : 
			productCode.replace(14, 17, code);
			pn[i-1] = record;
			break;
		}
		
		tfpc.setText(productCode.toString());
		
//		for(int i=0; i<ch.length; i++) {
//			System.out.println(ch[i].getSelectedIndex()); // ch[]���� ���õ� ���� index ��ȯ
//		}
		
	}
	
	public boolean checkProductNameArrayisEmpty() {
		boolean b = true;
		for(int i=0; i<pn.length; i++) {
			if(pn[i] == null) { // ù��° ���� : pn[] != null
				b = false;
				return b; // false ��ȯ
			}
		}
		if(productCode.substring(2, 3).equals(" ")) { // �ι�° ���� : productCode[2] != " "
			b = false;
			return b;
		}
		return b; // true ��ȯ
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean b = checkProductNameArrayisEmpty();
		
		if(b == false) {
			new ErrorMessageDialog("��� �׸��� �������ּ���.", "ǰ���ڵ� ���");
		}else {
			for(int j=0; j<pn.length; j++) {
				if(j==pn.length-1) {
					productName.append(pn[j]);
				}else {
					productName.append(pn[j] + "-");
				}
			}
			tfpn.setText(productName.toString());
			
			boolean b2 = true;
			Vo vo = new Vo(productCode.toString(), productName.toString(), productCode.substring(0, 1),
					productCode.substring(2, 3), productCode.substring(3, 6), productCode.substring(7, 8),
					productCode.substring(8, 10), productCode.substring(10, 13), productCode.substring(14, 17));
			Dao dao = new Dao();
			b2 = dao.insertProductList(vo);
			if(b2 == true) {
				new ErrorMessageDialog("ǰ���ڵ尡 ��ϵǾ����ϴ�.", "ǰ���ڵ� ���");
			}else{
				new ErrorMessageDialog("�̹� ��ϵ� �ڵ��Դϴ�.", "ǰ���ڵ� ���");
			}
		}
		productName.delete(0, productName.capacity() + 1);
	}
}
