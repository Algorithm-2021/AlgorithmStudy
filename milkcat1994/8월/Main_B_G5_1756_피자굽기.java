import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -피자굽기-
 * 1. 화덕 지름은 위에서 아래로 내림차순으로 지름이 설정된다.
 * 2. 내림차순이므로 위에서 아래로 반죽을 넣는 것이 아닌 아래에서 위로 순차적으로 탐색한다.
 * 
 * 메모리 : 64440 KB
 * 시간 : 592ms
 * 풀이 시간 : 50m
 */

//출처 : https://www.acmicpc.net/problem/1756
public class Main_B_G5_1756_피자굽기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int D, N;
		D = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		// 화덕 지름 길이 입력
		int[] arr = new int[D];
		int exR = Integer.MAX_VALUE;
		int curR;
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<D; ++i) {
			curR = Integer.parseInt(st.nextToken());
			if(exR < curR) {
				curR = exR;
			}
			arr[i] = curR;
			exR = curR;
		}
		
		// 피자 반죽 완성 순서
		st = new StringTokenizer(br.readLine(), " ");
		int ci = D-1;
		int pCurR;
		// 화덕에 넣을 수 없다면 더이상 확인할 필요가 없다.
		while(ci >= 0 && st.hasMoreTokens()) {
			pCurR = Integer.parseInt(st.nextToken());
			
			// 현재 위치가 화덕을 벗어나지 않았으며
			// 현재 화덕 지름 보다 들어오는 반죽 지름이 크다면 현재 위치를 뺴서 위쪽으로 올려준다.
			while(ci >= 0 && arr[ci] < pCurR) {
				ci--;
				// 만약 현재 반죽을 자리를 찾지 못했다면 0을 반환한다.
				if(ci<0) {
					System.out.println(0);
					return;
				}
			}
			// 다음 반죽은 현재 위치보다 한칸 앞에서 부터 확인 할 예정이다.
			ci--;
			System.out.println(ci);
		}
		
		// 아직 넣어야할 피자 반죽이 있다면 0을 반환
		// 반죽을 다 넣었다면 반죽의 위치는 +2를 한 위치이다.
		// (0이 아닌 1부터 시작 하기에 +1, ci는 다음 반죽이 들어올 위치이기에 현재 반죽의 위치는 +1 필요)
		System.out.println(st.hasMoreTokens() ? 0 : ci+2);
	}
	
}
