public class UserSolution {
	maxHeap heap;
	
	class user {
		int income;
		int uID;
		user(int uID, int income) {
			this.uID = uID;
			this.income = income;
		}
	}
	
	static class maxHeap {
		user[] heap;
		int HEAP_SIZE = 100001;
		int hCnt;
		
		maxHeap() {
			heap = new user[HEAP_SIZE];
			hCnt = 0;
		}
		
		void heapPush(user value) {
			heap[++hCnt] = value;
			int current = hCnt;
			int parent;
			while(true) {
				parent = current / 2;
				if(current <= 1 || heap[parent].income > heap[current].income) {
					break;
				}
				else if(heap[parent].income == heap[current].income) {
					if(heap[parent].uID < heap[current].uID) {
						break;
					}
					else {
						user temp = heap[parent];
						heap[parent] = heap[current];
						heap[current] = temp;
					}
				}
				else {
					user temp = heap[parent];
					heap[parent] = heap[current];
					heap[current] = temp;
				}
				current = parent;
			}
		}
        
		user getMax() {
			if(hCnt == 0) return null;
			return heap[1];
		}
		
		user heapPop() {
			if(hCnt == 0) return null;
			user ret = heap[1];
			heap[1] = heap[hCnt--];
			int current = 1;
			int child;
			
			while(true) {
				child = current * 2;
				
				if(child < hCnt && heap[child].income < heap[child + 1].income) {
					child += 1;
				}
				else if(child < hCnt && heap[child].income == heap[child + 1].income && heap[child].uID > heap[child + 1].uID) {
					child += 1;
				}
				
				if(child > hCnt || heap[child].income < heap[current].income) {
					break;
				}
				else if(child > hCnt || heap[child].income == heap[current].income && heap[child].uID > heap[current].uID) {
					break;
				}
				
				user temp = heap[current];
				heap[current] = heap[child];
				heap[child] = temp;
				current = child;
			}
			
			return ret;
		}
	}

	public void init() {
		heap = new maxHeap();
	}
	
	public void addUser(int uID, int income) {
		user newUser = new user(uID, income);
		heap.heapPush(newUser);
	}
	
	int getTop10(int[] result) {
		int cnt = 0;
		int ans = 0;
		user[] poppedList = new user[10];
		
		while(heap.getMax() != null) {
			if(cnt >= 10) break;
			poppedList[cnt] = heap.heapPop();
			result[cnt] = poppedList[cnt].uID;
			cnt++;
		}
		ans = cnt;
		
		for(int i = 0; i < cnt; i++) {
			heap.heapPush(poppedList[i]);
		}
		return cnt;
	}
}
