/*
 * -가장 긴 팰린드롬-
 * 1. 구간별로 나누어 팰린드롬인지 확인한다.
 * 2. 문자열의 가운데에서 부터 팰린드롬을 파악하여 중복 계산을 하지 않도록 한다.
 * 3. s.substring()의 경우 O(n)을 가지므로 find()함수를 호출할 때 index를 이용하여 계산한다.
 * 
 * 테스트 17 〉	통과 (0.02ms, 70.7MB)
 * 테스트 18 〉	통과 (0.06ms, 69.8MB)
 * 테스트 19 〉	통과 (0.88ms, 59.8MB)
 * 테스트 20 〉	통과 (0.47ms, 68.8MB)
 * 테스트 21 〉	통과 (0.65ms, 60.4MB)
 * 
 * 효율성  테스트
 * 테스트 1 〉	통과 (46.12ms, 52MB)
 * 테스트 2 〉	통과 (14.09ms, 52.3MB)
 * 
 * 풀이 시간 : 1H 20m
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/12904
public class Solution_P_L3_12904_가장긴팰린드롬 {
	public int solution(String s) {
		int answer = 1;
		int length = s.length();
		
		for (int start = 0; start < length; ++start) {
			for (int end = start+1; end < length; ++end) {
				if(answer > end-start) continue;
				answer = Math.max(find(start, end, s), answer);
			}
		}
		
		return answer;
	}
	
	public int find(int start, int end, String s) {
		int length = (end-start+1);
		int sum = end+start;
		int l = length%2 == 0 ? sum/2 : sum/2-1;
		int r = length%2 == 0 ? sum/2+1 : sum/2+1;
		
		while(l>=start && r<=end) {
			if(s.charAt(l) != s.charAt(r)) {
				return r-l-1;
			}
			l--; r++;
		}
		return length;
	}

	public static void main(String[] args) {
		Solution_P_L3_12904_가장긴팰린드롬 sol = new Solution_P_L3_12904_가장긴팰린드롬();
		String s = "abacde";
//		StringBuilder sb = new StringBuilder();
//		for(int i=0; i<2500; ++i)
//			sb.append('a');
//		String s = sb.toString();
		int answer = sol.solution(s);
		System.out.println(answer);
	}
}