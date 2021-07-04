import java.util.HashMap;

/*
 * -완주하지 못한 선수-
 * 
 * 효율성  테스트
 * 테스트 1 〉	통과 (43.37ms, 81.9MB)
 * 테스트 2 〉	통과 (67.26ms, 87.9MB)
 * 테스트 3 〉	통과 (106.98ms, 95.5MB)
 * 테스트 4 〉	통과 (83.54ms, 96.4MB)
 * 테스트 5 〉	통과 (93.68ms, 95.8MB)
 * 
 * 풀이 시간 : 5M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/42576
public class Solution_P_L1_42576_완주하지못한선수 {
	
	public String solution(String[] participant, String[] completion) {
		HashMap<String, Integer> hm = new HashMap<>();
		
		for(String part : participant) {
			hm.put(part, hm.getOrDefault(part, 0)+1);
		}
		
		int num;
		for(String comp : completion) {
			num = hm.get(comp);
			hm.put(comp, num-1);
			if(num == 1) {
				hm.remove(comp);
			}
		}
		
		return hm.keySet().iterator().next();
	}


	public static void main(String[] args) {
		Solution_P_L1_42576_완주하지못한선수 sol = new Solution_P_L1_42576_완주하지못한선수();
		String[] participant = {"leo", "kiki", "eden"};
		String[] completion = {"eden", "kiki"};
		String answer = sol.solution(participant, completion);
		System.out.println(answer);
	}
}