/*
 * memory : 22404 KB
 * time : 212 ms
 * 
 * time : 1 Hour 0 Minute
 * 
 * 풀이
 * 1. 구간의 합이 S 이상일 때까지 오른쪽 포인트를 한칸씩 이동
 * 2. 찾을경우 찾은 길이보다 하나 줄여 이동하며 S 이상인 경우 탐색
 * 3. S 이상인 경우를 또 찾으면 2번 반복
 * 
 * tip.
 * 1. 숫자를 배열에 대입하며 1개의 숫자가 S보다 큰경우 1을 출력하며 시간 단축
 * 2. 길이가 2인경우 1은 탐색하지 않고 종료
 * 
 */

//출처 : https://www.acmicpc.net/problem/1806
package algo_6월2주;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_G4_1806_부분합 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int arr[] = new int[N];
		boolean end = false;
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if (arr[i] >= S) {
				end = true;
				break;
			}
		}
		if (end) {
			System.out.println(1);
			return;
		}
		int answer = 200000;
		int aPos = 0;
		int bPos = 1;
		int sum = arr[0] + arr[1];
		while (bPos < N) {
			if (sum < S) {
				if (answer == 200000) {
					bPos++;
					if (bPos == N) {
						break;
					}
					sum += arr[bPos];
				} else {
					bPos++;
					if (bPos == N) {
						break;
					}
					sum += arr[bPos];
					sum -= arr[aPos];
					aPos++;
				}
			}
			if (sum >= S) {
				answer = bPos - aPos + 1;
				if (answer == 2) {
					break;
				}
				sum -= arr[aPos];
				aPos++;
			}
		}
		if (sum >= S) {
			answer = bPos - aPos + 1;
		}
		if (answer == 200000) {
			System.out.println(0);
		} else {
			System.out.println(answer);
		}
	}
}
