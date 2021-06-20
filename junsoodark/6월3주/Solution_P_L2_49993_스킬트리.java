/*
정확성 테스트

테스트 1 〉	통과 (0.12ms, 52.8MB)
테스트 2 〉	통과 (0.13ms, 52MB)
테스트 3 〉	통과 (0.14ms, 53MB)
테스트 4 〉	통과 (0.13ms, 52.8MB)
테스트 5 〉	통과 (0.12ms, 53.4MB)
테스트 6 〉	통과 (0.09ms, 51.8MB)
테스트 7 〉	통과 (0.15ms, 52.5MB)
테스트 8 〉	통과 (0.11ms, 51.9MB)
테스트 9 〉	통과 (0.13ms, 51.9MB)
테스트 10 〉	통과 (0.12ms, 53.4MB)
테스트 11 〉	통과 (0.15ms, 52.3MB)
테스트 12 〉	통과 (0.13ms, 52.3MB)
테스트 13 〉	통과 (0.15ms, 53.3MB)
테스트 14 〉	통과 (0.15ms, 52.4MB)

time : 0 Hour 20 Minute

풀이
hash를 이용하여 스킬트리에 있는 스킬들을 추출한후 추출한 길이만큼 스킬트리와 앞부터 비교하여 판단

*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/49993#fn1
package algo_6월3주;

import java.util.HashSet;

public class Solution_P_L2_49993_스킬트리 {
	public static void main(String[] args) {
		String skill = "CBD";
		String skill_trees[] = { "BACDE", "CBADF", "AECB", "BDA" };
		System.out.println(solution(skill, skill_trees));
	}

	public static int solution(String skill, String[] skill_trees) {
		HashSet<Character> hs = new HashSet<>();
		char arr[] = skill.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			hs.add(arr[i]);
		}
		int answer = 0;
		for (int i = 0; i < skill_trees.length; i++) {
			char tmpArr[] = skill_trees[i].toCharArray();
			int cnt = 0;
			boolean find = true;
			for (int j = 0; j < tmpArr.length; j++) {
				if (hs.contains(tmpArr[j])) {
					if (arr[cnt] != tmpArr[j]) {
						find = false;
						break;
					}
					cnt++;
				}
			}
			if (find) {
				answer++;
			}
		}
		return answer;
	}

}
