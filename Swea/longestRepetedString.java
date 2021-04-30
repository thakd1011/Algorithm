/*
PROBLEM : When S = A^n, it means S is made up of N times of subtring A, find the maximum n number.
If A's length is the shortest, and n is the biggest.
The logic to resolve it is that |A| = |S| / n and then S's front |A| counted character has to be the same with back A * (n-1) characters.

I used hash fingerprint method on this ps.
It used in this way that hashKey(DE) = hashKey(ABCDE) - hashKey(ABC) * (pow^n)
WARNING Point : it may cause hash collapse problem when it has a wide range of characters.
In addition, when we multiple constant(in this case, pow value), it may cause overflow.
To prevent it we have to use modulo operations when it has negative value so that not to compare later hash values.
*/

import java.util.Scanner;
public class expString {
    static int MAX_SIZE = 1000003;
    
    static long localMod(long n) {
        if(n>= 0) {
            return n % MAX_SIZE;
        }
        else {
            return ((-n/MAX_SIZE + 1)*MAX_SIZE + n) % MAX_SIZE;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine();
        for(int tc = 1; tc <= T; tc++) {
            long ans = 0;
            String s = sc.nextLine();
            int sLen = s.length();

            long[] hashVal = new long[sLen + 1];
            long[] pows = new long[sLen + 1];
            int n = 1;
            long pow = 1;

            hashVal[0] = 0;

            for(int i = 1; i <= sLen; i++) {
                hashVal[i] = localMod(hashVal[i - 1] * 2 + (long)s.charAt(i - 1));
                pow = localMod(2*pow);
                pows[i] = pow;
            }

            while(n <= sLen) {
                boolean isLongest = true;
                
                if(sLen % n == 0) {
                    long tempHash = hashVal[n];
                    for(int i = 2*n; i <= sLen; i += n) {
                        long nextHash = localMod(hashVal[i] - localMod(pows[n] * hashVal[i - n]));
                        if(nextHash != tempHash) {
                            isLongest = false;
                            break;
                        }
                    }
                    if(isLongest) {
                        ans = sLen / n;
                        break;
                    }
                }
                isLongest = true;
                n++;
            }
            System.out.println("#" + tc + " " + ans);
        }
    }
}
