import heapq
from collections import deque

def find_shortest(bowl, root, N, shark_size) :
	q = deque()
	q.append(root)
	visited = set()
	dirs = [(1,0), (-1,0), (0,1), (0,-1)]
	fish_candidates = []

	while q :
		tempR, tempC, dist = q.popleft()
		if (tempR, tempC) not in visited :
			visited.add((tempR, tempC))

			for dr, dc in dirs :
				nextR, nextC = tempR + dr, tempC + dc
				if nextR < 0 or nextC < 0 or nextR >= N or nextC >= N :
					continue;
				if bowl[nextR][nextC] == 0 or bowl[nextR][nextC] == shark_size :
					q.append((nextR, nextC, dist + 1))
				elif bowl[nextR][nextC] > shark_size :
					continue;
				else :
					heapq.heappush(fish_candidates, (dist + 1, nextR, nextC))

	if fish_candidates :
		return fish_candidates[0]
	else :
		return None




if __name__ == "__main__" :
	N = int(input())
	shark = []
	bowl = []
	for i in range(N) :
		tempRow = [int(item) for item in input().split(' ')]
		bowl += [tempRow]
		for j in range(N) :
			if tempRow[j] == 9 :
				root = (i, j, 0)

	shark_size = 2
	eaten_fish = 0
	time = 0
	bowl[root[0]][root[1]] = 0

	while True :
		nextFish = find_shortest(bowl, root, N, shark_size)

		if nextFish == None :
			break;
		
		dist, nextR, nextC = nextFish
		time += dist
		eaten_fish += 1

		if eaten_fish == shark_size :
			shark_size += 1
			eaten_fish = 0
		
		root = (nextR, nextC, 0)
		bowl[root[0]][root[1]] = 0

	print(time)

