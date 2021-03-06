//메모리 19972KB	시간 296ms
//걸린시간 1시간

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj_4485_녹색옷입은애가젤다지 {

	static class Data implements Comparable<Data>{
		int x;
		int y;
		int cost;
		
		public Data(int x, int y, int cost) {
			super();
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Data o) {
			return Integer.compare(this.cost, o.cost);
		}
		
		
		
	}
	static int N,ans;
	static int[][] map;
	static int[][] dist;
	static int[] dx= {0,0,1,-1};
	static int[] dy= {1,-1,0,0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc=1;
		while(true) {
			N=Integer.parseInt(br.readLine());
			if(N==0) break;
			map=new int[N][N];
			dist=new int[N][N];
			ans=987654321;
			for(int i=0; i<N; i++) {
				st=new StringTokenizer(br.readLine()," ");
				for(int j=0; j<N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
					dist[i][j]=ans;
				}
			}//입력끝
			PriorityQueue<Data> pq = new PriorityQueue<>();
			pq.offer(new Data(0,0,map[0][0]));
			while(!pq.isEmpty()) {
				Data data=pq.poll();
				for(int i=0; i<4; i++) {
					int nx=data.x+dx[i];
					int ny=data.y+dy[i];
					if(nx<0||ny<0||nx>=N||ny>=N)
						continue;
					if(data.cost+map[nx][ny]<dist[nx][ny]) {
						dist[nx][ny]=data.cost+map[nx][ny];
						pq.offer(new Data(nx,ny,data.cost+map[nx][ny]));
					}
				}
			}
			
			
			System.out.println("Problem "+tc+": "+dist[N-1][N-1]);
			tc++;
		}

	}

}
