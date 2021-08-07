import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * -오픈채팅방-
 * 테스트 1 〉	통과 (2.05ms, 53.2MB)
 * 테스트 2 〉	통과 (1.89ms, 52.6MB)
 * 테스트 3 〉	통과 (2.11ms, 52.8MB)
 * 테스트 4 〉	통과 (2.04ms, 52.5MB)
 * 테스트 5 〉	통과 (7.77ms, 53.7MB)
 * 테스트 6 〉	통과 (7.91ms, 53.4MB)
 * 테스트 7 〉	통과 (7.32ms, 53.4MB)
 * 테스트 8 〉	통과 (12.32ms, 53.8MB)
 * 테스트 9 〉	통과 (8.72ms, 55.6MB)
 * 테스트 10 〉	통과 (11.01ms, 55.4MB)
 * 테스트 11 〉	통과 (5.63ms, 53MB)
 * 테스트 12 〉	통과 (5.36ms, 53.4MB)
 * 테스트 13 〉	통과 (8.78ms, 53.9MB)
 * 테스트 14 〉	통과 (9.55ms, 54.7MB)
 * 테스트 15 〉	통과 (2.02ms, 53.4MB)
 * 테스트 16 〉	통과 (1.84ms, 53.6MB)
 * 테스트 17 〉	통과 (4.62ms, 55.5MB)
 * 테스트 18 〉	통과 (2.53ms, 54.3MB)
 * 테스트 19 〉	통과 (9.02ms, 53.7MB)
 * 테스트 20 〉	통과 (8.90ms, 53.3MB)
 * 테스트 21 〉	통과 (7.72ms, 53.3MB)
 * 테스트 22 〉	통과 (8.16ms, 53.2MB)
 * 테스트 23 〉	통과 (7.94ms, 54.7MB)
 * 테스트 24 〉	통과 (8.03ms, 53.8MB)
 * 테스트 25 〉	통과 (229.26ms, 142MB)
 * 테스트 26 〉	통과 (226.99ms, 145MB)
 * 테스트 27 〉	통과 (250.61ms, 148MB)
 * 테스트 28 〉	통과 (218.05ms, 150MB)
 * 테스트 29 〉	통과 (230.97ms, 150MB)
 * 테스트 30 〉	통과 (205.23ms, 142MB)
 * 테스트 31 〉	통과 (199.77ms, 145MB)
 * 테스트 32 〉	통과 (132.71ms, 123MB)
 * 풀이 시간 : 10M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/42888
public class Solution_P_L2_42888_오픈채팅방 {
	static final int ENTER = 0;
	static final int EXIT = 1;
	
	public String[] solution(String[] record) {
		String[] answer;
		List<String[]> stringArrList = new ArrayList<>();
		for(String str : record)
			stringArrList.add(str.split(" "));
		
		Map<String, String> hm = new HashMap<String, String>();
		int answerLength=0;
		for(String[] strArr : stringArrList) {
			switch (strArr[0]) {
			case "Enter":
				answerLength++;
			case "Change":
				hm.put(strArr[1], strArr[2]);
				break;
			case "Leave":
				answerLength++;
			default:
				// do Nothing
				break;
			}
		}
		
		String[] message = {"님이 들어왔습니다.", "님이 나갔습니다."};
		answer = new String[answerLength];
		int idx=0;
		for(String[] strArr : stringArrList) {
			switch (strArr[0]) {
			case "Enter":
				answer[idx++]= hm.get(strArr[1])+message[ENTER];
				break;
			case "Leave":
				answer[idx++]= hm.get(strArr[1])+message[EXIT];
				break;
			case "Change":
			default:
				// do Nothing
				break;
			}
		}
		return answer;
	}


	public static void main(String[] args) {
		Solution_P_L2_42888_오픈채팅방 sol = new Solution_P_L2_42888_오픈채팅방();
		String[] strArr = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
		String[] answer = sol.solution(strArr);
		for(String str : answer)
			System.out.println(str);
	}
}