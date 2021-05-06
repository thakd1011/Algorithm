class UserSolution {
	int MAX_SIZE = 2000;
    // event per group, and each groups has hash table, and each hash tables has 5 columns array to save event.
    // It needs hash key table to calculate each name's hash value and it also has got to save count value how many times it has been accessed.
    
	event[][][] group;
	long[][] groupHashTb;
    int[] user;

	void init()
	{
		groupHashTb = new long[MAX_SIZE][2];
		group = new event[100][MAX_SIZE][5];
		user = new int[1000];
	
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < MAX_SIZE; j++) {
				for(int k = 0; k < 5; k++) {
					group[i][j][k] = new event(-1, false);
				}
			}
		}
	}
	
	long getHash(char[] name) {
		long hash = 5381;
		for(int i = 0; name[i] != '\0'; i++) {
			hash = (hash * 31 + (int)name[i]) % 9999999;
		}
		return hash;
	}
	
	int findHash(char[] name) {
		long hash = getHash(name);
		int hashKey = (int)hash % MAX_SIZE;
		while(true) {
			if(groupHashTb[hashKey][0] == 0 && groupHashTb[hashKey][1] == 0) {
				groupHashTb[hashKey][0] = hash;
				groupHashTb[hashKey][1]++;
				return hashKey;
			}
			else if(groupHashTb[hashKey][0] == hash && groupHashTb[hashKey][1] != 0) {
				groupHashTb[hashKey][1]++;
				return hashKey;
			}
			else {
				hashKey = (hashKey + 1) % MAX_SIZE;
			}
		}
	}
	
	void addEvent(int uid, char ename[], int groupid)
	{
		int groupHk = findHash(ename);
		int posi = 0;
		int cnt = 0;
		for(int i = 0; i < 5; i++) {
			if(group[groupid][groupHk][i].uID == -1) {
				posi = i;
			}
			else {
				cnt++;
			}
		}
		
		if(cnt == 0) {
			group[groupid][groupHk][posi] = new event(uid, true);
		}
		else {
			group[groupid][groupHk][posi] = new event(uid, false);
		}
		
		user[uid]++;
	}

	int deleteEvent(int uid, char ename[])
	{
		// remove ename enrolled by uID in every groups.
		int eventHk = findHash(ename);
		int ret = 0;

		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 5; j++) {
				if(group[i][eventHk][j].uID == uid) {
					if(group[i][eventHk][j].isMaster) {
						for(int k = 0; k < 5; k++) {
							if(group[i][eventHk][k].uID != -1) {
								user[group[i][eventHk][k].uID]--;
								
								group[i][eventHk][k].uID = -1;
								group[i][eventHk][k].isMaster = false;
                                
                                // decrease access count in hash table.
								groupHashTb[eventHk][1]--;
								ret++;
							}
						}
						break;
					}
					else {
						group[i][eventHk][j].uID = -1;
						group[i][eventHk][j].isMaster = false;
						groupHashTb[eventHk][1]--;
						ret++;
						user[uid]--;
					}
				}
			}
		}
		return ret;
	}

	int changeEvent(int uid, char ename[], char cname[])
	{
		int eventHk = findHash(ename);
		int changeHk = findHash(cname);
		int ret = 0;
		// change event name from ename to cname which owned by uID.
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 5; j++) {
				if(group[i][eventHk][j].uID == uid) {
					// if when it is master event, all the events in the same group have to be moved to cname's event.
					
					if(group[i][eventHk][j].isMaster) {
						int[] changedID = new int[5];
						int idx = 0;
						int masterIdx = group[i][eventHk][j].uID;
						
						for(int k = 0; k < 5; k++) {
							if(group[i][eventHk][k].uID != -1) {
								// remember what have to be moved.
								changedID[idx++] = group[i][eventHk][k].uID;
                                
								group[i][eventHk][k].uID = -1;
								group[i][eventHk][k].isMaster = false;
                                
								groupHashTb[eventHk][1]--;
								ret++;
							}
						}
						
						int cnt = 0;
						for(int k = 0; k < 5; k++) {
							if(group[i][changeHk][k].uID == -1 && cnt < idx) {
                                // write at the empty array element.
								if(changedID[cnt] == masterIdx) {
									group[i][changeHk][k].isMaster = true;
								}
								else {
									group[i][changeHk][k].isMaster = false;
								}
								group[i][changeHk][k].uID = changedID[cnt++];
								groupHashTb[changeHk][1]++;
							}
						}
						break;
					}
					else {
						group[i][eventHk][j].uID = -1;
						group[i][eventHk][j].isMaster = false;
                        
						group[i][changeHk][j].uID = uid;
						group[i][changeHk][j].isMaster = true;
                        
						groupHashTb[eventHk][1]--;
						groupHashTb[changeHk][1]++;
						ret++;
					}
				}
			}
		}
		return ret;
	}

	int getCount(int uid)
	{
		return user[uid];
	}
	
	class event {
		int uID;
		boolean isMaster;
		event(int uID, boolean isMaster) {
			this.uID = uID;
			this.isMaster = isMaster;
		}
	}
}
