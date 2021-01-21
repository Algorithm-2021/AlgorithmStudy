package DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author 김대용
 * 26%에서 에러 => 정렬 두번쨰기준 color기준으로 해줬어야..
 * 메모리 : 114748kb
 * 실행시간:	1236ms
 * 푸는시간: 2D
 */
public class Main_B_G3_10800_컬러볼 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder sb= new StringBuilder();
		int N = Integer.parseInt(st.nextToken());

		Ball ball[] = new Ball[N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			ball[i] = new Ball(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i);
		}
		Arrays.sort(ball);
		
		int total_color[] = new int[N+1];
		int same_color_size[] = new int[N+1];
		
		int sum = ball[0].size;
		total_color[ball[0].color]=ball[0].size;

		int ans[] = new int[N+1];
		int same_size=0;
		int same_size_color=0;
		
		// 같은크기? 같은색깔
		for(int i=1; i<N; i++) {
			int size = ball[i].size; 
			int color = ball[i].color; 
			int idx = ball[i].idx;
			
			//이전 공이랑 같은 크기일 경우 체크
			if(size==ball[i-1].size) {
				same_size++;
				if(color==ball[i-1].color) {
					same_size_color++;
				}else {
					same_size_color=0;
				}
			}
			else {
				same_size=0;
				same_size_color=0;
			}
			
			//지금까지의 총합 - 지금까지의 같은색은 제외 - 같은 크기인거 제외 + 같은 색이면서 같은 크기인거
			ans[idx]=sum - total_color[color] - (size * same_size) + (size * same_size_color);
			
//			System.out.println("color "+ball[i].color +" size "+ball[i].size+ " idx "+ball[i].idx+" sum "+sum +" total_color["+color+"] "+total_color[color]+" " + same_size + " " +same_size_color);
			sum+=size; //지금까지의 총합
			total_color[color]+=size; //지금까지의 총합(같은색 빼는용도)
			
			
		}
		for(int i=0; i<N; i++) {
			sb.append(ans[i]+"\n");
		}
		System.out.println(sb.toString());
	}
	public static class Ball implements Comparable<Ball> {
		int color,size,idx;
		public Ball(int color, int size, int idx) {
			this.color= color;
			this.size=size;
			this.idx=idx;
		}
		@Override
		public int compareTo(Ball b) {
			int temp=this.size-b.size;
			if(this.size==b.size) {
				return this.color-b.color;
			}else {
				return this.size-b.size;
			}
		}
	}

	
}
