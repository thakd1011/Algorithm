import java.util.Scanner;

public class AwardPrice {
    static int N;
    static int moveCnt;
    static int numLen;

    static int[] tempNum;
    static int[] candidateNum;

    static int[] maximumNum;
    static int maxNum;


    static int makeNumber(int[] numbers, int len) {
        int n = 0;
        for(int i = 0; i < len; i++) {
            n = n * 10 + numbers[i];
        }
        return n;
    }

    static void swap(int a, int b, int[] numbers) {
        int temp = numbers[a];
        numbers[a] = numbers[b];
        numbers[b] = temp;
    }

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();
        for(int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            moveCnt = sc.nextInt();

            // make num to int array.
            int tempN;
            int copyN = N;
            while(copyN != 0) {
                numLen += 1;
                copyN /= 10;
            }

            tempNum = new int[numLen];
            maximumNum = new int[numLen];
            candidateNum = new int[numLen];

            for(int i = numLen - 1; i >= 0; i--) {
                tempN = N % 10;
                tempNum[i] = tempN;
                maximumNum[i] = tempN;
                N /= 10;
            }
            if(numLen == 1) {
                System.out.println("#" + tc + " " + makeNumber(tempNum, numLen));
                return;
            }
            // make maximum num
            int idx = 0;
            int localMax = 0;
            int localMaxIdx = 0;
            while(idx < numLen) {
                localMax = 0;
                for(int i = idx; i < numLen; i++) {
                    if(localMax < maximumNum[i]) {
                        localMax = maximumNum[i];
                        localMaxIdx = i;
                    }
                }
                swap(idx, localMaxIdx, maximumNum);
                idx++;
            }
            maxNum = makeNumber(maximumNum, numLen);

            // search the maximum number which could be made with number list.
            int[] nums = new int[10];
            boolean moreThanTwo = false;
            for(int i = 0; i < numLen; i++) {
                nums[tempNum[i]]++;
                if(nums[tempNum[i]] > 1) {
                    moreThanTwo = true;
                    break;
                }
            }

            // make maximumNum use all swapNum;
            int ans = makeNumber(tempNum, numLen);
            idx = 0;
            localMax = 0;
            localMaxIdx = 0;
            for(int i = 0; i < numLen; i++) {
                if(moveCnt == 0) break;
                if(makeNumber(tempNum, numLen) == maxNum) {
                    ans = maxNum;
                    break;
                }

                localMax = 0;
                for(int j = i; j < numLen; j++) {
                    if(localMax <= tempNum[j]) {
                        localMax = tempNum[j];
                        localMaxIdx = j;
                    }
                }
                if(localMaxIdx == i) {
                    continue;
                }
                else {
                    swap(i, localMaxIdx, tempNum);
                    ans = makeNumber(tempNum, numLen);
                    moveCnt--;
                }
            }

            if(moveCnt > 0) {
                if(moveCnt % 2 == 0) {
                    ans = makeNumber(tempNum, numLen);
                }
                else {
                    if(moreThanTwo) {
                        ans = makeNumber(tempNum, numLen);
                    }
                    else {
                        // last two thing swap
                        swap(numLen - 1, numLen - 2, tempNum);
                        ans = makeNumber(tempNum, numLen);
                    }
                }
            }
            System.out.println("#" + tc + " " + ans);
            numLen = 0;
        }
    }
}

