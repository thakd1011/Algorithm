import java.util.Scanner;

class equalism
{
    static int N;
    static int K;
    static int[] arr;
    static int[] copiedArr;

    static boolean isPossible(int value) {
        copiedArr = new int[N];
        int localK = K;
        for(int i = 0; i < N; i++) {
            copiedArr[i] = arr[i];
        }

        int diff;
	for(int i = 0; i < N - 1; i++) {
            diff = copiedArr[i + 1] - copiedArr[i];
            if(diff > value) {
                localK -= (diff - value);
                copiedArr[i + 1] -= (diff - value);
            }
            if(localK < 0) return false;
        }
        for(int i = N - 1; i > 0; i--) {
            diff = copiedArr[i - 1] - copiedArr[i];
            if(diff > value) {
                localK -= (diff - value);
                copiedArr[i - 1] -= (diff - value);
            }
            if(localK < 0) return false;
        }
        return true;
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
