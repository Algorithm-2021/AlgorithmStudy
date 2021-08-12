테스트 1 〉	통과 (6.80ms, 73.7MB)
테스트 2 〉	통과 (21.46ms, 80.7MB)
테스트 3 〉	통과 (0.41ms, 56.7MB)
테스트 4 〉	통과 (5.79ms, 56.4MB)
테스트 5 〉	통과 (1.57ms, 58.6MB)
테스트 6 〉	통과 (1.38ms, 59.3MB)
테스트 7 〉	통과 (45.89ms, 103MB)
테스트 8 〉	통과 (49.56ms, 112MB)
테스트 9 〉	통과 (41.14ms, 84.9MB)
테스트 10 〉	실패 (시간 초과)
테스트 11 〉	실패 (시간 초과)
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
