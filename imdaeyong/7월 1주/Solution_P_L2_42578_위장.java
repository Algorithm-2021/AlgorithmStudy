import java.util.HashMap;
/*
 * 테스트 1 〉	통과 (0.40ms, 51.7MB)
테스트 2 〉	통과 (0.26ms, 52.9MB)
테스트 3 〉	통과 (0.24ms, 53.9MB)
테스트 4 〉	통과 (0.39ms, 52.1MB)
테스트 5 〉	통과 (0.36ms, 53MB)
테스트 6 〉	통과 (0.31ms, 52.7MB)
테스트 7 〉	통과 (0.35ms, 53.7MB)
테스트 8 〉	통과 (0.25ms, 51.6MB)
테스트 9 〉	통과 (0.30ms, 52.6MB)
테스트 10 〉	통과 (0.27ms, 52.3MB)
테스트 11 〉	통과 (0.33ms, 52.7MB)
테스트 12 〉	통과 (0.32ms, 51.9MB)
테스트 13 〉	통과 (0.37ms, 52.8MB)
테스트 14 〉	통과 (0.24ms, 52.7MB)
테스트 15 〉	통과 (0.26ms, 52.8MB)
테스트 16 〉	통과 (0.27ms, 52.3MB)
테스트 17 〉	통과 (0.24ms, 52.7MB)
테스트 18 〉	통과 (0.27ms, 52.1MB)
테스트 19 〉	통과 (0.26ms, 52.3MB)
테스트 20 〉	통과 (0.24ms, 51.9MB)
테스트 21 〉	통과 (0.20ms, 52.6MB)
테스트 22 〉	통과 (0.31ms, 51.7MB)
테스트 23 〉	통과 (0.17ms, 52.4MB)
테스트 24 〉	통과 (0.30ms, 51.6MB)
테스트 25 〉	통과 (0.26ms, 52.7MB)
테스트 26 〉	통과 (0.35ms, 53MB)
테스트 27 〉	통과 (0.21ms, 52.4MB)
테스트 28 〉	통과 (0.23ms, 51.9MB)
4H
 */
public class Solution_P_L2_42578_위장 {
	static class Solution{
	 public int solution(String[][] clothes) {
	        HashMap<String, Integer> map= new HashMap<>();
	        for(int i=0; i<clothes.length;i++) {
	        	if(map.containsKey(clothes[i][1])) {
	        		map.put(clothes[i][1],map.get(clothes[i][1])+1);
	        	}else {
	        		map.put(clothes[i][1], 1);	        		
	        	}
	        }
	        System.out.println(map);	
 	        //1,2=5
	        //3,0=3
	        int cnt=1;
	        for(int i: map.values()){
	        	cnt = cnt * (i+1);
	        }
	        return cnt-1;
	    }	
	}
	public static void main(String[] args) {
		Solution solution = new Solution();
		String[][] clothes = {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}};
		System.out.println(solution.solution(clothes));
		
		
	}
}
