package step06.client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import step06.vo.Human;
import step6.manager.SESchoolManagerImpl;

public class SESClientManager implements SESchoolManagerImpl {
	private Socket socket ;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private Scanner sc = new Scanner(System.in);
	
	public SESClientManager() throws UnknownHostException, IOException {
		socket = new Socket("localhost", 7854); //localhost
		oos = new ObjectOutputStream(socket.getOutputStream());
		ois = new ObjectInputStream(socket.getInputStream());
		
	
	}

	@Override
	public boolean insertHuman(Human h) throws IOException, ClassNotFoundException {
		System.out.println("insertHuman 실행");
		Object[] obj = {"insert",h};
		System.out.println("등록을 위해 서버로 보냅니다.");
		oos.writeObject(obj);
	
		boolean bool = (boolean)ois.readObject();
		System.out.println("boolean 받아와서 읽어요 등록? or not?.");
		System.out.println(bool);
		return bool;
	}

	@Override
	public Human searchHuman(String jumin) throws IOException {
		Object[] obj = {"search",jumin};
		oos.writeObject(obj);
		Human hu= null;
		try {
		 hu = (Human)ois.readObject();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return hu;
		
	}

	@Override
	public boolean updateHuman(Human h) throws IOException {
		Object[] obj = {"change",h};
		oos.writeObject(obj);
		boolean bool = false;
		try {
			bool = (boolean)ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return bool;
	}

	@Override
	public boolean deleteHuman(String jumin) throws IOException, ClassNotFoundException {
		Object[] obj = {"delete",jumin};
		oos.writeObject(obj);
		boolean bool = (boolean)ois.readObject();
		System.out.println(bool);
		return bool;
	}

	public ArrayList<Human> printAll() throws IOException, ClassNotFoundException {
		System.out.println("---------전체 출력-----------");
		Human hoi = new Human("sdf", "sdf", 123);
		Object[] obj = {"printAll", hoi};
		String result="";
		
		oos.writeObject(obj);
		System.out.println("요청 보냄");
		ArrayList<Human> un=(ArrayList<Human>)ois.readObject();
		System.out.println("리스트 읽어옴");
		//for 문으로 전체 출력
//		for(Human humo :un){
//			result += humo.toString()+"\n";
//		}
		return un;
		
	}

	public boolean changeHuman(Human h) throws IOException, ClassNotFoundException {
		//Human h = searchHuman(jumin);
		
	/**	System.out.println("변경가능 항목 : [교수- 과목][학생- 학번][직원- 업무]");
		String change;
		if (h instanceof Professor){
		System.out.println("교수님 계정-------------------------");
		System.out.println("변경할 과목을 입력하세요.");
		change = sc.next();
		((Professor) h).setMajor(change);
		}
		if(h instanceof Trainee){
		System.out.println("학생 계정-------------------------");
		System.out.println("변경할 학번을 입력하세요.");
		change = sc.next();
		((Trainee) h).setHakbun(change);	

		}
		if(h instanceof Employee){
		System.out.println("직원 계정-------------------------");
		System.out.println("변경할 업무과목을 입력하세요.");
		change = sc.next();
		((Employee) h).setField(change);		

		}
		*/
		
		Object[] obj = {"change",h};
		oos.writeObject(obj);
		boolean bool = (boolean)ois.readObject();
		
		return bool;
	}

	

}
