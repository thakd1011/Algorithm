def findMaximum(candyBoard) :
	s = len(candyBoard)
	cnt = 0

	for row in range(s) :
		temp = candyBoard[row]
		idx = 0
		while idx < s - 1 :
			if temp[idx] == temp[idx + 1] :
				local = 1
				for i in range(idx + 1, s) :
					if temp[i - 1] != temp[i] :
						idx = i - 1
						break;
					local += 1
				if local > cnt :
					cnt = local

			idx += 1

	for col in range(s) :
		temp = [candyBoard[i][col] for i in range(s)]
		idx = 0
		while idx < s - 1 :
			if temp[idx] == temp[idx + 1] :
				local = 1
				for i in range(idx + 1, s) :
					if temp[i - 1] != temp[i] :
						idx = i - 1
						break;
					local += 1
				if local > cnt :
					cnt = local

			idx += 1
	return cnt


if __name__ == "__main__" :
	answer = 0
	N = int(input())
	board = []

	for i in range(N) :
		temp = list(input())
		board += [temp]

	for row in range(N) :
		for col in range(N - 1) :

			if board[row][col] != board[row][col + 1] :
				# exchange values
				temp = board[row][col + 1]
				board[row][col + 1] = board[row][col]
				board[row][col] = temp
				localAns = findMaximum(board)

				if localAns > answer :
					answer = localAns
				temp = board[row][col + 1]
				board[row][col + 1] = board[row][col]
				board[row][col] = temp

	for col in range(N) :
		for row in range(N - 1) :

			if board[row][col] != board[row + 1][col] :
				# exchange values
				temp = board[row + 1][col]
				board[row + 1][col] = board[row][col]
				board[row][col] = temp
				localAns = findMaximum(board)
				
				if localAns > answer :
					answer = localAns
				temp = board[row + 1][col]
				board[row + 1][col] = board[row][col]
				board[row][col] = temp
	print(answer)