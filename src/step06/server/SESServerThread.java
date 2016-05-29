package step06.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import step06.Exeption.DuplicateJuminException;
import step06.Exeption.RecordNotFoundException;
import step06.vo.Human;

public class SESServerThread implements Runnable{
	private SESServerThread sm;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private boolean exit;
	private Socket socket;
	private ArrayList<ObjectOutputStream> oosList;
	public SESServerThread(Socket socket, ObjectInputStream ois, ObjectOutputStream oos, ArrayList<ObjectOutputStream> oosList) {
		super();
		System.out.println("thread 생성!");
		this.socket = socket;
		this.ois = ois;
		this.oos = oos;
		this.oosList = oosList; 
		
	}
	@Override
	public void run() {
		SESServerManager serm = new SESServerManager();
		//TODO: Servermanager 반환값에 따라 case 바꿔줘기 
		System.out.println("run!");
		
		try{
		while(socket.isConnected()){
		Object[] obj = (Object[])ois.readObject();
		String remesage = (String) obj[0];
		Object object = obj[1];
		Human h ;
		boolean bool;
		switch(remesage){
		case "insert":
			System.out.println("insert !");
			bool = serm.insertHuman((Human)object);
			oos.writeObject(bool);
			break;
		case "search":
			h =serm.searchHuman((String)object);
			oos.writeObject(h);
			break;
		case "delete":
			bool = serm.deleteHuman((String)object);
			oos.writeObject(bool);
			break;
		case "printAll":
			System.out.println("전체 리스트를 보냅니다.");
			oos.writeObject(serm.getAllList());
			
			break;
		case "change":
			System.out.println(((Human)object).toString());
			bool =serm.updateHuman((Human)object);
			oos.writeObject(bool);
			break;
		}
		}//while
		
		}catch(IOException | ClassNotFoundException | RecordNotFoundException e){
			e.printStackTrace();
			try {
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (DuplicateJuminException e) {
			e.printStackTrace();
		}
		
	}//run
	//TODO:브로드캐스팅 메소드 만들기 
	
}
