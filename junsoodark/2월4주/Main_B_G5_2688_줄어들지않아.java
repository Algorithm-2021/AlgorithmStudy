/*
 * memory : 11436 KB
 * time : 80 ms
 * 
 * solve time : 0 Hour 30 Minute
 * 
 * 풀이
 * 10칸의 배열을 선언후 1을 채우고 2번째 칸부터 자기의 값과 전의 값을 합쳐나가면서 마지막 배열의 값을 결과값 배열에 저장해두고
 * 원하는 값을 찾아서 출력
 * 
 * 
 * 
 * 
 */
package algo_2월4주;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_B_G5_2688_줄어들지않아 {
	static int N;
	static long arr[];
	static long ans[];

	public static void main(String[] args) throws IOException {
		arr = new long[10];
		ans = new long[64];
		for (int i = 0; i < 10; i++) {
			arr[i] = 1;
		}
		for (int i = 0; i < 64; i++) {
			for (int j = 1; j < 10; j++) {
				arr[j] = arr[j] + arr[j - 1];
			}
			ans[i] = arr[9];
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int tmp = Integer.parseInt(br.readLine());
			System.out.println(ans[tmp - 1]);
		}
	}
}
