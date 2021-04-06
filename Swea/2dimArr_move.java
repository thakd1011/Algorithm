public class UserSolution {
    int[][] localBoard = new int[10][10];
    int[][] localObject = new int[4][4];
    int objSize;
    int boardSize;
    int startR;
    int startC;
    int objSizeR;
    int objSizeC;
    // dir 0=UP, 1=DOWN, 2=RIGHT, 3=LEFT
    int[][] dirSet = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    void rotateOnce(int M, int[][] object) {
        int[][] ret = new int[M][M];
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < M; j++) {
                ret[j][M - 1 - i] = object[i][j];
            }
        }
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < M; j++) {
                object[i][j] = ret[i][j];
            }
        }
    }
    void rotateTwice(int M, int[][] object) {
        int[][] ret = new int[M][M];
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < M; j++) {
                ret[M - 1 - i][M - 1 - j] = object[i][j];
            }
        }
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < M; j++) {
                object[i][j] = ret[i][j];
            }
        }
    }
    void rotateThreeTimes(int M, int[][] object) {
        int[][] ret = new int[M][M];
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < M; j++) {
                ret[M - 1 - j][i] = object[i][j];
            }
        }
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < M; j++) {
                object[i][j] = ret[i][j];
            }
        }
    }

    public void rotate_cw(int M, int[][] object, int count) {
        switch(count % 4) {
            case 0:
                break;
            case 1:
                rotateOnce(M, object);
                break;
            case 2:
                rotateTwice(M, object);
                break;
            case 3:
                rotateThreeTimes(M, object);
                break;
        }
    }

    public void rotate_ccw(int M, int[][] object, int count) {
        switch(count % 4) {
            case 0:
                break;
            case 1:
                rotateThreeTimes(M, object);
                break;
            case 2:
                rotateTwice(M, object);
                break;
            case 3:
                rotateOnce(M, object);
                break;
        }
    }
    // up side down = 0, left and right = 1
    void flip(int M, int[][] object, int vh)
    {
        // vh == 0
        int temp = 0;
        if(vh == 0) {
            for(int i = 0; i < M / 2; i++) {
                for(int j = 0; j < M; j++) {
                    temp = object[i][j];
                    object[i][j] = object[M - i - 1][j];
                    object[M - i -1][j] = temp;
                }
            }
        }
        else if(vh == 1) {
            for(int i = 0; i < M; i++) {
                for(int j = 0; j < M / 2; j++) {
                    temp = object[i][j];
                    object[i][j] = object[i][M - j - 1];
                    object[i][M - j - 1] = temp;
                }
            }
        }
    }

    public void initObject(int size, int[][] object) {
        objSize = size;
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                localObject[i][j] = object[i][j];
            }
        }
    }

    // board size and shape
    void initBoard(int size, int[][] board)
    {
        boardSize = size;
        startR = 0;
        startC = 0;
        objSizeR = objSize;
        objSizeC = objSize;

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                localBoard[i][j] = board[i][j];
            }
        }
        for(int i = 0; i < objSize; i++) {
            for(int j = 0; j < objSize; j++) {
                localBoard[i][j] = localObject[i][j];
            }
        }
    }

    void printMap() {
        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                System.out.print(localBoard[i][j] + " ");
            }
            System.out.println();
        }
    }
    // dir 0=UP, 1=DOWN, 2=RIGHT, 3=LEFT
    public void move(int dir, int count) {
        for(int i = 0; i < count; i++) {
            if(objSizeR == 0 || objSizeC == 0) {
                return;
            }

            int tempR = startR;
            int tempC = startC;
            int tempSizeR = objSizeR;
            int tempSizeC = objSizeC;

            if(startR + dirSet[dir][0] < 0) {
                for(int j = 0; j < startC + objSizeC; j++) {
                    if(localBoard[0][j] != 0) {
                        return;
                    }
                }
                tempSizeR--;
            }
            else if(startC + dirSet[dir][1] < 0) {
                for(int j = 0; j < startR + objSizeR; j++) {
                    if(localBoard[j][0] != 0) {
                        return;
                    }
                }
                tempSizeC--;
            }
            else if(startR + objSizeR - 1 + dirSet[dir][0] >= boardSize ) {
                for(int j = startC; j < startC + objSizeC; j++) {
                    if(localBoard[startR + objSizeR - 1][j] != 0) {
                        return;
                    }
                }
                tempR++;
                tempSizeR--;
            }
            else if(startC + objSizeC - 1 + dirSet[dir][1] >= boardSize) {
                for(int j = startR; j < startR + objSizeR; j++) {
                    if(localBoard[j][startC + objSizeC - 1] != 0) {
//						System.out.println("not zero");
                        return;
                    }
                }
                tempC++;
                tempSizeC--;
            }
            else {
                tempR = tempR + dirSet[dir][0];
                tempC = tempC + dirSet[dir][1];
            }

            int[][] copiedObject = new int[objSizeR][objSizeC];
            int nextR, nextC;

            for(int j = startR; j < startR + objSizeR; j++) {
                for(int k = startC; k < startC + objSizeC; k++) {
                    if(localBoard[j][k] == 2) {
                        copiedObject[j - startR][k - startC] = 0;
                    }
                    else {
                        copiedObject[j - startR][k - startC] = localBoard[j][k];
                    }
                    nextR = j + dirSet[dir][0];
                    nextC = k + dirSet[dir][1];
                    if(nextR < 0 || nextC < 0 || nextR >= boardSize || nextC >= boardSize) {
                        continue;
                    }
                    if(localBoard[nextR][nextC] == 2 && localBoard[j][k] == 1) {
                        return;
                    }
                }
            }

            for(int j = startR; j < startR + objSizeR; j++) {
                for(int k = startC; k < startC + objSizeC; k++) {
                    if(localBoard[j][k] != 2) {
                        localBoard[j][k] = 0;
                    }
                }
            }
            for(int j = startR; j < startR + objSizeR; j++) {
                for(int k = startC; k < startC + objSizeC; k++) {
                    nextR = j + dirSet[dir][0];
                    nextC = k + dirSet[dir][1];

                    if(nextR < 0 || nextC < 0 || nextR >= boardSize || nextC >= boardSize) {
                        continue;
                    }
                    else if(localBoard[nextR][nextC] == 2) {
                        continue;
                    }
                    else {
                        localBoard[nextR][nextC] = copiedObject[j - startR][k - startC];
                    }
                }
            }
            startR = tempR;
            startC = tempC;
            objSizeR = tempSizeR;
            objSizeC = tempSizeC;
        }
    }

    public void getBoard(int[][] board) {
        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                board[i][j] = localBoard[i][j];
            }
        }
    }
}
