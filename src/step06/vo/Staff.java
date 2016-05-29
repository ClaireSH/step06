package step06.vo;

public class Staff extends Human{
 public Staff(String name, String jumin, int age, String field) {
		super(name, jumin, age);
		this.field = field;
	}

private String field;

public String getField() {
	return field;
}

public void setField(String field) {
	this.field = field;
}

public void printInfo(){
	super.printInfo();
	System.out.println(", 업무: "+ field);
	
}

@Override
public String toString() {
	return "직원 = "+super.toString()+", 업무: " + field + " \n";
}
 
}
