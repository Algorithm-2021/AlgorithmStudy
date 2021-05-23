/*
 * -키패드 누르기-
 * 
 * 테스트 15 〉	통과 (0.24ms, 53.4MB)
 * 테스트 16 〉	통과 (0.19ms, 52.6MB)
 * 테스트 17 〉	통과 (0.40ms, 52.6MB)
 * 테스트 18 〉	통과 (0.34ms, 52.7MB)
 * 테스트 19 〉	통과 (0.43ms, 53MB)
 * 테스트 20 〉	통과 (0.34ms, 52.5MB)
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/67256
public class Solution_P_L1_67256_키패드누르기 {
	int[][] pos = {{3,1},
			{0,0}, {0,1}, {0,2},
			{1,0}, {1,1}, {1,2},
			{2,0}, {2,1}, {2,2},
			{3,0}, {3,2},
	};
	
	public String solution(int[] numbers, String hand) {
		StringBuilder sb = new StringBuilder();
		int lh = 10;
		int rh = 11;
		
		for(int number : numbers) {
			switch (number) {
			case 1:
			case 4:
			case 7:
				lh = number;
				sb.append('L');
				break;
				
			case 3:
			case 6:
			case 9:
				rh = number;
				sb.append('R');
				break;
			case 2:
			case 5:
			case 8:
			case 0:
				int d1 = getDist(lh, number);
				int d2 = getDist(rh, number);
				if(d1 == d2) {
					if(hand.equals("right")) {
						rh=number;
						sb.append('R');
					}
					else {
						lh=number;
						sb.append('L');
					}
				}
				else {
					if(d1 > d2) {
						rh=number;
						sb.append('R');
					}
					else {
						lh=number;
						sb.append('L');
					}
				}
				break;
				
			default:
				// do not
				break;
			}
		}
		
		return sb.toString();
	}
	
	int getDist(int pos1, int pos2) {
		return Math.abs(pos[pos1][0]-pos[pos2][0]) + Math.abs(pos[pos1][1]-pos[pos2][1]);
	}

	public static void main(String[] args) {
		Solution_P_L1_67256_키패드누르기 sol = new Solution_P_L1_67256_키패드누르기();
		int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
		String hand = "right";
		String answer = sol.solution(numbers, hand);
		System.out.println(answer);
	}
}