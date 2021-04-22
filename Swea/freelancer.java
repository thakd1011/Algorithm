import java.util.Scanner;
public class Solution {
    static int N, M;
    static task[] taskList;
    static long ans = 0;

    static void merge(int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;
        task[] copiedTask = new task[N + 1];
        while(i <= mid && j <= right) {
            if(taskList[i].end < taskList[j].end) {
                copiedTask[k++] = taskList[i++];
            }
            else {
                copiedTask[k++] = taskList[j++];
            }
        }
        while(i <= mid) {
            copiedTask[k++] = taskList[i++];
        }
        while(j <= right) {
            copiedTask[k++] = taskList[j++];
        }
        for(int m = left; m <= right; m++) {
            taskList[m] = copiedTask[m];
        }
    }

    static void mergeSort(int left, int right) {
        if(left >= right) return;
        int mid = (left + right) / 2;
        mergeSort(left, mid);
        mergeSort(mid + 1, right);
        merge(left, mid, right);
    }
    static long maxCost(long a, long b) {
        if( a > b) return a;
        else return b;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            M = sc.nextInt();
            taskList = new task[N + 1];
            long[] dp = new long[N + 1];
            int s, e, c;
            for(int i = 1; i <= N; i++) {
                s = sc.nextInt();
                e = sc.nextInt();
                c = sc.nextInt();
                taskList[i] = new task(s, e, c);
            }
            mergeSort(1, N);
            
            for(int i = 1; i <= N; i++) {
                int prevIdx = 0;
                for(int j = i - 1; j > 0; j--) {
                    if(taskList[i].start > taskList[j].end) {
                        prevIdx = j;
                        break;
                    }
                }
                dp[i] = maxCost(dp[i - 1], (dp[prevIdx] + taskList[i].cost));
            }
            System.out.println("#" + tc + " " + dp[N]);
        }
    }


    static class task {
        int start, end, cost;
        task(int s, int e, int c) {
            start = s;
            end = e;
            cost = c;
        }
    }
}