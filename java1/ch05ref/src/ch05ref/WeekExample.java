package ch05ref;

import java.util.Calendar;

public class WeekExample {

	public static void main(String[] args) {
		// week enum 안에 요일 변수들을 사용해야한다.
//		System.out.println(Week.MONDAY);
		Week today = null;
		// 오늘 날짜와 시간 정보 객체 만들기 - Date, Calendar, 
		// new -> calendar를 getInstance로 반환
		Calendar cal = Calendar.getInstance();
		int week = cal.get(Calendar.DAY_OF_WEEK);
		// 요일 정보 출력
		System.out.println("요일 정보의 위치 : "+ Calendar.DAY_OF_WEEK);
		// 1 - 일 , ... 5 - 목
		System.out.println("요일 정보 : " + week);
		switch(week) {
		case 1 : today = Week.SUNDAY;
		case 2 : today = Week.MONDAY;
		case 3 : today = Week.THURDAY;
		case 4 : today = Week.WEDESDAY;
		case 5 : today = Week.TUESDAY;
		case 6 : today = Week.FRIDAY;
		case 7 : today = Week.SATURDAY;
		}
		
		if (today == Week.SUNDAY) {
			System.out.println("일요일에는 축구를 합니다");
		} else {
			System.out.println("열심히 자바를 공부합니다.");
		}
	}

}
