import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * -이중 우선순위 큐-
 * 
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/42628
public class Solution_P_L3_42628_이중우선순위큐 {
	public int[] solution(String[] operations) {
		StringTokenizer st;
		
		PriorityQueue<Integer> minPQ = new PriorityQueue<>();
		PriorityQueue<Integer> maxPQ = new PriorityQueue<>((o1, o2) -> o2.compareTo(o1));
		
		HashMap<Integer,Integer> hm = new HashMap<>();
		
		
		String prefix;
		int postfix;
		for(String operation : operations) {
			st = new StringTokenizer(operation, " ");
			prefix = st.nextToken();
			postfix = Integer.parseInt(st.nextToken());
			
			switch (prefix) {
			case "I":
				minPQ.offer(postfix);
				maxPQ.offer(postfix);
				
				hm.put(postfix, hm.getOrDefault(postfix, 0)+1);
				break;
				
			case "D":
				// 최댓값 삭제
				if(postfix == 1) {
					deleteNum(maxPQ, hm);
				}
				// 최솟값 삭제
				else {
					deleteNum(minPQ, hm);
				}
				break;
			default:
				// Do Nothing
				break;
			}
		}
		
		cleanNum(maxPQ, hm);
		cleanNum(minPQ, hm);
		
		int[] answer = {
				maxPQ.isEmpty() ? 0 : maxPQ.peek(),
				minPQ.isEmpty() ? 0 : minPQ.peek()
		};
		return answer;
	}
	
	public void deleteNum(PriorityQueue<Integer> pq, HashMap<Integer,Integer> hm) {
		// pq가 비어있지 않고 hm에 해당 숫자가 존재 하지 않는다면 필요없는 값을 pq에서 삭제한다.
		while(!pq.isEmpty() && !hm.containsKey(pq.peek())) {
			pq.poll();
		}
		
		// pq가 비어있지 않다면 해당 값 제외 하면 됨
		if(!pq.isEmpty()) {
			int max = pq.peek();
			if(hm.get(max) == 1) {
				hm.remove(max);
			}
			else {
				hm.put(max, hm.get(max)-1);
			}
			pq.poll();
		}
	}
	
	public void cleanNum(PriorityQueue<Integer> pq, HashMap<Integer,Integer> hm) {
		while(!pq.isEmpty()) {
			int num = pq.peek();
			if(hm.containsKey(num))
				return;
			pq.poll();
		}
	}

	public static void main(String[] args) {
		Solution_P_L3_42628_이중우선순위큐 sol = new Solution_P_L3_42628_이중우선순위큐();
//		String[] operations = {"I 7","I 5","I -5","D -1"};
		String[] operations = {"I 16","D 1"};
		int[] answer = sol.solution(operations);
		System.out.println(Arrays.toString(answer));
	}
}