/*
테스트 28 〉	통과 (329.58ms, 171MB)
테스트 29 〉	통과 (318.99ms, 168MB)
테스트 30 〉	통과 (249.44ms, 155MB)
테스트 31 〉	통과 (257.24ms, 157MB)
테스트 32 〉	통과 (219.98ms, 144MB)
 * 40m
 * HashMap으로 유저정보 업데이트
 */
package PROGRAMMERS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Solution_P_L2_42888_오픈채팅방_정세린 {
	static class Solution {
		public String[] solution(String[] record) {
			String[] answer = {};
			int len = record.length;
			HashMap<String, String> userInfo = new HashMap<String, String>(); // <uid, 이름>
			
			// 유저정보 업데이트
			for (int i = 0; i < len; i++) {
				String[] rec = record[i].split(" ");
				if (rec.length == 3) { // 유저정보 변경 필요
					if (userInfo.get(rec[1]) == null) { // 등록된 정보 없음
						userInfo.put(rec[1], rec[2]); // 유저정보 등록
					} else { // 닉네임을 변경해야하는 경우
						userInfo.replace(rec[1], rec[2]);
					}
				}
			} // end of update userInfo

			ArrayList<String> ans = new ArrayList<String>();
			for (int i = 0; i < len; i++) {
				String[] rec = record[i].split(" ");
				switch (rec[0]) {
				case "Enter": // 입장
					ans.add(userInfo.get(rec[1]) + "님이 들어왔습니다.");
					break;
				case "Leave": // 퇴장
					ans.add(userInfo.get(rec[1]) + "님이 나갔습니다.");
					break;
				}
			} // end of log
			
			answer = ans.toArray(new String[ans.size()]);
			return answer;
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
		String[] answer = solution.solution(record);
		System.out.println(Arrays.toString(answer));
	}
}
