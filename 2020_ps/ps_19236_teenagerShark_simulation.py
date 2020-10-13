import copy
dirset = [(-1, 0), (-1, -1), (0, -1), (1, -1), (1, 0), (1, 1), (0, 1), (-1, 1)]
maxAns = -1

def turningFish(fishMap) :
    idx = 1
    while idx < 17 :
        i, j = -1, -1

        for m in range(4) :
            for n in range(4) :
                if fishMap[m][n][0] == idx :
                    i, j = m, n
                    break;
        if i != -1 and j != -1 :
        # if fishMap[i][j][0] == idx :
            tempDir = fishMap[i][j][1]
            # print("temp dir = ", tempDir)
            # print("idx = ", idx)
            for d in range(8) :
                # 6 7 8 9 / 6 7 0 1
                # 7 8 9 10 -> 6 7 8 9 % 8 6 7 0 1
                nextR, nextC = i + dirset[(tempDir + d - 1) % 8][0], j + dirset[(tempDir + d - 1) % 8][1]
                if nextR < 0 or nextC < 0 or nextR >= 4 or nextC >= 4 :
                    continue;
                if fishMap[nextR][nextC][0] == -1 :
                    continue;

                fishMap[i][j][1] = (tempDir + d) % 8
                if fishMap[nextR][nextC][0] == 0 :
                    fishMap[nextR][nextC] = fishMap[i][j]
                    fishMap[i][j] = [0, 0]
                    break;
                else :
                    # print("next = ", nextR, ", ", nextC)
                    # print("start ", fishMap[i][j], " and end ", fishMap[nextR][nextC], " swap!")
                    temp = fishMap[i][j]
                    fishMap[i][j] = fishMap[nextR][nextC]
                    fishMap[nextR][nextC] = temp
                    break;
        idx += 1
    # print("fishMap = ", fishMap)

def dfs(fishMap, shark, localAns) :
    global maxAns
    newMap = copy.deepcopy(fishMap)
    localAns += newMap[shark[0]][shark[1]][0]
    # print("localAns = ", localAns)
    shark[2] = newMap[shark[0]][shark[1]][1]
    newMap[shark[0]][shark[1]][0] = -1

    turningFish(newMap)

    sharkDir = shark[2]
    nextR, nextC = shark[0], shark[1]
    cnt = 0

    for i in range(3) :
        # print("shark dir = ", sharkDir)
        nextR, nextC = nextR + dirset[sharkDir - 1][0], nextC + dirset[sharkDir - 1][1]
        if nextR < 0 or nextC < 0 or nextR >= 4 or nextC >= 4 :
            break;
        if newMap[nextR][nextC][0] == 0 :
            continue;
        else :
            cnt += 1
            newMap[shark[0]][shark[1]] = [0, 0]
            dfs(newMap, [nextR, nextC, sharkDir], localAns)

    if cnt == 0 :
        if localAns > maxAns :
            maxAns = localAns


fishMap = [[[0,0] for _ in range(4)] for _ in range(4)]

for i in range(4) :
    temp = [int(item) for item in input().split(' ')]
    for j in range(4) :
        fishMap[i][j][0], fishMap[i][j][1] = temp[2*j], temp[2*j + 1]

shark = [0, 0, fishMap[0][0][1]]
dfs(fishMap, shark, 0)

print(maxAns)