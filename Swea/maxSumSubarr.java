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
	    int[] sum = new int[N];
            int maxVal = -200000000;

            for(int i = 0; i < N; i++) {
                arr[i] = sc.nextInt();
		if(i == 0) {
                    sum[i] = arr[i];
                    maxVal = sum[i];
                }
                else {
                    sum[i] = sum[i - 1] + arr[i];
                    if(sum[i] < 0) {
                        sum[i] = 0;
                        if(arr[i] > maxVal) {
                            maxVal = arr[i];
                        }
                    }
                    else {
                        if(sum[i] > maxVal) {
                            maxVal = sum[i];
                        }
                    }
                }
            }

            System.out.println("#" + test_case + " " + maxVal);
        }
    }
}
