package step06.client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.BevelBorder;

import step06.vo.Human;
import step06.vo.Professor;
import step06.vo.Staff;
import step06.vo.Trainee;

public class SESGUI extends JFrame implements ActionListener {

	private JFrame frame;
	private JTextField tf_name;
	private JTextField tf_age;
	private JTextField tf_citizenNo;
	private JTextField tf_major;
	private JTextField tf_stdNo;
	private JTextField tf_field;
	private DefaultListModel<Human> dflm = new DefaultListModel<>();
	private JButton btn_insert;
	private JButton btn_update;
	private JButton btn_search;
	private JButton btn_delete;
	private JButton btn_ok;
	private JButton btn_cancel;
	private SESClientManager mg;
	private String flag;
	private JList<Human> list ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SESGUI window = new SESGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public SESGUI() throws UnknownHostException, IOException {
		 mg = new SESClientManager();
		initialize();
		updateList();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 680, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, Color.DARK_GRAY));
		panel.setBackground(Color.ORANGE);
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel_6 = new JLabel("Soft Engineer School Manager");
		lblNewLabel_6.setFont(new Font("Lucida Fax", Font.BOLD, 18));
		panel.add(lblNewLabel_6);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new GridLayout(6, 2, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		
		JLabel lblNewLabel = new JLabel("Name");
		panel_4.add(lblNewLabel);
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5);
		
		tf_name = new JTextField();
		panel_5.add(tf_name);
		tf_name.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		panel_1.add(panel_6);
		
		JLabel lblNewLabel_1 = new JLabel("Age");
		panel_6.add(lblNewLabel_1);
		
		JPanel panel_7 = new JPanel();
		panel_1.add(panel_7);
		
		tf_age = new JTextField();
		panel_7.add(tf_age);
		tf_age.setColumns(10);
		
		JPanel panel_8 = new JPanel();
		panel_1.add(panel_8);
		
		JLabel lblNewLabel_2 = new JLabel("Citizen.No");
		panel_8.add(lblNewLabel_2);
		
		JPanel panel_9 = new JPanel();
		panel_1.add(panel_9);
		
		tf_citizenNo = new JTextField();
		panel_9.add(tf_citizenNo);
		tf_citizenNo.setColumns(10);
		
		JPanel panel_10 = new JPanel();
		panel_1.add(panel_10);
		
		JLabel lblNewLabel_3 = new JLabel("Major");
		panel_10.add(lblNewLabel_3);
		
		JPanel panel_11 = new JPanel();
		panel_1.add(panel_11);
		
		tf_major = new JTextField();
		panel_11.add(tf_major);
		tf_major.setColumns(10);
		
		JPanel panel_12 = new JPanel();
		panel_1.add(panel_12);
		
		JLabel lblNewLabel_4 = new JLabel("Std.No");
		panel_12.add(lblNewLabel_4);
		
		JPanel panel_14 = new JPanel();
		panel_1.add(panel_14);
		
		tf_stdNo = new JTextField();
		panel_14.add(tf_stdNo);
		tf_stdNo.setColumns(10);
		
		JPanel panel_15 = new JPanel();
		panel_1.add(panel_15);
		
		JLabel lblNewLabel_5 = new JLabel("Field");
		panel_15.add(lblNewLabel_5);
		
		JPanel panel_16 = new JPanel();
		panel_1.add(panel_16);
		
		tf_field = new JTextField();
		panel_16.add(tf_field);
		tf_field.setColumns(10);
		
		JPanel panel_13 = new JPanel();
		frame.getContentPane().add(panel_13, BorderLayout.EAST);
		
		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane);
		
		list = new JList();
		scrollPane.setViewportView(list);
		list.setModel(dflm);
		list.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
				super.mouseClicked(e);
				//선택된정보가 각 필드에 입력된다. 
				//TODO: tf모두 비활성화
				tfClear(false);
				Human h = list.getSelectedValue();
				String name = h.getName();
				tf_name.setText(name);
				String jumin = h.getJumin();
				tf_citizenNo.setText(jumin);
				String age = Integer.toString(h.getAge());
				tf_age.setText(age);
				if(h instanceof Professor){
					String major = ((Professor)h).getMajor();
					tf_major.setText(major);
				}else if(h instanceof Trainee){
					String stdNo = ((Trainee)h).getHakbun();
					tf_stdNo.setText(stdNo);
				}else if(h instanceof Staff){
					String field = ((Staff)h).getField();
					tf_field.setText(field);
				}
				tfSwitch(false);
				
			}
			
		});
		
		JLabel lblInformation = new JLabel("information");
		lblInformation.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblInformation);
		
		JPanel panel_3 = new JPanel();
		frame.getContentPane().add(panel_3, BorderLayout.SOUTH);
		
		JComboBox cb_insert = new JComboBox();
		cb_insert.setModel(new DefaultComboBoxModel(new String[] { "선택","교수", "학생", "직원"}));
		panel_3.add(cb_insert);
		cb_insert.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
			//	System.out.println(cb_insert.getSelectedItem());
			}

		});
		
		btn_insert = new JButton("등록");
		panel_3.add(btn_insert);
		btn_insert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				flag = "insert";
				//TODO: 버튼 반전 메소드 만들어서 붙이기 
				btnSwitch(false);
				//TODO:field 활성화
				String cb_text = (String) cb_insert.getSelectedItem();
				if(cb_text.equals("교수")){
					tf_stdNo.setEditable(false);
					tf_field.setEditable(false);
				}else if(cb_text.equals("학생")){
					tf_major.setEditable(false);
					tf_field.setEditable(false);
				}else if(cb_text.equals("직원")){
					tf_stdNo.setEditable(false);
					tf_major.setEditable(false);
				}
				
			}
		});
		
		 btn_update = new JButton("수정");
		panel_3.add(btn_update);
		btn_update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				flag = "update";
				//수정 가능 필드 활성화(주민번호 제외) 확인 취소 버튼 활성화
			
				tf_name.setEditable(true);
				tf_age.setEditable(true);
				if(tf_major.getText().equals("")&&tf_stdNo.getText().equals("")){
					tf_field.setEditable(true);
					
				}else if(tf_stdNo.getText().equals("")&&tf_field.getText().equals("")){
					tf_major.setEditable(true);
					
				}else if(tf_field.getText().equals("")&&tf_major.getText().equals("")){
				tf_stdNo.setEditable(true);}
				btnSwitch(false);
			}	
			
		});
		
		 btn_search = new JButton("검색");
		panel_3.add(btn_search);
		btn_search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				flag ="search";
				tfSwitch(false);
				tf_citizenNo.setEditable(true);
				btnSwitch(false);
			}
		});
		
		 btn_delete = new JButton("삭제");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag="delete";
				String jumin = tf_citizenNo.getText();
				try {
					boolean  res = mg.deleteHuman(jumin);
					updateList();
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel_3.add(btn_delete);
		
		
		 btn_ok = new JButton("확인");
		 panel_3.add(btn_ok);
		 
		btn_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(flag);
				if(flag.equals("insert")){
				//TODO: 버튼 초기 상태로 되돌리기, 입력필드 비활성화, 등록정보 리스트 갱신
				btnSwitch(true);
				tfSwitch(false);
				//분기 처리 교수, 학생, 직원
				String name = tf_name.getText();
				int age = Integer.parseInt(tf_age.getText());
				String jumin = tf_citizenNo.getText();
				String select= "";
				String cb_text = (String) cb_insert.getSelectedItem();
				Human h = null;
				if(cb_text.equals("교수")){
					select = tf_major.getText();
					h = new Professor(name, jumin, age, select);
					
				}else if(cb_text.equals("학생")){
					select = tf_stdNo.getText();
					h = new Trainee(name, jumin, age, select);
					
				}else if(cb_text.equals("직원")){
					select = tf_field.getText();
					h = new Staff(name, jumin, age, select);
				}
				System.out.println(h.toString());
				try {
					mg.insertHuman(h);
					updateList();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
					
				}
				}else if(flag.equals("search")){
					String jumin = tf_citizenNo.getText();
					try {
						Human h = mg.searchHuman(jumin);
						tf_name.setText(h.getName());
						tf_citizenNo.setText(h.getJumin());
						String age = Integer.toString(h.getAge());
						tf_age.setText(age);
						if(h instanceof Professor){
						String major =((Professor)h).getMajor();
						tf_major.setText(major);
						}else if(h instanceof Staff){
						String field = ((Staff)h).getField();
						tf_field.setText(field);
						}else if(h instanceof Trainee){
						String stdNo = ((Trainee)h).getHakbun();
						tf_stdNo.setText(stdNo);
						}
						dflm.addElement(h);
						System.out.println(h);
						updateList();
					}catch(NullPointerException e2){
						System.out.println("해당 정보 없음.");
						JOptionPane.showMessageDialog(null, "해당 정보 없음");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}else if(flag.equals("update")){
					//TODO: 입력필드 전부 비 활성화 ok
					//등록정보리스트 갱신
					//버튼 초기 상태로 돌림
					
					tfEnable(false);
					String name = tf_name.getText();
					int age = Integer.parseInt(tf_age.getText());
					String jumin = tf_citizenNo.getText();
					String suntaek = null;
					Human h = null;
					if(tf_major.getText().equals("")&&tf_stdNo.getText().equals("")){
						suntaek = tf_field.getText();
						h= new Staff(name, jumin, age, suntaek);
					}else if(tf_stdNo.getText().equals("")&&tf_field.getText().equals("")){
						suntaek = tf_major.getText();
						 h = new Professor(name, jumin, age, suntaek);
					}else if(tf_field.getText().equals("")&&tf_major.getText().equals("")){
						suntaek = tf_stdNo.getText();
						h = new Trainee(name, jumin, age, suntaek);
						}
					try {
						mg.updateHuman(h);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					updateList();
					btnSwitch(true);
					
				}else if(flag.equals("delete")){
					updateList();
				}
			}
		});
		
		 btn_cancel = new JButton("취소");
		panel_3.add(btn_cancel);
		btn_cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tfClear(true);
				btnSwitch(true);
			}
		});
	}

	public void btnSwitch(boolean t){
		//true가 기본값으로 , false 넣으면 ok 랑 cancel 만 활성화
		btn_insert.setEnabled(t);
		btn_update.setEnabled(t);
		btn_search.setEnabled(t);
		btn_delete.setEnabled(t);
		btn_ok.setEnabled(!t);
		btn_cancel.setEnabled(!t);
	}
	public void tfSwitch(boolean t){
		tf_age.setEditable(t);
		tf_citizenNo.setEditable(t);
		tf_field.setEditable(t);
		tf_major.setEditable(t);
		tf_name.setEditable(t);
		tf_stdNo.setEditable(t);
	}
	
	public void tfEnable(boolean t){
		tf_age.setEditable(t);
		tf_citizenNo.setEditable(t);
		tf_field.setEditable(t);
		tf_major.setEditable(t);
		tf_name.setEditable(t);
		tf_stdNo.setEditable(t);
	}
	public void tfClear(boolean t){
		tf_age.setEditable(t);
		tf_citizenNo.setEditable(t);
		tf_field.setEditable(t);
		tf_major.setEditable(t);
		tf_name.setEditable(t);
		tf_stdNo.setEditable(t);
		
		tf_age.setText("");
		tf_citizenNo.setText("");
		tf_field.setText("");
		tf_major.setText("");
		tf_name.setText("");
		tf_stdNo.setText("");
	}
	
	public void updateList(){
			dflm.clear();
			ArrayList<Human> cusList;
			try {
				cusList = mg.printAll();
				for(Human h :cusList){
				dflm.addElement(h);
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				
			
			}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
