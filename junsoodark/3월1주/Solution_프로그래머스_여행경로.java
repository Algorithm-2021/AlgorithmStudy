package algo_3월1주;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution_프로그래머스_여행경로 {
	static boolean visited[];
	static List<String> list = new ArrayList<>();
	static String tmp = "";

	public String[] solution(String[][] tickets) {
		String[] answer = {};
		visited = new boolean[tickets.length];
		for (int i = 0; i < tickets.length; i++) {
			String start = tickets[i][0];
			String end = tickets[i][1];
			if (start.equals("ICN")) {
				visited[i] = true;
				tmp = start + ",";
				dfs(tickets, end, 1);
				visited[i] = false;

			}
		}
		Collections.sort(list);
		answer = list.get(0).split(",");

		return answer;
	}

	static void dfs(String[][] tickets, String end, int count) {
		tmp += end + ",";

		if (count == tickets.length) {
			list.add(tmp);
			return;
		}

		for (int i = 0; i < tickets.length; i++) {
			String start = tickets[i][0];
			String end2 = tickets[i][1];

			if (end.equals(start) && !visited[i]) {
				visited[i] = true;
				dfs(tickets, end2, count + 1);
				visited[i] = false;
				tmp = tmp.substring(0, tmp.length() - 4);
			}
		}
	}
}
