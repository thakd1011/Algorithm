import java.util.Scanner;
import java.util.Arrays;
public class temp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for(int i = 0; i < tc; i++) {
            int N = sc.nextInt();
            int[] Lset = new int[N + 2];
            char[] alphabet = new char[26];
            String ans = "A";
            for(int j = 1; j < (N + 1); j++) {
                Lset[j] = sc.nextInt();
            }
            for(int j = 1; j < (N + 1); j++) {
                if(j % 2 == 0) {
                    String temp = "";
                    for(int k = 0; k < Lset[j]; k++) {
                        temp = (char)('A' + k) + temp;
                    }
                    ans += temp;
                }
                else {
                    String temp = "";
                    char start = (char)(ans.charAt(ans.length() - 1));
                    char end = '\0';
                    if(j + 1 < (N + 1)) {
                        end = (char)('A' + Lset[j + 1]);
                    }
                    if(end != '\0') {
                        // true일때까지 문자열 만들기
                        // n자리수 문자열 만들어야한다.
                        int letterCnt = (char)(start + Lset[j] - 1) - end;
                        if(letterCnt >= 0) {
                            for(int k = 0; k < Lset[j]; k++) {
                                temp += (char)(start + k + 1);
                            }
                        }
                        else {
                            for(int k = 0; k < Lset[j] - 1; k++) {
                                temp += (char)(start + k + 1);
                            }
                            temp += end;
                        }
                    }
                    else {
                        for(int k = 0; k < Lset[j]; k++) {
                            temp += (char)(start + k + 1);
                        }
                    }

                    ans += temp;
                    // temp string의 마지막 값 - end >= 0이기만 하면 된다.
                }
            }
            System.out.println("Case #" + (i + 1) + ": " + ans);
        }
    }
}
