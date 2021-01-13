package algo_study_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_S2_15486_퇴사2 {
static int N,T[],P[],DP[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		DP= new int[N+2];
		T = new int[N+1];
		P = new int[N+1];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			T[i+1]=Integer.parseInt(st.nextToken());
			P[i+1]=Integer.parseInt(st.nextToken());
		}
		int temp;
		for (int i = N; 0 < i; i--) {
			if(i+T[i]<=N+1) {//일을 할수 있을때
				temp = P[i]+DP[i+T[i]];//일을 했을때 최대
				if(temp<DP[i+1]){//이 일을 안했을 때 더 값이 크다
					DP[i] = DP[i+1];
				}
				else {//일을 했을때 값이 더 크다.
					DP[i] = temp;
				}
			}
			else DP[i]=DP[i+1]; //없을때
		}
		System.out.println(DP[1]);
	}

}
