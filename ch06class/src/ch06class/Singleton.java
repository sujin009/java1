package ch06class;

public class Singleton {
	
	private static Singleton singleton = new Singleton();
	
	// private 생성자
	private Singleton() {}
	
	// static이 아닌 요소들은 5번째 줄에서 생성되어 올라간다.
	// getter만 만든다 : 이미 new한 상태에서 저장, 저장된 주소가 변경x 계속 주기만하니까
	
	//Singleton을 사용하려면 변수 선언후 getInstance() 호출해서 사용
	public static Singleton getInstance() {return singleton;}
	//non static
	public void print () {System.out.println("Singleton");}
}
