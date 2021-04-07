int arr[1000][1000] = {0, };
int startCnt = 0;

void init(int N) {
    startCnt = 0;
    for(int i = 0; i < N; i++) {
        for(int j = 0; j < N; j++) {
            arr[i][j] = 0;
        }
    }
}

void start() {
    startCnt++;
}

bool check(int x, int y) {
    if(arr[x][y] == startCnt) {
        return true;
    }
    else {
        arr[x][y] = startCnt;
        return false;   
    }
}
