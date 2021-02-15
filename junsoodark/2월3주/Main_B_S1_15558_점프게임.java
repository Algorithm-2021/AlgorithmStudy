/*
 * memory : 25636 KB
 * time : 136 ms
 * 
 * solve time : 1 Hour
 * 
 * 풀이
 * dfs 방법으로 길이 없거나 탈출했을때까지 탐색하여 가능성을 판단한다.
 * 
 * 
 * 
 * 
 */



package algo_2월3주;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_S1_15558_점프게임 {
	static int N, K, res;
	static boolean end = false;
	static char ch[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		res = 0;
		ch = new char[2][];
		ch[0] = br.readLine().toCharArray();
		ch[1] = br.readLine().toCharArray();
		ch[0][0] = '0';
		dfs(0, 0, 0);
		System.out.println(res);
	}

	private static void dfs(int i, int j, int t) {
		if (j < t || end) {
			return;
		}
		if (j + 1 >= N || j + K >= N) {
			res = 1;
			end = true;
			return;
		}
		if (ch[i][j + 1] == '1') {
			ch[i][j + 1] = '0';
			dfs(i, j + 1, t + 1);
			ch[i][j + 1] = '1';
		}
		if (ch[(i + 1) % 2][j + K] == '1') {
			ch[(i + 1) % 2][j + K] = '0';
			dfs((i + 1) % 2, j + K, t + 1);
			ch[(i + 1) % 2][j + K] = '1';
		}
		if (j > 0 && ch[i][j - 1] == '1') {
			ch[i][j - 1] = '0';
			dfs(i, j - 1, t + 1);
			ch[i][j - 1] = '1';
		}
	}

}
