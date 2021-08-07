import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * -리모컨-
 * 1. 현재값, 가능한 번호[i]에서 방향키로 이동가능한 값 중 최소값 도출(Line 31)
 * 
 * 메모리 : 44312KB
 * 시간 : 248ms
 * 풀이 시간 : 1H 30M
 */

//출처 : https://www.acmicpc.net/problem/1107
public class Main_B_G5_1107_리모컨 {
	static int N,M;
	static final int MAX = 1000000;
	
	static boolean[] button;
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.print(solve());
	}
	
	static int solve() {
		// 초기위치 100번에서 방향키로 이동시 값.
		int res = Math.abs(N-100);
		for(int i=0; i<MAX; ++i) {
			if(isAble(i)) {
				res = Math.min(res, getLength(i)+Math.abs(N-i));
			}
		}
		
		return res;
	}

	// 활성 버튼에 따라 해당 버튼 누를 수 있는지 반환
	static boolean isAble(int num) {
		if(num==0) if(!button[0]) return false;
		
		while(num>0) {
			if(!button[num%10]) return false;
			num /= 10;
		}
		return true;
	}
	
	// 번호의 길이 추출
	static int getLength(int num) {
		return Integer.toString(num).length();
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		button = new boolean[10];
		Arrays.fill(button, true);
		
		if(M > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			while(st.hasMoreTokens()) {
				button[Integer.parseInt(st.nextToken())] = false;
			}
		}
	}
}
