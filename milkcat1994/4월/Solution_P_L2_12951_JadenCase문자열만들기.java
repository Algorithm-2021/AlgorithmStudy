/*
 * -JadenCase 문자열만들기-
 * 
 * 테스트 1 〉	통과 (0.09ms, 52.2MB)
 * 테스트 2 〉	통과 (0.11ms, 51.9MB)
 * 테스트 3 〉	통과 (0.11ms, 51.9MB)
 * 테스트 4 〉	통과 (0.13ms, 52.2MB)
 * 테스트 5 〉	통과 (0.18ms, 54MB)
 * 테스트 6 〉	통과 (0.15ms, 52MB)
 * 테스트 7 〉	통과 (0.10ms, 52.1MB)
 * 테스트 8 〉	통과 (0.08ms, 52MB)
 * 테스트 9 〉	통과 (0.13ms, 52.5MB)
 * 테스트 10 〉	통과 (0.06ms, 51.7MB)
 * 테스트 11 〉	통과 (0.15ms, 52.2MB)
 * 테스트 12 〉	통과 (0.12ms, 53.4MB)
 * 테스트 13 〉	통과 (0.11ms, 52.6MB)
 * 테스트 14 〉	통과 (0.13ms, 52MB)
 * 테스트 15 〉	통과 (0.17ms, 52.8MB)
 * 테스트 16 〉	통과 (0.08ms, 51.7MB)

 * 풀이 시간 : 25M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/12951
public class Solution_P_L2_12951_JadenCase문자열만들기 {
	
	public String solution(String s) {
		StringBuilder sb = new StringBuilder();
		boolean isStart = true;
		char ch;
		for(int idx=0; idx<s.length(); ++idx) {
			ch = s.charAt(idx);
			switch (ch) {
			case ' ':
				isStart = true;
				sb.append(ch);
				break;

			default:
				if(isStart)
					sb.append(Character.toUpperCase(ch));
				else
					sb.append(Character.toLowerCase(ch));
				isStart = false;
				break;
			}
		}
		return sb.toString();
	}


	public static void main(String[] args) {
		Solution_P_L2_12951_JadenCase문자열만들기 sol = new Solution_P_L2_12951_JadenCase문자열만들기();
//		String s = "3people unFollowed me";
		String s = "3people unFollowed me";
		String answer = sol.solution(s);
		System.out.println(answer);
	}
}