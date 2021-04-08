/*
테스트 16 〉	통과 (35.41ms, 54.1MB)
테스트 17 〉	통과 (38.64ms, 54.7MB)
테스트 18 〉	통과 (42.61ms, 55.9MB)
테스트 19 〉	통과 (40.94ms, 55.2MB)
테스트 20 〉	통과 (33.17ms, 54.2MB)
 * 30m
 * PriorityQueue
 */
package PROGRAMMERS;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution_P_L2_17686_파일명정렬_정세린 {

	static class Solution {
		class FileInfo {
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

		public String[] solution(String[] files) {
			int len = files.length;
			String[] answer = new String[len];
			PriorityQueue<FileInfo> pq = new PriorityQueue<>(new Comparator<FileInfo>() {
				@Override
				public int compare(FileInfo o1, FileInfo o2) {
					int head = o1.head.compareTo(o2.head); // 1순위 head
					if (head != 0) return head;
					int number = o1.number - o2.number; // 2순위 number
					if (number != 0) return number;
					return o1.idx - o2.idx; // 3순위 입력순서
				}
			});

			for (int i = 0; i < len; i++) {
				String head = "";
				String number = "";
				boolean headDone = false; 
				for (int j = 0; j < files[i].length(); j++) {
					char ch = files[i].charAt(j);
					if (!Character.isDigit(ch)) { // head입력
						if (headDone) break; // number, head 입력 마친 상태
						head += ch;
					} else if (Character.isDigit(ch)) { // number입력
						number += ch;
						headDone = true; // 헤드 입력이 끝남
					}
				}
				pq.offer(new FileInfo(i, files[i], head.toLowerCase(), Integer.parseInt(number)));
			} // end of input

			for (int i = 0; i < len; i++) {
				answer[i] = pq.poll().name;
			}
			
			return answer;
		}

	} // end of Solution

	public static void main(String[] args) {
		Solution s = new Solution();
		String[] files = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
//		String[] files = {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"};
		String[] ans = s.solution(files);
		System.out.println(Arrays.toString(ans));
	}

}
