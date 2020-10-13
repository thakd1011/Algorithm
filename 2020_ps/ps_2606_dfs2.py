if __name__ == "__main__" :
	N = int(input())
	E = int(input())
	network = []
	for i in range(N) :
		temp = [0 for j in range(N)]
		network += [temp]

	for i in range(E) :
		s, e = [int(item) for item in input().split(' ')]
		network[s - 1][e - 1] = 1
		network[e - 1][s - 1] = 1

	stack = [0]
	visited = []
	cnt = 0

	while stack :
		tempNode = stack.pop()
		if tempNode not in visited :
			visited += [tempNode]
			cnt += 1

			for i in range(N) :
				if network[tempNode][i] == 1 :
					stack.extend([i])

	print(cnt - 1)