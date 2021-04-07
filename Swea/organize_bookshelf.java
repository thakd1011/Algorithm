class UserSolution
{
    private final static int MAX_N				= 5;
    private final static int MAX_NAME_LEN		= 7;
    private final static int MAX_TAG_LEN		= 4;

    Book[] nameTb;
    linkedList[] typeTb;

    double[] nameHashkeyMap;
    double[] typeHashkeyMap;

    int MAX_NAME_HASH_SIZE = 50000;
    int MAX_TYPE_HASH_SIZE = 500;

    double getHash(char[] word) {
        double hash = 5381;
        for(int i = 0; word[i] != '\0'; i++) {
            double c = (double)word[i];
            hash = hash * 100 + c;
        }
        return hash;
    }

    int findNameHashKey(char[] word) {
        double hash = getHash(word);
        int hashKey = (int)(hash % MAX_NAME_HASH_SIZE);
        while(true) {
            if(nameHashkeyMap[hashKey] == 0) {
                nameHashkeyMap[hashKey] = hash;
                return hashKey;
            }
            else if(nameHashkeyMap[hashKey] == hash) {
                return hashKey;
            }
            else {
                hashKey = (hashKey + 1) % MAX_NAME_HASH_SIZE;
            }
        }
    }

    int findTypeHashKey(char[] word) {
        double hash = getHash(word);
        int hashKey = (int)(hash % MAX_TYPE_HASH_SIZE);
        while(true) {
            if(typeHashkeyMap[hashKey] == 0) {
                typeHashkeyMap[hashKey] = hash;
                return hashKey;
            }
            else if(typeHashkeyMap[hashKey] == hash) {
                return hashKey;
            }
            else {
                hashKey = (hashKey + 1) % MAX_TYPE_HASH_SIZE;
            }
        }
    }

    public void init(int M)
    {
        nameTb = new Book[50000];
        typeTb = new linkedList[500];
        nameHashkeyMap = new double [50000];
        typeHashkeyMap = new double [500];
        for(int i = 0; i < 500; i++) {
            typeTb[i] = new linkedList();
        }
    }

    public void add(char mName[], int mTypeNum, char mTypes[][], int mSection)
    {
        int nameHashkey = findNameHashKey(mName);

        Book newBook = new Book(mSection, mTypeNum);
        for(int i = 0; i < mTypeNum; i++) {
            int typeHashkey = findTypeHashKey(mTypes[i]);

            node newNode = new node(nameHashkey);
            typeTb[typeHashkey].addNode(newNode);
            newBook.types[i] = typeHashkey;
        }
        nameTb[nameHashkey] = newBook;
    }

    public int moveType(char mType[], int mFrom, int mTo)
    {
        int typeHashkey = findTypeHashKey(mType);
        int nameHashkey;
        int movedCnt = 0;

        node cursor = typeTb[typeHashkey].head.next;
        while(cursor.nameHashKey != -1) {
            nameHashkey = cursor.nameHashKey;
            if(nameTb[nameHashkey] != null && nameTb[nameHashkey].section == mFrom) {

                nameTb[nameHashkey].section = mTo;
                movedCnt++;
            }
            cursor = cursor.next;
        }
        return movedCnt;
    }

    public void moveName(char mName[], int mSection)
    {
        int nameHashkey = findNameHashKey(mName);
        nameTb[nameHashkey].section = mSection;
    }

    public void deleteName(char mName[])
    {
        int nameHashkey = findNameHashKey(mName);
        int typeHashkey;
        int typeCnt = nameTb[nameHashkey].typeNum;
        for(int i = 0; i < typeCnt; i++) {
            typeHashkey = nameTb[nameHashkey].types[i];
            node cursor = typeTb[typeHashkey].head;
            while(cursor.next.nameHashKey != -1) {
                if(cursor.next.nameHashKey == nameHashkey) {
                    node nextNode = cursor.next;
                    cursor.next = nextNode.next;
                    break;
                }
                cursor = cursor.next;
            }
        }
        nameTb[nameHashkey] = null;
    }

    public int countBook(int mTypeNum, char mTypes[][], int mSection)
    {
        int bookCnt = 0;
        int typeHashkey;
        int nameHashkey;
        int[][] checked = new int[MAX_NAME_HASH_SIZE][1];

        for(int i = 0; i < mTypeNum; i++) {
            typeHashkey = findTypeHashKey(mTypes[i]);
            node tempCursor = typeTb[typeHashkey].head.next;
            while(tempCursor.nameHashKey != -1) {
                nameHashkey = tempCursor.nameHashKey;
                if(checked[nameHashkey][0] == 0 && nameTb[nameHashkey] != null && nameTb[nameHashkey].section == mSection) {
                    checked[nameHashkey][0] = 1;
                    bookCnt++;
                }
                tempCursor = tempCursor.next;
            }
        }
        return bookCnt;
    }

    class Book{
        int section;
        int typeNum;
        int[] types;

        Book(int section, int typeNum) {
            this.section = section;
            this.typeNum = typeNum;
            types = new int[typeNum];
        }
    }

    class node {
        int nameHashKey;
        node next;
        node(int nameHashKey) {
            this.nameHashKey = nameHashKey;
            this.next = null;
        }
    }

    class linkedList {
        node head;
        node tail;
        linkedList() {
            head = new node(-1);
            tail = new node(-1);
            head.next = tail;
        }
        void addNode(node newNode) {
            node nextNode = head.next;
            head.next = newNode;
            newNode.next = nextNode;
        }
    }
}
