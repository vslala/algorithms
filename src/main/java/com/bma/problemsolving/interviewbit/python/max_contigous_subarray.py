def maxSum(arr):
    total = 0
    best = 0

    for i in range(0, len(arr)):
        total = max(arr[i], total + arr[i])
        best = max(best, total)

    return best


arr = [-1,2,4,-3,5,2,-5,2]
ans = maxSum(arr)
print(ans)
