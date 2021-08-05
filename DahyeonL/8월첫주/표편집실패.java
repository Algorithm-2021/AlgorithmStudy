import java.util.Stack; 
class Solution {
    public String solution(int n, int k, String[] cmd) {
        String answer = "";
        boolean[] isDeleted = new boolean[n];
        int orderN = cmd.length;
        String temp = "";
        char order = '?';
        int num = 0;
        Stack<Integer> deletedHang = new Stack<>();
        Stack<Integer> deletedHangTemp = new Stack<>();
        //최대 20만
        for(int i =0; i<orderN; i++){
            temp = cmd[i];
            order = temp.charAt(0);
            if(order =='U'){
                //위로 간다. 삭제된 행이면 그냥 지나감
                num = temp.charAt(2)-'0';
                if(deletedHang.empty()){
                    k = k-num;
                }else{
                    if(num<deletedHang.size()){
                        // 너무 오래걸림
                        while(0<num){
                        //만약 삭제된 행이 아닐경우
                            if(!isDeleted[k-1]) {
                                k--;
                                num--;
                                // System.out.println("U");
                            }else k--;
                        }
                    }
                    else{
                        //만약 이동하는 사이에 삭제된 행이 있다면 그 차이 만큼 빼는 수를 늘려준다.
                        while(!deletedHang.empty()){
                            int deleteTemp = deletedHang.pop();
                            deletedHangTemp.push(deleteTemp);
                            if((k-num)<=deleteTemp&&deleteTemp<k) num++;
                        }
                        //다시 돌려주기
                        while(!deletedHangTemp.empty()){
                            deletedHang.push(deletedHangTemp.pop());
                        }
                        k = k-num;
                    }
                }
            }else if(order =='D'){
                //아래로 간다. 위와 마찬가지
                num = temp.charAt(2)-'0';
                if(deletedHang.empty()){
                    k = k+num;
                }else{
                    if(num<deletedHang.size()){
                         while(0<num){
                            //만약 삭제된 행이 아닐경우
                            if(!isDeleted[k+1]) {
                                k++;
                                num--;
                            }else k++;
                        }
                    }
                    else{
                        while(!deletedHang.empty()){
                            int deleteTemp = deletedHang.pop();
                            deletedHangTemp.push(deleteTemp);
                            if(k<deleteTemp&&deleteTemp<=(k+num)) num++;
                        }
                        //다시 돌려주기
                        while(!deletedHangTemp.empty()){
                            deletedHang.push(deletedHangTemp.pop());
                        }
                        k = k+num;
                    }
                    
                }
            }else if(order =='C'){
                //삭제된 행을 스택에 넣고 트루로 바꾸기
                deletedHang.push(k);
                isDeleted[k]=true;
                //마지막 행인지 확인하기
                //1. k==n-1일때
                //2. 뒤가 모두 삭제된 행일 때
                int tempK = k+1;
                boolean isLast = true;
                if(deletedHang.empty()){
                    if(k==n-1) isLast = true;
                    else isLast = false;
                }else{
                    // 시간 너무 오래걸림
                    while(tempK<n){
                        if(!isDeleted[tempK]){
                            //하나라도 안지워져있으면
                            isLast = false;
                            break;
                        }
                    }
                }
                k = isLast? k-1 : k+1;
                
            }else{
                //되돌리기
                int lastDelete = deletedHang.pop();
                // System.out.println(lastDelete);
                isDeleted[lastDelete] = false;
            }
        }
        StringBuilder sb = new StringBuilder();
        //최대 백만번
        for(int i =0; i<n; i++){
            if(isDeleted[i]) sb.append("X");
            else sb.append("O");
        }
        answer = sb.toString();
        return answer;
    }
    
}
//불린으로 찾기
//삭제 행 기억하기
