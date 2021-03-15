import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * -튜플-
 * 
 * 테스트 1 〉	통과 (0.77ms, 52.1MB)
 * 테스트 2 〉	통과 (0.78ms, 54.4MB)
 * 테스트 3 〉	통과 (0.69ms, 52.7MB)
 * 테스트 4 〉	통과 (3.04ms, 51.9MB)
 * 테스트 5 〉	통과 (2.69ms, 53.2MB)
 * 테스트 6 〉	통과 (4.89ms, 51.8MB)
 * 테스트 7 〉	통과 (25.32ms, 56MB)
 * 테스트 8 〉	통과 (61.59ms, 61.9MB)
 * 테스트 9 〉	통과 (33.78ms, 56MB)
 * 테스트 10 〉	통과 (48.00ms, 60.1MB)
 * 테스트 11 〉	통과 (69.88ms, 64.4MB)
 * 테스트 12 〉	통과 (100.50ms, 69.6MB)
 * 테스트 13 〉	통과 (101.56ms, 69.3MB)
 * 테스트 14 〉	통과 (102.40ms, 69.6MB)
 * 테스트 15 〉	통과 (0.61ms, 53.2MB)
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/64065
public class Solution_P_L2_64065_튜플 {

	public int[] solution(String s) {
		int[] answer;
		String[] strArr = s.substring(2, s.length()-2).split("\\},\\{");

		List<String[]> list = new ArrayList<>();
		for(int i=0; i<strArr.length; ++i) {
			list.add(strArr[i].split(","));
		}
		Collections.sort(list, new Comparator<String[]>() {
			@Override
			public int compare(String[] o1, String[] o2) {
				return o1.length-o2.length;
			}
		});
		
		Set<Integer> hs = new HashSet<>();
		int strLength;
		int ti;
		List<Integer> answerList = new ArrayList<>();
		for(String[] strings : list) {
			strLength = strings.length;
			for(int i=0; i<strLength; ++i) {
				ti = Integer.parseInt(strings[i]);
				if(!hs.contains(ti)) {
					answerList.add(ti);
					hs.add(ti);
				}
			}
		}
		
		int answerListSize = answerList.size();
		answer = new int[answerListSize];
		for(int i=0; i<answerListSize; ++i) {
			answer[i] = answerList.get(i);
		}
		return answer;
	}


	public static void main(String[] args) {
		Solution_P_L2_64065_튜플 sol = new Solution_P_L2_64065_튜플();
		int[] answer = sol.solution("{{2},{2,1},{2,1,3},{2,1,3,4}}");
		for(int i=0; i<answer.length; ++i)
			System.out.print(answer[i]+" ");
	}
}