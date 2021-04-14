class UserSolution {
    /*
    !HOW TO APPROACH TO THIS PROBLEM?
    Approach in a brute force method at first.
    Recognize which order I should calculate directly.
    Breaking down result : approach with recurrence relation
    ""v_(i+1) = a_(i+1) * (a_i * v_i + b_i) + b_(i+1)
    or-> v'_(i+1) = a_i * (a_(i+1) * v_i + b_(i+1)) * b_i
    let's think that we sorted it in order.
    v_(i+1) < v'_(i+1)
    RELATION :: {a_(i+1)-1 / b_(i+1)} < {(a_i - 1) / b_i}

    Which order do we need to sort? in descending order {(a_i - 1) / b_i}
    -> in case, we can get a minimum velocity of roller coster.
    * */
    static final int MAX_SIZE = 1000000007;
    static int size;

    static rail heap[];
    static int heapSize = 0;

    static void heapInit()
    {
        heap = new rail[size];
        heapSize = 0;
    }

    static void heapPush(rail value)
    {
        if (heapSize + 1 > size)
        {
            return;
        }

        heap[heapSize] = value;

        int current = heapSize;
        while (current > 0 && ((double)(heap[current].a - 1) / heap[current].b) > ((double)(heap[(current - 1) / 2].a - 1) / heap[(current - 1) / 2].b))
        {
            rail temp = heap[(current - 1) / 2];
            heap[(current - 1) / 2] = heap[current];
            heap[current] = temp;
            current = (current - 1) / 2;
        }

        heapSize = heapSize + 1;
    }

    static rail heapPop()
    {
        if (heapSize <= 0)
        {
            return null;
        }

        rail value = heap[0];
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
                child = ((double)(heap[current * 2 + 1].a - 1) / heap[current * 2 + 1].b) > ((double)(heap[current * 2 + 2].a - 1) / heap[current * 2 + 2].b) ? current * 2 + 1 : current * 2 + 2;
            }

            if (((double)(heap[current].a - 1) / heap[current].b) > ((double)(heap[child].a - 1) / heap[child].b))
            {
                break;
            }

            rail temp = heap[current];
            heap[current] = heap[child];
            heap[child] = temp;

            current = child;
        }
        return value;
    }


    static class rail{
        int a;
        int b;
        rail(int a, int b) {
            this.a = a;
            this.b = b;
        }
        long getVelocity(long v) {
            long ret = ((long)((a*v) + b) % MAX_SIZE);
            return ret;
        }
    }

    public static int MinRailSpeed(int N, int[] a, int[] b) {
        int answer = 0;
        size = N;
        heapInit();

        for(int i = 0; i < N; i++) {
            heapPush(new rail(a[i], b[i]));
        }
        long v = 1;
        rail temp;
        for(int i = 0; i < N; i++) {
            temp = heapPop();
            v = temp.getVelocity(v);
        }
        answer = (int)v;
        return answer;
    }
}
