package s_20210326;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * -이차원 배열과 연산-
 * 
 * 메모리 : 21728KB
 * 시간 : 308ms
 * 풀이 시간 : 45M
 */

//출처 : https://www.acmicpc.net/problem/17140
public class Main_B_G4_17140_이차원배열과연산 {
	static List<ArrayList<Integer>> rList;
	static List<ArrayList<Integer>> cList;
	static List<ArrayList<Integer>> originList;
	
	static int r,c,k;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		r = Integer.parseInt(st.nextToken())-1;
		c = Integer.parseInt(st.nextToken())-1;
		k = Integer.parseInt(st.nextToken());
		
		rList = new ArrayList<>();
		cList = new ArrayList<>();
		originList = new ArrayList<>();
		for (int row = 0; row < 3; ++row) {
			st = new StringTokenizer(br.readLine(), " ");
			originList.add(new ArrayList<>());
			for (int col = 0; col < 3; ++col) {
				originList.get(row).add(Integer.parseInt(st.nextToken()));
			}
		}
		
		// R : r >= c
		// C : r < c
		int time = 0;
		int R,C;
		while(time <= 100) {
			// r,c 좌표의 값 확인
			if(isRight()) {
				System.out.println(time);
				return;
			}
			
			R = originList.size();
			C = originList.get(0).size();
			if(R < C) {
				originList = funcC();
			}
			else {
				originList = funcR();
			}
			time++;
		}
		System.out.println(-1);
	}
	
	static List<ArrayList<Integer>> funcC() {
		List<ArrayList<Integer>> list = new ArrayList<>();
		Map<Integer, Integer> hm;
		
		List<Point> pList;
		int num;
		int maxRow=0;
		
		int colSize = originList.get(0).size();
		int rowSize = originList.size();
		
		int targetNum;
		for(int col=0; col<colSize; ++col) {
			list.add(new ArrayList<>());
			hm = new HashMap<Integer, Integer>();
			
			// 해당 열별 계산
			for(int row=0; row<rowSize; ++row) {
				targetNum = originList.get(row).get(col);
				// 0은 무시
				if(targetNum == 0) continue;
				if(hm.containsKey(targetNum))
					hm.put(targetNum, hm.get(targetNum)+1);
				else
					hm.put(targetNum, 1);
			}

			// 나온 갯수 따라 List에 넣기
			Iterator<Integer> iter = hm.keySet().iterator();
			pList = new ArrayList<>();
			while(iter.hasNext()) {
				num = iter.next();
				pList.add(new Point(num, hm.get(num)));
			}
			
			// 횟수, 숫자 의한 정렬
			pList.sort((o1, o2) -> {
				if(o1.cnt == o2.cnt)
					return o1.num - o2.num;
				return o1.cnt - o2.cnt;
			});
			
			// 반환할 새로운 List에 넣기
			for(Point point : pList) {
				list.get(col).add(point.num);
				list.get(col).add(point.cnt);
			}
			
			maxRow = Math.max(maxRow, list.get(col).size());
		}
		
		// 가장 길이가 긴 열을 기준으로 모든 열 크기 설정 -> 숫자는 0으로 채울것.
		int sub;
		for(List<Integer> temp : list) {
			sub = maxRow-temp.size();
			for(int i=0; i<sub; ++i)
				temp.add(0);
		}
		
		// list의 현재 row는 col 기준으로 되어있음 -> 변환 필요
		List<ArrayList<Integer>> resList = new ArrayList<>();
		for(int i=0; i<maxRow; ++i)
			resList.add(new ArrayList<>());
		
		for(int col=0; col<colSize; ++col) {
			for(int row=0; row<list.get(col).size(); ++row) {
				resList.get(row).add(list.get(col).get(row));
			}
		}
		
		return resList;
	}

	static List<ArrayList<Integer>> funcR() {
		List<ArrayList<Integer>> list = new ArrayList<>();
		Map<Integer, Integer> hm;
		
		List<Integer> tList;
		List<Point> pList;
		int num;
		int maxCol=0;
		
		for(int i=0; i<originList.size(); ++i) {
			list.add(new ArrayList<>());
			hm = new HashMap<Integer, Integer>();
			tList = originList.get(i);
			
			// 해당 행별 계산
			for(int j=0; j<originList.get(i).size(); ++j) {
				// 0은 무시
				if(tList.get(j) == 0) continue;
				if(hm.containsKey(tList.get(j)))
					hm.put(tList.get(j), hm.get(tList.get(j))+1);
				else
					hm.put(tList.get(j), 1);
			}
			
			// 나온 갯수 따라 List에 넣기
			Iterator<Integer> iter = hm.keySet().iterator();
			pList = new ArrayList<>();
			while(iter.hasNext()) {
				num = iter.next();
				pList.add(new Point(num, hm.get(num)));
			}
			
			// 횟수, 숫자 의한 정렬
			pList.sort((o1, o2) -> {
				if(o1.cnt == o2.cnt)
					return o1.num - o2.num;
				return o1.cnt - o2.cnt;
			});
			
			// 반환할 새로운 List에 넣기
			for(Point point : pList) {
				list.get(i).add(point.num);
				list.get(i).add(point.cnt);
			}
			maxCol = Math.max(maxCol, list.get(i).size());
		}
		
		// 가장 길이가 긴 행을 기준으로 모든 행 크기 설정 -> 숫자는 0으로 채울것.
		int sub;
		for(List<Integer> temp : list) {
			sub = maxCol-temp.size();
			for(int i=0; i<sub; ++i)
				temp.add(0);
		}
		
		return list;
	}
	
	static class Point {
		int num, cnt;
		Point(int num, int cnt){
			this.num = num;
			this.cnt = cnt;
		}
	}
	
	static boolean isRight() {
		if(originList.size() <= r || originList.get(r).size() <= c || originList.get(r).get(c) != k)
			return false;
		return true;
	}
}
