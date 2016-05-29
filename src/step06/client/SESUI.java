package step06.client;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.synth.SynthSeparatorUI;

import javafx.scene.control.ComboBox;
import step06.vo.Staff;
import step06.vo.Human;
import step06.vo.Professor;
import step06.vo.Trainee;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.List;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.SystemColor;

public class SESUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField tF1;
	private JTextField tF2;
	private JTextField tF3;
	private JTextField tF4;
	private JTextField tF5;
	private JTextField tF6;
	private SESClientManager manager ;
	private JButton delete;
	private JComboBox comboBox;
	private DefaultListModel<String> dflm;
	private Human h = null;
	private ArrayList<Human> allInfo ;
/**
 * 등록 :
 	1.컨트롤 박스에서 선택하는 것에 따라 텍스트 필드 editable 바뀜
 	2.TextBox에 입력하고 등록 누르면 입력된 값에 따라 객체 생성해서 매니저로 넘김.

 	검색:
 	1. 주민번호 에다가만 넣고 검색 누르면 해당 계정 전체 출력

 	삭제:
 	1. 주민번호 넣고 삭제 누르면 해당 객체 정보 띄워주고 삭제 되었는지 나타내줌

 	변경:
 	1. 주민번호 넣고 다른 항목 변경할거 넣고 변경 누르면 변경해줌

 	전체 출력:
 	1. 누르면 전체를 등록정보에 보여줌
  	*/

	/**
	 * Create the frame.
	 */
	public SESUI() {
		allInfo =new ArrayList<>();
		try {
			manager = new SESClientManager();
		} catch (UnknownHostException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 716, 352);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("SESchool GUI");
		lblNewLabel.setForeground(new Color(224, 255, 255));
		lblNewLabel.setFont(new Font("한컴 소망 B", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 204, 204));
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(6, 2, 0, 0));
		
		JLabel lB1 = new JLabel("Name");
		lB1.setFont(new Font("AmeriGarmnd BT", Font.BOLD, 16));
		lB1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lB1);
		
		tF1 = new JTextField();
		panel.add(tF1);
		tF1.setColumns(10);
		
		JLabel lB2 = new JLabel("Age");
		lB2.setFont(new Font("AmeriGarmnd BT", Font.BOLD, 16));
		lB2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lB2);
		
		tF2 = new JTextField();
		panel.add(tF2);
		tF2.setColumns(10);
		
		JLabel lB3 = new JLabel("CitizenNo.");
		lB3.setFont(new Font("AmeriGarmnd BT", Font.PLAIN, 16));
		lB3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lB3);
		
		tF3 = new JTextField();
		panel.add(tF3);
		tF3.setColumns(10);
		
		JLabel lB4 = new JLabel("StdNo.");
		lB4.setFont(new Font("AmeriGarmnd BT", Font.PLAIN, 16));
		lB4.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lB4);
		
		tF4 = new JTextField();
		panel.add(tF4);
		tF4.setColumns(10);
		
		JLabel lB5 = new JLabel("Major");
		lB5.setFont(new Font("AmeriGarmnd BT", Font.PLAIN, 16));
		lB5.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lB5);
		
		tF5 = new JTextField();
		panel.add(tF5);
		tF5.setColumns(10);
		
		JLabel lB6 = new JLabel("Field");
		lB6.setFont(new Font("AmeriGarmnd BT", Font.PLAIN, 16));
		lB6.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lB6);
		
		tF6 = new JTextField();
		panel.add(tF6);
		tF6.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(204, 204, 153));
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		
		JLabel lblNewLabel_7 = new JLabel("Infomation");
		lblNewLabel_7.setBackground(new Color(240, 255, 240));
		lblNewLabel_7.setFont(new Font("Arial Unicode MS", Font.BOLD, 18));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_7, BorderLayout.NORTH);
		
		//
		JScrollPane scroll = new JScrollPane();
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_2.add(scroll, BorderLayout.CENTER);
		dflm = new DefaultListModel<String>();
		JList<String> list = new JList<String>();
		scroll.setViewportView(list);
		//panel_2.add(list, BorderLayout.CENTER);
		
		list.setModel(dflm);
		
		
