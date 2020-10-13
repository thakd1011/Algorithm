from itertools import combinations
from collections import deque
import copy

N, M = [int(item) for item in input().split(' ')]
laboratory = []
virus = deque()
# walls = deque()
candidates = []
maxArea = -1

for i in range(N) :
    temp = [int(item) for item in input().split(' ')]
    laboratory += [temp]
    for j in range(M) :
        if temp[j] == 2 :
            virus.append([i, j])
        elif temp[j] == 0 :
            candidates.append([i, j])
dirset = [(1,0), (0,1), (-1,0), (0,-1)]

for combi in list(combinations(candidates, 3)) :

    localMap = copy.deepcopy(laboratory)
    tempVirus = [item for item in virus]
    for i in range(3) :
        localMap[combi[i][0]][combi[i][1]] = 1

    while tempVirus :

        vr, vc = tempVirus.pop(0)

        for dr, dc in dirset :
            nextR, nextC = vr + dr, vc + dc
            if nextR < 0 or nextC < 0 or nextR >= N or nextC >= M :
                continue;
            if localMap[nextR][nextC] == 2 or localMap[nextR][nextC] == 1 :
                continue;
            else :
                localMap[nextR][nextC] = 2
                tempVirus.append([nextR, nextC])

    safeZone = 0
    for i in range(N) :
        for j in range(M) :
            if localMap[i][j] == 0 :
                safeZone += 1

    if safeZone > maxArea :
        maxArea = safeZone

print(maxArea)
