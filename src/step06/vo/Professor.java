package step06.vo;

public class Professor extends Human {
	private String major;

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public Professor(String name, String jumin,int age, String major) {
		super(name, jumin, age);
		this.major = major;
	}
	
	public void printInfo(){
		super.printInfo();
		System.out.println(", 전공: "+ major);
		
	}
	
	@Override
	public String toString() {
		
		return "교수 = "+super.toString() + ", 과목: " + major +" \n";
	}
	
	

}
