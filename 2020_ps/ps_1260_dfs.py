def dfs(root, graph) :
	stack = [root]
	visited = []
	ans = []

	while stack :
		tempNode = stack.pop()

		if tempNode not in visited :
			visited += [tempNode]
			ans += [tempNode]
			candidates = []
			for i in range(len(graph)) :
				if graph[tempNode][i] == 1 :
					candidates += [i]
			candidates.sort(reverse = True)
			stack.extend(candidates)

	for i in range(len(ans)) :
		print(ans[i] + 1, end=' ')

def bfs(root, graph) :
	queue = [root]
	visited = []
	ans = []

	while queue :
		tempNode = queue.pop(0)

		if tempNode not in visited :
			visited += [tempNode]
			ans += [tempNode]
			candidates = []

			for i in range(len(graph)) :
				if graph[tempNode][i] == 1 :
					candidates += [i]

			queue.extend(candidates)
	for i in range(len(ans)) :
		print(ans[i] + 1, end=' ')


# def bfs(root, graph) :


if __name__ == "__main__" :
	N, M, V = [int(i) for i in input().split(' ')]
	graph =[]
	for i in range(N) :
		temp = [0 for j in range(N)]
		graph += [temp]

	for i in range(M) :
		s, e = [int(j) for j in input().split(' ')]
		graph[s - 1][e - 1] = 1
		graph[e - 1][s - 1] = 1
	dfs(V - 1, graph)
	print()
	# visited.clear()
	bfs(V - 1, graph)
