package algo_study_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_G4_17142_연구소3 {
static int N, M, answer,countVirus,min;
static int[][] map, mapclone, virus,chooseVirus;
static int[] dn = {-1,0,1,0}, dm = {0,-1,0,1};
static boolean check[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		mapclone = new int[N][N];
		virus= new int[10][2];
		chooseVirus = new int[M][2];
		int input = 0;
		countVirus =0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				input = Integer.parseInt(st.nextToken());
				map[i][j] = input-3; //빈공간 :-3 벽 :-2  
				if(input ==2) {
					virus[countVirus][0] = i;
					virus[countVirus][1] = j;
					countVirus++;
				}
			}
		}
		check = new boolean[countVirus];
		min = Integer.MAX_VALUE;
		if(check0()) {
			Com(0,M);
			if(min==Integer.MAX_VALUE) {
				System.out.println(-1);
			}else {
				System.out.println(min);
			}
		}else {
			System.out.println(0);
		}
	}
	
	static class node{
		int n;
		int m;
		int sec;
		public node(int n, int m, int sec) {
			this.n = n;
			this.m = m;
			this.sec =sec;
		}
	}
	static void Com(int start_i,int r) {
		if(r==0) {
			int count =0;
			for (int i = 0; i < countVirus; i++) {
				if(check[i]) {
					chooseVirus[count][0]=virus[i][0];
					chooseVirus[count][1]=virus[i][1];
					count++;
				}
			}
			for (int i = 0; i < N; i++) {
				mapclone[i] = map[i].clone();
			}
			BFS();
			int check = check();
			if(check !=-1 && check<min) {
				min = check;
			}
			return;
		}
		for (int i = start_i; i < countVirus; i++) {
			check[i]=true;
			Com(i+1, r-1);
			check[i]=false;
		}
	}
	static void BFS() {
		Queue<node> q = new LinkedList<node>();
		for (int i = 0; i < M; i++) {//초기 바이러스 넣기
			int n = chooseVirus[i][0];
			int m = chooseVirus[i][1];
			q.offer(new node(n,m,0));
			mapclone[n][m] = 0;
		}
		while(!q.isEmpty()){
			node temp = q.poll();
			int tempN =0;
			int tempM =0;
			int value =0;
			for (int i = 0; i < 4; i++) {
				tempN = temp.n+dn[i];
				tempM = temp.m+dm[i];
				if(0<=tempN&&tempN<N&&0<=tempM&&tempM<N) {
					value = mapclone[tempN][tempM];
					if(value<0) { //지나가지 않았거나 벽이다.
						if(value==-3) { //빈공간일 경우
							q.offer(new node(tempN, tempM, temp.sec+1));
							mapclone[tempN][tempM]=temp.sec+1;//몇초에 도달했는가
						}
						if(value ==-1) { //비활성 바이러스 일경우
							q.offer(new node(tempN, tempM, temp.sec+1));
							mapclone[tempN][tempM] = temp.sec; //전과 같아야한다. 이미 만들어진 상태기 떄문에
						}
						//벽은 그냥 패스
					}
				}
			}
		}
		
	}
	
	static int check() {
		int max = -1;
		A:for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(mapclone[i][j]<-2) { //빈공간이 남아있을 경우
					max =-1;
					break A;
				}
				max = Math.max(max, mapclone[i][j]);
			}
		}
		return max;
	}
	static boolean check0() {
		boolean temp = false;
		A:for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]==-3) { 
					temp = true;
					break A;
				}
			}
		}
		return temp;
	}

}


