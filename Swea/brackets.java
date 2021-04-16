import java.util.Scanner;
class brackets
{
    static char[] brackets;
    static void stoc(String s, int len) {
        for(int i = 0; i < len; i++) {
            brackets[i] = s.charAt(i);
        }
    }

    // find the smallest negative brackets sum value's index
    static int findNegative(int len) {
        int sum = 0;
        int min = 1001;
        int idx = -1;
        for(int i = 0; i < len; i++) {
            if(brackets[i] == '(') sum += 1;
            else if(brackets[i] == ')') sum += -1;
            if(sum < 0) {
                if(min > sum) {
                    idx = i;
                    min = sum;
                }
            }
        }
        return idx;
    }

    // switch '(' to ')'
    static void reverse(int i, int j) {
        for(int k = i; k <= j; k++) {
            if(brackets[k] == '(') brackets[k] = ')';
            else if(brackets[k] == ')') brackets[k] = '(';
        }
    }
    // swap each index's bracket in range
    static void swap(int i, int j) {
        char temp = brackets[i];
        brackets[i] = brackets[j];
        brackets[j] = temp;
    }
    static void flip(int i, int j) {
        for(int k = i; k <= (i + j) / 2; k++) {
            swap(k, i + j - k);
        }
        reverse(i, j);
    }

    // last sum value
    static int lastSum(int len) {
        int sum = 0;
        for(int i = 0; i < len; i++) {
            if(brackets[i] == '(') sum++;
            else if(brackets[i] == ')') sum--;
        }
        return sum;
    }

    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int L = sc.nextInt();
            sc.nextLine();
            String s = sc.nextLine();

            // when it is not matched brackets pair count,
            // it would be impossible to correct right brackets pair.
            if(L % 2 == 1) {
                System.out.println("#" + test_case + " " + (-1));
            }
            else {
                int negIdx;
                int lastSumVal;
                int ans = 0;
                int[][] ansPair = new int[10][2];

                brackets = new char[L];
                stoc(s, L);

                while (true) {
                    if (ans >= 10) {
                        ans = -1;
                        break;
                    }

                    // we should change all ')' brackets position which is put ahead '('
                    negIdx = findNegative(L);
                    if (negIdx != -1) {
                        flip(0, negIdx);
                        ansPair[ans][0] = 0;
                        ansPair[ans][1] = negIdx;
                        ans++;
                    } else {
                        lastSumVal = lastSum(L);

                        // if all brackets is right place, the last sum value is 0.
                        if (lastSumVal == 0) {
                            break;
                        }

                        int tempIdx = 0;
                        int tempSum = 0;
                        for (int i = 0; i < L; i++) {
                            if (brackets[i] == '(') tempSum++;
                            else if (brackets[i] == ')') tempSum--;

                            // why divided two?
                            // Being the last value is even means '(' brackets more than ')'.
                            // At the point divided 2, it's the starting point where the pairs are not matched.
                            if (tempSum == lastSumVal / 2) {
                                tempIdx = i;
                            }
                        }
                        tempIdx++;
                        flip(tempIdx, L - 1);
                        ansPair[ans][0] = tempIdx;
                        ansPair[ans][1] = L - 1;
                        ans++;
                    }
                }
                System.out.println("#" + test_case + " " + ans);
                for (int i = 0; i < ans; i++) {
                    System.out.println(ansPair[i][0] + " " + ansPair[i][1]);
                }
            }
        }
    }
}
