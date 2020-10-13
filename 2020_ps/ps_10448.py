triNum = []

def checkTrinum(N) :
	global triNum
	temp = N
	for i in range(1,N) :
		iNum = temp - triNum[i]
		if iNum < 0 :
			break;
		else :
			for k in range(1,N) :
				kNum = iNum - triNum[k]
				if kNum < 0 :
					break;
				else :
					for j in range(1,N) :
						jNum = kNum - triNum[j]
						if jNum == 0 :
							print("1")
							return;
						elif jNum > 0 :
							continue;
						else :
							break;

	print("0")
	return;

if __name__ == "__main__" :
	for i in range(1000) :
		temp = int(i * (i + 1) / 2)
		triNum += [temp]

	T = int(input())

	for t in range(T) :
		N = int(input())
		checkTrinum(N)