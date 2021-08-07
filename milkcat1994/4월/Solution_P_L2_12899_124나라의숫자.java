/*
 * -124 나라의 숫자-
 * 
 * 3 -> 4 이므로 숫자를 -1해주고 3진수로 계산을 해나간다.
 * 정확히는 3의 배수일 경우에만 -1해주면 되지만 모든 숫자에 대해 -1을 진행하고
 * [0,1,2] -> [1,2,4] 로 교체하는 작업을 진행해준다.
 * 
 * 정확성  테스트
 * 테스트 1 〉	통과 (0.04ms, 53.1MB)
 * 테스트 2 〉	통과 (0.04ms, 52.3MB)
 * 테스트 3 〉	통과 (0.04ms, 52.1MB)
 * 테스트 4 〉	통과 (0.04ms, 52.7MB)
 * 테스트 5 〉	통과 (0.04ms, 52.3MB)
 * 테스트 6 〉	통과 (0.04ms, 52.8MB)
 * 테스트 7 〉	통과 (0.05ms, 53MB)
 * 테스트 8 〉	통과 (0.04ms, 51.7MB)
 * 테스트 9 〉	통과 (0.04ms, 52.2MB)
 * 테스트 10 〉	통과 (0.05ms, 52.2MB)
 * 테스트 11 〉	통과 (0.05ms, 51.8MB)
 * 테스트 12 〉	통과 (0.05ms, 52.3MB)
 * 테스트 13 〉	통과 (0.05ms, 52.3MB)
 * 테스트 14 〉	통과 (0.05ms, 51.8MB)
 * 
 * 효율성  테스트
 * 테스트 1 〉	통과 (0.04ms, 52.6MB)
 * 테스트 2 〉	통과 (0.06ms, 52.1MB)
 * 테스트 3 〉	통과 (0.05ms, 52.2MB)
 * 테스트 4 〉	통과 (0.05ms, 53.4MB)
 * 테스트 5 〉	통과 (0.05ms, 51.8MB)
 * 테스트 6 〉	통과 (0.05ms, 52MB)

 * 풀이 시간 : 15M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/12899
public class Solution_P_L2_12899_124나라의숫자 {
	public String solution(int n) {
		StringBuilder sb = new StringBuilder();
		
		while(n>0) {
			n--;
			sb.append(n%3+1);
			n/=3;
		}
		return sb.reverse().toString().replace('3', '4');
	}

	public static void main(String[] args) {
		Solution_P_L2_12899_124나라의숫자 sol = new Solution_P_L2_12899_124나라의숫자();
		int n=4;
		String answer = sol.solution(n);
		System.out.println(answer);
	}
}