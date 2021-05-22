/*
정확성 테스트

테스트 1 〉	통과 (1.15ms, 52.9MB)
테스트 2 〉	통과 (0.95ms, 52.1MB)
테스트 3 〉	통과 (5.61ms, 52.6MB)
테스트 4 〉	통과 (36.56ms, 55.5MB)
테스트 5 〉	통과 (45.07ms, 57MB)
테스트 6 〉	통과 (62.61ms, 57.5MB)
테스트 7 〉	통과 (69.33ms, 59.4MB)
테스트 8 〉	통과 (110.29ms, 60.3MB)
테스트 9 〉	통과 (108.20ms, 60.3MB)
테스트 10 〉	통과 (118.30ms, 60.1MB)
테스트 11 〉	통과 (60.04ms, 58MB)
테스트 12 〉	통과 (67.35ms, 57.9MB)
테스트 13 〉	통과 (70.39ms, 59.2MB)
테스트 14 〉	통과 (88.63ms, 57.9MB)
테스트 15 〉	통과 (78.85ms, 57.3MB)
테스트 16 〉	통과 (64.76ms, 57.8MB)
테스트 17 〉	통과 (60.99ms, 56.9MB)
테스트 18 〉	통과 (84.89ms, 57.2MB)

효율성 테스트

테스트 1 〉	실패 (시간 초과)
테스트 2 〉	실패 (시간 초과)
테스트 3 〉	실패 (시간 초과)
테스트 4 〉	실패 (시간 초과)

time : 1 Hour 0 Minute

풀이
info 배열 m 과 query 배열 n 을 각가 대조해서 비교한다. O(mn)이어서 효율성에서 통과를 못함

*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/72412
package algo_5월3주;

import java.util.Arrays;

public class Solution_P_L2_72412_순위검색 {
	public static void main(String[] args) {
		String info[] = { "java backend junior pizza 150", "python frontend senior chicken 210",
				"python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80",
				"python backend senior chicken 50" };
		String query[] = { "java and backend and junior and pizza 100",
				"python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250",
				"- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150" };
		System.out.println(Arrays.toString(solution(info, query)));
	}

	public static int[] solution(String[] info, String[] query) {
		String infoSplit[][] = new String[info.length][];
		for (int i = 0; i < infoSplit.length; i++) {
			infoSplit[i] = info[i].split(" ");
		}
		int[] answer = new int[query.length];

		for (int i = 0; i < answer.length; i++) {
			String str1[] = query[i].split(" and ");
			String str2[] = str1[3].split(" ");
			int cnt = 0;
			for (int j = 0; j < info.length; j++) {
				boolean notSame = false;
				for (int j2 = 0; j2 < 5; j2++) {
					if (notSame) {
						break;
					}
					if (j2 == 3) {
						if (str2[0].equals("-")) {
							continue;
						} else if (!infoSplit[j][j2].equals(str2[0])) {
							notSame = true;
						}
					} else if (j2 == 4) {
						if (str2[1].equals("-")) {
							continue;
						} else {
							int hapScore = Integer.parseInt(str2[1]);
							int score = Integer.parseInt(infoSplit[j][4]);
							if (score >= hapScore) {
								continue;
							} else {
								notSame = true;
								break;
							}
						}
					} else {
						if (str1[j2].equals("-")) {
							continue;
						} else if (!infoSplit[j][j2].equals(str1[j2])) {
							notSame = true;
						}
					}
				}
				if (!notSame) {
					cnt++;
				}
			}
			answer[i] = cnt;
		}

		return answer;
	}
}
