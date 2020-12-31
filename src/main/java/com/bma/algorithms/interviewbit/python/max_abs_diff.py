import sys

def maxArr(A):
    max1 = sys.maxsize * -1
    max2 = sys.maxsize *  -1
    min1 = sys.maxsize
    min2 = sys.maxsize

    for i in range(0, len(A)):
        max1 = max(max1, A[i] + i)
        min1 = min(min1, A[i] + i)
        max2 = max(max2, A[i] - i)
        min2 = min(min2, A[i] - i)

    return max(abs(max1 - min1), abs(max2 - min2))

A = [1, 3, -1]
ans = maxArr(A)
print(ans)
