import java.util.Scanner;
public class pole {
    static int N, L, R;
    static long ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            L = sc.nextInt();
            R = sc.nextInt();

            long[][][] dp = new long[N + 1][N + 1][N + 1];
            dp[1][1][1] = 1;
            // thinking standard value with dp[N][L][R].
            // looking into this case. for example, when N = 4,
            // dp[4][2][3] => 1 4 3 2
            // dp[5][2][3] has this case 2, 5, 4, 3.
            // it means, when dp[5][2][3] has the same numbers of cases continuously
            // when dp[4][2][3] (n1, n2, n3, n4) + 1 -> n1+1, n2+1, n3+1, n4+1.
            // put n5(=1) at the status of (n1+1, n2+1, n3+1, n4+1) for every interval between the numbers.
            // ==> (1, n1+1, n2+1, n3+1, n4+1), (n1+1, 1, n2+1, n3+1, n4+1), ... (n1+1, n2+1, n3+1, 1, n4+1), (n1+1, n2+1, n3+1, n4+1, 1)
            // when put 1 value at the first or the last, left view or right view is added 1 pole bar.
            // However, when putting 1 between each numbers, there is no change of the view numbers.
            // So, dp formula has this one : dp[i][j][k] = dp[i - 1][j - 1][k] + dp[i - 1][j][k - 1] + (dp[i - 1][j][k] * (i - 2))

            for(int i = 2; i <= N; i++) {
                for(int j = 1; j <= L; j++) {
                    for(int k = 1; k <= R; k++) {
                        dp[i][j][k] = dp[i - 1][j - 1][k] + dp[i - 1][j][k - 1] + (dp[i - 1][j][k] * (i - 2));
                    }
                }
            }

            ans = dp[N][L][R];
            System.out.println("#" + tc + " " + ans);
            ans = 0;

        }

    }
}
