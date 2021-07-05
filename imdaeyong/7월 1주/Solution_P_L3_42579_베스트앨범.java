import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/*
 * 정렬기준 
 * 1. 속한노래가 많이 재생된 장르 먼저.
 * 2. 장르내에서 많이 재생된 노래 먼저.
 * 3. 고유번호가 낮은 노래 먼저.
 * 
 * 테스트 1 〉	통과 (18.44ms, 53.6MB)
테스트 2 〉	통과 (17.13ms, 53.7MB)
테스트 3 〉	통과 (14.54ms, 54.2MB)
테스트 4 〉	통과 (14.48ms, 53.1MB)
테스트 5 〉	통과 (19.10ms, 53.2MB)
테스트 6 〉	통과 (18.41ms, 53.5MB)
테스트 7 〉	통과 (16.49ms, 54MB)
테스트 8 〉	통과 (13.54ms, 53MB)
테스트 9 〉	통과 (15.81ms, 53.5MB)
테스트 10 〉	통과 (20.82ms, 54.4MB)
테스트 11 〉	통과 (13.52ms, 52.5MB)
테스트 12 〉	통과 (16.39ms, 52.8MB)
테스트 13 〉	통과 (21.67ms, 53.4MB)
테스트 14 〉	통과 (18.18ms, 52.8MB)
테스트 15 〉	통과 (14.87ms, 52.5MB)
 * 2.5H
 */


public class Solution_P_L3_42579_베스트앨범 {
	static class Solution {
		public int[] solution(String[] genres, int[] plays) {
			Map<String, Integer> songs = new HashMap<>();
			Map<String, Integer> genreSum = new HashMap<>();
			
			int[] answer = new int[200];

			for (int i = 0; i < genres.length; i++) {
				// 3. 교유번호가 낮은노래 우선순위 높이기 = code
				int code = plays[i] * 10000 + genres.length - i;
				songs.put(genres[i]+i, code); //노래뒤에 고유번호 부여(같은장르 맵에 넣게)
				int sum = genreSum.getOrDefault(genres[i], 0) + plays[i];
				genreSum.put(genres[i], sum); //재생횟수 높고 + 고유번호 낮을수록 우선순위 높게 합산부여
			}
			//genreSum value기준 내림차순정렬
			List<String> sortGenreSum = new ArrayList<>(genreSum.keySet());
			Collections.sort(sortGenreSum,(v1,v2)->genreSum.get(v2).compareTo(genreSum.get(v1)));
			
			//songs value기준 내림차순정렬
			List<String> sortSongs = new ArrayList<>(songs.keySet());
			Collections.sort(sortSongs,(v1,v2)->songs.get(v2).compareTo(songs.get(v1)));
			
			int totalcnt=0;
			for(String key : sortGenreSum) {
//				System.out.println("이번 장르는 " + key+"이고 총 재생횟수는"+ genreSum.get(key));
				
				int limit=0; //장르별로 2개이상시 더이상 넣지않음
				for(String key2 : sortSongs) {
					if(key2.contains(key)&&limit<2) {
//						System.out.println(key2+" " + songs.get(key2));
						
						// 고유번호 다시 원상복귀!
						int code = songs.get(key2)%10000; 
						code = genres.length-code;
						answer[totalcnt++]=code;
						limit++;
					}
				}
			}
			//빈 배열 잘라내기
			int[] realAnswer = Arrays.copyOfRange(answer, 0, totalcnt);
			return realAnswer;

		}

	}

	public static void main(String[] args) {
		String[] genres = { "classic", "pop", "classic", "classic", "pop" };
		int[] plays = { 500, 600, 150, 800, 2500 };
		Solution solution = new Solution();
		int[] answer = solution.solution(genres, plays);
		System.out.println(Arrays.toString(answer));
	}
}
