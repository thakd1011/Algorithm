if __name__ == "__main__" :
	dwarfs = []
	idx = []
	s = 0

	for i in range(9) :
		temp = int(input())
		dwarfs += [temp]
		s += temp

	for i in range(9) :
		for j in range(9) :
			if i == j :
				continue;

			if s - dwarfs[i] - dwarfs[j] == 100 :
				idx = [i, j]
				break;

	idx = sorted(idx)
	dwarfs.pop(idx[0])
	dwarfs.pop(idx[1] - 1)
	ans = sorted(dwarfs)
	for i in range(7) :
		print(ans[i])