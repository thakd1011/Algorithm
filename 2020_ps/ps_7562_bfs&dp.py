from collections import deque

# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    T = int(input())
    for tc in range(T) :
        N = int(input())
        knightR, knightC = [int(item) for item in input().split(' ')]
        destR, destC = [int(item) for item in input().split(' ')]
        chessMap = [[0 for i in range(N)] for j in range(N)]

        q = deque()
        q.append([knightR, knightC])
        dirs = [(-1, -2), (-2, -1), (-2, 1), (-1, 2), (1, -2), (2, -1), (2, 1), (1, 2)]
        chessMap[knightR][knightC] = 1

        while q :
            tempR, tempC = q.popleft()
            if tempR == destR and tempC == destC :
                print(chessMap[tempR][tempC] - 1)
                break;

            for dr, dc in dirs :
                nextR, nextC = tempR + dr, tempC + dc
                if nextR < 0 or nextC < 0 or nextR >= N or nextC >= N :
                    continue;
                if chessMap[nextR][nextC] == 0 :
                    q.append([nextR, nextC])
                    chessMap[nextR][nextC] = chessMap[tempR][tempC] + 1

""" 
조건을 잘 읽어보면 알 수 있는 점 : 최소한으로 움직여서 도달하는 방법을 찾아야 하지?
그럼 이미 방문했다는 걸 체크하는 건 N번의 횟수를 거치니까 시간이 더 들게 돼
2차원 배열의 내부에 다른 의미있는 값이 주어지지 않는다면,
2차원 배열을 visited 개념으로 쓸 수 있는 거지!

TIP :
각 방향을 돌아보면서 다음 단계를 체크하는 for문 내부에서 visited 여부를 체크하는 게 시간 단축에 도움이 된다.
그 다음 while문의 횟수를 줄일 수 있는 거야 ㅇㅋ?

"""