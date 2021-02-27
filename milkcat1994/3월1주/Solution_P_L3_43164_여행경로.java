import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/*
 * -여행경로-
 * 1. 티켓 정보를 시작점을 기준으로 ticketsMap에 저장한다.
 * 2. 도착점을 가지는 List는 사전순으로 후에 정렬하여 앞부분부터 찾을 수 있도록 한다.
 * 3. 각 티켓의 방문처리를 위해 isVisited라는 hashMap을 추가한다.
 * 4. dfs를 이용하여 경로 저장 및 출력
 * 
 * 풀이 시간 : 2H 30M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/43164?language=java
public class Solution_P_L3_43164_여행경로 {
	// 티켓 정보 가지는 HashMap
	HashMap<String, List<String>> ticketsMap;
	// 방문 배열을 가지는 HashMap
	HashMap<String, boolean[]> isVisited;
	// 결과 정보 가지는 Deque
	Deque<String> res;
	// 다음 이동 공항명 임시 저장 변수
	String ts;
	// 총 티켓 갯수
	int totalTicketNum;
	
	public String[] solution(String[][] tickets) {
		init(tickets);
		
		res.offerLast("ICN");
        dfs("ICN", 0);
        
        // 결과 배열로 저장
        int size = res.size();
        String[] answer = new String[size];
        for(int i=0; i<size; ++i)
        	answer[i] = res.pollFirst();
        	
        return answer;
    }
	
	boolean dfs(String start, int cnt) {
		// 모든 티켓 사용했다면 true 반환
		if(cnt == totalTicketNum)
			return true;
		
		// 다음으로 이동할 수 없다면 false 반환
		if(!ticketsMap.containsKey(start)) return false;
		
		int size = ticketsMap.get(start).size();
		// 해당 시작지점에서 이동 가능한 곳으로 모두 이동
		for(int i=0; i<size; ++i) {
			// 이미 사용한 티켓이라면 패스
			if(isVisited.get(start)[i]) continue;
			
			// 다음 목적지 임시저장 및 경로 추가
			ts = ticketsMap.get(start).get(i);
			isVisited.get(start)[i] = true;
			res.offerLast(ts);
			
			// true 반환 받았다면 결과물이 완성 되었으므로 true 반환
			if(dfs(ts, cnt+1))
				return true;
			
			// 방문 취소 및 경로 취소
			isVisited.get(start)[i] = false;
			res.pollLast();
		}
		
		return false;
	}
	
	void init(String[][] tickets) {
		ticketsMap = new HashMap<String, List<String>>();
		isVisited = new HashMap<String, boolean[]>();
		res = new LinkedList<>();
		
		int ticketLength = tickets.length;
		totalTicketNum = ticketLength;
		for(int i=0; i<ticketLength; ++i) {
			if(ticketsMap.containsKey(tickets[i][0])) {
				ticketsMap.get(tickets[i][0]).add(tickets[i][1]);
			}
			else {
				ticketsMap.put(tickets[i][0], new ArrayList<String>());
				ticketsMap.get(tickets[i][0]).add(tickets[i][1]);
			}
		}
		
		Iterator<String> iter = ticketsMap.keySet().iterator();
		
		
		// 다음 으로 이동 가능한 부분 사전순으로 정렬
		String str;
		while(iter.hasNext()) {
			str = iter.next();
			Collections.sort(ticketsMap.get(str));
			isVisited.put(str, new boolean[ticketsMap.get(str).size()]);
		}
	}

    public static void main(String[] args) {
    	Solution_P_L3_43164_여행경로 sol = new Solution_P_L3_43164_여행경로();
    	
    	String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};
//    	String[][] tickets = {{"ICN", "B"}, {"B", "ICN"}, {"ICN", "B"}, {"B", "C"}};
    	
    	String[] answer = sol.solution(tickets);
    	StringBuilder sb = new StringBuilder();
    	for(int i=0; i<answer.length; ++i) {
    		sb.append(answer[i]+" ");
    	}
    	System.out.println(sb);
    }
}
