import java.util.Scanner;

class CRT
{
    static int size;
    static class pair{
        int n;
        int b;
        pair(int n, int b) {
            this.n = n;
            this.b = b;
        }
    }

    static pair heap[];
    static pair arr[];
    static int heapSize = 0;

    static void heapInit()
    {
        heap = new pair[size];
        heapSize = 0;
    }

    static void heapPush(pair value)
    {
        if (heapSize + 1 > size)
        {
            return;
        }

        heap[heapSize] = value;

        int current = heapSize;
        while (current > 0 && heap[current].n > heap[(current - 1) / 2].n)
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
                child = heap[current * 2 + 1].n > heap[current * 2 + 2].n ? current * 2 + 1 : current * 2 + 2;
            }

            if (heap[current].n > heap[child].n)
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
            size = sc.nextInt();
            heapInit();
            arr = new pair[size];

            int b, n, idx, k, compareNum;
            for(int i = 0; i < size; i++) {
                b = sc.nextInt();
                n = sc.nextInt();
                heapPush(new pair(n, b));
            }
            for(int i = 0; i < size; i++) {
                arr[i] = heapPop();
            }

            idx = 1;
            k = 1;
            compareNum = 0;
            boolean isPossible = false;
            while(true) {
                isPossible = false;
                compareNum = arr[idx - 1].n * k + arr[idx - 1].b;
                while((compareNum - arr[idx].b) % arr[idx].n == 0) {
                    idx++;
                    if(idx == size) {
                        isPossible = true;
                        break;
                    }
                }
                if(isPossible) {
                    break;
                }
                else {
                    k++;
                    idx = 1;
                }
            }
            System.out.println("#" + test_case + " " + compareNum);
        }
    }
}
