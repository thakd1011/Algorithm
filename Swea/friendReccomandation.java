class UserSolution
{
	static final int MAXL		= 5;
	static final int MAXF		= 10;
	static final int MAX_SIZE = 10001;

	static commonFriend heap[];
	static int heapSize = 0;
	
	linkedList[] users;
	int friendN;
	
	public void init(int N)
	{
		friendN = N;
		users = new linkedList[N + 1];
		for(int i = 0; i < N + 1; i++) {
			users[i] = new linkedList();
		}
	}
	
	public void add(int id, int F, int ids[])
	{
		for(int i = 0; i < F; i++) {
			users[id].addFriend(ids[i]);
			users[ids[i]].addFriend(id);
		}
	}
	
	public void del(int id1, int id2)
	{
		users[id1].delFriend(id2);
		users[id2].delFriend(id1);
	}
	
	public int recommend(int id, int list[])
	{
		heapSize = 0;
		heap = new commonFriend[MAX_SIZE];
		boolean[] isFriend = new boolean[friendN + 1];
		for(int i = 0; i < friendN + 1; i++) {
			isFriend[i] = false;
		}
		
		// search how is id's friend
		node cur = users[id].head;
		while(cur.next != null) {
			isFriend[cur.next.friendID] = true;
			cur = cur.next;
		}
		
		// count all the common friend count among who is not id's friend.
		for(int i = 1; i <= friendN; i++) {
			if(!isFriend[i] && i != id) {
				// i = id's non-friend's id.
				int friendCnt = 0;
				cur = users[i].head;
				
				while(cur.next != null) {
					if(isFriend[cur.next.friendID]) {
						friendCnt++;
					}
					cur = cur.next;
				}
				heapPush(new commonFriend(friendCnt, i));
			}
		}
		
		commonFriend candidate;
		int ret = 0;
		for(int i = 0; i < 5; i++) {
			candidate = heapPop();
			if(candidate == null) {
				break;
			}
			if(candidate.friendCnt == 0) {
				break;
			}
			list[ret++] = candidate.friendID;
		}
		
		heapSize = 0;
		heap = null;
		isFriend = null;
		
		return ret;
	}


	static void heapPush(commonFriend value)
	{
		if (heapSize + 1 > MAX_SIZE)
		{
			return;
		}

		heap[heapSize] = value;

		int current = heapSize;
		while (current > 0) 
		{
			if(heap[current].friendCnt > heap[(current - 1) / 2].friendCnt
					|| (heap[current].friendCnt == heap[(current - 1) / 2].friendCnt && heap[current].friendID < heap[(current - 1) / 2].friendID)) {
				commonFriend temp = heap[(current - 1) / 2];
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

	static commonFriend heapPop()
	{
		if (heapSize <= 0)
		{
			return null;
		}

		commonFriend value = heap[0];
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
				if(heap[current * 2 + 1].friendCnt > heap[current * 2 + 2].friendCnt) {
					child = current * 2 + 1;
				}
				else if(heap[current * 2 + 1].friendCnt == heap[current * 2 + 2].friendCnt) {
					if(heap[current * 2 + 1].friendID < heap[current * 2 + 2].friendID ) {
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

			if (heap[current].friendCnt > heap[child].friendCnt)
			{
				break;
			}
			else if(heap[current].friendCnt == heap[child].friendCnt) {
				if(heap[current].friendID < heap[child].friendID) {
					break;
				}
			}

			commonFriend temp = heap[current];
			heap[current] = heap[child];
			heap[child] = temp;

			current = child;
		}
		return value;
	}
	
	class commonFriend {
		int friendCnt;
		int friendID;
		commonFriend(int cnt, int id) {
			friendCnt = cnt;
			friendID = id;
		}
	}
	
	class node {
		int friendID;
		node next;
		node(int fID) {
			friendID = fID;
			next = null;
		}
	}
	
	class linkedList{
		node head;
		linkedList() {
			head = new node(-1);
		}
		
		void addFriend(int fID) {
			node cur = head;
			node friend = new node(fID);
			
			if(cur.next == null) {
				cur.next = friend;
			}
			else {
				friend.next = cur.next;
				cur.next = friend;
			}
		}
		
		void delFriend(int fID) {
			node cur = head;
			while(cur.next != null) {
				if(cur.next.friendID == fID) {
					if(cur.next.next == null) {
						cur.next = null;
					}
					else {
						cur.next = cur.next.next;
					}
					break;
				}
				cur = cur.next;
			}
		}
	}
}