//		dflm.addElement("abc");
//		model.remove(); index로 지우거나, removeall로 다 지우거나
		
		
		
		JButton insert = new JButton("등록");
		insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("insert 버튼 눌림");
				String name  = tF1.getText();
				int age = Integer.parseInt(tF2.getText());
				String jumin = tF3.getText();
				Human h = new Human();
				String major = null;
				String stdNo = null;
				String field = null;
				String t = null;
				if(comboBox.getSelectedItem().equals("--교수--")){//만약에 콤보박스 선택이 교수 였다면 
				major = tF5.getText();
				t = "전공: "+ major;
				h= new Professor(name, jumin, age, major);
				}
				if(comboBox.getSelectedItem().equals("--학생--")){
				stdNo = tF4.getText();
				t = "학번: "+stdNo;
				h= new Trainee(name, jumin, age, stdNo); 
				}
				if(comboBox.getSelectedItem().equals("--직원--")){
				field = tF6.getText();
				t = "업무: "+field;
				h= new Staff(name, jumin, age, field);
				}
				
				boolean result = false;
				try {
					result = manager.insertHuman(h);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dflm.addElement("이름: "+h.getName()+"주민 : "+ h.getJumin()+"나이: "+h.getAge()+t);
			if(result == true) JOptionPane.showMessageDialog(null, "등록 성공");
			else JOptionPane.showMessageDialog(null, "등록 실패");
			//popup 창!
			//JOptionPane.showMessageDialog(null, "information","gkkkk", JOptionPane.INFORMATION_MESSAGE);
			//popup 창 message 제어 	
			}
		});
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"--선택--", "--학생--", "--교수--", "--직원--"}));
		
		comboBox.setFont(new Font("굴림", Font.PLAIN, 14));
		comboBox.setToolTipText("선택");
		comboBox.setBackground(new Color(255, 255, 255));
		
		panel_1.add(comboBox);
		panel_1.add(insert);
		
		comboBox.addActionListener(this);
		
		JButton search = new JButton("검색");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newStart();
				//주민번호에 입력하고 누르면 해당 정보 찾아오기 
				String jumin = tF3.getText();
				Human h= new Human();
				try {
					h = manager.searchHuman(jumin);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dflm.addElement(h.toString());
				
			}
		});
		panel_1.add(search);
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				System.out.println(e.getSource());
			}
		});
		
		list.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {//클릭했을 때 인덱스 번호 받아서 getelementat으로 가져오기 가져온 것을 팝업창으로 뿌리기
				int index = list.getSelectedIndex();
				String info = (String)dflm.getElementAt(index);
				System.out.println(JOptionPane.showInputDialog(null, info)); //텍스트필드 + 확인 + 취소
				int ans = JOptionPane.showConfirmDialog(null, "선택한 정보를 삭제하시겠습니까?"); // 텍스트 필드 + 예 + 아니오 + 취소
				if(ans == 0){
					// 삭제 그럼 인덱스가 같으니까 선택 당시 인덱스 받아와서 그 인덱스에 있는 주민번호 입력해서 삭제. 
					String jumin = allInfo.get(index).getJumin();
					try {
						manager.deleteHuman(jumin);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//전체출력 창 띄워주기 
					dflm.removeAllElements();
					try {
						ArrayList<String> allinfo = manager.printAll();
					for(String st: allinfo){
						dflm.addElement(st);
					}
					
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					
					}
				}
			}
		});
		
		delete = new JButton("삭제");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//list에 있는 한 줄 찍으면 거기서 주민번호 발최하고 삭제 누르면 삭제 
				newStart();
				//주민번호를 입력하고 버튼 누르면 주민번호를 delete 메소드로 넘겨주고 결과 boolean에 따라 출력해줌 deleteHuman 메소드
				String jumin = tF3.getText();
//				Human h= new Human();
			boolean b = false;
//				try {
//					h = manager.searchHuman(jumin);
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				textArea.setText(h.toString()+" 의 정보를 삭제합니다.");
				
				try {
					b = manager.deleteHuman(jumin);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				    System.out.println(b);	
				    if(b==true){
				    	dflm.addElement("삭제 되었습니다.");
				    
				    }else if(b==false){
				    	dflm.addElement("삭제 실패");
				}
			
			}
		});
		panel_1.add(delete);
		
		JButton update = new JButton("변경");
		panel_1.add(update);
		update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
