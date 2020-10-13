if __name__ == "__main__" :
	numList = []
	T = int(input())
	condition = []
	for t in range(T) :
		temp = input().split(' ')
		sets = [temp[0]] + [int(temp[1])] + [int(temp[2])]
		condition += [sets]

	for i in range(1, 10) :
		temp = str(i)
		for j in range(1, 10) :
			localJ = str(j)
			if temp[0] != localJ :
				jNum = temp + localJ
				for k in range(1, 10) :
					localK = str(k)
					if jNum[0] != localK and jNum[1] != localK :
						kNum = jNum + localK
						numList += [int(kNum)]
	# print(str(numList[1]))
	# print(len(numList))

	answer = 0

	for c in range(len(numList)) :
		candidate = str(numList[c])
		# print("candidate = ", candidate)
		for i in range(T) :
			s_count = 0
			b_count = 0

			question = condition[i][0]
			strike = condition[i][1]
			ball = condition[i][2]
			# print("condition = ", condition)

			for idx in range(3) :
				pivot = candidate[idx]
				if question[idx] == pivot :
					s_count += 1
				else :
					nextOne = int((idx + 1) % 3)
					nextTwo = int((idx + 2) % 3)
					if question[nextOne] == pivot or question[nextTwo] == pivot :
						b_count += 1
			# print("s&b = ", s_count, ", ", b_count)

			if s_count != strike or b_count != ball :
				numList[c] = 0
				# print("nope!")
				break;
			
		# print("\n")

	# print(numList)
	for i in range(len(numList)) :
		if numList[i] != 0 :
			# print("ans = ", numList[i])
			answer += 1

	print(answer)
