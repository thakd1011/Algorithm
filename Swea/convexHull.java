import java.util.Scanner;
public class convexHull {
    final static int MAX_STACK_SIZE = 100001;

    static int N;
    static pair[] pairList;
    static pair[] sortedPair;
    static pair[] ansPair;
    static stack pairStack;

    static class pair {
        int x, y;
        pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static long dist(pair a, pair b) {
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }

    static void merge(int left, int mid, int right) {
        int i = left, j = mid + 1;
        int idx = left;

        while(i <= mid && j <= right) {
            if(pairList[i].y < pairList[j].y) {
                sortedPair[idx++] = pairList[i++];
            }
            else if(pairList[i].y == pairList[j].y) {
                if(pairList[i].x < pairList[j].x) {
                    sortedPair[idx++] = pairList[i++];
                }
                else {
                    sortedPair[idx++] = pairList[j++];
                }
            }
            else {
                sortedPair[idx++] = pairList[j++];
            }
        }

        while(i <= mid) {
            sortedPair[idx++] = pairList[i++];
        }
        while(j <= right) {
            sortedPair[idx++] = pairList[j++];
        }

        // copy sortedPair and past in pairList;
        for(int k = left; k <= right; k++) {
            pairList[k] = sortedPair[k];
        }
    }

    static void mergeSort(int left, int right) {
        if(left >= right) return;
        int mid = (left + right) / 2;
        mergeSort(left, mid);
        mergeSort(mid + 1, right);
        merge(left, mid, right);
    }

    static void mergeRelative(int left, int mid, int right) {
        int i = left, j = mid + 1;
        int idx = left;

        while(i <= mid && j <= right) {
            long ccwVal = ccw(pairList[1], pairList[i], pairList[j]);
            if(ccwVal > 0) { // counter clock-wise
                sortedPair[idx++] = pairList[i++];
            }
            else if(ccwVal < 0) { // clock-wise
                sortedPair[idx++] = pairList[j++];
            }
            else {
                long distA = dist(pairList[1], pairList[i]);
                long distB = dist(pairList[1], pairList[j]);
                if(distA > distB) {
                    sortedPair[idx++] = pairList[j++];
                }
                else {
                    sortedPair[idx++] = pairList[i++];
                }
            }
        }

        while(i <= mid) {
            sortedPair[idx++] = pairList[i++];
        }
        while(j <= right) {
            sortedPair[idx++] = pairList[j++];
        }

        // copy sortedPair and past in pairList;
        for(int k = left; k <= right; k++) {
            pairList[k] = sortedPair[k];
        }
    }

    static void mergeSortRelative(int left, int right) {
        if(left >= right) return;
        int mid = (left + right) / 2;

        mergeSortRelative(left, mid);
        mergeSortRelative(mid + 1, right);
        mergeRelative(left, mid, right);
    }

    static class stack{
        int stackSize;
        pair[] s;

        void stackInit() {
            stackSize = 0;
            s = new pair[MAX_STACK_SIZE];
        }
        boolean stackIsEmpty() {
            if(stackSize == 0) return true;
            else return false;
        }
        boolean stackIsFull() {
            if(stackSize >= MAX_STACK_SIZE) return true;
            else return false;
        }
        void stackPush(pair newPair) {
            if(!stackIsFull()) {
                s[stackSize++] = newPair;
            }
            else {
                return;
            }
        }
        pair stackPop() {
            if(!stackIsEmpty()) {
                pair ret = s[stackSize - 1];
                stackSize--;
                return ret;
            }
            else {
                return null;
            }
        }
        int getSize() {
            return stackSize;
        }
    }

    static long ccw(pair a, pair b, pair c) {
        long criteria = (long)(b.x - a.x) * (long)(c.y - a.y) - (long)(c.x - a.x) * (long)(b.y - a.y);
        return criteria;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            pairList = new pair[N + 1];
            sortedPair = new pair[N + 1];
            pairStack = new stack();
            pairStack.stackInit();

            int x, y;
            pairList[0] = new pair(0, 0);
            for(int i = 1; i <= N; i++) {
                x = sc.nextInt();
                y = sc.nextInt();
                pairList[i] = new pair(x, y);
            }
            // mergesort
            mergeSort(1, N);
            mergeSortRelative(2, N);
            pairStack.stackPush(pairList[1]);

            pair first, second, pairComp;
            int pairCnt;
            for(int i = 2; i <= N; i++) {
                while(pairStack.stackSize > 1) {
                    pairCnt = pairStack.getSize();
                    second = pairStack.s[pairCnt - 1];
                    first = pairStack.s[pairCnt - 2];
                    pairComp = pairList[i];

                    if(ccw(first, second, pairComp) <= 0) {
                        pairStack.stackPop();
                    }
                    else {
                        break;
                    }
                }
                pairStack.stackPush(pairList[i]);
            }

            System.out.println("#" + tc + " " + pairStack.stackSize);
        }
    }
}

