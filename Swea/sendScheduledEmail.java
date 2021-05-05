class UserSolution
{
    /*
    mail box specs : first input, first output. It has a characteristic that mail is been stacked as it comes.
    - We need to know that even if a mail which been deleted in a recvBox, the mail should be checked if it is read or not.
    Therefore, we write the part of deleting mail in the way checking is deleted or not, not to erase.
    It's the same mechanism with server save all the mails to check if it's opend or not.

    Also, we need to update sentBox and inBox in each user's mailBox when the time has come to update.
    We'll delete mail Id in user's sentbox and inbox, however, we shouldn't delete mail at "mail list" on server so that we're able to check is read by recipient and deleted or not.

    Additionally, we need the scheduled mail queue by scheduled time order so I'll use min heap as a priority queue.
    # Why use min heap? Cuase' each mail box's mail has to be saved by ascending recieved-time order, we have to send waiting mail as decending order to stack it.
    # warning : isRead variable has the perspective in recipient.
    * */
    final int MAX_SIZE = 100001;

    mailBox[] userList;
    mail[] mailList;

    node heap[];
    int heapSize = 0;

    void heapInit()
    {
        heapSize = 0;
    }

    void heapPush(node value)
    {
        if (heapSize + 1 > MAX_SIZE)
        {
            return;
        }

        heap[heapSize] = value;

        int current = heapSize;
        // min heap -> current가 부모보다 작으면 교환
        while (current > 0)
        {
            if(heap[current].scheduledTime < heap[(current - 1) / 2].scheduledTime
                || (heap[current].scheduledTime == heap[(current - 1) / 2].scheduledTime && heap[current].mID < heap[(current - 1) / 2].mID)) {

                node temp = heap[(current - 1) / 2];
                heap[(current - 1) / 2] = heap[current];
                heap[current] = temp;
                current = (current - 1) / 2;
            }
            else {
                break;
            }
        }
        heapSize = heapSize + 1;
    }

    node heapPop()
    {
        if (heapSize <= 0)
        {
            return null;
        }

        node value = heap[0];
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
                if(heap[current * 2 + 1].scheduledTime < heap[current * 2 + 2].scheduledTime) {
                    child = current * 2 + 1;
                }
                else if(heap[current * 2 + 1].scheduledTime == heap[current * 2 + 2].scheduledTime) {
                    if(heap[current * 2 + 1].mID < heap[current * 2 + 2].mID) {
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

            if (heap[current].scheduledTime < heap[child].scheduledTime)
            {
                break;
            }
            else if(heap[current].scheduledTime == heap[child].scheduledTime) {
                if(heap[current].mID < heap[child].mID) {
                    break;
                }
            }

            node temp = heap[current];
            heap[current] = heap[child];
            heap[child] = temp;

            current = child;
        }
        return value;
    }

    node getMin() {
        return heap[0];
    }

    public void init(int N)
    {
        userList = new mailBox[N + 1];
        mailList = new mail[100001];
        heap = new node[MAX_SIZE];
        heapSize = 0;
        for(int i = 0; i < N + 1; i++) {
            userList[i] = new mailBox();
        }
    }

    void sendWaitingMail(int cTimestamp) {
        if(heapSize == 0) {
            return;
        }
        while(heapSize > 0) {
            node tempMsg = getMin();
//            System.out.println("candidate = " + tempMsg.mID);
            if(tempMsg == null) {
                break;
            }
            if(tempMsg.scheduledTime > cTimestamp) {
                break;
            }

            // send mail
            mail tempMail = mailList[tempMsg.mID];
            if(!tempMail.check_sentBox) {
                heapPop();
                continue;
            }

            userList[tempMail.toID].recvBox.addMsg(new message(tempMsg.mID));
            mailList[tempMsg.mID].check_recvBox = true;
            heapPop();
        }

    }

    public void sendMessage(int cTimestamp, int uID1, int uID2, int mID, int scheduleTimestamp)
    {
        mail newMail = new mail(uID1, uID2, false, true, false);
        mailList[mID] = newMail;
        userList[uID1].sentBox.addMsg(new message(mID));
        heapPush(new node(scheduleTimestamp, mID));

        sendWaitingMail(cTimestamp);
    }

    public int retrieveSentbox(int cTimestamp, int uID, int mIDList[], int uIDList[], int readList[])
    {
        sendWaitingMail(cTimestamp);
        message cur = userList[uID].sentBox.head;
        int cnt = 0;

        while(cur.next != null && cnt < 3) {
            int tempMsgId = cur.next.mID;
            mail tempMail = mailList[tempMsgId];
            if(!tempMail.check_sentBox) {
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
                // when it's not deleted in sentMail, it could be read.
                mIDList[cnt] = tempMsgId;
                uIDList[cnt] = tempMail.toID;
                if(tempMail.isRead) {
                    readList[cnt] = 1;
                }
                else {
                    readList[cnt] = 0;
                }
                cnt++;
                cur = cur.next;
            }
        }
        return cnt;
    }

    public int retrieveInbox(int cTimestamp, int uID, int mIDList[], int uIDList[], int readList[])
    {
        sendWaitingMail(cTimestamp);
        message cur = userList[uID].recvBox.head;
        int cnt = 0;

        while(cur.next != null && cnt < 3) {
            int tempMsgId = cur.next.mID;
            mail tempMail = mailList[tempMsgId];
            if(!tempMail.check_recvBox) {
                // 보낸 메일함에서 삭제됐다면
                // recv 있는 메일 삭제
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
                // 받 메일함에서 삭제 안 된 경우 -> 읽을 수 있음
                mIDList[cnt] = tempMsgId;
                uIDList[cnt] = tempMail.fromID;
                if(tempMail.isRead) {
                    readList[cnt] = 1;
                }
                else {
                    readList[cnt] = 0;
                }
                cnt++;
                cur = cur.next;
            }
        }
        return cnt;
    }

    public int readMessage(int cTimestamp, int uID, int mID)
    {
        sendWaitingMail(cTimestamp);

        int ret = 0;
        message cur = userList[uID].sentBox.head;
        while(cur.next != null) {
            if(!mailList[cur.next.mID].check_sentBox) {
                if(cur.next.next != null) {
                    cur.next = cur.next.next;
                    continue;
                }
                else {
                    cur.next = null;
                    continue;
                }
            }

            if(cur.next.mID == mID) {
                ret = 1;
                break;
            }
            cur = cur.next;
        }
        cur = userList[uID].recvBox.head;
        while(cur.next != null) {
            if(!mailList[cur.next.mID].check_recvBox) {
                // This mail has already deleted in recvBox so we don't need to count and then delete it for real in recvBox.
                if(cur.next.next != null) {
                    cur.next = cur.next.next;
                    continue;
                }
                else {
                    cur.next = null;
                    continue;
                }
            }

            if(cur.next.mID == mID) {
                // it hasn't been deleted yet, and it currently has been in recv_box.
                mailList[cur.next.mID].isRead = true;
                ret = 1;
                break;
            }
            cur = cur.next;
        }
        return ret;
    }

    public int deleteMessage(int cTimestamp, int uID, int mID)
    {
        sendWaitingMail(cTimestamp);
        int ret = 0;
        if(mailList[mID].fromID == uID) {
            if(mailList[mID].check_sentBox) {
                mailList[mID].check_sentBox = false;
                ret = 1;
            }
        }
        if(mailList[mID].toID == uID) {
            if(mailList[mID].check_recvBox) {
                mailList[mID].check_recvBox = false;
                ret = 1;
            }
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
        message head;

        linkedList() {
            head = new message(-1);
        }

        void addMsg(message newMsg) {
            if(head.next == null) {
                head.next = newMsg;
                return;
            }
            else {
                newMsg.next = head.next;
                head.next = newMsg;
                return;
            }
        }
    }

    class mail {
        int fromID;
        int toID;
        boolean isRead;
        boolean check_sentBox;
        boolean check_recvBox;

        mail(int fromID, int toID, boolean isRead, boolean check_sentBox, boolean check_recvBox) {
            this.fromID = fromID;
            this.toID = toID;
            this.isRead = isRead;
            this.check_sentBox = check_sentBox;
            this.check_recvBox = check_recvBox;
        }
    }

    class node {
        int scheduledTime;
        int mID;

        node(int scheduledTime, int mID) {
            this.scheduledTime = scheduledTime;
            this.mID = mID;
        }
    }

    class message {
        int mID;
        message next;

        message(int mID) {
            this.mID = mID;
            next = null;
        }
    }
}

