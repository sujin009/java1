package ch05ref;

import java.util.Arrays;

public class ShortArray {

	public static void main(String[] args) {
		int[] nums = { 5, 7, 2, 1, 6 };
		System.out.println(Arrays.toString(nums));	// 정렬 전 출력
		// 중첩 for select short
		// 맨 앞에 부터 차례로 작은 데이터로 정렬 시키기위해 선택한다. -> 마지막은 자동 정렬
		for (int i = 0; i < nums.length - 1; i++) {
			// 선택한 다음 데이터부터 마지막 데이터까지 비교한다.
			for (int j = i + 1; j < nums.length; j++) {
				// 선택된 데이터가 비교 데이터보다 크면 바꾼다
				if (nums[i] > nums[j]) {
					int temp = nums[i];
					nums[i] = nums[j];
					nums[j] = temp;
				} // end of for if
//				System.out.println(Arrays.toString(nums)); // 정렬 하는 과정
			} // end of for j
		} // end of for i
		System.out.println(Arrays.toString(nums));	// 정렬 후 출력
	}// main end
}// class end
