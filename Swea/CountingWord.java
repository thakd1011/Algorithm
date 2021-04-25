class UserSolution {
    final static double MAX_HASH_SIZE = 1e+9 + 9;
    static double[] hashTb;
    static int[] wordCnt;

    static double mPow(int a, int b) {
        double ret = 1;
        for(int i = 0; i < b; i++) {
            ret = ret * a;
        }
        return ret;
    }

    public static int FindString(int N, String A, int M, String B) {
        int answer = 0;
        double sHash = 0;
        double bHash = 0;
        double pow = 1;

        for(int i = M - 1; i >= 0; i--) {
            sHash += (double)A.charAt(i) * pow;
            bHash += (double)B.charAt(i) * pow;
            if(i > 0 ) pow *= 2;
        }

        if(sHash == bHash) answer++;

        for(int i = 1; i <= N - M; i++) {
            sHash = 2 * (sHash - (double)A.charAt(i - 1) * pow) + (int)A.charAt(i + M - 1);
            if(sHash == bHash) answer++;
        }

        return answer;
    }
}