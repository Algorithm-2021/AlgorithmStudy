/*
정확성 테스트

테스트 1 〉	통과 (0.30ms, 53.1MB)
테스트 2 〉	통과 (0.32ms, 52.3MB)
테스트 3 〉	통과 (0.25ms, 52.5MB)
테스트 4 〉	통과 (0.28ms, 52.4MB)
테스트 5 〉	통과 (0.37ms, 52.5MB)
테스트 6 〉	통과 (0.37ms, 52.5MB)
테스트 7 〉	통과 (0.36ms, 52.9MB)
테스트 8 〉	통과 (0.36ms, 52.1MB)
테스트 9 〉	통과 (0.32ms, 52.1MB)
테스트 10 〉	통과 (0.42ms, 52.6MB)
테스트 11 〉	통과 (0.29ms, 51.6MB)
테스트 12 〉	통과 (0.34ms, 52.5MB)
테스트 13 〉	통과 (0.40ms, 52.9MB)
테스트 14 〉	통과 (0.37ms, 52.6MB)
테스트 15 〉	통과 (0.30ms, 52.3MB)

time : 1 Hour 0 Minute

풀이
해시맵으로 장르별 총 재생횟수를 합하고 해시맵의 키와 값을 각각 배열로 바꾸어 값의 내림차순으로 키와 같이 정렬
키의 순서에 따라 반복문을 돌며 가장 높은 재생수의 노래 두개의 고유번호를 list에 저장
list를 배열로 바꾸어 값 출력

*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/42579
package algo_7월1주;

import java.util.*;

public class Solution_P_L3_42579_베스트앨범 {
	public static void main(String[] args) {
		int inf = Integer.MAX_VALUE;
		System.out.println(inf);
	}

	public static int[] solution(String[] genres, int[] plays) {
		HashMap<String, Integer> totPlay = new HashMap<>();
		for (int i = 0; i < plays.length; i++) {
			if (totPlay.containsKey(genres[i])) {
				totPlay.put(genres[i], totPlay.get(genres[i]) + plays[i]);
			} else {
				totPlay.put(genres[i], plays[i]);
			}
		}
		int size = totPlay.size();
		String g[] = new String[size];
		int p[] = new int[size];
		int i = 0;
		for (String str : totPlay.keySet()) {
			g[i] = str;
			i++;
		}
		i = 0;
		for (int a : totPlay.values()) {
			p[i] = a;
			i++;
		}
		for (int j = 0; j < size - 1; j++) {
			for (int k = 0; k < size - j - 1; k++) {
				if (p[k] < p[k + 1]) {
					int tmp = p[k];
					p[k] = p[k + 1];
					p[k + 1] = tmp;
					String tmps = g[k];
					g[k] = g[k + 1];
					g[k + 1] = tmps;
				}
			}
		}
		List<Integer> list = new LinkedList<>();
		for (int j = 0; j < size; j++) {
			int a = -1;// 큰수
			int b = -1;// 작은수
			int posa = 0;
			int posb = 0;
			for (int k = 0; k < plays.length; k++) {
				if (g[j].equals(genres[k])) {
					if (b == -1) {
						b = plays[k];
						posb = k;
					} else if (a == -1) {
						if (b < plays[k]) {
							a = plays[k];
							posa = k;
						} else {
							a = b;
							posa = posb;
							b = plays[k];
							posb = k;
						}
					} else {
						if (b < plays[k]) {
							b = plays[k];
							posb = k;
							if (a < b) {
								int tmp = b;
								int tmppos = posb;
								b = a;
								posb = posa;
								a = tmp;
								posa = tmppos;
							}
						}
					}
				}
			}
			if (a == -1) {
				list.add(posb);
			} else {
				list.add(posa);
				list.add(posb);
			}
		}
		int[] answer = new int[list.size()];
		int c = 0;
		for (int j : list) {
			answer[c] = j;
			c++;
		}
		return answer;
	}

}
