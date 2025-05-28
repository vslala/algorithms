file = open("../inputs/day1.txt")

l = [1721,979,366,299,675,1456]
# for line in file:
#     l.append(int(line))

target = 2020

# Bruteforce O(n^2)
for i in range(0, len(l)):
    for j in range(i + 1, len(l)):
        if l[i] + l[j] == target:
            print(l[i] * l[j])
            break

# Two pointer approach -  O(nlogn)
l.sort()
left=0
right=len(l) - 1
while left < right:
    if l[left] + l[right] > target:
        right = right - 1
    elif l[left] + l[right] < target:
        left = left + 1
    else:
        print(l[left] * l[right])
        break

# O(n)
# 1721,979,366,299,675,1456
d = {}
for item in l:
    if item in d:
        print(d[item] * item)
        break
    
    d[target - item] = item

