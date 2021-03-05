package DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author 김대용
 * 메모리 12040kb
 * 시간 132ms
 * 푸는시간 4H
 *
 */
public class Main_B_G5_1107_리모컨 {
	static boolean broken[] = new boolean[10]; // 고장난 버튼 체크
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int M = Integer.parseInt(in.readLine());
		StringTokenizer st = null;
		if(M!=0) { // M이 0이면... 받으면 안된다
			st = new StringTokenizer(in.readLine());
			
		}
		
		for(int i=0; i<M; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			broken[tmp] = true;
		}
		int near=Math.abs(N-100); // 가장 가까운 채널과의 거리
		int channel=100; //가장 가까운 채널
		int cnt=Math.abs(N-channel); // 눌러야할 버튼의 수
		
//		System.out.println("접근할수있는 가장 가까운 채널과의 거리"+near);
//		System.out.println("100부터 시작한다면 눌러야할 버튼의 수"+cnt);
		
		for(int i=0; i<1000001; i++) { //1~500000까지 다 돌면서 제일 가까운 값을 찾는다.
			if(works(i)) { // 버튼이 누를수 있는 버튼일떄
//				System.out.println("눌렀어요"+i); 
				
				int current = Math.abs(N-i); //현재값에서 목표 채널까지의 거리
				if(near>current) {
					near=current; 
					channel=i;
					cnt=(int)(Math.log10(channel)+1); //정수 자릿수 구하기
					if(cnt<0) cnt=1; //1자리일떄 -21억 되는거 방지
				}
			}
		}	
		int result = near+cnt;
		if(result>Math.abs(N-100)) { //버튼만 누르는게 더 빠를때..
			System.out.println(Math.abs(N-100));
		}else {
			System.out.println(near+cnt);
		}
	}

	private static boolean works(int i) {
		if(i==0) { //0일떄 따로 처리해줘야함.
			if(broken[0]) return false;
		}
		while(i>0) { // 맨 뒷자리부터 누를수 있는 번호가 있는지 체크
			if(broken[i%10]) return false; // 누를수 없는 번호일경우 false 리턴 
			i/=10; // 다음자리 체크
		}
		return true;
	}
}
