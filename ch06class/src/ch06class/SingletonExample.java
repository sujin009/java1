package ch06class;

public class SingletonExample {

	public static void main(String[] args) {
		// 싱글톤 객체가 한번만 생성이된것을 사용한다
		// 한 번만 생성이 되는 이유 : 여러사람이 들어오더라도 똑같은 프로그램을 사용하기 위해서
		Singleton obj1 = Singleton.getInstance();
		obj1.print();
		Singleton obj2 = Singleton.getInstance();
		obj2.print();
		
		System.out.println(obj1 == obj2); //obj1,2가 같은가?
		System.out.println(System.identityHashCode(obj1));
		System.out.println(System.identityHashCode(obj2));
		
	}// end of main

}// end of class
