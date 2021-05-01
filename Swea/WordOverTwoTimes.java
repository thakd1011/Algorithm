import java.util.Scanner;
public class wordOverTwoTimes {
    static long MOD = 99999999;
    static int HASH_SIZE = 10000039;

    static String S;
    static int L;
    static long[] hashVal;
    static long[] pows;
    static long[] hashTb;
    static long[] strIdx;

    static long localMod(long n) {
        if(n >= 0) {
            return n % MOD;
        }
        else {
            return ((-n / MOD + 1) * MOD + n) % MOD;
        }
    }

    static boolean findHash(long hash, int n, int idx) {

        int hashKey = (int)(hash % HASH_SIZE);
        while(true) {
            if (hashTb[hashKey] == 0) {
                hashTb[hashKey] = hash;
                strIdx[hashKey] = idx;
                return false;
            }
            else if (hashTb[hashKey] == hash) {
                boolean sameWord = true;
                for(int i = 0; i < n; i++) {
                    if(S.charAt(idx + i - 1) != S.charAt((int)strIdx[hashKey] + i - 1)) {
                        sameWord = false;
                        break;
                    }
                }
                if(sameWord) {
                    return true;
                }
                else {
                    hashKey = (hashKey + 1) % HASH_SIZE;
                }
            }
            else {
                hashKey = (hashKey + 1) % HASH_SIZE;
            }
        }
    }

    static boolean isExist(int n) {
//        hashTb = new long[HASH_SIZE][2];
        for(int i = 1; i <= L - n + 1; i++) {
            // n = 3 -> 1, 2, 3, 4, 5, 6, 7, 8, 9 -> 11 - 3 =
            long tempHash = localMod(hashVal[i + n - 1] - localMod(pows[n] * hashVal[i - 1]));
            if(findHash(tempHash, n, i)) {
                return true;
            }
            else {
                continue;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int tc = 1; tc <= T; tc++) {
            int ans = 0;
            L = sc.nextInt();
            sc.nextLine();

            S = sc.nextLine();
            int start = 1, end = L, k;
            hashVal = new long[L + 1];
            pows = new long[L + 1];
            hashTb = new long[HASH_SIZE];
            strIdx = new long[HASH_SIZE];

            long pow = 1;
            hashVal[0] = 0;
            for(int i = 1; i <= L; i++) {
                hashVal[i] = localMod(localMod(hashVal[i - 1] * 31) + (int)S.charAt(i - 1));
                pow = localMod(31 * pow);
                pows[i] = localMod(pow);
            }

            // find k using binary search;
            while(start < end) {
                k = (start + end) / 2;
                // Do check if it has the same string over two times.
                if(isExist(k)) {
                    ans = k;
                    start = k + 1;
                }
                else {
                    end = k;
                }
            }
            System.out.println("#" + tc + " " + (ans));
            hashVal = null;
            pows = null;
            hashTb = null;
            strIdx = null;
        }
    }

}