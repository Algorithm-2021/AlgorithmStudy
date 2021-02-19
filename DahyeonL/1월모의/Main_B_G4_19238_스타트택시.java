package algo_study_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_G4_19238_스타트택시 {
static int N, M, F;
static boolean Map[][]; 
static int taxiN, taxiM;
static int guest[][];
static boolean ismoved[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());
		Map = new boolean[N][N];
		guest = new int[M][4];
		ismoved = new boolean[M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int temp = Integer.parseInt(st.nextToken());
				Map[i][j] = (temp != 0);
			}
		}
		st = new StringTokenizer(br.readLine());
		taxiN = Integer.parseInt(st.nextToken());
		taxiM = Integer.parseInt(st.nextToken());
		for (int i = 0; i < args.length; i++) {
			st = new StringTokenizer(br.readLine());
			guest[i][0] = Integer.parseInt(st.nextToken());
			guest[i][1] = Integer.parseInt(st.nextToken());
			guest[i][2] = Integer.parseInt(st.nextToken());
			guest[i][3] = Integer.parseInt(st.nextToken());
			
		}
		int check = M;//손님수 체크하기
		int min = Integer.MAX_VALUE;
		int guestN = -1;
		A:
		while(0<check) {
			//현재 택시의 위치에서 가장 가까운 고객 탐색
			for (int i = 0; i < M; i++) { 
				if(!ismoved[i]) {
					int temp = BFS(taxiN,taxiM,guest[i][0],guest[i][1]);
					if(temp==-1) {//거리 탐색이 되지 않는 손님이 있을 때 -1출력 종료
						F = -1;
						break A;
					}
					if(temp<min) { //지금 검사하는 고객이 더 최단거리가 짧을 때
						min = temp;
						guestN = i;
					}else if(temp == min) { //최단거리가 같을 때
						if(guest[i][0]<guest[guestN][0]) { //현재 손님의 행이 더 작을 때
							min = temp;
							guestN = i;
						}else if(guest[i][0]==guest[guestN][0]) { // 최단거리도 같고 행의 번호도 같을 때
							if(guest[i][1]<guest[guestN][1]) { //현재 손님의 열이 더 작을 때
								min = temp;
								guestN = i;
							}
						}
					}
				}
			}
			//손님한테 이동
			if(0<=F-min) {//연료가 가기에 충분하다
				taxiN = guest[guestN][0];
				taxiM = guest[guestN][1];
				F = F - min;
			}else { //연료가 부족해 갈수 없다.
				F = -1;
				break A;
			}
			//목적지로 이동
			int movedis = BFS(taxiN, taxiM, guest[guestN][2], guest[guestN][3]);
			if(movedis<0) { //목적지로 갈 수 없다.
				F = -1;
				break A;
			}
			if(F-movedis<0) { //연료 부족으로 목적지로 갈수 없다.
				F = -1;
				break A;
			}
			taxiN = guest[guestN][2]; //택시 이동
			taxiM = guest[guestN][3];
			F = F - movedis; // 연료 감소
			ismoved[guestN] = true;//이동완료 표시
			check--;//손님 숫자 줄음
			// 연료 채우기
			F = F + movedis*2;
		}
		System.out.println(F); //남은 연료 or -1 출력
	}
	static int BFS(int taxiN, int taxiM, int n, int m) { //여기를 모르겠눼에에에에
		//택시를 중점으로 가까운 손님 찾기
		int min=0;
		//못갈때
		if(min < 0) {
			return -1;
		}
		//갈때는 최단거리
		return min;
	}

}
//질문 guest 파악 하는 법 boolean 쓰지 않고 stack 이나 queue 사용가능한지?
//맵에 아예 손님 넣어버리기
