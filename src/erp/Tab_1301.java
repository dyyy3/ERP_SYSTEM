package erp;

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

public class Tab_1301 implements ActionListener, ItemListener {
//	폴더 그림:40,40
//	Label 사이즈:150,30
//	TextField 사이즈:200,30
//	Button 사이즈:50,30
//	가로 여백 30, 세로 여백 10
	JPanel p;
	Choice ch;
	TextField[] tf;
	Button plb, b1, b2;
	UtilDateModel model;
	UtilDateModel model2;
	DefaultTableModel dtm;
	Dao dao = new Dao();
	Vo vo;
	
	public Tab_1301() {
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

		Label l1 = new Label("입고 정보");
		l1.setBounds(50, 10, 150, 30);
		p.add(l1);
		
		// Label 추가
		Label[] label = new Label[4];
		String[] labelName = {"기간", "offer번호", "거래처", "품목코드"};

		for (int i = 0; i < label.length; i++) {
			label[i] = new Label(labelName[i]);
			p.add(label[i]);
		}
		
		label[0].setBounds(10, 50, 150, 30);
		label[1].setBounds(380, 50, 150, 30);
		label[2].setBounds(380, 90, 150, 30);
		label[3].setBounds(380, 130, 150, 30);
		
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
		
		// Choice 추가
		ch = new Choice();
		ch.setBounds(540, 90, 200, 30); // 거래처
		ch.addItemListener((ItemListener) this);
		p.add(ch);
		
		vo = new Vo("client", "client_name");
		String[] clientList = dao.selectOneField(vo);
		for(int i=0; i<clientList.length; i++ ) {
			ch.add(clientList[i]);
		}
		
		// TextField 추가
		tf = new TextField[2];
		
		for(int i=0; i<tf.length; i++) {
			tf[i] = new TextField();
			p.add(tf[i]);
			tf[i].addActionListener(this);
		}
		tf[0].setBounds(540, 50, 200, 30); // offer번호
		tf[1].setBounds(540, 130, 200, 30); // 품목코드
		
		// Button 추가
		plb = new Button("…");
		plb.setBounds(750, 130, 30, 30);
		p.add(plb);
		/*
		버튼을 TextField 안으로 넣을수 있는지 체크
		*/
		
		b1 = new Button("조회");
		b1.setBounds(750, 50, 50, 30);
		b1.addActionListener(this);
		p.add(b1);

		b2 = new Button("입고처리");
		b2.setBounds(10, 130, 50, 30);
		b2.addActionListener(this);
		p.add(b2);
		
		// Table 추가
		String[] header = {
				"", "순번", "offer번호", "거래처", "품목코드",
				"품목명", "단위", "수량"
		};
		
		/*
		checkbox 추가되어야함
		*/
		
		dtm = new DefaultTableModel(header, 0);		
		JTable table = new JTable(dtm);
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(10, 170, 1000, 500);
		p.add(sp);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "조회" :
			String date1 = model.getYear() + "-" + (model.getMonth() + 1) + "-" + model.getDay();
			String date2 = model2.getYear() + "-" + (model2.getMonth() + 1) + "-" + model2.getDay();
			String offer_num = tf[0].getText();
			String client_name = ch.getSelectedItem();
			String product_code = tf[1].getText();
			
			// 기간 + offer번호로 조회
			// 기간 + 거래처로 조회
			// 기간 + 품목코드로 조회
			break;
		case "입고처리" :
			break;
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
}
