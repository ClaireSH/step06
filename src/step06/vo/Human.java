package step06.vo;
import java.io.Serializable;

public class Human implements Serializable {

	private static final long serialVersionUID = 7467106567159739834L;
	private String name;
	private String jumin;
	private int age;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJumin() {
		return jumin;
	}
	public void setJumin(String jumin) {
		this.jumin = jumin;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void printInfo(){
		
		System.out.println("이름: "+this.name+", 주민: "+this.jumin+", 나이: "+this.age);
		
	}
	
	//public Human(){}
	
	public Human(String name, String jumin, int age) {
		super();
		this.name = name;
		this.jumin = jumin;
		this.age = age;
	}
	
	public Human() {
	}
	protected void finalize() throws Throwable {};
	@Override
	public String toString() {
		return "이름: " + name + ", 주민: " + jumin + ", 나이: " + age +" ";
		
	}
	
}
