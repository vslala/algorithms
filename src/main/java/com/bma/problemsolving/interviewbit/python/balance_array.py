class MostVisitedPatternLeetcode:
    # @param A : list of integers
    # @return an integer
    def solve(self, A):
        pre = []
        post = [0]*len(A)
        val = 0
        for index, value in enumerate(A):
            if index % 2 == 0:
                val  += value
            else:
                val -=  value
            pre.append(val)
            
        val  = 0
        for index in range(len(A) - 1, -1, -1):
            if index % 2 == 0:
                val += A[index]
            else:
                val -= A[index]
            post[index] = val
            
        count = 0
        for i in range(0, len(A)):
            if pre[i] == post[i]:
                count += 1
                
        return count

A = [5, 5, 2, 5, 8]
ans = MostVisitedPatternLeetcode().solve(A)
print(ans)
