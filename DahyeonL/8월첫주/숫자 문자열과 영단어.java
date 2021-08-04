// 테스트 1 〉	통과 (23.38ms, 59.8MB)
// 테스트 2 〉	통과 (11.95ms, 57.8MB)
// 테스트 3 〉	통과 (8.63ms, 66.8MB)
// 테스트 4 〉	통과 (12.17ms, 73.4MB)
// 테스트 5 〉	통과 (9.15ms, 58.2MB)
// 테스트 6 〉	통과 (8.61ms, 56.9MB)
// 테스트 7 〉	통과 (12.66ms, 73.3MB)
// 테스트 8 〉	통과 (12.93ms, 58.1MB)
// 테스트 9 〉	통과 (12.81ms, 55.4MB)
// 테스트 10 〉	통과 (0.08ms, 56.6MB)


class Solution {
    static String[] num = {"zero","one","two","three","four","five", "six","seven","eight","nine"};
    public int solution(String s) {
        int answer = 0;
        char[] str = s.toCharArray();
        int strLen = s.length();
        StringBuilder sb = new StringBuilder();
        String temp = "";
        for(int i = 0; i<strLen;i++){
            if('0'<=str[i]&&str[i]<='9') {
                if(!temp.equals("")) sb.append(change(temp));
                sb.append(str[i]);
                temp ="";
            }
            else {
                int tempN = change(temp);
                if(0<=tempN&&tempN<=9){
                    sb.append(change(temp));
                    temp ="";
                }
                temp = temp+str[i];
            }
        }
        if(!temp.equals("")) sb.append(change(temp));
        answer = Integer.parseInt(sb.toString());
        return answer;
    }
    // static int change(String s){
    //     int len = s.length();
    //     if(len<3) return -1;
    //     char first = s.charAt(0);
    //     char second = s.charAt(1);
    //     if(len == 3){
    //         if(first == 'o') return 1;
    //         else if(first == 't') return 2;
    //         else return 6;
    //     }else if(len == 4){
    //         if(first == 'n') return 9;
    //         else if(first =='z') return 0;
    //         else {
    //             if(second=='o') return 4;
    //             else return 5;     
    //         }
    //     }else if(len == 5){
    //         if(first =='f') return 3;
    //         else if(first =='s') return 7;
    //         else return 8;
    //     }
    //     return -1;
    // }
    static int change(String s){
        for(int i =0; i<10; i++){
            if(num[i].equals(s)) return i;
        }
        return -1;
    }
}
