/*
정확성 테스트

테스트 1 〉	통과 (0.18ms, 52.3MB)
테스트 2 〉	통과 (0.19ms, 52.7MB)
테스트 3 〉	통과 (0.23ms, 52.3MB)
테스트 4 〉	통과 (0.32ms, 52.2MB)
테스트 5 〉	통과 (0.57ms, 52.1MB)
테스트 6 〉	통과 (0.36ms, 52.1MB)
테스트 7 〉	통과 (0.42ms, 53.4MB)
테스트 8 〉	통과 (0.79ms, 51.9MB)
테스트 9 〉	통과 (0.41ms, 53.2MB)
테스트 10 〉	통과 (0.22ms, 53MB)

효율성 테스트

테스트 1 〉	통과 (8.36ms, 53.2MB)
테스트 2 〉	통과 (4.15ms, 53.5MB)
테스트 3 〉	통과 (4.45ms, 53.5MB)
테스트 4 〉	통과 (5.35ms, 54.3MB)
테스트 5 〉	통과 (6.74ms, 52.7MB)
테스트 6 〉	통과 (9.79ms, 53.3MB)
테스트 7 〉	통과 (6.36ms, 52.9MB)
테스트 8 〉	통과 (8.79ms, 53MB)
테스트 9 〉	통과 (7.13ms, 52.6MB)
테스트 10 〉	통과 (7.23ms, 52.5MB)

time : 1 Hour 0 Minute

풀이
bfs 탐색으로 풀이
q 에 넣을떄가 아니라 나올때 visit 체크를 하며 나오면서 방문 처리 되어 있으면 continue
방문 처리 안되어 있으면 방문처리 해준후 다음 포인트에 현재 포인트의 숫자를 더해준다.
q 를 비어있을때까지 돌린후 학교위치의 값을 출력

*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/42898
package algo_7월4주;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_P_L3_42898_등굣길 {
	static int div = 1000000007;
	static int result[][];
	static int di[] = { 0, 0, 1, -1 };
	static int dj[] = { 1, -1, 0, 0 };

	public static void main(String[] args) {
		int m = 4;
		int n = 3;
		int puddles[][] = { { 2, 2 } };
		System.out.println(solution(m, n, puddles));
	}

	public static int solution(int m, int n, int[][] puddles) {
		result = new int[m][n];
		int num[][] = new int[m][n];
		num[0][0] = 1;
		boolean map[][] = new boolean[m][n];
		int len = puddles.length;
		for (int i = 0; i < len; i++) {
			int a = puddles[i][0];
			int b = puddles[i][1];
			map[a - 1][b - 1] = true;
		}
		Queue<Integer> q = new LinkedList<>();
		q.add(0);
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int tmp = q.poll();
				int a = tmp / 1000;
				int b = tmp % 1000;
				if (map[a][b]) {
					continue;
				}
				map[a][b] = true;
				int in = num[a][b];
				for (int j = 0; j < 4; j++) {
					int ni = a + di[j];
					int nj = b + dj[j];
					if (ni < 0 || ni >= m || nj < 0 || nj >= n || map[ni][nj]) {
						continue;
					}
					num[ni][nj] = (num[ni][nj] + in) % div;
					q.add(ni * 1000 + nj);
				}

			}
		}
		return num[m - 1][n - 1];
	}

}
