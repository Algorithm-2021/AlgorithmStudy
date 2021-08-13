/*
테스트 1 〉	통과 (0.91ms, 60.5MB)
테스트 2 〉	통과 (12.16ms, 63.7MB)
테스트 3 〉	통과 (11.81ms, 59.8MB)
테스트 4 〉	통과 (0.86ms, 59.8MB)
테스트 5 〉	통과 (2.14ms, 60.6MB)
테스트 6 〉	통과 (2.35ms, 72.8MB)
테스트 7 〉	통과 (13.79ms, 73.7MB)
테스트 8 〉	통과 (11.71ms, 74.3MB)
테스트 9 〉	통과 (2.88ms, 58.1MB)
테스트 10 〉	통과 (1.41ms, 58.6MB)
테스트 11 〉	통과 (1.38ms, 71.2MB)
테스트 12 〉	통과 (12.11ms, 64.3MB)
테스트 13 〉	통과 (2.65ms, 71.3MB)
테스트 14 〉	통과 (1.15ms, 72.6MB)
테스트 15 〉	통과 (0.82ms, 69.6MB)
테스트 16 〉	통과 (0.94ms, 60.8MB)
테스트 17 〉	통과 (0.81ms, 72.1MB)
테스트 18 〉	통과 (19.95ms, 62.2MB)
테스트 19 〉	통과 (17.63ms, 61.8MB)
테스트 20 〉	통과 (18.38ms, 75.9MB)
테스트 21 〉	통과 (0.98ms, 73.1MB)
테스트 22 〉	통과 (0.80ms, 74.3MB)
 * 1H 10m
 * 종료시간 기준 정렬 후 완탐.
 * https://programmers.co.kr/learn/courses/30/lessons/17676
 */
package PROGRAMMERS;

import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution_P_L3_17676_1차추석트래픽_정세린 {
	static class Solution {
		class Log {
			String date;
			int start;
			int end;

			public Log(String date, int start, int end) {
				this.date = date;
				this.start = start;
				this.end = end;
			}
		}

		public int solution(String[] lines) {
			int answer = 0;
			int len = lines.length;
			Log[] logs = new Log[len];
			StringTokenizer st = null;

			for (int i = 0; i < len; i++) {
				st = new StringTokenizer(lines[i].substring(0, lines[i].length() - 1));
				String date = st.nextToken();
				double end = toSecond(st.nextToken()) * 1000;
				double start = end - Double.parseDouble(st.nextToken()) * 1000 + 1;

				logs[i] = new Log(date, (int) start, (int) end);
			}

			Arrays.sort(logs, new Comparator<Log>() {
				@Override
				public int compare(Log o1, Log o2) {
					int endSub = o1.end - o2.end;
					if (endSub != 0) return endSub;
					int startSub = o1.start - o2.start;
					return startSub;
				}
			});

			int max = 1;
			for (int i = 0; i < len; i++) {
				int cnt = 1;
				for (int j = i + 1; j < len; j++) {
					if (logs[i].end + 1000 > logs[j].start) cnt++;
				}
				max = Integer.max(max, cnt);
			}

			answer = max;
			return answer;
		}

		double toSecond(String time) {
			StringTokenizer st = new StringTokenizer(time, ":");
			double sec = 3600 * (Double.parseDouble(st.nextToken()));
			sec += 60 * (Double.parseDouble(st.nextToken()));
			sec += Double.parseDouble(st.nextToken());
			return sec;
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		String[] lines = {"2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s", "2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s", "2016-09-15 20:59:59.591 1.412s", "2016-09-15 21:00:00.464 1.466s", "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s", "2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s"};
		int answer = solution.solution(lines);
		System.out.println(answer);
	}

}
