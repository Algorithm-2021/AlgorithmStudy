import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * 1. 인쇄 대기목록의 가장 앞에 있는 문서(J)를 대기목록에서 꺼냅니다.	
 * 2. 나머지 인쇄 대기목록에서 J보다 중요도가 높은 문서가 한 개라도 존재하면 J를 대기목록의 가장 마지막에 넣습니다.
 * 3. 그렇지 않으면 J를 인쇄합니다.
 테스트 1 〉	통과 (2.12ms, 52.2MB)
테스트 2 〉	실패 (1.97ms, 52.2MB)
테스트 3 〉	통과 (1.53ms, 52MB)
테스트 4 〉	통과 (1.40ms, 53.4MB)
테스트 5 〉	통과 (0.78ms, 52.8MB)
테스트 6 〉	통과 (1.10ms, 52MB)
테스트 7 〉	통과 (0.83ms, 52MB)
테스트 8 〉	통과 (3.13ms, 52.5MB)
테스트 9 〉	통과 (0.81ms, 54.3MB)
테스트 10 〉	통과 (0.80ms, 52.7MB)
테스트 11 〉	실패 (2.07ms, 52.8MB)
테스트 12 〉	통과 (0.72ms, 52.5MB)
테스트 13 〉	통과 (2.62ms, 51.6MB)
테스트 14 〉	통과 (0.80ms, 53.6MB)
테스트 15 〉	통과 (1.03ms, 53MB)
테스트 16 〉	통과 (1.02ms, 52.8MB)
테스트 17 〉	실패 (1.72ms, 52.8MB)
테스트 18 〉	실패 (3.57ms, 52.2MB)
테스트 19 〉	실패 (1.67ms, 52.7MB)
테스트 20 〉	통과 (2.34ms, 52.6MB)
 */
public class Solution_P_L2_42587_프린터 {			
	static List<node> listAfter = new ArrayList<>(); // 입력받은후 정렬하는 리스트(큰수부터 작은수까지)
	static class Solution {
		public int solution(int[] priorities, int location) {
			List<node> listBefore = new ArrayList<>(); // 처음에 입력받는 리스트
			Queue<node> pq = new PriorityQueue<>(); // 큰수부터 작은수 정렬 + 이전 큰값과의 거리순 정렬

			for (int i = 0; i < priorities.length; i++) {
				node now = new node(i, priorities[i], 0);
				listBefore.add(now);
			}

			// 최대값부터 맨앞에 삽입정렬
			while (!listBefore.isEmpty()) {
				int now = 0;
				node max = listBefore.get(now);
				int maxPoint = now;
				for (int j = now; j < listBefore.size(); j++) {
					if (max.value < listBefore.get(j).value) {
						max = listBefore.get(j);
						maxPoint = j;
					}
				}
				listAfter.add(max);
				listBefore.remove(maxPoint);
			}
			
			// 각 노드마다 거리 부여
			for (int i = 1; i < priorities.length; i++) {
				node before = listAfter.get(i-1);
				node now = listAfter.get(i);
			
				listAfter.get(i).range = compareNum(before,now,i);
				
			}
			
			// pq에 넣어서 큰수 -> 거리순 우선순위정렬
			for(int i=0; i<priorities.length;i++) {
				pq.offer(listAfter.get(i));
			}
			
			int answer = 0;
			for(int i=0; i<priorities.length;i++) {
				if(location== pq.poll().index) {
					answer=i+1;
				}
			}

			return answer;
		}

		private int compareNum(node before, node now,int i) {
			
			// 이전 노드의 값이 더 클경우
			if(now.value<before.value) {
				
				// 이전 노드보다 앞에 있는 노드일경우, 맨 뒤로 보냄
				if(now.index<before.index) {
					return now.index+101;
				
				// 이전 노드보다 뒤에 있는 노드일경우, 현재 노드와 이전 노드의 거리 계산
				}else {
					return now.index-before.index;
				}
			}
				
			// 이전 노드의 값과 같을 경우
			int range=0;
			if(now.value==before.value) {
				// 현재 자신의 값보다 큰값을 찾아서 거슬러올라감
				while(now.value==listAfter.get(i).value) {
					range++;
					i--;
          if(i==0) {
					  range=now.index;
					  break;
				  }
				}
				// 이전 노드보다 앞일경우, 맨 뒤로 보냄
				if(listAfter.get(i).index>now.index) {
					range = now.index+101;
				}
			}
			return range;
		}
	}


	public static class node implements Comparable<node> {
		int index, value, range;

		public node(int index, int value, int range) {
			this.index = index;
			this.value = value;
			this.range = range;
		}

		@Override
		public int compareTo(node n) {
			int win = n.value - this.value;
			if (win == 0)
				win = this.range - n.range;
			return win;
		}
	}
	public static void main(String[] args) {
		int[] priorities = { 1, 1, 9, 1, 1, 1 };
		int location = 0;
		Solution s = new Solution();
		System.out.println(s.solution(priorities, location));
	}
}
