import java.util.Arrays;

/*
 * -2개 이하로 다른 비트-
 * 1. 처음으로 0이나오는 비트를 1로 변경
 * 2. 그 이전 비트인 1을 0으로 변경
 * 
 * 테스트 5 〉	통과 (0.13ms, 67.1MB)
 * 테스트 6 〉	통과 (0.08ms, 57.5MB)
 * 테스트 7 〉	통과 (6.58ms, 112MB)
 * 테스트 8 〉	통과 (8.42ms, 109MB)
 * 테스트 9 〉	통과 (4.45ms, 82.6MB)
 * 테스트 10 〉	통과 (15.69ms, 108MB)
 * 테스트 11 〉	통과 (13.84ms, 124MB)
 * 
 * 풀이시간 : 25m
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/77885
public class Solution_P_L2_77885_2개이하로다른비트 {
	public long[] solution(long[] numbers) {
		int length = numbers.length;
		long[] answer = new long[length];
		
		for(int i=0; i<length; ++i) {
			long number = numbers[i];
			int idx=0;
			// 처음으로 0을 만나는 지점 찾기
			while((number & (1L<<idx++)) != 0);
			
			// 해당 지점 1로 변경해주기
			idx--;
			number |= (1L<<idx);
			// 만약 0번째 지점이 아니라면 이전 지점(1인 부분) 0으로 변경
			if(idx != 0) {
				idx--;
				number ^= (1L<<idx);
			}
			answer[i] = number;
		}
		return answer;
	}

	public static void main(String[] args) {
		Solution_P_L2_77885_2개이하로다른비트 sol = new Solution_P_L2_77885_2개이하로다른비트();
		long[] numbers = {2,7,(1L<<50)-1};
		System.out.println(Arrays.toString(numbers));
		long[] answer = sol.solution(numbers);
		System.out.println(Arrays.toString(answer));
	}
}
