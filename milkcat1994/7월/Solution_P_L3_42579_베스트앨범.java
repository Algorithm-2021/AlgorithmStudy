import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
 * -베스트 앨범-
 * 1. 장르별, 플레이목록의 HashMap을 생성, 초기화한다.
 * 2. 장르를 기준으로 내림차순으로 먼저 정렬한다.
 * 3. 내림차순 정렬된 장르를 통해 플레이목록의 List를 가져온다.
 * 4. 장르별 플레이목록은 2개까지 가능하다.
 * 
 * 테스트 7 〉	통과 (1.47ms, 51.7MB)
 * 테스트 8 〉	통과 (1.51ms, 52.3MB)
 * 테스트 9 〉	통과 (1.42ms, 52.3MB)
 * 테스트 10 〉	통과 (1.55ms, 52.2MB)
 * 테스트 11 〉	통과 (1.35ms, 52.8MB)
 * 테스트 12 〉	통과 (1.43ms, 52.5MB)
 * 테스트 13 〉	통과 (1.55ms, 52.3MB)
 * 테스트 14 〉	통과 (1.56ms, 52.2MB)
 * 테스트 15 〉	통과 (1.48ms, 52.1MB)
 * 
 * 풀이시간 : 20M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/42579
public class Solution_P_L3_42579_베스트앨범 {
	public int[] solution(String[] genres, int[] plays) {
		List<Integer> answerList = new ArrayList<>();
		int[] answer = {};
		
		HashMap<String, Genre> genreMap = new HashMap<>();
		HashMap<String, List<Play>> playMap = new HashMap<>();
		String tempStr;
		for(int i=0; i<genres.length; ++i) {
			tempStr = genres[i];
			if(genreMap.containsKey(tempStr)) {
				genreMap.get(tempStr).addPlay(plays[i]);
			}
			else {
				genreMap.put(tempStr, new Genre(genres[i], plays[i]));
			}
			
			if(!playMap.containsKey(tempStr)) {
				playMap.put(tempStr, new ArrayList<Play>());
			}
			playMap.get(tempStr).add(new Play(tempStr, plays[i], i));
		}
		
		// 장르 HashMap 내림차순 정렬
		Genre[] genArr = new Genre[genreMap.size()];
		genArr = genreMap.values().toArray(new Genre[genreMap.size()]);
		Arrays.sort(genArr, (o1, o2) -> o2.totalPlay - o1.totalPlay);
		
		// 장르별 플레이 목록 정렬
		List<Play> playList;
		int cnt = 0;
		for(Genre gen : genArr) {
			cnt = 0;
			playList = playMap.get(gen.name);
			playList.sort((o1, o2) -> {
				if(o1.play == o2.play) {
					return o1.idx - o2.idx;
				}
				else {
					return o2.play - o1.play;
				}
			});
			
			// 장르별 2개까지만 등록
			for(Play play : playList) {
				if(cnt == 2) break;
				answerList.add(play.idx);
				cnt++;
			}
		}
		
		// 정답 배열 생성
		answer = new int[answerList.size()];
		for(int i=0; i<answerList.size(); ++i) {
			answer[i] = answerList.get(i);
		}
		return answer;
	}
	
	public class Genre {
		String name;
		int totalPlay;
		
		Genre(String name, int totalPlay){
			this.name = name;
			this.totalPlay = totalPlay;
		}
		
		void addPlay(int play){
			this.totalPlay += play;
		}
	}

	public class Play {
		String genre;
		int play;
		int idx;
		
		public Play(String genre, int play, int idx) {
			this.genre = genre;
			this.play = play;
			this.idx = idx;
		}
	}
	

	public static void main(String[] args) {
		Solution_P_L3_42579_베스트앨범 sol = new Solution_P_L3_42579_베스트앨범();
		String[] genres = {"classic", "pop", "classic", "classic", "pop"};
		int[] plays = {500, 600, 150, 800, 2500};
		int[] answer = sol.solution(genres, plays);
		System.out.println(Arrays.toString(answer));
	}
}