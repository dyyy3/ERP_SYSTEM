package erp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;
import javax.swing.table.*;

public class Tab_1501 implements ActionListener {
//	���� �׸� : 40, 40
//	Label ������ : 150, 30
//	TextField ������ : 200, 30
//	Button ������ : 50, 30
//	���� ���� 30, ���� ���� 10
	
	JPanel p;
	TextField[] tf;
	DefaultTableModel dtm1;
	JTable table1, table2;
	Dao dao = new Dao();
	Vo vo;
	
	DefaultTableModel model = new DefaultTableModel() {
		public Class<?> getColumnClass(int column)
		{
			switch(column)
			{
			case 0:
				return String.class;
			case 1:
				return String.class;
			case 2:
				return Boolean.class;
			case 3:
				return Boolean.class;
			case 4:
				return Boolean.class;
			case 5:
				return Boolean.class;
			default:
				return String.class;
			}
		}
	};
	
	DefaultTableCellRenderer dcr = new DefaultTableCellRenderer() {
		public Component getTableCellRendererComponent // ��������
		(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			JCheckBox box = new JCheckBox();
			box.setSelected(((Boolean) value).booleanValue());
			box.setHorizontalAlignment(JLabel.CENTER);
			return box;
		}
	};
	
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
		tf = new TextField[4];
		
		for(int i=0; i<tf.length; i++) {
			tf[i] = new TextField();
			p.add(tf[i]);
		}
		
		tf[0].setBounds(170, 50, 200, 30); // ����ڵ�(ID)
		tf[1].setBounds(170, 90, 200, 30); // �̸�
		tf[2].setBounds(540, 50, 200, 30); // password
		tf[3].setBounds(540, 90, 200, 30); // �μ�
		
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
		Button[] button = new Button[2];
		String[] buttonName = {
				"���", "��ȸ"
//				"���", "�μ��ڵ� ��ȸ", "��ȸ", "����"
		};

		for(int i=0; i<button.length; i++) {
			button[i] = new Button(buttonName[i]);
			button[i].addActionListener(this);
			p.add(button[i]);
		}
		
		button[0].setBounds(750, 50, 50, 30);
//		button[1].setBounds(750, 90, 100, 30); // �μ��ڵ� ��ȸ
		button[1].setBounds(10, 170, 50, 30);
//		button[2].setBounds(10, 170, 50, 30);
//		button[3].setBounds(70, 170, 50, 30); // ����
		
		// ��ȸ table �߰�
		String[] header1 = {"����ڵ�(ID)", "�μ��ڵ�", "�μ���", "�̸�", "password"};
		dtm1 = new DefaultTableModel(header1, 0);
		table1 = new JTable(dtm1);
		table1.setFillsViewportHeight(true); //�����̳��� ��ü ���̸� ���̺��� ���� ����ϵ��� ����

		JScrollPane sp1 = new JScrollPane(table1);
		sp1.setBounds(10, 210, 1000, 55);
		
		String[] contents1 = {"", "", "", "", ""};
		dtm1.addRow(contents1);

		// �� �ʺ� ����
		TableColumn t0 = table1.getColumnModel().getColumn(0);
		TableColumn t1 = table1.getColumnModel().getColumn(1);
		TableColumn t2 = table1.getColumnModel().getColumn(2);
		TableColumn t3 = table1.getColumnModel().getColumn(3);
		TableColumn t4 = table1.getColumnModel().getColumn(4);

		t0.setPreferredWidth(200);
		t1.setPreferredWidth(200);
		t2.setPreferredWidth(200);
		t3.setPreferredWidth(200);
		t4.setPreferredWidth(200);

		// �� ���� ����
		table1.setRowHeight(0, 30);
		
		p.add(sp1);
		
		// ���� table �߰�
		table2 = new JTable(model);
		table2.setFillsViewportHeight(true); //�����̳��� ��ü ���̸� ���̺��� ���� ����ϵ��� ����
		
		JScrollPane sp2 = new JScrollPane(table2);
		sp2.setBounds(10, 270, 1000, 295);
		
		String[] header2 = {"��޴�", "�߸޴�", "�б�", "����", "����", "����"};
		
		for(int i=0; i<header2.length; i++) {
			model.addColumn(header2[i]);
		}
		
