class Solution {
    static int answer = 0;
    static int count = 0;
    static char[] arr = {'A', 'C', 'F', 'J', 'M', 'N', 'R','T'};
    public int solution(int n, String[] data) {
        answer = 0;
        char[] output = new char[8];
        boolean[] visited = new boolean[8];
        per(output, visited, 0,data,n);
        return answer;
    }
    //순서만드는 per or 포문
    static void per(char[] output, boolean[] visited,  int depth, String[] data,int n){
        if(depth == 8){
            boolean check = check(output, n, data);
            count++;
            if(check) answer++;
            return;
        }
        for(int i = 0; i<8;i++){
            if(!visited[i]){
                visited[i] = true;
                output[depth] = arr[i];
                per(output,visited, depth+1,data,n);
                visited[i] = false;
            }
        }
    }
    static boolean check(char[] output, int n, String[] data){
        boolean check = false;
        for(int i=0; i <n; i++){
            char first = data[i].charAt(0);
            char second = data[i].charAt(2);
            char len = data[i].charAt(3);
            int num = data[i].charAt(4)-'0';
            //위치 찾기
            int firstPos = findWhere(output, first);
            // System.out.println(firstPos);
            int secondPos = findWhere(output, second);
            // System.out.println(secondPos);
            int dis = Math.abs(firstPos-secondPos)-1;
            // System.out.println(dis+" dis");
            if(len =='='){
                check = (dis==num)? true : false;
            }else if(len =='<'){
                //거리가 조건보다 작을 때
                check = (dis<num)? true : false;
            }else{
                //거리가 조건보다 클 때
                check = (dis>num)? true : false;
            }
            if(!check) return false;
        }
        return check;
    }
    static int findWhere(char[] output, char word){
        for(int i =0; i<8;  i++) if(output[i] == word) return i;
        return -1;
    }
}
//네오는 프로도와 나란히
//라이언은 적어도 세 칸 이상 튜브
//원하는 조건 입력 받았을 때 모든 조건을 만족
// 완탐으로 거르기
// 최대 횟수 8!*100=4,032,000
