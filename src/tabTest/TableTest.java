package tabTest;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;
import javax.swing.table.*;

public class TableTest {
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize(1200, 800);
		
		JPanel p = new JPanel();
		p.setLayout(null);
		
		// ��ȸ table
		String[] header1 = { "����ڵ�(ID)", "�μ��ڵ�", "�μ���", "�̸�", "password" };
		String[][] contents1 = { { "", "", "", "", "" } };
		
		DefaultTableModel tm1 = new DefaultTableModel(contents1, header1);		
		JTable table1 = new JTable(tm1);
//		JTable table1 = new JTable(contents1, header1);

		JScrollPane sp1 = new JScrollPane(table1);
		sp1.setBounds(10, 210, 1000, 50);
		p.add(sp1);
		
		JCheckBox cb1 = new JCheckBox();
		
		tm1.setValueAt(cb1, 0, 0);

		// ���� table
		String[] header2 = { "��޴�", "�߸޴�", "�б�", "����", "����", "����" };
		String[][] contents2 = { { "ǰ��", "ǰ���ڵ� ���", "", "", "", "" }, { "����", "����offer ���", "", "", "", "" },
				{ "", "���Կ��� ���", "", "", "", "" }, { "", "�԰� ���", "", "", "", "" }, { "����", "��� ���", "", "", "", "" },
				{ "", "���� ��Ȳ", "", "", "", "" }, { "���", "���� ���", "", "", "", "" }, { "", "ǰ����Һ�", "", "", "", "" },
				{ "�λ�", "���� �� ���� ����", "", "", "", "" } };
		JTable table2 = new JTable(contents2, header2);

		JScrollPane sp2 = new JScrollPane(table2);
		sp2.setBounds(10, 270, 1000, 250);
		p.add(sp2);
		
		
		
		
//		JCheckBox cb = new JCheckBox(); 
//		
//		cb.setHorizontalAlignment(SwingConstants.CENTER); // ������ �������̵� ? �ε�? 
//		
//		DefaultCellEditor checkEditor = new DefaultCellEditor(cb){ 
//			public Component getTableCellEditorComponent(JTable table,Object value,boolean isSelected, int row,int column){ 
//				JCheckBox editor; 
//				editor = (JCheckBox)super.getTableCellEditorComponent(table,value,isSelected,row,column); 
//				return editor;
//			} 
//		};
//		
//		// �����Ͷ� ������ ��� 
//		viwTab.getColumn("���༱��").setCellEditor(checkEditor); 
//		viwTab.getColumn("���༱��").setCellRenderer(new cellCheckRenderer()); 
//		
//		class cellCheckRenderer extends DefaultTableCellRenderer{ 
//			public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected,boolean hasFocus, int row,int column){ 
//				JCheckBox check = new JCheckBox();
//				check.setSelected(((Boolean)value).booleanValue());
//				check.setHorizontalAlignment(SwingConstants.CENTER); 
//				return check; 
//			} 
//		}
		
		
		
		f.getContentPane().add(p);
		f.setVisible(true);
	}
}