		String[][] contents2 = {{"ǰ��", "ǰ���ڵ� ���"},
								{"����", "���� offer���"},
								{"", "���Կ��� ���"},
								{"����", "�԰� ���"},
								{"", "��� ���"},
								{"", "��� ��Ȳ"},
								{"���", "���� ���"},
								{"", "ǰ����Һ�"},
								{"�λ�", "���� �� ���� ����"}
								};
		
		for(int i=0; i<9; i++) {
			Object[] addRow = new Object[6];
			addRow[0] = contents2[i][0];
			addRow[1] = contents2[i][1];
			for(int j=2; j<6; j++) {
				addRow[j] = false;
			}
			model.addRow(addRow);
		}
		
		// �� �ʺ� ����
		TableColumn c0 = table2.getColumnModel().getColumn(0); 
		TableColumn c1 = table2.getColumnModel().getColumn(1); 
		TableColumn c2 = table2.getColumnModel().getColumn(2); 
		TableColumn c3 = table2.getColumnModel().getColumn(3); 
		TableColumn c4 = table2.getColumnModel().getColumn(4); 
		TableColumn c5 = table2.getColumnModel().getColumn(5); 
		
		c0.setPreferredWidth(150);
		c1.setPreferredWidth(250);
		c2.setPreferredWidth(150);
		c3.setPreferredWidth(150);
		c4.setPreferredWidth(150);
		c5.setPreferredWidth(150);
		 
		// �� ���� ����
		for(int i=0; i<table2.getRowCount(); i++) {
			table2.setRowHeight(i, 30);
		}
		
		p.add(sp2);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "���" :
			// ��ĭ üũ
			boolean checkUserInfoNull = true;
			
			if(tf[0].getText() == null || tf[0].getText().equals("")) {
				new ErrorMessageDialog("����ڵ�(ID)�� �Է��ϼ���.", "���� �� ���� ����");
				checkUserInfoNull = false;
				break;
			}
			if(tf[1].getText() == null || tf[1].getText().equals("")) {
				new ErrorMessageDialog("�̸��� �Է��ϼ���.", "���� �� ���� ����");
				checkUserInfoNull = false;
				break;
			}
			if(tf[2].getText() == null || tf[2].getText().equals("")) {
				new ErrorMessageDialog("password�� �Է��ϼ���.", "���� �� ���� ����");
				checkUserInfoNull = false;
				break;
			}
			if(tf[3].getText() == null || tf[3].getText().equals("")) {
				new ErrorMessageDialog("�μ��ڵ带 �Է��ϼ���.", "���� �� ���� ����");
				checkUserInfoNull = false;
				break;
			}
			
			// user info ����
			boolean tryInsertUserInfo = false;
			
			if(checkUserInfoNull == true) {
				String user_id = tf[0].getText(); // ����ڵ�(ID)
				String user_pw = tf[2].getText(); // password
				int dept_id = Integer.parseInt(tf[3].getText()); // �μ��ڵ�
				String user_name = tf[1].getText(); // �̸�
				
				vo = new Vo(user_id, user_pw, dept_id, user_name);
				tryInsertUserInfo = dao.insertUserInfo(vo);
			}
			
