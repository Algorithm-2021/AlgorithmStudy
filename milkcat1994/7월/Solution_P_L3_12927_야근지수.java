import java.util.Iterator;
import java.util.PriorityQueue;

/*
 * -야근 지수-
 * 1. works를 PQ에 내림차순으로 삽입
 * 2. 가장 많은 야근 지수를 빼내며 시간만큼 진행
 * 
 * 풀이 시간 : 10m
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/12927
public class Solution_P_L3_12927_야근지수 {
	public long solution(int n, int[] works) {
		long answer = 0;
		
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2-o1);
		
		for(int i=0; i<works.length; ++i) {
			pq.offer(works[i]);
		}
		
		int ti;
		while(--n >= 0) {
			ti = pq.poll();
			// 최대 많이 남은 일의 시간이 0이라면 모든 값이 0이므로 야근지수가 없다.
			if(ti == 0) {
				return 0;
			}
			
			pq.offer(ti-1);
		}
		
		Iterator<Integer> iter = pq.iterator();
		while(iter.hasNext()) {
			answer += pow(iter.next());
		}
		
		return answer;
	}
	
	public int pow(int x) {
		return x*x;
	}

	public static void main(String[] args) {
		Solution_P_L3_12927_야근지수 sol = new Solution_P_L3_12927_야근지수();
		int n = 4;
		int[] works = {4,3,3};
		long answer = sol.solution(n, works);
		System.out.println(answer);
	}
}