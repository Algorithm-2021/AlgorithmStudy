import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * -징검다리-
 * 1. 구간거리를 이분탐색으로 적절한 구간 길이를 찾는다.
 * 2. 문제와 달리 "n개"가 아닌 "n개 이하"의 돌을 제거하더라도 구간의 값이 갱신되어야한다.
 * 
 * 백준의 "휴게소 세우기"와 비슷한 문제
 * 
 * 정확성  테스트
 * 테스트 1 〉	통과 (0.63ms, 61.9MB)
 * 테스트 2 〉	통과 (0.65ms, 59.6MB)
 * 테스트 3 〉	통과 (0.65ms, 69.5MB)
 * 테스트 4 〉	통과 (9.19ms, 73.2MB)
 * 테스트 5 〉	통과 (8.33ms, 72.5MB)
 * 테스트 6 〉	통과 (30.87ms, 78.3MB)
 * 테스트 7 〉	통과 (46.32ms, 65.9MB)
 * 테스트 8 〉	통과 (33.33ms, 77.4MB)
 * 테스트 9 〉	통과 (0.34ms, 60.2MB)
 * 
 * 풀이 시간 : 1H 30m
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/43236
public class Solution_P_L4_43236_징검다리 {
	public int solution(int distance, int[] rocks, int n) {
		int answer=0;
		int rocksLength = rocks.length;
		List<Integer> list = new ArrayList<>();

		// 범위를 list에 모두 저장
		Arrays.sort(rocks);
		int s = 0;
		for(int i=0; i<rocksLength; ++i) {
			list.add(rocks[i]-s);
			s = rocks[i];
		}
		list.add(distance - rocks[rocksLength-1]);
		
		int left = 0;
		int right = distance;
		int mid;
		int min;
		int sum;
		int listSize = list.size();
		int cnt;
		
		while(left <= right) {
			mid = (left + right) / 2;
			sum = 0;
			min = Integer.MAX_VALUE;
			cnt = 0;
			
			// 모든 구간길이 순서대로 확인하며 mid값 이상인 구간 길이를 저장한다.
			for(int i=0; i<listSize; ++i) {
				sum += list.get(i);
				if(sum < mid) {
					cnt++;
				}
				else {
					min = min < sum ? min : sum;
					sum=0;
				}
			}
			
			// 남아있는 최소 길이를 갱신한다.
			if(sum != 0) {
				min = min < sum ? min : sum;
			}
			
			// 삭제한 돌의 개수가 n개 이하라면 값을 모두 갱신한다.
			if(cnt <= n) {
				answer = min < answer ? answer : min;
				left = mid+1;
			}
			else {
				right = mid-1;
			}
		}
		
		return answer;
	}

	public static void main(String[] args) {
		Solution_P_L4_43236_징검다리 sol = new Solution_P_L4_43236_징검다리();
		int distance = 25;
		int[] rocks = {2,14,11,21,17};
		int n = 2;
		int answer = sol.solution(distance, rocks, n);
		System.out.println(answer);
	}
}