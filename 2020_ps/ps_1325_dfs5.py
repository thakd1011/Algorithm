from collections import deque

ans = -1

def bfs(start, networks) :
	cnt = 0
	q = deque()
	visited = [0 for i in range(N + 1)]
	q.append(start)

	while q :
		tempCom = q.popleft()
		if visited[tempCom] == 0 :
			visited[tempCom] = 1
			cnt += 1
			for a in networks[tempCom] :
				q.append(a)
	return cnt


if __name__ == "__main__" :
	N, M = [int(item) for item in input().split(' ')]
	networks = [[] for _ in range(N + 1)]
	coms = []

	for i in range(M) :
		a, b = [int(item) for item in input().split(' ')]
		networks[b].append(a)

	for i in range(1, N + 1) :
		localAns = bfs(i, networks)

		if localAns == ans :
			coms += [i]
		elif localAns > ans :
			ans = localAns
			coms = [i]

	coms.sort()
	for i in range(len(coms)) :
		print(coms[i], end=' ')
	# print(ans)