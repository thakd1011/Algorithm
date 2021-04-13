import java.util.Scanner;

class equalism
{
    static int N;
    static int K;
    static int[] arr;
    static int[] copiedArr;

    static int mAbs(int a, int b) {
        int ret = a - b;
        if(ret < 0) return -ret;
        else return ret;
    }

    static int biggerIdxInArr(int a, int b) {
        if(copiedArr[a] > copiedArr[b]) return a;
        else return b;
    }

    static boolean isPossible(int value) {
        copiedArr = new int[N];
        int localK = K;
        for(int i = 0; i < N; i++) {
            copiedArr[i] = arr[i];
        }

        int diff;
        for(int i = 0; i < N - 1; i++) {
            diff = mAbs(copiedArr[i], copiedArr[i + 1]);
            if(diff > value) {
                // 큰 거에서 차이만큼 뺀다.
                int idx = biggerIdxInArr(i, i + 1);
                copiedArr[idx] -= (diff - value);
                localK -= (diff - value);
            }
        }
	for(int i = 0; i < N - 1; i++) {
            diff = mAbs(copiedArr[i], copiedArr[i + 1]);
            if(diff > value) {
                // 큰 거에서 차이만큼 뺀다.
                int idx = biggerIdxInArr(i, i + 1);
                copiedArr[idx] -= (diff - value);
                localK -= (diff - value);
            }
        }

        if(localK < 0) {
            return false;
        }
        else {
            return true;
        }
        
    }
    
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            N = sc.nextInt();
            K = sc.nextInt();
            arr = new int[N];
            int maxValue = -1;
            for(int i = 0; i < N; i++) {
                arr[i] = sc.nextInt();
                if(i != 0) {
                    int temp = arr[i] - arr[i - 1];
                    if(temp < 0) temp *= -1;
                    if(temp > maxValue) maxValue = temp;
//                    System.out.println(temp);
                }
            }

            int start = 0;
            int end = maxValue;
            int middle;
            while(start < end) {
                middle = (start + end) / 2;
                if(isPossible(middle)) {
                    end = middle;
                }
                else {
                    start = middle + 1;
                }
            }
            System.out.println("#" + test_case + " " + start);
        }
    }
}

// 종류는 네 가지 a < b < c , a > b > c, a < b > c , a > b < c
//
