

import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dp[] = new int[100000001];
        int index[] = new int[101];
        int w[] = new int[101];
        int v[] = new int[101];
        int count = 0;

        int N = scanner.nextInt();
        int V = scanner.nextInt();

        for(int i=0;i<N;i++){
            w[i] = scanner.nextInt();
            v[i] = scanner.nextInt();
        }

        for(int i=0;i<N;i++){
            for(int j=V;j>=0;j--) {
                if(j>=w[i]) {
                    if(dp[j] < dp[j-w[i]] + v[i]) {
                        dp[j] = dp[j-w[i]] + v[i];

                    }
                }
            }
        }
        System.out.println(dp[V]);
        System.out.println(count);
        for(int i=0;i<count;i++) {
            if(i ==0)
                System.out.print(index[i]);
            else
                System.out.print(" "+index[i]);
        }
        System.out.println();
    }
}

