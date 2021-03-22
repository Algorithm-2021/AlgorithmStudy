import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * -징검다리 건너기-
 * 1. union-find 이용하여 k개 이상의 집합이 나오는 순간까지 파악한다.
 * 2. 징검다리의 cnt는 오름차순 정렬하여 위의 조건에 부합하는 최소 cnt를 찾는다.
 * 
 * 테스트 1 〉	통과 (0.96ms, 51.6MB)
 * 테스트 2 〉	통과 (0.88ms, 53.2MB)
 * 테스트 3 〉	통과 (1.01ms, 52.3MB)
 * 테스트 4 〉	통과 (0.99ms, 52.9MB)
 * 테스트 5 〉	통과 (1.17ms, 52.3MB)
 * 테스트 6 〉	통과 (2.71ms, 52.4MB)
 * 테스트 7 〉	통과 (3.44ms, 52.7MB)
 * 테스트 8 〉	통과 (4.17ms, 53.1MB)
 * 테스트 9 〉	통과 (4.95ms, 54.2MB)
 * 테스트 10 〉	통과 (1.38ms, 52.9MB)
 * 테스트 11 〉	통과 (0.90ms, 51.9MB)
 * 테스트 12 〉	통과 (1.05ms, 52.6MB)
 * 테스트 13 〉	통과 (1.19ms, 52.4MB)
 * 테스트 14 〉	통과 (2.06ms, 52.7MB)
 * 테스트 15 〉	통과 (4.35ms, 52.3MB)
 * 테스트 16 〉	통과 (3.79ms, 52.8MB)
 * 테스트 17 〉	통과 (5.10ms, 52.5MB)
 * 테스트 18 〉	통과 (1.01ms, 52.7MB)
 * 테스트 19 〉	통과 (1.06ms, 52.4MB)
 * 테스트 20 〉	통과 (1.26ms, 52.1MB)
 * 테스트 21 〉	통과 (2.42ms, 53.4MB)
 * 테스트 22 〉	통과 (4.51ms, 53.1MB)
 * 테스트 23 〉	통과 (4.19ms, 52.2MB)
 * 테스트 24 〉	통과 (3.95ms, 52.3MB)
 * 테스트 25 〉	통과 (0.89ms, 51.6MB)
 * 
 * 효율성  테스트
 * 테스트 1 〉	통과 (228.33ms, 74.4MB)
 * 테스트 2 〉	통과 (221.54ms, 74.2MB)
 * 테스트 3 〉	통과 (238.17ms, 74.4MB)
 * 테스트 4 〉	통과 (172.63ms, 74.6MB)
 * 테스트 5 〉	통과 (186.26ms, 74.4MB)
 * 테스트 6 〉	통과 (200.64ms, 74.1MB)
 * 테스트 7 〉	통과 (258.59ms, 74.1MB)
 * 테스트 8 〉	통과 (236.27ms, 74.4MB)
 * 테스트 9 〉	통과 (236.25ms, 74MB)
 * 테스트 10 〉	통과 (246.75ms, 74.3MB)
 * 테스트 11 〉	통과 (224.94ms, 74.4MB)
 * 테스트 12 〉	통과 (241.24ms, 74MB)
 * 테스트 13 〉	통과 (42.50ms, 71.9MB)
 * 테스트 14 〉	통과 (43.86ms, 70.7MB)
 * 
 * 풀이 시간 : 30M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/64062
public class Solution_P_L3_64062_징검다리건너기 {
	static int[] parents;
	static int[] cnt;
	
	public int solution(int[] stones, int k) {
		int N = stones.length;
		cnt = new int[N];
		Arrays.fill(cnt, 1);
		initParent(N);
		
		List<Stone> stoneList = new ArrayList<>();
		for(int idx = 0; idx<N; ++idx) {
			stoneList.add(new Stone(idx, stones[idx]));
		}
		Collections.sort(stoneList, (a,b) -> a.cnt-b.cnt);
		
		int belowCnt = 0;
		int pa,pb;
		for(Stone stone : stoneList) {
			belowCnt = stone.cnt;
			
			if(!isOut(stone.idx-1, N) && stones[stone.idx-1] <= belowCnt) {
				pa = getParent(stone.idx-1);
				pb = getParent(stone.idx);
				if(union(pa, pb)) {
					cnt[pb] = cnt[pa] + cnt[pb];
				}
			}
			
			if(!isOut(stone.idx+1, N) && stones[stone.idx+1] <= belowCnt) {
				pa = getParent(stone.idx);
				pb = getParent(stone.idx+1);
				if(union(pa, pb)){
					cnt[pb] = cnt[pa] + cnt[pb];
				}
			}
			if(cnt[getParent(stone.idx)] >= k) {
				return belowCnt;
			}
		}
		return belowCnt;
	}
	
	static boolean isOut(int idx, int n) {
		if(idx<0 || idx>=n)
			return true;
		return false;
	}
	
	static class Stone{
		int idx, cnt;
		Stone(int idx, int cnt){
			this.idx = idx;
			this.cnt = cnt;
		}
	}
	
	static void initParent(int n) {
		parents = new int[n];
		Arrays.fill(parents, -1);
	}

	static int getParent(int x) {
		if(parents[x] == -1) return x;
		return parents[x] = getParent(parents[x]);
	}
	
	static boolean union(int a, int b) {
		int pa = getParent(a);
		int pb = getParent(b);
		
		if(pa == pb) return false;
		
		parents[pa] = pb;
		
		return true;
	}
	

	public static void main(String[] args) {
		Solution_P_L3_64062_징검다리건너기 sol = new Solution_P_L3_64062_징검다리건너기();
		int[] stones = {2,4,5,3,2,1,4,2,5,1};
		int k = 3;
		int answer = sol.solution(stones, k);
		System.out.println(answer);
	}
}