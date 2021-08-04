/*
테스트 1 〉	통과 (4.15ms, 57.8MB)
테스트 2 〉	통과 (0.10ms, 57.3MB)
테스트 3 〉	통과 (0.08ms, 57.6MB)
테스트 4 〉	통과 (0.10ms, 58.5MB)
테스트 5 〉	통과 (0.13ms, 58MB)
테스트 6 〉	통과 (0.13ms, 69.7MB)
테스트 7 〉	통과 (0.13ms, 57.3MB)
테스트 8 〉	통과 (0.13ms, 71.5MB)
테스트 9 〉	통과 (0.13ms, 55.2MB)
테스트 10 〉	통과 (0.09ms, 55MB)
 * 10m
 * 해시맵
 * https://programmers.co.kr/learn/courses/30/lessons/81301?language=java
 */
package PROGRAMMERS;

import java.util.HashMap;

public class Solution_P_L1_81301_숫자문자열과영단어_정세린 {
	static class Solution {
		public int solution(String s) {
			int answer = 0;
			HashMap<String, Integer> map = new HashMap<>();
			map.put("zero", 0);
			map.put("one", 1);
			map.put("two", 2);
			map.put("three", 3);
			map.put("four", 4);
			map.put("five", 5);
			map.put("six", 6);
			map.put("seven", 7);
			map.put("eight", 8);
			map.put("nine", 9);

			StringBuilder num = new StringBuilder();
			StringBuilder str = new StringBuilder();
			for (int i = 0; i < s.length(); i++) {
				if (Character.isDigit(s.charAt(i))) {
					if (str.length() > 0) {
						num.append(map.get(str.toString()));
						str.setLength(0);
					}
					num.append(s.charAt(i));
				} else {
					str.append(s.charAt(i));
					if (map.get(str.toString()) != null) {
						num.append(map.get(str.toString()));
						str.setLength(0);
					}
				}

			}
			
			answer = Integer.parseInt(num.toString());
			return answer;
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		String s = "one4seveneight";
		int answer = solution.solution(s);
		System.out.println(answer);
	}

}
