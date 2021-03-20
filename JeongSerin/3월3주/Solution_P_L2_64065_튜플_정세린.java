/*
테스트 11 〉	통과 (86.55ms, 64MB)
테스트 12 〉	통과 (76.14ms, 68.5MB)
테스트 13 〉	통과 (82.75ms, 69.4MB)
테스트 14 〉	통과 (82.42ms, 70.2MB)
테스트 15 〉	통과 (0.62ms, 53.2MB)
 * 1H 30m
 * LinkedHashSet사용
 * -HashSet: 순서 보장 x, TreeSet: 오름차순 정렬, LinkedHashSet: 넣은 순서 유지
 */
package PROGRAMMERS;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;

public class Solution_P_L2_64065_튜플_정세린 {
	static class Solution {
		public int[] solution(String s) {
			int[] answer = {};
			
			// 1. 집합 -> 배열
			String subs = s.substring(2, s.length() - 2);
			String[] str = subs.split("\\},\\{");

			// 2. 원소개수가 작은 집합이 앞으로 오게 정렬
			Arrays.sort(str, new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return o1.length() - o2.length();
				}
			});

			// 튜플의 원소 개수
			int num = str.length; 
			answer = new int[num];

			// 3. LinkedHashSet(: 중복허용X, 넣은 순서 유지)에 원소 담기
			LinkedHashSet<Integer> hs = new LinkedHashSet<>();
			for (int i = 0; i < num; i++) {
				String[] subset = str[i].split(","); // 부분집합
				for (int j = 0; j < subset.length; j++) {
					int element = Integer.parseInt(subset[j]); // 부분집합 내의 원소
					hs.add(element);
				}
			}

			// 4. int 배열로 바꾸기
			Integer[] ans = hs.toArray(new Integer[num]);
			for (int i = 0; i < num; i++) {
				answer[i] = ans[i];
			}

			return answer;
		}
	}

	public static void main(String[] args) {
		String s = "{{1,2,3},{2,1},{1,2,4,3},{2}}";
		Solution solution = new Solution();
		int[] answer = solution.solution(s);
		System.out.println(Arrays.toString(answer));
	}

}
