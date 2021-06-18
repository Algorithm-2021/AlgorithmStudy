/*
테스트 1 〉	통과 (0.11ms, 53.2MB)
테스트 2 〉	통과 (0.15ms, 53.1MB)
테스트 3 〉	통과 (0.13ms, 52.9MB)
테스트 4 〉	통과 (0.12ms, 52.4MB)
테스트 5 〉	통과 (0.18ms, 52.7MB)
테스트 6 〉	통과 (0.15ms, 52.4MB)
테스트 7 〉	통과 (0.17ms, 53.8MB)
테스트 8 〉	통과 (0.10ms, 52.5MB)
테스트 9 〉	통과 (0.15ms, 52.8MB)
테스트 10 〉	통과 (0.13ms, 53.1MB)
테스트 11 〉	통과 (0.18ms, 54.3MB)
테스트 12 〉	통과 (0.24ms, 52.2MB)
테스트 13 〉	통과 (0.15ms, 53.2MB)
테스트 14 〉	통과 (0.12ms, 52.3MB)
 * 20m
 * 해쉬맵 사용
 * https://programmers.co.kr/learn/courses/30/lessons/49993?language=java
 */
package PROGRAMMERS;

import java.util.HashMap;

public class Solution_P_L2_49993_스킬트리_정세린 {

	static class Solution {
		public int solution(String skill, String[] skill_trees) {
			int answer = 0;
			HashMap<Character, Integer> map = new HashMap<>(); // <스킬명, 순서>

			for (int i = 0; i < skill.length(); i++) map.put(skill.charAt(i), i);

			for (String str : skill_trees) {
				int before = -1;
				boolean possible = true;

				for (int i = 0; i < str.length(); i++) {
					if (map.get(str.charAt(i)) == null) continue; // 독립스킬일 경우 상관없음
					if (before == -1 && map.get(str.charAt(i)) == 0)
						before = map.get(str.charAt(i)); // 스킬트리에 있는 스킬 처음 등장(0번스킬이어야함)
					else {
						if (before + 1 == map.get(str.charAt(i))) {
							before = map.get(str.charAt(i));
							continue; // 선행스킬을 배운 뒤라면
						} else possible = false; // 선행스킬을 배우지 않음

					}
				}

				if (possible) answer++;
			}

			return answer;
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		String skill = "CBD";
		String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};
		int answer = solution.solution(skill, skill_trees);
		System.out.println(answer);
	}

}
