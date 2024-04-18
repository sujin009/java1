package ch05ref;

public class ReplaceExample {

	public static void main(String[] args) {

		// String - 문자열 : 문자 배열
		String oldStr = ("자바 문자열은 불변입니다. 자바 문자열은 String입니다.");
		// 한글 자바 -> 영문 JAVA로 변경해 보자. : replace(); -> 문자열 바꾸기;
		String newStr = oldStr.replace("자바", "JAVA");

		System.out.println(oldStr);
		System.out.println(newStr);
	}

}
