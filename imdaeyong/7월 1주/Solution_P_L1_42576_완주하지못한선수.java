  import java.util.Arrays;

/*
 * 
 * 정확성  테스트
테스트 1 〉	통과 (0.25ms, 52.9MB)
테스트 2 〉	통과 (0.29ms, 53MB)
테스트 3 〉	통과 (3.45ms, 52.5MB)
테스트 4 〉	통과 (9.77ms, 53.5MB)
테스트 5 〉	통과 (4.61ms, 53.3MB)
효율성  테스트
테스트 1 〉	통과 (129.33ms, 83.7MB)
테스트 2 〉	통과 (221.29ms, 89.6MB)
테스트 3 〉	통과 (280.77ms, 94.8MB)
테스트 4 〉	통과 (321.31ms, 96.8MB)
테스트 5 〉	통과 (298.62ms, 96.4MB)

40M
 */
public class Solution_P_L1_42576_완주하지못한선수 {
	static class Solution {
		public String solution(String[] participant, String[] completion) {
			Arrays.sort(participant);
			Arrays.sort(completion);
			
			for(int i=0; i<completion.length;i++) {
				if(!completion[i].equals(participant[i])) {
					return participant[i];
				}
			}
			
			return participant[participant.length-1];
			
				
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		String participant[]= {"mislav", "stanko", "mislav", "ana"};
		String completion[]= {"stanko", "ana", "mislav"};
		String answer = solution.solution(participant,completion);
		System.out.println(answer);
	}

}
