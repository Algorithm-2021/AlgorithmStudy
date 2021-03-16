import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * -불량사용자-
 * 
 * 테스트 1 〉	통과 (20.52ms, 53.4MB)
 * 테스트 2 〉	통과 (19.37ms, 53.5MB)
 * 테스트 3 〉	통과 (21.26ms, 53.3MB)
 * 테스트 4 〉	통과 (18.71ms, 53.2MB)
 * 테스트 5 〉	통과 (292.70ms, 86.4MB)
 * 테스트 6 〉	통과 (43.35ms, 55.2MB)
 * 테스트 7 〉	통과 (21.56ms, 53.7MB)
 * 테스트 8 〉	통과 (21.21ms, 53.8MB)
 * 테스트 9 〉	통과 (19.84ms, 53.5MB)
 * 테스트 10 〉	통과 (22.44ms, 54.3MB)
 * 테스트 11 〉	통과 (23.23ms, 53.3MB)
 * 풀이 시간 : 40M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/64064
public class Solution_P_L3_64064_불량사용자 {
	static boolean[] isSelected;
	static List<List<Integer>> list;
	static Set<String> set;
	static int answer = 0;
	
	public int solution(String[] user_id, String[] banned_id) {
		HashMap<String, Integer> hm = new HashMap<>();
		// ban_id에 해당하는 user_id 리스트를 저장하기 위한 리스트
		list = new ArrayList<List<Integer>>();
		set = new HashSet<String>();
		
		for(int i=0; i<user_id.length; ++i) {
			hm.put(user_id[i], i);
		}
		
		// 해당 아이디가 선택 되었는지 확인
		isSelected = new boolean[user_id.length];
		
		// ban_id형태를 정규표현식으로 바꾸기 위한 작업
		String[] convertBannedId = new String[banned_id.length];
		for(int i=0; i<banned_id.length; ++i) {
			convertBannedId[i] = banned_id[i].replaceAll("\\*", ".");
			list.add(new ArrayList<>());
		}
		
		// 정규표현식 이용한 매칭 되는 id 미리 구성
		for(int regexIdx=0; regexIdx<convertBannedId.length; ++regexIdx) {
			for(int i=0; i<user_id.length; ++i) {
				if(user_id[i].matches("^"+convertBannedId[regexIdx]+"$")) {
					list.get(regexIdx).add(hm.get(user_id[i]));
				}
			}
		}
		
		dfs(0, 0, banned_id.length, "");
		
		answer = set.size();
		return answer;
	}
	
	void dfs(int idx, int cnt, int maxCnt, String str){
		// 모두 골랐다면 set으로 중복 제거
		if(cnt==maxCnt) {
			String[] sArr = str.split("");
			Arrays.sort(sArr, (a,b) -> a.compareTo(b));
			StringBuilder sb = new StringBuilder();
			for(String ts : sArr)
				sb.append(ts);
			set.add(sb.toString());
			return;
		}
		
		List<Integer> tempList = list.get(idx);
		int idIdx;
		for(int j=0; j<tempList.size(); ++j) {
			idIdx = tempList.get(j);
			if(isSelected[idIdx]) continue;
			
			isSelected[idIdx]=true;
			
			dfs(idx+1, cnt+1, maxCnt, str+idIdx);
			
			isSelected[idIdx]=false;
		}
	}


	public static void main(String[] args) {
		Solution_P_L3_64064_불량사용자 sol = new Solution_P_L3_64064_불량사용자();
		String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
		String[] banned_id = {"fr*d*", "abc1**"};
		int answer = sol.solution(user_id, banned_id);
		System.out.println(answer);
	}
}