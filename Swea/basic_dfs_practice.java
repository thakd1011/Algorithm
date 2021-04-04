public class UserSolution {
    int[][] graph;
    int king = -1;
    int ans = -1;
    int kingExist = -1;
    int ansExist = -1;

    public void dfs_init(int N, int[][] path) {
        graph = new int[100][6];
        int childCnt = 0;
        int parent;
        int child;
        for(int i = 0; i < N; i++) {
            parent = path[i][0];
            child = path[i][1];
            childCnt = graph[parent][0];
            graph[parent][childCnt + 1] = child;
            graph[parent][0]++;
        }
    }

    public int dfs(int N) {
        if(kingExist == -1) {
            king = N;
            kingExist = 1;
            ans = -1;
        }

        int child = -1;
        for(int i = 1; i < 6; i++) {
            child = graph[N][i];
            if(child == 0) continue;
            if(ansExist != 1 && child > king) {
                ans = child;
                ansExist = 1;
                break;
            }
            else {
                dfs(child);
            }
        }
        if(king == N) {
            kingExist = -1;
            ansExist = -1;
        }

        return ans;
    }
}
