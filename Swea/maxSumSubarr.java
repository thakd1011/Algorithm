import java.util.Scanner;
class maxSumSubarr
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int N = sc.nextInt();
            int[] arr = new int[N];
            int maxVal = -200000000;

            for(int i = 0; i < N; i++) {
                arr[i] = sc.nextInt();
            }

            int start, end;
            start = end = 0;
            int prev = 0;
            while(end < N) {
                int localSum = 0;
                for(int i = start; i <= end; i++) {
                    localSum += arr[i];
                }
                if(localSum < prev) {
                    start = end + 1;
                    end++;
                }
                else {
                    if(localSum > maxVal) {
                        maxVal = localSum;
                    }
                    end++;
                }
            }
            System.out.println("#" + test_case + " " + maxVal);
        }
    }
}
