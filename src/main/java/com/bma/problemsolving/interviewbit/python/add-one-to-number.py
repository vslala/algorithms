class MostVisitedPatternLeetcode:

    def plusOne(self, A):
        N = len(A)
        carry = 1
        for i in range(N  - 1, -1, -1):
            if carry == 0:
                break
            
        
            num = A[i]
            sum = num + carry
            carry = sum // 10
            A[i]  = sum % 10

        if carry == 1:
            A.insert(0, carry)

        output = []
        is_prefix = True
        for i, digit in enumerate(A):
            if digit != 0:
                is_prefix  = False
            if digit == 0 and is_prefix:
                pass
            else:
                output.append(digit)
        
        output_num = ""
        for digit in output:
            output_num += str(digit)
        return output_num


A = [0,1,2,9]
A = [ 2, 5, 6, 8, 6, 1, 2, 4, 5 ]
A = [ 9, 9, 9, 9, 9 ]
A = [ 2, 6, 7, 8, 0, 2, 1, 5, 6, 3, 8, 3 ]
output = MostVisitedPatternLeetcode().plusOne(A)
print(output)
