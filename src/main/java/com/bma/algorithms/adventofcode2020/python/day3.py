f = open ("../inputs/day3.txt")

frame = []
for line  in f:
    line = line.strip()
    col = []
    for char in line:
        col.append(char)
    
    frame.append(col)

slopes = [(3,1)]

r = 0
c = 0
total =  1
for (dc,dr) in slopes:
    count = 0
    r += dr
    c += dc

    while r+1 < len(frame):
        c += dc
        r += dr
        if frame[r][c % len(frame[r])] == '#':
            count +=  1


    print(count)
    total = total * count
    
    
print(total)