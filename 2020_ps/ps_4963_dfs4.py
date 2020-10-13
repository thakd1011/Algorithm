if __name__ == "__main__" :
	while True :
		w, h = [int(item) for item in input().split(' ')]
		if w == 0 and h == 0 :
			break;
		landMap = []
		# stack = []
		nodes = []
		visited = []
		cnt = 0
		dirs = [[0,1],[1,0],[0,-1],[-1,0],[1,1],[1,-1],[-1,1],[-1,-1]]

		for i in range(h) :
			row = [int(item) for item in input().split(' ')]
			for j in range(w) :
				if row[j] == 1 :
					nodes += [[i, j]]

			landMap += [row]

		while nodes :
			stack = [nodes[0]]

			while stack :
				tempNode = stack.pop()

				if tempNode not in visited :
					visited += [tempNode]
					nodes.remove(tempNode)

					for i in range(8) :
						nextR = tempNode[0] + dirs[i][0]
						nextC = tempNode[1] + dirs[i][1]
						if nextR < 0 or nextC < 0 or nextR >= h or nextC >= w :
							continue;
						if [nextR, nextC] in nodes :
							stack.extend([[nextR, nextC]])

			cnt += 1
		print(cnt)



