package ch06class;

// Member.java에서 데이터만 가져오기
public class MemberExample {
	public static void main(String[] args) {

		// Member를 불러와서 사용해보자.
		Member m1 = new Member();	// static은 자동으로 클래스이름이 불려지면 올라간다
		System.out.println(m1.name);
		Member m2 = new Member();	
		System.out.println(m2.name);
		// 클래스 이름 확인
		System.out.println(Member.className);
		
		// 생성자를 사용 데이터 초기화
		Member m3 = new Member("admin","수진",29);
		System.out.println(m3.name);
		m3.name = "임창균";
				System.out.println(m3.name);
	}
}
