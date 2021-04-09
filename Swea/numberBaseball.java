import java.util.Scanner;
class UserSolution {
    public final static int N = 4;
    static boolean[] check = new boolean[10];
    static node head = new node();

    static class node {
        int[] numCombi;
        node next;
        node() {
            next = null;
            numCombi = new int[4];
        }
    }

    static void permutation(int idx, int[] nums) {

        if(idx == 4) {
            node numNode = new node();
            for(int i = 0; i < 4; i++) {
                numNode.numCombi[i] = nums[i];
            }
            node nextNode = head.next;
            head.next = numNode;
            numNode.next = nextNode;
            return;
        }

        if(idx < 4) {
            for(int i = 0; i < 10; i++) {
                if(!check[i]) {
                    check[i] = true;
                    nums[idx] = i;
                    permutation(idx + 1, nums);
                    check[i] = false;
                }
            }
        }
    }

    static Solution.Result localQuery(int[] guessNum, int[] compareNum) {
        int strike = 0;
        int ball = 0;
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(guessNum[i] == compareNum[j]) {
                    if(i == j) strike++;
                    else ball++;
                }
            }
        }
        Solution.Result ret = new Solution.Result();
        ret.strike = strike;
        ret.ball = ball;
        return ret;
    }

    static void deleteNode(int strike, int ball, int[] guess) {
        node cursor = head;
        while(cursor.next != null) {
            Solution.Result ret = localQuery(guess, cursor.next.numCombi);
            if(ret.strike != strike || ret.ball != ball) {
                node temp = cursor.next;
                cursor.next = temp.next;
            }
            else {
                cursor = cursor.next;
            }
        }
    }


    public void doUserImplementation(int guess[]) {
        int[] nums = new int[4];
        permutation(0, nums);
        int strike;
        int ball;

        while(true) {
            node tempNode = head.next;
            for(int i = 0; i < 4; i++) {
                guess[i] = tempNode.numCombi[i];
            }

            Solution.Result ret = Solution.query(guess);
            strike = ret.strike;
            ball = ret.ball;
            head = tempNode;

            if(strike == 4) break;
            else deleteNode(strike, ball, guess);
        }
    }
}
