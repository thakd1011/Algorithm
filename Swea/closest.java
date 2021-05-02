import java.util.Scanner;
public class closest {
    static int N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        long x, y;
        for(int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            point[] pointList = new point[N];

            for(int i = 0; i < N; i++) {
                x = sc.nextLong();
                y = sc.nextLong();
                pointList[i] = new point(x, y);
            }
            mergeSort(pointList, 0, N - 1, 0); // sort by x.
            System.out.println("#" + tc + " " + closestPoint(pointList, 0, N - 1));
        }
    }

    static long dist(point a, point b) {
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }

    static long searchAll(point[] pointList, int start, int end) {
        long minVal = 10000000000L;
        for(int i = start; i < end; i++) {
            for(int j = i + 1; j <= end; j++) {
                long temp = dist(pointList[i], pointList[j]);
                if(temp < minVal) minVal = temp;
            }
        }
//        System.out.println(minVal);
        return minVal;
    }

    static long closestPoint(point[] pointList, int start, int end) {
        int pointCnt = end - start + 1;

        if(pointCnt <= 3) {
            return searchAll(pointList, start, end);
        }

        int mid = (start + end) / 2;

        // divide and conquer
        long leftMinDist = closestPoint(pointList, start, mid);
        long rightMinDist = closestPoint(pointList, mid + 1, end);
        long localMinDist;
        if(leftMinDist < rightMinDist) {
            localMinDist = leftMinDist;
        }
        else {
            localMinDist = rightMinDist;
        }
        // divide points which are within pivoted localMinDist.
        // ascending sort with y point.
        point[] candidates = new point[pointCnt];
        int size = 0;
        for(int i = start; i <= end; i++) {
            long tempVal = pointList[mid].x - pointList[i].x;
            if(tempVal * tempVal < localMinDist) {
                candidates[size++] = pointList[i];
            }
        }
        mergeSort(candidates, 0, size - 1, 1);
        // calculate distance with point having bigger y point than itself.
        for(int i = 0; i < size - 1; i++) {
            for(int j = i + 1; j < i + 7 && j < size; j++) {
                long tempVal = candidates[j].y - candidates[i].y;
                if(tempVal * tempVal < localMinDist) {
                    localMinDist = localMinDist < dist(candidates[i], candidates[j]) ? localMinDist : dist(candidates[i], candidates[j]);
                }
                else {
                    break;
                }
            }
        }
//        System.out.println(localMinDist);
        return localMinDist;
    }

    static void merge(point[] pointList, int left, int mid, int right, int mode) {
        point[] copiedPoint = new point[N];
        int i = left, j = mid + 1;
        int idx = left;

        if(mode == 0) {
            while(i <= mid && j <= right) {
                if (pointList[i].x < pointList[j].x) {
                    copiedPoint[idx++] = pointList[i++];
                } else if (pointList[i].x == pointList[j].x) {
                    if (pointList[i].y < pointList[j].y) {
                        copiedPoint[idx++] = pointList[i++];
                    } else {
                        copiedPoint[idx++] = pointList[j++];
                    }
                } else {
                    copiedPoint[idx++] = pointList[j++];
                }
            }
        }
        else if(mode == 1) {
            while(i <= mid && j <= right) {
                if (pointList[i].y < pointList[j].y) {
                    copiedPoint[idx++] = pointList[i++];
                } else if (pointList[i].y == pointList[j].y) {
                    if (pointList[i].x < pointList[j].x) {
                        copiedPoint[idx++] = pointList[i++];
                    } else {
                        copiedPoint[idx++] = pointList[j++];
                    }
                } else {
                    copiedPoint[idx++] = pointList[j++];
                }
            }
        }

        while(i <= mid) {
            copiedPoint[idx++] = pointList[i++];
        }
        while(j <= right) {
            copiedPoint[idx++] = pointList[j++];
        }

        for(int k = left; k <= right; k++) {
            pointList[k] = copiedPoint[k];
        }
    }

    static void mergeSort(point[] pointList, int left, int right, int mode) {
        // if mode == 0 -> x, mode == 1 -> y
        if(left >= right) return;
        int mid = (left + right) / 2;
        mergeSort(pointList, left, mid, mode);
        mergeSort(pointList, mid + 1, right, mode);
        merge(pointList, left, mid, right, mode);
    }
    static class point{
        long x;
        long y;
        point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
}
