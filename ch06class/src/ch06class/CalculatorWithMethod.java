package ch06class;

public class CalculatorWithMethod {

	public static void main(String[] args) {

		// result
		// var1, var2 - 값을 가지고 5칙 연산 처리
		// 값 세팅
		int var1 = 10;
		int var2 = 3;
		
		// 메서드의 호출 사용
		add(var1,var2);		// +
		minus(var1, var2);  // -
		multi(var1, var2);	// *
		divide(var1, var2); // /
		remain(var1, var2); // %
		
//		// 더하기
//		int result = var1 + var2;
//		System.out.println(var1 + " + " + var2 + " = " + result);
//		// 빼기
//		result = var1 - var2;
//		System.out.println(var1 + " - " + var2 + " = " + result);
//		// 곱하기
//		result = var1 * var2;
//		System.out.println(var1 + " * " + var2 + " = " + result);
//		// 나누기
//		result = var1 / var2;
//		System.out.println(var1 + " / " + var2 + " = " + result);
//		// 나머지
//		result = var1 % var2;
//		System.out.println(var1 + " % " + var2 + " = " + result);
//		
		// 더하자. (반복되면 사용)
//		add(var1,var2);
		
	}// main end

	// 메서드를 작성해서 호출해서 사용해보자. (선언 부분)
	// 파라미터의 개수,순서,타입이 같아야 한다 / 변수 이름이 다른데 타입이 같으면 같다고 인식
	// 더해서 출력하는 메서드
	static void add(int var1, int var2) {
		int result = var1 + var2;
		resultPrint(var1, var2, result, "+");
	}
	// 빼서 출력하는 메서드 - minus()
	static void minus(int var1, int var2) {
		int result = var1 - var2;
//		System.out.println(var1 + " - " + var2 + " = " + result);
		resultPrint(var1, var2, result, "-");
	}
	// 곱하는 메서드 - multi()
	static void multi(int var1, int var2) {
		int result = var1 * var2;
//		System.out.println(var1 + " * " + var2 + " = " + result);
		resultPrint(var1, var2, result, "*");
	}
	// 나누기 메서드 - divide()
	static void divide(int var1, int var2) {
		int result = var1 / var2;
//		System.out.println(var1 + " / " + var2 + " = " + result);
		resultPrint(var1, var2, result, "/");
	}
	// 나머지 메서드 - remain()
	static void remain(int var1, int var2) {
		int result = var1 % var2;
//		System.out.println(var1 + " % " + var2 + " = " + result);
		resultPrint(var1, var2, result, "%");
	}
	
	// 결과를 출력하는 메서드
	// op -> "%" 
	static void resultPrint(int var1, int var2 , int result, String op) {
		System.out.println(var1 + " " + op + " "+ var2 + " = " + result);
	}
	
}// class end
