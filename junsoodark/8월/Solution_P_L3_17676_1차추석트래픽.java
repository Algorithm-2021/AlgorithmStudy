/*
정확성 테스트

테스트 1 〉	통과 (0.53ms, 70.9MB)
테스트 2 〉	통과 (21.90ms, 75.1MB)
테스트 3 〉	통과 (19.91ms, 73.9MB)
테스트 4 〉	통과 (0.41ms, 59.1MB)
테스트 5 〉	통과 (2.26ms, 72.6MB)
테스트 6 〉	통과 (2.21ms, 73.4MB)
테스트 7 〉	통과 (25.59ms, 76.5MB)
테스트 8 〉	통과 (27.48ms, 75.1MB)
테스트 9 〉	통과 (2.34ms, 58.3MB)
테스트 10 〉	통과 (0.50ms, 59.5MB)
테스트 11 〉	통과 (0.55ms, 68MB)
테스트 12 〉	통과 (23.36ms, 74.2MB)
테스트 13 〉	통과 (2.17ms, 61.2MB)
테스트 14 〉	통과 (0.42ms, 71MB)
테스트 15 〉	통과 (0.46ms, 68.9MB)
테스트 16 〉	통과 (0.44ms, 67.8MB)
테스트 17 〉	통과 (0.42ms, 72.9MB)
테스트 18 〉	통과 (28.89ms, 75.4MB)
테스트 19 〉	통과 (41.84ms, 64.4MB)
테스트 20 〉	통과 (36.57ms, 74MB)
테스트 21 〉	통과 (0.39ms, 74.1MB)
테스트 22 〉	통과 (0.38ms, 71.1MB)

time : 1 Hour 20 Minute

풀이
시작 시간과 끝시간을 배열로 만든 후 각 끝시간을 기준으로 1초 구간을 지정하여
구간내에 들어오는 구간의 갯수를 세어 최대 갯수를 반환한다.


*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/17676
package algo_8월;

import java.util.StringTokenizer;

public class Solution_P_L3_17676_1차추석트래픽 {
	public static void main(String[] args) {
		String[] lines = { "2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s",
				"2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s", "2016-09-15 20:59:59.591 1.412s",
				"2016-09-15 21:00:00.464 1.466s", "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s",
				"2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s" };
		System.out.println(solution(lines));
	}

	public static int solution(String[] lines) {
		int max = 0;
		int arr[][] = new int[lines.length][2];
		for (int i = 0; i < lines.length; i++) {
			StringTokenizer st = new StringTokenizer(lines[i]);
			st.nextToken();
			String str1 = st.nextToken();
			String str2 = st.nextToken();
			String str[] = str1.split(":");
			arr[i][1] = (Integer.parseInt(str[0]) * 3600000);
			arr[i][1] += (Integer.parseInt(str[1]) * 60000);
			arr[i][1] += (int) (Double.parseDouble(str[2]) * 1000);
			arr[i][0] = arr[i][1] - (int) ((Double.parseDouble(str2.substring(0, str2.length() - 1)) - 0.001) * 1000);
		}
		for (int i = 0; i < lines.length; i++) {
			int start = arr[i][1];
			int end = start + 999;
			int cnt = 0;
			for (int j = 0; j < lines.length; j++) {
				if ((start >= arr[j][0] && start <= arr[j][1]) || (end >= arr[j][0] && end <= arr[j][1])
						|| (start <= arr[j][0] && end >= arr[j][0]) || (start <= arr[j][1] && end >= arr[j][1])) {
					cnt++;
				}
			}
			if (max < cnt) {
				max = cnt;
			}
		}
		return max;
	}
}
