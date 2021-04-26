import java.util.Scanner;
public class secret {
    static int ans;
    static int N;
    static int[][] path;
    static boolean[] visited;

    static void dfs(int src, int cnt) {
        visited[src] = true;
        if(ans < cnt) ans = cnt;
        for(int i = 1; i <= N; i++) {
            if(path[src][i] == 1 && visited[i] == false) {
                dfs(i, cnt+1);
            }
        }
        visited[src] = false;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            int K = sc.nextInt();
            path = new int[N + 1][N + 1];
            visited = new boolean[N + 1];

            for(int i = 0; i < K; i++) {
                int M = sc.nextInt();
                int prev = 0;
                int temp = 0;
                for(int j = 0; j < M; j++) {
                    temp = sc.nextInt();
                    if(j == 0) prev = temp;
                    else {
                        path[prev][temp] = 1;
                        prev = temp;
                    }
                }
            }

            System.out.print("#" + tc);
            for(int i = 1; i <= N; i++) {
                int sum = 0;
                for(int j = 1; j <= N; j++) {
                    sum += path[i][j];
                }
                System.out.print(" " + sum);
            }
            for(int i = 1; i <= N; i++) {
                dfs(i, 1);
            }
            System.out.print(" " + ans);
            System.out.println();
            ans = 0;
        }
    }
}

