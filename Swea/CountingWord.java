class UserSolution {
    final static int MAX_HASH_SIZE = 500001;
    static double[] hashTb = new double[MAX_HASH_SIZE];
    static int[] wordCnt = new int[MAX_HASH_SIZE];

    static int findHashKey(double hash) {
        int hashKey = (int)(hash % MAX_HASH_SIZE);
        while(true) {
            if(hashTb[hashKey] == 0) {
                hashTb[hashKey] = hash;
                return hashKey;
            }
            else if(hashTb[hashKey] == hash ) {
                return hashKey;
            }
            else {
                hashKey = (hashKey + 1) % MAX_HASH_SIZE;
            }
        }
    }

    public static int FindString(int N, String A, int M, String B) {
        int answer = 0;

        double foundWordHash = 5381;
        for(int i = 0; i < M; i++) {
            foundWordHash += (int)B.charAt(i);
        }

        double subStrHash = 5381;
        int subStrKey = 0;
        for(int i = 0; i < M; i++) {
            subStrHash += (int)A.charAt(i);
            subStrKey = findHashKey(subStrHash);
            wordCnt[subStrKey]++;
        }

        for(int i = 1; i < N - M; i++) {
            subStrHash = subStrHash - (int)A.charAt(i - 1) + (int)A.charAt(i + M);
            subStrKey = findHashKey(subStrHash);
            wordCnt[subStrKey]++;
        }

        answer = wordCnt[findHashKey(foundWordHash)];
        return answer;
    }
}
