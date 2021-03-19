/*
 * 테스트 1 〉		통과 (2.91ms, 52.5MB)
 * 테스트 2 〉		통과 (3.00ms, 51.9MB)
 * 테스트 3 〉		통과 (3.13ms, 52.4MB)
 * 테스트 4 〉		통과 (4.00ms, 53.1MB)
 * 테스트 5 〉		통과 (8.74ms, 54.3MB)
 * 테스트 6 〉		통과 (8.21ms, 53.3MB)
 * 테스트 7 〉		통과 (11.50ms, 54.1MB)
 * 테스트 8 〉		통과 (7.40ms, 53.9MB)
 * 테스트 9 〉		통과 (8.47ms, 53.7MB)
 * 테스트 10 〉	통과 (8.64ms, 56.4MB)
 * 테스트 11 〉	통과 (5.65ms, 54MB)
 * 테스트 12 〉	통과 (5.84ms, 54.2MB)
 * 테스트 13 〉	통과 (8.72ms, 53.4MB)
 * 테스트 14 〉	통과 (8.63ms, 54.4MB)
 * 테스트 15 〉	통과 (2.98ms, 52.3MB)
 * 테스트 16 〉	통과 (3.06ms, 52.8MB)
 * 테스트 17 〉	통과 (4.72ms, 52.3MB)
 * 테스트 18 〉	통과 (5.16ms, 52.4MB)
 * 테스트 19 〉	통과 (10.04ms, 53.9MB)
 * 테스트 20 〉	통과 (7.08ms, 53.6MB)
 * 테스트 21 〉	통과 (9.03ms, 54.2MB)
 * 테스트 22 〉	통과 (7.36ms, 54.4MB)
 * 테스트 23 〉	통과 (8.87ms, 54.1MB)
 * 테스트 24 〉	통과 (11.28ms, 54.2MB)
 * 테스트 25 〉	통과 (193.88ms, 131MB)
 * 테스트 26 〉	통과 (221.07ms, 138MB)
 * 테스트 27 〉	통과 (217.06ms, 140MB)
 * 테스트 28 〉	통과 (201.68ms, 145MB)
 * 테스트 29 〉	통과 (245.38ms, 142MB)
 * 테스트 30 〉	통과 (179.34ms, 133MB)
 * 테스트 31 〉	통과 (228.69ms, 140MB)
 * 테스트 32 〉	통과 (146.88ms, 121MB)
 * 
 * time : 1 Hour 0 Minute
 * 
 * 풀이
 * 각각의 String을 " "로 split 한후 각각의 단어마다 분기마다 enter, leave, change를 구현하여 hashmap에 저장하였다가 나중에 일괄 출력
 * 
 */

package algo_3월3주;

import java.util.HashMap;

//출처 : https://programmers.co.kr/learn/courses/30/lessons/42888
public class Solution_프로그래머스_오픈채팅방 {

	public static void main(String[] args) {
		String str[] = { "Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo",
				"Change uid4567 Ryan" };
		String result[] = solution(str);
		for (String string : result) {
			System.out.println(string);
		}
	}

	public static String[] solution(String[] record) {
		HashMap<String, String> hs = new HashMap<>();
		int len = record.length;
		int cnt = len;
		String[] answer = {};
		String tmp[] = new String[len];
		int[] innout = new int[len];
		for (int i = 0; i < len; i++) {
			String[] strtmp = record[i].split(" ");
			tmp[i] = strtmp[1];
			if (strtmp.length > 2) {
				hs.put(strtmp[1], strtmp[2]);
			}
			if (strtmp[0].equals("Change")) {
				cnt--;
				innout[i] = 3;
			} else if (strtmp[0].equals("Enter")) {
				innout[i] = 1;
			} else {
				innout[i] = 2;
			}
		}
		answer = new String[cnt];
		for (int i = 0, j = 0; i < len; i++) {
			if (innout[i] > 2) {
				continue;
			} else if (innout[i] == 2) {
				answer[j] = hs.get(tmp[i]);
				answer[j] = answer[j] + "님이 나갔습니다.";
				j++;
			} else {
				answer[j] = hs.get(tmp[i]);
				answer[j] = answer[j] + "님이 들어왔습니다.";
				j++;
			}
		}
		return answer;
	}
}
