package discard;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long dp[][] = new long[2][100001];
        int s1[] = new int[100003];
        int s2[] = new int[100003];
        int t1[] = new int[100001];
        int t2[] = new int[100001];
        int n = scanner.nextInt();
        for(int i=0;i<n+2;i++) {
            s1[i] = scanner.nextInt();
        }
        for(int i=0;i<n+2;i++) {
            s2[i] = scanner.nextInt();
        }
        for(int i=1;i<n;i++) {
            t1[i] = scanner.nextInt();
        }
        for(int i=1;i<n;i++) {
            t2[i] = scanner.nextInt();
        }
        dp[0][1] = s1[0] + s1[1];
        dp[1][1] = s2[0] + s2[1];
        for(int i=2;i<=n;i++) {
            if(dp[0][i-1] + s1[i] <= dp[1][i-1] + t2[i-1] + s1[i]) {
                dp[0][i] = dp[0][i-1] + s1[i];
            }else {
                dp[0][i] = dp[1][i-1] + t2[i-1] + s1[i];
            }

            if(dp[1][i-1] + s2[i] <= dp[0][i-1] + t1[i-1] + s2[i]) {
                dp[1][i] = dp[1][i-1] + s2[i];
            }else {
                dp[1][i] = dp[0][i-1] + t1[i-1] + s2[i];
            }
        }
        dp[0][n+1] = dp[0][n] + s1[n+1];
        dp[1][n+1] = dp[1][n] + s2[n+1];
        System.out.println(dp[0][n+1]<dp[1][n+1]? dp[0][n+1]:dp[1][n+1]);
    }
}

