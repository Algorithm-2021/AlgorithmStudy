/*
테스트 1 〉	통과 (0.92ms, 52MB)
테스트 2 〉	통과 (0.99ms, 52.9MB)
테스트 3 〉	통과 (0.92ms, 54.4MB)
테스트 4 〉	통과 (1.08ms, 53.4MB)
테스트 5 〉	통과 (1.48ms, 52.8MB)
테스트 6 〉	통과 (1.52ms, 52.5MB)
테스트 7 〉	통과 (1.28ms, 52.1MB)
테스트 8 〉	통과 (1.05ms, 52.7MB)
테스트 9 〉	통과 (0.95ms, 52.8MB)
테스트 10 〉	통과 (1.66ms, 53MB)
테스트 11 〉	통과 (1.05ms, 53MB)
테스트 12 〉	통과 (1.16ms, 52MB)
테스트 13 〉	통과 (1.10ms, 52.7MB)
테스트 14 〉	통과 (1.58ms, 52.7MB)
테스트 15 〉	통과 (0.98ms, 53.1MB)
 * 1H
 * 해시맵, pq
 * https://programmers.co.kr/learn/courses/30/lessons/42579
 */
package PROGRAMMERS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Solution_P_L3_42579_베스트앨범_정세린 {
	static class Solution {
		class GenreInfo {
			int idx;
			String name;
			int play;

			public GenreInfo() { };
			public GenreInfo(int idx, String name, int play) {
				this.idx = idx;
				this.name = name;
				this.play = play;
			}
		}

		public int[] solution(String[] genres, int[] plays) {
			HashMap<String, Integer> map = new HashMap<>(); // name, play
			int len = genres.length;

			for (int i = 0; i < len; i++) {
				if (map.get(genres[i]) == null) map.put(genres[i], plays[i]);
				else {
					int p = map.get(genres[i]) + plays[i];
					map.put(genres[i], p);
				}
			}

			PriorityQueue<GenreInfo> pq = new PriorityQueue<>(new Comparator<GenreInfo>() {
				@Override
				public int compare(GenreInfo o1, GenreInfo o2) {
					int genrePlaySub = map.get(o2.name) - map.get(o1.name);
					int playSub = o2.play - o1.play;
					int idxSub = o1.idx - o2.idx;
					if (genrePlaySub == 0 && playSub == 0) return idxSub; // 장르 재생횟수 같으면 고유번호순
					else if (genrePlaySub == 0) return playSub; // 장르가 같으면 재생횟수
					return genrePlaySub; // 장르먼저 수록
				}
			});

			for (int i = 0; i < len; i++) pq.offer(new GenreInfo(i, genres[i], plays[i]));

			HashMap<String, Integer> numMap = new HashMap<>(); // 장르, 수록횟수
			ArrayList<Integer> arr = new ArrayList<>();

			while (!pq.isEmpty()) {
				GenreInfo g = pq.poll();
				if (numMap.get(g.name) == null) {
					numMap.put(g.name, 1);
					arr.add(g.idx);
				} else if (numMap.get(g.name) < 2) {
					numMap.put(g.name, numMap.get(g.name) + 1);
					arr.add(g.idx);
				}
			}

			int[] answer = new int[arr.size()];
			for (int i = 0; i < answer.length; i++) answer[i] = arr.get(i);

			return answer;
		}
	}
	
	public static void main(String[] args) {
		Solution solution = new Solution();
		String[] genres = {"classic", "pop", "classic", "classic", "pop"};
		int[] plays = {500, 600, 150, 800, 2500};
		int[] answer = solution.solution(genres, plays);
		System.out.println(Arrays.toString(answer));
	}
	
}
