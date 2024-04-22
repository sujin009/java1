package ch06class;

// 일반 게시판의 데이터를 저장하는 객체
// -> BoardVO == BoardDTO(데이터전달목적) == Board
public class Board {

	// 변수 선언 - 데이터 저장
	// private(개인적인) - 같은 클래스에서만 사용 : 변수로 만들어서 직접 접근이 안되도록 한다.
	private long no;
	private String title;
	private String content;
	private String writer;

	// 맨 처음 데이터 세팅 - 생성자 작성 / 필요하면 만든다.

	// 데이터를 넣는 setter 메서드(), 데이터를 가져가는 getter() 메서드 만들어서
	public long getNo() {
		// no : 위로 올라가면서 선언된 변수를 찾는다. 지역->전역 순으로
		// this.no : 클래스 객체안에 변수no을 찾는다 -> 전역 변수만 찾음
		return this.no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	// 출력할때 자동으로 호출되는 메서드 : toString()
	// 원래는 Object에 있는데 똑같은걸 만들면 우선 출력 없으면 상속받는걸 호출
	// 주로 데이터 확인용으로 사용
	public String toString() {
		return "Board[no = " + no + ", title = " + title + ", content = " + content + ", writer = " + writer + "]";
	}
}
