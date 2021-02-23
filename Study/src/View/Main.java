package View;

import Control.Client;

class Main {
	public static void main(String[] args) {
		//System.out.println("Hello world!");
		//testClient();
	    Window win = new Window();
	}
	
	public static void testClient(){
		Client client;
	    client = new Client("Guilherme","123456789");
	
	    System.out.println("Nome: " + client.getName());
	    System.out.println("Telefone: " + client.getPhone());
	}
}