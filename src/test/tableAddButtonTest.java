package test;

import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;

public class tableAddButtonTest extends JDialog {
    
    private Object[] objColNms = new Object[] { "Col1", "Col2", "Del" };
 
    public static void main(String[] args) {
        new tableAddButtonTest();
    }
 
    private JFrame jf;
    private DefaultTableModel dtm;
    private JTable jtable;
    private JScrollPane jsp;
 
    public tableAddButtonTest() {
        jf = new JFrame("JTable Add Delete Button");
        jf.setLocationRelativeTo(null);
        jf.setSize(300, 300);
 
        dtm = new DefaultTableModel(
                new Object[][] { { "data1", "data2", "data3" }, { "data4", "data5", "data6" }, { "data7", "data8", "data9" } },
                objColNms);
        jtable = new JTable(dtm);
        jsp = new JScrollPane(jtable);
 
        jtable.getColumnModel().getColumn(2).setCellRenderer(new TableCell());
        jtable.getColumnModel().getColumn(2).setCellEditor(new TableCell());
 
        jf.add(jsp);
 
        jf.setVisible(true);
    }
 
    @SuppressWarnings("rawtypes")
    public void JTableRemoveRow() {
        int row = jtable.getSelectedRow();
        if (row == -1)
            return;
 
        DefaultTableModel model = (DefaultTableModel) jtable.getModel();
        model.removeRow(row);
 
        int rowCnt = jtable.getRowCount();
 
        if (rowCnt > 0) {
            Vector vector = model.getDataVector();
            Object[][] objData = new Object[vector.size()][((Vector) vector.get(0)).size()];
            for (int i = 0; i < vector.size(); i++) {
                Vector vec = (Vector) vector.get(i);
                for (int j = 0; j < vec.size(); j++) {
                    objData[i][j] = vec.get(j);
                }
            }
 
            DefaultTableModel clonModel = new DefaultTableModel(objData, objColNms);
            JTable clonTable = new JTable(clonModel);
 
            jf.getContentPane().removeAll();
 
            jtable = clonTable;
            jsp = new JScrollPane(jtable);
            jtable.getColumnModel().getColumn(2).setCellRenderer(new TableCell());
            jtable.getColumnModel().getColumn(2).setCellEditor(new TableCell());
 
            jf.add(jsp);
            jf.revalidate();
            jf.repaint();
        } else {
            DefaultTableModel clonModel = new DefaultTableModel(null, objColNms);
            JTable clonTable = new JTable(clonModel);
 
            jf.getContentPane().removeAll();
 
            jtable = clonTable;
            jsp = new JScrollPane(jtable);
 
            jf.add(jsp);
            jf.revalidate();
            jf.repaint();
        }
    } // end public void JTableRemoveRow()
 
    class TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
        JButton jb;
 
        public TableCell() {
            jb = new JButton("Del");
            jb.addActionListener(e -> {
                JTableRemoveRow();
            });
        }
 
        @Override
        public Object getCellEditorValue() {
            return null;
        }
 
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            return jb;
        }
 
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                int column) {
            return jb;
        }
    } // end class TableCell extends AbstractCellEditor implements TableCellEditor,
        // TableCellRenderer
}
