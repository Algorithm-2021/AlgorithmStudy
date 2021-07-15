import java.util.LinkedList;
import java.util.Queue;

/*
 * -게임 맵 최단거리-
 * 
 * 풀이 시간 : 10M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/1844
public class Solution_P_L2_1844_게임맵최단거리 {
	int[] drow = {0,0,1,-1};
	int[] dcol = {1,-1,0,0};
	
	int ROW,COL;
	
	class Point{
		int r,c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	public int solution(int[][] maps) {
		ROW = maps.length;
		COL = maps[0].length;
		boolean[][] visited = new boolean[ROW][COL];
		
		Queue<Point> que = new LinkedList<>();
		que.offer(new Point(0,0));
		visited[0][0] = true;
		
		Point tp;
		int cr,cc, nr,nc, size, time=1;
		while(!que.isEmpty()) {
			time++;
			size = que.size();
			
			while(--size>=0) {
				tp = que.poll();
				cr=tp.r; cc=tp.c;
				for(int d=0; d<4; ++d) {
					nr=cr+drow[d]; nc=cc+dcol[d];
					if(isOut(nr,nc) || visited[nr][nc] || maps[nr][nc] == 0) continue;
					
					if(nr==ROW-1 && nc==COL-1) {
						return time;
					}
					
					que.offer(new Point(nr,nc));
					visited[nr][nc] = true;
				}
			}
		}
		
		return -1;
	}
	
	public boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=ROW || c>=COL)
			return true;
		return false;
	}


	public static void main(String[] args) {
		Solution_P_L2_1844_게임맵최단거리 sol = new Solution_P_L2_1844_게임맵최단거리();
		int[][] map = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
		int answer = sol.solution(map);
		System.out.println(answer);
	}
}