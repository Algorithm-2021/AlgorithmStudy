/*
정확성 테스트

테스트 1 〉	통과 (0.81ms, 51.9MB)
테스트 2 〉	통과 (1.94ms, 51.9MB)
테스트 3 〉	통과 (4.83ms, 52.6MB)
테스트 4 〉	통과 (0.78ms, 54.2MB)
테스트 5 〉	통과 (0.68ms, 52.4MB)
테스트 6 〉	통과 (0.96ms, 54MB)
테스트 7 〉	통과 (0.95ms, 52.7MB)
테스트 8 〉	통과 (1.45ms, 52.4MB)
테스트 9 〉	통과 (0.71ms, 52.1MB)
테스트 10 〉	통과 (1.06ms, 52.9MB)
테스트 11 〉	통과 (1.04ms, 52.5MB)
테스트 12 〉	통과 (0.87ms, 53.2MB)
테스트 13 〉	통과 (1.22ms, 52.5MB)
테스트 14 〉	통과 (0.69ms, 52.6MB)
테스트 15 〉	통과 (0.56ms, 53.1MB)
테스트 16 〉	통과 (0.85ms, 52.6MB)
테스트 17 〉	통과 (1.54ms, 52.2MB)
테스트 18 〉	통과 (0.74ms, 52.6MB)
테스트 19 〉	통과 (0.96ms, 52.1MB)
테스트 20 〉	통과 (0.95ms, 52MB)

time : 0 Hour 30 Minute

풀이
스택과 큐를 사용
정렬하기 전에 큐에 숫자를 저장
오름차순 정렬하여 스택에 숫자를 저장
큐에서 숫자를 뽑고 스택에서 숫자를 뽑고 두 숫자가 같으면 빼고 아니면 둘다 다시 넣는다
두 숫자가 같을 때 카운트를 1 증가시키고 location 위치의 숫자가 나올때 카운트값 출력
 

*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/42587
package algo_7월1주;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Solution_P_L2_42587_프린터 {
	public static void main(String[] args) {
		int arr[] = { 1, 3, 2, 5, 6, 9, 8 };
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public static int solution(int[] priorities, int location) {
		int tmp[] = new int[priorities.length];
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = priorities[i];
		}
		Arrays.sort(tmp);
		Stack<Integer> s = new Stack<>();
		for (int a : tmp) {
			s.push(a);
		}
		int cnt = 0;
		int pos = location;
		Queue<Integer> q = new LinkedList<>();
		for (int a : priorities) {
			q.add(a);
		}
		while (true) {
			int a = q.poll();
			int b = s.pop();
			if (pos == 0) {
				if (a == b) {
					cnt++;
					break;
				}
				q.add(a);
				pos = q.size() - 1;
				s.push(b);
				continue;
			}
			if (a == b) {
				cnt++;
				pos--;
			} else {
				pos--;
				q.add(a);
				s.push(b);
			}
		}
		return cnt;
	}
}
