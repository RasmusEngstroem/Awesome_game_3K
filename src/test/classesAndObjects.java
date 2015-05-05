package test;

public class classesAndObjects {
	
	public static void main(String[] args) {
		
		person john = new person("John");
		
		john.setAge(20);
		
		person bob = new person("Bob");
		
		bob.setAge(32);
		
		
		System.out.println("John is " + john.age + " years old");
		System.out.println("Bob is " + bob.age + " years old");
		
	}
}
