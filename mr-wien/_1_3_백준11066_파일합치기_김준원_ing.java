/*
 * 사용 메모리 : KB
 * 코드 동작시간 : ms
 * 풀이에 걸린 시간 :
 *      시작 - 13:00
 *      종료 - 
 */
public class _1_3_백준11066_파일합치기_김준원_ing {
    public static void main(String[] args) throws Exception {
        int testcase = 0, t, k = 0, res = 0;
        for (t = System.in.read(); t != '\n'; t = System.in.read())
            if ('0' <= t && t <= '9')
                testcase = testcase * 10 + t - '0';
        int a[] = new int[500];
        StringBuilder txt = new StringBuilder();
        for (int tc = 1; tc <= testcase; tc++) {
            for (t = System.in.read(); t != '\n'; t = System.in.read())
                if ('0' <= t && t <= '9')
                    k = k * 10 + t - '0';
            for (int i = 0; i < k; i++)
                for (t = System.in.read(); '0' <= t && t <= '9'; t = System.in.read())
                    a[i] = a[i] * 10 + t - '0';
            if (t == '\r')
                System.in.read();
            res=(1<<31)-1;
            txt.append(res).append('\n');
        }
        System.out.print(txt);
    }

}