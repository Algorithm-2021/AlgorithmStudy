// 테스트 1 〉	통과 (0.01ms, 57.2MB)
// 테스트 2 〉	통과 (0.03ms, 58.7MB)
// 테스트 3 〉	통과 (0.03ms, 58.2MB)
// 테스트 4 〉	통과 (0.49ms, 57.6MB)
// 테스트 5 〉	통과 (0.47ms, 73.2MB)
// 테스트 6 〉	통과 (0.51ms, 58.8MB)
// 테스트 7 〉	통과 (30.00ms, 132MB)
// 테스트 8 〉	통과 (24.28ms, 121MB)
// 테스트 9 〉	통과 (24.62ms, 119MB)

class Solution {
    public int[] solution(int n) {
        // 첫 줄 빼면 1번 통과 못함 => while문 계속 돎
        if(n==1) return new int[] {1};
        int max = n*(n+1)/2;
        int[] answer = new int[max];
        int[][] snail = new int[n][];
        //밑, 오른쪽, 대각선 위
        int[] dn = {1,0,-1};
        int[] dm = {0,1,-1};
        //초기화
        for(int i = 1; i<=n;i++){
            int[] hang = new int[i];
            snail[i-1] = hang;
        }
        //숫자 넣기
        int h = 0;
        int y = 0;
        int num = 1;
        int dir = 0;
        while(snail[h][y]==0){
            snail[h][y]=num;
            num++;
            int newH = h+dn[dir];
            int newY = y+dm[dir];
            if(n<=newH) dir = 1;
            else if(snail[h].length<=newY) dir = 2;
            else if(snail[newH][newY]!=0) {
                if(dir==0) dir=1;
                else if(dir==1) dir=2;
                else dir=0;
            }
            //재설정
            h = h+dn[dir];
            y = y+dm[dir];
        }
        int temp = 0;
        for(int i =0;i<n; i++){
            for(int j=0; j<snail[i].length;j++){
                answer[temp] = snail[i][j];
                temp++;
            }
        }
        return answer;
    }
}
