class Solution {
    ArrayList<Integer> ans = new ArrayList<Integer> ();
    int[][] visited;
    int[][] matrixMap;
    int[][] dir = {{0,1}, {1,0}, {-1,0}, {0,-1}};
    int R, C;
    
    void dfs(int row, int col, ArrayList<Integer> temp) {
        for(int i = 0; i < 4; i++) {
            int dr = dir[i][0];
            int dc = dir[i][1];
            int nextR = row + dr;
            int nextC = col + dc;
            if(nextR < 0 || nextC < 0 || nextR >= R || nextC >= C) {
                continue;
            }
            if(visited[nextR][nextC] == 1) {
                continue;
            }
            
            if(matrixMap[nextR][nextC] > matrixMap[row][col]) {
                visited[nextR][nextC] = 1;
                temp.add(matrixMap[nextR][nextC]);
                dfs(nextR, nextC, temp);
                int idx = temp.size() - 1;
                temp.remove(idx);
                visited[nextR][nextC] = 0;
            }
        }
        
        if(ans.size() < temp.size()) {
            ans = new ArrayList(temp);
        }
    }
    
    public int longestIncreasingPath(int[][] matrix) {
        R = matrix.length;
        C = matrix[0].length;
        
        visited = new int[R][C];
        matrixMap = new int[R][C];
        
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                matrixMap[i][j] = matrix[i][j];
            }
        }
        
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                ArrayList<Integer> candidate = new ArrayList<Integer>();
                candidate.add(matrix[i][j]);
                visited[i][j] = 1;
                dfs(i, j, candidate);
                visited[i][j] = 0;
            }
        }
        
        // for(int i = 0; i < ans.size(); i++) {
        //     System.out.println(ans.get(i));
        // }
        return ans.size();
    }
}
