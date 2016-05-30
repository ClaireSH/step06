package step06.server;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import step06.Exeption.DuplicateJuminException;
import step06.Exeption.RecordNotFoundException;
import step06.dao.SESConnectionManager;
import step06.vo.Human;
import step06.vo.Professor;
import step06.vo.Staff;
import step06.vo.Trainee;
import step6.manager.SESchoolManagerImpl;

public class SESServerManager implements SESchoolManagerImpl {

	/**조인해서 각자의 서브 테이블에 연결된 정보를 가지고 온다. 
	 * 
	 * @return
	 */
	public ArrayList<Human> getAllList() {
		//TODO: ArrayList retuen 타입 Human 으로 바꾸기
		ArrayList<Human> allList = new ArrayList<>() ; //배열 생성
		String info = "";
		Connection con  = SESConnectionManager.getConnection();
		String sql = "SELECT h.name , h.type, h.age, h.jumin, p.major, s.field, t.stdno "
				+ "FROM human h, professor p, staff s, trainee t "
				+ "WHERE h.jumin = p.jumin(+) AND h.jumin = s.jumin(+) AND h.jumin = t.jumin(+)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String name = rs.getString(1);
				String type = rs.getString(2);
				int age = rs.getInt(3);
				String jumin = rs.getString(4);
				String major = rs.getString(5);
				String field = rs.getString(6);
				String hakbun = rs.getString(7);
				String gonton = "";
				Human h = null;
				switch (type) {
				case "professor":
					gonton = major;
					h = new Professor(name, jumin, age, gonton);
					break;
				case "trainee":
					gonton = hakbun;
					h = new Trainee(name, jumin, age, gonton);
					break;
				case "staff":
					gonton = field;
					h = new Staff(name, jumin, age, gonton);
					break;

				default:
					break;
				}
				
				allList.add(h);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			SESConnectionManager.close(con);
		}
		
