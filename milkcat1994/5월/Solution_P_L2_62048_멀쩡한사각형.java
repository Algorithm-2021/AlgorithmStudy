/*
 * -멀쩡한 사각형-
 * x를 1칸씩으로 분할한다.
 * x의 시작지점은 내림하여 y좌표를 잡고, y의 시작지점은 올림하여 y좌표를 잡는다.
 * 해당 y좌표의 차이만큼 사라질 사각형 갯수가 나온다.
 * 
 * 풀이 시간 : 1H
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/62048
public class Solution_P_L2_62048_멀쩡한사각형 {
	
	public long solution(int w, int h) {
		long answer = (long)w * h;
		long lost = 0;
		
		for(int idx=1; idx<=w; ++idx) {
			lost += Math.ceil((double)h*idx/w) - Math.floor((double)h*(idx-1)/w);
		}
		return answer - lost;
	}


	public static void main(String[] args) {
		Solution_P_L2_62048_멀쩡한사각형 sol = new Solution_P_L2_62048_멀쩡한사각형();
		int w=8;
		int h=12;
		long answer = sol.solution(w, h);
		System.out.println(answer);
	}
}