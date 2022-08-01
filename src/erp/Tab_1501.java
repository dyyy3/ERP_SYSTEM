package erp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;
import javax.swing.table.*;

public class Tab_1501 implements ActionListener {
//	폴더 그림 : 40, 40
//	Label 사이즈 : 150, 30
//	TextField 사이즈 : 200, 30
//	Button 사이즈 : 50, 30
//	가로 여백 30, 세로 여백 10
	
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
		public Component getTableCellRendererComponent // 셀렌더러
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
		JLabel imageLabel = new JLabel(); // ImageIcon 을 담을 Label 생성

		ImageIcon folder = new ImageIcon("src/images/folder-icon_34416.png");
		Image img = folder.getImage(); // image 크기가 512, 512이므로 40, 40으로 바꾸기 위해 ImageIcon -> Image로 변경
		Image changeimg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon changefolder = new ImageIcon(changeimg);
		
		imageLabel.setIcon(changefolder);
		p.add(imageLabel);
		imageLabel.setBounds(10, 10, 30, 30);
		
		Label l1 = new Label("사원 등록");
		l1.setBounds(50, 10, 150, 30);
		p.add(l1);
		
		// Label
		Label[] label = new Label[4];
		String[] labelName = {
				"사원코드(ID)", "이름",
				"password", "부서"};

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
		
		tf[0].setBounds(170, 50, 200, 30); // 사원코드(ID)
		tf[1].setBounds(170, 90, 200, 30); // 이름
		tf[2].setBounds(540, 50, 200, 30); // password
		tf[3].setBounds(540, 90, 200, 30); // 부서
		
		// icon
		JLabel imageLabel2 = new JLabel(); // ImageIcon 을 담을 Label 생성

		ImageIcon folder2 = new ImageIcon("src/images/folder-icon_34416.png");
		Image img2 = folder2.getImage(); // image 크기가 512, 512이므로 40, 40으로 바꾸기 위해 ImageIcon -> Image로 변경
		Image changeimg2 = img2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon changefolder2 = new ImageIcon(changeimg2);

		imageLabel2.setIcon(changefolder2);
		p.add(imageLabel2);
		imageLabel2.setBounds(10, 130, 30, 30);
		
		Label l2 = new Label("권한 설정");
		l2.setBounds(50, 130, 150, 30);
		p.add(l2);
		
		// Button
		Button[] button = new Button[2];
		String[] buttonName = {
				"등록", "조회"
//				"등록", "부서코드 조회", "조회", "수정"
		};

		for(int i=0; i<button.length; i++) {
			button[i] = new Button(buttonName[i]);
			button[i].addActionListener(this);
			p.add(button[i]);
		}
		
		button[0].setBounds(750, 50, 50, 30);
//		button[1].setBounds(750, 90, 100, 30); // 부서코드 조회
		button[1].setBounds(10, 170, 50, 30);
//		button[2].setBounds(10, 170, 50, 30);
//		button[3].setBounds(70, 170, 50, 30); // 수정
		
		// 조회 table 추가
		String[] header1 = {"사원코드(ID)", "부서코드", "부서명", "이름", "password"};
		dtm1 = new DefaultTableModel(header1, 0);
		table1 = new JTable(dtm1);
		table1.setFillsViewportHeight(true); //컨테이너의 전체 높이를 테이블이 전부 사용하도록 설정

		JScrollPane sp1 = new JScrollPane(table1);
		sp1.setBounds(10, 210, 1000, 55);
		
		String[] contents1 = {"", "", "", "", ""};
		dtm1.addRow(contents1);

		// 열 너비 조정
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

		// 행 높이 조정
		table1.setRowHeight(0, 30);
		
		p.add(sp1);
		
		// 권한 table 추가
		table2 = new JTable(model);
		table2.setFillsViewportHeight(true); //컨테이너의 전체 높이를 테이블이 전부 사용하도록 설정
		
		JScrollPane sp2 = new JScrollPane(table2);
		sp2.setBounds(10, 270, 1000, 295);
		
		String[] header2 = {"대메뉴", "중메뉴", "읽기", "쓰기", "수정", "삭제"};
		
		for(int i=0; i<header2.length; i++) {
			model.addColumn(header2[i]);
		}
		
