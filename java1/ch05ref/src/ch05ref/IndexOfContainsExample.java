package ch05ref;

public class IndexOfContainsExample {

	public static void main(String[] args) {
		String subject = "자바 프로그래밍 자바 프로그래밍 자바 프로그래밍";
		// indexOf ("1") -> 1을 찾아라 // 메소드는 검색을 문자열의 처음부터 시작합니다.
		// 사용 예시 -> indexOf(String str, int fromIndex)
		// lastIndexOf() - > 메소드는 검색을 문자열의 끝부터 시작합니다.
		// int(타입) fromIndex : 검색을 시작할 인덱스를 지정합니다.
		int location = subject.indexOf("프로그래밍");
		System.out.println(location);
		// substring (시작인덱스) -> 시작 인덱스부터 끝까지 잘라내기를 한다.
		String substring = subject.substring(location); 
		System.out.println(substring);
		// 재활용
		// 과목명에 자바가 있는지 알아 보고 싶다. 있다->index가 0이상, 없다 ->  -1
		location = subject.indexOf("자바");
		// 자바와 관련이 있는지 if문으로 출력
		if (location != -1) {
			System.out.println("자바와 관련된 책이군요.");
		} else {
			System.out.println("자바와 관련 없는 책이군요.");
		}
		// 자바가 포함이 되어있다. -> contains(). 1개 이상 포함되어 있으면 true , 없으면 false를 돌려준다.
		boolean result = subject.contains("자바");
		if (result) { // 있으면
			System.out.println("자바와 관련된 책이군요.");
		} else {	// 없으면
			System.out.println("자바와 관련 없는 책이군요.");
		}
		
		// 마지막에 나타나는 자바의 위치 찾기
		System.out.println(subject.lastIndexOf("자바"));
		
		// 2번째 자바의 위치 찾기
		// subject.indexOf("자바")+1 시작위치 
		System.out.println(subject.indexOf("자바",subject.indexOf("자바")+1));
		// 맨 앞에 자바가 있다면
		System.out.println(subject.indexOf("자바",1));
		
		// 파일명 확장자 찾아 보기 - 파일의 특성
		String fileName1 = "c:/my.photo/test.01.jpg";
		String fileName2 = "c:/my.photo/my.jpg";
		System.out.println(fileName1.substring(fileName1.lastIndexOf(".")+1));
		System.out.println(fileName2.substring(fileName2.lastIndexOf(".")+1));
		
	}

}
