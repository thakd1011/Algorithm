# 90 degrees
def clockwise(plane) :
	N = len(plane)
	rotated = []
	for i in range(N) :
		rotated += [[0 for j in range(N)]]
	for r in range(N) :
		for c in range(N) :
			newR = c
			newC = N - r - 1
			rotated[newR][newC] = plane[r][c]

	return rotated


# -90 degrees
def counterClockwise(plane) :
	N = len(plane)
	rotated = []
	for i in range(N) :
		rotated += [[0 for j in range(N)]]

	for r in range(N) :
		for c in range(N) :
			newR = N - c - 1
			newC = r
			rotated[newR][newC] = plane[r][c]

	return rotated

def left(cube, rotation) :
	if rotation == '+' :
		cube[4] = clockwise(cube[4])
		temp = [cube[3][r][0] for r in range(3)]
		
		# cube[3][r][0] = cube[2][r][0]
		for r in range(3) :
			cube[3][r][0] = cube[2][r][0]
		# cube[2][r][0] = cube[1][r][0]
		for r in range(3) :
			cube[2][r][0] = cube[1][r][0]
		for r in range(3) :
			cube[1][r][0] = cube[0][r][0]
		for r in range(3) :
			cube[0][r][0] = temp[r]
	else :
		cube[4] = counterClockwise(cube[4])
		temp = [cube[0][r][0] for r in range(3)]
		for r in range(3) :
			cube[0][r][0] = cube[1][r][0]
		for r in range(3) :
			cube[1][r][0] = cube[2][r][0]
		for r in range(3) :
			cube[2][r][0] = cube[3][r][0]
		for r in range(3) :
			cube[3][r][0] = temp[r]

	return cube

def right(cube, rotation) :
	if rotation == '+' :
		cube[5] = clockwise(cube[5])
		temp = [cube[0][r][2] for r in range(3)]
		for r in range(3) :
			cube[0][r][2] = cube[1][r][2]
		for r in range(3) :
			cube[1][r][2] = cube[2][r][2]
		for r in range(3) :
			cube[2][r][2] = cube[3][r][2]
		for r in range(3) :
			cube[3][r][2] = temp[r]

	else :
		cube[5] = counterClockwise(cube[5])
		temp = [cube[3][r][2] for r in range(3)]
		for r in range(3) :
			cube[3][r][2] = cube[2][r][2]
		for r in range(3) :
			cube[2][r][2] = cube[1][r][2]
		for r in range(3) :
			cube[1][r][2] = cube[0][r][2]
		for r in range(3) :
			cube[0][r][2] = temp[r]

	return cube

def front(cube, rotation) :
	if rotation == '+' :
		cube[3] = clockwise(cube[3])
		temp = [cube[5][2][2 - c] for c in range(3)]
		for c in range(3) :
			cube[5][2][2 - c] = cube[2][2][2 - c]
		for c in range(3) :
			cube[2][2][2 - c] = cube[4][2][2 - c]
		for c in range(3) :
			cube[4][2][2 - c] = cube[0][0][c]
		for c in range(3) :
			cube[0][0][c] = temp[c]

	else :
		cube[3] = counterClockwise(cube[3])
		temp = [cube[0][0][c] for c in range(3)]
		for c in range(3) :
			cube[0][0][c] = cube[4][2][2 - c]
		for c in range(3) :
			cube[4][2][2 - c] = cube[2][2][2 - c]
		for c in range(3) :
			cube[2][2][2 - c] = cube[5][2][2 - c]
		for c in range(3) :
			cube[5][2][2 - c] = temp[c]
	return cube

def back(cube, rotation) :
	if rotation == '+' :
		cube[1] = clockwise(cube[1])
		temp = [cube[0][2][c] for c in range(3)]
		for c in range(3) :
			cube[0][2][c] = cube[4][0][2 - c]
		for c in range(3) :
			cube[4][0][2 - c] = cube[2][0][2 - c]
		for c in range(3) :
			cube[2][0][2 - c] = cube[5][0][2 - c]
		for c in range(3) :
			cube[5][0][2 - c] = temp[c]

	else :
		cube[1] = counterClockwise(cube[1])
		temp = [cube[5][0][2 - c] for c in range(3)]
		for c in range(3) :
			cube[5][0][2 - c] = cube[2][0][2 - c]
		for c in range(3) :
			cube[2][0][2 - c] = cube[4][0][2 - c]
		for c in range(3) :
			cube[4][0][2 - c] = cube[0][2][c]
		for c in range(3) :
			cube[0][2][c] = temp[c]
	return cube

