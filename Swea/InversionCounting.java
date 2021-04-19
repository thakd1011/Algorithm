import java.util.Scanner;

public class inversion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            long[][] arr = new long[N][3];
            boolean[] num = new boolean[N + 1];
            int ans = 0;

            for(int i = 0; i < N; i++) {
                arr[i][0] = sc.nextInt();
                num[(int)arr[i][0]] = true;
                if(i == 0) {
                    arr[i][1] = arr[i][0];
                    arr[i][2] = 0;
                }
                else {
                    if(arr[i][0] > arr[i - 1][1]) {
                        arr[i][1] = arr[i][0];
                        arr[i][2] = arr[i - 1][2];
                    }
                    else {
                        int cnt = 0;
                        arr[i][1] = arr[i - 1][1];
                        for(long k = arr[i][0] + 1; k <= arr[i - 1][1]; k++) {
                            if(num[(int)k]) cnt++;
                        }
                        arr[i][2] = arr[i - 1][2] + cnt;
                    }
                }
            }
            System.out.println("#" + tc + " " + arr[N - 1][2]);
            ans = 0;
        }
    }
}
