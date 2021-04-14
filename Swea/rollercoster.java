class UserSolution {
    final static long MAX_SIZE = 1000000007;
    static rail[] railList;
//    static rail[] orderedRail;
    static long minVal = MAX_SIZE;
    static int size;

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

    static void swap(int i, int j) {
        rail temp = railList[i];
        railList[i] = railList[j];
        railList[j] = temp;
    }

    static void perm(int idx, int cnt, long v) {
        if(v > minVal) return;
        if(cnt >= size) {
            if(minVal > v) minVal = v;
            return;
        }
        else {
            for(int i = idx; i < size; i++) {
                if(railList[i].getVelocity(v) > minVal) return;
                swap(idx, i);
                perm(idx + 1, cnt + 1, railList[i].getVelocity(v));
                swap(idx, i);
            }
        }
    }

    public static int MinRailSpeed(int N, int[] a, int[] b) {
        int answer = 0;
        size = N;
        railList = new rail[N];

        for(int i = 0; i < N; i++) {
            railList[i] = new rail(a[i], b[i]);
        }
        perm(0, 0, 1);
        answer = (int)minVal;
        minVal = MAX_SIZE;
        return answer;
    }
}


