import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
 * -프린터 -
 * 1. Deque이용한 대기목록 관리
 * 2. 작업 우선순위는 List로 받아 역정렬
 * 3. 찾고자 하는 문서 위치 idx 변수 이용한 조절
 * 
 * 테스트 16 〉	통과 (0.97ms, 52.1MB)
 * 테스트 17 〉	통과 (1.49ms, 52.5MB)
 * 테스트 18 〉	통과 (0.80ms, 52.3MB)
 * 테스트 19 〉	통과 (1.39ms, 52.4MB)
 * 테스트 20 〉	통과 (1.12ms, 52.4MB)
 * 
 * 풀이 시간 : 20M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/42587
public class Solution_P_L2_42587_프린터 {
	public int solution(int[] priorities, int location) {
		int idx=location;
		int res = 1;

		Deque<Integer> deque = new LinkedList<>();
		List<Integer> priorityList = new ArrayList<>();
		for(int i=0; i<priorities.length; ++i) {
			deque.offerLast(priorities[i]);
			priorityList.add(priorities[i]);
		}
		
		// 우선순위 역정렬
		Collections.sort(priorityList, (o1, o2) -> o2 - o1);
		
		for(Integer priority : priorityList) {
			// deque의 앞자리가 priority와 같을 때 까지 반복
			while(deque.peekFirst() != priority) {
				deque.offerLast(deque.pollFirst());
				idx--;
				if(idx== -1) idx = deque.size()-1;
			}
			if(idx == 0)
				return res;
			deque.pollFirst();
			idx--;
			res++;
		}
		
		return res;
	}


	public static void main(String[] args) {
		Solution_P_L2_42587_프린터 sol = new Solution_P_L2_42587_프린터();
		int[] priorities = {2, 1, 3, 2};
		int location = 2;
		int answer = sol.solution(priorities, location);
		System.out.println(answer);
	}
}