package ch06class;

public class Calculator {

	public static void main(String[] args) {

		// result
		// var1, var2 - 값을 가지고 5칙 연산 처리
		// 값 세팅
		int var1 = 10;
		int var2 = 3;
		// 더하기 
		int result = var1 + var2;
		System.out.println(var1 + " + " + var2 + " = " + result);
		// 빼기
		result = var1 - var2;
		System.out.println(var1 + " - " + var2 + " = " + result);
		// 곱하기
		result = var1 * var2;
		System.out.println(var1 + " * " + var2 + " = " + result);
		// 나누기
		result = var1 / var2;
		System.out.println(var1 + " / " + var2 + " = " + result);
		// 나머지
		result = var1 % var2;
		System.out.println(var1 + " % " + var2 + " = " + result);
	}// main end
}// class end
