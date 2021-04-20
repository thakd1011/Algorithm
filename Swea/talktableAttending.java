import java.util.Scanner;
public class talktableAttending {

    static final int MAX_SIZE = 500001;
    static long ans = 0;
    static int N;
    static int M;
    static int X;

    static linkedList[] adj;
    static linkedList[] reversedAdj;
    static long[] path;
    static long[] reversedPath;

    static pair heap[];
    static int heapSize = 0;

    static void talktableRounding(linkedList[] graph, long[] path, int src) {
        // start from src, target = dest room.
        pair temp;
        node cursor, next;

        boolean[] isVisited = new boolean[N + 1];
        path[src] = 0;
        heapPush(new pair(src, 0));

        while(heapSize > 0) {
            temp = heapPop();

            if(isVisited[temp.roomNum]) continue;
            else isVisited[temp.roomNum] = true;

            cursor = graph[temp.roomNum].head;
            while (cursor.next != null) {
                next = cursor.next;
                if (path[next.lectureRoom] == 0 || path[next.lectureRoom] > path[temp.roomNum] + next.time) {
                    path[next.lectureRoom] = path[temp.roomNum] + next.time;
                    heapPush(new pair(next.lectureRoom, path[next.lectureRoom]));
                }
                cursor = cursor.next;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            M = sc.nextInt();
            X = sc.nextInt();

            adj = new linkedList[N + 1];
            reversedAdj = new linkedList[N + 1];
            path = new long[N + 1];
            reversedPath = new long[N + 1];
            heapInit();
            for(int i = 0; i < N + 1; i++) {
                adj[i] = new linkedList();
                reversedAdj[i] = new linkedList();
            }

            int s, e, t;
            for(int i = 0; i < M; i++) {
                s = sc.nextInt();
                e = sc.nextInt();
                t = sc.nextInt();
                adj[s].addNode(new node(e, t));
                reversedAdj[e].addNode(new node(s, t));
            }
            // i to X, x to i
            talktableRounding(adj, path, X);
            talktableRounding(reversedAdj, reversedPath, X);
            for(int i = 1; i < N + 1; i++) {
                if(i == X) continue;
                 path[i] += reversedPath[i];
                if(path[i] > ans) ans = path[i];
            }
            System.out.println("#" + tc + " " + ans);
            ans = 0;
        }
    }

    // make priority queue.
    // put priority on shorter path weight during traversing dijkstra.
    static void heapInit() {
        heap = new pair[MAX_SIZE];
        heapSize = 0;
    }

    static void heapPush(pair value)
    {
        if (heapSize + 1 > MAX_SIZE)
        {
            return;
        }

        heap[heapSize] = value;

        int current = heapSize;
        while (current > 0 && heap[current].pathWeight < heap[(current - 1) / 2].pathWeight)
        {
            pair temp = heap[(current - 1) / 2];
            heap[(current - 1) / 2] = heap[current];
            heap[current] = temp;
            current = (current - 1) / 2;
        }
        heapSize = heapSize + 1;
    }

    static pair heapPop()
    {
        if (heapSize <= 0)
        {
            return null;
        }

        pair value = heap[0];
        heapSize = heapSize - 1;

        heap[0] = heap[heapSize];

        int current = 0;
        while (current < heapSize && current * 2 + 1 < heapSize)
        {
            int child;
            if (current * 2 + 2 >= heapSize)
            {
                child = current * 2 + 1;
            }
            else
            {
                child = heap[current * 2 + 1].pathWeight < heap[current * 2 + 2].pathWeight ? current * 2 + 1 : current * 2 + 2;
            }

            if (heap[current].pathWeight < heap[child].pathWeight)
            {
                break;
            }

            pair temp = heap[current];
            heap[current] = heap[child];
            heap[child] = temp;

            current = child;
        }
        return value;
    }

    static class node {
        int lectureRoom;
        int time;
        node next;

        node(int r, int t) {
            lectureRoom = r;
            time = t;
            next = null;
        }
    }

    static class pair{
        int roomNum;
        long pathWeight;
        pair(int roomNum, long pathWeight) {
            this.roomNum = roomNum;
            this.pathWeight = pathWeight;
        }
    }

    static class linkedList {
        node head;
        linkedList() {
            head = new node(-1, 0);
        }

        void addNode(node newNode) {
            if(head.next != null) {
                newNode.next = head.next;
            }
            head.next = newNode;
        }
    }
}
