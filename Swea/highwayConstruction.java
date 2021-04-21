import java.util.Scanner;
public class highwayConstruction {

    static final int MAX_HEAP_SIZE = 200001;
    static final int MAX_QUEUE_SIZE = 50001;
    static int N;
    static int M;

    static linkedList[] adj;
    static linkedList connectedEdges;
    static boolean[] isVisited;

    static edge heap[];
    static int queue[];
    static int heapSize = 0;

    static void construction(int src) {
        // start from src, target = dest room.
        while(!queueIsEmpty()) {
            int tempVertexNum = queuePop();
            isVisited[tempVertexNum] = true;

            edge cursor = adj[tempVertexNum].head;
            edge tempEdge = null;
            while(cursor.next != null) {
                tempEdge = cursor.next;
                if(!isVisited[tempEdge.end]) {
                    heapPush(tempEdge);
                }
                cursor = cursor.next;
            }

            while(heapSize > 0 ) {
                tempEdge = heapPop();
                if(!isVisited[tempEdge.end]) {
                    queuePush(tempEdge.end);
                    isVisited[tempEdge.end] = true;
                    connectedEdges.addEdge(tempEdge);
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            M = sc.nextInt();

            adj = new linkedList[N + 1];
            isVisited = new boolean[N + 1];
            connectedEdges = new linkedList();
            for(int i = 0; i < N + 1; i++) {
                adj[i] = new linkedList();
            }

            int s, e, t;
            for(int i = 0; i < M; i++) {
                s = sc.nextInt();
                e = sc.nextInt();
                t = sc.nextInt();
                adj[s].addEdge(new edge(s, e, t));
                adj[e].addEdge(new edge(e, s, t));
            }

            heapInit();
            queueInit();
            queuePush(1);
            construction(1);

            long sum = 0;
            edge cursor = connectedEdges.head;
            while(cursor.next != null) {
//                System.out.println(cursor.next.start + " to" + cursor.next.end + ", " + cursor.next.weight);
                sum += cursor.next.weight;
                cursor = cursor.next;
            }
            System.out.println("#" + tc + " " + sum);
        }
    }
    // make normal queue
    static int front, rear;
    static void queueInit() {
        queue = new int[MAX_QUEUE_SIZE];
        front = 0; rear = 0;
    }
    static boolean queueIsFull() {
        if((rear + 1) % MAX_QUEUE_SIZE == front) return true;
        else return false;
    }
    static boolean queueIsEmpty() {
        if(front == rear) return true;
        else return false;
    }
    static void queuePush(int vertexNum) {
        if(!queueIsFull()) {
            queue[rear] = vertexNum;
            rear = (rear + 1) % MAX_QUEUE_SIZE;
        }
    }
    static int queuePop() {
        int ret = -1;
        if(!queueIsEmpty()) {
            ret = queue[front];
            front = (front + 1) % MAX_QUEUE_SIZE;
        }
        return ret;
    }

    // make priority queue.
    // put priority on shorter path weight during traversing dijkstra.
    static void heapInit() {
        heap = new edge[MAX_HEAP_SIZE];
        heapSize = 0;
    }

    static void heapPush(edge value)
    {
        if (heapSize + 1 > MAX_HEAP_SIZE)
        {
            return;
        }

        heap[heapSize] = value;

        int current = heapSize;
        while (current > 0 && heap[current].weight < heap[(current - 1) / 2].weight)
        {
            edge temp = heap[(current - 1) / 2];
            heap[(current - 1) / 2] = heap[current];
            heap[current] = temp;
            current = (current - 1) / 2;
        }
        heapSize = heapSize + 1;
    }

    static edge heapPop()
    {
        if (heapSize <= 0)
        {
            return null;
        }

        edge value = heap[0];
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
                child = heap[current * 2 + 1].weight < heap[current * 2 + 2].weight ? current * 2 + 1 : current * 2 + 2;
            }

            if (heap[current].weight < heap[child].weight)
            {
                break;
            }

            edge temp = heap[current];
            heap[current] = heap[child];
            heap[child] = temp;

            current = child;
        }
        return value;
    }

    static class edge{
        int start;
        int end;
        long weight;
        edge next;
        edge(int start, int end, long weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
            this.next = null;
        }
    }

    static class linkedList {
        edge head;
        linkedList() {
            head = new edge(-1, -1, -1);
        }

        void addEdge(edge newEdge) {
            if(head.next != null) {
                newEdge.next = head.next;
            }
            head.next = newEdge;
        }
    }
}
