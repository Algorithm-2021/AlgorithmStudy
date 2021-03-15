/*
 * 테스트 1 〉	통과 (15.32ms, 57.3MB)
 * 30m
 * dfs로 탐색, 방문관리 필요
 */
package PROGRAMMERS;

import java.util.Arrays;

public class Solution_P_L2_1829_카카오프렌즈컬러링북 {

	static class Solution {
	    int M, N;
	    int[][] map;
	    boolean[][] visited;
	    int numberOfArea = 0;
	    int maxSizeOfOneArea = 0;
	    int sizeCnt; // 영역크기
	    int[][] dh = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	    
	    public int[] solution(int m, int n, int[][] picture) {
	        int[] answer = new int[2];
	        map = new int[m][n];
	        visited = new boolean[m][n];
	        
	        // 멤버변수에 복사
	        M = m;
	        N = n;
	        for (int i = 0; i < m; i++) map[i] = Arrays.copyOf(picture[i], n);
	        
	        // dfs로 탐색
	        for (int i = 0; i < m; i++){
	            for (int j = 0; j < n; j++){
	            	int color = map[i][j]; // 그림의 색
	                if (visited[i][j] || color == 0) continue; // 이미 방문했거나 빈공간일경우 pass
	                sizeCnt = 0;
	                dfs(i, j, color);
	                maxSizeOfOneArea = Integer.max(maxSizeOfOneArea,sizeCnt); // 영역 사이즈 최대값 비교
	                numberOfArea++; // 영역의 개수+
	            }
	        }
	        answer[0] = numberOfArea;
	        answer[1] = maxSizeOfOneArea;
	        return answer;
	    }
	    
	    public void dfs(int i, int j, int color) {
	        visited[i][j] = true;
	        sizeCnt++; // 영역의 크기
	        
	        for (int d = 0; d < 4; d++){
	            int ni = i + dh[d][0];
	            int nj = j + dh[d][1];
	            if (ni < 0 || ni >= M || nj < 0 || nj >= N) continue; // 범위확인
	            if (!visited[ni][nj] && map[ni][nj] == color) { // 방문여부 및 컬러확인
	                dfs(ni, nj, color);
	            } 
	        }
	    } // end of dfs
	    
	} // end of Solution
	
	public static void main(String[] args) {
		Solution s = new Solution();
		int m = 6;
		int n = 4;
		int[][] picture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}}; // [4, 5]
//		int[][] picture = {{1, 1, 1, 0}, {1, 1, 1, 0}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}}; // [2, 6]
		int[] answer = s.solution(m, n, picture);
		
		System.out.println(Arrays.toString(answer));
	} // end of main
	
}