			if(tryInsertUserInfo == true) {
				new ErrorMessageDialog("��� ������ ��ϵǾ����ϴ�.", "���� �� ���� ����");
				
//				// ������ ������ �۾��� ���� �����̹Ƿ� ���� ���̺� ����� ���� set
//				vo = new Vo("user_id", tf[0].getText());
//				String[] result = dao.selectAllUserInfoAndDepartmentInfoWheretwoFields(vo);
//
//				dtm1.setValueAt(result[0], 0, 0);
//				dtm1.setValueAt(result[2], 0, 1);
//				dtm1.setValueAt(result[5], 0, 2);
//				dtm1.setValueAt(result[3], 0, 3);
//				dtm1.setValueAt(result[1], 0, 4);
				
				// ���� ȭ�鿡 ���� ���� ���� 1�� ����
				String user_id = tf[0].getText(); // ����ڵ�(ID)
				vo = new Vo(user_id, "UI-A-1000", 1, 1, 1, 1);
//				INSERT INTO PERMISSION (USER_ID, SCREEN_ID, R, C, U, D) VALUES ('201804003', 'UI-A-1000', 1, 1, 1, 1)
				dao.insertPermission(vo);
				
				// dept_id�� ���� �μ��� �⺻ ���� ���� ����
				int dept_id = Integer.parseInt(tf[3].getText()); // �μ��ڵ�
				switch(dept_id) {
				case 0 : // ������
					vo = new Vo(user_id, "UI-A-1101", 1, 1, 1, 1); // ǰ���ڵ� ���
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1201", 1, 1, 1, 1); // ����offer ���
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1202", 1, 1, 1, 1); // ���Կ��� ���
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1301", 1, 1, 1, 1); // �԰� ���
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1302", 1, 1, 1, 1); // ��� ���
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1303", 1, 0, 1, 1); // ��� ��Ȳ
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1401", 1, 0, 1, 1); // ���� ���
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1402", 1, 0, 1, 1); // ǰ����Һ�
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1501", 1, 1, 1, 1); // �λ����
					dao.insertPermission(vo);
					break;
				case 10 : // ����
					vo = new Vo(user_id, "UI-A-1101", 1, 1, 1, 1); // ǰ���ڵ� ���
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1201", 1, 1, 1, 1); // ����offer ���
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1202", 1, 1, 1, 1); // ���Կ��� ���
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1301", 1, 1, 1, 1); // �԰� ���
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1302", 1, 0, 0, 0); // ��� ���
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1303", 1, 0, 0, 0); // ��� ��Ȳ
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1401", 1, 0, 0, 0); // ���� ���
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1402", 1, 0, 0, 0); // ǰ����Һ�
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1501", 0, 0, 0, 0); // �λ����
					dao.insertPermission(vo);
					break;
				case 20 : // ���� 
					vo = new Vo(user_id, "UI-A-1101", 0, 0, 0, 0); // ǰ���ڵ� ���
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1201", 0, 0, 0, 0); // ����offer ���
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1202", 0, 0, 0, 0); // ���Կ��� ���
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1301", 1, 0, 0, 0); // �԰� ���
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1302", 1, 1, 1, 1); // ��� ���
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1303", 1, 0, 0, 0); // ��� ��Ȳ
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1401", 0, 0, 0, 0); // ���� ���
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1402", 0, 0, 0, 0); // ǰ����Һ�
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1501", 0, 0, 0, 0); // �λ����
					dao.insertPermission(vo);
					break;
				case 30 : // ���
					vo = new Vo(user_id, "UI-A-1101", 0, 0, 0, 0); // ǰ���ڵ� ���
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1201", 0, 0, 0, 0); // ����offer ���
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1202", 0, 0, 0, 0); // ���Կ��� ���
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1301", 1, 0, 0, 0); // �԰� ���
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1302", 0, 0, 0, 0); // ��� ���
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1303", 1, 0, 0, 0); // ��� ��Ȳ
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1401", 1, 0, 0, 0); // ���� ���
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1402", 1, 0, 0, 0); // ǰ����Һ�
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1501", 0, 0, 0, 0); // �λ����
					dao.insertPermission(vo);
					break;
				case 40 : // �λ�
					vo = new Vo(user_id, "UI-A-1101", 0, 0, 0, 0); // ǰ���ڵ� ���
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1201", 0, 0, 0, 0); // ����offer ���
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1202", 0, 0, 0, 0); // ���Կ��� ���
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1301", 0, 0, 0, 0); // �԰� ���
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1302", 0, 0, 0, 0); // ��� ���
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1303", 1, 0, 0, 0); // ��� ��Ȳ
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1401", 0, 0, 0, 0); // ���� ���
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1402", 0, 0, 0, 0); // ǰ����Һ�
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1501", 1, 1, 1, 0); // �λ����
					dao.insertPermission(vo);
					break;
				case 50 : // ����
					vo = new Vo(user_id, "UI-A-1101", 0, 0, 0, 0); // ǰ���ڵ� ���
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1201", 0, 0, 0, 0); // ����offer ���
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1202", 0, 0, 0, 0); // ���Կ��� ���
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1301", 0, 0, 0, 0); // �԰� ���
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1302", 0, 0, 0, 0); // ��� ���
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1303", 1, 0, 0, 0); // ��� ��Ȳ
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1401", 0, 0, 0, 0); // ���� ���
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1402", 0, 0, 0, 0); // ǰ����Һ�
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1501", 0, 0, 0, 0); // �λ����
					dao.insertPermission(vo);
					break;
				}
				
				vo = new Vo();
				
				
				
			}else {
				new ErrorMessageDialog("�̹� ��ϵ� ��� �ڵ��Դϴ�.", "���� �� ���� ����");
			}
			
