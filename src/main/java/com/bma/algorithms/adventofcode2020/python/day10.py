f = open("../inputs/day10test.txt")

arr = [0]
for item in f:
    arr.append(int(item))

print  (arr)
arr.sort()
arr.append(arr[-1] + 3)
print (arr)

one_diff = 0
three_diff = 0

for i in range(0, len(arr) - 1):
        if arr[i + 1] - arr[i] == 1:
            one_diff += 1
        if arr[i + 1] - arr[i] == 3:
            three_diff += 1

# PART ONE
print (one_diff, three_diff)

# PART TWO
d =  {}
for item in arr:
    d[item] = 0

d[0] = 1

for place in arr:
    for valid_place in range(1, 4):
        if place + valid_place in d:
            d[place + valid_place] += d[place]

print (d)
