package algo_4월1주;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution_P_L2_17686_파일명정렬 {
	public static void main(String[] args) {
		String[] str = new String[2];
		str[1] = "MUZI01.zip";
		str[0] = "muzi1.png";
		System.out.println(getHead(str[0]));
		System.out.println(getHead(str[1]));
		System.out.println(getNumber(str[0]));
		System.out.println(getNumber(str[1]));
		System.out.println(Arrays.toString(solution(str)));
	}

	public static String[] solution(String[] files) {
		String[] answer = {};
		PriorityQueue<String> q = new PriorityQueue<String>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				String head1 = getHead(o1).toLowerCase();
				String head2 = getHead(o2).toLowerCase();
				if (head1.compareTo(head2) == 0) {
					int a = getNumber(o1);
					int b = getNumber(o2);
					return a - b;
				} else {
					return head1.compareTo(head2);
				}
			}
		});
		for (int i = 0; i < files.length; i++) {
			q.offer(files[i]);
		}
		answer = new String[files.length];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = q.poll();
		}
		return answer;
	}

	public static String getHead(String str) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
				return sb.toString();
			}
			sb.append(str.charAt(i));
		}
		return sb.toString();
	}

	public static int getNumber(String str) {
		StringBuilder sb = new StringBuilder();
		boolean start = false;
		int cnt = 0;
		for (int i = 0; i < str.length(); i++) {
			if (cnt >= 5) {
				break;
			}
			if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
				start = true;
				cnt++;
				sb.append(str.charAt(i));
				continue;
			}
			if (start) {
				return Integer.parseInt(sb.toString());
			}
		}
		return Integer.parseInt(sb.toString());
	}

}
