public class UserSolution {
	int mstrcmp(char[] a, char[] b) {
		int i;
		for(i = 0; a[i] != '\0'; i++) {
			if(a[i] != b[i]) {
				return a[i] - b[i];
			}
		}
		return a[i] - b[i];
	}
	void mstrcpy(char[] dest, char[] src) {
		int i = 0;
		while(src[i] != '\0') {
			dest[i] = src[i];
			i++;
		}
		dest[i] = src[i];
	}
	
	final int capacity = 623;
	int initR;
	int initC;
	int[][] storage = new int[301][301];
	Item[] itemHash;
	Tag[] tagHash;	
	
	void init(int R, int C) {
		initR = R;
		initC = C;
		for(int i = 1; i <= R; i++) {
			for(int j = 1; j <= C; j++) {
				storage[i][j] = -1;
			}
		}
		itemHash = new Item[capacity];
		tagHash = new Tag[capacity];
	}
	
	int addItem(char name[], char tagname[], int height, int width, int mode, int r, int c) {
		
		if(mode == 0) {
			// 지정 위치 add
			// outta range
			if(r < 1 || c < 1 || (r + height - 1) > initR || (c + width - 1) > initC) {
				return 0;
			}
			// already exist item
			if( putPossibleAtPosition(r, c, height, width) != -1) {
				return 0;
			}
		}
		else {
			int[] rcPair = findPosition(height, width);
			if(rcPair[0] == -1) {
				return 0;
			}
			r = rcPair[0];
			c = rcPair[1];
		}
		// random 위치 add -> row우선순위
		// random 찾으면서 안 되는 경우가 언제일까? -> overlapped, outta range
		int itemId = getItemId(name);
		int tagId = getTagId(tagname);
		// 가장 왼쪽, 그리고 가장 위쪽의 위치를 찾아서 ->
		// item, tag hash에 itemId, tagId key값으로 item, tag 넣으면됨
		
		// rcPair의 위치에 item 넣는다
		itemHash[itemId].add(tagId, r, c, width, height);
		tagHash[tagId].add(itemId);
		// storage도 itemId로 바꿔야지
		for(int i = r; i < r + height; i++) {
			for(int j = c; j < c + width; j++) {
				storage[i][j] = itemId;
			}
		}
		return 1;
	}
	
	int[] findPosition(int height, int width) {
		int maxR = initR - height + 1;
		int maxC = initC - width + 1;
		int[] rcPair = new int[2];
		int tempCol;
		rcPair[0] = -1; rcPair[1] = -1;
		
		for(int c = 1; c <= maxC; c++) {
			int leftCol = 999;
			for(int r = 1; r <= maxR; r++) {
				
				int itemId = putPossibleAtPosition(r, c, height, width);
				if(itemId == -1) {
					rcPair[0] = r; rcPair[1] = c;
					return rcPair;
				}
				
				// overlapped;
				r = itemHash[itemId].r + itemHash[itemId].height - 1;
				// find most left col value
				tempCol = itemHash[itemId].c + itemHash[itemId].width - 1;
				if(leftCol > tempCol) {
					leftCol = tempCol;
				}				
			}
			if(leftCol != 999) {// at least, overlapped at once;
				c = leftCol;
			}
		}
		return rcPair;
	}
	
	int putPossibleAtPosition(int r, int c, int height, int width) {
		for(int i = r; i < r + height; i++) {
			for(int j = c; j < c + width; j++) {
				if(storage[i][j] != -1 && !itemHash[storage[i][j]].isRemoved) {
					return storage[i][j]; // itemId를 return한 것 -> 찾아가면 된다.
				}
			}
		}
		return -1;
	}
	
	int removeItemByName(char name[]) {
		int itemId = getItemId(name);
		if(itemId == -1 || itemHash[itemId].isRemoved) {
			return 0;
		}
		// 만약에 존재하고 있다면? 지워야지.
		itemHash[itemId].isRemoved = true;
		return 1;
	}
	
