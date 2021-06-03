/*
테스트 1 〉	통과 (0.03ms, 53.4MB)
테스트 2 〉	통과 (0.03ms, 52.6MB)
테스트 3 〉	통과 (0.03ms, 52.9MB)
테스트 4 〉	통과 (0.03ms, 52.3MB)
테스트 5 〉	통과 (0.04ms, 52.7MB)
테스트 6 〉	통과 (0.04ms, 52.3MB)
테스트 7 〉	통과 (0.03ms, 52.2MB)
테스트 8 〉	통과 (0.04ms, 52.3MB)
테스트 9 〉	통과 (0.04ms, 54MB)
테스트 10 〉	통과 (0.03ms, 52MB)
테스트 11 〉	통과 (0.04ms, 52.7MB)
 * 1H
 * https://programmers.co.kr/learn/courses/30/lessons/42860?language=java
 */
package PROGRAMMERS;

public class Solution_P_L2_42860_조이스틱_정세린 {
	static class Solution {
		int[] cnt;
		int len;
	
		public int solution(String name) {
			int answer = 0;
			len = name.length();
			cnt = new int[len];
			int done = len; // 만들어야하는 철자 수
	
			for (int i = 0; i < len; i++) {
				int up = name.charAt(i) - 'A';
				int down = ('Z' + 1) - name.charAt(i);
				cnt[i] = up < down ? up : down; // 작은 수 넣기
				if (cnt[i] == 0) done--; // A인것 제외
			}
	
			int dir = direction(0); // 처음 움직일 방향
			int pos = 0; // 처음 위치
			int sum = 0;
			int move = 0; // 좌우로 움직인 횟수
	
			while (done > 0) {
				if (cnt[pos] > 0) {
					sum += cnt[pos];
					sum += move;
					cnt[pos] = 0;
					move = 0;
					dir = direction(pos); // 방향 다시 정하기
					done--; // 만든후 -1
				}
				pos += dir;
				if (pos == -1) pos = len - 1;
				move++;
			}
			
			answer = sum;
			return answer;
		}
	
		// 0을 기준으로 오른쪽 왼쪽의 연속된 0의 개수
		// 더 적은 쪽으로 방향 결정
		int direction(int pos) {
			int zeroCntR = 0;
			int ri = pos + 1;
			if (ri == len) ri = 0;
			while (ri != pos) {
				if (cnt[ri] != 0) break;
				zeroCntR++;
				ri++;
				if (ri >= len) ri = 0;
			}
	
			int zeroCntL = 0;
			int li = pos - 1;
			if (li < 0) li = len - 1;
			while (li != pos) {
				if (cnt[li] != 0) break;
				zeroCntL++;
				li--;
				if (li == -1) li = len - 1;
			}
	
			return zeroCntR <= zeroCntL ? 1 : -1;
		}
	
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		String name = "JEROEN";
		int answer = solution.solution(name);
		System.out.println(answer);
	}

}
