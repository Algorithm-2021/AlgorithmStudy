/*
정확성 테스트

테스트 1 〉	통과 (0.50ms, 52.3MB)
테스트 2 〉	통과 (0.51ms, 52.3MB)
테스트 3 〉	통과 (0.50ms, 52.1MB)
테스트 4 〉	통과 (0.52ms, 52.2MB)
테스트 5 〉	통과 (0.54ms, 52.2MB)
테스트 6 〉	통과 (0.55ms, 52.2MB)
테스트 7 〉	통과 (0.49ms, 52MB)
테스트 8 〉	통과 (0.46ms, 52.7MB)
테스트 9 〉	통과 (0.47ms, 51.8MB)
테스트 10 〉	통과 (1.52ms, 51.8MB)
테스트 11 〉	통과 (0.46ms, 53.2MB)
테스트 12 〉	통과 (0.49ms, 51.6MB)
테스트 13 〉	통과 (0.46ms, 52.8MB)
테스트 14 〉	통과 (5.23ms, 54.8MB)
테스트 15 〉	통과 (7.54ms, 53.5MB)
테스트 16 〉	통과 (9.15ms, 54.8MB)
테스트 17 〉	통과 (10.97ms, 55.2MB)
테스트 18 〉	통과 (16.86ms, 55.7MB)
테스트 19 〉	통과 (12.81ms, 55.2MB)
테스트 20 〉	통과 (13.75ms, 55.4MB)

효율성 테스트

테스트 1 〉	통과 (15.37ms, 56.5MB)
테스트 2 〉	통과 (10.59ms, 59.8MB)
테스트 3 〉	통과 (336.48ms, 244MB)
테스트 4 〉	통과 (308.61ms, 134MB)

time : 0 Hour 30 Minute

풀이
문자열의 길이로 정렬하여 해쉬에 넣기전 subString이 포함되어 있는지 확인

*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/42577
package algo_6월4주;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class Solution_P_L2_42577_전화번호목록 {
	static HashSet<String> hs = new HashSet<>();

	public static void main(String[] args) {
		String phone_book[] = {"119", "97674223", "1195524421"};
		System.out.println(solution(phone_book));
	}

	public static boolean solution(String[] phone_book) {
		List<String> list = new ArrayList<>();
		Comparator<String> c = new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return Integer.compare(o1.length(), o2.length());
			}
		};
		for (String str : phone_book) {
			list.add(str);
		}
		Collections.sort(list, c);
		for (String str : list) {
			if (hs.size() == 0) {
				hs.add(str);
			} else {
				for (int i = 1; i < str.length(); i++) {
					if (hs.contains(str.substring(0, i))) {
						return false;
					}
				}
				hs.add(str);
			}
		}
		return true;
	}

}
