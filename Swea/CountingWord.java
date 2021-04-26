class UserSolution {
    static int[] pi;

    public static int FindString(int N, String A, int M, String B) {
        int answer = 0;
        // A = origin string, B = shorter string what we want to find in A.
        pi = new int[100001];

        // KMP Algorithm for making pi array.
        int start = 1, matchedCnt = 0;
        while(start + matchedCnt < M) {
            if(B.charAt(start + matchedCnt) == B.charAt(matchedCnt)) {
                matchedCnt++;
                pi[start + matchedCnt - 1] = matchedCnt;
            }
            else {
                if(matchedCnt == 0) {
                    start++;
                }
                else {
                    start += matchedCnt - pi[matchedCnt - 1];
                    matchedCnt = pi[matchedCnt - 1];
                }
            }
        }
        // KMP Algorithm for searching B in A.
        int begin = 0;
        matchedCnt = 0;
        while(begin <= N - M) {
            // when the specific character coincides with from origin string.
            if(matchedCnt < M && A.charAt(begin + matchedCnt) == B.charAt(matchedCnt)) {
                matchedCnt++;
                if(matchedCnt == M) {
                    answer++;
                }
            }
            // when the value of character doesn't have the same characters.
            else {
                if(matchedCnt == 0) {
                    begin++;
                }
                else {
                    begin += matchedCnt - pi[matchedCnt - 1];
                    matchedCnt = pi[matchedCnt - 1];
                }
            }
        }

        return answer;
    }
}