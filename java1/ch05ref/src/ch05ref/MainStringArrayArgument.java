package ch05ref;

public class MainStringArrayArgument {

	public static void main(String[] args) {
		// 전달된 인수의 개수가 2가 아니라면, 조건문 실행
		// 입력을 안 받았기 때문에 바로 run 실행시 오류 메시지 출력 , 입력 값을 넣어야함 (p.193 참고)
		// args.length : 자바 프로그램이 실행될 때, 사용자는 명령 프롬프트나 터미널을 통해 프로그램을 실행할 때 추가적인 정보를 전달
		if (args.length !=2) {
			System.out.println("프로그램 입력값이 부족"); // 오류 메세지 출력
			System.exit(0); // 출력 후 프로그램 종료
		}
		//사용자로부터 입력받은 첫 번째 문자열
		String strNum1 = args[0];	// index
		//사용자로부터 입력받은 두 번째 문자열
		String strNum2 = args[1];
		// String(문자열)을 int(정수)로 변환시키기
		int num1 = Integer.parseInt(strNum1);
		int num2 = Integer.parseInt(strNum2);
		// 결과 값 연산 후 출력
		int result = num1 + num2;
		System.out.println(num1 + " + " + num2 + " + " + " = " + result);
	}

}
