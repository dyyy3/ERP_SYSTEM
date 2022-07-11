package tabTest;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Calendar;
//import javax.swing.event.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.toedter.calendar.JDateChooser;

import net.sourceforge.jdatepicker.impl.*;
import com.toedter.calendar.JYearChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JCalendar;

public class Tab_1201 implements ActionListener {
//	폴더 그림 : 40, 40
//	Label 사이즈 : 150, 30
//	TextField 사이즈 : 200, 30
//	가로 여백 30, 세로 여백 10
	
	JPanel p;
//	JDateChooser dateChooser;
	UtilDateModel model;
	Button b;
	
	public Tab_1201() {
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

		Label l1 = new Label("수입offer 정보");
		l1.setBounds(50, 10, 150, 30);
		p.add(l1);
		
		// Label 추가
		Label[] label = new Label[5];
		String[] labelName = {"offer번호", "일자", "통화", "거래처", "가격조건"};
		
		for(int i=0; i<label.length; i++) {
			label[i] = new Label(labelName[i]);
			p.add(label[i]);
		}
		
		label[0].setBounds(10, 50, 150, 30);
		label[1].setBounds(10, 90, 150, 30);
		label[2].setBounds(380, 50, 150, 30);
		label[3].setBounds(380, 90, 150, 30);
		label[4].setBounds(10, 130, 150, 30);
		
		// TextField 추가
		TextField tf = new TextField();
		tf.setBounds(170, 50, 200, 30);
		p.add(tf);
		
		// DateChooser를 대신할 choice
		Choice chYear = new Choice();
		Choice chMonth = new Choice();
		Choice chDay = new Choice();
		
		for(int i=1; i<13; i++) {
			f({
				
			})
			chMonth.add("0" + i);
		}
		
		chYear.setBounds(170, 90, 80, 30);
		chMonth.setBounds(260, 90, 50, 30);
		chDay.setBounds(320, 90, 50, 30);
		// 세개의 choice 총 길이 (170, 90, 200, 30)
		
		p.add(chYear);
		p.add(chMonth);
		p.add(chDay);
		
		// DateChooser 추가
//		dateChooser = new JDateChooser();
//		dateChooser.setBounds(10, 250, 150, 30);
//		dateChooser.setDateFormatString("yyyy-mm-dd");
//		Date now = new Date();
//		dateChooser.setDate(now);
//		p.add(dateChooser);

		// 날짜 선택 버튼
//		b = new Button("선택");
//		b.setBounds(400, 250, 50, 30);
//		b.addActionListener(this);
//		p.add(b);
		
		// Choice 추가
		Choice[] ch = new Choice[3];
		
		for(int i=0; i<ch.length; i++) {
			ch[i] = new Choice();
			p.add(ch[i]);
//			ch[i].addItemListener((ItemListener)this); // 에러
		}
		
		Dao dao = new Dao();
		
		/*
		각 choice마다 값을 불러오는 코드 작성 필요
		*/
		
//		Dao dao = new Dao();
//		
//		for(int i=0; i<tableName.length; i++) {
//			Vo vo = new Vo(tableName[i]);
//			String[] result = dao.select(vo);
//			for(int j=0; j<result.length; j++) {
//				ch[i].add(result[j]);
//			}
//		}
		
		ch[0].setBounds(540, 50, 200, 30);
		ch[1].setBounds(540, 90, 200, 30);
		ch[2].setBounds(170, 130, 200, 30);
		
		// Table 추가
		String[] header = {"", "순번", "품목코드", "품목명", "단위", "수량", "단가", "금액"};
		String[][] contents = {
				{"", "", "", "", "", "", "", ""}
		};
		/*
		'+'버튼이 클릭될떄마다 table의 행이 추가되어야하므로, String 2차원 배열 대신 객체 배열로 넣을수 있는지 테스트해보기
		*/
		JTable table = new JTable(contents, header);
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(10, 170, 1000, 50);
		p.add(sp);
		

		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
//		String theDate = dateFormat.format(Calendar.getInstance());
//		System.out.println(theDate);
	}
}
