import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * -기능개발-
 * Math.ceil() 내부 계산에서 분모인 speeds[idx]로 나누어 줄 때 (double)로 캐스팅 안 하면
 * Math.ceil() 이전에 이미 자동 int로 계산되니 주의!
 * 
 * 테스트 7 〉	통과 (3.54ms, 53.4MB)
 * 테스트 8 〉	통과 (8.91ms, 52.4MB)
 * 테스트 9 〉	통과 (7.05ms, 53.8MB)
 * 테스트 10 〉	통과 (2.74ms, 53.4MB)
 * 테스트 11 〉	통과 (2.91ms, 53.2MB)
 * 
 * 풀이시간 : 30M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/42586
public class Solution_P_L2_42586_기능개발 {
	
	public int[] solution(int[] progresses, int[] speeds) {
		Queue<Integer> que = new LinkedList<>();
		List<Integer> list = new ArrayList<>();
		
		int pLength = progresses.length;
		for(int idx=0; idx<pLength; ++idx) {
			que.offer((int) Math.ceil((100 - progresses[idx])/(double)speeds[idx]));
		}
		
		int max = que.poll(), cnt=1;
		while(!que.isEmpty()) {
			if(que.peek() <= max) {
				que.poll();
				cnt++;
			}
			else {
				max = que.poll();
				list.add(cnt);
				cnt=1;
			}
		}
		list.add(cnt);
		
		return list.stream().mapToInt(i->i).toArray();
	}

	public static void main(String[] args) {
		Solution_P_L2_42586_기능개발 sol = new Solution_P_L2_42586_기능개발();
		int[] progresses = {93,30,55};
		int[] speeds = {1,30,5};
		int[] answer = sol.solution(progresses, speeds);
		System.out.println(Arrays.toString(answer));
	}
}