/**
 * 변경 누르면 jumin 만 활성화. 창에다가 변경 가능 항목 띄워주고 주민을 입력하세요.
 * search로 찾아옴. 그 객체의 타입에 따라 변경 가능 필드 열어줌. 
 * 변경할 내용 입력 후 변경 누르기. 
 * 변경 완료 띄워주기  
 */	
				
				String jumin = null;
				if(tF3.getText().equals("")){
					newStart();
					tF1.setEnabled(false);
					tF2.setEnabled(false);
					tF3.setEnabled(true);
					tF4.setEnabled(false);
					tF5.setEnabled(false);
					tF6.setEnabled(false);
					dflm.addElement("변경할 계정의 주민 번호를 누르고 |변경| 버튼을 눌러주세요." );
				
				
				}else if(!tF3.getText().equals("")&&tF4.getText().isEmpty()&&tF5.getText().isEmpty()&&tF6.getText().isEmpty()){
				jumin  = tF3.getText();
				try {
					h = manager.searchHuman(jumin);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
					if(extracted(h) instanceof Professor){//객체가 원래 교수면
						tF5.setEnabled(true);
						dflm.addElement(extracted(h).toString()+"\n변경가능 항목 : [교수- 과목] \n");
					}else if(extracted(h) instanceof Trainee){//객체가 원래 학생이면
						tF4.setEnabled(true);
						dflm.addElement(extracted(h).toString()+"\n변경가능 항목 : [학생- 학번] \n");
					}else if(extracted(h) instanceof Staff){//객체가 원래 직원이면
						tF6.setEnabled(true);
						dflm.addElement(extracted(h).toString()+"\n변경가능 항목 : [직원- 업무] \n");
					}
				
			}
			else if(!tF4.getText().isEmpty()){//변경 누르면 이제 바꾼 애 넣어서 보내주기 Human  객체에 변경 사항 담아서 보내주기
					//학생
				String stdNu=tF4.getText();
				((Trainee)extracted(h)).setHakbun(stdNu);
				boolean a = false;
				try {
					a = manager.changeHuman(extracted(h));
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (a){
					dflm.addElement("변경 완료");
				}else{
					dflm.addElement("변경 불가");
				}
			}else if(!tF5.getText().isEmpty()){
					//교수
				String major = tF5.getText();
				((Professor)extracted(h)).setMajor(major);
				boolean a = false;
				try {
					a = manager.changeHuman(extracted(h));
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (a){
					dflm.addElement("변경 완료");
				}else{
					dflm.addElement("변경 불가");
				}
			}else if(!tF6.getText().isEmpty()){
					//직원
				String field = tF6.getText();
				((Staff)extracted(h)).setField(field);
				boolean a = false;
				try {
					a = manager.changeHuman(extracted(h));
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (a){
					dflm.addElement("변경 완료");
				}else{
					dflm.addElement("변경 불가");
				}
				
				
				}
			}//actionPerformed

			private Human extracted(Human h) {
				return h;
			}
				
		}	
				
		);
		
		JButton printAll = new JButton("전체출력");
		panel_1.add(printAll);
		
		
		printAll.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				newStart();
				dflm.removeAllElements();
				try {
					ArrayList<String> allinfo = manager.printAll();
					for(String st : allinfo ){
						dflm.addElement(st);
					}
				
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
	

		JButton btnNewButton = new JButton("창 초기화");
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				newStart();
				dflm.clear();
			}
		});
		this.setVisible(true);
	}

	
	public void newStart(){
		
		tF1.setEnabled(true);
		tF2.setEnabled(true);
		tF3.setEnabled(true);
		tF4.setEnabled(true);
		tF5.setEnabled(true);
		tF6.setEnabled(true);
		tF1.setText("");
		tF2.setText("");
		tF3.setText("");
		tF4.setText("");
		tF5.setText("");
		tF6.setText("");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	// 분기 처리 1. 콤보 박스 
		//insert 스위치 눌렀을 때 등록하기 
		Object obj = e.getSource();
		
		
	
		if(obj instanceof JComboBox){
		
			System.out.println(comboBox.getSelectedItem());
			if(comboBox.getSelectedItem().equals("--교수--")){
				newStart();
				tF4.setEnabled(false);
				tF5.setEnabled(true);
				tF6.setEnabled(false);
				
			}if(comboBox.getSelectedItem().equals("--학생--")){
				newStart();
				tF4.setEnabled(true);
				tF5.setEnabled(false);
				tF6.setEnabled(false);
				
			}if(comboBox.getSelectedItem().equals("--직원--")){
				newStart();
				tF4.setEnabled(false);
				tF5.setEnabled(false);
				tF6.setEnabled(true);
				
			}
			
			
		}
		
		}
		
	}


