class UserSolution {
    private final static int MAXR    = 99;
    private final static int MAXC  = 26;

    private final static int NUM = 1;
    private final static int ADD = 2;
    private final static int SUB = 3;
    private final static int MUL = 4;
    private final static int DIV = 5;
    private final static int MAX = 6;
    private final static int MIN = 7;
    private final static int SUM = 8;

    cell[][] cellTable;
    int tableSizeR, tableSizeC;

    class cell {
        int func;
        int value;
        int R1, C1;
        int R2, C2;

        void setup(char input[]) {
            switch(input[0]) {
                case 'A' :
                    func = ADD;
                    break;
                case 'S' :
                    if(input[2] == 'B') {
                        func = SUB;
                        break;
                    }
                    if(input[2] == 'M') {
                        func = SUM;
                        break;
                    }
                    break;
                case 'M' :
                    if(input[2] == 'L') {
                        func = MUL;
                        break;
                    }
                    if(input[2] == 'X') {
                        func = MAX;
                        break;
                    }
                    if(input[2] == 'N'){
                        func = MIN;
                        break;
                    }
                    break;
                case 'D' :
                    func = DIV;
                    break;
                default :
                    func = NUM;
                    value = this.charToInt(input);
                    break;
            }

            if(func != NUM) {
                int rowNum = 0;
                int idx;
                C1 = input[4] - 'A' + 1;
                for(idx = 5; input[idx] != ','; idx++) {
                    rowNum = rowNum * 10 + (input[idx] - '0');
                }
                R1 = rowNum;
                rowNum = 0;
                C2 = input[++idx] - 'A' + 1;
                idx++;
                for(; input[idx] != ')'; idx++) {
                    rowNum = rowNum * 10 + (input[idx] - '0');
                }
                R2 = rowNum;
                rowNum = 0;
            }
        }

        int charToInt(char input[]) {
            int sign = 1;
            int num = 0;
            for(int i = 0; input[i] != '\0'; i++) {
                if(i == 0 && input[0] == '-') {
                    sign = -1;
                    continue;
                }
                num = num * 10 + (input[i] - '0');
            }
            return sign * num;
        }
    }
    int getValue(cell tempCell) {
        int A, B;
        if(tempCell == null) {
            return 0;
        }
        if(tempCell.func == NUM) {
            return tempCell.value;
        }

        switch(tempCell.func) {
            case ADD :
                return getValue(cellTable[tempCell.R1][tempCell.C1]) + getValue(cellTable[tempCell.R2][tempCell.C2]);
            case SUB :
                return getValue(cellTable[tempCell.R1][tempCell.C1]) - getValue(cellTable[tempCell.R2][tempCell.C2]);
            case MUL :
                return getValue(cellTable[tempCell.R1][tempCell.C1]) * getValue(cellTable[tempCell.R2][tempCell.C2]);
            case DIV :
                return getValue(cellTable[tempCell.R1][tempCell.C1]) / getValue(cellTable[tempCell.R2][tempCell.C2]);
            case MAX :
                int localMax = -1000000001;
                int tempMax;
                if(tempCell.C1 == tempCell.C2) {
                    for(int i = tempCell.R1; i <= tempCell.R2; i++) {
                        tempMax = getValue(cellTable[i][tempCell.C1]);
                        if(tempMax > localMax) localMax = tempMax;
                    }
                }
                else {
                    for(int i = tempCell.C1; i <= tempCell.C2; i++) {
                        tempMax = getValue(cellTable[tempCell.R1][i]);
                        if(tempMax > localMax) localMax = tempMax;
                    }
                }
                return localMax;

            case MIN :
                int localMin = 1000000001;
                int tempMin;
                if(tempCell.C1 == tempCell.C2) {
                    for(int i = tempCell.R1; i <= tempCell.R2; i++) {

                        tempMin = getValue(cellTable[i][tempCell.C1]);
                        if(tempMin < localMin) localMin = tempMin;
                    }
                }
                else {
                    for(int i = tempCell.C1; i<= tempCell.C2; i++) {

                        tempMin = getValue(cellTable[tempCell.R1][i]);
                        if(tempMin < localMin) localMin = tempMin;
                    }
                }
                return localMin;

            case SUM :
                int sum = 0;
                if(tempCell.C1 == tempCell.C2) {
                    for(int i = tempCell.R1; i <= tempCell.R2; i++) {
                        sum += getValue(cellTable[i][tempCell.C1]);
                    }
                }
                else {
                    for(int i = tempCell.C1; i<= tempCell.C2; i++) {
                        sum += getValue(cellTable[tempCell.R1][i]);
                    }
                }
                return sum;
        }
        return -1;
    }

    void init(int C, int R) {
        cellTable = new cell[R + 1][C + 1];
        tableSizeR = R + 1;
        tableSizeC = C + 1;
    }

    void set(int col, int row, char input[]) {
        cellTable[row][col] = new cell();
        cellTable[row][col].setup(input);
    }

    void update(int value[][]) {
        for(int i = 0; i < tableSizeR - 1; i++) {
            for(int j = 0; j < tableSizeC - 1; j++) {
                if(cellTable[i + 1][j + 1] == null) continue;
                value[i][j] = getValue(cellTable[i + 1][j + 1]);
            }
        }
    }
}
