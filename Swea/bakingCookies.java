class UserSolution
{
    static final int MAX_SIZE = 20001;
    int tempDate = 0;
    int cutterCnt = 0;
    int orderCnt = 0;
    static order heap[];
    static int heapSize;
    cutter[] cutterList;
    linkedList[] orderListByShape;

    public void init()
    {
        tempDate = 0;
        cutterCnt = 0;
        orderCnt = 0;
        heapSize = 0;
        cutterList = new cutter[101];
        orderListByShape = new linkedList[1001];
        heap = new order[MAX_SIZE];
        for(int i = 0; i < 1001; i++) {
            orderListByShape[i] = new linkedList();
        }
    }

    public void addCookieCutter(int mID, int N, int mShapeList[])
    {
        cutterCnt++;
        cutterList[mID] = new cutter(mID, N, mShapeList);
    }

    public void orderCookie(int mShape, int daysLeft)
    {
        order newOrder = new order(mShape, daysLeft + tempDate, orderCnt);
        orderListByShape[mShape].addOrder(daysLeft + tempDate, orderCnt);
        orderCnt++;
        heapPush(newOrder);
    }

    public int checkRemain(int mShape)
    {
        return orderListByShape[mShape].orderCnt;
    }
    public void newDay()
    {
        tempDate++;
        boolean isExist = true;
        while(isExist) {
            // 1. get mindate order which is able to deliver at temp date.
            order firstOrder = getMindateOrder();
            // 2. check if it's possible to deliver or not + check if it is in orderListByShape's first node.
            if(firstOrder.deliveredDate > tempDate) {
                isExist = false;
                break;
            }
            // 3. pq.pop() and delete that order in orderListByShape
            if(firstOrder.deliveredDate == tempDate) {
                firstOrder = heapPop();

                if(orderListByShape[firstOrder.mShape].orderCnt == 0) {
                    continue;
                }
                else if(orderListByShape[firstOrder.mShape].head.next.deliveredDate != tempDate) {
                    continue;
                }
                else if(orderListByShape[firstOrder.mShape].head.next.deliveredDate == tempDate && orderListByShape[firstOrder.mShape].head.next.orderID != firstOrder.orderID) {
                    continue;
                }
                else {
                    // 4. choose the cutter which is able to bake the most amount of cookies and has the minimum mID
                    // it must include firstOrder.mShape in cutterShapeList.
                    int bakingCookieCnt = 0;
                    int cutterId = 0;

                    for (int i = 1; i <= cutterCnt; i++) {
                        // how many cookies tempCutter can make.
                        // 5. get all shapes which can make cookies in particular cutter.
                        int tempCnt = 0;
                        boolean includeFirstShape = false;
                        cutter tempCutter = cutterList[i];
                        for (int j = 0; j < tempCutter.shapeCnt; j++) {
                            if (tempCutter.mShapeList[j] == firstOrder.mShape) {
                                includeFirstShape = true;
                            }
                            if (orderListByShape[tempCutter.mShapeList[j]].orderCnt > 0) {
                                tempCnt++;
                            }
                        }
                        if (includeFirstShape && bakingCookieCnt < tempCnt) {
                            bakingCookieCnt = tempCnt;
                            cutterId = i;
                        }
                    }

                    // 6. delete order in orderListByShape which is on the first node position.
                    // and then call Solution.makeCookies(mID);
                    for (int i = 0; i < cutterList[cutterId].shapeCnt; i++) {
                        int shape = cutterList[cutterId].mShapeList[i];
                        if(orderListByShape[shape] != null) {
                            orderListByShape[shape].deleteOrder();
                        }
                    }
                    Solution.makeCookies(cutterId);
                }
            }
            // repete all upper steps again til' first order in queue is not the temp date.
        }
    }

    void mIntcpy(int[] src, int[] dest, int size) {
        for(int i = 0; i < size; i++) {
            dest[i] = src[i];
        }
    }

    void heapInit()
    {
        heapSize = 0;
    }

    void heapPush(order value)
    {
        if (heapSize + 1 > MAX_SIZE)
        {
            return;
        }

        heap[heapSize] = value;

        int current = heapSize;
        while (current > 0)
        {
            if(heap[current].deliveredDate < heap[(current - 1) / 2].deliveredDate) {
                order temp = heap[(current - 1) / 2];
                heap[(current - 1) / 2] = heap[current];
                heap[current] = temp;
                current = (current - 1) / 2;
            }
            else if(heap[current].deliveredDate == heap[(current - 1) / 2].deliveredDate) {
                if(heap[current].orderID < heap[(current - 1) / 2].orderID) {
                    order temp = heap[(current - 1) / 2];
                    heap[(current - 1) / 2] = heap[current];
                    heap[current] = temp;
                    current = (current - 1) / 2;
                }
                else {
                    break;
                }
            }
            else {
                break;
            }
        }

        heapSize = heapSize + 1;
    }

    order heapPop()
    {
        if (heapSize <= 0)
        {
            return null;
        }

        order value = heap[0];
        heapSize = heapSize - 1;

        heap[0] = heap[heapSize];

        int current = 0;
        while (current < heapSize && current * 2 + 1 < heapSize)
        {
            int child;
            if (current * 2 + 2 >= heapSize)
            {
                child = current * 2 + 1;
            }
            else
            {
                if(heap[current * 2 + 1].deliveredDate < heap[current * 2 + 2].deliveredDate) {
                    child = current * 2 + 1;
                }
                else if(heap[current * 2 + 1].deliveredDate == heap[current * 2 + 2].deliveredDate) {
                    if(heap[current * 2 + 1].orderID < heap[current * 2 + 2].orderID) {
                        child = current * 2 + 1;
                    }
                    else {
                        child = current * 2 + 2;
                    }
                }
                else {
                    child = current * 2 + 2;
                }
            }

            if (heap[current].deliveredDate < heap[child].deliveredDate)
            {
                break;
            }
            else if(heap[current].deliveredDate == heap[child].deliveredDate) {
                if(heap[current].orderID < heap[child].orderID) {
                    break;
                }
            }
            order temp = heap[current];
            heap[current] = heap[child];
            heap[child] = temp;

            current = child;
        }
        return value;
    }

    order getMindateOrder() {
        return heap[0];
    }

    class cutter {
        int mID;
        int shapeCnt;
        int[] mShapeList;

        cutter(int mID, int N, int[] mShapeList) {
            this.mID = mID;
            shapeCnt = N;
            this.mShapeList = new int[N];
            mIntcpy(mShapeList, this.mShapeList, N);
        }
    }

    class order {
        int orderID;
        int deliveredDate;
        int mShape;
        order(int shape, int date, int orderID) {
            this.orderID = orderID;
            deliveredDate = date;
            mShape = shape;
        }
    }

    class node {
        int deliveredDate;
        int orderID;
        node next;
        node(int deliveredDate, int orderID) {
            this.deliveredDate = deliveredDate;
            this.orderID = orderID;
            next = null;
        }
    }

    class linkedList {
        node head;
        int orderCnt;
        linkedList() {
            head = new node(-1, -1);
            orderCnt = 0;
        }

        // linkedList add new order
        // mShape -> orderDate
        void addOrder(int orderDate, int orderID) {
            if(orderCnt == 0) {
                head.next = new node(orderDate, orderID);
            }
            else {
                node cur = this.head;
                boolean inserted = false;
                while (cur.next != null) {
                    if (cur.next.deliveredDate > orderDate) {
                        node newOrder = new node(orderDate, orderID);
                        newOrder.next = cur.next;
                        cur.next = newOrder;
                        cur = null;
                        inserted = true;
                        break;
                    }
                    else if(cur.next.deliveredDate == orderDate) {
                        cur = cur.next;
                    }
                    else {
                        cur = cur.next;
                    }
                }
                if(!inserted) {
                    cur.next = new node(orderDate, orderID);
                }
                cur = null;
            }
            orderCnt++;
        }

        // delete order by ordered date
        void deleteOrder() {
            if(orderCnt > 0) {
                orderCnt--;
                node removedNode = head.next;
                if(removedNode.next == null) {
                    head.next = null;
                    removedNode = null;
                }
                else {
                    head.next = removedNode.next;
                    removedNode = null;
                }
            }
        }
    }
}
