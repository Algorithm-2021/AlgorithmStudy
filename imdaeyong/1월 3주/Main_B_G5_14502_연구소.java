package DP;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
public class Main_B_G5_14502_연구소 {

    static class Node{
        int x,y;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int N, M;
    static int[][] map, nmap;
    static int answer = 0;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static List<Node> virusPosition = new ArrayList<Node>();

    public static void main( String[] args ) throws IOException, NumberFormatException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");

        //Get Input Area
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        nmap = new int[N][M];
        for( int i=0;i<N;i++){
            st =new StringTokenizer(in.readLine(), " ");
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2)
                    virusPosition.add(new Node(i,j));
                }
        }

        solve(0,0);
        //Algorithms Section
        System.out.println(answer);
    }

    static void solve(int start, int depth){
        if( depth == 3){
            copyMap();
            for( Node pos : virusPosition)
                spread(pos.x, pos.y);

            answer = Math.max(answer, getSafeArea());

            return;
        }

        for(int i=start;i<N*M;i++){
            int x = i/M;
            int y = i%M;
            if( map[x][y] == 0){
                map[x][y] =1;
                solve(i+1, depth+1);
                map[x][y] = 0;
            }
        }

    }
    static void copyMap(){
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                nmap[i][j] = map[i][j];
            }
        }
    }
    static void spread(int x, int y){
        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(0 <= nx && nx < N && 0 <= ny && ny < M) {
                if(nmap[nx][ny] == 0) {
                    nmap[nx][ny] = 2;
                    spread(nx, ny);
                }
            }
        }
    }

    static int getSafeArea(){
        int safe = 0;

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if( nmap[i][j] ==0 )
                    safe++;
            }
        }
        return safe;
    }
}
