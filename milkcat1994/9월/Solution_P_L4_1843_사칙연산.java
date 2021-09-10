import java.util.Arrays;

/*
 * -사칙연산-
 * 1. 구간합을 정해진 사칙연산에 따라 모두 구하는 방식
 * 
 * 참고 : https://velog.io/@longroadhome/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-LV.4-%EC%82%AC%EC%B9%99%EC%97%B0%EC%82%B0
 * 
 * 테스트 6 〉	통과 (0.09ms, 70.8MB)
 * 테스트 7 〉	통과 (0.08ms, 57.9MB)
 * 테스트 8 〉	통과 (0.09ms, 70.5MB)
 * 테스트 9 〉	통과 (0.05ms, 70.5MB)
 * 테스트 10 〉	통과 (0.05ms, 58.5MB)
 * 
 * 효율성  테스트
 * 테스트 1 〉	통과 (20.66ms, 54.1MB)
 * 테스트 2 〉	통과 (18.38ms, 53.1MB)
 * 테스트 3 〉	통과 (18.02ms, 52.6MB)
 * 테스트 4 〉	통과 (22.31ms, 53MB)
 * 테스트 5 〉	통과 (15.42ms, 53.5MB)
 * 테스트 6 〉	통과 (15.07ms, 53.5MB)
 * 테스트 7 〉	통과 (16.33ms, 53.9MB)
 * 테스트 8 〉	통과 (15.30ms, 53.2MB)
 * 
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/1843
public class Solution_P_L4_1843_사칙연산 {
	public int solution(String arr[]) {
		int arrLength = arr.length;
		int operatorCnt = arrLength / 2;
		int numCnt = operatorCnt+1;
		
		int[][] maxMemo = new int[numCnt][numCnt];
		int[][] minMemo = new int[numCnt][numCnt];
		
		for (int row = 0; row < numCnt; ++row) {
			Arrays.fill(maxMemo[row], Integer.MIN_VALUE);
			Arrays.fill(minMemo[row], Integer.MAX_VALUE);
		}
		
		for(int i = 0; i < numCnt; ++i) {
			maxMemo[i][i] = minMemo[i][i] = Integer.parseInt(arr[i*2]);
		}

		for(int opCnt = 1; opCnt <= operatorCnt; ++opCnt) {
			for(int start = 0; start < numCnt - opCnt; ++start) {
				int end = start + opCnt;
				for(int k = start; k<end; ++k) {
					if(arr[k*2+1].equals("+")) {
						maxMemo[start][end] = Math.max(maxMemo[start][end], maxMemo[start][k] + maxMemo[k+1][end]);
						minMemo[start][end] = Math.min(minMemo[start][end], minMemo[start][k] + minMemo[k+1][end]);
					}
					else {
						maxMemo[start][end] = Math.max(maxMemo[start][end], maxMemo[start][k] - minMemo[k+1][end]);
						minMemo[start][end] = Math.min(minMemo[start][end], minMemo[start][k] - maxMemo[k+1][end]);
					}
				}
			}
		}
		
		return maxMemo[0][numCnt-1];
	}

	public static void main(String[] args) {
		Solution_P_L4_1843_사칙연산 sol = new Solution_P_L4_1843_사칙연산();
		String[] arr = {"5", "-", "3", "+", "1", "+", "2", "-", "4"};
		int answer = sol.solution(arr);
		System.out.println(answer);
	}
}