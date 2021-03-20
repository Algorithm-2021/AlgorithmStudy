/*
 * 테스트 1 〉		통과 (0.80ms, 52.5MB)
 * 테스트 2 〉		통과 (0.79ms, 53.9MB)
 * 테스트 3 〉		통과 (0.62ms, 52.6MB)
 * 테스트 4 〉		통과 (1.11ms, 54.1MB)
 * 테스트 5 〉		통과 (1.64ms, 52.9MB)
 * 테스트 6 〉		통과 (4.59ms, 53.4MB)
 * 테스트 7 〉		통과 (19.38ms, 57.9MB)
 * 테스트 8 〉		통과 (35.22ms, 59.5MB)
 * 테스트 9 〉		통과 (21.67ms, 55.6MB)
 * 테스트 10 〉	통과 (44.40ms, 59.6MB)
 * 테스트 11 〉	통과 (51.23ms, 63.7MB)
 * 테스트 12 〉	통과 (60.14ms, 68MB)
 * 테스트 13 〉	통과 (64.39ms, 68.4MB)
 * 테스트 14 〉	통과 (67.15ms, 68.3MB)
 * 테스트 15 〉	통과 (0.60ms, 51.8MB)
 * 
 * time : 0 Hour 40 Minute
 * 
 * 풀이
 * 처음 배열에서 양끝의 괄호를 잘라서 }, 로 split을 하면 각각의 배열은 {n1,n2  {n1   {n1,n2,n3 형식으로 잘린다
 * 이것을 길이에 따라 정렬한후 다시 앞의 {를 제거한후 ,로 split한후 hashset에 추가하며 없던 숫자면 배열에 추가한다.
 *
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/64065
package algo_3월3주;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class Solution_프로그래머스_튜플 {
	static ArrayList<String> arr = new ArrayList<>();
	static HashSet<String> hs = new HashSet<>();

	static public int[] solution(String s) {
		split(s);
		Collections.sort(arr, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				if (o1.length() > o2.length()) {
					return 1;
				}
				return -1;
			}
		});
		int[] answer = new int[arr.size()];
		int cnt = 0;
		for (String string : arr) {
			String tmp[] = string.substring(1).split(",");
			for (int i = 0; i < tmp.length; i++) {
				if (hs.add(tmp[i])) {
					answer[cnt] = Integer.parseInt(tmp[i]);
					cnt++;
				}
			}
		}
		return answer;
	}

	public static void main(String[] args) {
		String str = "{{1,2,3},{2,1},{1,2,4,3},{2}}";
		int a[] = solution(str);
		System.out.println(Arrays.toString(a));
	}

	private static void split(String s) {
		String sub = s.substring(1, s.length() - 2);
		String str[] = sub.split("},");
		for (int i = 0; i < str.length; i++) {
			arr.add(str[i]);
		}
	}
}
