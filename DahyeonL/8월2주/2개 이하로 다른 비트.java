// or연산 1의 갯수와 and연산 1의 갯수 빼기
// 1의 갯수 구하기
// 2^49<10^15<2^50 
// https://kylog.tistory.com/6 ->1의 갯수새기
class Solution {
    public long[] solution(long[] numbers) {
        int len = numbers.length;
        long[] answer = new long[len];
        for(int i = 0; i < len ; i++){
            long num = numbers[i];
            int increase = 0;
            //최대 10^15-num
            while(true){ 
                int countAnd = countOne(num&(num+increase));
                int countOr = countOne(num|(num+increase));
                if(0<(countOr-countAnd)&&(countOr-countAnd)<=2) {
                    answer[i] = num+increase;
                    break;
                }else {
                    increase++;
                }
            }
        }
        return answer;
    }
    
    //최대 50번
    static int countOne(long n){
        int i;
        for(i=0;n!=0;i++){
            n&=(n-1);
        }
        return i;
    }
}
