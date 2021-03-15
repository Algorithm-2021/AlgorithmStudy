import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * -튜플-
 * 1. split과 sort를 이용하여 집합을 정렬한다.
 * 2. set을 이용하여 새롭게 추가된 요소만 answer 배열에 추가시켜준다.
 * 
 * 테스트 1 〉	통과 (0.89ms, 52.9MB)
 * 테스트 2 〉	통과 (1.09ms, 53.1MB)
 * 테스트 3 〉	통과 (0.94ms, 51.7MB)
 * 테스트 4 〉	통과 (0.93ms, 52.5MB)
 * 테스트 5 〉	통과 (5.25ms, 53MB)
 * 테스트 6 〉	통과 (5.47ms, 53.1MB)
 * 테스트 7 〉	통과 (27.65ms, 55.1MB)
 * 테스트 8 〉	통과 (52.62ms, 59.5MB)
 * 테스트 9 〉	통과 (41.41ms, 56.5MB)
 * 테스트 10 〉	통과 (54.67ms, 59.7MB)
 * 테스트 11 〉	통과 (71.12ms, 62.7MB)
 * 테스트 12 〉	통과 (78.54ms, 69.2MB)
 * 테스트 13 〉	통과 (93.29ms, 68.9MB)
 * 테스트 14 〉	통과 (82.06ms, 69.4MB)
 * 테스트 15 〉	통과 (2.86ms, 52.8MB)
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/64065
public class Solution_P_L2_64065_튜플 {

	public int[] solution(String s) {
		int[] answer;
		String[] strArr = s.substring(2, s.length()-2).split("\\},\\{");

		Arrays.sort(strArr, (o1, o2) -> {
			return o1.length() - o2.length();
		});
		answer = new int[strArr.length];
		
		Set<Integer> hs = new HashSet<>();
		int ti;
		int idx=0;
		for(String strings : strArr) {
			for(String num : strings.split(",")) {
				ti = Integer.parseInt(num);
				if(hs.add(ti)) {
					answer[idx++] = ti;
				}
			}
		}
			
		return answer;
	}


	public static void main(String[] args) {
		Solution_P_L2_64065_튜플 sol = new Solution_P_L2_64065_튜플();
		int[] answer = sol.solution("{{2},{2,1},{2,1,3},{2,1,3,4}}");
		for(int i=0; i<answer.length; ++i)
			System.out.print(answer[i]+" ");
	}
}