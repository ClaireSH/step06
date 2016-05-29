package step06.vo;

public class Trainee extends Human{
 public Trainee(String name, String jumin, int age, String hakbun) {
		super(name, jumin, age);
		this.hakbun = hakbun;
	}

private String hakbun;

public String getHakbun() {
	return hakbun;
}

public void setHakbun(String hakbun) {
	this.hakbun = hakbun;
}
public void printInfo(){
	super.printInfo();
	System.out.println(", 학번: "+ hakbun);
	
}
@Override
public String toString() {
	return "학생= "+super.toString()+", 학번: " + hakbun + " \n";
}
 
}
