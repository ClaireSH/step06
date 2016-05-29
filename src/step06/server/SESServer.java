package step06.server;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import step06.vo.Human;
import step6.manager.SESchoolManagerImpl;
/**
 * ArrayList 안에다가 outputStream 저장 
 * @author SCMaster
 *
 */
public class SESServer {
	
	public SESServer(){
		
	}
	
	
	
	public static void main(String[] args) {
		ArrayList<ObjectOutputStream> oosList = new ArrayList<>();
		try {
			
			ServerSocket serverSocket = new ServerSocket(7854);
			System.out.println("is wating...");
			while(true){
			Socket socket = serverSocket.accept();
			System.out.println("사용자 접속");
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oosList.add(oos);
			SESServerThread st = new SESServerThread(socket ,ois, oos, oosList);
			Thread th = new Thread(st);
			th.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
