import java.util.Scanner;
public class notBeHalfAges {
    static int[] flashDrive;
    static int[] subGroup;

    static int ans;
    static int N;
    static int K;
    static int T;
    static int endValue;

    static boolean isPossible(int value) {
        int cnt = 0;
        int groupIdx = 0;
        for(int i = 0; i < N; i++) {
            if(flashDrive[i] <= value) {
                cnt++;
            }
            else {
                cnt = 0;
            }
            if(subGroup[groupIdx] == cnt) {
                groupIdx++;
                if(groupIdx == K) {
                    break;
                }
                cnt = 0;
            }
        }
        if(groupIdx == K) {
            return true;
        }
        else {
            return false;
        }
    }
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int sum = 0;
            N = sc.nextInt();
            K = sc.nextInt();
            flashDrive = new int[N];
            subGroup = new int[K];
            ans = 200001;
            endValue = 0;

            for(int i = 0; i < N; i++) {
                flashDrive[i] = sc.nextInt();
                if(flashDrive[i] > endValue) endValue = flashDrive[i];
            }
            for(int i = 0; i < K; i++) {
                subGroup[i] = sc.nextInt();
            }

            // do parametric approaches using the binary search,
            int start = 1;
            int middle = 0;
            while(start < endValue) {
                middle = (start + endValue) / 2;
                if(isPossible(middle)) {
                    endValue = middle;
                }
                else {
                    start = middle + 1;
                }
            }
            System.out.println("#" + test_case + " " + start);
        }
    }
}
import java.util.Scanner;
public class notBeHalfAges {
    static int[] flashDrive;
    static int[] subGroup;

    static int ans;
    static int N;
    static int K;
    static int T;
    static int endValue;

    static boolean isPossible(int value) {
        int cnt = 0;
        int groupIdx = 0;
        for(int i = 0; i < N; i++) {
            if(flashDrive[i] <= value) {
                cnt++;
            }
            else {
                cnt = 0;
            }
            if(subGroup[groupIdx] == cnt) {
                groupIdx++;
                if(groupIdx == K) {
                    break;
                }
                cnt = 0;
            }
        }
        if(groupIdx == K) {
            return true;
        }
        else {
            return false;
        }
    }
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int sum = 0;
            N = sc.nextInt();
            K = sc.nextInt();
            flashDrive = new int[N];
            subGroup = new int[K];
            ans = 200001;
            endValue = 0;

            for(int i = 0; i < N; i++) {
                flashDrive[i] = sc.nextInt();
                if(flashDrive[i] > endValue) endValue = flashDrive[i];
            }
            for(int i = 0; i < K; i++) {
                subGroup[i] = sc.nextInt();
            }

            // do parametric approaches using the binary search,
            int start = 1;
            int middle = 0;
            while(start < endValue) {
                middle = (start + endValue) / 2;
                if(isPossible(middle)) {
                    endValue = middle;
                }
                else {
                    start = middle + 1;
                }
            }
            System.out.println("#" + test_case + " " + start);
        }
    }
}

