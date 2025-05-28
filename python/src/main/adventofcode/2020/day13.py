import math

f = open("../inputs/day13.txt")
lines = []
for line in f:
    lines.append(line.strip())

# 939
#7,13,x,x,59,x,31,19
t0 = int(lines[0])
bus_ids = [int(bus_id) for bus_id in lines[1].split(",") if bus_id != 'x']

best = None
for b in bus_ids:
    t = t0
    while t % b != 0:
        t += 1

    score = t - t0
    if best is None or score < best[0]:
        best = (score, b)

print("PART ONE:", best[0] * best[1])


# PART - TWO
inputs = []
constraints = []
N = 1
for (i,b) in enumerate(lines[1].strip().split(",")):
    if b != 'x':
        b = int(b)
        inputs.append((i,b));
        constraints.append(( (b-i) % b,b))
        N *= b

# NI = N/b
NI_DEBUG = []
ans = 0
for i,b in constraints:
    NI = int(N/b)
    NI_DEBUG.append(NI)
    
    # always be 1
    assert math.gcd(NI, b) == 1
    
    mi = pow(NI, -1, b)

    # mod_inverse of NI, b, when divided by b, the remainder will always be 1
    assert (mi * NI) % b == 1
    assert (i * mi * NI) % b == 1 * i

    for_b = i * mi * NI
    assert for_b % b == i
    assert for_b % NI == 0

    ans += for_b

ans = ans % N
for i,b in constraints:
    assert ans % b == i

print ("PART TWO: ", ans)


print("Input:\t\t\t", inputs)
print("Constaints:\t\t", constraints)
print("Product:\t\t", N)
print("NI\t\t\t", NI_DEBUG)
