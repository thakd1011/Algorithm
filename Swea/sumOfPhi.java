import java.util.Scanner;
class sumOfPhi
{
    static long[] pieValue = new long[1000001];

    static void makePrimeFactor() {
        long tempNum;
        long resultNum;
        for(int i = 1; i <= 1000000; i++) {
            tempNum = i;
            resultNum = i;

            for(int j = 2; j*j <= tempNum; j++) {
                if(tempNum % j == 0) {
                    resultNum = (resultNum / j) * (j - 1);
                }
                while(tempNum % j == 0) {
                    tempNum /= j;
                }
            }
            if(tempNum != 1) {
                resultNum = (resultNum / tempNum) * (tempNum - 1);
            }
            pieValue[i] = resultNum;
        }
    }

    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
        makePrimeFactor();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int a, b;
            long ans = 0;
            a = sc.nextInt();
            b = sc.nextInt();
            for(int i = a; i <= b; i++) {
                ans += pieValue[i];
            }
            System.out.println("#" + test_case + " " + ans);
        }
    }
}
