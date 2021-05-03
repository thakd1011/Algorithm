class UserSolution {

    int MAX_SIZE = 30000;
    long[] groupHash;
    long[] customerHash;
    account[] accountAddr;
    linkedList[] groupTb;
    linkedList[] customerTb;

    long getHash(char[] word) {
        long hash = 5381;
        for(int i = 0; word[i] != '\0'; i++) {
            hash = ((hash * 31) + (int)word[i]);
        }
        return hash;
    }

    int findGroupHashKey(char[] word) {
        long hash = getHash(word);
        int hashKey = (int)(hash % MAX_SIZE);
        while(true) {
            if(groupHash[hashKey] == 0) {
                groupHash[hashKey] = hash;
                return hashKey;
            }
            else if(groupHash[hashKey] == hash) {
                return hashKey;
            }
            else {
                hashKey = (hashKey + 1) % MAX_SIZE;
            }
        }
    }

    int findCustomerHashKey(char[] word) {
        long hash = getHash(word);
        int hashKey = (int)(hash % MAX_SIZE);
        while(true) {
            if(customerHash[hashKey] == 0) {
                customerHash[hashKey] = hash;
                return hashKey;
            }
            else if(customerHash[hashKey] == hash) {
                return hashKey;
            }
            else {
                hashKey = (hashKey + 1) % MAX_SIZE;
            }
        }
    }

    void init(){
        groupHash = null;
        customerHash = null;
        accountAddr = null;
        groupTb = null;
        customerTb = null;
        groupHash = new long[30000];
        customerHash = new long[30000];
        accountAddr = new account[100001];
        groupTb = new linkedList[30000];
        customerTb = new linkedList[30000];

        for(int i = 0; i < 30000; i++) {
            groupTb[i] = new linkedList();
            customerTb[i] = new linkedList();
        }
    }

    void openAccount(int mTime, char mName[], char mGroup[], int mNumber, int mAsset){

        int hName = findCustomerHashKey(mName);
        int hGroup = findGroupHashKey(mGroup);
        account tempAccount = new account(mTime, mAsset, hGroup);

        // customer에 계좌번호 리스트 저장
        customerTb[hName].addNode(new node(mNumber));

        // group에 customer  hash값 저장
        groupTb[hGroup].addNode(new node(hName));

        // account 추가
        accountAddr[mNumber] = tempAccount;
    }

    int closeAccount(int mTime, int mNumber){
        int remainMoney = accountAddr[mNumber].mAsset;
        accountAddr[mNumber] = null;
        return remainMoney;
    }

    int sendByNumber(int mTime, int mNumber, int mReceiveNumber, int mValue){
        if(accountAddr[mReceiveNumber] == null || accountAddr[mNumber] == null) {
            return -1;
        }
        else if(accountAddr[mNumber].mAsset < mValue) {
            return -1;
        }
        else {
            accountAddr[mNumber].mAsset -= mValue;
            accountAddr[mReceiveNumber].mAsset += mValue;

            accountAddr[mNumber].mTime = mTime;
            accountAddr[mReceiveNumber].mTime = mTime;

            accountAddr[mNumber].status = 1;
            accountAddr[mReceiveNumber].status = 2;

            return accountAddr[mReceiveNumber].mAsset;
        }
    }

    int sendByName(int mTime, int mNumber, char mReceiveName[], int mValue){
        if(accountAddr[mNumber] == null) {
            return -1;
        }
        if(accountAddr[mNumber].mAsset < mValue) {
            return -1;
        }

        // 고객들 중에 해당 이름이 존재하지 않는 경우
        long h = getHash(mReceiveName);
        int tempHk = (int)(h % MAX_SIZE);
        boolean haveCustomer = true;
        while(true) {
            if(customerHash[tempHk] == 0) {
                haveCustomer = false;
                break;
            }
            else if(customerHash[tempHk] == h) {
                break;
            }
            else {
                tempHk = (tempHk + 1) % MAX_SIZE;
            }
        }
        if(!haveCustomer ) {
            return -1;
        }

        // find account having priority.
        int customerHk = tempHk;
        node cur = customerTb[customerHk].head;

        int receiveAccount = 0;
        int tempStatus = 0;
        int tempTime = 0;

        while(cur.next != null) {
            account tempAccount = accountAddr[(int)cur.next.value];
            if(tempAccount == null) {
                if(cur.next.next != null) {
                    cur.next = cur.next.next;
                    continue;
                }
                else {
                    cur.next = null;
                    continue;
                }
            }
            else {
                if(tempAccount.mTime > tempTime || (tempAccount.mTime == tempTime && tempAccount.status > tempStatus)) {
                    receiveAccount = (int)cur.next.value;
                    tempStatus = tempAccount.status;
                    tempTime = tempAccount.mTime;
                }
                cur = cur.next;
            }
        }
        // 송금
        accountAddr[receiveAccount].mAsset += mValue;
        accountAddr[mNumber].mAsset -= mValue;

        accountAddr[mNumber].mTime = mTime;
        accountAddr[receiveAccount].mTime = mTime;

        accountAddr[mNumber].status = 1;
        accountAddr[receiveAccount].status = 2;

        return accountAddr[receiveAccount].mAsset;
    }

    void sendBonus(int mTime, char mGroup[], int mValue){
        int groupHk = findGroupHashKey(mGroup);
        node customerHashList = groupTb[groupHk].head;

        while(customerHashList.next != null) {
            int customerHk = (int)customerHashList.next.value;
            node accountList = customerTb[customerHk].head;

            int receiveAccount = 0;
            int tempStatus = 0;
            int tempTime = 0;

            // account list 돌면서 최우선순위의 account를 찾아라.
            while(accountList.next != null) {
                account tempAccount = accountAddr[(int)accountList.next.value];
                if(tempAccount == null) {
                    if(accountList.next.next != null) {
                        accountList.next = accountList.next.next;
                        continue;
                    }
                    else {
                        accountList.next = null;
                        continue;
                    }
                }

                else if(tempAccount.groupHk != groupHk) {
                    accountList = accountList.next;
                    continue;
                }
                else {
                    if(tempAccount.mTime > tempTime || (tempAccount.mTime == tempTime && tempAccount.status < tempStatus)) {
                        receiveAccount = (int)accountList.next.value;
                        tempStatus = tempAccount.status;
                        tempTime = tempAccount.mTime;
                    }
                    accountList = accountList.next;
                }
            }

            if(receiveAccount != 0) {
                accountAddr[receiveAccount].mAsset += mValue;
                accountAddr[receiveAccount].mTime = mTime;
                accountAddr[receiveAccount].status = 2;
            }
            customerHashList = customerHashList.next;
        }
    }

    class account {
        int groupHk;
        int mAsset;
        int mTime;
        int status; // status = 0 : open, 1 : sent, 2 : receive;
        account(int mTime, int mAsset, int groupHk) {
            this.mAsset = mAsset;
            this.mTime = mTime;
            this.groupHk = groupHk;
            status = 0;
        }
    }

    class linkedList {
        node head;
        linkedList() {
            head = new node(-1);
        }

        void addNode(node newNode) {
            node cur = head;
            if(cur.next == null) {
                cur.next = newNode;
            }
            boolean isExist = false;
            while(cur.next != null) {
                if(cur.next.value == newNode.value) {
                    // 이미 있는 경우
                    isExist = true;
                    break;
                }
                cur = cur.next;
            }
            if(!isExist ) {
                cur.next = newNode;
            }
        }
    }

    class node {
        int value;
        node next;
        node(int value) {
            this.value = value;
            next = null;
        }
    }
}


