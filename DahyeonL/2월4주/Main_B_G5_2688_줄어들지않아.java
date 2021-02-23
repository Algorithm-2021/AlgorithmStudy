package algo_study_2021;
/*
 * 메모리 : 11376 KB
 * 시간 : 76 ms
 * 풀이 시간 : 50M
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_B_G5_2688_줄어들지않아 {
static long[] answer,sum, num;
static int T;
	public static void main(String[] args) throws NumberFormatException, IOException {
		answer = new long[65];
		sum = new long[10];
		num = new long[10];
		Arrays.fill(num, 1);
		answer[1] = 10;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		int temp =0;
		for (int i = 0; i < T; i++) {
			temp = Integer.parseInt(br.readLine());
			if(answer[temp]==0) {
				find(temp);
			}
			System.out.println(answer[temp]);
		}
		
	}
	
	static void find(int n) {
		for (int i = 2; i <= n; i++) {
			if(answer[i]!=0) continue;
			long tempSum =0;
			for (int j = 0; j < 10; j++) {
				tempSum = tempSum + num[j];
				sum[j] = tempSum;
			}
			long answerSum =0;
			for (int j = 0; j < 10; j++) {
				answerSum = answerSum + sum[j];
			}
			answer[i] = answerSum; 
			num = sum.clone();
		}
	}

}
