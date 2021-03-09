import java.util.LinkedList;
import java.util.Queue;

/*
 * -카카오프렌즈 컬러링북-
 * 1. BFS이용한 영역 카운트
 * 
 * 테스트 1 〉	통과 (23.02ms, 58.6MB)
 * 풀이 시간 : 30M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/1829
public class Solution_P_L2_1829_카카오프렌즈컬러링북 {
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	static boolean[][] isVisited;
	
	static int M,N;
	
	public int[] solution(int m, int n, int[][] picture) {
		int numberOfArea = 0;
		int maxSizeOfOneArea = 0;
		M=m; N=n;
		isVisited = new boolean[M][N];
		
		for (int row = 0; row < m; ++row) {
			for (int col = 0; col < n; ++col) {
				if(isVisited[row][col] || !isColor(row,col,picture)) continue;
				numberOfArea++;
				maxSizeOfOneArea = Math.max(maxSizeOfOneArea, spread(row,col,picture));
			}
		}
		
		int[] answer = new int[2];
		answer[0] = numberOfArea;
		answer[1] = maxSizeOfOneArea;
		return answer;
	}
	
	public static int spread(int row, int col, int[][] picture) {
		Queue<Point> que = new LinkedList<>();
		que.offer(new Point(row,col));
		isVisited[row][col]=true;
		
		int cr,cc, nr,nc;
		int colorCnt=1;
		int targetColor = picture[row][col];
		Point tp;
		while(!que.isEmpty()) {
			tp = que.poll();
			cr=tp.r; cc=tp.c;
			
			for(int d=0; d<4; ++d) {
				nr=cr+drow[d]; nc=cc+dcol[d];
				if(isOut(nr, nc) || isVisited[nr][nc] || !isTargetColor(nr,nc,picture, targetColor)) continue;
				
				que.offer(new Point(nr, nc));
				isVisited[nr][nc]=true;
				colorCnt++;
			}
		}
		return colorCnt;
	}
	
	public static boolean isColor(int r, int c, int[][] picture) {
		if(picture[r][c]!=0)
			return true;
		return false;
	}
	
	public static boolean isTargetColor(int r, int c, int[][] picture, int targetColor) {
		if(picture[r][c]==targetColor)
			return true;
		return false;
	}
	
	public static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=M || c>=N)
			return true;
		return false;
	}
	
	static class Point {
		int r,c;
		Point(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) {
		Solution_P_L2_1829_카카오프렌즈컬러링북 sol = new Solution_P_L2_1829_카카오프렌즈컬러링북();
		int m=6;
		int n=4;
		int[][] arr = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
		int[] answer = sol.solution(m,n,arr);
		for(int i=0; i<answer.length; ++i)
			System.out.print(answer[i]+" ");
	}
}