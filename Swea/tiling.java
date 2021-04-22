import java.util.Scanner;
public class Solution {
    static String reverseStr(String s) {
        String ret = "";
        for(int i = s.length() - 1; i >= 0; i--) {
            ret += s.charAt(i);
        }
        return ret;
    }

    static String strAdd(String s1, String s2) {
        int l1 = s1.length(), l2 = s2.length();
        String str = "";
        int carry = 0;
        if(l1 < l2) {
            for (int i = 0; i < l1; i++) {
                int sum = ((int) (s1.charAt(i) - '0') + (int) (s2.charAt(i) - '0') + carry);
                str += (char) (sum % 10 + '0');
                carry = sum / 10;
            }
            for (int i = l1; i < l2; i++) {
                int sum = ((int) (s2.charAt(i) - '0') + carry);
                str += (char) (sum % 10 + '0');
                carry = sum / 10;
            }
        }
        else {
            for (int i = 0; i < l2; i++) {
                int sum = ((int) (s1.charAt(i) - '0') + (int) (s2.charAt(i) - '0') + carry);
                str += (char) (sum % 10 + '0');
                carry = sum / 10;
            }
            for (int i = l2; i < l1; i++) {
                int sum = ((int) (s1.charAt(i) - '0') + carry);
                str += (char) (sum % 10 + '0');
                carry = sum / 10;
            }
        }
        if(carry > 0) {
            str += (char)(carry + '0');
        }
        String ret = "";
        for(int i = str.length() - 1; i >= 0; i--) {
            ret += str.charAt(i);
        }
        return ret;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            String[] dp = new String[N + 2];
            dp[0] = "0";
            dp[1] = "1";
            dp[2] = "3";
            String temp = "";
            for(int i = 3; i <= N; i++) {
                temp = strAdd(reverseStr(dp[i - 2]), reverseStr(dp[i - 2]));
                dp[i] = strAdd(reverseStr(temp), reverseStr(dp[i - 1]));
            }

            System.out.println("#" + tc + " " + dp[N]);
        }
    }
}
