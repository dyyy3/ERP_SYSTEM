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
		
		// 조회 table
		String[] header1 = { "사원코드(ID)", "부서코드", "부서명", "이름", "password" };
		String[][] contents1 = { { "", "", "", "", "" } };
		
		DefaultTableModel tm1 = new DefaultTableModel(contents1, header1);		
		JTable table1 = new JTable(tm1);
//		JTable table1 = new JTable(contents1, header1);

		JScrollPane sp1 = new JScrollPane(table1);
		sp1.setBounds(10, 210, 1000, 50);
		p.add(sp1);
		
		JCheckBox cb1 = new JCheckBox();
		
		tm1.setValueAt(cb1, 0, 0);

		// 권한 table
		String[] header2 = { "대메뉴", "중메뉴", "읽기", "쓰기", "수정", "삭제" };
		String[][] contents2 = { { "품목", "품목코드 등록", "", "", "", "" }, { "무역", "수입offer 등록", "", "", "", "" },
				{ "", "수입원가 등록", "", "", "", "" }, { "", "입고 등록", "", "", "", "" }, { "자재", "출고 등록", "", "", "", "" },
				{ "", "자재 현황", "", "", "", "" }, { "결산", "원가 계산", "", "", "", "" }, { "", "품목수불부", "", "", "", "" },
				{ "인사", "계정 및 권한 관리", "", "", "", "" } };
		JTable table2 = new JTable(contents2, header2);

		JScrollPane sp2 = new JScrollPane(table2);
		sp2.setBounds(10, 270, 1000, 250);
		p.add(sp2);
		
		
		
		
//		JCheckBox cb = new JCheckBox(); 
//		
//		cb.setHorizontalAlignment(SwingConstants.CENTER); // 에디터 오버라이딩 ? 로딩? 
//		
//		DefaultCellEditor checkEditor = new DefaultCellEditor(cb){ 
//			public Component getTableCellEditorComponent(JTable table,Object value,boolean isSelected, int row,int column){ 
//				JCheckBox editor; 
//				editor = (JCheckBox)super.getTableCellEditorComponent(table,value,isSelected,row,column); 
//				return editor;
//			} 
//		};
//		
//		// 에디터랑 랜더러 등록 
//		viwTab.getColumn("예약선택").setCellEditor(checkEditor); 
//		viwTab.getColumn("예약선택").setCellRenderer(new cellCheckRenderer()); 
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
