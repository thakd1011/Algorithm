public class UserSolution {
    int[][] dirSet = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    boolean[][] visited;
    int[][] localMap;
    queue q;

    class position{
        int r;
        int c;
        int distCnt;
        position(int r, int c) {
            this.r = r;
            this.c = c;
            distCnt = 0;
        }
    }

    class queue {
        int MAX_SIZE = 1000;
        position[] queue;
        int front;
        int rear;
        queue() {
            front = 0;
            rear = 0;
            queue = new position[MAX_SIZE];
        }

        boolean queueIsEmpty() {
            if(front == rear) return true;
            else return false;
        }
        boolean queueIsFull() {
            if((rear + 1) % MAX_SIZE == front) return true;
            else return false;
        }
        void queueEnqueue(position value) {
            if(queueIsFull()) return;
            queue[rear] = value;
            rear = (rear + 1) % MAX_SIZE;
        }
        position queueDequeue() {
            if(queueIsEmpty()) return null;
            else {
                position ret = queue[front];
                front = (front + 1) % MAX_SIZE;
                return ret;
            }
        }
    }

    public void bfs_init(int N, int[][] map) {
        q = new queue();
        localMap = new int[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                localMap[i][j] = map[i][j];
            }
        }
    }

    public int bfs(int x1, int y1, int x2, int y2) {
        visited = new boolean[10][10];
        position start = new position(y1 - 1, x1 - 1);
        position dest = new position(y2 - 1, x2 - 1);
        int ans = -1;
        q.queueEnqueue(start);

        while(!q.queueIsEmpty()) {
            position temp = q.queueDequeue();

            if(temp.r == dest.r && temp.c == dest.c) {
                ans = temp.distCnt;
                break;
            }

            int nextR, nextC;
            for(int i = 0; i < 4; i++) {
                nextR = temp.r + dirSet[i][0];
                nextC = temp.c + dirSet[i][1];
                if(nextR < 0 || nextC < 0 || nextR >= 10 || nextC >= 10) continue;
                if(localMap[nextR][nextC] == 1) continue;
                if(visited[nextR][nextC]) continue;
                else {
                    position next = new position(nextR, nextC);
                    next.distCnt = temp.distCnt + 1;
                    q.queueEnqueue(next);
                    visited[nextR][nextC] = true;
                }
            }
        }
        while(!q.queueIsEmpty()) {
            q.queueDequeue();
        }

        return ans;
    }
}
