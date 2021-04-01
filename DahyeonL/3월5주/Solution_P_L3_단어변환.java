package algo_study_2021;

/*
 * 테스트 1 〉	통과 (0.37ms, 52.5MB)
테스트 2 〉	통과 (0.38ms, 52MB)
테스트 3 〉	통과 (0.49ms, 52.8MB)
테스트 4 〉	통과 (0.37ms, 52.3MB)
테스트 5 〉	통과 (0.25ms, 53.2MB)

*/
import java.util.LinkedList;
import java.util.Queue;

public class Solution_P_L3_단어변환 {

	public static void main(String[] args) {
		String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
		System.out.println(solution("hit", "cog", words));
	}
	
	static int solution(String begin, String target, String[] words) {
		int answer = Integer.MAX_VALUE;
		int countW = words.length;
		boolean isvisit[] = new boolean[countW]; //왔다간 노드인지 확인
		boolean isTarget=false; // 끝단어가 words에 있는지 확인
		
        node firstnode = new node(0,begin);
        
        for (int i = 0; i < countW; i++) {
			if(begin.equals(words[i])) { //단어들안에 첫 단어가 있을 때
				firstnode = new node(0,words[i]);
				isvisit[i] = true;
			}
			if(target.equals(words[i])) isTarget = true; 
        }
        //words에 찾는 단어가 없을 때 
        if(!isTarget) return 0;
        
        //bfs
		Queue<node> q = new LinkedList<node>();
		q.add(firstnode);
        while(!q.isEmpty()) {
        	node temp = q.poll();
        	for (int i = 0; i < countW; i++) {
        		if(isvisit[i]) continue;//이미 지나감
        		if(isConnected(temp.str, words[i])) {
        			//타겟에 도착했다면
        			if(target.equals(words[i])) {
        				answer = Math.min(answer, temp.count+1);
        			}else {
        				q.add(new node(temp.count+1, words[i]));
        				isvisit[i] = true;
        			}
        		}
			}
        }
        if(answer == Integer.MAX_VALUE) {
        	return 0;
        }
		return answer;
    }

	static boolean isConnected(String str, String str2) {
		int countL = str.length();
		int countC =0;
		for (int i = 0; i < countL; i++) {
			if(str.charAt(i)!=str2.charAt(i)) countC++;
			if(countC>1) return false;
		}
		return true;
	}
	static class node {
		int count;
		String str;
		public node(int count,  String str) {
			this.count =count;
			this.str = str;
		}
	}
}
