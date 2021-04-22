import java.util.Scanner;
public class DNA_LCS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine();
        for(int tc = 1; tc <= T; tc++) {
            String a = sc.nextLine();
            String b = sc.nextLine();
            int lenA = a.length();
            int lenB = b.length();

            int[][] dp = new int[lenA + 1][lenB + 1];

            // dp[i][j] means the longest common string where considered stringA's range i and stringB's range j.
            for(int i = 1; i <= lenA; i++) {
                for(int j = 1; j <= lenB; j++) {
                    if(a.charAt(i - 1) == b.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }
                    else {
                        dp[i][j] = dp[i - 1][j] > dp[i][j - 1] ? dp[i - 1][j] : dp[i][j - 1];
                    }
                }
            }
            System.out.println("#" + tc + " " + dp[lenA][lenB]);
            dp = null;
        }
    }
}

