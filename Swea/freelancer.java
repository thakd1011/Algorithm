import java.util.Scanner;
public class freelancer {
    static int N, M;
    static class task {
        int start, end, cost;
        task(int s, int e, int c) {
            start = s;
            end = e;
            cost = c;
        }
    }
    static task[] taskList;
    static long ans = 0;
    static void findMaxPrice(int tempTime, long tempCost) {
        if(ans < tempCost) ans = tempCost;

        for(int i = 0; i < N; i++) {
            if(taskList[i].start > tempTime) {
                findMaxPrice(taskList[i].end, tempCost + taskList[i].cost);
            }
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            M = sc.nextInt();
            taskList = new task[N];
            int s, e, c;
            for(int i = 0; i < N; i++) {
                s = sc.nextInt();
                e = sc.nextInt();
                c = sc.nextInt();
                taskList[i] = new task(s, e, c);
            }

            findMaxPrice(0, 0);
            System.out.println("#" + tc + " " + ans);
        }
    }
}

