import java.util.Scanner;
public class Solution {
    static int minSquare(int i, int j, int k) {
        if (i <= j && j <= k) return i;
        else if(i <= k && k <= j) return i;
        else if(j <= i && i <= k) return j;
        else if(j <= k && k <= i) return j;
        else if(k <= i && i <= j) return k;
        else if(k <= j && j <= i) return k;
        else return k;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int tc = 1; tc <= T; tc++ ){
            int N = sc.nextInt();

            char[][] images = new char[N + 1][N + 1];
            int[][] dp = new int[N + 1][N + 1];
            int maxVal = 0;
            sc.nextLine();

            String oneLine;
            for(int i = 1; i <= N; i++) {
                oneLine = sc.nextLine();
                for(int j = 1; j < oneLine.length(); j++) {
                    images[i][j] = oneLine.charAt(j - 1);
                }
            }

            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    if(images[i][j] == '1') dp[i][j] = 0;
                    else {
                        dp[i][j] = minSquare(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1;
                    }
                    if(maxVal < dp[i][j]) maxVal = dp[i][j];
                }
            }

            System.out.println("#" + tc + " " + maxVal);
        }
    }
}


