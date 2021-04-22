import java.util.Scanner;
public class AllPairShortestPath {
    static final int INF = 1000001;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            int[][] route = new int[N + 1][N + 1];
            int[][] dp = new int[N + 1][N + 1];
            int s, e, w;

            for(int i = 0; i < M; i++) {
                s = sc.nextInt();
                e = sc.nextInt();
                w = sc.nextInt();
                if(s == e) {
                    route[s][e] = 0;
                }
                else if(route[s][e] != 0) {
                    if(route[s][e] > w) {
                        route[s][e] = w;
                    }
                }
                else {
                    route[s][e] = w;
                }
            }
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    if(i != j && route[i][j] == 0) {
                        dp[i][j] = INF;
                    }
                    else {
                        dp[i][j] = route[i][j];
                    }
                }
            }

            for(int k = 1; k <= N; k++) {
                for(int i = 1; i <= N; i++) {
                    for(int j = 1; j <= N; j++) {
                        if(dp[i][k] + dp[k][j] < dp[i][j]) {
                            dp[i][j] = dp[i][k] + dp[k][j];
                        }
                    }
                }
            }
            System.out.print("#" + tc);
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    if(i == j) {
                        System.out.print(" " + 0);
                    }
                    else if(dp[i][j] == INF) {
                        System.out.print(" " + (-1));
                    }
                    else {
                        System.out.print(" " + dp[i][j]);
                    }
                }
            }
            System.out.println();
        }
    }
}

