import java.util.Scanner;
class lineUpInOrder
{
    static int N;
    static int M;
    static int[] aheadCnt;
    static int[] queue;
    static linkedList[] order;
    static int front;
    static int rear;
    static int MAX_Q_SIZE = 50001;

    static class node {
        int studentID;
        node next;
        node(int id) {
            studentID = id;
            next = null;
        }
    }
    static class linkedList {
        node head;
        linkedList() {
            head = new node(-1);
        }
        void add(node student) {
            if(head.next != null) {
                node nextNode = head.next;
                student.next = nextNode;
            }
            head.next = student;
        }
    }
    static void queueInit() {
        front = 0;
        rear = 0;
        queue = new int[N + 1];
    }
    static boolean isQEmpty() {
        if(front == rear) return true;
        else return false;
    }
    static boolean isQFull() {
        if((rear + 1) % MAX_Q_SIZE == front) return true;
        else return false;
    }
    static void queuePush(int n) {
        if(!isQFull()) {
            queue[rear] = n;
            rear = (rear + 1) % MAX_Q_SIZE;
        }
    }
    static int queuePop() {
        int ret = -1;
        if(!isQEmpty()) {
            ret = queue[front];
            front = (front + 1) % MAX_Q_SIZE;
        }
        return ret;
    }

    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
        for(int test_case = 1; test_case <= T; test_case++)
        {
            int a = 0;
            int b = 0;
            N = sc.nextInt();
            M = sc.nextInt();
            aheadCnt = new int[N + 1];
            order = new linkedList[N + 1];
            for(int i = 0; i < N + 1; i++) {
                aheadCnt[i] = 0;
                order[i] = new linkedList();
            }
            queueInit();

            for(int i = 0; i < M; i++) {
                a = sc.nextInt();
                b = sc.nextInt();
                aheadCnt[b]++;
                order[a].add(new node(b));
            }
            for(int i = 1; i < N + 1; i++) {
                if(aheadCnt[i] == 0) {
                    queuePush(i);
                }
            }

            int nextStudent;
            int tempStudent;
            System.out.print("#" + test_case);
            while(!isQEmpty()) {
                tempStudent = queuePop();
                System.out.print(" " + tempStudent);

                node cur = order[tempStudent].head;
                while(cur.next != null) {
                    nextStudent = cur.next.studentID;
                    aheadCnt[nextStudent]--;
                    if(aheadCnt[nextStudent] == 0) {
                        queuePush(nextStudent);
                    }
                    cur = cur.next;
                }
            }
            System.out.println();
        }
    }
}
