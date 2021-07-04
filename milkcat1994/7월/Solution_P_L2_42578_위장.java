import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/*
 * -위장-
 * 1. 완전탐색 풀이
 * 
 * 테스트 20 〉	통과 (0.06ms, 52.5MB)
 * 테스트 21 〉	통과 (0.07ms, 52.1MB)
 * 테스트 22 〉	통과 (0.08ms, 53.2MB)
 * 테스트 23 〉	통과 (0.09ms, 52.8MB)
 * 테스트 24 〉	통과 (0.07ms, 52.3MB)
 * 테스트 25 〉	통과 (0.34ms, 52.7MB)
 * 테스트 26 〉	통과 (13.52ms, 52.3MB)
 * 테스트 27 〉	통과 (0.10ms, 53MB)
 * 테스트 28 〉	통과 (0.44ms, 52.8MB)
 * 
 * 풀이 시간 : 15M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/42578
public class Solution_P_L2_42578_위장 {
	public int solution(String[][] clothes) {
		
		HashMap<String, Integer> hm = new HashMap<>();
		for(String[] cloth : clothes) {
			hm.put(cloth[1], hm.getOrDefault(cloth[1], 0)+1);
		}
		
		List<Integer> list = new ArrayList<>();
		Iterator<Integer> iter = hm.values().iterator();
		while(iter.hasNext()) {
			list.add(iter.next());
		}
		
		return combi(list, 0, 1) -1;
	}
	
	static int combi(List<Integer> list, int idx, int able) {
		if(list.size() <= idx) {
			return able;
		}
		
		return combi(list, idx+1, able * list.get(idx)) + combi(list, idx+1, able);
	}

	public static void main(String[] args) {
		Solution_P_L2_42578_위장 sol = new Solution_P_L2_42578_위장();
		String[][] clothes ={{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}};
		int answer = sol.solution(clothes);
		System.out.println(answer);
	}
}