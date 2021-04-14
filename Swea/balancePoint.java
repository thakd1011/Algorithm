import java.util.Scanner;
class balancePoint
{
    static double[] balancedPoint;
    static int[] xCoordination;
    static int[] mass;
    static int N;
    static double err;

    static int isPossible(int idx, double point) {
        double leftForce = 0;
        double rightForce = 0;
        double tempForce;
        double dist;
        for(int i = 0; i < N; i++) {
            dist = point - xCoordination[i];
            tempForce = (double)(mass[i] / dist*dist);
            if(i <= idx) {
                leftForce += tempForce;
            }
            else {
                rightForce += tempForce;
            }
        }
        
	double pivot = rightForce - leftForce;
        if(pivot < err && pivot > -err) return 0;
        else if(pivot < err) return -1;
        else return 1;
    }

    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            N = sc.nextInt();
            balancedPoint = new double[N - 1];
            xCoordination = new int[N];
            mass = new int[N];

            for(int i = 0; i < N; i++) {
                xCoordination[i] = sc.nextInt();
            }
            for(int i = 0; i < N; i++) {
                mass[i] = sc.nextInt();
            }

            double start, end;
            double middle;
            err = e1-13;
            for(int i = 0; i < N - 1; i++) {
                start = xCoordination[i];
                end = xCoordination[i + 1];
                while(end - start > err) {
                    middle = (double)(start + end) / 2;
                    int possibleVal = isPossible(i, middle);
                    if(possibleVal == 0) {
                        start = middle;
                        break;
                    }
                    else if(possibleVal == 1){
                        // move onto left side;
                        end = middle - err;
                    }
                    else {
                        start = middle + err;
                    }
                }
                balancedPoint[i] = start;
            }

            System.out.print("#" + test_case);
            for(int i = 0; i < N - 1; i++) {
                System.out.print(" " + String.format("%.10f", balancedPoint[i]));
            }
            System.out.println();
        }
    }
}
