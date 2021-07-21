import java.util.Arrays;

/*
 * -카펫-
 * 1. 완전탐색으로 진행
 * 2. 직사각형으로 만들 수 있는지 확인
 * 3. 직사각형을 만들 수 있다면 노란색의 개수가 맞는지 확인
 * 
 * 풀이시간 : 15M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/42842
public class Solution_P_L2_42842_카펫 {
	public int[] solution(int brown, int yellow) {
		int sum = brown + yellow;
		
		int start=3, end=sum/3;
		for(int i=start; i<=end; ++i) {
			// 완벽히 나누어 떨어질 때 노란색의 개수가 맞는지 확인
			if(sum%i == 0 && yellow == ((i-2) * (sum/i-2))) {
				return new int[] {sum/i, i};
			}
		}
		
		return new int[] {0,0};
	}


	public static void main(String[] args) {
		Solution_P_L2_42842_카펫 sol = new Solution_P_L2_42842_카펫();
		int brown = 10;
		int yellow = 2;
		int[] answer = sol.solution(brown, yellow);
		System.out.println(Arrays.toString(answer));
	}
}