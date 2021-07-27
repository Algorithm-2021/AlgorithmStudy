import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * -단속 카메라-
 * 1. 구간을 종료시점이 오름차순이 되도록 정렬
 * 2. 가장 일찍 종료되는 시점에 카메라를 설치하고 해당 카메라를 지날 수 있는 구간은 지나가기
 * 3. 위를 반복하며 카메라 설치 및 개수 계산
 * 
 * 풀이 시간 : 10m
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/42884
public class Solution_P_L3_42884_단속카메라 {
	public int solution(int[][] routes) {
		int answer = 0;
		
		List<Point> list = new ArrayList<>();
		for(int i=0; i<routes.length; ++i) {
			list.add(new Point(routes[i][0], routes[i][1]));
		}
		
		Collections.sort(list, (o1, o2) -> o1.e - o2.e);
		
		int camera = -30001;
		for(Point point : list) {
			// 카메라가 확인할 수 있는 구간인지 확인한다.
			if(point.s <= camera && camera <= point.e)
				continue;
			// 카메라가 확인할 수 없는 구간이라면 카메라를 추가로 하나 설치 한다.
			// 설치 장소는 가장 빨리 나오는 구간의 끝 위치이다.
			answer++;
			camera = point.e;
		}
		
		return answer;
	}
	
	public class Point {
		int s,e;
		Point(int s, int e){
			this.s = s;
			this.e = e;
		}
	}

	public static void main(String[] args) {
		Solution_P_L3_42884_단속카메라 sol = new Solution_P_L3_42884_단속카메라();
		int[][] routes = {{-20,15}, {-14,-5}, {-18,-13}, {-5,-3}};
		int answer = sol.solution(routes);
		System.out.println(answer);
	}
}