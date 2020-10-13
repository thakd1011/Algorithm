if __name__ == "__main__" :
	T = int(input())
	for t in range(T) :
		M, N, K = [int(item) for item in input().split(' ')]
		ground = []
		for r in range(N) :
			temp = [0 for c in range(M)]
			ground += [temp]

		candidates = []
		for k in range(K) :
			c, r = [int(item) for item in input().split(' ')]
			ground[r][c] = 1
			candidates.append([r, c])


		cnt = 0
		

		while candidates :

			cabage = candidates[0]
			# print("stack cabage = ", cabage)
			stack = [cabage]
			visited = []
			dirs = [[0,1],[0,-1],[1,0],[-1,0]]

			while stack :
				tempCabage = stack.pop()
				# print("tempCabage = ", tempCabage)
				if tempCabage not in visited :
					visited += [tempCabage]
					candidates.remove(tempCabage)
					# print("removed candidates = ", candidates)

					for d in range(4) :
						nextR = tempCabage[0] + dirs[d][0]
						nextC = tempCabage[1] + dirs[d][1]
						if nextR < 0 or nextC < 0 or nextR >= N or nextC >= M :
							continue;
						else :
							if [nextR, nextC] not in visited and [nextR, nextC] in candidates :
								stack.append([nextR, nextC])
			# print("candidates = ", candidates)
			cnt += 1

		print(cnt)