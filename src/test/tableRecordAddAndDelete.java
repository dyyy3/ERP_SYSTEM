package test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class tableRecordAddAndDelete extends JFrame implements MouseListener,KeyListener {
	
	private final String[] labels= {"Name","Age","Sex","Korean","English","Math"};
	private JTextField []fields=new JTextField[6];
	
	private JScrollPane scrolledTable;
	private JTable table;
	
	private JButton addBtn;
	private JButton delBtn;
	
	public static void main(String[] args) {
		new tableRecordAddAndDelete("test");
	}
	
	public tableRecordAddAndDelete(String title) {
		
		this.setLayout(new BorderLayout(10,10));
		
		JPanel topPanel=new JPanel(new GridLayout(6,4,10,5));
		
		for(int i=0;i<6;i++) {
			topPanel.add(new JLabel(labels[i]));
			fields[i]=new JTextField(30);
			topPanel.add(fields[i]);
		}
		topPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		this.add("North",topPanel);				//���� ���� Panel ����
		
		String header[]= {"Name","Age","Sex","Korean","English","Math"};
		DefaultTableModel model=new DefaultTableModel(header,0);	//header�߰�, ���� 0�� ����
		
		table=new JTable(model);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrolledTable=new JScrollPane(table);	//��ũ�� �� �� �ֵ��� JScrollPane ����
		scrolledTable.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));	//�ʹ� �پ��־ �����ڸ� ���(padding)
		this.add("Center",scrolledTable);	//����� JTable �߰�
		
		
		
		JPanel rightPanel=new JPanel(new GridLayout(5,1,10,10));
		
		addBtn=new JButton("���ڵ� �߰�");
		delBtn=new JButton("���ڵ� ����");			
		
		
		rightPanel.add(addBtn);
		rightPanel.add(delBtn);
		rightPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		this.add("East",rightPanel);		//�����ʿ� ��ư�� �߰�
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(620,400);
		this.setLocationRelativeTo(null);	//â ��� ��ġ
		this.setVisible(true);
		
		
		//�̺�Ʈ �߰�
		addBtn.addMouseListener(this);	//�߰� ó��
		delBtn.addMouseListener(this);	//���� ó��
		for(int i=0;i<6;i++)
			fields[i].addKeyListener(this);	//���� ó��
		table.addMouseListener(this);	//�� �б� ó��
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	//KeyListener Overrides
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
	
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}