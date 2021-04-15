// on swea 가장 짧은 길 전부 청소하기 
import java.util.Scanner;
class shortestCleaning
{
    static final int MAX_SIZE = 500001;
    static int N;
    static int M;

    static linkedList[] adj;
    static long[][] path;
    static long[] minWeight;

    static pair heap[];
    static int heapSize = 0;

    // make priority queue.
    // put priority on shorter path weight during traversing dijkstra.
    static void heapInit() {
        heap = new pair[MAX_SIZE];
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

    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            N = sc.nextInt();
            M = sc.nextInt();
            adj = new linkedList[N + 1];
            path = new long[N + 1][2];
            minWeight = new long[N + 1];

            int src, dest;
            long w;
            node temp;
            for(int i = 1; i < N + 1; i++) {
                adj[i] = new linkedList();
                minWeight[i] = 0;
            }

            // put src, dest, weight to adjusted array.
            for(int i = 0; i < M; i++) {
                src = sc.nextInt();
                dest = sc.nextInt();
                w = sc.nextInt();

                temp = new node(dest, w);
                adj[src].addNode(temp);

                temp = new node(src, w);
                adj[dest].addNode(temp);
            }

            heapInit();
            pair tempPath;
            node cursor;
            node nextNode;

            minWeight[1] = 0;
            heapPush(new pair(1, 0));
            while(heapSize != 0) {
                tempPath = heapPop();
                cursor = adj[tempPath.nodeNum].head;

                while(cursor.next != null) {
                    nextNode = cursor.next;
                    // we fixed first node is starting point at this PS.
                    if(nextNode.dest == 1) {
                        cursor = cursor.next;
                        continue;
                    }

                    /*
                     update next node with shorter path, and then put it's node number in path parray.
                     * path array is set to memorize where it comes from.
                     path[nextnode][0] = "src node"
                     path[nextnode][1] = "src to dest edge's weight"
                     when it doesn't need to be updated cause' it has the same weight,
                     it has to be compared to edge's weight to calculate 'minimum' path's sum.
                     */

                    if(minWeight[nextNode.dest] == 0 || minWeight[nextNode.dest] > minWeight[tempPath.nodeNum] + nextNode.weight) {
                        minWeight[nextNode.dest] = minWeight[tempPath.nodeNum] + nextNode.weight;
                        path[nextNode.dest][0] = tempPath.nodeNum;
                        path[nextNode.dest][1] = nextNode.weight;
                        heapPush(new pair(nextNode.dest, minWeight[nextNode.dest]));
                    }
                    else if(minWeight[nextNode.dest] == minWeight[tempPath.nodeNum] + nextNode.weight) {
                        if(path[nextNode.dest][1] > nextNode.weight) {
                            path[nextNode.dest][0] = tempPath.nodeNum;
                            path[nextNode.dest][1] = nextNode.weight;
                        }
                    }
                    // important part!!
                    // erase dest to src edge to block time limit exceeded.
                    node eraseCur = adj[nextNode.dest].head;
                    while(eraseCur.next != null) {
                        if(eraseCur.next.dest == tempPath.nodeNum) {
                            eraseCur.next = eraseCur.next.next;
                            break;
                        }
                        eraseCur = eraseCur.next;
                    }
                    cursor = cursor.next;
                }
            }

            long sum = 0;
            for(int i = 1; i < N + 1; i++) {
                sum += path[i][1];
            }
            System.out.println("#" + test_case + " " + sum);
        }
    }

    static class node {
        int dest;
        long weight;
        node next;
        node(int dest, long w) {
            this.dest = dest;
            weight = w;
            next = null;
        }
    }

    static class linkedList {
        node head;
        linkedList() {
            head = new node(0, -1);
        }

        void addNode(node newNode) {
            node next = head.next;
            if(next != null) {
                newNode.next = next;
            }
            head.next = newNode;
        }
    }
    static class pair{
        int nodeNum;
        long pathWeight;
        pair(int nodeNum, long pathWeight) {
            this.nodeNum = nodeNum;
            this.pathWeight = pathWeight;
        }
    }
}
