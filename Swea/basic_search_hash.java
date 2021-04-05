public class UserSolution {

	private final static int MAX_LEN = 20;
	double[] wordHash = new double[300000];
	
	double getHash(char[] word) {
		double hash = 5381;
		for(int i = 0; word[i] != '\0'; i++) {
			hash = hash * 26 + (double)word[i];
		}
		return hash;
	}
	
    char search(char[] word) {
    	double hash = getHash(word);
    	int hashKey = (int)(hash % 300000);
    	
    	while(true) {
    		if(wordHash[hashKey] == 0) {
    			wordHash[hashKey] = hash;
    			return '0';
    		}
    		else if(wordHash[hashKey] == hash) {
    			return '1';
    		}
    		else {
    			hashKey = (hashKey + 1) % 300000;
    		}
    	}
    }
}


