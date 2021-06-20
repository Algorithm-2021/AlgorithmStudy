/*
정확성 테스트

테스트 1 〉	통과 (0.26ms, 54.7MB)
테스트 2 〉	통과 (0.26ms, 52.2MB)
테스트 3 〉	통과 (0.28ms, 53.1MB)
테스트 4 〉	통과 (0.23ms, 53.3MB)
테스트 5 〉	통과 (0.25ms, 52.3MB)
테스트 6 〉	통과 (0.24ms, 51.7MB)
테스트 7 〉	통과 (0.52ms, 53.9MB)
테스트 8 〉	통과 (0.42ms, 53MB)
테스트 9 〉	통과 (0.23ms, 52.2MB)
테스트 10 〉	통과 (0.24ms, 52MB)
테스트 11 〉	통과 (0.26ms, 52.5MB)
테스트 12 〉	통과 (0.38ms, 52.5MB)
테스트 13 〉	통과 (0.29ms, 52.9MB)
테스트 14 〉	통과 (0.28ms, 52MB)

time : 0 Hour 20 Minute

풀이
hash를 이용하여 스킬트리에 있는 스킬들을 추출한후 추출한 길이만큼 스킬트리와 앞부터 비교하여 판단

*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/49993#fn1
package algo_6월3주;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

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
			Queue<Character> q = new LinkedList<>();
			for (int j = 0; j < tmpArr.length; j++) {
				if (hs.contains(tmpArr[j])) {
					q.offer(tmpArr[j]);
				}
			}
			int size = q.size();
			boolean find = true;
			for (int j = 0; j < size; j++) {
				if (arr[j] != q.poll()) {
					find = false;
					break;
				}
			}
			if (find) {
				answer++;
			}
		}
		return answer;
	}

}
