class UserSolution
{
    private final static int MAXM	= 3;
    mailBox[] userList;
    linkedList waitingBox;
    int mID = 1;

    public void init(int N)
    {
        mID = 1;
        userList = new mailBox[N + 1];
        waitingBox = new linkedList();
        for(int i = 0; i < N + 1; i++) {
            userList[i] = new mailBox();
        }
    }

    void sendWaitingMail(int cTimestamp) {
        node cur = waitingBox.head;

        while(cur.next != null) {
            if(cur.next.scheduleTime <= cTimestamp) {
                // send mail
                node sentMail = new node(cur.next.scheduleTime, cur.next.fromID, cur.next.toID, cur.next.mID);
                sentMail.isRead = cur.next.isRead;
                userList[cur.next.toID].recvBox.addMail(sentMail);

                if(cur.next.next != null) {
                    cur.next = cur.next.next;
                }
                else {
                    cur.next = null;
                    break;
                }
            }
            else {
                cur = cur.next;
            }
        }
        // last node check;
        if(cur.scheduleTime != -1) {
            if(cur.scheduleTime <= cTimestamp) {
                node recvMail = new node(cur.scheduleTime, cur.fromID, cur.toID, cur.mID);
                recvMail.isRead = cur.isRead;
                userList[cur.toID].recvBox.addMail(recvMail);
                waitingBox.head.next = null;
            }
        }
        cur = null;
    }

    public void sendMessage(int cTimestamp, int uID1, int uID2, int mID, int scheduleTimestamp)
    {
        userList[uID1].sentBox.addMail(new node(cTimestamp, uID1, uID2, mID));
        waitingBox.addMail(new node(scheduleTimestamp, uID1, uID2, mID));

        sendWaitingMail(cTimestamp);
    }

    public int retrieveSentbox(int cTimestamp, int uID, int mIDList[], int uIDList[], int readList[])
    {
        sendWaitingMail(cTimestamp);
        node cur = userList[uID].sentBox.head;
        int cnt = 0;
        while(cur.next != null && cnt < 3) {
            node toCur = userList[cur.next.toID].recvBox.head;
            while(toCur.next != null) {
                if(toCur.next.mID == cur.next.mID) {
                    if(toCur.next.isRead == 1) {
                        readList[cnt] = 1;
                    }
                    else {
                        readList[cnt] = 0;
                    }
                    break;
                }
                toCur = toCur.next;
            }
            if(toCur.next == null) {
                readList[cnt] = 0;
            }
            toCur = null;

            mIDList[cnt] = cur.next.mID;
            uIDList[cnt] = cur.next.toID;
            cur = cur.next;
            cnt++;
        }
        cur = null;
        return cnt;
    }

    public int retrieveInbox(int cTimestamp, int uID, int mIDList[], int uIDList[], int readList[])
    {
        sendWaitingMail(cTimestamp);

        node cur = userList[uID].recvBox.head;
        int cnt = 0;
        while(cur.next != null && cnt < 3) {
            if(cur.next.isDeleted) {
                cur = cur.next;
                continue;
            }
            mIDList[cnt] = cur.next.mID;
            uIDList[cnt] = cur.next.fromID;
            readList[cnt] = cur.next.isRead;
            cnt++;
            cur = cur.next;
        }
        cur = null;
        return cnt;
    }

    public int readMessage(int cTimestamp, int uID, int mID)
    {
        sendWaitingMail(cTimestamp);
        node sentCur = userList[uID].sentBox.head;
        node recvCur = userList[uID].recvBox.head;
        boolean isExist = false;
        while(sentCur.next != null) {
            if(sentCur.next.mID == mID && !sentCur.next.isDeleted) {
                sentCur.next.isRead = 1;
                isExist = true;
                break;
            }
            sentCur = sentCur.next;
        }
        while(recvCur.next != null) {
            if(recvCur.next.mID == mID && !recvCur.next.isDeleted) {
                recvCur.next.isRead = 1;
                isExist = true;
                break;
            }
            recvCur = recvCur.next;
        }
        sentCur = null;
        recvCur = null;

        if(isExist) {
            return 1;
        }
        else {
            return 0;
        }
    }

    public int deleteMessage(int cTimestamp, int uID, int mID)
    {
        sendWaitingMail(cTimestamp);

        boolean deleteOnWaiting = false;
        int ret = 0;
        int deleteStatus = userList[uID].sentBox.deleteMail(cTimestamp, mID);

        if(deleteStatus == 2) {
            deleteOnWaiting = true;
            ret = 1;
        }
        else if(deleteStatus == 1) {
            ret = 1;
        }

        if(deleteOnWaiting) {
            waitingBox.deleteMail(cTimestamp, mID);
        }

        deleteStatus = userList[uID].recvBox.peudoDeleteMail(cTimestamp, mID);
        if(deleteStatus > 0) {
            ret = 1;
        }
        return ret;
    }

    class mailBox{
        linkedList sentBox;
        linkedList recvBox;

        mailBox() {
            sentBox = new linkedList();
            recvBox = new linkedList();
        }
    }
    class linkedList{
        node head;
        linkedList() {
            head = new node(-1, -1, -1, -1);
        }

        void addMail(node newNode) {
            node cur = head;

            if(cur.next == null) {
                cur.next = newNode;
                return;
            }
            boolean isAdded = false;
            node temp;

            while(cur.next != null) {
                temp = cur.next;
                if(temp.scheduleTime < newNode.scheduleTime || (temp.scheduleTime == newNode.scheduleTime && temp.mID < newNode.mID)) {
                    newNode.next = temp;
                    cur.next = newNode;
                    isAdded = true;
                    break;
                }
                cur = cur.next;
            }

            if(!isAdded) {
                cur.next = newNode;
            }
            cur = null;
        }

        int peudoDeleteMail(int timeStamp, int mID) {
            node cur = head;
            boolean isDeleted = false;
            boolean timeBigger = false;
            while(cur.next != null) {
                if(cur.next.isDeleted) {
                    cur = cur.next;
                    continue;
                }

                if(cur.next.mID == mID) {
                    isDeleted = true;
                    if(cur.next.scheduleTime < timeStamp) {
                        timeBigger = true;
                    }
                    cur.next.isDeleted = true;
                    break;
                }
                cur = cur.next;
            }

            if(isDeleted) {
                if(timeBigger) {
                    return 2;
                }
                else {
                    return 1;
                }
            }
            else {
                return 0;
            }
        }

        int deleteMail(int timeStamp, int mID) {
            node cur = head;
            boolean isDeleted = false;
            boolean timeBigger = false;
            while(cur.next != null) {
                if(cur.next.isDeleted) {
                    cur = cur.next;
                    continue;
                }

                if(cur.next.mID == mID) {
                    isDeleted = true;
                    if(cur.next.scheduleTime < timeStamp) {
                        timeBigger = true;
                    }

                    if(cur.next.next != null) {
                        cur.next = cur.next.next;
                    }
                    else {
                        cur.next = null;
                    }
                    break;
                }
                cur = cur.next;
            }

            if(isDeleted) {
                if(timeBigger) {
                    return 2;
                }
                else {
                    return 1;
                }
            }
            else {
                return 0;
            }
        }
    }

    class node {
        int scheduleTime;
        int fromID;
        int toID;
        int mID;
        int isRead;
        node next;
        boolean isDeleted;

        node(int scheduleTime, int fromID, int toID, int mID) {
            this.scheduleTime = scheduleTime;
            this.fromID = fromID;
            this.toID = toID;
            this.mID = mID;
            this.isRead = 0;
            next = null;
            isDeleted = false;
        }
    }
}


