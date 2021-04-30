import java.util.Scanner;
public class wordOverTwoTimes {
    static long MOD = 1000000019;

    static long localMod(long n) {
        if(n >= 0) {
            return n % MOD;
        }
        else {
            return ((-n / MOD + 1) * MOD + n) % MOD;
        }
    }

    static void merge(long[] hashList, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;
        long[] copiedHashList = new long[200001];
        while(i <= mid && j <= right) {
            if(hashList[i] < hashList[j]) {
                copiedHashList[k++] = hashList[i++];
            }
            else {
                copiedHashList[k++] = hashList[j++];
            }
        }
        while(i <= mid) {
            copiedHashList[k++] = hashList[i++];
        }
        while(j <= right) {
            copiedHashList[k++] = hashList[j++];
        }
        for(int m = left; m <= right; m++) {
            hashList[m] = copiedHashList[m];
        }
    }

    static void mergeSort(long[] hashList, int left, int right) {
        if(left >= right) return;
        int mid = (left + right) / 2;
        mergeSort(hashList, left , mid);
        mergeSort(hashList,mid + 1, right);
        merge(hashList, left, mid, right);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int tc = 1; tc <= T; tc++) {
            int ans = 0;
            int L = sc.nextInt();
            sc.nextLine();

            String S = sc.nextLine();
            int start = 1, end = L, k;
            long[] hashVal = new long[L + 1];
            long[] pows = new long[L + 1];

            long pow = 1;
            hashVal[0] = 0;
            for(int i = 1; i <= L; i++) {
                hashVal[i] = localMod(localMod(hashVal[i - 1] * 2) + (int)S.charAt(i - 1));
                pow = localMod(2 * pow);
                pows[i] = localMod(pow);
            }

            // find k using binary search;
            while(start < end) {
                k = (start + end) / 2;
                boolean isExist = false;
                long tempHash = 0;
                long[] hashList = new long[200001];
                int idx = 0;

                for(int i = k; i <= L; i++) {
//                    System.out.println()
                    tempHash = localMod(hashVal[i] - localMod(pows[k] * hashVal[i - k]));
//                    System.out.println("temp hash = " + tempHash);
                    hashList[idx++] = tempHash;
                }
                // sort and check;
                mergeSort(hashList, 0, idx - 1);
                long prev = hashList[0], next = 0;
                for(int i = 1; i < idx; i++) {
                    next = hashList[i];
                    if(next == prev) {
                        isExist = true;
                        break;
                    }
                    else {
                        prev = next;
                    }
                }

//                System.out.println("is exist = " + isExist);
                if(isExist) {
                    ans = k;
                    start = k + 1;
                }
                else {
                    end = k;
                }
            }
            System.out.println("#" + tc + " " + ans);
        }
    }

}
