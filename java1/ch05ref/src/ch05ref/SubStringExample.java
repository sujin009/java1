package ch05ref;

public class SubStringExample {

	public static void main(String[] args) {
		String ssn = "880815-1234567";
		System.out.println(ssn.length()); // 문자열 개수
		
		String firstNum = ssn.substring(0,6); // index 0~6까지 자르면 '880815'만 출력이 된다.
		System.out.println(firstNum);
		
		String secondNum = ssn.substring(7); // index 7부터 보여준다.
		System.out.println(secondNum); 
		
		secondNum = ssn.substring(ssn.indexOf("-")+1); // " - " 를 찾는다. "-" 다음부터 잘라내기
		System.out.println(secondNum); 
	}

}
