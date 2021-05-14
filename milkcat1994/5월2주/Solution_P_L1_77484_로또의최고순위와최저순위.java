import java.util.Arrays;

/*
 * --
 * 
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/77484
public class Solution_P_L1_77484_로또의최고순위와최저순위 {
	
	public int[] solution(int[] lottos, int[] win_nums) {
		int[] answer;
		int anyCnt = 0;
		int rightCnt = 0;
		for(int i=0; i<lottos.length; ++i) {
			if(lottos[i] == 0)
				anyCnt++;
			else {
				for(int j=0; j<win_nums.length; ++j) {
					if(lottos[i] == win_nums[j]) {
						rightCnt++;
						break;
					}
				}
			}
		}
		int[] rank = new int[] {6,6,5,4,3,2,1};
		answer = new int[] {rank[rightCnt+anyCnt], rank[rightCnt]};
		return answer;
	}


	public static void main(String[] args) {
		Solution_P_1 sol = new Solution_P_1();
		int[] lottos = {44, 1, 0, 0, 31, 25};
		int[] win_nums = {31, 10, 45, 1, 6, 19};
		int[] answer = sol.solution(lottos, win_nums);
		System.out.println(Arrays.toString(answer));
	}
}