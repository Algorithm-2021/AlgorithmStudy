/*
테스트 1 〉	통과 (1.16ms, 53.9MB)
테스트 2 〉	통과 (1.44ms, 52.5MB)
테스트 3 〉	통과 (1.25ms, 53.2MB)
테스트 4 〉	통과 (3.86ms, 52.3MB)
테스트 5 〉	통과 (3.46ms, 52.9MB)
테스트 6 〉	통과 (2.47ms, 52.8MB)
테스트 7 〉	통과 (4.58ms, 52.5MB)
테스트 8 〉	통과 (12.59ms, 61MB)
테스트 9 〉	통과 (9.94ms, 61.8MB)
테스트 10 〉	통과 (7.26ms, 53.1MB)
테스트 11 〉	통과 (5.04ms, 53.3MB)
테스트 12 〉	통과 (6.37ms, 52.6MB)
테스트 13 〉	통과 (8.50ms, 53.7MB)
테스트 14 〉	통과 (9.40ms, 56.9MB)
테스트 15 〉	통과 (13.86ms, 56.1MB)
테스트 16 〉	통과 (6.06ms, 52.6MB)
테스트 17 〉	통과 (10.25ms, 57.3MB)
테스트 18 〉	통과 (4.31ms, 52.5MB)
테스트 19 〉	통과 (6.20ms, 60.4MB)
테스트 20 〉	통과 (4.86ms, 52.7MB)
 * 1H 20m
 * 해시맵, 조합
 * https://programmers.co.kr/learn/courses/30/lessons/72411
 */
package PROGRAMMERS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Solution_P_L2_72411_메뉴리뉴얼_정세린 {

	static class Solution {
		StringBuilder sb = new StringBuilder();
		HashMap<String, Integer> map = new HashMap<>(); // <코스, 횟수>
		String order;
		int[] max = new int[11];

		public String[] solution(String[] orders, int[] course) {
			ArrayList<String> ans = new ArrayList<>();

			for (String order : orders) {
				char[] ch = order.toCharArray(); 
				Arrays.sort(ch); // 주문한것 정렬하고 시작
				this.order = String.valueOf(ch);

				for (int r : course) {
					sb.setLength(0);
					if (order.length() < r) break;
					else combination(0, order.length(), r);
				}
			}

			for (String key : map.keySet()) { // 주문수가 최대인것만 담음
				int cnt = map.get(key);
				if (cnt > 1 && max[key.length()] == cnt) ans.add(key);
			}

			Collections.sort(ans); // 정렬
			String[] answer = ans.toArray(new String[ans.size()]);
			
			return answer;
		}

		void combination(int idx, int n, int r) {
			if (r == 0) {
				if (map.get(sb.toString()) == null) {
					map.put(sb.toString(), 1);
				} else {
					map.put(sb.toString(), map.get(sb.toString()) + 1);
					max[sb.toString().length()] = Integer.max(max[sb.toString().length()], map.get(sb.toString()));
				}
				return;
			}

			for (int i = idx; i < n; i++) {
				sb.append(order.charAt(i));
				combination(i + 1, n, r - 1);
				sb.setLength(sb.length() - 1);
			}
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		String[] orders = {"XYZ", "XWY", "WXA"};
		int[] course = {2, 3, 4};
		String[] answer = solution.solution(orders, course);
		System.out.println(Arrays.toString(answer));
	}

}
