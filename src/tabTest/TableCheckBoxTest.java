package tabTest;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class TableCheckBoxTest extends JDialog {

	public static void main(String[] args) {
		new TableCheckBoxTest();
	}

	private JFrame jf;
	private DefaultTableModel dtm;
	private JTable jtable;
	private JScrollPane jsp;
	
	
	public TableCheckBoxTest() {
		jf = new JFrame("���̺� ��ư �߰� �̺�Ʈ");
		jf.setLocationRelativeTo(null);
		jf.setSize(500, 300);
		
		String[][] contents = {
				{ "������1", "������2", "������3" }, 
				{ "������4", "������5", "������6" }, 
				{ "������7", "������8", "������9" }
		};
		String[] header = { "�ʵ�1", "�ʵ�2", "�ʵ�3" };
		dtm = new DefaultTableModel(contents, header);
		
		// �� �� ������ �Ʒ� �� �������� �ٲ� �� ����
//		dtm = new DefaultTableModel(new Object[][] {{ "������1", "������2", "������3" }, { "������4", "������5", "������6" }, { "������7", "������8", "������9" }}, new Object[]{ "�ʵ�1", "�ʵ�2", "�ʵ�3" });
		jtable = new JTable(dtm);
		jsp = new JScrollPane(jtable);

		jtable.getColumnModel().getColumn(0).setCellRenderer(new TableCell());;
		jtable.getColumnModel().getColumn(0).setCellEditor(new TableCell());;
		
		jf.add(jsp);
		
		jf.setVisible(true);
	}	
	
	
	
	class TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer{

		JButton jb;
		
		public TableCell() {
			// TODO Auto-generated constructor stub
			jb = new JButton("��ư");
			
			jb.addActionListener(e -> {
				System.out.println(jtable.getValueAt(jtable.getSelectedRow(), 1));
			});
		
		}
		
		@Override
		public Object getCellEditorValue() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			// TODO Auto-generated method stub
			return jb;
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			// TODO Auto-generated method stub
			return jb;
		}
		
	}

}
