import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Kruth's optimization (https://js1jj2sk3.tistory.com/3) << 이걸로 간단히 풀수도 있다는데 이해어렵
 * @author 김대용
 * 메모리 19596kb
 * 실행시간 720ms
 * 푸는시간 2일
 *
 */
public class Main_B_G3_11066_파일합치기 {

	static int T,K;
	static int[] file, sum;
	static int[][] memo;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		T = Integer.parseInt(st.nextToken());

		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(in.readLine());
			K = Integer.parseInt(st.nextToken());

			file = new int[K + 1]; //입력받은 파일
			sum = new int[K + 1]; //해당 번호까지의 합
			memo = new int[K + 1][K + 1]; // 메모이제이션

			//메모이제이션 초기화
			for (int[] arr : memo)
				Arrays.fill(arr, -1);

			//file입력 및  sum초기화
			st = new StringTokenizer(in.readLine());
			for (int i = 1; i <= K; i++) {
				file[i] = Integer.parseInt(st.nextToken());
				sum[i] = sum[i - 1] + file[i];
			}

			System.out.println(dp(1, K));
		}

	}

	public static int dp(int start, int end) {
//		System.out.println();
//		System.out.println("dp진입 start = " +start + " end =" +end);
		if (start >= end) {
//			System.out.println("start "+start+" end "+end+" return 0");
			return 0;
		}

		else if (memo[start][end] != -1) {
//			System.out.println("start "+start+" end "+end+" return "+memo[start][end]);
			return memo[start][end];
		}
		
		else if (end - start == 1) {
//			System.out.println("start "+start+" end "+end+" return "+(file[start] + file[end]));
			return file[start] + file[end];
		}

		else {
			memo[start][end] = Integer.MAX_VALUE;
			
			int plus  = sum[end] - sum[start-1];
			for (int i = start; i <= end; i++) {
				int temp = dp(start, i) + dp(i + 1, end)+ plus;
				//start가 1, end가 3이라고 가정
				//for 1번 =>dp(1,1) +     dp(2,3)     + sum(3) - sum(0)
				//            0    + file(2)+file(3)
				
 				//for 2번 =>     dp(1,2)    + dp(3,3) + sum(3) - sum(0)
				//         file(1)+file(2) +  0
				
				//for 1번(file(2)+file(3)) 과 for 2번(file(1)+file(2))을 비교해서, 더 작은값을 메모
				
				memo[start][end] = Math.min(memo[start][end], temp);
			}
	
			return memo[start][end];
		}
	}
}
