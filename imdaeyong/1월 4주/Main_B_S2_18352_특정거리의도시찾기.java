package DP;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 *  @author 김대용
 *	사용 메모리 : 332916kb
 *  코드 동작 시간 : 1292ms
 *  풀이에 걸린 시간 : 2H
 *
 */
public class Main_B_S2_18352_특정거리의도시찾기 {
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static int[] min;
    static boolean[] check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        min = new int[N + 1];
        check = new boolean[N+1];
        
        for (int i = 0; i < N + 1; i++) {
            ArrayList<Integer> arr = new ArrayList<>();
            list.add(arr);
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list.get(start).add(end);
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(X);
        check[X] = true;
        min[X] = 0;
        while(!q.isEmpty()){
            int poll = q.poll();
            ArrayList<Integer> next = list.get(poll);
            
            for(int i = 0 ; i < next.size(); i++){
                if(check[next.get(i)] == false){
                    min[next.get(i)] = min[poll] + 1;
                    check[next.get(i)] = true;
                    q.add(next.get(i));
                }
            }
        }
        int count = 0;        
        for(int i = 1 ; i < N + 1; i++){
            if(min[i] == K){
                sb.append(i).append("\n");
                count++;
            }
        }
        if(count == 0){
            System.out.println(-1);
        }else{
            System.out.println(sb.toString());
        }
    }

}
