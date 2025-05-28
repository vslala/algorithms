import fileinput
import re

L = [line.strip() for line in fileinput.input()]

limits = []
mine = None
other = []

for line in  L:
    ints = [int(x) for x in re.findall('\d+', line)]
    if len(ints) == 4:
        limits.append(ints)
    elif len(ints) > 4:
        if mine is None:
            mine = ints
        else:
            other.append(ints)

n = len(limits)
assert n == 20
part1 = 0
for tickets in other:
    assert len(tickets) == len(limits)
    valid_ticket = True
    
    for each_number in tickets:
        valid = False 
        for a,b,c,d  in limits:
            if a <= each_number <= b or c <= each_number <= d:
                valid = True

        if not valid:
            part1 += each_number
            valid_ticket = False 
    

print(part1)
