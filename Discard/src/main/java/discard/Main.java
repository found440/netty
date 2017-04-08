package discard;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dp[][] = new int[100003][100003];

        int t1[] = new int[100001];
        int t2[] = new int[100001];
        int n = scanner.nextInt();
        for(int i=1;i<=n+2;i++) {
            dp[0][i] = scanner.nextInt();
        }
        for(int i=1;i<=n+2;i++) {
            dp[i][0] = scanner.nextInt();
        }
        for(int i=1;i<=n-1;i++) {
            t1[i] = scanner.nextInt();
        }
        for(int i=1;i<=n-1;i++) {
            t2[i] = scanner.nextInt();
        }
        for(int i=1;i<=n;i++){
            dp[i-1][]
            for(int j=1;j<=n;j++){
                dp[i][j] = dp[i-1][j] +t[]
            }
        }
    }
}