			break;
			
		case "�μ��ڵ� ��ȸ" :
			break;
			
		case "��ȸ" :
			// ��ĭ üũ
			boolean checkUserCodeNull = true;
			
			if(dtm1.getValueAt(0, 0) == null || dtm1.getValueAt(0, 0).equals("")) {
				new ErrorMessageDialog("����ڵ�(ID)�� �Է��ϼ���.", "���� �� ���� ����");
				checkUserCodeNull = false;
				break;
			}
			
			// user_info�� department_info join ���̺�, permission ���̺� �� �о����
			if(checkUserCodeNull == true) {
				String user_id = dtm1.getValueAt(0, 0).toString();
				vo = new Vo("user_id", user_id);
				String[] result = dao.selectAllUserInfoAndDepartmentInfoWheretwoFields(vo);

				dtm1.setValueAt(result[2], 0, 1); // �μ��ڵ�
				dtm1.setValueAt(result[5], 0, 2); // �μ���
				dtm1.setValueAt(result[3], 0, 3); // �̸�
				dtm1.setValueAt(result[1], 0, 4); // password
				
				vo = new Vo("user_id", user_id, "screen_id");
//				SELECT * FROM PERMISSION WHERE USER_ID = '202201000' ORDER BY SCREEN_ID
				String[][] result2 = dao.selectAllPermissionWhere(vo);
				
				for(int i=1; i<result2.length; i++) { // i=0 : ����ȭ��
					for(int j=2; j<result2[i].length; j++) {
						if(result2[i][j].equals("1")) {
							model.setValueAt(true, i-1, j);
						}else if(result2[i][j].equals("0")){
							model.setValueAt(false, i-1, j);
						}
					}
				}
			}
			
			break;
			
//		case "����" :
//			boolean tryInsertPermission = false;
//			boolean tryUpdatePermission = false;
//			
//			// screen id
//			vo = new Vo("screen", "screen_id", "screen_id");
//			String[] screenID = dao.selectOneFieldOrderBy(vo);
//			String[] checkBox = new String[4];
//			
//			for(int i=1; i<=9; i++ ) {
//				String user_id = dtm1.getValueAt(0, 0).toString();
//				String screen_id = screenID[i];
//				
//				// permission ���̺��� screen_id�� user_id�� select
//				vo = new Vo("permission", "user_id", "user_id", user_id, "screen_id", screenID[i]);
//				String result = dao.selectOneFieldWhereTwoFields(vo);
//				
//				// ������� ������ insert
//				if(result == null || result.equals("")) {
//					for(int j=2; j<=5; j++) {
//						String s = model.getValueAt(i-1, j).toString();
//						
//						System.out.println("s : " + s);
//						
//						if(s.equals("true")) {
//							checkBox[j-2] = "1";
//						}else if(s.equals("false")) {
//							checkBox[j-2] = "0";
//						}
//					}
//					
//					vo = new Vo(user_id, screen_id, Integer.parseInt(checkBox[0]), Integer.parseInt(checkBox[1]),
//							Integer.parseInt(checkBox[2]), Integer.parseInt(checkBox[3]));
//					tryInsertPermission = dao.insertPermission(vo);
//					
//				// ������� ������ update
//				}else {
//					for(int j=2; j<=5; j++) {
//						String s = model.getValueAt(i-1, j).toString();
//						if(s.equals("true")) {
//							checkBox[j-2] = "1";
//						}else if(s.equals("false")) {
//							checkBox[j-2] = "0";
//						}
//					}
//					vo = new Vo("permission",
//								"r", Integer.parseInt(checkBox[0]), "c", Integer.parseInt(checkBox[1]),
//							 	"u", Integer.parseInt(checkBox[2]), "d", Integer.parseInt(checkBox[3]),
//							 	"user_id", user_id, "screen_id", screenID[i]);
//					tryUpdatePermission = dao.updateFourIntFieldsWhereTwoFields(vo);
//				}
//			}
//			
//			if(tryInsertPermission == true || tryUpdatePermission == true) {
//				new ErrorMessageDialog("�����Ǿ����ϴ�.", "���� �� ���� ����");
//			}else {
//				new ErrorMessageDialog("���� �����Ͽ����ϴ�.", "���� �� ���� ����");
//			}
//			break;
		}
	}
}
