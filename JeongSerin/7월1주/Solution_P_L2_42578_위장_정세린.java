/*
테스트 1 〉	통과 (0.12ms, 54MB)
테스트 2 〉	통과 (0.06ms, 52.3MB)
테스트 3 〉	통과 (0.07ms, 52.4MB)
테스트 4 〉	통과 (0.12ms, 53.1MB)
테스트 5 〉	통과 (0.10ms, 52.4MB)
테스트 6 〉	통과 (0.06ms, 51.9MB)
테스트 7 〉	통과 (0.09ms, 52MB)
테스트 8 〉	통과 (0.07ms, 52.9MB)
테스트 9 〉	통과 (0.04ms, 52.3MB)
테스트 10 〉	통과 (0.06ms, 51.7MB)
테스트 11 〉	통과 (0.06ms, 52.1MB)
테스트 12 〉	통과 (0.10ms, 53.3MB)
테스트 13 〉	통과 (0.09ms, 52MB)
테스트 14 〉	통과 (0.04ms, 52.4MB)
테스트 15 〉	통과 (0.04ms, 52.9MB)
테스트 16 〉	통과 (0.03ms, 51.7MB)
테스트 17 〉	통과 (0.04ms, 52.7MB)
테스트 18 〉	통과 (0.06ms, 52.6MB)
테스트 19 〉	통과 (0.07ms, 54.1MB)
테스트 20 〉	통과 (0.06ms, 52.7MB)
테스트 21 〉	통과 (0.04ms, 52MB)
테스트 22 〉	통과 (0.04ms, 52.4MB)
테스트 23 〉	통과 (0.05ms, 52.4MB)
테스트 24 〉	통과 (0.05ms, 53.2MB)
테스트 25 〉	통과 (0.08ms, 52.8MB)
테스트 26 〉	통과 (0.11ms, 52.7MB)
테스트 27 〉	통과 (0.05ms, 52.6MB)
테스트 28 〉	통과 (0.09ms, 52.4MB)
 * 30m
 * HashMap
 */
package PROGRAMMERS;

import java.util.ArrayList;
import java.util.HashMap;

public class Solution_P_L2_42578_위장_정세린 {
	static class Solution {
		public int solution(String[][] clothes) {
			HashMap<String, Integer> map = new HashMap<>();
			ArrayList<ArrayList<String>> item = new ArrayList<>();

			int val = 0;
			for (String[] c : clothes) {
				if (map.get(c[1]) == null) {
					map.put(c[1], val);
					val++;
					item.add(new ArrayList<String>());
				}
				item.get(map.get(c[1])).add(c[0]);
			}

			int num = 1;
			for (int i = 0; i < item.size(); i++)
				num *= (item.get(i).size() + 1);

			return num - 1;
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		String[][] clothes = {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}};
		int answer = solution.solution(clothes);
		System.out.println(answer);
	}

}
