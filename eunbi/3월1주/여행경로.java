
/*테스트 1 〉	통과 (109.44ms, 74.1MB)
테스트 2 〉	통과 (21.71ms, 53MB)
테스트 3 〉	통과 (11.91ms, 54MB)
테스트 4 〉	통과 (12.05ms, 52.2MB)
풀이시간: 1시간
*/
import java.util.*;

public class 여행경로 {

	static class Solution{
		int N;
		boolean[] visit;
		List<String> answers= new ArrayList<>();
		public String[] solution(String[][] tickets) {
			N=tickets.length;
			visit=new boolean[N];
			dfs(0,"ICN","ICN",tickets);
			Collections.sort(answers);
			String[] answer = answers.get(0).split(" ");
			//System.out.println(Arrays.toString(answer));
	        return answer;
	    }
		private void dfs(int cnt, String now, String result, String[][] tickets) {
			if(cnt==N) {
				answers.add(result);
				return;
			}
			for(int i=0; i<tickets.length; i++) {
				if(!visit[i] && tickets[i][0].equals(now)) {
					visit[i]=true;
					dfs(cnt+1, tickets[i][1], result+" "+tickets[i][1], tickets);
					visit[i]=false;
				}
			}
			
		}
		
	}
	public static void main(String[] args) {
		Solution sol=new Solution();
		sol.solution(new String[][]{{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}});
	}

}
