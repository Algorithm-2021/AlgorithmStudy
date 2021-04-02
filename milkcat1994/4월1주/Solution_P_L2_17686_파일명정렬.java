import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * -파일명 정렬-
 * 
 * 테스트 1 〉	통과 (15.05ms, 54.4MB)
 * 테스트 2 〉	통과 (15.13ms, 53.1MB)
 * 테스트 3 〉	통과 (48.62ms, 56.2MB)
 * 테스트 4 〉	통과 (52.72ms, 55.4MB)
 * 테스트 5 〉	통과 (45.09ms, 56.4MB)
 * 테스트 6 〉	통과 (38.62ms, 55.2MB)
 * 테스트 7 〉	통과 (37.74ms, 54.8MB)
 * 테스트 8 〉	통과 (40.69ms, 53.7MB)
 * 테스트 9 〉	통과 (47.91ms, 54.5MB)
 * 테스트 10 〉	통과 (40.82ms, 55.2MB)
 * 테스트 11 〉	통과 (43.59ms, 54.6MB)
 * 테스트 12 〉	통과 (40.09ms, 53.7MB)
 * 테스트 13 〉	통과 (27.63ms, 55.5MB)
 * 테스트 14 〉	통과 (30.73ms, 55.5MB)
 * 테스트 15 〉	통과 (35.57ms, 54.5MB)
 * 테스트 16 〉	통과 (38.73ms, 54.8MB)
 * 테스트 17 〉	통과 (29.99ms, 54.7MB)
 * 테스트 18 〉	통과 (43.88ms, 54.4MB)
 * 테스트 19 〉	통과 (31.84ms, 55.8MB)
 * 테스트 20 〉	통과 (39.19ms, 55.6MB)
 * 
 * 풀이 시간 : 35M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/17686
public class Solution_P_L2_17686_파일명정렬 {
	public String[] solution(String[] files) {
		String[] answer = {};
		String head, number, tail;
		List<File> list = new ArrayList<>();
		for(String str : files) {
			int firstIntIdx = findInteger(str);
			head = str.substring(0,firstIntIdx);
			
			String back = str.substring(firstIntIdx);
			int secondCharacterIdx = findCharacter(back);
			number = back.substring(0, secondCharacterIdx);
			
			tail="";
			// tail이 있다면
			if(back.length()-1 != secondCharacterIdx) {
				tail = back.substring(secondCharacterIdx);
			}
			list.add(new File(head, number, tail));
		}
		
		Collections.sort(list, (o1, o2) -> {
			int n1,n2;
			n1 = Integer.parseInt(o1.number);
			n2 = Integer.parseInt(o2.number);
			String low1, low2;
			low1 = o1.head.toLowerCase();
			low2 = o2.head.toLowerCase();
			if(low1.equals(low2)) {
				if(n1==n2) {
					// 숫자까지 같다면 자리 바꾸지 않기
					return 0;
				}
				else {
					return Integer.compare(n1, n2);
				}
			}
			else {
				return low1.toLowerCase().compareTo(low2.toLowerCase());
			}
		});
		
		answer = new String[list.size()];
		for(int idx=0; idx<list.size(); ++idx) {
			answer[idx] = list.get(idx).toString();
		}
		return answer;
	}
	
	int findCharacter(String str) {
		for(int i=0; i<str.length(); ++i) {
			if('0' > str.charAt(i) || str.charAt(i) > '9') {
				return i;
			}
		}
		return str.length();
	}
	
	int findInteger(String str) {
		for(int i=0; i<str.length(); ++i) {
			if('0' <= str.charAt(i) && str.charAt(i) <= '9') {
				return i;
			}
		}
		return str.length();
	}
	
	public class File{
		String head, number, tail;
		
		File(String head, String number, String tail){
			this.head = head;
			this.number = number;
			this.tail = tail;
		}
		
		@Override
		public String toString() {
			return head+number+tail;
		}
	}

	public static void main(String[] args) {
		Solution_P_L2_17686_파일명정렬 sol = new Solution_P_L2_17686_파일명정렬();
		String[] files = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
		String[] answer = sol.solution(files);
		for(String str : answer)
			System.out.println(str);
	}
}