		return allList;
	}
	/**
	 * Humantable에 존재하는 모든 정보를 가져온다. 
	 * 가져와서 type 조사를 하고 그에 따라 분기처리해서 전공, 학번, 분야 가져와서 
	 * 객체 생성하고 배열에 하나씩 넣는다.  
	 * @return ArrayList<Human> reHList
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public ArrayList<Human> getH_List() throws ClassNotFoundException, IOException {
		ArrayList<Human> reHList = new ArrayList<>() ; //배열 생성
		Connection con = SESConnectionManager.getConnection();
		String sql = "SELECT * FROM human ";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String jumin = rs.getString("jumin");
				int age = rs.getInt("age");
				String type = rs.getString("type");
				String name = rs.getString("name");
				System.out.println(jumin+type+name);
				
				Human hu = null;
				String sql1 = "SELECT ";
				PreparedStatement pstmt1 ;
				//ResultSet rs1 = null;
				
				switch(type){
				case "professor":
					sql1 = sql1+" major from professor where jumin = ";
					sql1 = sql1 +"'"+jumin+"'";
					pstmt1 = con.prepareStatement(sql);
					ResultSet rs1 = pstmt1.executeQuery();
					
					if(rs1.next()){
					String slct = rs1.getString(2);
						hu = new Professor(name, jumin, age, slct);
					} 
					
					break;
				case "staff":
					sql1 = sql1+"field from staff where jumin = ";
					sql1 = sql1 +"'"+jumin+"'";
					pstmt1 = con.prepareStatement(sql);
					ResultSet rs2 = pstmt1.executeQuery();
					if(rs2.next()){
						String slct1 = rs2.getString(2);
						hu = new Staff(name, jumin, age, slct1);
					} 
					
					break;
				case "trainee":
					
					sql1 = sql1+"hakbun from trainee where jumin = ";
					sql1 = sql1 +"'"+jumin+"'";
					pstmt1 = con.prepareStatement(sql);
					ResultSet rs3 = pstmt1.executeQuery();
					if(rs3.next()){
					String slct2 = rs3.getString(2);
						hu = new Trainee(name, jumin, age, slct2);
					} 
					
					break;
				default:
					break;
				}//switch
				reHList.add(hu);
			}//while
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			SESConnectionManager.close(con);
		}
		return reHList;
	}

	

	/**
	 * 1.해당 아이디가 존재하는지 검사
	 * 없다면 insertquery 있다면 이미존재하는 아이디 익셉션 발생
	 */
	@Override
	public boolean insertHuman(Human h) throws DuplicateJuminException, IOException{
		boolean bool= false;
		if(searchHuman(h.getJumin())!=null){ 
			throw new DuplicateJuminException();}
		Connection con = SESConnectionManager.getConnection();
		String sql = "INSERT INTO human VALUES( ?, ?, ?, ?)";
		int type = 0;
		String slct = null;
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, h.getJumin());
			if(h instanceof Professor){
				type = 1;
				slct = ((Professor)h).getMajor();
			pstmt.setString(2, "professor");
			}else if(h instanceof Staff){
				type = 2;
				slct = ((Staff)h).getField();
			pstmt.setString(2, "staff");
			}else if(h instanceof Trainee){
				type = 3;
				slct = ((Trainee)h).getHakbun();
			pstmt.setString(2, "trainee");
			}
			pstmt.setString(3, h.getName());
			pstmt.setInt(4, h.getAge());
			
			int row = pstmt.executeUpdate();
			
			if(row == 1){System.out.println("등록 성공");
			//sql = "INSERT INTO ? VALUES(?, ?)";
			sql = "INSERT INTO ";
			//pstmt = con.prepareStatement(sql);
			System.out.println(type);
			switch (type) {
			case 1:
				//pstmt.setString(1, "PROFESSOR");
				sql = sql + "professor ";
				break;
			case 2:
				sql = sql + "staff ";
				break;
			case 3:
				sql = sql + "trainee ";
				break;
			default:
				break;
			}
			//pstmt.setString(2, h.getJumin());
			sql = sql + "VALUES('" +h.getJumin();
			//pstmt.setString(3, slct);
			sql = sql + "', '"+slct+"')";
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			int row1 = pstmt.executeUpdate();
			if(row1 ==1){
				bool = true;
				System.out.println("subtableupdate 성공");
			}
			}else if(row ==0){
				System.out.println("등록 실패");
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			SESConnectionManager.close(con);

		}
		return bool;
	}

	@Override
	public Human searchHuman(String jumin) {
		Human h = null;
		Connection con = SESConnectionManager.getConnection();
		//String sql = "SELECT * FROM human WHERE jumin = ?";
		String sql = "SELECT human.name, human.age, human.type, trainee.stdno, professor.major, staff.field"
				+ " FROM human, trainee, professor, staff "
				+ " WHERE human.jumin = trainee.jumin(+) AND"
				+ " human.jumin = professor.jumin(+) AND"
				+ " human.jumin = staff.jumin(+) AND "
				+ "human.jumin = ?";
		//TODO: table join 해서 읽어오기 나눠서 객체 만들기 
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, jumin);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){//해당 주민이 존재하면
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String type = rs.getString("type");
				if(type.equals("professor")){
					String major = rs.getString("major");
					h= new Professor(name, jumin, age, major);
				}else if(type.equals("trainee")){
					String hakbun = rs.getString("stdno");
					h = new Trainee(name, jumin, age, hakbun);
					
				}else if(type.equals("staff")){
					String field = rs.getString("field");
					h = new Staff(name, jumin, age, field);
				}
				System.out.println(h);
				System.out.println(name+"님의 기록이 존재합니다. ");
			}//주민이 존재하지 않으면 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			SESConnectionManager.close(con);

		}
		return h;
	}

	/**
	 * Human 객체를 받아와서 있는지 검사한다. 
	 * 있으면 update 문 실행 
	 * 없으면 throw
	 */
	@Override
	public boolean updateHuman(Human h) throws RecordNotFoundException{
		//이름 나이 각자 가진 항목만 변경가능
		if(searchHuman(h.getJumin())== null){
			throw new RecordNotFoundException();
		}
		boolean res =false;
		Connection con = SESConnectionManager.getConnection();
		String sql1 = "UPDATE human set name =?, age = ? where jumin =?";
		String sql = "UPDATE "; // ? set ? = ? where jumin = ?";
		String slct ="";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql1);
			pstmt.setString(1, h.getName());
			pstmt.setInt(2, h.getAge());
			pstmt.setString(3, h.getJumin());
			int row = pstmt.executeUpdate();
			if(row == 1){System.out.println("update 성공");
		
			if(h instanceof Professor){
			slct = ((Professor)h).getMajor();
			sql = sql + " professor set major = '";
			}else if(h instanceof Staff){
			slct = ((Staff)h).getField();
			sql = sql +" staff set field = '";
			}else if(h instanceof Trainee){
			slct = ((Trainee)h).getHakbun();
			sql = sql+" trainee set hakbun = '";
			}
			sql = sql + slct +"' WHERE jumin = '"+ h.getJumin()+"'";
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			row = pstmt.executeUpdate();
			if(row ==1){System.out.println("subtable update 성공");
			res = true;
			}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			SESConnectionManager.close(con);
		}
		return res;
	}

	/**
	 * jumin으로 있는지 찾아서 결과가 null이 아니면 실행
	 * @param jumin
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	
	@Override
	public boolean deleteHuman(String jumin) throws RecordNotFoundException, ClassNotFoundException, IOException {
		boolean result= false ;
		if(searchHuman(jumin)==null){throw new RecordNotFoundException(); }
		Connection con = SESConnectionManager.getConnection();
		String sql = "DELETE FROM human where jumin = ?"; 
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, jumin);
			int row = pstmt.executeUpdate();
			if(row==1){
				System.out.println("삭제 성공");
				result = true;}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			SESConnectionManager.close(con);
		}
		return result;
	}

	
//	public static void main(String[] args) throws ClassNotFoundException, RecordNotFoundException, IOException {
//		 SESServerManager sm = new SESServerManager();
////		 Human h = new Staff("도고포", "95731-383541", 13, "9624");
////		 try {
////			sm.insertHuman(h);
////		} catch (DuplicateJuminException e) {
////			e.printStackTrace();
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
////		 sm.deleteHuman("99911-895423");
////	ArrayList<Human> hList = sm.getH_List();
////	for(Human h : hList){
////	System.out.println(h.toString());
////	}
////		 sm.updateHuman(h);
//		ArrayList<String> allList = sm.getAllList();
//		for(String st : allList){
//			System.out.println(st);
//		}
//	}
}
 

