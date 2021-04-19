import java.util.Scanner;

public class inversion {
    static int[] numbers;
    static int[] copiedNumbers;
    static long ans;

    static void merge(int left, int middle, int right) {
        int i = left;
        int j = middle + 1;
        int idx = left;

        while(i <= middle && j <= right) {
            if(numbers[i] < numbers[j]) {
                copiedNumbers[idx++] = numbers[i++];
            }
            else {
                // in case : left value is bigger than the right value, so it has been moved middle - i + 1 times.
                copiedNumbers[idx++] = numbers[j++];
                ans += middle - i + 1;
            }
        }
        while(i <= middle) {
            copiedNumbers[idx++] = numbers[i++];
        }
        while(j <= right) {
            copiedNumbers[idx++] = numbers[j++];
        }

        for(int k = left; k <= right; k++) {
            numbers[k] = copiedNumbers[k];
        }
    }

    static void MergeSort(int left, int right) {
        if(left >= right) return;
        int middle = (left + right) / 2;
        MergeSort(left, middle);
        MergeSort(middle + 1, right);
        merge(left, middle, right);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            numbers = new int[N + 1];
            copiedNumbers = new int[N + 1];

            for(int i = 0; i < N; i++) {
                numbers[i + 1] = sc.nextInt();
            }
            MergeSort(1, N);
            System.out.println("#" + tc + " " + ans);
            ans = 0;
        }
    }
}
