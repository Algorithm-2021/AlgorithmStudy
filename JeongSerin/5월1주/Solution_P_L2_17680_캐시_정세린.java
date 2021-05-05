/*
테스트 16 〉	통과 (1.66ms, 52.5MB)
테스트 17 〉	통과 (3.04ms, 53.1MB)
테스트 18 〉	통과 (3.68ms, 53.4MB)
테스트 19 〉	통과 (4.21ms, 53.3MB)
테스트 20 〉	통과 (3.63ms, 53.9MB)
 * 40m
 */
package PROGRAMMERS;

import java.util.LinkedList;

public class Solution_P_L2_17680_캐시_정세린 {
	
	static class Solution {
		public int solution(int cacheSize, String[] cities) {
			int answer = 0;
			LinkedList<String> cache = new LinkedList<>();

			for (String city : cities) {
				city = city.toLowerCase();
				int idx = cache.indexOf(city);
				// hit
				if (idx >= 0) {
					cache.remove(idx);
					cache.add(city);
					answer++;
					continue;
				}

				// miss
				cache.add(city);
				answer += 5;
				if (cache.size() > cacheSize) cache.remove(0);
			}

			return answer;
		}
	} // end of Solution

	public static void main(String[] args) {
		Solution solution = new Solution();
		int cacheSize = 5;
		String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};
		int answer = solution.solution(cacheSize, cities);
		System.out.println(answer);
	}

}
