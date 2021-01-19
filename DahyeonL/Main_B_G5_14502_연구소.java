package algo_study_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_G5_14502_연구소 {
static int N, M, point2[][],count2, map[][], tempmap[][],max;
static int dn[] = {-1,0,1,0};
static int dm[] = {0,-1,0,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		point2 = new int[10][2]; //2의 위치
		count2 = 0; //2의 갯수
		map = new int[N][M];
		tempmap = new int[N][M];
		max =0;//max 초기화
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < M; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if(temp==2) {
					point2[count2][0]=i;
					point2[count2][1]=j;
					count2++;
				}
				map[i][j] = temp;
			}
		}
		com(0,3);
		System.out.println(max);
		
	}
	
	static void com(int start, int r) {//벽세우기
		if(start>N*M||r==0) { //벽이 다 세워 졌을 때
			resetArrays(); //tempmap초기화
			for (int i = 0; i <count2; i++) {//2의 위치만큼 돌려주기
				spread2(point2[i][0],point2[i][1]);
			}
			int tempmax = count();
//			System.out.println("count= "+tempmax);
//			print();
			if(max<tempmax) max= tempmax;
			return;
		}
		for (int i = 0; i < N*M; i++) {
			if(map[i/M][i%M]==0) {
				map[i/M][i%M]=1;
				com(i+1, r-1);
				map[i/M][i%M]=0;
			}
		}
	}
	
	static void spread2(int n, int m) {//2 퍼뜨리기
		for (int i = 0; i < 4; i++) {
			int tempN = n+dn[i];
			int tempM = m+dm[i];
			if(0<=tempN&&tempN<N&&0<=tempM&&tempM<M) {
				if(tempmap[tempN][tempM]==0) {
					tempmap[tempN][tempM]=2;
					spread2(tempN, tempM);
				}
				else continue;
			}
		}
	}
	static void resetArrays() {
		for(int i=0; i<N; i++){
            tempmap[i] = map[i].clone(); 
        }
	}
	
	static int count() {
		int count =0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(tempmap[i][j]==0) count++;
			}
		}
		return count;
	}

	static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
					System.out.print(tempmap[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}

