class Solution {
    
    public boolean isPowerOfThree(int n) {
        int[] arr = new int[32];
        int num = 1;
        int idx = 1;
        
        arr[0] = 1;
        while(num > 0) {
            num *= 3;
            arr[idx++] = num;
        }
        
        boolean ret = false;
        int start = 0, end = idx - 1, mid = (start + end) / 2;
        while(start < end) {
            mid = (start + end) / 2;
            if(arr[mid] == n) {
                ret = true;
                break;
            }
            else {
                if(arr[mid] < n) {
                    start = mid + 1;
                }
                else {
                    end = mid;
                }
            }
        }
        return ret;
    }
}
