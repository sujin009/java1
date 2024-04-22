package ch06class;

public class ComputerExample {

	public static void main(String[] args) {

		//가변길이 메서드의 사용
		System.out.println(sum(10,20));
		System.out.println(sum(100,90,85,100,50));
		
	}// main end
		// 타입이 같은 가변길이 메서드 작성 -> 배열 대신 사용할 수 있다.(*배열많이사용)
		// main()가 static이므로 바로 사용하려면 static을 붙여야한다.
	public static int sum(int...nums) {// ... 때문에 int[]로 나옴
		int result = 0;
		for(int num : nums) result += num;
		return result;
	}
}// class end
