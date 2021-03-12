package algo_study_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_G5_3190_뱀 {
static int N,K, L;
static boolean mapApple[][];
static int mapSnack[][];
static char pointC[];
static int pointS[];
static int[] dx = {-1,0,1,0};
static int[] dy = {0,1,0,-1};//상,우,하,좌
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		mapApple = new boolean[N][N];
		mapSnack = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(mapSnack[i],-1);
		}
		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken())-1;
			mapApple[n][m] = true;
		}
		L = Integer.parseInt(br.readLine());
		pointS = new int[L];
		pointC = new char[L];
		for (int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			pointS[i]= Integer.parseInt(st.nextToken());
			pointC[i]= st.nextToken().charAt(0);
		}
		//뱀 위치 초기화
		mapSnack[0][0]=1;
		int sec =0;
		//이동방향 초기화
		int dir=1;
		//움직임
		int countP=0;
		int headN=0,headM=0;//머리위치
		int tailN=0, tailM=0; //꼬리 위치
		while(true) {
			//조건
			if(countP<L&&pointS[countP]==sec) {
				if(pointC[countP]=='D') {
					if(dir==3) dir=0;//우회전
					else dir++;
				}else {
					if(dir==0) dir=3;//자회전
					else dir--;
				}
				countP++;
			}
			int tempN = headN+dx[dir];
			int tempM = headM+dy[dir];
			if(tempN<0||N<=tempN||tempM<0||N<=tempM) {
				sec++;
				break;//벽
			}
			if(mapSnack[tempN][tempM]>0) {
				sec++;
				break;//자기 몸통
			}
			if(mapApple[tempN][tempM]) { //사과가 존재할 경우
				mapSnack[tempN][tempM] = mapSnack[headN][headM]+1;
				headN = tempN;
				headM = tempM;
			}else {//사과가 존재하지 않을 경우
				mapSnack[tempN][tempM] = mapSnack[headN][headM]+1;
				headN = tempN;
				headM = tempM;//머리옮기고
				//꼬리옮기기
				int now =mapSnack[tailN][tailM];
				A:for (int i = 0; i < 4; i++) {
					int nextN = tailN+dx[i];
					int nextM = tailM+dy[i];
					if(0<=nextN&&nextN<N&&0<=nextM&&nextM<N) {
						int next = mapSnack[nextN][nextM];
						if(now == next-1) {
							mapSnack[tailN][tailM] =-1;
							tailN = nextN;
							tailM = nextM;
							break A;
						}
					}
				}
			}
			sec++;
		}
		System.out.println(sec);
	}


}
