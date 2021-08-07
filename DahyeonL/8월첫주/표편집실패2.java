//스트링 빌더로 풀기
import java.util.*; 
class Solution {
    public String solution(int n, int k, String[] cmd) {
        String answer = "";
        StringBuilder sbAnswer = new StringBuilder();
        for(int i = 0; i<n; i++) sbAnswer.append(i);
        Stack<int[]> deletedHang = new Stack<>();
        String temp = "";
        char order;
        int orderN = cmd.length;
        int num =0;
        //최대 20만
        for(int i =0; i<orderN; i++){
            temp = cmd[i];
            order = temp.charAt(0);
            if(order =='U'){
                //위로 간다. 삭제된 행이면 그냥 지나감
                num = temp.charAt(2)-'0';
                k = k-num;
            }else if(order =='D'){
                //아래로 간다. 위와 마찬가지
                num = temp.charAt(2)-'0';
                k = k+num;
            }else if(order =='C'){
                //삭제된 행 번호와 그때의 위치를 스택에 넣기
                int[] tempStack = {k,sbAnswer.charAt(k)-'0'};
                deletedHang.push(tempStack);
                // 끝이라면
                if(k == sbAnswer.length()-1){
                    sbAnswer.deleteCharAt(7);
                    // k = k-1;
                    System.out.print(k+" "+sbAnswer.length());
                }
                else sbAnswer.deleteCharAt(k);
            }else{
                //되돌리기
                int[] tempStack = deletedHang.pop();
                int tempK = tempStack[0];
                int tempNum = tempStack[1];
                if(tempK<=k) k++; 
                if(sbAnswer.length()-1<tempK) sbAnswer.append(tempNum);
                else sbAnswer.insert(tempK,tempNum);
            }
        }
        
        for(int i = 0, j=0; i<n; i++) {
            if((sbAnswer.charAt(j)-'0') == i) {
                answer = answer+"O";
                j++;
            }else {
                answer = answer+"X";
            }
        } 
        return answer;
    }
    
}
