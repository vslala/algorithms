def find_row(seat = "BFFFBBFRRR"):
    a = 64
    row = 0
    for i in range(0, len(seat) - 3):
        if seat[i] == 'B':
            row += a

        a = a//2

    return row
        
def find_col(seat = "BFFFBBFRRR"):
    b = 4
    col = 0
    for i in range(len(seat) - 3, len(seat)):
        if seat[i] == 'R':
            col += b
        
        b = b // 2
    
    return col

def get_seat_id(row, col):
    return (row * 8) + col

test_input = ["FBFBBFFRLR", "BFFFBBFRRR", "FFFBBBFRRR", "BBFFBBFRLL"]
for test in test_input:
    pass
    #    print(get_seat_id(find_row(test), find_col(test)))

seats = []
f = open("../inputs/day5.txt")
for line in f:
    line = line.strip()
    seat_id = get_seat_id(find_row(line), find_col(line))
    seats.append(seat_id)

#print(max(seats))

p2 = None
for id_ in sorted(seats):
    if  id_+1 not in seats and id_ + 2 in seats:
        p2 = id_ + 1

print(p2)
