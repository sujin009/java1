package ch07inhe;

public class Borad {

	public static void main(String[] args) {
		// board 안에 사용할 수 있는 것. - 없다. 그러나 모든 클래스는 Object를 상속받는다.
		Borad board1 = new Borad();
		Borad board2 = new Borad();
		// object안에 있는 toString 자동 호출 메소드
		// 데이터 확인할때 toString() 사용
		System.out.println(board1.toString());
		//new에서 주소를 물어봐서 false로 뜬다 (boolean)
		System.out.println(board1.equals(board2));
		// 주소가 같으면 true라고 출력됨
		String name1 = "함수진";
		String name2 = "함수진";
		System.out.println(name1.equals(name2));
	}

}
