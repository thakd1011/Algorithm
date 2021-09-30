
def solution(people, limit):
    answer = 0
    start = 0
    end = len(people) - 1
    people = sorted(people, reverse=True)

    while start <= end:
        if start == end:
            if people[start] <= limit:
                answer += 1
                break;
        else:
            if people[start] + people[end] <= limit:
                start += 1
                end -= 1
            else:
                start += 1
        answer += 1
        
    return answer

def main():
    solution([70, 50, 80, 50], 100)

if __name__ == "__main__":
    main()
