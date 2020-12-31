class Solution:
    def solve (self, A,B):
        last = -1
        N = len(A)
        count = 0
        while last < N - 1:
            pos = -1
            start = max(-1, last - B + 1)
            end = min(N - 1, last + B)

            for i in range(end, start, -1):
                if A[i] == 1 and last >= i - B:
                    pos = i
                    break

            if pos == -1:
                return -1

            count += 1
            last = pos + B - 1

        return count



A = [0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0]
B = 12
ans = Solution().solve(A,B)
print("Ans:", ans)

