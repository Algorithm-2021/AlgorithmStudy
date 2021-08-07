/*
 * -입국심사-
 * 
 * 테스트 1 〉	통과 (0.04ms, 52.3MB)
 * 테스트 2 〉	통과 (0.10ms, 56.2MB)
 * 테스트 3 〉	통과 (1.82ms, 53.1MB)
 * 테스트 4 〉	통과 (34.30ms, 57.2MB)
 * 테스트 5 〉	통과 (53.99ms, 59.6MB)
 * 테스트 6 〉	통과 (35.37ms, 57.3MB)
 * 테스트 7 〉	통과 (43.80ms, 58MB)
 * 테스트 8 〉	통과 (54.36ms, 57.2MB)
 * 테스트 9 〉	통과 (0.06ms, 52.1MB)
 * 
 * 풀이 시간 : 25M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/43238
public class Solution_P_L3_43238_입국심사 {
	public long solution(int n, int[] times) {
		long min=0, max=0;
		for(int i=0; i<times.length; ++i) {
			max = Math.max(max, times[i]);
		}
		
		max *= n;
		
		long cnt;
		long mid;
		while(min<=max) {
			cnt=0;
			mid = (min+max)/2;
			for(int i=0; i<times.length; ++i) {
				cnt += mid/times[i];
				if(n < cnt) {
					break;
				}
			}
			
			if(n <= cnt) {
				max = mid-1;
			}
			else {
				min = mid+1;
			}
		}
		
		return min;
	}


	public static void main(String[] args) {
		Solution_P_L3_43238_입국심사 sol = new Solution_P_L3_43238_입국심사();
		int n = 6;
		int[] times = {7,10};
		long answer = sol.solution(n, times);
		System.out.println(answer);
	}
}