/*
 * 사용 메모리 : 11416 KB
 * 코드 동작시간 : 72 ms
 * 풀이에 걸린 시간 : 20m
 */
public class 백준2748_피보나치2_김준원 {
    public static void main(String[] args) throws Exception {
        int n = System.in.read() - '0', t = System.in.read();
        if ('0' <= t)
            n = n * 10 + t - '0';
        long a[] = new long[n + 1];
        a[1] = 1;
        for (int i = 2; i <= n; i++)
            a[i] = a[i - 1] + a[i - 2];
        System.out.print(a[n]);
    }
}