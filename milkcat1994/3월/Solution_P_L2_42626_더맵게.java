import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * -더 맵게-
 * 
 * 테스트 1 〉	통과 (0.74ms, 52.9MB)
 * 테스트 2 〉	통과 (0.64ms, 52.4MB)
 * 테스트 3 〉	통과 (0.66ms, 52.9MB)
 * 테스트 4 〉	통과 (0.66ms, 52.8MB)
 * 테스트 5 〉	통과 (0.69ms, 52.9MB)
 * 테스트 6 〉	통과 (3.70ms, 53.3MB)
 * 테스트 7 〉	통과 (3.76ms, 52.2MB)
 * 테스트 8 〉	통과 (1.57ms, 52.9MB)
 * 테스트 9 〉	통과 (1.18ms, 53.1MB)
 * 테스트 10 〉	통과 (2.20ms, 52.9MB)
 * 테스트 11 〉	통과 (3.27ms, 53.9MB)
 * 테스트 12 〉	통과 (5.02ms, 54MB)
 * 테스트 13 〉	통과 (4.22ms, 52.5MB)
 * 테스트 14 〉	통과 (0.75ms, 52.5MB)
 * 테스트 15 〉	통과 (6.48ms, 52.8MB)
 * 테스트 16 〉	통과 (0.73ms, 52.7MB)
 * 
 * 효율성  테스트
 * 테스트 1 〉	통과 (135.21ms, 65.6MB)
 * 테스트 2 〉	통과 (273.06ms, 87.2MB)
 * 테스트 3 〉	통과 (1280.66ms, 123MB)
 * 테스트 4 〉	통과 (115.24ms, 63.6MB)
 * 테스트 5 〉	통과 (1617.80ms, 123MB)
 * 
 * 풀이 시간 : 5M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/42626
public class Solution_P_L2_42626_더맵게 {
	public int solution(int[] scoville, int K) {
		int answer = 0;
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o1, o2);
			}
		});
		
		for(Integer ti : scoville)
			pq.offer(ti);
		
		while(pq.size() > 1 && pq.peek() < K) {
			pq.offer(pq.poll() + pq.poll()*2);
			answer++;
		}
		
		return pq.peek() < K ? -1 : answer;
	}


	public static void main(String[] args) {
		Solution_P_L2_42626_더맵게 sol = new Solution_P_L2_42626_더맵게();
		int[] scoville = {1,2,3,9,10,12};
		int K = 7;
		int answer = sol.solution(scoville, K);
		System.out.println(answer);
	}
}