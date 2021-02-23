package Control;

public class Client {
	String name = "";
	String phone = "";

	public Client(){
	}

	public Client(String name, String phone){
	    this.name = name;
	    this.phone = phone;
	}

	public String getName(){
	    return this.name;
	}

	public String getPhone(){
	    return this.phone;
	}
}