def upper(cube, rotation) :
	if rotation == '+' :
		cube[2] = clockwise(cube[2])
		temp = [cube[4][2 - i][2] for i in range(3)]
		for i in range(3) :
			cube[4][2 - i][2] = cube[3][0][2 - i]
		for i in range(3) :
			cube[3][0][2 - i] = cube[5][i][0]
		for i in range(3) :
			cube[5][i][0] = cube[1][2][i]
		for i in range(3) :
			cube[1][2][i] = temp[i]

	else :
		cube[2] = counterClockwise(cube[2])
		temp = [cube[1][2][i] for i in range(3)]
		for i in range(3) :
			cube[1][2][i] = cube[5][i][0]
		for i in range(3) :
			cube[5][i][0] = cube[3][0][2 - i]
		for i in range(3) :
			cube[3][0][2 - i] = cube[4][2 - i][2]
		for i in range(3) :
			cube[4][2 - i][2] = temp[i]
	return cube

def down(cube, rotation) :
	cube[0] = clockwise(cube[0])
	if rotation == '+' :
		temp = [cube[1][0][2 - i] for i in range(3)]
		for i in range(3) :
			cube[1][0][2 - i] = cube[5][2 - i][2]
		for i in range(3) :
			cube[5][2 - i][2] = cube[3][2][i]
		for i in range(3) :
			cube[3][2][i] = cube[4][i][0]
		for i in range(3) :
			cube[4][i][0] = temp[i]

	else :
		cube[0] = counterClockwise(cube[0])
		temp = [cube[4][i][0] for i in range(3)]
		for i in range(3) :
			cube[4][i][0] = cube[3][2][i]
		for i in range(3) :
			cube[3][2][i] = cube[5][2 - i][2]
		for i in range(3) :
			cube[5][2 - i][2] = cube[1][0][2 - i]
		for i in range(3) :
			cube[1][0][2 - i] = temp[i]
	return cube

def printCube(cube) :
	for i in range(3) :
		print("* * *", cube[0][i][0], cube[0][i][1], cube[0][i][2], "* * *")
	for i in range(3) :
		print("* * *", cube[1][i][0], cube[1][i][1], cube[1][i][2], "* * *")
	for i in range(3) :
		print(cube[4][i][0], cube[4][i][1], cube[4][i][2], cube[2][i][0], cube[2][i][1], cube[2][i][2], cube[5][i][0], cube[5][i][1], cube[5][i][2])
	for i in range(3) :
		print("* * *", cube[3][i][0], cube[3][i][1], cube[3][i][2], "* * *")

if __name__ == "__main__" :
	N = int(input())

	for i in range(N) :
		cnt = int(input())
		rotateList = input().split(' ')

		cube = [
		[['y','y','y'],['y','y','y'],['y','y','y']],
		[['o','o','o'],['o','o','o'],['o','o','o']],
		[['w','w','w'],['w','w','w'],['w','w','w']],
		[['r','r','r'],['r','r','r'],['r','r','r']],
		[['g','g','g'],['g','g','g'],['g','g','g']],
		[['b','b','b'],['b','b','b'],['b','b','b']]
		]

		# print("<< new start! >>")
		# printCube(cube)
		# print("Let's go!")
		for c in range(cnt) :
			temp = rotateList[c]
			plane = temp[0]
			direction = temp[1]
			# print(temp[0], temp[1])
			if plane == 'L' :
				cube = left(cube, direction)
			elif plane == 'R' :
				cube = right(cube, direction)
			elif plane == 'F' :
				cube = front(cube, direction)
			elif plane == 'B' :
				cube = back(cube, direction)
			elif plane == 'U' :
				cube = upper(cube, direction)
			elif plane == 'D' :
				cube = down(cube, direction)
			# print("================")
			# printCube(cube)
			# print("================\n")
		# print(cube[2][0])
		# print(cube[2][1])
		# print(cube[2][2])
		for j in range(3) :
			temp = ""
			for k in range(3) :
				temp += cube[2][j][k]
			print(temp)