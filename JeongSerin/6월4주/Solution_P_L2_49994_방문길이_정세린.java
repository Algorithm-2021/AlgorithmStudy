/*
테스트 1 〉	통과 (0.09ms, 52.9MB)
테스트 2 〉	통과 (0.04ms, 52.4MB)
테스트 3 〉	통과 (0.03ms, 52.1MB)
테스트 4 〉	통과 (0.13ms, 52.9MB)
테스트 5 〉	통과 (0.12ms, 51.8MB)
테스트 6 〉	통과 (0.09ms, 54MB)
테스트 7 〉	통과 (0.06ms, 52.4MB)
테스트 8 〉	통과 (0.09ms, 53.2MB)
테스트 9 〉	통과 (0.08ms, 52MB)
테스트 10 〉	통과 (0.10ms, 52.7MB)
테스트 11 〉	통과 (0.10ms, 52.7MB)
테스트 12 〉	통과 (0.35ms, 52.8MB)
테스트 13 〉	통과 (0.28ms, 52.9MB)
테스트 14 〉	통과 (0.15ms, 51.8MB)
테스트 15 〉	통과 (0.13ms, 52.5MB)
테스트 16 〉	통과 (0.42ms, 51.9MB)
테스트 17 〉	통과 (0.42ms, 53.1MB)
테스트 18 〉	통과 (0.42ms, 53.2MB)
테스트 19 〉	통과 (0.63ms, 52.5MB)
테스트 20 〉	통과 (0.42ms, 53.4MB)
 * 30m
 * HashSet
 * https://programmers.co.kr/learn/courses/30/lessons/49994
 */
package PROGRAMMERS;

import java.util.HashSet;

public class Solution_P_L2_49994_방문길이_정세린 {
	static class Solution {
		HashSet<Integer> set = new HashSet<>(); // 100i + j, 작은 값이 앞으로 (*10000)

		public int solution(String dirs) {
			int answer = 0;
			int ipos = 5; // 현재 위치 // (0, 0)좌측하단, (10, 10) 우측상단
			int jpos = 5;

			for (int i = 0; i < dirs.length(); i++) {
				int curpos = ipos * 100 + jpos;
				switch (dirs.charAt(i)) { // 이동
				case 'U':
					if (ipos + 1 > 10) continue;
					ipos += 1;
					break;
				case 'D':
					if (ipos - 1 < 0) continue;
					ipos -= 1;
					break;
				case 'R':
					if (jpos + 1 > 10) continue;
					jpos += 1;
					break;
				case 'L':
					if (jpos - 1 < 0) continue;
					jpos -= 1;
					break;
				} // end of switch ~ case

				int nextpos = ipos * 100 + jpos;
				int way = (curpos < nextpos) ? curpos * 10000 + nextpos : nextpos * 10000 + curpos;
				set.add(way);
			}

			answer = set.size();
			return answer;
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		String dirs = "ULURRDLLU";
		int answer = solution.solution(dirs);
		System.out.println(answer);
	}

}
