import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * -퍼즐 조각 채우기-
 * 1. table, game_board에서 bfs를 통해 각 도형을 가져온다.
 * 2. table에서 도형을 가져올 때 도형을 이루는 개수를 이용하여 Map에 정리하여 더한다.
 * 3. game_board의 도형을 각도마다 돌려가며 table의 도형과 확인한다.
 * 
 * 테스트 15 〉	통과 (0.78ms, 76.8MB)
 * 테스트 16 〉	통과 (0.47ms, 59.2MB)
 * 테스트 17 〉	통과 (0.60ms, 60.4MB)
 * 테스트 18 〉	통과 (0.65ms, 67.6MB)
 * 테스트 19 〉	통과 (0.72ms, 69.8MB)
 * 테스트 20 〉	통과 (0.50ms, 72.6MB)
 * 테스트 21 〉	통과 (0.48ms, 59.3MB)
 * 테스트 22 〉	통과 (0.79ms, 72.7MB)
 * 
 * 풀이 시간 : 2h+
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/84021
public class Solution_P_L3_84021_퍼즐조각채우기 {
	int size;
	int[] drow = {0,0,-1,1};
	int[] dcol = {1,-1,0,0};
	
	public int solution(int[][] game_board, int[][] table) {
		size = game_board.length;
		HashMap<Integer, ArrayList<Figure>> tableMap = new HashMap<Integer, ArrayList<Figure>>();
		List<Figure> gameBoardList = new ArrayList<>();
		ArrayList<Figure> list;
		
		// table의 도형 저장하기
		for (int row = 0; row < size; ++row) {
			for (int col = 0; col < size; ++col) {
				// 도형자리인 경우 확인하고 저장하기
				if(table[row][col] == 1) {
					Figure figure = getFigure(row, col, table, 1);
					int cnt = figure.cnt;

					list = tableMap.getOrDefault(cnt, new ArrayList<>());
					list.add(figure);
					tableMap.put(cnt, list);
				}
			}
		}
		
		// game_board의 도형 저장하기
		for (int row = 0; row < size; ++row) {
			for (int col = 0; col < size; ++col) {
				// 도형자리인 경우 확인하고 저장하기
				if(game_board[row][col] == 0) {
					Figure figure = getFigure(row, col, game_board, 0);
					gameBoardList.add(figure);
				}
			}
		}

		int answer = 0;
		
		int[][] boardFigureArr;
		boolean isFind;
		for(Figure boardFigure : gameBoardList) {
			boardFigureArr = boardFigure.arr;
			isFind = false;
			
			for(int d = 0; d < 4; ++d) {
				if(!tableMap.containsKey(boardFigure.cnt))
					break;
				
				list = tableMap.get(boardFigure.cnt);
				// 도형의 숫자가 일치하는 table의 도형과 확인
				for(Figure tableFigure : list) {
					
					if(checkFitFigure(boardFigureArr, tableFigure.arr)) {
						isFind = true;
						answer += boardFigure.cnt;
						list.remove(tableFigure);
						break;
					}
				}
				
				if(isFind) break;
				
				boardFigureArr = rotate(boardFigureArr);
			}
		}
		
		return answer;
	}

	public boolean checkFitFigure(int[][] figure_board, int[][] game_board) {
		int rSize = figure_board.length;
		int cSize = figure_board[0].length;
		
		// 가로 세로 길이가 동일하지 않다면 확인 X
		if(rSize != game_board.length || cSize != game_board[0].length)
			return false;
		
		for (int row = 0; row < rSize; ++row) {
			for (int col = 0; col < cSize; ++col) {
				if(figure_board[row][col] == game_board[row][col])
					return false;
			}
		}
		return true;
	}
	
	public int[][] rotate(int[][] game_board){
		int rSize = game_board.length;
		int cSize = game_board[0].length;
		int[][] new_arr = new int[cSize][rSize];

		for (int row = 0; row < cSize; ++row) {
			for (int col = 0; col < rSize; ++col) {
				new_arr[row][col] = game_board[rSize-col-1][row];
			}
		}
		
		return new_arr;
	}
	
	public Figure getFigure(int r, int c, int[][] board, int target) {
		boolean[][] isVisited = new boolean[size][size];
		
		Queue<Point> que = new LinkedList<>();
		Queue<Point> boardQue = new LinkedList<>();
		que.offer(new Point(r,c));
		boardQue.offer(new Point(r,c));
		isVisited[r][c] = true;
		
		// 해당 도형의 시작, 끝 행열 정보
		int sr,er;
		int sc,ec;
		sr = er = r;
		sc = ec = c;
		
		int cr,cc, nr,nc, cnt=1;
		Point tp;
		while(!que.isEmpty()) {
			tp = que.poll();
			cr = tp.r; cc = tp.c;
			for(int d=0; d<4; ++d) {
				nr = cr + drow[d];
				nc = cc + dcol[d];
				if(isOut(nr,nc) || isVisited[nr][nc] || board[nr][nc] != target) continue;
				
				isVisited[nr][nc] = true;
				cnt++;
				
				sr = min(sr, nr);
				er = max(er, nr);
				sc = min(sc, nc);
				ec = max(ec, nc);
				
				que.offer(new Point(nr, nc));
				boardQue.offer(new Point(nr, nc));
			}
		}
		
		int[][] arr = new int[er-sr+1][ec-sc+1];
		
		for (int row = 0; row < arr.length; ++row) {
			Arrays.fill(arr[row], target == 0 ? 1 : 0);
		}
		
		while(!boardQue.isEmpty()) {
			tp = boardQue.poll();
			arr[tp.r - sr][tp.c - sc] = target;
			board[tp.r][tp.c] = 2;
		}
		
		return new Figure(cnt, arr);
	}
	
	boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=size || c>=size)
			return true;
		return false;
	}
	
	int max(int a, int b){
		return a > b ? a : b;
	}
	
	int min(int a, int b) {
		return a > b ? b : a;
	}
	
	class Figure {
		int cnt;
		int[][] arr;
		Figure(int cnt, int[][] arr){
			this.cnt = cnt;
			this.arr = arr;
		}
	}
	
	class Point{
		int r, c;
		Point(int r, int c){
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) {
		Solution_P_L3_84021_퍼즐조각채우기 sol = new Solution_P_L3_84021_퍼즐조각채우기();
		int[][] game_board = {{0,0,1,0,1,0,1,0,1,0,1,0,0,1,0,0,0,0}, {1,0,0,0,1,0,1,0,1,0,1,0,0,1,0,1,1,1}, {0,1,1,1,0,0,1,0,1,0,0,1,1,0,1,0,0,0}, {0,0,0,0,1,1,0,0,1,1,0,1,0,0,1,0,0,0}, {0,1,1,1,0,0,1,1,1,1,0,1,1,1,0,1,1,1}, {1,0,1,0,0,0,1,0,0,0,1,0,0,0,0,1,0,0}, {0,0,0,1,1,1,0,0,1,1,0,1,1,1,1,0,0,1}, {1,1,1,0,0,0,1,1,0,0,1,0,0,0,0,1,1,0}, {0,0,1,0,1,1,1,0,0,1,0,1,1,1,1,0,0,0}, {1,1,0,1,1,0,1,1,1,1,0,1,0,0,0,1,1,1}, {0,0,0,0,1,0,0,0,0,1,0,1,0,0,1,0,1,0}, {1,1,1,1,0,1,1,1,1,1,0,1,0,1,0,0,1,0}, {0,0,1,0,0,0,1,0,0,0,1,0,1,0,1,1,0,0}, {1,0,1,1,0,1,1,0,0,0,1,0,0,0,1,0,0,1}, {1,0,0,1,1,0,0,1,1,1,0,1,1,1,0,1,1,0}, {0,1,1,0,0,1,0,1,0,0,1,0,0,0,0,0,1,0}, {0,0,0,1,0,1,0,1,0,0,1,1,1,1,1,1,1,0}, {0,1,0,1,1,0,0,1,0,1,0,0,0,0,0,0,1,0}};
		int[][] table = {{1,1,1,1,1,1,0,1,0,1,1,0,0,1,0,0,1,0}, {0,0,0,0,0,0,1,1,1,0,1,0,1,1,0,1,1,0}, {1,0,1,1,0,1,0,1,0,1,1,0,1,0,1,1,0,1}, {1,1,0,1,1,1,0,1,0,1,0,1,1,0,1,0,0,1}, {1,1,1,0,0,0,1,0,1,0,1,0,0,1,0,0,1,1}, {0,0,0,1,1,1,0,1,1,1,0,1,1,0,1,0,0,0}, {1,1,1,0,0,0,0,0,1,1,0,1,1,0,1,1,1,1}, {0,0,1,0,1,1,0,1,0,0,1,0,0,1,0,0,0,0}, {1,0,1,0,0,0,0,1,0,1,1,0,1,1,0,1,1,1}, {1,0,1,0,1,1,1,1,0,1,1,0,0,0,1,1,1,0}, {1,1,0,1,0,0,0,0,1,0,0,1,1,1,0,0,0,0}, {0,0,1,1,1,1,0,1,1,0,1,0,0,0,1,1,0,1}, {1,1,0,1,0,0,1,0,0,1,0,1,0,1,0,1,0,1}, {1,1,0,0,1,1,1,0,1,1,0,1,0,1,0,1,0,1}, {0,0,1,1,0,1,1,0,1,0,1,1,0,0,0,1,0,0}, {1,1,1,0,1,0,0,1,0,1,1,0,0,1,0,1,0,1}, {0,0,0,0,1,0,1,1,1,0,0,1,0,1,1,0,1,1}, {0,1,1,1,1,0,0,1,0,0,1,1,0,1,0,0,1,1}};
//		answer = 73;
		
//		int[][] game_board = {{1,1,0,0,1,0},{0,0,1,0,1,0},{0,1,1,0,0,1},{1,1,0,1,1,1},{1,0,0,0,1,0},{0,1,1,1,0,0}};
//		int[][] table = {{1,0,0,1,1,0},{1,0,1,0,1,0},{0,1,1,0,1,1},{0,0,1,0,0,0},{1,1,0,1,1,0},{0,1,0,0,0,0}};
//		answer = 14;
		int answer = sol.solution(game_board, table);
		System.out.println(answer);
	}
}