
import sys

profile = sys.argv[1] if len(sys.argv)  == 2 else ""

def height_validator(val = ""):
    if not val[0:-2].isnumeric():
        return False

    if val[-2:] == "cm":
        return range_validator(val[:-2], 150, 193)
    
    if val[-2:]  == "in":
        return range_validator(val[:-2], 59, 76)

    return False

def range_validator(val, low, high):
    number = int(val)
    if low <= number <= high:
        return True

def color_validator(val):
    if val[0] != '#':
        return False
    
    if len(val) != 7:
        return False

    flag = True
    for index in range(1, len(val)):
        if not ('0' <= val[index] <= '9' or 'a' <= val[index] <= 'f'):
            flag = False
            break

    return flag

def number_validator(val = ""):
    return val.isdigit()

mandatory_fields = {
    "byr": lambda val: range_validator(val, 1920, 2002), 
    "iyr": lambda val: range_validator(val, 2010, 2020), 
    "eyr": lambda val: range_validator(val, 2020, 2030), 
    "hgt": lambda val: height_validator(val), 
    "hcl": lambda val: color_validator(val), 
    "ecl": lambda val: val in ("amb", "blu", "brn", "gry", "grn", "hzl", "oth"), 
    "pid": lambda val: len(val) == 9 and val.isdigit(),
    "cid": lambda val: True
}

def is_valid(details):

    for field in mandatory_fields:
        if field == "cid": continue
        if field not in details:
            return False
    
    for field in details:
        if not mandatory_fields[field](details[field]):
            return False
    
    return True

input_file = ""
if profile == "test":
    input_file = "../inputs/day4test.txt"
else: 
    input_file = "../inputs/day4.txt"

f = open(input_file)
all_lines = f.read().strip()
lines = all_lines.split("\n\n")

count = 0
for line in lines:
    line = line.strip().replace("\n", " ")

    details = {}
    for keyval in line.split(" "):
        key,val = keyval.split(":")[0], keyval.split(":")[1]
        details[key] = val
    
    if is_valid(details):
        count += 1


print (count)