import fileinput

lines = [line.strip() for line in fileinput.input()]

# since we are relying on the empty line
# to identify the group of people
# empty line at the end is required to count 
# the last group
lines.append('')

# we need all the unique answers
# set is the correct datastructure to keep track of
# distinct objects
yes_questions = set()
all_yes = None
any_yes = None
sum1 = 0
sum2 = 0
for line in lines:
    if not line:
        # if we encounter an empty line
        # count the total questions answered YES
        # and reset the set
        sum1 += len(any_yes)
        sum2 += len(all_yes)
        all_yes = None
        any_yes = None
    else:
        if all_yes is None:
            all_yes = set(line)
        else:
            all_yes = all_yes & set(line)
        
        if any_yes is None:
            any_yes = set(line)
        else:
            any_yes = any_yes | set(line)


print(sum1)
print(sum2)
