class Node {
	public int data;
	public Node prev;
	public Node next;

	public Node(int data) {
		this.data = data;
		this.prev = null;
		this.next = null;
	}
	public Node() {
		this.prev = null;
		this.next = null;
	}
}

public class UserSolution {
	
	private final static int MAX_NODE = 10000;

	private Node[] node = new Node[MAX_NODE];
	private int nodeCnt = 0;
	private Node head;
    
	public Node getNode(int data) {
		node[nodeCnt] = new Node(data);
		return node[nodeCnt++];
	}

	public void init() {
		head = new Node();
	}
	
	public void addNode2Head(int data) {
		Node newNode = getNode(data);
		Node nextNode = head.next;
		head.next = newNode;
		newNode.prev = head;
		if(nextNode != null) {
			newNode.next = nextNode;
			nextNode.prev = newNode;
		}
	}

	public void addNode2Tail(int data) {
		Node cursor = head;
		while(cursor.next != null) {
			cursor = cursor.next;
		}
		Node newNode = getNode(data);
		cursor.next = newNode;
		newNode.prev = cursor;
	}

	public void addNode2Num(int data, int num) {
		Node cursor = head;
		Node newNode = getNode(data);
		int nodeNum = 0;
		
		while(cursor != null) {
			if(nodeNum + 1 == num) {
				Node nextNode = cursor.next;
				cursor.next = newNode;
				newNode.prev = cursor;
				if(nextNode != null) {
					newNode.next = nextNode;
					nextNode.prev = newNode;
				}
				break;
			}
			nodeNum += 1;
			cursor = cursor.next;
		}
	}
	
	public int findNode(int data) {
		Node cursor = head.next;
		int nodeNum = 0;
		while(cursor != null) {
			nodeNum += 1;
			if(cursor.data == data) {
				break;
			}
			cursor = cursor.next;
		}
		
		return nodeNum;
	}
	
	public void removeNode(int data) {
		Node cursor = head.next;

		while(cursor != null) {
			if(cursor.data == data) {
				Node prevNode = cursor.prev;
				Node nextNode = cursor.next;
				prevNode.next = nextNode;
				if(nextNode != null) {
					nextNode.prev = prevNode;
				}
				cursor = null;
				break;
			}
			cursor = cursor.next;
		}
	}

	public int getList(int[] output) {
		int nodeCnt = 0;
		Node cursor = head.next;
		while(cursor != null) {
			output[nodeCnt] = cursor.data;
			nodeCnt += 1;
			cursor = cursor.next;
		}
		return nodeCnt;
	}
	
	public int getReversedList(int[] output) {
		int nodeCnt = 0;
		Node cursor = head;
		while(cursor.next != null) {
			cursor = cursor.next;
		}
		
		Node backCursor = cursor;
		while(backCursor != head) {
			output[nodeCnt] = backCursor.data;
			nodeCnt += 1;
			backCursor = backCursor.prev;
		}
		
		return nodeCnt;
	}
}
