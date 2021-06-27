/*
정확성 테스트

테스트 1 〉	통과 (0.07ms, 52.2MB)
테스트 2 〉	통과 (0.05ms, 52.2MB)
테스트 3 〉	통과 (0.05ms, 52.9MB)
테스트 4 〉	통과 (0.06ms, 51.9MB)
테스트 5 〉	통과 (0.08ms, 52.2MB)
테스트 6 〉	통과 (0.07ms, 52.6MB)
테스트 7 〉	통과 (0.07ms, 53MB)
테스트 8 〉	통과 (0.06ms, 52.2MB)
테스트 9 〉	통과 (0.04ms, 52.3MB)
테스트 10 〉	통과 (0.06ms, 51.8MB)
테스트 11 〉	통과 (0.07ms, 51.7MB)
테스트 12 〉	통과 (0.07ms, 52.1MB)
테스트 13 〉	통과 (0.07ms, 52.6MB)
테스트 14 〉	통과 (0.08ms, 52.4MB)
테스트 15 〉	통과 (0.08ms, 52.6MB)
테스트 16 〉	통과 (0.12ms, 51.8MB)
테스트 17 〉	통과 (0.12ms, 52.1MB)
테스트 18 〉	통과 (0.11ms, 52.2MB)
테스트 19 〉	통과 (0.12ms, 53.1MB)
테스트 20 〉	통과 (0.09ms, 52.6MB)

time : 0 Hour 20 Minute

풀이
방문 배열을 이용하여 안지나간 길이면 + 범위 밖이면 skip 하고 지나간 길이면 좌표만 변경

*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/49994
package algo_6월4주;

public class Solution_P_L2_49994_방문길이 {
	public static void main(String[] args) {
		String dirs = "LLLLRLRLRLL";
		System.out.println(solution(dirs));
	}

	public static int solution(String dirs) {
		boolean visited[][][] = new boolean[11][11][4];
		int x = 5;
		int y = 5;
		char dir[] = dirs.toCharArray();
		int answer = 0;
		for (char c : dir) {
			int nx = x;
			int ny = y;
			if (c == 'U') {
				ny = ny + 1;
				if (ny > 10) {
					continue;
				}
				if (!visited[nx][ny][2]) {
					answer++;
					visited[nx][ny][2] = true;
					visited[x][y][0] = true;
				}
			} else if (c == 'D') {
				ny = ny - 1;
				if (ny < 0) {
					continue;
				}
				if (!visited[nx][ny][0]) {
					answer++;
					visited[nx][ny][0] = true;
					visited[x][y][2] = true;
				}
			} else if (c == 'R') {
				nx = nx + 1;
				if (nx > 10) {
					continue;
				}
				if (!visited[nx][ny][3]) {
					answer++;
					visited[nx][ny][3] = true;
					visited[x][y][1] = true;
				}
			} else if (c == 'L') {
				nx = nx - 1;
				if (nx < 0) {
					continue;
				}
				if (!visited[nx][ny][1]) {
					answer++;
					visited[nx][ny][1] = true;
					visited[x][y][3] = true;
				}
			}
			x = nx;
			y = ny;
		}
		return answer;
	}

}