	int removeItemByTag(char[] tag) {
		int tagId = getTagId(tag);
		int removedCnt = 0;
		
		if(tagId == -1 || tagHash[tagId].itemCnt == 0) {
			return 0;
		}
		
		itemNode node = tagHash[tagId].head.next;
		while(node != null) {
			if( !itemHash[node.itemId].isRemoved ) {
				removedCnt++;
				itemHash[node.itemId].isRemoved = true;
			}
			node = node.next;
		}
		tagHash[tagId].itemCnt = 0;
		tagHash[tagId].head.next = null;
		return removedCnt;
	}
	
	
	int getItem(int r, int c, char name[], char tag[]) {
		int itemId = storage[r][c];
		if(itemId == -1 || itemHash[itemId].isRemoved) {
			return 0;
		}
		int tagId = itemHash[itemId].tagId;
		mstrcpy(name, itemHash[itemId].name);
		mstrcpy(tag, tagHash[tagId].tagName);
		
		return 1;
	}
	
	int getArea(char tag[]) {
		int tagId = getTagId(tag);
		if(tagHash[tagId].itemCnt == 0) {
			return 0;
		}
		int areaSum = tagHash[tagId].getArea();
		return areaSum;
	}
	
	int getHashKey(char[] str) {
		int key = 0;
		for(int i = 0; str[i] != '\0'; i++) {
			key = (key * 31 + str[i]) % capacity;
		}
		return key;
	}
	
	int getTagId(char[] tagName) {
		int tagHashKey = getHashKey(tagName);
		for(int i = 0; i < capacity; i++) {
			tagHashKey = (tagHashKey + i) % capacity;
			
			// hash table이 아예 비어있는 경우
			if(tagHash[tagHashKey] == null) {
				tagHash[tagHashKey] = new Tag(tagName);
				return tagHashKey;
			}
			// hashkey에 있는 tag명이 같을 경우 -> 있던 key return
			else if(mstrcmp(tagHash[tagHashKey].tagName, tagName) == 0) {
				return tagHashKey;
			}
		}
		return -1;
	}
	
	int getItemId(char[] itemName) {
		int itemHashKey = getHashKey(itemName);
		// key부터 1씩 증가시켜가면서 hash table의 빈 공간을 탐색한다.
		for(int i = 0; i < capacity; i++) {
			itemHashKey = (itemHashKey + i) % capacity;
			if(itemHash[itemHashKey] == null) {
				itemHash[itemHashKey] = new Item(itemName);
				return itemHashKey;
			}
			else if(mstrcmp(itemHash[itemHashKey].name, itemName) == 0) {
				// 같은 이름이 있는 hash값이면 해당 key값을 return한다. -> 이게 해당 name의 id값
				return itemHashKey;
			}
		}
		return -1;
	}
	
	class Item{
		char[] name = new char[11];
		int tagId;
		int r;
		int c;
		int width;
		int height;
		boolean isRemoved;
		
		Item(char[] name) {
			mstrcpy(this.name, name);
			isRemoved = true;
		}
		
		void add(int tagId, int r, int c, int width, int height) {
			this.tagId = tagId;
			this.r = r;
			this.c = c;
			this.width = width;
			this.height = height;
			isRemoved = false;
		}
	}
	
	class Tag{
		char[] tagName = new char[11];
		int itemCnt;
		itemNode head; // 맨 처음 head는 아무것도 포함하지 않은 head이다.
		
		Tag(char[] tagName) {
			mstrcpy(this.tagName, tagName);
			itemCnt = 0;
			head = new itemNode(-1);
		}
		
		void add(int itemId) {			
			itemCnt++;
			itemNode newItem = new itemNode(itemId);
			newItem.next = head.next;
			head.next = newItem;
		}
		
		int getArea() {
			int areaSum = 0;
			boolean[] visit = new boolean[capacity];
			itemNode tempItem = head.next;
			
			while(tempItem != null) {
				if( !itemHash[tempItem.itemId].isRemoved && !visit[tempItem.itemId]) {
					visit[tempItem.itemId] = true;
					areaSum = areaSum + (itemHash[tempItem.itemId].width * itemHash[tempItem.itemId].height);
				}
				tempItem = tempItem.next;
			}
			return areaSum;
		}
	}
	
	class itemNode{
		int itemId;
		itemNode next;
		
		itemNode(int itemId) {
			this.itemId = itemId;
		}
	}
}
