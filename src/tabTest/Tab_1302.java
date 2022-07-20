package tabTest;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
//import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class Tab_1302 implements ActionListener {
//	폴더 그림:40,40
//	Label 사이즈:150,30
//	TextField 사이즈:200,30
//	Button 사이즈:50,30
//	가로 여백 30, 세로 여백 10
	JPanel p;
	UtilDateModel model;
	UtilDateModel model2;
	UtilDateModel model3;
	DefaultTableModel dtm;
	JTable table;
	Dao dao = new Dao();
	Vo vo;
	
	public Tab_1302() {
		p = new JPanel();
		p.setLayout(null);
		
		// icon
		JLabel imageLabel = new JLabel(); // ImageIcon 을 담을 Label 생성

		ImageIcon folder = new ImageIcon("src/images/folder-icon_34416.png");
		Image img = folder.getImage(); // image 크기가 512, 512이므로 40, 40으로 바꾸기 위해 ImageIcon -> Image로 변경
		Image changeimg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon changefolder = new ImageIcon(changeimg);

		imageLabel.setIcon(changefolder);
		p.add(imageLabel);
		imageLabel.setBounds(10, 10, 30, 30);

		Label l1 = new Label("출고 정보");
		l1.setBounds(50, 10, 150, 30);
		p.add(l1);
		
		// Label 추가
		Label[] label = new Label[4];
		String[] labelName = {"기간", "출고일자", "품목코드", "품목명"};

		for (int i = 0; i < label.length; i++) {
			label[i] = new Label(labelName[i]);
			p.add(label[i]);
		}
		
		label[0].setBounds(10, 50, 150, 30);
		label[1].setBounds(10, 130, 150, 30);
		label[2].setBounds(380, 50, 150, 30);
		label[3].setBounds(380, 90, 150, 30);

		// JDatePicker 추가
		model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		datePicker.setBounds(170, 50, 200, 30);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date todayDate = new Date();
		String today = sdf.format(todayDate);

		String[] date = today.split("-");
		int dateY = Integer.parseInt(date[0]);
		int dateM = Integer.parseInt(date[1]) - 1;
		int dateD = Integer.parseInt(date[2]);
		model.setDate(dateY, dateM, dateD);
		model.setSelected(true);

		p.add(datePicker);

		// JDatePicker 추가(2)
		model2 = new UtilDateModel();
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
		JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2);
		datePicker2.setBounds(170, 90, 200, 30);

		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		Date todayDate2 = new Date();
		String today2 = sdf2.format(todayDate2);

		String[] date2 = today2.split("-");
		int dateY2 = Integer.parseInt(date2[0]);
		int dateM2 = Integer.parseInt(date2[1]) - 1;
		int dateD2 = Integer.parseInt(date2[2]);
		model2.setDate(dateY2, dateM2, dateD2);
		model2.setSelected(true);

		p.add(datePicker2);
		
		// JDatePicker 추가(3)
		model3 = new UtilDateModel();
		JDatePanelImpl datePanel3 = new JDatePanelImpl(model3);
		JDatePickerImpl datePicker3 = new JDatePickerImpl(datePanel3);
		datePicker3.setBounds(170, 130, 200, 30);
		
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
		Date todayDate3 = new Date();
		String today3 = sdf3.format(todayDate3);
		
		String[] date3 = today3.split("-");
		int dateY3 = Integer.parseInt(date3[0]);
		int dateM3 = Integer.parseInt(date3[1]) - 1;
		int dateD3 = Integer.parseInt(date3[2]);
		model3.setDate(dateY3, dateM3, dateD3);
		model3.setSelected(true);
		
		p.add(datePicker3);

		// TextField 추가
		TextField pctf = new TextField();
		pctf.setBounds(540, 50, 200, 30);
		p.add(pctf);

		TextField pntf = new TextField();
		pntf.setBounds(540, 90, 200, 30);
		p.add(pntf);

		// Button 추가
		Button pcb = new Button("…");
		pcb.setBounds(750, 50, 30, 30);
		p.add(pcb);
		/*
		 * 버튼을 TextField 안으로 넣을수 있는지 체크
		 */

		Button b1 = new Button("조회");
		b1.setBounds(790, 50, 50, 30);
		p.add(b1);

		Button b2 = new Button("출고처리");
		b2.setBounds(10, 170, 50, 30);
		p.add(b2);

		// Table 추가
		String[] header = { "", "순번", "offer번호", "거래처", "품목코드", "품목명", "단위", "수량" };

		dtm = new DefaultTableModel(header, 0);

		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(10, 210, 1000, 500);
		p.add(sp);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
