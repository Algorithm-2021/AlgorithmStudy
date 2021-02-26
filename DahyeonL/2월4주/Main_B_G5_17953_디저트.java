package algo_study_2021;
/*
 * 메모리 : 105056 KB
 * 시간 :  512 ms
 * 풀이 시간 : 1H 20M
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_G5_17953_디저트 {
static int N, M;
static int[][] menu;
static int[][] max;// 디저트의 최대 만족도
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		menu = new int[M][N];
		max = new int[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				menu[i][j]=Integer.parseInt(st.nextToken());// j날의 i번째 디저트의 만족도
				if(j==0) {
					max[i][j]= menu[i][j];//첫날 만족도
				}
			}
		}
		for (int j = 1; j < N; j++) {
			for (int i = 0; i < M; i++) {
				int tempM = 0;
				for (int k = 0; k < M; k++) {
					if(i==k) {
						tempM = Math.max(menu[i][j]/2+max[k][j-1],tempM);
					}else {
						tempM = Math.max(menu[i][j]+max[k][j-1],tempM);
					}
				}
				max[i][j] = tempM;
			}
		}
		int answer=0;
		for (int i = 0; i < M; i++) {
			answer = Math.max(answer, max[i][N-1]);
		}
		System.out.println(answer);
	}

}
