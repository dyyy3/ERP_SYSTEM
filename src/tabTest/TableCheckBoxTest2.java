package tabTest;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class TableCheckBoxTest2 extends JDialog {

	public static void main(String[] args) {
		new TableCheckBoxTest2();
	}

	private JFrame jf;
	private DefaultTableModel dtm;
	private JTable jtable;
	private JScrollPane jsp;
	
	
	public TableCheckBoxTest2() {
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
		jtable = new JTable(dtm);
		jsp = new JScrollPane(jtable);

		jtable.getColumnModel().getColumn(0).setCellRenderer(new TableCell2());
		jtable.getColumnModel().getColumn(0).setCellEditor(new TableCell2());

		jtable.getColumnModel().getColumn(1).setCellRenderer(new TableCell2());
		jtable.getColumnModel().getColumn(1).setCellEditor(new TableCell2());
		
//		getColumeModel() : Returns the TableColumnModel that contains all column information of this table.
//		getColumn() : Returns the TableColumn object for the column at columnIndex.
//		setCellRenderer() : 
//			void javax.swing.table.TableColumn.setCellEditor(TableCellEditor cellEditor)
//			@BeanProperty(description="The editor to use for cell values.")
//			Sets the editor to used by when a cell in this column is edited.
//		setCellEditor() : 
//			void javax.swing.table.TableColumn.setCellEditor(TableCellEditor cellEditor)
//			@BeanProperty(description="The editor to use for cell values.")
//			Sets the editor to used by when a cell in this column is edited.

		jf.add(jsp);
		
		jf.setVisible(true);
	}	
	
	
	class TableCell2 extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {

		JCheckBox cb;
		
		public TableCell2() {
			cb = new JCheckBox();
			
//			jb.addActionListener(e -> {
//				System.out.println(jtable.getValueAt(jtable.getSelectedRow(), 1));
//			});
		}
		
		@Override
		public Object getCellEditorValue() { // AbstractCellEditor �߻�Ŭ������ �߻�޼���
			return null;
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) { // TableCellEditor �������̽��� �߻�޼���
			return cb;
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) { // TableCellRenderer �������̽��� �߻�޼���
			return cb;
		}
		
	}
}	