		String[][] contents2 = {{"품목", "품목코드 등록"},
								{"무역", "수입 offer등록"},
								{"", "수입원가 등록"},
								{"자재", "입고 등록"},
								{"", "출고 등록"},
								{"", "재고 현황"},
								{"결산", "원가 계산"},
								{"", "품목수불부"},
								{"인사", "계정 및 권한 관리"}
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
		
		// 열 너비 조정
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
		 
		// 행 높이 조정
		for(int i=0; i<table2.getRowCount(); i++) {
			table2.setRowHeight(i, 30);
		}
		
		p.add(sp2);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "등록" :
			// 빈칸 체크
			boolean checkUserInfoNull = true;
			
			if(tf[0].getText() == null || tf[0].getText().equals("")) {
				new ErrorMessageDialog("사원코드(ID)를 입력하세요.", "계정 및 권한 관리");
				checkUserInfoNull = false;
				break;
			}
			if(tf[1].getText() == null || tf[1].getText().equals("")) {
				new ErrorMessageDialog("이름을 입력하세요.", "계정 및 권한 관리");
				checkUserInfoNull = false;
				break;
			}
			if(tf[2].getText() == null || tf[2].getText().equals("")) {
				new ErrorMessageDialog("password를 입력하세요.", "계정 및 권한 관리");
				checkUserInfoNull = false;
				break;
			}
			if(tf[3].getText() == null || tf[3].getText().equals("")) {
				new ErrorMessageDialog("부서코드를 입력하세요.", "계정 및 권한 관리");
				checkUserInfoNull = false;
				break;
			}
			
			// user info 저장
			boolean tryInsertUserInfo = false;
			
			if(checkUserInfoNull == true) {
				String user_id = tf[0].getText(); // 사원코드(ID)
				String user_pw = tf[2].getText(); // password
				int dept_id = Integer.parseInt(tf[3].getText()); // 부서코드
				String user_name = tf[1].getText(); // 이름
				
				vo = new Vo(user_id, user_pw, dept_id, user_name);
				tryInsertUserInfo = dao.insertUserInfo(vo);
			}
			
			if(tryInsertUserInfo == true) {
				new ErrorMessageDialog("사원 정보가 등록되었습니다.", "계정 및 권한 관리");
				
//				// 다음에 수행할 작업이 권한 설정이므로 권한 테이블에 저장된 정보 set
//				vo = new Vo("user_id", tf[0].getText());
//				String[] result = dao.selectAllUserInfoAndDepartmentInfoWheretwoFields(vo);
//
//				dtm1.setValueAt(result[0], 0, 0);
//				dtm1.setValueAt(result[2], 0, 1);
//				dtm1.setValueAt(result[5], 0, 2);
//				dtm1.setValueAt(result[3], 0, 3);
//				dtm1.setValueAt(result[1], 0, 4);
				
				// 메인 화면에 대한 접근 권한 1로 설정
				String user_id = tf[0].getText(); // 사원코드(ID)
				vo = new Vo(user_id, "UI-A-1000", 1, 1, 1, 1);
//				INSERT INTO PERMISSION (USER_ID, SCREEN_ID, R, C, U, D) VALUES ('201804003', 'UI-A-1000', 1, 1, 1, 1)
				dao.insertPermission(vo);
				
				// dept_id에 따라 부서별 기본 접근 권한 설정
				int dept_id = Integer.parseInt(tf[3].getText()); // 부서코드
				switch(dept_id) {
				case 0 : // 마스터
					vo = new Vo(user_id, "UI-A-1101", 1, 1, 1, 1); // 품목코드 등록
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1201", 1, 1, 1, 1); // 수입offer 등록
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1202", 1, 1, 1, 1); // 수입원가 등록
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1301", 1, 1, 1, 1); // 입고 등록
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1302", 1, 1, 1, 1); // 출고 등록
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1303", 1, 0, 1, 1); // 재고 현황
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1401", 1, 0, 1, 1); // 원가 계산
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1402", 1, 0, 1, 1); // 품목수불부
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1501", 1, 1, 1, 1); // 인사관리
					dao.insertPermission(vo);
					break;
				case 10 : // 구매
					vo = new Vo(user_id, "UI-A-1101", 1, 1, 1, 1); // 품목코드 등록
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1201", 1, 1, 1, 1); // 수입offer 등록
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1202", 1, 1, 1, 1); // 수입원가 등록
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1301", 1, 1, 1, 1); // 입고 등록
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1302", 1, 0, 0, 0); // 출고 등록
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1303", 1, 0, 0, 0); // 재고 현황
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1401", 1, 0, 0, 0); // 원가 계산
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1402", 1, 0, 0, 0); // 품목수불부
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1501", 0, 0, 0, 0); // 인사관리
					dao.insertPermission(vo);
					break;
				case 20 : // 물류 
					vo = new Vo(user_id, "UI-A-1101", 0, 0, 0, 0); // 품목코드 등록
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1201", 0, 0, 0, 0); // 수입offer 등록
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1202", 0, 0, 0, 0); // 수입원가 등록
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1301", 1, 0, 0, 0); // 입고 등록
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1302", 1, 1, 1, 1); // 출고 등록
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1303", 1, 0, 0, 0); // 재고 현황
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1401", 0, 0, 0, 0); // 원가 계산
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1402", 0, 0, 0, 0); // 품목수불부
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1501", 0, 0, 0, 0); // 인사관리
					dao.insertPermission(vo);
					break;
				case 30 : // 결산
					vo = new Vo(user_id, "UI-A-1101", 0, 0, 0, 0); // 품목코드 등록
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1201", 0, 0, 0, 0); // 수입offer 등록
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1202", 0, 0, 0, 0); // 수입원가 등록
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1301", 1, 0, 0, 0); // 입고 등록
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1302", 0, 0, 0, 0); // 출고 등록
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1303", 1, 0, 0, 0); // 재고 현황
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1401", 1, 0, 0, 0); // 원가 계산
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1402", 1, 0, 0, 0); // 품목수불부
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1501", 0, 0, 0, 0); // 인사관리
					dao.insertPermission(vo);
					break;
				case 40 : // 인사
					vo = new Vo(user_id, "UI-A-1101", 0, 0, 0, 0); // 품목코드 등록
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1201", 0, 0, 0, 0); // 수입offer 등록
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1202", 0, 0, 0, 0); // 수입원가 등록
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1301", 0, 0, 0, 0); // 입고 등록
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1302", 0, 0, 0, 0); // 출고 등록
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1303", 1, 0, 0, 0); // 재고 현황
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1401", 0, 0, 0, 0); // 원가 계산
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1402", 0, 0, 0, 0); // 품목수불부
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1501", 1, 1, 1, 0); // 인사관리
					dao.insertPermission(vo);
					break;
				case 50 : // 영업
					vo = new Vo(user_id, "UI-A-1101", 0, 0, 0, 0); // 품목코드 등록
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1201", 0, 0, 0, 0); // 수입offer 등록
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1202", 0, 0, 0, 0); // 수입원가 등록
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1301", 0, 0, 0, 0); // 입고 등록
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1302", 0, 0, 0, 0); // 출고 등록
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1303", 1, 0, 0, 0); // 재고 현황
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1401", 0, 0, 0, 0); // 원가 계산
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1402", 0, 0, 0, 0); // 품목수불부
					dao.insertPermission(vo);
					vo = new Vo(user_id, "UI-A-1501", 0, 0, 0, 0); // 인사관리
					dao.insertPermission(vo);
					break;
				}
				
				vo = new Vo();
				
				
				
			}else {
				new ErrorMessageDialog("이미 등록된 사원 코드입니다.", "계정 및 권한 관리");
			}
			
			break;
			
		case "부서코드 조회" :
			break;
			
		case "조회" :
			// 빈칸 체크
			boolean checkUserCodeNull = true;
			
			if(dtm1.getValueAt(0, 0) == null || dtm1.getValueAt(0, 0).equals("")) {
				new ErrorMessageDialog("사원코드(ID)를 입력하세요.", "계정 및 권한 관리");
				checkUserCodeNull = false;
				break;
			}
			
			// user_info와 department_info join 테이블, permission 테이블 값 읽어오기
			if(checkUserCodeNull == true) {
				String user_id = dtm1.getValueAt(0, 0).toString();
				vo = new Vo("user_id", user_id);
				String[] result = dao.selectAllUserInfoAndDepartmentInfoWheretwoFields(vo);

				dtm1.setValueAt(result[2], 0, 1); // 부서코드
				dtm1.setValueAt(result[5], 0, 2); // 부서명
				dtm1.setValueAt(result[3], 0, 3); // 이름
				dtm1.setValueAt(result[1], 0, 4); // password
				
				vo = new Vo("user_id", user_id, "screen_id");
//				SELECT * FROM PERMISSION WHERE USER_ID = '202201000' ORDER BY SCREEN_ID
				String[][] result2 = dao.selectAllPermissionWhere(vo);
				
				for(int i=1; i<result2.length; i++) { // i=0 : 메인화면
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
			
//		case "수정" :
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
//				// permission 테이블에서 screen_id와 user_id로 select
//				vo = new Vo("permission", "user_id", "user_id", user_id, "screen_id", screenID[i]);
//				String result = dao.selectOneFieldWhereTwoFields(vo);
//				
//				// 결과값이 없으면 insert
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
//				// 결과값이 있으면 update
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
//				new ErrorMessageDialog("수정되었습니다.", "계정 및 권한 관리");
//			}else {
//				new ErrorMessageDialog("수정 실패하였습니다.", "계정 및 권한 관리");
//			}
//			break;
		}
	}
}
