package ch06class;

/**
 * 
 * 회원을 관리해주는 데이터 객체로 사용. 아이디, 이름, 나이, 클래스이름
 */
public class Member {
	// 데이터를 저장하는 변수들을 가직 있다.
	// 클래스의 구성원으로 가지고 있다. member 변수 == 전역변수 - 기본값 세팅을 해준다.
	// static 변수 - 자동으로 올라간다. Member.className으로 사용
	static String className;
	// non - static 변수 - new 해서 올리다. Member m1 = new Member(); m1.id;
	String id; // 기본값 - null;
	String name;// 기본값 - null;
	int age; // 기본값 - 0

	// new Member();
	// - 모든 클래스는 생성자를 만들지 않으면 컴파일할때 자동으로 기본 생성자 - 클래스 이름 ()
	// 생성자를 작성하는 목적은 변수의 초기 데이터를 세팅할 때 사용된다.

	// 초기화 블록 - Member 클래스 이름이 불려지면 자동으로 실행되는 처리문 : 부르지 않아도 자동으로 실행해서 실행문을 작성하지 않음
	// static 초기화 블록 -클래스 이름이 불려지면 자동으로 실행 - new랑 상관없이 딱 1번만 실행 : static 변수 초기값 세팅
	static {
		System.out.println("static 초기화 블록 처리중 : 딱 한 번 ---------");
		className = "Member";
	}
	// non-static 초기화 블록 - new 할 때 마다 자동으로 실행 - non static 변수 초기값 세팅
	{
		System.out.println("non - static 초기화 블록 처리중 : new 할 때 마다 ---------");
	}

	// 생성자 - 값을 전달 받아서 non-static 변수의 값을 세팅하기 위해서 사용 (1번만 실행 / new 할 때)

	// 기본 생성자 - 기본 값으로 데이터를 세팅해 주는 생성자 - 1. 리턴타입 없음 2. 클래스 이름과 같다.
	// : 기본값 세팅을 하지 않으면 생략 가능 -> Compiler가 자동으로 만들어준다. ** 생성자가 1개라도 있으면 기본 생성자를
	// 만들어주지 않음
	public Member() {
		this("test", "홍길동", 30); // 생성자가 생성자를 호출 할 때는 맨 위에 올려서 사용해야함
		System.out.println("기본 생성자 실행 : - new 기본 생성자()--------");
		// 기본값 세팅
//		id = "test";
//		name = "홍길동";
//		age = 30;

	}

	// 생성자 - non static 변수 초기화에 사용
	// this. : Member class 포함되어 있는 것 중에서 찾음 (아래로 내려감) 기본 생성자가 똑같은게 있어야 사용가능
	public Member(String id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	};
	
	// ** 오버로드 : 은 이름의 메서드를 여러 개 정의, 파라미터는 달라야함 
	// ** 오버라이드 : 부모 클래스에서 상속된 메서드를 자식 클래스에서 재정의

}
