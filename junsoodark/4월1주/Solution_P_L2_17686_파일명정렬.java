/*

테스트 1 〉	통과 (0.92ms, 52.7MB)
테스트 2 〉	통과 (0.96ms, 51.8MB)
테스트 3 〉	통과 (15.40ms, 54MB)
테스트 4 〉	통과 (16.54ms, 53.7MB)
테스트 5 〉	통과 (11.90ms, 53.9MB)
테스트 6 〉	통과 (13.42ms, 53.9MB)
테스트 7 〉	통과 (14.08ms, 53.2MB)
테스트 8 〉	통과 (16.70ms, 53.9MB)
테스트 9 〉	통과 (17.49ms, 53.9MB)
테스트 10 〉	통과 (13.40ms, 54MB)
테스트 11 〉	통과 (13.30ms, 53.6MB)
테스트 12 〉	통과 (16.32ms, 55.3MB)
테스트 13 〉	통과 (14.15ms, 53.5MB)
테스트 14 〉	통과 (14.97ms, 54.6MB)
테스트 15 〉	통과 (18.42ms, 53.9MB)
테스트 16 〉	통과 (13.33ms, 53.7MB)
테스트 17 〉	통과 (14.14ms, 53.8MB)
테스트 18 〉	통과 (12.17ms, 53.8MB)
테스트 19 〉	통과 (14.07ms, 54.4MB)
테스트 20 〉	통과 (13.61ms, 54.8MB)


time : 1 Hour 0 Minute

풀이
우선순위 큐에 넣어 정렬 조건을 넣어서 출력한다.

*/

//출처 : https://programmers.co.kr/learn/courses/30/lessons/17686
package algo_4월1주;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution_P_L2_17686_파일명정렬 {
	public static void main(String[] args) {
		String[] str = new String[2];
		str[1] = "IMG01.GIF";
		str[0] = "img1.png";
		System.out.println(getHead(str[0]));
		System.out.println(getHead(str[1]));
		System.out.println(getNumber(str[0]));
		System.out.println(getNumber(str[1]));
		System.out.println(Arrays.toString(solution(str)));
	}

	public static String[] solution(String[] files) {
		String[] answer = {};
		PriorityQueue<FileInfo> q = new PriorityQueue<FileInfo>(new Comparator<FileInfo>() {

			@Override
			public int compare(FileInfo o1, FileInfo o2) {
				if (o1.head.compareTo(o2.head) == 0) {
					if (o1.number == o2.number) {
						return o1.idx - o2.idx;
					}
					return o1.number - o2.number;
				} else {
					return o1.head.compareTo(o2.head);
				}
			}

		});
		for (int i = 0; i < files.length; i++) {
			q.offer(new FileInfo(i, files[i], getHead(files[i]), getNumber(files[i])));
		}
		answer = new String[files.length];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = q.poll().name;
		}
		return answer;
	}

	public static String getHead(String str) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
				return sb.toString().toLowerCase();
			}
			sb.append(str.charAt(i));
		}
		return sb.toString().toLowerCase();
	}

	public static int getNumber(String str) {
		StringBuilder sb = new StringBuilder();
		boolean start = false;
		int cnt = 0;
		for (int i = 0; i < str.length(); i++) {
			if (cnt >= 5) {
				break;
			}
			if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
				start = true;
				cnt++;
				sb.append(str.charAt(i));
				continue;
			}
			if (start) {
				return Integer.parseInt(sb.toString());
			}
		}
		return Integer.parseInt(sb.toString());
	}

	public static class FileInfo {
		int idx; // 입력 순서
		String name, head; // 파일 이름, 헤드
		int number; // 넘버

		FileInfo(int idx, String name, String head, int number) {
			this.idx = idx;
			this.name = name;
			this.head = head;
			this.number = number;
		}
	}

}
