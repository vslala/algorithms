f = open ("../inputs/day3.txt")

frame = []
for line  in f:
    col = []
    for char in line:
        col.append(char)
    
    frame.append(col)

slopes = [[3,1]]

count = 0
for slope in slopes:
    x = slope[0]
    y = slope[1]

    while x < len(frame):
        if frame[x][y % len(frame[x])]:
            count += 1

        y += slope[1]
        x += slope[0]

    print(count)