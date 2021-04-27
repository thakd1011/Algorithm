import java.util.Scanner;
public class wordOverTwoTimes {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int tc = 1; tc <= T; tc++) {
            int ans = 0;
            int L = sc.nextInt();
            sc.nextLine();

            String S = sc.nextLine();
            int[] pi;
            for(int i = 0; i < L; i++) {
                int begin = 1, matchedCnt = 0;
                pi = null;
                pi = new int[L];
                while(begin + matchedCnt + i < L) {
                    if(S.charAt(i + begin + matchedCnt) == S.charAt(i + matchedCnt)) {
                        matchedCnt++;
                        pi[i + begin + matchedCnt - 1] = matchedCnt;
                        if(ans < pi[i + begin + matchedCnt - 1]) {
                            ans = pi[i + begin + matchedCnt - 1];
                        }
                    }
                    else {
                        if(matchedCnt == 0) {
                            begin++;
                        }
                        else {
                            begin += matchedCnt - pi[i + matchedCnt - 1];
                            matchedCnt = pi[i + matchedCnt - 1];
                        }
                    }
                }
            }

            System.out.println("#" + tc + " " + ans);
        }
    }
}

