import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -점프게임-
 * 1. 점프를 하는 경우를 먼저 본다.
 * 2. 점프를 한 부분에서 앞,뒤 이동이 가능한지 확인한다.
 * 
 * 메모리 : 23216KB
 * 시간 : 236ms
 * 풀이 시간 : 1H
 */

//출처 : https://www.acmicpc.net/problem/15558
public class Main_B_S1_15558_점프게임 {
	static final int LINE = 2;
	static int N,K;
	static String[] line;
	static boolean[][] isVisited;
	
	static int[] move;
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.print(solve() ? 1 : 0);
	}
	
	// 1:safe, 2:danger
	static boolean solve() {
		// 점프 먼저 실행, 이후 앞,뒤 이동
		Queue<Point> que = new LinkedList<>();
		que.offer(new Point(0,0,0));
		isVisited[0][0]=true;
		
		int qs, cpos, npos, ctime, ntime, cline, nline;
		Point tp;
		while(!que.isEmpty()) {
			qs = que.size();
			while(--qs>=0) {
				tp = que.poll();
				que.offer(tp);
				System.out.println(tp);
				cpos=tp.pos; ctime=tp.time; cline=tp.line;
				
				npos = cpos + move[2];
				ntime = ctime+1;
				nline = (tp.line+1)%LINE;
				
				// 도착가능하다면
				if(isFinal(npos)) return true;
				
				// 점프 먼저 진행
				// 안전지대라면 계속 진행, 아니라면 진행 불가
				while(line[nline].charAt(npos) == '1' && !isVisited[nline][npos]) {
					que.offer(new Point(nline, npos, ntime));
					System.out.println(">>"+npos +" "+ ntime + " " + nline);
					isVisited[nline][npos]=true;
					npos += move[2];
					nline = (nline+1)%LINE;
					ntime++;
					// 도착가능하다면
					if(isFinal(npos)) return true;
				}
			}
			
			// 해당 점프에서 한칸씩 이동하기
			qs = que.size();
			while(--qs>=0) {
				tp = que.poll();
				System.out.println(tp);
				cpos=tp.pos; ctime=tp.time; cline=tp.line;

				for(int dir=0; dir<2; ++dir) {
					npos = cpos + move[dir];
					ntime = ctime+1;
					
					// 도착가능하다면
					if(isFinal(npos)) return true;
					
					if(isOut(npos) || line[cline].charAt(npos) == '0' || isVisited[cline][npos] || ntime > npos) continue;
					
					que.offer(new Point(cline, npos, ntime));
					System.out.println("<<"+npos +" "+ ntime + " " + cline);
					isVisited[cline][npos] = true;
				}
			}
		}
		return false;
	}
	
	static boolean isOut(int pos) {
		return pos < 0 ? true : false;
	}
	
	static boolean isFinal(int pos) {
		return pos >= N ? true : false;
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		isVisited = new boolean[2][N];
		
		line = new String[2];
		line[0] = br.readLine();
		line[1] = br.readLine();
		
		move = new int[] {-1,1,K};
	}
	
	static class Point {
		int line, pos, time;
		
		Point(int line, int pos, int time){
			this.line = line;
			this.pos = pos;
			this.time = time;
		}
		
		@Override
		public String toString() {
			return pos +" "+ time + " " + line;
		}
	}
}
